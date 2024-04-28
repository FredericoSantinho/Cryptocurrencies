package neuro.cryptocurrencies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import neuro.cryptocurrencies.data.database.CryptocurrenciesDatabase

@InstallIn(SingletonComponent::class)
@Module
class DaoModule {

	@Provides
	fun provideCoinTickerDao(cryptocurrenciesDatabase: CryptocurrenciesDatabase) =
		cryptocurrenciesDatabase.coinTickerDao

	@Provides
	fun provideCoinDetailsDao(cryptocurrenciesDatabase: CryptocurrenciesDatabase) =
		cryptocurrenciesDatabase.coinDetailsDao

	@Provides
	fun provideTagDao(cryptocurrenciesDatabase: CryptocurrenciesDatabase) =
		cryptocurrenciesDatabase.tagDao

	@Provides
	fun provideTeamDao(cryptocurrenciesDatabase: CryptocurrenciesDatabase) =
		cryptocurrenciesDatabase.teamDao

	@Provides
	fun provideTagDetailsDao(cryptocurrenciesDatabase: CryptocurrenciesDatabase) =
		cryptocurrenciesDatabase.tagDetailsDao
}