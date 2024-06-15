package ru.mixail_akulov.a50_51_hilt.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.mixail_akulov.a50_51_hilt.app.utils.logger.LogCatLogger
import ru.mixail_akulov.a50_51_hilt.app.utils.logger.Logger

/**
 * Module for providing [Logger] implementation based on
 * system [Log] class.
 */
@Module
@InstallIn(SingletonComponent::class)
class StuffsModule {

    /**
     * We don't need scope annotation here because LogCatHolder is
     * 'object' (already singleton)
     */
    @Provides
    fun provideLogger(): Logger {
        return LogCatLogger
    }

}