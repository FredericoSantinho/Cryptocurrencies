package neuro.cryptocurrencies.domain.di

import neuro.cryptocurrencies.domain.usecase.coin.FetchCoinsUseCase
import neuro.cryptocurrencies.domain.usecase.coin.FetchCoinsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.GetCoinDetailsWithPriceUseCase
import neuro.cryptocurrencies.domain.usecase.coin.GetCoinDetailsWithPriceUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.GetCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.GetCoinsTickersUseCaseImpl
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
	factory<GetCoinsTickersUseCase> { GetCoinsTickersUseCaseImpl(get()) }
	factory<FetchCoinsUseCase> { FetchCoinsUseCaseImpl(get()) }
}