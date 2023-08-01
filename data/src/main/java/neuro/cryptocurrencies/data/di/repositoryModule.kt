package neuro.cryptocurrencies.data.di

import neuro.cryptocurrencies.data.repository.coin.FetchCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.GetCoinTickerRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.HasCachedCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.ObserveCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.details.GetCachedCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.details.GetCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.details.ObserveCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.details.SaveCoinDetailsWithPriceRepositoryImpl
import neuro.cryptocurrencies.data.repository.tag.FetchTagDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.tag.GetTagDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.tag.ObserveTagDetailsRepositoryImpl
import neuro.cryptocurrencies.domain.repository.coin.FetchCoinsTickersRepository
import neuro.cryptocurrencies.domain.repository.coin.GetCoinTickerRepository
import neuro.cryptocurrencies.domain.repository.coin.HasCachedCoinsTickersRepository
import neuro.cryptocurrencies.domain.repository.coin.ObserveCoinsTickersRepository
import neuro.cryptocurrencies.domain.repository.coin.details.GetCachedCoinDetailsRepository
import neuro.cryptocurrencies.domain.repository.coin.details.GetCoinDetailsRepository
import neuro.cryptocurrencies.domain.repository.coin.details.ObserveCoinDetailsRepository
import neuro.cryptocurrencies.domain.repository.coin.details.SaveCoinDetailsWithPriceRepository
import neuro.cryptocurrencies.domain.repository.tag.FetchTagDetailsRepository
import neuro.cryptocurrencies.domain.repository.tag.GetTagDetailsRepository
import neuro.cryptocurrencies.domain.repository.tag.ObserveTagDetailsRepository
import org.koin.dsl.module

val repositoryModule = module {
	factory<GetCoinDetailsRepository> { GetCoinDetailsRepositoryImpl(get()) }
	factory<ObserveCoinDetailsRepository> { ObserveCoinDetailsRepositoryImpl(get()) }
	factory<GetCachedCoinDetailsRepository> { GetCachedCoinDetailsRepositoryImpl(get()) }
	factory<SaveCoinDetailsWithPriceRepository> {
		SaveCoinDetailsWithPriceRepositoryImpl(
			get(),
			get(),
			get(),
			get()
		)
	}
	factory<ObserveTagDetailsRepository> { ObserveTagDetailsRepositoryImpl(get()) }
	factory<GetTagDetailsRepository> { GetTagDetailsRepositoryImpl(get()) }
	factory<FetchTagDetailsRepository> { FetchTagDetailsRepositoryImpl(get(), get()) }
	factory<ObserveCoinsTickersRepository> { ObserveCoinsTickersRepositoryImpl(get()) }
	factory<GetCoinTickerRepository> { GetCoinTickerRepositoryImpl(get()) }
	factory<FetchCoinsTickersRepository> { FetchCoinsTickersRepositoryImpl(get(), get()) }
	factory<HasCachedCoinsTickersRepository> { HasCachedCoinsTickersRepositoryImpl(get()) }
}