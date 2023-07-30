package neuro.cryptocurrencies.data.di

import neuro.cryptocurrencies.data.repository.FetchCoinsRepositoryImpl
import neuro.cryptocurrencies.data.repository.GetCoinByIdRepositoryImpl
import neuro.cryptocurrencies.data.repository.ObserveCoinsRepositoryImpl
import neuro.cryptocurrencies.domain.repository.FetchCoinsRepository
import neuro.cryptocurrencies.domain.repository.GetCoinByIdRepository
import neuro.cryptocurrencies.domain.repository.ObserveCoinsRepository
import org.koin.dsl.module

val repositoryModule = module {
	factory<GetCoinByIdRepository> { GetCoinByIdRepositoryImpl(get()) }
	factory<ObserveCoinsRepository> { ObserveCoinsRepositoryImpl(get()) }
	factory<FetchCoinsRepository> { FetchCoinsRepositoryImpl(get(), get()) }
}