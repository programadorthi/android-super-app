package dev.programadorthi.shared.ui.resource

import android.content.Context
import dev.programadorthi.shared.domain.resource.StringProvider

object StringProviderFactory {
    operator fun invoke(
        context: Context
    ): StringProvider = StringProviderImpl(context)
}
