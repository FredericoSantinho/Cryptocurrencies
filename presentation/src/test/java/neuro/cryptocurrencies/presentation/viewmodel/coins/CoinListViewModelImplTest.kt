package neuro.cryptocurrencies.presentation.viewmodel.coins

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.mocks.coinTickerMockList
import neuro.cryptocurrencies.domain.usecase.coin.FetchCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.HasCachedCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.ObserveCoinsTickersUseCase
import neuro.cryptocurrencies.presentation.MainDispatcherRule
import neuro.cryptocurrencies.presentation.mocks.coinTickerModelMockList
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class CoinListViewModelImplTest {

	@get:Rule
	val mainDispatcherRule = MainDispatcherRule()

	@Test
	fun testHappyPath() = runTest {
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

		verify(fetchCoinsTickersUseCase, times(1)).execute()
		verify(observeCoinsTickersUseCase, times(1)).execute()

		assertEquals(
			CoinListState(
				coins = coinTickerModelMockList(),
				isLoading = false,
				isRefreshing = false,
				isError = false
			), coinListViewModel.uiState.value
		)
	}

	@Test
	fun testErrorObservingCoinsTickers() {
		runTest {
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

			verify(fetchCoinsTickersUseCase, times(1)).execute()
			verify(observeCoinsTickersUseCase, times(1)).execute()

			assertEquals(
				CoinListState(
					isError = true,
					errorMessage = errorMessage,
					isLoading = false,
					isRefreshing = false
				), coinListViewModel.uiState.value
			)
		}
	}

	@Test
	fun testNetworkErrorWithCachedCoinsTickersNotRefreshing() = runTest {
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

		verify(fetchCoinsTickersUseCase, times(1)).execute()

		assertEquals(
			CoinListState(
				coins = coinTickerModelMockList(),
				isError = false,
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)
	}

	@Test
	fun testNetworkErrorWithCachedCoinsTickersRefreshing() = runTest {
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

		verify(fetchCoinsTickersUseCase, times(1)).execute()

		coinListViewModel.onRefresh()

		testIoDispatcher.scheduler.runCurrent()

		verify(fetchCoinsTickersUseCase, times(2)).execute()

		assertEquals(
			CoinListState(
				coins = coinTickerModelMockList(),
				errorMessage = errorMessage,
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)
	}

	@Test
	fun testNetworkErrorWithoutCachedCoinsTickersRefreshing() = runTest {
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

		verify(fetchCoinsTickersUseCase, times(1)).execute()

		coinListViewModel.onRefresh()

		testIoDispatcher.scheduler.runCurrent()

		verify(fetchCoinsTickersUseCase, times(2)).execute()

		assertEquals(
			CoinListState(
				coins = null,
				isError = true,
				errorMessage = errorMessage,
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)
	}

	@Test
	fun testNetworkErrorWithoutCachedCoinsTickersNotRefreshing() = runTest {
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

		verify(fetchCoinsTickersUseCase, times(1)).execute()

		assertEquals(
			CoinListState(
				coins = null,
				isError = true,
				errorMessage = errorMessage,
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)
	}

	@Test
	fun testDatabaseErrorCheckingCachedCoinsTickers() = runTest {
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

		verify(fetchCoinsTickersUseCase, times(1)).execute()
		verify(observeCoinsTickersUseCase, times(1)).execute()

		assertEquals(
			CoinListState(
				isError = true,
				errorMessage = errorMessage,
				isLoading = false,
				isRefreshing = false
			), coinListViewModel.uiState.value
		)
	}

}