package neuro.cryptocurrencies.domain.di

import neuro.cryptocurrencies.domain.usecase.coin.FetchCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.FetchCoinsTickersUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.HasCachedCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.HasCachedCoinsTickersUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.ObserveCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.ObserveCoinsTickersUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.details.FetchCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coin.details.FetchCoinDetailsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.details.HasCachedCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coin.details.HasCachedCoinDetailsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.details.ObserveCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coin.details.ObserveCoinDetailsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.tag.FetchTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tag.FetchTagDetailsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.tag.HasCachedTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tag.HasCachedTagDetailsUsecaseImpl
import neuro.cryptocurrencies.domain.usecase.tag.ObserveTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tag.ObserveTagDetailsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
	factory<ObserveCoinDetailsUseCase> { ObserveCoinDetailsUseCaseImpl(get()) }
	factory<FetchCoinDetailsUseCase> { FetchCoinDetailsUseCaseImpl(get(), get(), get()) }
	factory<HasCachedCoinDetailsUseCase> { HasCachedCoinDetailsUseCaseImpl(get()) }
	factory<ObserveTagDetailsUseCase> { ObserveTagDetailsUseCaseImpl(get()) }
	factory<HasCachedTagDetailsUseCase> { HasCachedTagDetailsUsecaseImpl(get()) }
	factory<FetchTagDetailsUseCase> { FetchTagDetailsUseCaseImpl(get(), get()) }
	factory<ObserveCoinsTickersUseCase> { ObserveCoinsTickersUseCaseImpl(get()) }
	factory<FetchCoinsTickersUseCase> { FetchCoinsTickersUseCaseImpl(get(), get()) }
	factory<HasCachedCoinsTickersUseCase> { HasCachedCoinsTickersUseCaseImpl(get()) }
}