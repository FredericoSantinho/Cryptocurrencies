package neuro.cryptocurrencies.data.di

import androidx.room.Room
import neuro.cryptocurrencies.data.database.CryptocurrenciesDatabase
import org.koin.dsl.module

private const val DATABASE_NAME = "cryptocurrencies_database"

val databaseModule = module {
	single<CryptocurrenciesDatabase> {
		Room.databaseBuilder(get(), CryptocurrenciesDatabase::class.java, DATABASE_NAME).build()
	}
}