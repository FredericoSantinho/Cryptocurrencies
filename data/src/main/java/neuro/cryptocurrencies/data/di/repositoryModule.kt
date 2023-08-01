package neuro.cryptocurrencies.data.di

import neuro.cryptocurrencies.data.repository.coin.GetCoinTickerRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.GetCoinTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.HasCachedCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.ObserveCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.SaveCoinTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.details.GetCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.details.HasCachedCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.details.ObserveCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.coin.details.SaveCoinDetailsWithPriceRepositoryImpl
import neuro.cryptocurrencies.data.repository.tag.GetTagDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.tag.HasCachedTagDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.tag.ObserveTagDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.tag.SaveTagDetailsRepositoryImpl
import neuro.cryptocurrencies.domain.repository.coin.GetCoinTickerRepository
import neuro.cryptocurrencies.domain.repository.coin.GetCoinTickersRepository
import neuro.cryptocurrencies.domain.repository.coin.HasCachedCoinsTickersRepository
import neuro.cryptocurrencies.domain.repository.coin.ObserveCoinsTickersRepository
import neuro.cryptocurrencies.domain.repository.coin.SaveCoinTickersRepository
import neuro.cryptocurrencies.domain.repository.coin.details.GetCoinDetailsRepository
import neuro.cryptocurrencies.domain.repository.coin.details.HasCachedCoinDetailsRepository
import neuro.cryptocurrencies.domain.repository.coin.details.ObserveCoinDetailsRepository
import neuro.cryptocurrencies.domain.repository.coin.details.SaveCoinDetailsWithPriceRepository
import neuro.cryptocurrencies.domain.repository.tag.GetTagDetailsRepository
import neuro.cryptocurrencies.domain.repository.tag.HasCachedTagDetailsRepository
import neuro.cryptocurrencies.domain.repository.tag.ObserveTagDetailsRepository
import neuro.cryptocurrencies.domain.repository.tag.SaveTagDetailsRepository
import org.koin.dsl.module

val repositoryModule = module {
	factory<GetCoinDetailsRepository> { GetCoinDetailsRepositoryImpl(get()) }
	factory<ObserveCoinDetailsRepository> { ObserveCoinDetailsRepositoryImpl(get()) }
	factory<HasCachedCoinDetailsRepository> { HasCachedCoinDetailsRepositoryImpl(get()) }
	factory<SaveCoinDetailsWithPriceRepository> {
		SaveCoinDetailsWithPriceRepositoryImpl(
			get(),
			get(),
			get(),
			get()
		)
	}
	factory<ObserveTagDetailsRepository> { ObserveTagDetailsRepositoryImpl(get()) }
	factory<HasCachedTagDetailsRepository> { HasCachedTagDetailsRepositoryImpl(get()) }
	factory<GetTagDetailsRepository> { GetTagDetailsRepositoryImpl(get()) }
	factory<SaveTagDetailsRepository> { SaveTagDetailsRepositoryImpl(get()) }
	factory<ObserveCoinsTickersRepository> { ObserveCoinsTickersRepositoryImpl(get()) }
	factory<GetCoinTickerRepository> { GetCoinTickerRepositoryImpl(get()) }
	factory<GetCoinTickersRepository> { GetCoinTickersRepositoryImpl(get()) }
	factory<SaveCoinTickersRepository> { SaveCoinTickersRepositoryImpl(get()) }
	factory<HasCachedCoinsTickersRepository> { HasCachedCoinsTickersRepositoryImpl(get()) }
}