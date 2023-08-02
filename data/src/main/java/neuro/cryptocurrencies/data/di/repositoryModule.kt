package neuro.cryptocurrencies.data.di

import neuro.cryptocurrencies.data.repository.database.coin.HasCachedCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.coin.ObserveCoinsTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.coin.SaveCoinTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.coin.details.HasCachedCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.coin.details.ObserveCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.coin.details.SaveCoinDetailsWithPriceRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.tag.HasCachedTagDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.tag.ObserveTagDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.tag.SaveTagDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.team.HasCachedTeamMemberDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.team.ObserveTeamMemberDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.database.team.SaveTeamMemberDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.network.coin.GetCoinTickerRepositoryImpl
import neuro.cryptocurrencies.data.repository.network.coin.GetCoinTickersRepositoryImpl
import neuro.cryptocurrencies.data.repository.network.coin.details.GetCoinDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.network.tag.GetTagDetailsRepositoryImpl
import neuro.cryptocurrencies.data.repository.network.team.GetTeamMemberDetailsRepositoryImpl
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
import neuro.cryptocurrencies.domain.repository.team.GetTeamMemberDetailsRepository
import neuro.cryptocurrencies.domain.repository.team.HasCachedTeamMemberDetailsRepository
import neuro.cryptocurrencies.domain.repository.team.ObserveTeamMemberDetailsRepository
import neuro.cryptocurrencies.domain.repository.team.SaveTeamMemberDetailsRepository
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
	factory<ObserveTeamMemberDetailsRepository> { ObserveTeamMemberDetailsRepositoryImpl(get()) }
	factory<HasCachedTeamMemberDetailsRepository> { HasCachedTeamMemberDetailsRepositoryImpl(get()) }
	factory<GetTeamMemberDetailsRepository> { GetTeamMemberDetailsRepositoryImpl(get()) }
	factory<SaveTeamMemberDetailsRepository> { SaveTeamMemberDetailsRepositoryImpl(get()) }
}