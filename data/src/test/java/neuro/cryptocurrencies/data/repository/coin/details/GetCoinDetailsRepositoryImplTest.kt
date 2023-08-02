package neuro.cryptocurrencies.data.repository.coin.details

import kotlinx.coroutines.runBlocking
import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.model.network.coin.details.CoinDetailsDto
import neuro.cryptocurrencies.data.model.network.coin.details.Links
import neuro.cryptocurrencies.data.model.network.coin.details.Whitepaper
import neuro.cryptocurrencies.domain.entity.CoinDetails
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class GetCoinDetailsRepositoryImplTest {
	@Test
	fun test() = runBlocking {
		val coinPaprikaApi = mock<CoinPaprikaApi>()

		val getCoinDetailsRepository = GetCoinDetailsRepositoryImpl(coinPaprikaApi)

		val coinId = "btc-bitcoin"
		whenever(coinPaprikaApi.getCoinById(coinId)).thenReturn(coinDetailsDtoMock())

		verifyNoInteractions(coinPaprikaApi)

		val coinDetails = getCoinDetailsRepository.getCoinDetails(coinId)

		verify(coinPaprikaApi, times(1)).getCoinById(coinId)

		assertEquals(coinDetailsMock(), coinDetails)
	}

	private fun coinDetailsMock(): CoinDetails {
		return CoinDetails(
			"btc-bitcoin",
			"description",
			"SHA-256",
			"logo",
			"Bitcoin",
			true,
			"POW",
			1,
			"BTC",
			emptyList(),
			emptyList(),
			"coin"
		)
	}

	private fun coinDetailsDtoMock() = CoinDetailsDto(
		"description",
		"",
		"",
		true,
		"SHA-256",
		"btc-bitcoin",
		true,
		true,
		"",
		Links(emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList()),
		emptyList(),
		"logo",
		"",
		"Bitcoin",
		true,
		"",
		"POW",
		1,
		"",
		"BTC",
		emptyList(),
		emptyList(),
		"coin",
		Whitepaper("", "")
	)
}