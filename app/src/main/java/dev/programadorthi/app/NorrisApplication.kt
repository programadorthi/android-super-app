package dev.programadorthi.app

import android.app.Application
import android.content.Context
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

class NorrisApplication : Application(), DIAware {
    override val di: DI by DI.lazy {
        import(SharedDatabaseModule())
        import(SharedDatabaseAndroidModule())
        import(SharedDomainModule())
        import(SharedRetrofitModule())
        import(SharedNetworkModule())
        import(SharedUIModule())

        bindProvider<Context> { this@NorrisApplication }
        bindProvider(tag = DatabaseInjectionTags.DATABASE_NAME) { BuildConfig.DATABASE_NAME }
        bindProvider(tag = NetworkInjectionTags.BASE_URL) { BuildConfig.BASE_URL }
    }
}
