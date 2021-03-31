package dev.programadorthi.norris.ui.adapter

import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import dev.programadorthi.norris.ui.databinding.ItemFactBinding
import dev.programadorthi.norris.ui.model.FactViewData

internal class FactsViewHolder(
    private val viewBinding: ItemFactBinding
) : RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(fact: FactViewData) {
        with(viewBinding) {
            TextViewCompat.setTextAppearance(itemFactContentTextView, fact.style)
            itemFactContentTextView.text = fact.value
            itemFactCategoryTextView.text = fact.category
        }
    }
}
