package com.example.activity2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.activity2.model.Complaint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ComplaintDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaint_detail)

        val complaint = intent.getSerializableExtra("complaint") as? Complaint

        if (complaint == null) {
            finish()
            return
        }

        findViewById<TextView>(R.id.tvDetailTitle).text = complaint.title
        findViewById<TextView>(R.id.tvDetailStatus).text = "Status: ${complaint.status}"
        findViewById<TextView>(R.id.tvDetailStudentName).text = "Name: ${complaint.studentName}"
        findViewById<TextView>(R.id.tvDetailRollNumber).text = "Roll No: ${complaint.rollNumber}"
        findViewById<TextView>(R.id.tvDetailCategory).text = complaint.category
        findViewById<TextView>(R.id.tvDetailPriority).text = complaint.priority
        findViewById<TextView>(R.id.tvDetailDescription).text = complaint.description

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val date = Date(complaint.timestamp)
        findViewById<TextView>(R.id.tvDetailDate).text = "Submitted on: ${sdf.format(date)}"

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}