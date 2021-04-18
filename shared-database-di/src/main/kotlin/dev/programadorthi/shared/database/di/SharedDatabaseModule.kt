package dev.programadorthi.shared.database.di

import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.programadorthi.shared.database.NorrisQueries
import dev.programadorthi.shared.database.SuperApp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
