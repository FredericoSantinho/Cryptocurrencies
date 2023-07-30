package neuro.cryptocurrencies.presentation.di

import neuro.cryptocurrencies.presentation.viewmodel.coins.CoinListViewModel
import neuro.cryptocurrencies.presentation.viewmodel.coins.details.CoinDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
	viewModel { CoinListViewModel(get(), get()) }
	viewModel { CoinDetailsViewModel(get(), get()) }
}