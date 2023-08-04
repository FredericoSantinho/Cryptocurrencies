package neuro.cryptocurrencies.presentation.viewmodel.coins.details

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
import neuro.cryptocurrencies.domain.usecase.coin.details.HasCachedCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coin.details.ObserveCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.error.NoDataAvailableException
import neuro.cryptocurrencies.domain.usecase.tag.FetchTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tag.HasCachedTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tag.ObserveTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.team.FetchTeamMemberUseCase
import neuro.cryptocurrencies.domain.usecase.team.HasCachedTeamMemberDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.team.ObserveTeamMemberDetailsUseCase
import neuro.cryptocurrencies.presentation.mapper.toPresentation
import neuro.cryptocurrencies.presentation.model.DialogText
import neuro.cryptocurrencies.presentation.model.ErrorMessage
import neuro.cryptocurrencies.presentation.model.TagModel
import neuro.cryptocurrencies.presentation.model.TeamModel

class CoinDetailsViewModelImpl(
	private val observeCoinDetailsUseCase: ObserveCoinDetailsUseCase,
	private val fetchCoinDetailsUseCase: FetchCoinDetailsUseCase,
	private val hasCachedCoinDetailsUseCase: HasCachedCoinDetailsUseCase,
	private val observeTagDetailsUseCase: ObserveTagDetailsUseCase,
	private val fetchTagDetailsUseCase: FetchTagDetailsUseCase,
	private val hasCachedTagDetailsUsecase: HasCachedTagDetailsUseCase,
	private val observeTeamMemberDetailsUseCase: ObserveTeamMemberDetailsUseCase,
	private val fetchTeamMemberUseCase: FetchTeamMemberUseCase,
	private val hasCachedTeamMemberDetailsUseCase: HasCachedTeamMemberDetailsUseCase,
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
		_uiState.value = uiState.value.copy(errorMessage = ErrorMessage.Empty)
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
		_uiState.value =
			uiState.value.copy(showDialog = false, dialogTitle = "", dialogText = DialogText.Empty)
	}

	override fun onTagClick(tagModel: TagModel) {
		_uiState.value =
			uiState.value.copy(showDialog = true, dialogTitle = tagModel.name, dialogLoading = true)
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
			}.catch {
				_uiState.value =
					uiState.value.copy(
						isError = true,
						errorMessage = it.localizedMessage?.let { ErrorMessage.GivenMessage(it) }
							?: ErrorMessage.UnexpectedErrorOccurred,
						isLoading = false,
						isRefreshing = false
					)
			}
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
							errorMessage = throwable1.localizedMessage?.let { ErrorMessage.GivenMessage(it) }
								?: ErrorMessage.UnexpectedErrorOccurred,
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
								isError = true,
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
			observeTagDetailsUseCase.execute(tagModel.id).flowOn(Dispatchers.IO).onEach { tagDetails ->
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
		viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
			// In case a network error occurs.
			viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable1 ->
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
				val hasCachedTagDetails = hasCachedTagDetailsUsecase.execute(tagModel.id)
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

	private fun observeTeamMember(teamModel: TeamModel) {
		_uiState.value =
			uiState.value.copy(showDialog = true, dialogTitle = teamModel.name, dialogLoading = true)
		dialogFeedingJob.value =
			observeTeamMemberDetailsUseCase.execute(teamModel.id).flowOn(Dispatchers.IO)
				.onEach { teamMemberDetails ->
					_uiState.value =
						uiState.value.copy(
							dialogText = DialogText.GivenText(teamMemberDetails.description),
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

	private fun fetchTeamMember(teamModel: TeamModel) {
		viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
			// In case a network error occurs.
			viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable1 ->
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
				val hasCachedTeamMemberDetails = hasCachedTeamMemberDetailsUseCase.execute(teamModel.id)
				withContext(Dispatchers.Main) {
					if (!hasCachedTeamMemberDetails) {
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
						throwable.printStackTrace()
					}
				}
			}
		}) {
			fetchTeamMemberUseCase.execute(teamModel.id)
		}
	}

	override fun onTeamMemberClick(teamModel: TeamModel) {
		_uiState.value =
			uiState.value.copy(
				showDialog = true,
				dialogTitle = "${teamModel.name} (${teamModel.position})",
				dialogLoading = true
			)
		observeTeamMember(teamModel)
		fetchTeamMember(teamModel)
	}

	companion object {
		const val PARAM_COIN_ID = "coinId"
	}
}