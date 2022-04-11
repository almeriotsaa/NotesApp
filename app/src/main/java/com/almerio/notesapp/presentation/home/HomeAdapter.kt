package com.almerio.notesapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.almerio.notesapp.data.local.entity.Notes
import com.almerio.notesapp.databinding.RowItemNotesBinding
import com.almerio.notesapp.presentation.DiffCallback

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    var listNotes = ArrayList<Notes>()

    inner class MyViewHolder(val binding: RowItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listNotes.get(position)
        holder.binding.apply {
            mNotes = data
            executePendingBindings()

        }
    }

    override fun getItemCount(): Int = listNotes.size

    fun setData(data: List<Notes>){
        if (data == null) return
        val diffCallback = DiffCallback(listNotes, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listNotes.clear()
        listNotes.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }
}