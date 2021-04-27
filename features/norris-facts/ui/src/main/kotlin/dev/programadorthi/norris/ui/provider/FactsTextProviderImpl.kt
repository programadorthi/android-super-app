package dev.programadorthi.norris.ui.provider

import android.content.Context
import dev.programadorthi.norris.domain.provider.FactsTextProvider
import dev.programadorthi.norris.ui.R
import dev.programadorthi.shared.domain.provider.SharedTextProvider

internal class FactsTextProviderImpl(
    private val context: Context,
    private val sharedTextProvider: SharedTextProvider
) : FactsTextProvider {
    override fun emptySearchTerm(): String =
        context.getString(R.string.activity_facts_empty_search_term)

    override fun generalFailure(): String =
        sharedTextProvider.somethingWrong()

    override fun withoutCategory(): String =
        context.getString(R.string.item_fact_view_holder_uncategorized_label)
}
