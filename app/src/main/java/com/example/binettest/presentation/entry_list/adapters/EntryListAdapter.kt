package com.example.binettest.presentation.entry_list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.binettest.R
import com.example.binettest.data.entry_list.models.EntryDataListModel
import com.example.binettest.domain.entry_list.models.EntryListModel

class EntryListAdapter(
    ): RecyclerView.Adapter<EntryListAdapter.EntryHolder>() {

    private var entryList = emptyList<EntryListModel?>()
    var onItemClick: ((EntryListModel?) -> Unit)? = null

    inner class EntryHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        private val dateEntryAdded: TextView = view.findViewById(R.id.date_added)
        private val dateEntryModified: TextView = view.findViewById(R.id.date_modified)
        private val body: TextView = view.findViewById(R.id.body)
        private val cardView: CardView = view.findViewById(R.id.entry_item)

        init {
            cardView.setOnClickListener {
                onItemClick?.invoke(entryList[adapterPosition])
            }
        }

        fun bind(dateAdded: String?, dateModified: String?, bodyText: String?) {
            if (dateAdded != dateModified) {
                dateEntryModified.text = dateModified
            }
            dateEntryAdded.text = dateAdded
            body.text = bodyText?.take(200)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return EntryHolder(view)
    }

    override fun onBindViewHolder(holder: EntryHolder, position: Int) {
        holder.bind(
            entryList[position]?.dateAdded,
            entryList[position]?.dateModified,
            entryList[position]?.entryText
        )
    }

    override fun getItemCount(): Int {
        return entryList.size
    }

    fun setList(list: List<EntryListModel?>) {
        entryList = list
        notifyDataSetChanged()
    }
}