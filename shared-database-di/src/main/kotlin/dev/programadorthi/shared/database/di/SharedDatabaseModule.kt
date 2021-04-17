package dev.programadorthi.shared.database.di

import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import dev.programadorthi.shared.database.NorrisQueries
import dev.programadorthi.shared.database.SuperApp
import javax.inject.Singleton

@DisableInstallInCheck
@Module
object SharedDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        driver: SqlDriver
    ): SuperApp = SuperApp(driver)

    @Singleton
    @Provides
    fun provideNorrisQueries(
        superApp: SuperApp
    ): NorrisQueries = superApp.norrisQueries
}
