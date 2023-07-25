package neuro.coin.paprika.data.di

import neuro.coin.paprika.data.api.CoinPaprikaApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.coinpaprika.com/"

val networkModule = module {
	single<Retrofit> {
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}
	single<CoinPaprikaApi> { get<Retrofit>().create(CoinPaprikaApi::class.java) }
}