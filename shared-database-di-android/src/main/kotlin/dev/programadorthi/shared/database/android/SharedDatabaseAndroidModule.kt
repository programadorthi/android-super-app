package dev.programadorthi.shared.database.android

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.migration.DisableInstallInCheck
import dev.programadorthi.shared.database.SuperApp
import javax.inject.Singleton

@DisableInstallInCheck
@Module
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
