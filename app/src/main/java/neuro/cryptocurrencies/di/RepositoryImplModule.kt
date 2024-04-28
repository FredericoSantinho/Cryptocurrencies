package neuro.cryptocurrencies.di

import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.dao.CoinDetailsDao
import neuro.cryptocurrencies.data.dao.CoinTickerDao
import neuro.cryptocurrencies.data.dao.TagDao
import neuro.cryptocurrencies.data.dao.TagDetailsDao
import neuro.cryptocurrencies.data.dao.TeamDao
import neuro.cryptocurrencies.data.repository.database.coin.HasCachedCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.coin.ObserveCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.coin.SaveCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.coin.details.HasCachedCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.coin.details.ObserveCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.coin.details.SaveCoinDetailsWithPriceRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.tag.HasCachedTagDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.tag.ObserveTagDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.tag.SaveTagDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.network.coin.GetCoinTickerRepositoryImpl
import neuro.cryptocurrencies.data.repository.network.coin.GetCoinTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.network.coin.details.GetCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.network.tag.GetTagDetailsRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
class RepositoryImplModule {

	@Provides
	fun provideObserveCoinsTickersRepositoryImpl(coinTickerDao: CoinTickerDao) =
		ObserveCoinsTickersRepositoryImpl(coinTickerDao)

	@Provides
	fun provideGetCoinTickersRepositoryImpl(coinPaprikaApi: CoinPaprikaApi) =
		GetCoinTickersRepositoryImpl(coinPaprikaApi)

	@Provides
	fun provideSaveCoinsTickersRepositoryImpl(coinTickerDao: CoinTickerDao) =
		SaveCoinsTickersRepositoryImpl(coinTickerDao)

	@Provides
	fun provideHasCachedCoinsTickersRepositoryImpl(coinTickerDao: CoinTickerDao) =
		HasCachedCoinsTickersRepositoryImpl(coinTickerDao)

	@Provides
	fun provideObserveCoinDetailsRepositoryImpl(coinDetailsDao: CoinDetailsDao) =
		ObserveCoinDetailsRepositoryImpl(coinDetailsDao)

	@Provides
	fun provideGetCoinTickerRepositoryImpl(coinPaprikaApi: CoinPaprikaApi) =
		GetCoinTickerRepositoryImpl(coinPaprikaApi)

	@Provides
	fun provideGetCoinDetailsRepositoryImpl(coinPaprikaApi: CoinPaprikaApi) =
		GetCoinDetailsRepositoryImpl(coinPaprikaApi)

	@Provides
	fun provideSaveCoinDetailsWithPriceRepositoryImpl(
		roomDatabase: RoomDatabase,
		coinDetailsDao: CoinDetailsDao,
		tagDao: TagDao,
		teamDao: TeamDao
	) = SaveCoinDetailsWithPriceRepositoryImpl(roomDatabase, coinDetailsDao, tagDao, teamDao)

	@Provides
	fun provideHasCachedCoinDetailsRepositoryImpl(coinDetailsDao: CoinDetailsDao) =
		HasCachedCoinDetailsRepositoryImpl(coinDetailsDao)

	@Provides
	fun provideObserveTagDetailsRepositoryImpl(tagDetailsDao: TagDetailsDao) =
		ObserveTagDetailsRepositoryImpl(tagDetailsDao)

	@Provides
	fun provideGetTagDetailsRepositoryImpl(coinPaprikaApi: CoinPaprikaApi) =
		GetTagDetailsRepositoryImpl(coinPaprikaApi)

	@Provides
	fun provideSaveTagDetailsRepositoryImpl(tagDetailsDao: TagDetailsDao) =
		SaveTagDetailsRepositoryImpl(tagDetailsDao)

	@Provides
	fun provideHasCachedTagDetailsRepositoryImpl(tagDetailsDao: TagDetailsDao) =
		HasCachedTagDetailsRepositoryImpl(tagDetailsDao)
}