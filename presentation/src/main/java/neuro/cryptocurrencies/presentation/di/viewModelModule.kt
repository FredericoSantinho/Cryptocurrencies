package neuro.cryptocurrencies.presentation.di

import neuro.cryptocurrencies.presentation.viewmodel.coinDetails.CoinDetailsViewModelImpl
import neuro.cryptocurrencies.presentation.viewmodel.coinList.CoinListViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
	viewModel { CoinListViewModelImpl(get(), get(), get()) }
	viewModel {
		CoinDetailsViewModelImpl(
			get(),
			get(),
			get(),
			get(),
			get(),
			get(),
			get(),
			get(),
		)
	}
}