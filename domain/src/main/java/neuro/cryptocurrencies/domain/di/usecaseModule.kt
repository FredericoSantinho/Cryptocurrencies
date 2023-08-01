package neuro.cryptocurrencies.domain.di

import neuro.cryptocurrencies.domain.usecase.coin.FetchCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.FetchCoinsTickersUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.HasCachedCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.HasCachedCoinsTickersUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.ObserveCoinsTickersUseCase
import neuro.cryptocurrencies.domain.usecase.coin.ObserveCoinsTickersUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.details.FetchCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coin.details.FetchCoinDetailsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.details.GetCachedCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coin.details.GetCachedCoinDetailsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.coin.details.ObserveCoinDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.coin.details.ObserveCoinDetailsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.tag.FetchTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tag.FetchTagDetailsUseCaseImpl
import neuro.cryptocurrencies.domain.usecase.tag.GetTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tag.GetTagDetailsUsecaseImpl
import neuro.cryptocurrencies.domain.usecase.tag.ObserveTagDetailsUseCase
import neuro.cryptocurrencies.domain.usecase.tag.ObserveTagDetailsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
	factory<ObserveCoinDetailsUseCase> { ObserveCoinDetailsUseCaseImpl(get()) }
	factory<FetchCoinDetailsUseCase> { FetchCoinDetailsUseCaseImpl(get(), get(), get()) }
	factory<GetCachedCoinDetailsUseCase> { GetCachedCoinDetailsUseCaseImpl(get()) }
	factory<ObserveTagDetailsUseCase> { ObserveTagDetailsUseCaseImpl(get()) }
	factory<GetTagDetailsUseCase> { GetTagDetailsUsecaseImpl(get()) }
	factory<FetchTagDetailsUseCase> { FetchTagDetailsUseCaseImpl(get()) }
	factory<ObserveCoinsTickersUseCase> { ObserveCoinsTickersUseCaseImpl(get()) }
	factory<FetchCoinsTickersUseCase> { FetchCoinsTickersUseCaseImpl(get()) }
	factory<HasCachedCoinsTickersUseCase> { HasCachedCoinsTickersUseCaseImpl(get()) }
}