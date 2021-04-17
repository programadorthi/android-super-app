package dev.programadorthi.shared.ui.provider

import android.content.Context
import dev.programadorthi.shared.domain.provider.SharedTextProvider
import dev.programadorthi.shared.ui.R

internal class SharedTextProviderImpl(
    private val context: Context
) : SharedTextProvider {
    override fun noInternetConnection(): String =
        context.getString(R.string.no_internet_connection)

    override fun somethingWrong(): String =
        context.getString(R.string.something_wrong)
}
