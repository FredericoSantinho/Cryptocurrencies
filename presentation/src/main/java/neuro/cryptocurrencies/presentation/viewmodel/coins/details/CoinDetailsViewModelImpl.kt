package neuro.cryptocurrencies.presentation.viewmodel.coins.details

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import neuro.cryptocurrencies.domain.usecase.coin.details.FetchCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coin.details.GetCachedCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coin.details.ObserveCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tag.FetchTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tag.GetTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tag.ObserveTagDetailsUseCase
import neuro.cryptocurrencies.presentation.R
import neuro.cryptocurrencies.presentation.mapper.toPresentation
import neuro.cryptocurrencies.presentation.model.TagModel
import neuro.cryptocurrencies.presentation.model.TeamModel

class CoinDetailsViewModelImpl(
	private val observeCoinDetailsUseCase: ObserveCoinDetailsUseCase,
	private val fetchCoinDetailsUseCase: FetchCoinDetailsUseCase,
	private val getCachedCoinDetailsUseCase: GetCachedCoinDetailsUseCase,
	private val observeTagDetailsUseCase: ObserveTagDetailsUseCase,
	private val fetchTagDetailsUseCase: FetchTagDetailsUseCase,
	private val getTagDetailsUsecase: GetTagDetailsUseCase,
	private val context: Application,
	savedStateHandle: SavedStateHandle
) : ViewModel(), CoinDetailsViewModel {
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
		_uiState.value = uiState.value.copy(errorMessage = "")
	}

	override fun onRetry() {
		_uiState.value = uiState.value.copy(isLoading = true, isError = false)
		fetchCoinDetailsWithPrice()
	}

	override fun onRefresh() {
		_uiState.value = uiState.value.copy(isRefreshing = true)
		fetchCoinDetailsWithPrice()
	}

	override fun onDialogDismiss() {
		dialogFeedingJob.value?.cancel()
		_uiState.value = uiState.value.copy(showDialog = false, dialogTitle = "", dialogText = "")
	}

	override fun onTagClick(tagModel: TagModel) {
		observeTag(tagModel)
		fetchTag(tagModel)
	}

	private fun observeCoinDetailsWithPrice() {
		observeCoinDetailsUseCase.execute(coinId).flowOn(Dispatchers.IO)
			.onEach { coinDetailsWithPrice ->
				_uiState.value =
					uiState.value.copy(
						coinDetailsWithPriceModel = coinDetailsWithPrice.toPresentation(),
						isLoading = false,
						isError = false,
						isRefreshing = false
					)
			}.catch { Log.e("TAG", "observeCoinDetailsWithPrice: ", it) }
			.launchIn(viewModelScope)
	}

	private fun fetchCoinDetailsWithPrice() {
		viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
			// In case a network error occurs.
			viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable1 ->
				// In case a database error occurs.
				viewModelScope.launch {
					_uiState.value =
						uiState.value.copy(
							errorMessage = throwable1.localizedMessage ?: "Unexpected error occurred!",
						)
				}
			}) {
				val coinDetailsWithPrice = getCachedCoinDetailsUseCase.execute(coinId)
				withContext(Dispatchers.Main) {
					if (coinDetailsWithPrice == null) {
						_uiState.value =
							uiState.value.copy(
								errorMessage = throwable.localizedMessage ?: "Unexpected error occurred!",
								isError = true,
								isLoading = false,
								isRefreshing = false
							)
					} else {
						if (uiState.value.isRefreshing) {
							_uiState.value = uiState.value.copy(
								isRefreshing = false,
								errorMessage = throwable.localizedMessage ?: "Unexpected error occurred!"
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
		_uiState.value =
			uiState.value.copy(showDialog = true, dialogTitle = tagModel.name, dialogLoading = true)
		dialogFeedingJob.value =
			observeTagDetailsUseCase.execute(tagModel.id).flowOn(Dispatchers.IO).onEach { tagDetails ->
				_uiState.value =
					uiState.value.copy(dialogText = tagDetails.description, dialogLoading = false)
			}.catch { throwable ->
				_uiState.value = uiState.value.copy(
					errorMessage = throwable.localizedMessage ?: "Unexpected error occurred!",
					dialogText = context.getString(R.string.error_retrieving_data),
					dialogLoading = false
				)
			}.launchIn(viewModelScope)
	}

	private fun fetchTag(tagModel: TagModel) {
		viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
			// In case a network error occurs.
			viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable1 ->
				// In case a database error occurs.
				viewModelScope.launch {
					_uiState.value =
						uiState.value.copy(
							errorMessage = throwable1.localizedMessage ?: "Unexpected error occurred!",
							dialogText = context.getString(R.string.unexpected_error),
							dialogLoading = false
						)
				}
			}) {
				val tagDetails = getTagDetailsUsecase.execute(tagModel.id)
				withContext(Dispatchers.Main) {
					if (tagDetails == null) {
						_uiState.value =
							uiState.value.copy(
								errorMessage = throwable.localizedMessage ?: "Unexpected error occurred!",
								dialogText = context.getString(R.string.error_retrieving_data),
								dialogLoading = false
							)
					}
				}
			}
		}) {
			fetchTagDetailsUseCase.execute(tagModel.id)
		}
	}

	override fun onTeamMemberClick(teamModel: TeamModel) {
		_uiState.value = uiState.value.copy(
			showDialog = true,
			dialogTitle = "${teamModel.name} (${teamModel.position})",
			dialogLoading = true
		)
	}

	companion object {
		const val PARAM_COIN_ID = "coinId"
	}
}