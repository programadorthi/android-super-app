package dev.programadorthi.app

import android.app.Application
import android.content.Context
import dev.programadorthi.norris.di.NorrisModule
import dev.programadorthi.norris.ui.di.NorrisUIModule
import dev.programadorthi.shared.database.DatabaseInjectionTags
import dev.programadorthi.shared.database.android.SharedDatabaseAndroidModule
import dev.programadorthi.shared.database.di.SharedDatabaseModule
import dev.programadorthi.shared.domain.di.SharedDomainModule
import dev.programadorthi.shared.network.NetworkInjectionTags
import dev.programadorthi.shared.network.di.SharedNetworkModule
import dev.programadorthi.shared.retrofit.di.SharedRetrofitModule
import dev.programadorthi.shared.ui.di.SharedUIModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bindProvider

class SuperApplication : Application(), DIAware {
    override val di: DI by DI.lazy {
        import(SharedDatabaseModule())
        import(SharedDatabaseAndroidModule())
        import(SharedDomainModule())
        import(SharedRetrofitModule())
        import(SharedNetworkModule())
        import(SharedUIModule())
        // Importing global to avoid having imports on Activities
        // Importing on activities create a couple that is hard to test using Espresso ;D
        // TODO: But should other modules know norris modules? :thinking
        import(NorrisModule())
        import(NorrisUIModule())

        bindProvider<Context> { this@SuperApplication }
        bindProvider(tag = DatabaseInjectionTags.DATABASE_NAME) { BuildConfig.DATABASE_NAME }
        bindProvider(tag = NetworkInjectionTags.BASE_URL) { BuildConfig.BASE_URL }
    }
}
