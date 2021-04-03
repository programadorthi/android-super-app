package dev.programadorthi.shared.database.android

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dev.programadorthi.shared.database.DatabaseInjectionTags
import dev.programadorthi.shared.database.SuperApp
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object SharedDatabaseAndroidModule {
    operator fun invoke() = DI.Module(name = "shared-database-android") {
        bindSingleton<SqlDriver> {
            AndroidSqliteDriver(
                schema = SuperApp.Schema,
                context = instance(),
                name = instance(DatabaseInjectionTags.DATABASE_NAME)
            )
        }
    }
}
