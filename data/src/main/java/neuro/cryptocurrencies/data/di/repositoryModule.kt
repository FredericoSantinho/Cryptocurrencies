package neuro.cryptocurrencies.data.di

import neuro.cryptocurrencies.data.repository.coin.FetchCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.GetCoinTickerRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.HasCachedCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.ObserveCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.details.GetCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.tag.FetchTagRepositoryImpl
import neuro.cryptocurrencies.data.repository.tag.GetTagRepositoryImpl
import neuro.cryptocurrencies.data.repository.tag.ObserveTagRepositoryImpl
import neuro.cryptocurrencies.domain.repository.coin.FetchCoinsTickersRepository
import neuro.cryptocurrencies.domain.repository.coin.GetCoinTickerRepository
import neuro.cryptocurrencies.domain.repository.coin.HasCachedCoinsTickersRepository
import neuro.cryptocurrencies.domain.repository.coin.ObserveCoinsTickersRepository
import neuro.cryptocurrencies.domain.repository.coin.details.GetCoinDetailsRepository
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
	factory<FetchCoinsTickersRepository> { FetchCoinsTickersRepositoryImpl(get(), get()) }
	factory<HasCachedCoinsTickersRepository> { HasCachedCoinsTickersRepositoryImpl(get()) }
}