package neuro.cryptocurrencies.presentation.viewmodel.coins.details

import android.app.Application
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
import neuro.cryptocurrencies.domain.usecase.coin.details.GetCoinDetailsWithPriceUseCase
import neuro.cryptocurrencies.domain.usecase.tag.FetchTagUseCase
import neuro.cryptocurrencies.domain.usecase.tag.GetTagUseCase
import neuro.cryptocurrencies.domain.usecase.tag.ObserveTagUseCase
import neuro.cryptocurrencies.presentation.R
import neuro.cryptocurrencies.presentation.mapper.toPresentation
import neuro.cryptocurrencies.presentation.model.TagModel
import neuro.cryptocurrencies.presentation.model.TeamModel

class CoinDetailsViewModelImpl(
	private val getCoinDetailsWithPriceUseCase: GetCoinDetailsWithPriceUseCase,
	private val observeTagUseCase: ObserveTagUseCase,
	private val fetchTagUseCase: FetchTagUseCase,
	private val getTagUsecase: GetTagUseCase,
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
			getCoinDetails(it)
		}
	}

	override fun errorShown() {
		_uiState.value = uiState.value.copy(errorMessage = "")
	}

	override fun onRetry() {
		_uiState.value = uiState.value.copy(isLoading = true, isError = false)
		getCoinDetails(coinId)
	}

	override fun onRefresh() {
		_uiState.value = uiState.value.copy(isRefreshing = true)
		getCoinDetails(coinId)
	}

	override fun onDialogDismiss() {
		dialogFeedingJob.value?.cancel()
		_uiState.value = uiState.value.copy(showDialog = false, dialogTitle = "", dialogText = "")
	}

	override fun onTagClick(tagModel: TagModel) {
		observeTag(tagModel)
		fetchTag(tagModel)
	}

	private fun observeTag(tagModel: TagModel) {
		_uiState.value =
			uiState.value.copy(showDialog = true, dialogTitle = tagModel.name, dialogLoading = true)
		dialogFeedingJob.value =
			observeTagUseCase.execute(tagModel.id).flowOn(Dispatchers.IO).onEach { tagDetails ->
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
			viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
				// In case a database error occurs.
				viewModelScope.launch {
					_uiState.value =
						uiState.value.copy(
							errorMessage = throwable.localizedMessage ?: "Unexpected error occurred!",
							dialogText = context.getString(R.string.unexpected_error),
							dialogLoading = false
						)
				}
			}) {
				val tagDetails = getTagUsecase.execute(tagModel.id)
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
			fetchTagUseCase.execute(tagModel.id)
		}
	}

	override fun onTeamMemberClick(teamModel: TeamModel) {
		_uiState.value = uiState.value.copy(
			showDialog = true,
			dialogTitle = "${teamModel.name} (${teamModel.position})",
			dialogLoading = true
		)
	}

	private fun getCoinDetails(coinId: String) {
		viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
			viewModelScope.launch {
				_uiState.value =
					uiState.value.copy(
						errorMessage = throwable.localizedMessage ?: "Unexpected error occurred!",
						isError = true,
						isLoading = false,
						isRefreshing = false
					)
			}
		}) {
			val coinDetailsWithPriceModel =
				getCoinDetailsWithPriceUseCase.execute(coinId, viewModelScope).toPresentation()
			withContext(Dispatchers.Main) {
				_uiState.value =
					uiState.value.copy(
						coinDetailsWithPriceModel = coinDetailsWithPriceModel,
						isLoading = false,
						isError = false,
						isRefreshing = false
					)
			}
		}
	}

	companion object {
		const val PARAM_COIN_ID = "coinId"
	}
}