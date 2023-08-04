package neuro.cryptocurrencies.presentation.viewmodel.coins.details

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice
import neuro.cryptocurrencies.domain.mocks.coinDetailsWithPriceMock
import neuro.cryptocurrencies.domain.usecase.coin.details.FetchCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coin.details.HasCachedCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coin.details.ObserveCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tag.FetchTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tag.HasCachedTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tag.ObserveTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.team.FetchTeamMemberDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.team.HasCachedTeamMemberDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.team.ObserveTeamMemberDetailsUseCase
import neuro.cryptocurrencies.presentation.MainDispatcherRule
import neuro.cryptocurrencies.presentation.mocks.coinDetailsModelMock
import neuro.cryptocurrencies.presentation.mocks.coinDetailsWithPriceModelMock
import neuro.cryptocurrencies.presentation.model.CoinDetailsWithPriceModel
import neuro.cryptocurrencies.presentation.model.ErrorMessage
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

class CoinDetailsViewModelImplTest {

	@get:Rule
	val mainDispatcherRule = MainDispatcherRule()

	@Test
	fun testHappyPath() = runTest(timeout = Duration.parse("1m")) {

		val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase>()
		val fetchCoinDetailsUseCase = mock<FetchCoinDetailsUseCase>()
		val hasCachedCoinDetailsUseCase = mock<HasCachedCoinDetailsUseCase>()
		val observeTagDetailsUseCase = mock<ObserveTagDetailsUseCase>()
		val fetchTagDetailsUseCase = mock<FetchTagDetailsUseCase>()
		val hasCachedTagDetailsUseCase = mock<HasCachedTagDetailsUseCase>()
		val observeTeamMemberDetailsUseCase = mock<ObserveTeamMemberDetailsUseCase>()
		val fetchTeamMemberDetailsUseCase = mock<FetchTeamMemberDetailsUseCase>()
		val hasCachedTeamMemberDetailsUseCase = mock<HasCachedTeamMemberDetailsUseCase>()
		val coinId = "btc-bitcoin"
		val savedStateHandle = SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))
		val testIoDispatcher = StandardTestDispatcher()

		whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(flow {
			emit(
				coinDetailsWithPriceMock()
			)
		})

		val coinDetailsViewModel = CoinDetailsViewModelImpl(
			observeCoinDetailsUseCase,
			fetchCoinDetailsUseCase,
			hasCachedCoinDetailsUseCase,
			observeTagDetailsUseCase,
			fetchTagDetailsUseCase,
			hasCachedTagDetailsUseCase,
			observeTeamMemberDetailsUseCase,
			fetchTeamMemberDetailsUseCase,
			hasCachedTeamMemberDetailsUseCase,
			savedStateHandle,
			testIoDispatcher
		)

		verifyNoInteractions(fetchCoinDetailsUseCase)

		assertEquals(CoinDetailsState(isLoading = true), coinDetailsViewModel.uiState.value)

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
		verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId), any())

		assertEquals(
			CoinDetailsState(
				coinDetailsWithPriceModel = coinDetailsWithPriceModelMock(),
				isError = false,
				isLoading = false,
				isRefreshing = false
			), coinDetailsViewModel.uiState.value
		)
	}

	@Test
	fun testRefresh() = runTest(timeout = Duration.parse("1m")) {

		val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase>()
		val fetchCoinDetailsUseCase = mock<FetchCoinDetailsUseCase>()
		val hasCachedCoinDetailsUseCase = mock<HasCachedCoinDetailsUseCase>()
		val observeTagDetailsUseCase = mock<ObserveTagDetailsUseCase>()
		val fetchTagDetailsUseCase = mock<FetchTagDetailsUseCase>()
		val hasCachedTagDetailsUseCase = mock<HasCachedTagDetailsUseCase>()
		val observeTeamMemberDetailsUseCase = mock<ObserveTeamMemberDetailsUseCase>()
		val fetchTeamMemberDetailsUseCase = mock<FetchTeamMemberDetailsUseCase>()
		val hasCachedTeamMemberDetailsUseCase = mock<HasCachedTeamMemberDetailsUseCase>()
		val coinId = "btc-bitcoin"
		val savedStateHandle = SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))
		val testIoDispatcher = StandardTestDispatcher()

		val coinDetailsWithPriceFlow = MutableSharedFlow<CoinDetailsWithPrice>()
		whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(coinDetailsWithPriceFlow)

		val coinDetailsViewModel = CoinDetailsViewModelImpl(
			observeCoinDetailsUseCase,
			fetchCoinDetailsUseCase,
			hasCachedCoinDetailsUseCase,
			observeTagDetailsUseCase,
			fetchTagDetailsUseCase,
			hasCachedTagDetailsUseCase,
			observeTeamMemberDetailsUseCase,
			fetchTeamMemberDetailsUseCase,
			hasCachedTeamMemberDetailsUseCase,
			savedStateHandle,
			testIoDispatcher
		)

		verifyNoInteractions(fetchCoinDetailsUseCase)

		assertEquals(CoinDetailsState(isLoading = true), coinDetailsViewModel.uiState.value)

		coinDetailsWithPriceFlow.emit(coinDetailsWithPriceMock())
		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
		verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId), any())

		coinDetailsViewModel.onRefresh()

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
		verify(fetchCoinDetailsUseCase, times(2)).execute(eq(coinId), any())

		coinDetailsWithPriceFlow.emit(coinDetailsWithPriceMock(1))

		testIoDispatcher.scheduler.runCurrent()

		val expectedCoinDetailsWithPriceModel =
			CoinDetailsWithPriceModel(coinDetailsModelMock(), "$ 25222.00")

		assertEquals(
			CoinDetailsState(
				coinDetailsWithPriceModel = expectedCoinDetailsWithPriceModel,
				isError = false,
				isLoading = false,
				isRefreshing = false
			), coinDetailsViewModel.uiState.value
		)
	}

	@Test
	fun testErrorObservingCoinDetails() = runTest(timeout = 1.minutes) {

		val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase>()
		val fetchCoinDetailsUseCase = mock<FetchCoinDetailsUseCase>()
		val hasCachedCoinDetailsUseCase = mock<HasCachedCoinDetailsUseCase>()
		val observeTagDetailsUseCase = mock<ObserveTagDetailsUseCase>()
		val fetchTagDetailsUseCase = mock<FetchTagDetailsUseCase>()
		val hasCachedTagDetailsUseCase = mock<HasCachedTagDetailsUseCase>()
		val observeTeamMemberDetailsUseCase = mock<ObserveTeamMemberDetailsUseCase>()
		val fetchTeamMemberDetailsUseCase = mock<FetchTeamMemberDetailsUseCase>()
		val hasCachedTeamMemberDetailsUseCase = mock<HasCachedTeamMemberDetailsUseCase>()
		val coinId = "btc-bitcoin"
		val savedStateHandle = SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))
		val testIoDispatcher = StandardTestDispatcher()

		val errorMessage = "some error"

		whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(flow {
			throw RuntimeException(
				errorMessage
			)
		})

		val coinDetailsViewModel = CoinDetailsViewModelImpl(
			observeCoinDetailsUseCase,
			fetchCoinDetailsUseCase,
			hasCachedCoinDetailsUseCase,
			observeTagDetailsUseCase,
			fetchTagDetailsUseCase,
			hasCachedTagDetailsUseCase,
			observeTeamMemberDetailsUseCase,
			fetchTeamMemberDetailsUseCase,
			hasCachedTeamMemberDetailsUseCase,
			savedStateHandle,
			testIoDispatcher
		)

		verifyNoInteractions(fetchCoinDetailsUseCase)

		assertEquals(CoinDetailsState(isLoading = true), coinDetailsViewModel.uiState.value)

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
		verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId), any())

		assertEquals(
			CoinDetailsState(
				isError = true,
				errorMessage = ErrorMessage.GivenMessage(errorMessage),
				isLoading = false,
				isRefreshing = false
			), coinDetailsViewModel.uiState.value
		)
	}

	@Test
	fun testNetworkErrorWithCachedCoinDetailsNotRefreshing() =
		runTest(timeout = Duration.parse("1m")) {

			val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase>()
			val fetchCoinDetailsUseCase = mock<FetchCoinDetailsUseCase>()
			val hasCachedCoinDetailsUseCase = mock<HasCachedCoinDetailsUseCase>()
			val observeTagDetailsUseCase = mock<ObserveTagDetailsUseCase>()
			val fetchTagDetailsUseCase = mock<FetchTagDetailsUseCase>()
			val hasCachedTagDetailsUseCase = mock<HasCachedTagDetailsUseCase>()
			val observeTeamMemberDetailsUseCase = mock<ObserveTeamMemberDetailsUseCase>()
			val fetchTeamMemberDetailsUseCase = mock<FetchTeamMemberDetailsUseCase>()
			val hasCachedTeamMemberDetailsUseCase = mock<HasCachedTeamMemberDetailsUseCase>()
			val coinId = "btc-bitcoin"
			val savedStateHandle =
				SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))
			val testIoDispatcher = StandardTestDispatcher()

			val errorMessage = "some error"

			whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(flow {
				emit(
					coinDetailsWithPriceMock()
				)
			})
			whenever(fetchCoinDetailsUseCase.execute(eq(coinId), any())).thenThrow(
				RuntimeException(
					errorMessage
				)
			)
			whenever(hasCachedCoinDetailsUseCase.execute(coinId)).thenReturn(true)

			val coinDetailsViewModel = CoinDetailsViewModelImpl(
				observeCoinDetailsUseCase,
				fetchCoinDetailsUseCase,
				hasCachedCoinDetailsUseCase,
				observeTagDetailsUseCase,
				fetchTagDetailsUseCase,
				hasCachedTagDetailsUseCase,
				observeTeamMemberDetailsUseCase,
				fetchTeamMemberDetailsUseCase,
				hasCachedTeamMemberDetailsUseCase,
				savedStateHandle,
				testIoDispatcher
			)

			verifyNoInteractions(fetchCoinDetailsUseCase)

			assertEquals(CoinDetailsState(isLoading = true), coinDetailsViewModel.uiState.value)

			testIoDispatcher.scheduler.runCurrent()

			verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
			verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId), any())
			verify(hasCachedCoinDetailsUseCase, times(1)).execute(eq(coinId))

			assertEquals(
				CoinDetailsState(
					coinDetailsWithPriceModel = coinDetailsWithPriceModelMock(),
					isError = false,
					isLoading = false,
					isRefreshing = false
				), coinDetailsViewModel.uiState.value
			)
		}

	@Test
	fun testNetworkErrorWithCachedCoinDetailsRefreshing() =
		runTest(timeout = Duration.parse("1m")) {

			val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase>()
			val fetchCoinDetailsUseCase = mock<FetchCoinDetailsUseCase>()
			val hasCachedCoinDetailsUseCase = mock<HasCachedCoinDetailsUseCase>()
			val observeTagDetailsUseCase = mock<ObserveTagDetailsUseCase>()
			val fetchTagDetailsUseCase = mock<FetchTagDetailsUseCase>()
			val hasCachedTagDetailsUseCase = mock<HasCachedTagDetailsUseCase>()
			val observeTeamMemberDetailsUseCase = mock<ObserveTeamMemberDetailsUseCase>()
			val fetchTeamMemberDetailsUseCase = mock<FetchTeamMemberDetailsUseCase>()
			val hasCachedTeamMemberDetailsUseCase = mock<HasCachedTeamMemberDetailsUseCase>()
			val coinId = "btc-bitcoin"
			val savedStateHandle =
				SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))
			val testIoDispatcher = StandardTestDispatcher()

			val errorMessage = "some error"

			whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(flow {
				emit(
					coinDetailsWithPriceMock()
				)
			})
			whenever(fetchCoinDetailsUseCase.execute(eq(coinId), any())).thenThrow(
				RuntimeException(
					errorMessage
				)
			)
			whenever(hasCachedCoinDetailsUseCase.execute(coinId)).thenReturn(true)

			val coinDetailsViewModel = CoinDetailsViewModelImpl(
				observeCoinDetailsUseCase,
				fetchCoinDetailsUseCase,
				hasCachedCoinDetailsUseCase,
				observeTagDetailsUseCase,
				fetchTagDetailsUseCase,
				hasCachedTagDetailsUseCase,
				observeTeamMemberDetailsUseCase,
				fetchTeamMemberDetailsUseCase,
				hasCachedTeamMemberDetailsUseCase,
				savedStateHandle,
				testIoDispatcher
			)

			verifyNoInteractions(fetchCoinDetailsUseCase)

			assertEquals(CoinDetailsState(isLoading = true), coinDetailsViewModel.uiState.value)

			testIoDispatcher.scheduler.runCurrent()

			verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
			verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId), any())
			verify(hasCachedCoinDetailsUseCase, times(1)).execute(eq(coinId))

			coinDetailsViewModel.onRefresh()

			testIoDispatcher.scheduler.runCurrent()

			verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
			verify(fetchCoinDetailsUseCase, times(2)).execute(eq(coinId), any())
			verify(hasCachedCoinDetailsUseCase, times(2)).execute(eq(coinId))

			assertEquals(
				CoinDetailsState(
					coinDetailsWithPriceModel = coinDetailsWithPriceModelMock(),
					isError = false,
					isLoading = false,
					errorMessage = ErrorMessage.GivenMessage(errorMessage),
					isRefreshing = false
				), coinDetailsViewModel.uiState.value
			)
		}

	@Test
	fun testNetworkErrorWithoutCachedCoinDetailsRefreshing() =
		runTest(timeout = Duration.parse("1m")) {

			val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase>()
			val fetchCoinDetailsUseCase = mock<FetchCoinDetailsUseCase>()
			val hasCachedCoinDetailsUseCase = mock<HasCachedCoinDetailsUseCase>()
			val observeTagDetailsUseCase = mock<ObserveTagDetailsUseCase>()
			val fetchTagDetailsUseCase = mock<FetchTagDetailsUseCase>()
			val hasCachedTagDetailsUseCase = mock<HasCachedTagDetailsUseCase>()
			val observeTeamMemberDetailsUseCase = mock<ObserveTeamMemberDetailsUseCase>()
			val fetchTeamMemberDetailsUseCase = mock<FetchTeamMemberDetailsUseCase>()
			val hasCachedTeamMemberDetailsUseCase = mock<HasCachedTeamMemberDetailsUseCase>()
			val coinId = "btc-bitcoin"
			val savedStateHandle =
				SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))
			val testIoDispatcher = StandardTestDispatcher()

			val errorMessage = "some error"
			val observeCoinDetailsSharedFlow = MutableSharedFlow<CoinDetailsWithPrice>()

			whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(observeCoinDetailsSharedFlow)
			whenever(fetchCoinDetailsUseCase.execute(eq(coinId), any())).thenThrow(
				RuntimeException(
					errorMessage
				)
			)
			whenever(hasCachedCoinDetailsUseCase.execute(coinId)).thenReturn(false)

			val coinDetailsViewModel = CoinDetailsViewModelImpl(
				observeCoinDetailsUseCase,
				fetchCoinDetailsUseCase,
				hasCachedCoinDetailsUseCase,
				observeTagDetailsUseCase,
				fetchTagDetailsUseCase,
				hasCachedTagDetailsUseCase,
				observeTeamMemberDetailsUseCase,
				fetchTeamMemberDetailsUseCase,
				hasCachedTeamMemberDetailsUseCase,
				savedStateHandle,
				testIoDispatcher
			)

			verifyNoInteractions(fetchCoinDetailsUseCase)

			assertEquals(CoinDetailsState(isLoading = true), coinDetailsViewModel.uiState.value)

			testIoDispatcher.scheduler.runCurrent()

			verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
			verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId), any())
			verify(hasCachedCoinDetailsUseCase, times(1)).execute(eq(coinId))

			coinDetailsViewModel.onRefresh()

			testIoDispatcher.scheduler.runCurrent()

			verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
			verify(fetchCoinDetailsUseCase, times(2)).execute(eq(coinId), any())
			verify(hasCachedCoinDetailsUseCase, times(2)).execute(eq(coinId))

			assertEquals(
				CoinDetailsState(
					isError = true,
					errorMessage = ErrorMessage.GivenMessage(errorMessage),
					isLoading = false,
					isRefreshing = false
				), coinDetailsViewModel.uiState.value
			)
		}

	@Test
	fun testNetworkErrorWithoutCachedCoinDetailsNotRefreshing() =
		runTest(timeout = Duration.parse("1m")) {

			val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase>()
			val fetchCoinDetailsUseCase = mock<FetchCoinDetailsUseCase>()
			val hasCachedCoinDetailsUseCase = mock<HasCachedCoinDetailsUseCase>()
			val observeTagDetailsUseCase = mock<ObserveTagDetailsUseCase>()
			val fetchTagDetailsUseCase = mock<FetchTagDetailsUseCase>()
			val hasCachedTagDetailsUseCase = mock<HasCachedTagDetailsUseCase>()
			val observeTeamMemberDetailsUseCase = mock<ObserveTeamMemberDetailsUseCase>()
			val fetchTeamMemberDetailsUseCase = mock<FetchTeamMemberDetailsUseCase>()
			val hasCachedTeamMemberDetailsUseCase = mock<HasCachedTeamMemberDetailsUseCase>()
			val coinId = "btc-bitcoin"
			val savedStateHandle =
				SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))
			val testIoDispatcher = StandardTestDispatcher()

			val errorMessage = "some error"
			val observeCoinDetailsSharedFlow = MutableSharedFlow<CoinDetailsWithPrice>()

			whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(observeCoinDetailsSharedFlow)
			whenever(fetchCoinDetailsUseCase.execute(eq(coinId), any())).thenThrow(
				RuntimeException(
					errorMessage
				)
			)
			whenever(hasCachedCoinDetailsUseCase.execute(coinId)).thenReturn(false)

			val coinDetailsViewModel = CoinDetailsViewModelImpl(
				observeCoinDetailsUseCase,
				fetchCoinDetailsUseCase,
				hasCachedCoinDetailsUseCase,
				observeTagDetailsUseCase,
				fetchTagDetailsUseCase,
				hasCachedTagDetailsUseCase,
				observeTeamMemberDetailsUseCase,
				fetchTeamMemberDetailsUseCase,
				hasCachedTeamMemberDetailsUseCase,
				savedStateHandle,
				testIoDispatcher
			)

			verifyNoInteractions(fetchCoinDetailsUseCase)

			assertEquals(CoinDetailsState(isLoading = true), coinDetailsViewModel.uiState.value)

			testIoDispatcher.scheduler.runCurrent()

			verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
			verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId), any())
			verify(hasCachedCoinDetailsUseCase, times(1)).execute(eq(coinId))

			assertEquals(
				CoinDetailsState(
					isError = true,
					errorMessage = ErrorMessage.GivenMessage(errorMessage),
					isLoading = false,
					isRefreshing = false
				), coinDetailsViewModel.uiState.value
			)

			coinDetailsViewModel.errorShown()

			assertEquals(
				CoinDetailsState(
					isError = true,
					errorMessage = ErrorMessage.Empty,
					isLoading = false,
					isRefreshing = false
				), coinDetailsViewModel.uiState.value
			)
		}

	@Test
	fun testDatabaseErrorCheckingCachedCoinDetails() =
		runTest(timeout = Duration.parse("1m")) {

			val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase>()
			val fetchCoinDetailsUseCase = mock<FetchCoinDetailsUseCase>()
			val hasCachedCoinDetailsUseCase = mock<HasCachedCoinDetailsUseCase>()
			val observeTagDetailsUseCase = mock<ObserveTagDetailsUseCase>()
			val fetchTagDetailsUseCase = mock<FetchTagDetailsUseCase>()
			val hasCachedTagDetailsUseCase = mock<HasCachedTagDetailsUseCase>()
			val observeTeamMemberDetailsUseCase = mock<ObserveTeamMemberDetailsUseCase>()
			val fetchTeamMemberDetailsUseCase = mock<FetchTeamMemberDetailsUseCase>()
			val hasCachedTeamMemberDetailsUseCase = mock<HasCachedTeamMemberDetailsUseCase>()
			val coinId = "btc-bitcoin"
			val savedStateHandle =
				SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))
			val testIoDispatcher = StandardTestDispatcher()

			val errorMessage = "some error"
			val observeCoinDetailsSharedFlow = MutableSharedFlow<CoinDetailsWithPrice>()

			whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(observeCoinDetailsSharedFlow)
			whenever(fetchCoinDetailsUseCase.execute(eq(coinId), any())).thenThrow(
				RuntimeException(
					errorMessage
				)
			)
			whenever(hasCachedCoinDetailsUseCase.execute(coinId)).thenThrow(RuntimeException(errorMessage))

			val coinDetailsViewModel = CoinDetailsViewModelImpl(
				observeCoinDetailsUseCase,
				fetchCoinDetailsUseCase,
				hasCachedCoinDetailsUseCase,
				observeTagDetailsUseCase,
				fetchTagDetailsUseCase,
				hasCachedTagDetailsUseCase,
				observeTeamMemberDetailsUseCase,
				fetchTeamMemberDetailsUseCase,
				hasCachedTeamMemberDetailsUseCase,
				savedStateHandle,
				testIoDispatcher
			)

			verifyNoInteractions(fetchCoinDetailsUseCase)

			assertEquals(CoinDetailsState(isLoading = true), coinDetailsViewModel.uiState.value)

			testIoDispatcher.scheduler.runCurrent()

			verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
			verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId), any())
			verify(hasCachedCoinDetailsUseCase, times(1)).execute(eq(coinId))

			assertEquals(
				CoinDetailsState(
					isError = true,
					errorMessage = ErrorMessage.GivenMessage(errorMessage),
					isLoading = false,
					isRefreshing = false

				), coinDetailsViewModel.uiState.value
			)

			coinDetailsViewModel.errorShown()

			assertEquals(
				CoinDetailsState(
					isError = true,
					errorMessage = ErrorMessage.Empty,
					isLoading = false,
					isRefreshing = false
				), coinDetailsViewModel.uiState.value
			)
		}

	@Test
	fun testOnRetry() = runTest(timeout = Duration.parse("1m")) {

		val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase>()
		val fetchCoinDetailsUseCase = mock<FetchCoinDetailsUseCase>()
		val hasCachedCoinDetailsUseCase = mock<HasCachedCoinDetailsUseCase>()
		val observeTagDetailsUseCase = mock<ObserveTagDetailsUseCase>()
		val fetchTagDetailsUseCase = mock<FetchTagDetailsUseCase>()
		val hasCachedTagDetailsUseCase = mock<HasCachedTagDetailsUseCase>()
		val observeTeamMemberDetailsUseCase = mock<ObserveTeamMemberDetailsUseCase>()
		val fetchTeamMemberDetailsUseCase = mock<FetchTeamMemberDetailsUseCase>()
		val hasCachedTeamMemberDetailsUseCase = mock<HasCachedTeamMemberDetailsUseCase>()
		val coinId = "btc-bitcoin"
		val savedStateHandle = SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))
		val testIoDispatcher = StandardTestDispatcher()

		val errorMessage = "some error"

		whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(flow {
			throw RuntimeException(
				errorMessage
			)
		})

		val coinDetailsViewModel = CoinDetailsViewModelImpl(
			observeCoinDetailsUseCase,
			fetchCoinDetailsUseCase,
			hasCachedCoinDetailsUseCase,
			observeTagDetailsUseCase,
			fetchTagDetailsUseCase,
			hasCachedTagDetailsUseCase,
			observeTeamMemberDetailsUseCase,
			fetchTeamMemberDetailsUseCase,
			hasCachedTeamMemberDetailsUseCase,
			savedStateHandle,
			testIoDispatcher
		)

		verifyNoInteractions(fetchCoinDetailsUseCase)

		assertEquals(CoinDetailsState(isLoading = true), coinDetailsViewModel.uiState.value)

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
		verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId), any())

		assertEquals(
			CoinDetailsState(
				isError = true,
				errorMessage = ErrorMessage.GivenMessage(errorMessage),
				isLoading = false,
				isRefreshing = false
			), coinDetailsViewModel.uiState.value
		)

		coinDetailsViewModel.errorShown()

		assertEquals(
			CoinDetailsState(
				isError = true,
				errorMessage = ErrorMessage.Empty,
				isLoading = false,
				isRefreshing = false
			), coinDetailsViewModel.uiState.value
		)

		coinDetailsViewModel.onRetry()

		testIoDispatcher.scheduler.runCurrent()

		assertEquals(
			CoinDetailsState(
				isError = false,
				errorMessage = ErrorMessage.Empty,
				isLoading = true,
				isRefreshing = false
			), coinDetailsViewModel.uiState.value
		)

		verify(fetchCoinDetailsUseCase, times(2)).execute(eq(coinId), any())
	}
}