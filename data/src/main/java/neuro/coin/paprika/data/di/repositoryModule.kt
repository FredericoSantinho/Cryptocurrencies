package neuro.coin.paprika.data.di

import neuro.coin.paprika.data.repository.GetCoinRepositoryImpl
import neuro.coin.paprika.data.repository.GetCoinsRepositoryImpl
import neuro.coin.paprika.domain.repository.GetCoinRepository
import neuro.coin.paprika.domain.repository.GetCoinsRepository
import org.koin.dsl.module

val repositoryModule = module {
	factory<GetCoinRepository> { GetCoinRepositoryImpl(get()) }
	factory<GetCoinsRepository> { GetCoinsRepositoryImpl(get()) }
}