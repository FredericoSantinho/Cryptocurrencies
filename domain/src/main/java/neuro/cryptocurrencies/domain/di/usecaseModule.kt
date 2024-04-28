package neuro.cryptocurrencies.domain.di

import neuro.cryptocurrencies.domain.usecase.coinDetails.FetchCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coinDetails.FetchCoinDetailsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coinDetails.HasCachedCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coinDetails.HasCachedCoinDetailsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coinDetails.ObserveCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coinDetails.ObserveCoinDetailsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coinTickers.FetchCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coinTickers.FetchCoinsTickersUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coinTickers.HasCachedCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coinTickers.HasCachedCoinsTickersUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coinTickers.ObserveCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coinTickers.ObserveCoinsTickersUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.tagDetails.FetchTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tagDetails.FetchTagDetailsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.tagDetails.HasCachedTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tagDetails.HasCachedTagDetailsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.tagDetails.ObserveTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tagDetails.ObserveTagDetailsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
	factory<ObserveCoinDetailsUseCase> { ObserveCoinDetailsUseCaseImpl(get()) }
	factory<FetchCoinDetailsUseCase> { FetchCoinDetailsUseCaseImpl(get(), get(), get()) }
	factory<HasCachedCoinDetailsUseCase> { HasCachedCoinDetailsUseCaseImpl(get()) }
	factory<ObserveTagDetailsUseCase> { ObserveTagDetailsUseCaseImpl(get()) }
	factory<HasCachedTagDetailsUseCase> { HasCachedTagDetailsUseCaseImpl(get()) }
	factory<FetchTagDetailsUseCase> { FetchTagDetailsUseCaseImpl(get(), get()) }
	factory<ObserveCoinsTickersUseCase> { ObserveCoinsTickersUseCaseImpl(get()) }
	factory<FetchCoinsTickersUseCase> { FetchCoinsTickersUseCaseImpl(get(), get()) }
	factory<HasCachedCoinsTickersUseCase> { HasCachedCoinsTickersUseCaseImpl(get()) }
}