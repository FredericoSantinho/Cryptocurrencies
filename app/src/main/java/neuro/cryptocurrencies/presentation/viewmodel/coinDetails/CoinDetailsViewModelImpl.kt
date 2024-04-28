package neuro.cryptocurrencies.presentation.viewmodel.coinDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice
import neuro.cryptocurrencies.domain.entity.TagDetails
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
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModelImpl @Inject constructor(
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
		loadParams(savedStateHandle)
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

	private fun loadParams(savedStateHandle: SavedStateHandle) {
		savedStateHandle.get<String>(PARAM_COIN_ID)?.let {
			coinId = it
		}
	}

	private fun observeCoinDetailsWithPrice() {
		observeCoinDetailsUseCase.execute(coinId).flowOn(ioDispatcher)
			.onEach { coinDetailsWithPrice -> setCoinDetails(coinDetailsWithPrice) }
			.catch { showErrorAndSetErrorState(it) }
			.launchIn(viewModelScope)
	}

	private fun fetchCoinDetailsWithPrice() {
		viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
			handleFetchCoinDetailsError(throwable)
		}) {
			fetchCoinDetailsUseCase.execute(coinId)
		}
	}

	private fun observeTag(tagModel: TagModel) {
		dialogFeedingJob.value =
			observeTagDetailsUseCase.execute(tagModel.id).flowOn(ioDispatcher)
				.onEach { tagDetails -> setDialog(tagDetails) }
				.catch { throwable -> setDialogErrorState(throwable) }.launchIn(viewModelScope)
	}

	private fun fetchTag(tagModel: TagModel) {
		viewModelScope.launch(ioDispatcher + CoroutineExceptionHandler { _, throwable ->
			handleFetchTagDetailsError(throwable, tagModel)
		}) {
			fetchTagDetailsUseCase.execute(tagModel.id)
		}
	}

	private fun setCoinDetails(coinDetailsWithPrice: CoinDetailsWithPrice) {
		_uiState.value =
			uiState.value.copy(
				coinDetailsWithPriceModel = coinDetailsWithPrice.toPresentation(),
				isLoading = false,
				isErrorState = false,
				isRefreshing = false
			)
	}

	private fun setDialog(tagDetails: TagDetails) {
		_uiState.value =
			uiState.value.copy(
				dialogText = DialogText.GivenText(tagDetails.description),
				dialogLoading = false
			)
	}

	private fun setDialogErrorState(throwable: Throwable) {
		_uiState.value = uiState.value.copy(
			errorMessage = throwable.localizedMessage?.let { ErrorMessage.GivenMessage(it) }
				?: ErrorMessage.UnexpectedErrorOccurred,
			dialogText = DialogText.UnexpectedError,
			dialogLoading = false
		)
	}

	private fun setDialogNoDataAvailable() {
		_uiState.value =
			uiState.value.copy(
				dialogText = DialogText.NoDataAvailable,
				dialogLoading = false
			)
	}

	private fun setDialogErrorRetrievingData() {
		_uiState.value =
			uiState.value.copy(
				dialogText = DialogText.ErrorRetrievingData,
				dialogLoading = false
			)
	}

	private fun showError(throwable: Throwable) {
		_uiState.value = uiState.value.copy(
			isRefreshing = false,
			errorMessage = throwable.localizedMessage?.let { ErrorMessage.GivenMessage(it) }
				?: ErrorMessage.UnexpectedErrorOccurred
		)
	}

	private fun showErrorAndSetErrorState(throwable: Throwable) {
		_uiState.value =
			uiState.value.copy(
				errorMessage = throwable.localizedMessage?.let { ErrorMessage.GivenMessage(it) }
					?: ErrorMessage.UnexpectedErrorOccurred,
				isErrorState = true,
				isLoading = false,
				isRefreshing = false
			)
	}

	private fun handleFetchCoinDetailsError(throwable: Throwable) {
		viewModelScope.launch(CoroutineExceptionHandler { _, throwable1 ->
			showErrorAndSetErrorState(throwable1)
		}) {
			handleErrorIfNoCachedCoinDetails(throwable)
		}
	}

	private suspend fun handleErrorIfNoCachedCoinDetails(throwable: Throwable) {
		val hasCachedCoinDetails = hasCachedCoinDetailsUseCase.execute(coinId)
		if (!hasCachedCoinDetails) {
			showErrorAndSetErrorState(throwable)
		} else {
			if (uiState.value.isRefreshing) {
				showError(throwable)
			}
		}
	}

	private fun handleFetchTagDetailsError(
		throwable: Throwable,
		tagModel: TagModel
	) {
		viewModelScope.launch(ioDispatcher + CoroutineExceptionHandler { _, throwable1 ->
			// In case a database error occurs.
			setDialogErrorState(throwable)
		}) {
			handleErrorIfNoCachedTagDetails(tagModel, throwable)
		}
	}

	private suspend fun handleErrorIfNoCachedTagDetails(
		tagModel: TagModel,
		throwable: Throwable
	) {
		val hasCachedTagDetails = hasCachedTagDetailsUseCase.execute(tagModel.id)
		if (!hasCachedTagDetails) {
			if (throwable is NoDataAvailableException) {
				setDialogNoDataAvailable()
			} else {
				setDialogErrorRetrievingData()
			}
		}
	}

	companion object {
		const val PARAM_COIN_ID = "coinId"
	}
}