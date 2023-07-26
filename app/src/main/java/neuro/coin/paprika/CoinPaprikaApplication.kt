package neuro.coin.paprika

import android.app.Application
import neuro.coin.paprika.data.di.networkModule
import neuro.coin.paprika.data.di.repositoryModule
import neuro.coin.paprika.presentation.di.useCaseModule
import neuro.coin.paprika.presentation.di.viewModelModule
import org.koin.core.context.startKoin

class CoinPaprikaApplication : Application() {
	override fun onCreate() {
		super.onCreate()

		startKoin {
			modules(networkModule, repositoryModule, useCaseModule, viewModelModule)
		}
	}
}