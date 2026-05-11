package com.example.activity2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.activity2.model.Complaint
import com.example.activity2.repository.ComplaintRepository
import kotlinx.coroutines.launch

class AddComplaintActivity : AppCompatActivity() {

    private lateinit var etStudentName: EditText
    private lateinit var etRollNumber: EditText
    private lateinit var etTitle: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var spinnerPriority: Spinner
    private lateinit var etDescription: EditText
    private lateinit var btnSubmit: Button

    private val repository = ComplaintRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_complaint)

        initViews()

        btnSubmit.setOnClickListener {
            submitComplaint()
        }
    }

    private fun initViews() {
        etStudentName = findViewById(R.id.etStudentName)
        etRollNumber = findViewById(R.id.etRollNumber)
        etTitle = findViewById(R.id.etTitle)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        spinnerPriority = findViewById(R.id.spinnerPriority)
        etDescription = findViewById(R.id.etDescription)
        btnSubmit = findViewById(R.id.btnSubmit)
    }

    private fun submitComplaint() {
        val name = etStudentName.text.toString().trim()
        val roll = etRollNumber.text.toString().trim()
        val title = etTitle.text.toString().trim()
        val category = spinnerCategory.selectedItem.toString()
        val priority = spinnerPriority.selectedItem.toString()
        val description = etDescription.text.toString().trim()

        if (name.isEmpty() || roll.isEmpty() || title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val complaint = Complaint(
            studentName = name,
            rollNumber = roll,
            title = title,
            category = category,
            priority = priority,
            description = description
        )

        lifecycleScope.launch {
            btnSubmit.isEnabled = false
            val result = repository.addComplaint(complaint)
            if (result.isSuccess) {
                Toast.makeText(this@AddComplaintActivity, "Complaint submitted successfully", Toast.LENGTH_SHORT).show()
                clearFields()
                finish()
            } else {
                Toast.makeText(this@AddComplaintActivity, "Error: ${result.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
                btnSubmit.isEnabled = true
            }
        }
    }

    private fun clearFields() {
        etStudentName.text.clear()
        etRollNumber.text.clear()
        etTitle.text.clear()
        etDescription.text.clear()
        spinnerCategory.setSelection(0)
        spinnerPriority.setSelection(0)
    }
}