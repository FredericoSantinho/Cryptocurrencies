package neuro.cryptocurrencies.di

import android.app.Application
import android.content.Context
import org.koin.dsl.module

val appModule = module {
	factory<Application> { get<Context>() as Application }
}