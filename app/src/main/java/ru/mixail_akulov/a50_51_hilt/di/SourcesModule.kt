package ru.mixail_akulov.a50_51_hilt.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.mixail_akulov.a50_51_hilt.app.model.accounts.AccountsSource
import ru.mixail_akulov.a50_51_hilt.app.model.boxes.BoxesSource
import ru.mixail_akulov.a50_51_hilt.sources.accounts.RetrofitAccountsSource
import ru.mixail_akulov.a50_51_hilt.sources.boxes.RetrofitBoxesSource

/**
 * This module binds concrete sources implementations to their
 * interfaces: [RetrofitAccountsSource] is bound to [AccountsSource]
 * and [RetrofitBoxesSource] is bound to [BoxesSource].
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class SourcesModule {

    @Binds
    abstract fun bindAccountsSource(
        retrofitAccountsSource: RetrofitAccountsSource
    ): AccountsSource

    @Binds
    abstract fun bindBoxesSource(
        retrofitBoxesSource: RetrofitBoxesSource
    ): BoxesSource

}