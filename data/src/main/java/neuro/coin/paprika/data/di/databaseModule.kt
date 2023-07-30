package neuro.coin.paprika.data.di

import androidx.room.Room
import neuro.coin.paprika.data.database.CoinpaprikaDatabase
import org.koin.dsl.module

private const val DATABASE_NAME = "coinpaprika_database"

val databaseModule = module {
	single<CoinpaprikaDatabase> {
		Room.databaseBuilder(get(), CoinpaprikaDatabase::class.java, DATABASE_NAME).build()
	}
}