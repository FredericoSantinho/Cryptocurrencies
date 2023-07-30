package neuro.cryptocurrencies.data.di

import neuro.cryptocurrencies.data.dao.CoinDao
import neuro.cryptocurrencies.data.database.CryptocurrenciesDatabase
import org.koin.dsl.module

val daoModule = module {
	factory<CoinDao> { get<CryptocurrenciesDatabase>().coinDao }
}