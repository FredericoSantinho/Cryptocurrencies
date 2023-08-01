package neuro.cryptocurrencies.domain.di

import neuro.cryptocurrencies.domain.usecase.coin.FetchCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.FetchCoinsTickersUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.HasCachedCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.HasCachedCoinsTickersUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.ObserveCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.ObserveCoinsTickersUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.details.GetCoinDetailsWithPriceUseCase
import neuro.cryptocurrencies.domain.usecase.coin.details.GetCoinDetailsWithPriceUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.tag.FetchTagUseCase
import neuro.cryptocurrencies.domain.usecase.tag.FetchTagUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.tag.GetTagUseCase
import neuro.cryptocurrencies.domain.usecase.tag.GetTagUsecaseImpl
import neuro.cryptocurrencies.domain.usecase.tag.ObserveTagUseCase
import neuro.cryptocurrencies.domain.usecase.tag.ObserveTagUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
	factory<GetCoinDetailsWithPriceUseCase> { GetCoinDetailsWithPriceUseCaseImpl(get(), get()) }
	factory<ObserveTagUseCase> { ObserveTagUseCaseImpl(get()) }
	factory<GetTagUseCase> { GetTagUsecaseImpl(get()) }
	factory<FetchTagUseCase> { FetchTagUseCaseImpl(get()) }
	factory<ObserveCoinsTickersUseCase> { ObserveCoinsTickersUseCaseImpl(get()) }
	factory<FetchCoinsTickersUseCase> { FetchCoinsTickersUseCaseImpl(get()) }
	factory<HasCachedCoinsTickersUseCase> { HasCachedCoinsTickersUseCaseImpl(get()) }
}