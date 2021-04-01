package dev.programadorthi.norris.ui

import android.app.Application
import dev.programadorthi.norris.di.NorrisModule
import dev.programadorthi.norris.ui.di.NorrisUIModule
import dev.programadorthi.shared.database.di.SharedDatabaseModule
import dev.programadorthi.shared.network.di.SharedNetworkModule
import dev.programadorthi.shared.ui.di.SharedUIModule
import org.kodein.di.DI
import org.kodein.di.DIAware

class NorrisApplication : Application(), DIAware {
    override val di: DI by DI.lazy {
        import(SharedDatabaseModule())
        import(SharedNetworkModule())
        import(SharedUIModule())
        import(NorrisModule())
        import(NorrisUIModule())
        /*bindSingleton {
            RetrofitBuilder(
                baseUrl = "",
                httpClient = okhttp3.OkHttpClient(),
                json = instance()
            )
        }*/
    }
}
