package dev.programadorthi.shared.ui.resource

import android.content.Context
import androidx.annotation.StringRes

interface StringProvider {
    fun getString(@StringRes stringId: Int): String
    fun getString(@StringRes stringId: Int, vararg args: Any): String

    companion object Instance {
        operator fun invoke(
            context: Context
        ): StringProvider = StringProviderImpl(context)
    }
}
