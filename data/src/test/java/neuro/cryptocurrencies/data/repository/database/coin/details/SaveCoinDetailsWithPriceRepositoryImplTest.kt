package neuro.cryptocurrencies.data.repository.database.coin.details

import androidx.room.RoomDatabase
import androidx.room.withTransaction
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockkStatic
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import neuro.cryptocurrencies.data.dao.CoinDetailsDao
import neuro.cryptocurrencies.data.dao.TagDao
import neuro.cryptocurrencies.data.dao.TeamDao
import neuro.cryptocurrencies.data.mocks.database.roomCoinDetailsWithPriceMock
import neuro.cryptocurrencies.data.mocks.database.roomTagMockList
import neuro.cryptocurrencies.data.mocks.database.roomTeamMemberMockList
import neuro.cryptocurrencies.domain.mocks.coinDetailsMock
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions

class SaveCoinDetailsWithPriceRepositoryImplTest {

	private val database = mock<RoomDatabase>()

	@Before
	fun initMocks() {
		MockKAnnotations.init(this)

		mockkStatic(
			"androidx.room.RoomDatabaseKt"
		)

		val transactionLambda = slot<suspend () -> Unit>()
		coEvery { database.withTransaction(capture(transactionLambda)) } coAnswers {
			transactionLambda.captured.invoke()
		}
	}

	@Test
	fun test() = runBlocking {
		val coinDetailsDao = mock<CoinDetailsDao>()
		val tagDao = mock<TagDao>()
		val teamDao = mock<TeamDao>()

		val saveCoinDetailsWithPriceRepository =
			SaveCoinDetailsWithPriceRepositoryImpl(database, coinDetailsDao, tagDao, teamDao)

		verifyNoInteractions(coinDetailsDao)
		verifyNoInteractions(tagDao)
		verifyNoInteractions(teamDao)

		saveCoinDetailsWithPriceRepository.saveCoinDetailsWithPrice(coinDetailsMock(), 25221.0)

		verify(tagDao, times(1)).upsertTags(roomTagMockList())
		verify(teamDao, times(1)).upsertTeamMember(roomTeamMemberMockList())
		verify(coinDetailsDao, times(1)).upsertCoinDetails(roomCoinDetailsWithPriceMock())
	}
}