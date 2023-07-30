package neuro.coin.paprika.data.di

import neuro.coin.paprika.data.repository.FetchCoinsRepositoryImpl
import neuro.coin.paprika.data.repository.GetCoinByIdRepositoryImpl
import neuro.coin.paprika.data.repository.ObserveCoinsRepositoryImpl
import neuro.coin.paprika.domain.repository.FetchCoinsRepository
import neuro.coin.paprika.domain.repository.GetCoinByIdRepository
import neuro.coin.paprika.domain.repository.ObserveCoinsRepository
import org.koin.dsl.module

val repositoryModule = module {
	factory<GetCoinByIdRepository> { GetCoinByIdRepositoryImpl(get()) }
	factory<ObserveCoinsRepository> { ObserveCoinsRepositoryImpl(get()) }
	factory<FetchCoinsRepository> { FetchCoinsRepositoryImpl(get(), get()) }
}