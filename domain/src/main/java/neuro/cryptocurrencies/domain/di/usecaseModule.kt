package neuro.cryptocurrencies.domain.di

import neuro.cryptocurrencies.domain.usecase.FetchCoinsUseCase
import neuro.cryptocurrencies.domain.usecase.FetchCoinsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.GetCoinDetailsWithPriceUseCase
import neuro.cryptocurrencies.domain.usecase.GetCoinDetailsWithPriceUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.GetCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.GetCoinsTickersUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
	factory<GetCoinDetailsWithPriceUseCase> { GetCoinDetailsWithPriceUseCaseImpl(get(), get()) }
	factory<GetCoinsTickersUseCase> { GetCoinsTickersUseCaseImpl(get()) }
	factory<FetchCoinsUseCase> { FetchCoinsUseCaseImpl(get()) }
}