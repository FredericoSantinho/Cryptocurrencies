package neuro.coin.paprika.domain.di

import neuro.coin.paprika.domain.usecase.GetCoinUseCase
import neuro.coin.paprika.domain.usecase.GetCoinUseCaseImpl
import neuro.coin.paprika.domain.usecase.GetCoinsUseCase
import neuro.coin.paprika.domain.usecase.GetCoinsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
	factory<GetCoinUseCase> { GetCoinUseCaseImpl(get()) }
	factory<GetCoinsUseCase> { GetCoinsUseCaseImpl(get()) }
}