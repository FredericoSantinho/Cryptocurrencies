package neuro.cryptocurrencies.data.di

import neuro.cryptocurrencies.data.repository.FetchCoinsRepositoryImpl
import neuro.cryptocurrencies.data.repository.FetchTagRepositoryImpl
import neuro.cryptocurrencies.data.repository.GetCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.GetCoinTickerRepositoryImpl
import neuro.cryptocurrencies.data.repository.GetTagRepositoryImpl
import neuro.cryptocurrencies.data.repository.ObserveCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.ObserveTagRepositoryImpl
import neuro.cryptocurrencies.domain.repository.coin.FetchCoinsRepository
import neuro.cryptocurrencies.domain.repository.coin.GetCoinDetailsRepository
import neuro.cryptocurrencies.domain.repository.coin.GetCoinTickerRepository
import neuro.cryptocurrencies.domain.repository.coin.ObserveCoinsTickersRepository
import neuro.cryptocurrencies.domain.repository.tag.FetchTagRepository
import neuro.cryptocurrencies.domain.repository.tag.GetTagRepository
import neuro.cryptocurrencies.domain.repository.tag.ObserveTagRepository
import org.koin.dsl.module

val repositoryModule = module {
	factory<GetCoinDetailsRepository> { GetCoinDetailsRepositoryImpl(get()) }
	factory<ObserveTagRepository> { ObserveTagRepositoryImpl(get()) }
	factory<GetTagRepository> { GetTagRepositoryImpl(get()) }
	factory<FetchTagRepository> { FetchTagRepositoryImpl(get(), get()) }
	factory<ObserveCoinsTickersRepository> { ObserveCoinsTickersRepositoryImpl(get()) }
	factory<GetCoinTickerRepository> { GetCoinTickerRepositoryImpl(get()) }
	factory<FetchCoinsRepository> { FetchCoinsRepositoryImpl(get(), get()) }
}