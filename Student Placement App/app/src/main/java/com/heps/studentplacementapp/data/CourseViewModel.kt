package com.heps.studentplacementapp.data

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

//
//class CourseViewModel(
//    var navController: NavHostController,
//    var context: Context
//) {
//    private val database = FirebaseDatabase.getInstance()
//
//    fun addCourse(
//        courseId: String,
//        institutionName: String,
//        institutionType: String,
//        courseLevel: String,
//        courseCategory: String,
//        courseName: String
//    ) {
//        val course = hashMapOf(
//            "courseId" to courseId,
//            "institutionName" to institutionName,
//            "institutionType" to institutionType,
//            "courseLevel" to courseLevel,
//            "courseCategory" to courseCategory,
//            "courseName" to courseName
//        )
//
//        database.reference
//            .child("Courses")
//            .child(courseId)
//            .setValue(course)
//            .addOnSuccessListener {
//                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
//                navController.navigate(ROUTE_COURSE_MANAGE)
//            }
//            .addOnFailureListener {
//                Toast.makeText(context, "ERROR: ${it.message}", Toast.LENGTH_SHORT).show()
//                navController.navigate(ROUTE_COURSE_ADD)
//            }
//    }
//
//    fun updateCourse(
//        courseId: String,
//        institutionName: String,
//        institutionType: String,
//        courseLevel: String,
//        courseCategory: String,
//        courseName: String
//    ) {
//        val course = hashMapOf(
//            "courseId" to courseId,
//            "institutionName" to institutionName,
//            "institutionType" to institutionType,
//            "courseLevel" to courseLevel,
//            "courseCategory" to courseCategory,
//            "courseName" to courseName
//        )
//
//        database.reference
//            .child("Courses")
//            .child(courseId)
//            .setValue(course)
//            .addOnSuccessListener {
//                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
//                navController.navigate(ROUTE_COURSE_MANAGE)
//            }
//            .addOnFailureListener {
//                Toast.makeText(context,"ERROR: ${it.message}", Toast.LENGTH_SHORT).show()
//                navController.navigate(ROUTE_COURSE_MANAGE + "/$courseId")
//            }
//    }
//
//    fun deleteCourse(
//        courseId: String
//    ) {
//        database.reference
//            .child("Courses")
//            .child(courseId)
//            .removeValue()
//            .addOnSuccessListener {
//                Toast.makeText(context, "Course deleted", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener {
//                Toast.makeText(context, "ERROR: ${it.message}", Toast.LENGTH_SHORT).show()
//
//            }
//    }
//
//    fun viewCourses(
//        listener: (List<Course>) -> Unit,
//    ) {
//        val courses = mutableListOf<Course>()
//        database.reference
//            .child("Courses")
//            .addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    for (courseSnapshot in snapshot.children) {
//                        val course = courseSnapshot.getValue(Course::class.java)
//                        course?.let { courses.add(it) }
//                    }
//                    listener(courses) // Notify the listener with the fetched courses
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
//                }
//            })
//    }
//}



class CourseViewModel(var navController: NavHostController, var context: Context) {


    fun saveCourse(
        courseId: String,
        institutionName: String,
        institutionType: String,
        courseLevel: String,
        courseCategory: String,
        courseName: String
    ) {
        var courseData = Course(courseId, institutionName, institutionType, courseLevel, courseCategory, courseName)
        var courseRef = FirebaseDatabase.getInstance().getReference()
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

    fun viewCourses(
        course: MutableState<Course>,
        courses: SnapshotStateList<Course>
    ): SnapshotStateList<Course> {
        val ref = FirebaseDatabase.getInstance().getReference().child("Products")
//        val course = mutableListOf<Course>()
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
        courseIdState: String,
        institutionNameState: String,
        institutionTypeState: String,
        courseLevelState: String,
        courseCategoryState: String,
        courseNameState: String
    ) {
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Courses/$courseIdState")
        var updateData = Course(courseIdState, institutionNameState, institutionTypeState, courseLevelState, courseCategoryState, courseNameState)
        updateRef.setValue(updateData).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}