package neuro.cryptocurrencies.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import neuro.cryptocurrencies.data.database.CryptocurrenciesDatabase

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

	@Provides
	fun provideCryptocurrenciesDatabase(@ApplicationContext context: Context) =
		Room.databaseBuilder(context, CryptocurrenciesDatabase::class.java, DATABASE_NAME).build()

	@Provides
	fun provideRoomDatabase(cryptocurrenciesDatabase: CryptocurrenciesDatabase): RoomDatabase =
		cryptocurrenciesDatabase

	companion object {
		private const val DATABASE_NAME = "cryptocurrencies_database"
	}
}