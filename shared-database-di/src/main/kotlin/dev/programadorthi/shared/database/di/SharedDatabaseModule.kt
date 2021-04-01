package dev.programadorthi.shared.database.di

import com.squareup.sqldelight.db.SqlDriver
import dev.programadorthi.shared.database.SuperApp
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object SharedDatabaseModule {
    operator fun invoke() = DI.Module(name = "shared-database") {
        bindSingleton {
            val driver = instance<SqlDriver>()
            SuperApp(driver)
        }

        bindSingleton {
            instance<SuperApp>().norrisQueries
        }
    }
}
