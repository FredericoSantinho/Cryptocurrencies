package neuro.cryptocurrencies.data.di

import neuro.cryptocurrencies.data.repository.FetchCoinsRepositoryImpl
import neuro.cryptocurrencies.data.repository.GetCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.GetCoinTickerRepositoryImpl
import neuro.cryptocurrencies.data.repository.ObserveCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.domain.repository.FetchCoinsRepository
import neuro.cryptocurrencies.domain.repository.GetCoinDetailsRepository
import neuro.cryptocurrencies.domain.repository.GetCoinTickerRepository
import neuro.cryptocurrencies.domain.repository.ObserveCoinsTickersRepository
import org.koin.dsl.module

val repositoryModule = module {
	factory<GetCoinDetailsRepository> { GetCoinDetailsRepositoryImpl(get()) }
	factory<ObserveCoinsTickersRepository> { ObserveCoinsTickersRepositoryImpl(get()) }
	factory<GetCoinTickerRepository> { GetCoinTickerRepositoryImpl(get()) }
	factory<FetchCoinsRepository> { FetchCoinsRepositoryImpl(get(), get()) }
}