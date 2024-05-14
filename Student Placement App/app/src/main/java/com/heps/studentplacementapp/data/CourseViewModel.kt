package com.heps.studentplacementapp.data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.heps.studentplacementapp.models.Course
import com.heps.studentplacementapp.navigation.ROUTE_COURSE_ADD
import com.heps.studentplacementapp.navigation.ROUTE_COURSE_MANAGE


class CourseViewModel(
    var navController: NavHostController,
    var context: Context
) {
    private val database = FirebaseDatabase.getInstance()

    fun addCourse(
        courseId: String,
        institutionName: String,
        institutionType: String,
        courseLevel: String,
        courseCategory: String,
        courseName: String
    ) {
        val course = hashMapOf(
            "courseId" to courseId,
            "institutionName" to institutionName,
            "institutionType" to institutionType,
            "courseLevel" to courseLevel,
            "courseCategory" to courseCategory,
            "courseName" to courseName
        )

        database.reference
            .child("Courses")
            .child(courseId)
            .setValue(course)
            .addOnSuccessListener {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_COURSE_MANAGE)
            }
            .addOnFailureListener {
                Toast.makeText(context, "ERROR: ${it.message}", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_COURSE_ADD)
            }
    }

    fun updateCourse(
        courseId: String,
        institutionName: String,
        institutionType: String,
        courseLevel: String,
        courseCategory: String,
        courseName: String
    ) {
        val course = hashMapOf(
            "courseId" to courseId,
            "institutionName" to institutionName,
            "institutionType" to institutionType,
            "courseLevel" to courseLevel,
            "courseCategory" to courseCategory,
            "courseName" to courseName
        )

        database.reference
            .child("Courses")
            .child(courseId)
            .setValue(course)
            .addOnSuccessListener {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_COURSE_MANAGE)
            }
            .addOnFailureListener {
                Toast.makeText(context,"ERROR: ${it.message}", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_COURSE_MANAGE + "/$courseId")
            }
    }

    fun deleteCourse(
        courseId: String
    ) {
        database.reference
            .child("Courses")
            .child(courseId)
            .removeValue()
            .addOnSuccessListener {
                Toast.makeText(context, "Course deleted", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "ERROR: ${it.message}", Toast.LENGTH_SHORT).show()

            }
    }

    fun viewCourses(
        listener: (List<Course>) -> Unit,
    ) {
        val courses = mutableListOf<Course>()
        database.reference
            .child("Courses")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (courseSnapshot in snapshot.children) {
                        val course = courseSnapshot.getValue(Course::class.java)
                        course?.let { courses.add(it) }
                    }
                    listener(courses) // Notify the listener with the fetched courses
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            })
    }
}
