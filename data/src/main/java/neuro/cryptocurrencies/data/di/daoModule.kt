package neuro.cryptocurrencies.data.di

import neuro.cryptocurrencies.data.dao.CoinDetailsDao
import neuro.cryptocurrencies.data.dao.CoinTickerDao
import neuro.cryptocurrencies.data.dao.TagDao
import neuro.cryptocurrencies.data.dao.TagDetailsDao
import neuro.cryptocurrencies.data.dao.TeamDao
import neuro.cryptocurrencies.data.database.CryptocurrenciesDatabase
import org.koin.dsl.module

val daoModule = module {
	factory<CoinTickerDao> { get<CryptocurrenciesDatabase>().coinTickerDao }
	factory<CoinDetailsDao> { get<CryptocurrenciesDatabase>().coinDetailsDao }
	factory<TagDetailsDao> { get<CryptocurrenciesDatabase>().tagDetailsDao }
	factory<TagDao> { get<CryptocurrenciesDatabase>().tagDao }
	factory<TeamDao> { get<CryptocurrenciesDatabase>().teamDao }
}