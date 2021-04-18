package dev.programadorthi.shared.database.android

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.programadorthi.shared.database.SuperApp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedDatabaseAndroidModule {
    @Singleton
    @Provides
    fun provideSqlDriver(
        @ApplicationContext context: Context
    ): SqlDriver = AndroidSqliteDriver(
        schema = SuperApp.Schema,
        context = context,
        name = "superapp"
    )
}
