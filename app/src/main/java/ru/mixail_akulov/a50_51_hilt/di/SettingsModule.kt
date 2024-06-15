package ru.mixail_akulov.a50_51_hilt.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.mixail_akulov.a50_51_hilt.app.model.settings.AppSettings
import ru.mixail_akulov.a50_51_hilt.app.model.settings.SharedPreferencesAppSettings

/**
 * This module is responsible for binding the concrete implementation
 * [SharedPreferencesAppSettings] to the [AppSettings] interface, so
 * Hilt will know which instance should be delivered to clients when
 * they ask for [AppSettings] dependency.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

    @Binds
    abstract fun bindAppSettings(
        appSettings: SharedPreferencesAppSettings
    ): AppSettings

}
