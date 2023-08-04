package neuro.cryptocurrencies.presentation.viewmodel.coins

import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.mocks.coinTickerMockList
import neuro.cryptocurrencies.domain.usecase.coin.FetchCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.HasCachedCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.ObserveCoinsTickersUseCase
import neuro.cryptocurrencies.presentation.MainDispatcherRule
import neuro.cryptocurrencies.presentation.mocks.coinTickerModelMock
import neuro.cryptocurrencies.presentation.mocks.coinTickerModelMockList
import neuro.cryptocurrencies.presentation.model.ErrorMessage
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.minutes

class CoinListViewModelImplTest {

	@get:Rule
	val mainDispatcherRule = MainDispatcherRule()

	@Test
	fun testHappyPath() = runTest(timeout = 1.minutes) {
		val observeCoinsTickersUseCase = mock<ObserveCoinsTickersUseCase>()
		val fetchCoinsTickersUseCase = mock<FetchCoinsTickersUseCase>()
		val hasCachedCoinsTickersUseCase = mock<HasCachedCoinsTickersUseCase>()
		val testIoDispatcher = StandardTestDispatcher()

		whenever(observeCoinsTickersUseCase.execute()).thenReturn(flow { emit(coinTickerMockList()) })

		val coinListViewModel = CoinListViewModelImpl(
			observeCoinsTickersUseCase,
			fetchCoinsTickersUseCase,
			hasCachedCoinsTickersUseCase,
			testIoDispatcher
		)

		assertEquals(CoinListState(isLoading = true), coinListViewModel.uiState.value)

		verifyNoInteractions(fetchCoinsTickersUseCase)

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinsTickersUseCase, times(1)).execute()
		verify(fetchCoinsTickersUseCase, times(1)).execute()

