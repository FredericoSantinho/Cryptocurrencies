package neuro.coin.paprika.data.di

import neuro.coin.paprika.data.dao.CoinDao
import neuro.coin.paprika.data.database.CoinpaprikaDatabase
import org.koin.dsl.module

val daoModule = module {
	factory<CoinDao> { get<CoinpaprikaDatabase>().coinDao }
}