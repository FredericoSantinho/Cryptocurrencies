package neuro.cryptocurrencies.viewmodel.coinDetails

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.MainDispatcherRule
import neuro.cryptocurrencies.domain.entity.CoinDetailsWithPrice
import neuro.cryptocurrencies.domain.mocks.coinDetailsWithPriceMock
import neuro.cryptocurrencies.domain.usecase.coinDetails.FetchCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coinDetails.HasCachedCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coinDetails.ObserveCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tagDetails.FetchTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tagDetails.HasCachedTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tagDetails.ObserveTagDetailsUseCase
import neuro.cryptocurrencies.mocks.coinDetailsModelMock
import neuro.cryptocurrencies.mocks.coinDetailsWithPriceModelMock
import neuro.cryptocurrencies.presentation.model.CoinDetailsWithPriceModel
import neuro.cryptocurrencies.presentation.model.ErrorMessage
import neuro.cryptocurrencies.presentation.viewmodel.coinDetails.CoinDetailsState
import neuro.cryptocurrencies.presentation.viewmodel.coinDetails.CoinDetailsViewModelImpl
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
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
		val coinId = "btc-bitcoin"
		val savedStateHandle =
			SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))

		whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(flow {
			emit(
				coinDetailsWithPriceMock()
			)
		})

		val coinDetailsViewModel =
			CoinDetailsViewModelImpl(
				observeCoinDetailsUseCase,
				fetchCoinDetailsUseCase,
				hasCachedCoinDetailsUseCase,
				observeTagDetailsUseCase,
				fetchTagDetailsUseCase,
				hasCachedTagDetailsUseCase,
				savedStateHandle
			)

		verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
		verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId))

		assertEquals(
			CoinDetailsState(
				coinDetailsWithPriceModel = coinDetailsWithPriceModelMock(),
				isErrorState = false,
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
		val coinId = "btc-bitcoin"
		val savedStateHandle =
			SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))

		val coinDetailsWithPriceFlow = MutableSharedFlow<CoinDetailsWithPrice>()
		whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(coinDetailsWithPriceFlow)

		val coinDetailsViewModel =
			CoinDetailsViewModelImpl(
				observeCoinDetailsUseCase,
				fetchCoinDetailsUseCase,
				hasCachedCoinDetailsUseCase,
				observeTagDetailsUseCase,
				fetchTagDetailsUseCase,
				hasCachedTagDetailsUseCase,
				savedStateHandle
			)

		assertEquals(
			CoinDetailsState(
				isLoading = true
			), coinDetailsViewModel.uiState.value
		)

		coinDetailsWithPriceFlow.emit(coinDetailsWithPriceMock())

		verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
		verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId))

		coinDetailsViewModel.onRefresh()

		verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
		verify(fetchCoinDetailsUseCase, times(2)).execute(eq(coinId))

		coinDetailsWithPriceFlow.emit(coinDetailsWithPriceMock(1))

		val expectedCoinDetailsWithPriceModel =
			CoinDetailsWithPriceModel(coinDetailsModelMock(), "$ 25222.00")

		assertEquals(
			CoinDetailsState(
				coinDetailsWithPriceModel = expectedCoinDetailsWithPriceModel,
				isErrorState = false,
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
		val coinId = "btc-bitcoin"
		val savedStateHandle =
			SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))

		val errorMessage = "some error"

		whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(flow {
			throw RuntimeException(
				errorMessage
			)
		})

		val coinDetailsViewModel =
			CoinDetailsViewModelImpl(
				observeCoinDetailsUseCase,
				fetchCoinDetailsUseCase,
				hasCachedCoinDetailsUseCase,
				observeTagDetailsUseCase,
				fetchTagDetailsUseCase,
				hasCachedTagDetailsUseCase,
				savedStateHandle
			)

		verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
		verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId))

		assertEquals(
			CoinDetailsState(
				isErrorState = true,
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
			val coinId = "btc-bitcoin"
			val savedStateHandle =
				SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))

			val errorMessage = "some error"

			whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(flow {
				emit(
					coinDetailsWithPriceMock()
				)
			})
			whenever(fetchCoinDetailsUseCase.execute(eq(coinId))).thenThrow(
				RuntimeException(
					errorMessage
				)
			)
			whenever(hasCachedCoinDetailsUseCase.execute(coinId)).thenReturn(true)

			val coinDetailsViewModel =
				CoinDetailsViewModelImpl(
					observeCoinDetailsUseCase,
					fetchCoinDetailsUseCase,
					hasCachedCoinDetailsUseCase,
					observeTagDetailsUseCase,
					fetchTagDetailsUseCase,
					hasCachedTagDetailsUseCase,
					savedStateHandle
				)

			verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
			verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId))
			verify(hasCachedCoinDetailsUseCase, times(1)).execute(eq(coinId))

			assertEquals(
				CoinDetailsState(
					coinDetailsWithPriceModel = coinDetailsWithPriceModelMock(),
					isErrorState = false,
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
			val coinId = "btc-bitcoin"
			val savedStateHandle =
				SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))

			val errorMessage = "some error"

			whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(flow {
				emit(
					coinDetailsWithPriceMock()
				)
			})
			whenever(fetchCoinDetailsUseCase.execute(eq(coinId))).thenThrow(
				RuntimeException(
					errorMessage
				)
			)
			whenever(hasCachedCoinDetailsUseCase.execute(coinId)).thenReturn(true)

			val coinDetailsViewModel =
				CoinDetailsViewModelImpl(
					observeCoinDetailsUseCase,
					fetchCoinDetailsUseCase,
					hasCachedCoinDetailsUseCase,
					observeTagDetailsUseCase,
					fetchTagDetailsUseCase,
					hasCachedTagDetailsUseCase,
					savedStateHandle
				)

			verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
			verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId))
			verify(hasCachedCoinDetailsUseCase, times(1)).execute(eq(coinId))

			coinDetailsViewModel.onRefresh()

			verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
			verify(fetchCoinDetailsUseCase, times(2)).execute(eq(coinId))
			verify(hasCachedCoinDetailsUseCase, times(2)).execute(eq(coinId))

			assertEquals(
				CoinDetailsState(
					coinDetailsWithPriceModel = coinDetailsWithPriceModelMock(),
					isErrorState = false,
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
			val coinId = "btc-bitcoin"
			val savedStateHandle =
				SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))

			val errorMessage = "some error"
			val observeCoinDetailsSharedFlow = MutableSharedFlow<CoinDetailsWithPrice>()

			whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(observeCoinDetailsSharedFlow)
			whenever(fetchCoinDetailsUseCase.execute(eq(coinId))).thenThrow(
				RuntimeException(
					errorMessage
				)
			)
			whenever(hasCachedCoinDetailsUseCase.execute(coinId)).thenReturn(false)

			val coinDetailsViewModel =
				CoinDetailsViewModelImpl(
					observeCoinDetailsUseCase,
					fetchCoinDetailsUseCase,
					hasCachedCoinDetailsUseCase,
					observeTagDetailsUseCase,
					fetchTagDetailsUseCase,
					hasCachedTagDetailsUseCase,
					savedStateHandle
				)

			verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
			verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId))
			verify(hasCachedCoinDetailsUseCase, times(1)).execute(eq(coinId))

			coinDetailsViewModel.onRefresh()

			verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
			verify(fetchCoinDetailsUseCase, times(2)).execute(eq(coinId))
			verify(hasCachedCoinDetailsUseCase, times(2)).execute(eq(coinId))

			assertEquals(
				CoinDetailsState(
					isErrorState = true,
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
			val coinId = "btc-bitcoin"
			val savedStateHandle =
				SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))

			val errorMessage = "some error"
			val observeCoinDetailsSharedFlow = MutableSharedFlow<CoinDetailsWithPrice>()

			whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(observeCoinDetailsSharedFlow)
			whenever(fetchCoinDetailsUseCase.execute(eq(coinId))).thenThrow(
				RuntimeException(
					errorMessage
				)
			)
			whenever(hasCachedCoinDetailsUseCase.execute(coinId)).thenReturn(false)

			val coinDetailsViewModel =
				CoinDetailsViewModelImpl(
					observeCoinDetailsUseCase,
					fetchCoinDetailsUseCase,
					hasCachedCoinDetailsUseCase,
					observeTagDetailsUseCase,
					fetchTagDetailsUseCase,
					hasCachedTagDetailsUseCase,
					savedStateHandle
				)

			verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
			verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId))
			verify(hasCachedCoinDetailsUseCase, times(1)).execute(eq(coinId))

			assertEquals(
				CoinDetailsState(
					isErrorState = true,
					errorMessage = ErrorMessage.GivenMessage(errorMessage),
					isLoading = false,
					isRefreshing = false
				), coinDetailsViewModel.uiState.value
			)

			coinDetailsViewModel.errorShown()

			assertEquals(
				CoinDetailsState(
					isErrorState = true,
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
			val coinId = "btc-bitcoin"
			val savedStateHandle =
				SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))

			val errorMessage = "some error"
			val observeCoinDetailsSharedFlow = MutableSharedFlow<CoinDetailsWithPrice>()

			whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(observeCoinDetailsSharedFlow)
			whenever(fetchCoinDetailsUseCase.execute(eq(coinId))).thenThrow(
				RuntimeException(
					errorMessage
				)
			)
			whenever(hasCachedCoinDetailsUseCase.execute(coinId)).thenThrow(RuntimeException(errorMessage))

			val coinDetailsViewModel =
				CoinDetailsViewModelImpl(
					observeCoinDetailsUseCase,
					fetchCoinDetailsUseCase,
					hasCachedCoinDetailsUseCase,
					observeTagDetailsUseCase,
					fetchTagDetailsUseCase,
					hasCachedTagDetailsUseCase,
					savedStateHandle
				)

			verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
			verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId))
			verify(hasCachedCoinDetailsUseCase, times(1)).execute(eq(coinId))

			assertEquals(
				CoinDetailsState(
					isErrorState = true,
					errorMessage = ErrorMessage.GivenMessage(errorMessage),
					isLoading = false,
					isRefreshing = false

				), coinDetailsViewModel.uiState.value
			)

			coinDetailsViewModel.errorShown()

			assertEquals(
				CoinDetailsState(
					isErrorState = true,
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
		val coinId = "btc-bitcoin"
		val savedStateHandle =
			SavedStateHandle(mapOf(CoinDetailsViewModelImpl.PARAM_COIN_ID to coinId))

		val errorMessage = "some error"

		whenever(observeCoinDetailsUseCase.execute(coinId)).thenReturn(flow {
			throw RuntimeException(
				errorMessage
			)
		})

		val coinDetailsViewModel =
			CoinDetailsViewModelImpl(
				observeCoinDetailsUseCase,
				fetchCoinDetailsUseCase,
				hasCachedCoinDetailsUseCase,
				observeTagDetailsUseCase,
				fetchTagDetailsUseCase,
				hasCachedTagDetailsUseCase,
				savedStateHandle
			)

		verify(observeCoinDetailsUseCase, times(1)).execute(coinId)
		verify(fetchCoinDetailsUseCase, times(1)).execute(eq(coinId))

		assertEquals(
			CoinDetailsState(
				isErrorState = true,
				errorMessage = ErrorMessage.GivenMessage(errorMessage),
				isLoading = false,
				isRefreshing = false
			), coinDetailsViewModel.uiState.value
		)

		coinDetailsViewModel.errorShown()

		assertEquals(
			CoinDetailsState(
				isErrorState = true,
				errorMessage = ErrorMessage.Empty,
				isLoading = false,
				isRefreshing = false
			), coinDetailsViewModel.uiState.value
		)

		coinDetailsViewModel.onRetry()

		assertEquals(
			CoinDetailsState(
				isErrorState = false,
				errorMessage = ErrorMessage.Empty,
				isLoading = true,
				isRefreshing = false
			), coinDetailsViewModel.uiState.value
		)

		verify(fetchCoinDetailsUseCase, times(2)).execute(eq(coinId))
	}
}