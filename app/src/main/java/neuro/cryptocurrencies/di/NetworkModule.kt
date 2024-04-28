package neuro.cryptocurrencies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import neuro.cryptocurrencies.data.api.CoinPaprikaApi
import neuro.cryptocurrencies.data.dao.CoinTickerDao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

	@Provides
	fun provideRetrofit(coinTickerDao: CoinTickerDao) =
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()


	@Provides
	fun provideCoinPaprikaApi(retrofit: Retrofit) =
		retrofit.create(CoinPaprikaApi::class.java)

	companion object {
		private const val BASE_URL = "https://api.coinpaprika.com/"
	}
}