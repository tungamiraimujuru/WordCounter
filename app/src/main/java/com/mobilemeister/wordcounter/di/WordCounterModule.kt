package com.mobilemeister.wordcounter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
interface WordCounterModule {

    companion object {
        @Provides
        @Named("ui")
        fun provideUiContext(): CoroutineContext {
            return Dispatchers.Main
        }

        @Provides
        @Named("io")
        fun provideIoContext(): CoroutineContext {
            return Dispatchers.IO
        }

        @Provides
        @Named("Unconfined")
        fun providesUnconfinedContext(): CoroutineContext {
            return Dispatchers.Unconfined
        }

        @Provides
        @Named("Default")
        fun providesDefaultContext(): CoroutineContext {
            return Dispatchers.Default
        }
    }
}