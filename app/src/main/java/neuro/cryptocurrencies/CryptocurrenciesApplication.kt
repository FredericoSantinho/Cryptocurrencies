package neuro.cryptocurrencies

import android.app.Application
import neuro.cryptocurrencies.data.di.daoModule
import neuro.cryptocurrencies.data.di.databaseModule
import neuro.cryptocurrencies.data.di.networkModule
import neuro.cryptocurrencies.data.di.repositoryModule
import neuro.cryptocurrencies.presentation.di.useCaseModule
import neuro.cryptocurrencies.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptocurrenciesApplication : Application() {
	override fun onCreate() {
		super.onCreate()

		startKoin {
			modules(
				networkModule,
				daoModule,
				databaseModule,
				repositoryModule,
				useCaseModule,
				viewModelModule,
			)
			androidContext(this@CryptocurrenciesApplication)
		}
	}
}