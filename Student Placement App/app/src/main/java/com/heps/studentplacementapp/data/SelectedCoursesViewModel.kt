package com.heps.studentplacementapp.data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.heps.studentplacementapp.models.Course

class SelectedCoursesViewModel(
    var navController: NavHostController,
    private val context: Context
) {
    private val studentAuthViewModel =  StudentAuthViewModel(
        navController, context
    )
    private val database = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private var user: FirebaseUser? = null
    private val studentId = studentAuthViewModel.getStudentId()
    init {
        // Listen for authentication state changes
        auth.addAuthStateListener { firebaseAuth ->
            user = firebaseAuth.currentUser
        }
    }

    // Function to add selected courses to Firebase Realtime Database
    fun addSelectedCourses(selectedCourses: List<Course>) {
        if (studentId.isBlank()) {
            Toast.makeText(context, "User not authenticated", Toast.LENGTH_SHORT).show()
            return
        }

        val userCoursesRef = database.reference
            .child("Students")
            .child(studentId)
            .child("SelectedCourses")

        // Clear existing data before adding new data (optional)
        userCoursesRef.removeValue().addOnSuccessListener {
            // Add selected courses to Firebase Realtime Database
            selectedCourses.forEach { course ->
                userCoursesRef.child(course.courseId).setValue(course)
            }
            Toast.makeText(context, "Selected courses saved", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { exception ->
            Toast.makeText(context, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to retrieve selected courses from Firebase Realtime Database
    fun getSelectedCourses(listener: (List<Course>) -> Unit) {
        if (studentId.isBlank()) {
            Toast.makeText(context, "User not authenticated", Toast.LENGTH_SHORT).show()
            return
        }
        val userCoursesRef = database.reference
            .child("Students")
            .child(studentId)
            .child("SelectedCourses")

        userCoursesRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val courses = mutableListOf<Course>()
                for (courseSnapshot in snapshot.children) {
                    val course = courseSnapshot.getValue(Course::class.java)
                    course?.let { courses.add(it) }
                }
                listener(courses)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Function to remove a selected course from Firebase Realtime Database
    fun removeSelectedCourse(courseId: String) {
        if (studentId.isBlank()) {
            Toast.makeText(context, "User not authenticated", Toast.LENGTH_SHORT).show()
            return
        }

        val userCourseRef = database.reference
            .child("Students")
            .child(studentId)
            .child("SelectedCourses")
            .child(courseId)

        userCourseRef.removeValue().addOnSuccessListener {
            Toast.makeText(context, "Course removed", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { exception ->
            Toast.makeText(context, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
        }
    }
}