package dev.programadorthi.shared.ui.resource

import android.content.Context

internal class StringProviderImpl(
    private val context: Context
) : StringProvider {
    override fun getString(stringId: Int): String =
        context.getString(stringId)

    override fun getString(stringId: Int, vararg args: Any): String =
        context.getString(stringId, args)
}
