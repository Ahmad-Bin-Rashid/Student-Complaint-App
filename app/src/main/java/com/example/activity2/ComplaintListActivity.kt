package com.example.activity2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.activity2.adapter.ComplaintAdapter
import com.example.activity2.model.Complaint
import com.example.activity2.repository.ComplaintRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ComplaintListActivity : AppCompatActivity() {

    private lateinit var rvComplaints: RecyclerView
    private lateinit var tvEmpty: TextView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var adapter: ComplaintAdapter
    private val repository = ComplaintRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaint_list)

        rvComplaints = findViewById(R.id.rvComplaints)
        tvEmpty = findViewById(R.id.tvEmpty)
        fabAdd = findViewById(R.id.fabAdd)

        setupRecyclerView()

        fabAdd.setOnClickListener {
            startActivity(Intent(this, AddComplaintActivity::class.java))
        }

        fetchComplaints()
    }

    private fun setupRecyclerView() {
        adapter = ComplaintAdapter { complaint ->
            val intent = Intent(this, ComplaintDetailActivity::class.java)
            intent.putExtra("complaint", complaint)
            startActivity(intent)
        }
        rvComplaints.adapter = adapter
    }

    private fun fetchComplaints() {
        repository.getComplaints(
            onSuccess = { complaints ->
                if (complaints.isEmpty()) {
                    tvEmpty.visibility = View.VISIBLE
                    rvComplaints.visibility = View.GONE
                } else {
                    tvEmpty.visibility = View.GONE
                    rvComplaints.visibility = View.VISIBLE
                    adapter.submitList(complaints)
                }
            },
            onFailure = { e ->
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        )
    }
}