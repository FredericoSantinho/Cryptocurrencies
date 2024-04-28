package neuro.cryptocurrencies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class DispatchersModule {

	@Provides
	fun provideCoroutineDispatcher() = Dispatchers.IO
}