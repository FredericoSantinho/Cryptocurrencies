package neuro.coin.paprika.domain.di

import neuro.coin.paprika.domain.usecase.GetCoinDetailsUseCase
import neuro.coin.paprika.domain.usecase.GetCoinDetailsUseCaseImpl
import neuro.coin.paprika.domain.usecase.GetCoinsUseCase
import neuro.coin.paprika.domain.usecase.GetCoinsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
	factory<GetCoinDetailsUseCase> { GetCoinDetailsUseCaseImpl(get()) }
	factory<GetCoinsUseCase> { GetCoinsUseCaseImpl(get()) }
}