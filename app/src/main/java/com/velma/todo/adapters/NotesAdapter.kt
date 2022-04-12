package com.velma.todo.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.velma.todo.R
import com.velma.todo.model.Note

class NotesAdapter(private val context: Context, private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    private val notes: MutableList<Note> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.note_view_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //hey, no, yes
        //val note = notes[position]
        holder.bind(notes[position])
        //holder.itemView.setOnClickListener()
        holder.itemView.setOnClickListener {
            Log.i("adapter", "onBindViewHolder: noteCardView")
        }
    }
    override fun getItemCount(): Int {

        return notes.size
    }

    fun addNotes() {
        val note = Note("The epic day", "24/3/2022", "Hello World. I had a good day.", "24/4/2022")
        notes.add(note)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val noteTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val noteDate = itemView.findViewById<TextView>(R.id.tvDate)
        private val noteCard = itemView.findViewById<MaterialCardView>(R.id.noteCardView)

        fun bind(note: Note) {
            noteTitle.text = note.title
            noteDate.text = note.timeStamp
            noteCard.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val note = notes[adapterPosition]
            if (view != null) {
                itemClickListener.itemClicked(view, note)
            }
            /*val card = noteCard
            itemClickListener.itemClicked(card, note)*/
        }
    }
    interface ItemClickListener {
        fun itemClicked(view: View, note: Note)
        /*fun itemClicked(cardView: MaterialCardView)*/
    }
}