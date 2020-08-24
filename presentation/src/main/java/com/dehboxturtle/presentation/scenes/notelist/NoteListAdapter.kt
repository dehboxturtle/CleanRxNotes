package com.dehboxturtle.presentation.scenes.notelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dehboxturtle.domain.model.Note
import com.dehboxturtle.presentation.R

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    var data: List<Note> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount() = data.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Note) = with(itemView) {
            // TODO: Bind the data with View
        }
    }
}