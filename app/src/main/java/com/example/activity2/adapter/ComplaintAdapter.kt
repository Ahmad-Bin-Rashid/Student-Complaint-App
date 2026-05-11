package com.example.activity2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.activity2.R
import com.example.activity2.model.Complaint

class ComplaintAdapter(private val onClick: (Complaint) -> Unit) :
    RecyclerView.Adapter<ComplaintAdapter.ViewHolder>() {

    private var complaints: List<Complaint> = emptyList()

    fun submitList(list: List<Complaint>) {
        complaints = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_complaint, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(complaints[position])
    }

    override fun getItemCount(): Int = complaints.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        private val tvStudentInfo: TextView = view.findViewById(R.id.tvStudentInfo)
        private val tvCategory: TextView = view.findViewById(R.id.tvCategory)
        private val tvPriority: TextView = view.findViewById(R.id.tvPriority)

        fun bind(complaint: Complaint) {
            tvTitle.text = complaint.title
            tvStudentInfo.text = "${complaint.studentName} (${complaint.rollNumber})"
            tvCategory.text = complaint.category
            tvPriority.text = complaint.priority

            val colorRes = when (complaint.priority.lowercase()) {
                "urgent" -> android.R.color.holo_red_dark
                "high" -> android.R.color.holo_orange_dark
                "medium" -> android.R.color.holo_blue_dark
                else -> android.R.color.holo_green_dark
            }
            tvPriority.setBackgroundColor(ContextCompat.getColor(itemView.context, colorRes))

            itemView.setOnClickListener { onClick(complaint) }
        }
    }
}