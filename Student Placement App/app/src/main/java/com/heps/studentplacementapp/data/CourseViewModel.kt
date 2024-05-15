package com.heps.studentplacementapp.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.heps.studentplacementapp.models.Course
class CourseViewModel(
    var navController: NavHostController,
    var context: Context
) {
    var progress: ProgressDialog

    init {
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait a moment...")
    }
    fun saveCourse(
        courseId: String,
        institutionName: String,
        institutionType: String,
        courseLevel: String,
        courseCategory: String,
        courseName: String
    ) {
        if (
            courseId.isEmpty() ||
            institutionName.isEmpty() ||
            institutionType.isEmpty() ||
            courseLevel.isEmpty() ||
            courseCategory.isEmpty() ||
            courseName.isEmpty()
            ) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        } else{
            val courseData = Course(
                courseId,
                institutionName,
                institutionType,
                courseLevel,
                courseCategory,
                courseName
            )
            val courseRef = FirebaseDatabase.getInstance().getReference()
                .child("Courses/$courseId")
            courseRef.setValue(courseData).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    fun viewCourses(
        course: MutableState<Course>,
        courses: SnapshotStateList<Course>
    ): SnapshotStateList<Course> {
        val ref = FirebaseDatabase.getInstance().getReference().child("Courses")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                courses.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Course::class.java)
                   course.value = value!!
                    courses.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return courses
    }



    fun deleteCourse(courseId: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("Courses/$courseId")
        delRef.removeValue().addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Product deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
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
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Courses/$courseId")
        var updateData = Course(
            courseId,
            institutionName,
            institutionType,
            courseLevel,
            courseCategory,
            courseName
        )
        updateRef.setValue(updateData).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}