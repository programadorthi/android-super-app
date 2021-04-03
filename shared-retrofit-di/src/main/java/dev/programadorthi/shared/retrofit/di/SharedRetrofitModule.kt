package dev.programadorthi.shared.retrofit.di

import dev.programadorthi.shared.network.NetworkInjectionTags
import dev.programadorthi.shared.retrofit.RetrofitBuilder
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object SharedRetrofitModule {
    operator fun invoke() = DI.Module("shared-retrofit") {
        bindSingleton {
            RetrofitBuilder(
                baseUrl = instance(NetworkInjectionTags.BASE_URL),
                json = instance()
            )
        }
    }
}