		assertEquals(
			CoinListState(
				coins = coinTickerModelMockList().toImmutableList(),
				isLoading = false,
				isRefreshing = false,
				isError = false
			), coinListViewModel.uiState.value
		)
	}

	@Test
	fun testRefresh() = runTest(timeout = 1.minutes) {
		val observeCoinsTickersUseCase = mock<ObserveCoinsTickersUseCase>()
		val fetchCoinsTickersUseCase = mock<FetchCoinsTickersUseCase>()
		val hasCachedCoinsTickersUseCase = mock<HasCachedCoinsTickersUseCase>()
		val testIoDispatcher = StandardTestDispatcher()

		val coinsTickersStateFlow = MutableStateFlow(coinTickerMockList())
		whenever(observeCoinsTickersUseCase.execute()).thenReturn(coinsTickersStateFlow)
		whenever(hasCachedCoinsTickersUseCase.execute()).thenReturn(true)

		val coinListViewModel = CoinListViewModelImpl(
			observeCoinsTickersUseCase,
			fetchCoinsTickersUseCase,
			hasCachedCoinsTickersUseCase,
			testIoDispatcher
		)

		assertEquals(CoinListState(isLoading = true), coinListViewModel.uiState.value)

		verifyNoInteractions(fetchCoinsTickersUseCase)

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinsTickersUseCase, times(1)).execute()
		verify(fetchCoinsTickersUseCase, times(1)).execute()

		coinListViewModel.onRefresh()

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinsTickersUseCase, times(1)).execute()
		verify(fetchCoinsTickersUseCase, times(2)).execute()

		coinsTickersStateFlow.value = emptyList()

		testIoDispatcher.scheduler.runCurrent()

		coinsTickersStateFlow.value = coinTickerMockList()

		testIoDispatcher.scheduler.runCurrent()

		assertEquals(
			CoinListState(
				coins = coinTickerModelMockList().toImmutableList(),
				isLoading = false,
				isRefreshing = false,
				isError = false
			), coinListViewModel.uiState.value
		)
	}

	@Test
	fun testSearch() = runTest(timeout = 1.minutes) {
		val observeCoinsTickersUseCase = mock<ObserveCoinsTickersUseCase>()
		val fetchCoinsTickersUseCase = mock<FetchCoinsTickersUseCase>()
		val hasCachedCoinsTickersUseCase = mock<HasCachedCoinsTickersUseCase>()
		val testIoDispatcher = StandardTestDispatcher()

		whenever(observeCoinsTickersUseCase.execute()).thenReturn(flow { emit(coinTickerMockList()) })

		val coinListViewModel = CoinListViewModelImpl(
			observeCoinsTickersUseCase,
			fetchCoinsTickersUseCase,
			hasCachedCoinsTickersUseCase,
			testIoDispatcher
		)

		assertEquals(CoinListState(isLoading = true), coinListViewModel.uiState.value)

		verifyNoInteractions(fetchCoinsTickersUseCase)

		testIoDispatcher.scheduler.runCurrent()

		coinListViewModel.onSearchTerm("bitcoin")

		verify(observeCoinsTickersUseCase, times(1)).execute()
		verify(fetchCoinsTickersUseCase, times(1)).execute()

		assertEquals(
			CoinListState(
				coins = listOf(coinTickerModelMock()).toImmutableList(),
				isLoading = false,
				isRefreshing = false,
				isError = false
			), coinListViewModel.uiState.value
		)
	}

	@Test
	fun testOnCoinClick() = runTest(timeout = 1.minutes) {
		val observeCoinsTickersUseCase = mock<ObserveCoinsTickersUseCase>()
		val fetchCoinsTickersUseCase = mock<FetchCoinsTickersUseCase>()
		val hasCachedCoinsTickersUseCase = mock<HasCachedCoinsTickersUseCase>()
		val testIoDispatcher = StandardTestDispatcher()

		whenever(observeCoinsTickersUseCase.execute()).thenReturn(flow { emit(coinTickerMockList()) })

		val coinListViewModel = CoinListViewModelImpl(
			observeCoinsTickersUseCase,
			fetchCoinsTickersUseCase,
			hasCachedCoinsTickersUseCase,
			testIoDispatcher
		)

		assertEquals(CoinListState(isLoading = true), coinListViewModel.uiState.value)

		verifyNoInteractions(fetchCoinsTickersUseCase)

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinsTickersUseCase, times(1)).execute()
		verify(fetchCoinsTickersUseCase, times(1)).execute()

		assertEquals(
			CoinListState(
				coins = coinTickerModelMockList().toImmutableList(),
				isLoading = false,
				isRefreshing = false,
				isError = false
			), coinListViewModel.uiState.value
		)

		val uiEventDeferred = async {
			coinListViewModel.uiEvent.first()
		}

		launch {
			coinListViewModel.onCoinClick("btc-bitcoin")
		}

		assertEquals(
			CoinListViewModelImpl.UiEvent.NavigateToDetails("btc-bitcoin"),
			uiEventDeferred.await()
		)
	}

	@Test
	fun testErrorObservingCoinsTickers() = runTest(timeout = 1.minutes) {
		val observeCoinsTickersUseCase = mock<ObserveCoinsTickersUseCase>()
		val fetchCoinsTickersUseCase = mock<FetchCoinsTickersUseCase>()
		val hasCachedCoinsTickersUseCase = mock<HasCachedCoinsTickersUseCase>()
		val testIoDispatcher = StandardTestDispatcher()

		val errorMessage = "some error"

		whenever(observeCoinsTickersUseCase.execute()).thenReturn(flow {
			throw RuntimeException(
				errorMessage
			)
		})

		val coinListViewModel = CoinListViewModelImpl(
			observeCoinsTickersUseCase,
			fetchCoinsTickersUseCase,
			hasCachedCoinsTickersUseCase,
			testIoDispatcher
		)

		assertEquals(CoinListState(isLoading = true), coinListViewModel.uiState.value)

		verifyNoInteractions(fetchCoinsTickersUseCase)

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinsTickersUseCase, times(1)).execute()
		verify(fetchCoinsTickersUseCase, times(1)).execute()

		assertEquals(
			CoinListState(
				isError = true,
				errorMessage = ErrorMessage.GivenMessage(errorMessage),
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)

		coinListViewModel.errorShown()

		assertEquals(
			CoinListState(
				isError = true,
				errorMessage = ErrorMessage.Empty,
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)
	}

	@Test
	fun testNetworkErrorWithCachedCoinsTickersNotRefreshing() = runTest(timeout = 1.minutes) {
		val observeCoinsTickersUseCase = mock<ObserveCoinsTickersUseCase>()
		val fetchCoinsTickersUseCase = mock<FetchCoinsTickersUseCase>()
		val hasCachedCoinsTickersUseCase = mock<HasCachedCoinsTickersUseCase>()
		val testIoDispatcher = StandardTestDispatcher()

		val errorMessage = "someError"
		whenever(observeCoinsTickersUseCase.execute()).thenReturn(flow { emit(coinTickerMockList()) })
		whenever(fetchCoinsTickersUseCase.execute()).thenThrow(RuntimeException(errorMessage))
		whenever(hasCachedCoinsTickersUseCase.execute()).thenReturn(true)

		val coinListViewModel = CoinListViewModelImpl(
			observeCoinsTickersUseCase,
			fetchCoinsTickersUseCase,
			hasCachedCoinsTickersUseCase,
			testIoDispatcher
		)

		assertEquals(CoinListState(isLoading = true), coinListViewModel.uiState.value)

		verifyNoInteractions(fetchCoinsTickersUseCase)

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinsTickersUseCase, times(1)).execute()
		verify(fetchCoinsTickersUseCase, times(1)).execute()
		verify(hasCachedCoinsTickersUseCase, times(1)).execute()

		assertEquals(
			CoinListState(
				coins = coinTickerModelMockList().toImmutableList(),
				isError = false,
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)
	}

	@Test
	fun testNetworkErrorWithCachedCoinsTickersRefreshing() = runTest(timeout = 1.minutes) {
		val observeCoinsTickersUseCase = mock<ObserveCoinsTickersUseCase>()
		val fetchCoinsTickersUseCase = mock<FetchCoinsTickersUseCase>()
		val hasCachedCoinsTickersUseCase = mock<HasCachedCoinsTickersUseCase>()
		val testIoDispatcher = StandardTestDispatcher()

		val errorMessage = "someError"
		whenever(observeCoinsTickersUseCase.execute()).thenReturn(flow { emit(coinTickerMockList()) })
		whenever(fetchCoinsTickersUseCase.execute()).thenThrow(RuntimeException(errorMessage))
		whenever(hasCachedCoinsTickersUseCase.execute()).thenReturn(true)

		val coinListViewModel = CoinListViewModelImpl(
			observeCoinsTickersUseCase,
			fetchCoinsTickersUseCase,
			hasCachedCoinsTickersUseCase,
			testIoDispatcher
		)

		assertEquals(CoinListState(isLoading = true), coinListViewModel.uiState.value)

		verifyNoInteractions(fetchCoinsTickersUseCase)

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinsTickersUseCase, times(1)).execute()
		verify(fetchCoinsTickersUseCase, times(1)).execute()
		verify(hasCachedCoinsTickersUseCase, times(1)).execute()

		coinListViewModel.onRefresh()

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinsTickersUseCase, times(1)).execute()
		verify(fetchCoinsTickersUseCase, times(2)).execute()
		verify(hasCachedCoinsTickersUseCase, times(2)).execute()

		assertEquals(
			CoinListState(
				coins = coinTickerModelMockList().toImmutableList(),
				errorMessage = ErrorMessage.GivenMessage(errorMessage),
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)

		coinListViewModel.errorShown()

		assertEquals(
			CoinListState(
				coins = coinTickerModelMockList().toImmutableList(),
				errorMessage = ErrorMessage.Empty,
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)
	}

	@Test
	fun testNetworkErrorWithoutCachedCoinsTickersRefreshing() = runTest(timeout = 1.minutes) {
		val observeCoinsTickersUseCase = mock<ObserveCoinsTickersUseCase>()
		val fetchCoinsTickersUseCase = mock<FetchCoinsTickersUseCase>()
		val hasCachedCoinsTickersUseCase = mock<HasCachedCoinsTickersUseCase>()
		val testIoDispatcher = StandardTestDispatcher()

		val errorMessage = "someError"
		whenever(observeCoinsTickersUseCase.execute()).thenReturn(flow { emit(emptyList()) })
		whenever(fetchCoinsTickersUseCase.execute()).thenThrow(RuntimeException(errorMessage))
		whenever(hasCachedCoinsTickersUseCase.execute()).thenReturn(false)

		val coinListViewModel = CoinListViewModelImpl(
			observeCoinsTickersUseCase,
			fetchCoinsTickersUseCase,
			hasCachedCoinsTickersUseCase,
			testIoDispatcher
		)

		assertEquals(CoinListState(isLoading = true), coinListViewModel.uiState.value)

		verifyNoInteractions(fetchCoinsTickersUseCase)

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinsTickersUseCase, times(1)).execute()
		verify(fetchCoinsTickersUseCase, times(1)).execute()
		verify(hasCachedCoinsTickersUseCase, times(1)).execute()

		coinListViewModel.onRefresh()

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinsTickersUseCase, times(1)).execute()
		verify(fetchCoinsTickersUseCase, times(2)).execute()
		verify(hasCachedCoinsTickersUseCase, times(2)).execute()

		assertEquals(
			CoinListState(
				coins = null,
				isError = true,
				errorMessage = ErrorMessage.GivenMessage(errorMessage),
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)

		coinListViewModel.errorShown()

		assertEquals(
			CoinListState(
				coins = null,
				isError = true,
				errorMessage = ErrorMessage.Empty,
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)
	}

	@Test
	fun testNetworkErrorWithoutCachedCoinsTickersNotRefreshing() = runTest(timeout = 1.minutes) {
		val observeCoinsTickersUseCase = mock<ObserveCoinsTickersUseCase>()
		val fetchCoinsTickersUseCase = mock<FetchCoinsTickersUseCase>()
		val hasCachedCoinsTickersUseCase = mock<HasCachedCoinsTickersUseCase>()
		val testIoDispatcher = StandardTestDispatcher()

		val errorMessage = "someError"
		whenever(observeCoinsTickersUseCase.execute()).thenReturn(flow { emit(emptyList()) })
		whenever(fetchCoinsTickersUseCase.execute()).thenThrow(RuntimeException(errorMessage))
		whenever(hasCachedCoinsTickersUseCase.execute()).thenReturn(false)

		val coinListViewModel = CoinListViewModelImpl(
			observeCoinsTickersUseCase,
			fetchCoinsTickersUseCase,
			hasCachedCoinsTickersUseCase,
			testIoDispatcher
		)

		assertEquals(CoinListState(isLoading = true), coinListViewModel.uiState.value)

		verifyNoInteractions(fetchCoinsTickersUseCase)

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinsTickersUseCase, times(1)).execute()
		verify(fetchCoinsTickersUseCase, times(1)).execute()
		verify(hasCachedCoinsTickersUseCase, times(1)).execute()

		assertEquals(
			CoinListState(
				coins = null,
				isError = true,
				errorMessage = ErrorMessage.GivenMessage(errorMessage),
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)

		coinListViewModel.errorShown()

		assertEquals(
			CoinListState(
				coins = null,
				isError = true,
				errorMessage = ErrorMessage.Empty,
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)
	}

	@Test
	fun testDatabaseErrorCheckingCachedCoinsTickers() = runTest(timeout = 1.minutes) {
		val observeCoinsTickersUseCase = mock<ObserveCoinsTickersUseCase>()
		val fetchCoinsTickersUseCase = mock<FetchCoinsTickersUseCase>()
		val hasCachedCoinsTickersUseCase = mock<HasCachedCoinsTickersUseCase>()
		val testIoDispatcher = StandardTestDispatcher()

		val errorMessage = "someError"
		whenever(observeCoinsTickersUseCase.execute()).thenReturn(flow { emit(emptyList()) })
		whenever(fetchCoinsTickersUseCase.execute()).thenThrow(RuntimeException(errorMessage))
		whenever(hasCachedCoinsTickersUseCase.execute()).thenThrow(RuntimeException(errorMessage))

		val coinListViewModel = CoinListViewModelImpl(
			observeCoinsTickersUseCase,
			fetchCoinsTickersUseCase,
			hasCachedCoinsTickersUseCase,
			testIoDispatcher
		)

		assertEquals(CoinListState(isLoading = true), coinListViewModel.uiState.value)

		verifyNoInteractions(fetchCoinsTickersUseCase)

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinsTickersUseCase, times(1)).execute()
		verify(fetchCoinsTickersUseCase, times(1)).execute()
		verify(hasCachedCoinsTickersUseCase, times(1)).execute()

		assertEquals(
			CoinListState(
				isError = true,
				errorMessage = ErrorMessage.GivenMessage(errorMessage),
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)

		coinListViewModel.errorShown()

		assertEquals(
			CoinListState(
				isError = true,
				errorMessage = ErrorMessage.Empty,
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)
	}

	@Test
	fun testOnRetry() = runTest(timeout = 1.minutes) {
		val observeCoinsTickersUseCase = mock<ObserveCoinsTickersUseCase>()
		val fetchCoinsTickersUseCase = mock<FetchCoinsTickersUseCase>()
		val hasCachedCoinsTickersUseCase = mock<HasCachedCoinsTickersUseCase>()
		val testIoDispatcher = StandardTestDispatcher()

		val errorMessage = "some error"

		whenever(observeCoinsTickersUseCase.execute()).thenReturn(flow {
			throw RuntimeException(
				errorMessage
			)
		})

		val coinListViewModel = CoinListViewModelImpl(
			observeCoinsTickersUseCase,
			fetchCoinsTickersUseCase,
			hasCachedCoinsTickersUseCase,
			testIoDispatcher
		)

		assertEquals(CoinListState(isLoading = true), coinListViewModel.uiState.value)

		verifyNoInteractions(fetchCoinsTickersUseCase)

		testIoDispatcher.scheduler.runCurrent()

		verify(observeCoinsTickersUseCase, times(1)).execute()
		verify(fetchCoinsTickersUseCase, times(1)).execute()

		assertEquals(
			CoinListState(
				isError = true,
				errorMessage = ErrorMessage.GivenMessage(errorMessage),
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)

		coinListViewModel.errorShown()

		assertEquals(
			CoinListState(
				isError = true,
				errorMessage = ErrorMessage.Empty,
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)

		coinListViewModel.onRetry()

		testIoDispatcher.scheduler.runCurrent()

		assertEquals(
			CoinListState(
				isError = false,
				errorMessage = ErrorMessage.Empty,
				isLoading = true,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)

		verify(fetchCoinsTickersUseCase, times(2)).execute()
	}
}