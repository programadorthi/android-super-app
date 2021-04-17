package dev.programadorthi.shared.ui.provider

import android.content.Context
import dev.programadorthi.shared.domain.provider.SharedTextProvider

object SharedTextProviderFactory {
    operator fun invoke(context: Context): SharedTextProvider =
        SharedTextProviderImpl(context)
}
