package dev.programadorthi.shared.ui.network

import android.content.Context
import dev.programadorthi.shared.domain.network.ConnectionCheck

object ConnectionCheckFactory {
    operator fun invoke(
        context: Context
    ): ConnectionCheck = ConnectionCheckImpl(context)
}
