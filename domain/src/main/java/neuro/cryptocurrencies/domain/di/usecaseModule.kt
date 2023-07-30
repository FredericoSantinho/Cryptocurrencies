package neuro.cryptocurrencies.domain.di

import neuro.cryptocurrencies.domain.usecase.FetchCoinsUseCase
import neuro.cryptocurrencies.domain.usecase.FetchCoinsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.GetCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.GetCoinDetailsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.GetCoinsUseCase
import neuro.cryptocurrencies.domain.usecase.GetCoinsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
	factory<GetCoinDetailsUseCase> { GetCoinDetailsUseCaseImpl(get()) }
	factory<GetCoinsUseCase> { GetCoinsUseCaseImpl(get()) }
	factory<FetchCoinsUseCase> { FetchCoinsUseCaseImpl(get()) }
}