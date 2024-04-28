package neuro.cryptocurrencies.presentation.viewmodel.coinDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import neuro.cryptocurrencies.domain.usecase.coinDetails.FetchCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coinDetails.HasCachedCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coinDetails.ObserveCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.error.NoDataAvailableException
import neuro.cryptocurrencies.domain.usecase.tagDetails.FetchTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tagDetails.HasCachedTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tagDetails.ObserveTagDetailsUseCase
import neuro.cryptocurrencies.presentation.mapper.toPresentation
import neuro.cryptocurrencies.presentation.model.DialogText
import neuro.cryptocurrencies.presentation.model.ErrorMessage
import neuro.cryptocurrencies.presentation.model.TagModel
import neuro.cryptocurrencies.presentation.ui.base.BaseViewModel

class CoinDetailsViewModelImpl(
	private val observeCoinDetailsUseCase: ObserveCoinDetailsUseCase,
	private val fetchCoinDetailsUseCase: FetchCoinDetailsUseCase,
	private val hasCachedCoinDetailsUseCase: HasCachedCoinDetailsUseCase,
	private val observeTagDetailsUseCase: ObserveTagDetailsUseCase,
	private val fetchTagDetailsUseCase: FetchTagDetailsUseCase,
	private val hasCachedTagDetailsUseCase: HasCachedTagDetailsUseCase,
	savedStateHandle: SavedStateHandle,
	private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel(), CoinDetailsViewModel {
	private val _uiState = mutableStateOf(CoinDetailsState(isLoading = true))
	override val uiState: State<CoinDetailsState> = _uiState

	private lateinit var coinId: String

	private val dialogFeedingJob = mutableStateOf<Job?>(null)

	init {
		savedStateHandle.get<String>(PARAM_COIN_ID)?.let {
			coinId = it
		}
		observeCoinDetailsWithPrice()
		fetchCoinDetailsWithPrice()
	}

	override fun errorShown() {
		_uiState.value = uiState.value.copy(errorMessage = ErrorMessage.Empty)
	}

	override fun onRetry() {
		_uiState.value = uiState.value.copy(isLoading = true, isErrorState = false)
		fetchCoinDetailsWithPrice()
	}

	override fun onRefresh() {
		_uiState.value = uiState.value.copy(isRefreshing = true)
		fetchCoinDetailsWithPrice()
	}

	override fun onDialogDismiss() {
		dialogFeedingJob.value?.cancel()
		_uiState.value =
			uiState.value.copy(showDialog = false, dialogTitle = null, dialogText = DialogText.Empty)
	}

	override fun onTagClick(tagModel: TagModel) {
		_uiState.value =
			uiState.value.copy(showDialog = true, dialogTitle = tagModel.name, dialogLoading = true)
		observeTag(tagModel)
		fetchTag(tagModel)
	}

	private fun observeCoinDetailsWithPrice() {
		observeCoinDetailsUseCase.execute(coinId).flowOn(ioDispatcher)
			.onEach { coinDetailsWithPrice ->
				_uiState.value =
					uiState.value.copy(
						coinDetailsWithPriceModel = coinDetailsWithPrice.toPresentation(),
						isLoading = false,
						isErrorState = false,
						isRefreshing = false
					)
			}.catch {
				_uiState.value =
					uiState.value.copy(
						isErrorState = true,
						errorMessage = it.localizedMessage?.let { ErrorMessage.GivenMessage(it) }
							?: ErrorMessage.UnexpectedErrorOccurred,
						isLoading = false,
						isRefreshing = false
					)
			}
			.launchIn(viewModelScope)
	}

	private fun fetchCoinDetailsWithPrice() {
		viewModelScope.launch(ioDispatcher + CoroutineExceptionHandler { _, throwable ->
			// In case a network error occurs.
			viewModelScope.launch(ioDispatcher + CoroutineExceptionHandler { _, throwable1 ->
				// In case a database error occurs.
				viewModelScope.launch {
					_uiState.value =
						uiState.value.copy(
							isErrorState = true,
							errorMessage = throwable1.localizedMessage?.let { ErrorMessage.GivenMessage(it) }
								?: ErrorMessage.UnexpectedErrorOccurred,
							isLoading = false,
							isRefreshing = false
						)
				}
			}) {
				val hasCachedCoinDetails = hasCachedCoinDetailsUseCase.execute(coinId)
				withContext(Dispatchers.Main) {
					if (!hasCachedCoinDetails) {
						_uiState.value =
							uiState.value.copy(
								errorMessage = throwable.localizedMessage?.let { ErrorMessage.GivenMessage(it) }
									?: ErrorMessage.UnexpectedErrorOccurred,
								isErrorState = true,
								isLoading = false,
								isRefreshing = false
							)
					} else {
						if (uiState.value.isRefreshing) {
							_uiState.value = uiState.value.copy(
								isRefreshing = false,
								errorMessage = throwable.localizedMessage?.let { ErrorMessage.GivenMessage(it) }
									?: ErrorMessage.UnexpectedErrorOccurred
							)
						}
					}
				}
			}
		}) {
			fetchCoinDetailsUseCase.execute(coinId, viewModelScope)
		}
	}

	private fun observeTag(tagModel: TagModel) {
		dialogFeedingJob.value =
			observeTagDetailsUseCase.execute(tagModel.id).flowOn(ioDispatcher).onEach { tagDetails ->
				_uiState.value =
					uiState.value.copy(
						dialogText = DialogText.GivenText(tagDetails.description),
						dialogLoading = false
					)
			}.catch { throwable ->
				_uiState.value = uiState.value.copy(
					errorMessage = throwable.localizedMessage?.let { ErrorMessage.GivenMessage(it) }
						?: ErrorMessage.UnexpectedErrorOccurred,
					dialogText = DialogText.UnexpectedError,
					dialogLoading = false
				)
			}.launchIn(viewModelScope)
	}

	private fun fetchTag(tagModel: TagModel) {
		viewModelScope.launch(ioDispatcher + CoroutineExceptionHandler { _, throwable ->
			// In case a network error occurs.
			viewModelScope.launch(ioDispatcher + CoroutineExceptionHandler { _, throwable1 ->
				// In case a database error occurs.
				viewModelScope.launch {
					_uiState.value =
						uiState.value.copy(
							errorMessage = throwable1.localizedMessage?.let { ErrorMessage.GivenMessage(it) }
								?: ErrorMessage.UnexpectedErrorOccurred,
							dialogText = DialogText.UnexpectedError,
							dialogLoading = false
						)
				}
			}) {
				val hasCachedTagDetails = hasCachedTagDetailsUseCase.execute(tagModel.id)
				withContext(Dispatchers.Main) {
					if (!hasCachedTagDetails) {
						if (throwable is NoDataAvailableException) {
							_uiState.value =
								uiState.value.copy(
									dialogText = DialogText.NoDataAvailable,
									dialogLoading = false
								)
						} else {
							_uiState.value =
								uiState.value.copy(
									dialogText = DialogText.ErrorRetrievingData,
									dialogLoading = false
								)
						}
					}
				}
			}
		}) {
			fetchTagDetailsUseCase.execute(tagModel.id)
		}
	}

	companion object {
		const val PARAM_COIN_ID = "coinId"
	}
}