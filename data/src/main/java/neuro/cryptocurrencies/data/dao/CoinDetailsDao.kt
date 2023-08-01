package neuro.cryptocurrencies.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import neuro.cryptocurrencies.data.model.database.coin.details.RoomCoinDetailsWithPrice
import neuro.cryptocurrencies.data.model.database.coin.details.RoomCoinDetailsWithPriceWithTagsAndTeam

@Dao
interface CoinDetailsDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertCoinDetails(roomCoinTickers: RoomCoinDetailsWithPrice)

	@Transaction
	@Query("select * from coin_details_with_price_table where id=:coinId")
	fun observeCoinDetails(coinId: String): Flow<RoomCoinDetailsWithPriceWithTagsAndTeam?>

	@Transaction
	@Query("select * from coin_details_with_price_table where id=:coinId")
	suspend fun getCoinDetails(coinId: String): RoomCoinDetailsWithPriceWithTagsAndTeam?
}