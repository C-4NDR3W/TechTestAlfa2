package com.example.techtestalfa2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewStudent)
        recyclerView.layoutManager = LinearLayoutManager(this)

        studentAdapter = StudentAdapter(studentList)
        recyclerView.adapter = studentAdapter

        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        val db = FirebaseFirestore.getInstance()
        db.collection("students")
            .get()
            .addOnSuccessListener { documents ->
                studentList.clear()
                for (document in documents) {
                    val student = document.toObject(Student::class.java)
                    studentList.add(student)
                }
                studentAdapter = StudentAdapter(studentList)
                recyclerView.adapter = studentAdapter
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore Error", exception.message.toString())
            }
    }
}