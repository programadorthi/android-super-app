package dev.programadorthi.norris.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.programadorthi.norris.domain.model.presentation.FactViewData
import dev.programadorthi.norris.ui.databinding.ItemFactBinding

internal class FactsAdapter(
    private val shareAction: (FactViewData) -> Unit
) : RecyclerView.Adapter<FactsViewHolder>() {
    private val dataSet = mutableListOf<FactViewData>()

    override fun getItemCount(): Int = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemFactBinding.inflate(inflater)
        return FactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            shareAction.invoke(item)
        }
    }

    fun update(facts: List<FactViewData>) {
        dataSet.clear()
        dataSet.addAll(facts)
        notifyDataSetChanged()
    }
}
