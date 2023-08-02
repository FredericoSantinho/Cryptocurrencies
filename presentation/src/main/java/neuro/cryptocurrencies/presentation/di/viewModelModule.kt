package neuro.cryptocurrencies.presentation.di

import neuro.cryptocurrencies.presentation.viewmodel.coins.CoinListViewModelImpl
import neuro.cryptocurrencies.presentation.viewmodel.coins.details.CoinDetailsViewModelImpl
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
			get(),
			get(),
			get()
		)
	}
}