package com.heps.studentplacementapp.data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.heps.studentplacementapp.models.Student
import com.heps.studentplacementapp.navigation.ROUTE_HOME
import com.heps.studentplacementapp.navigation.ROUTE_STUDENT_DASHBOARD
import com.heps.studentplacementapp.navigation.ROUTE_STUDENT_SIGNIN
import com.heps.studentplacementapp.navigation.ROUTE_STUDENT_SIGNUP

class StudentAuthViewModel(
    var navController: NavHostController,
    var context: Context
) {
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var student = Student()

    fun signup(
        studentName: String,
        email: String,
        password: String,
        confirmPassword: String,
    ) {
//        progress.show()
        if (studentName.isBlank()||
            email.isBlank() ||
            password.isBlank() ||
            confirmPassword.isBlank()
        ) {
            Toast.makeText(
                context, "Fields cannot be blank",
                Toast.LENGTH_LONG
            ).show()
            return
        } else if (password != confirmPassword) {
            Toast.makeText(context, "Password do not match", Toast.LENGTH_LONG).show()
            return
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    val studentId = mAuth.currentUser!!.uid
                    val studentDetail = Student(
                        studentName, email, password, studentId,
                    )
                    FirebaseDatabase.getInstance().getReference()
                        .child("Students/" + studentId)
                        .setValue(studentDetail).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Registered Successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(ROUTE_STUDENT_DASHBOARD)

                            } else {
                                Toast.makeText(
                                    context,
                                    "${it.exception!!.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(ROUTE_STUDENT_SIGNUP)
                            }
                        }
                }else {
                    Toast.makeText(
                        context,
                        "${it.exception!!.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(ROUTE_STUDENT_SIGNUP)
                }
            }
        }
    }

    fun signIn(
        email: String,
        password: String
    ) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(
                    context,
                    "Successfully Logged in",
                    Toast.LENGTH_LONG
                ).show()
                navController.navigate(ROUTE_STUDENT_DASHBOARD)
                //navController.navigate(ROUTE_REGISTER) TO TAKE YOU TO A DIFFERENT PAGE
            } else {
                Toast.makeText(
                    context, "${it.exception!!.message}",
                    Toast.LENGTH_LONG
                ).show()
                navController.navigate(ROUTE_STUDENT_SIGNIN)
            }
        }
    }

    fun signOut() {
        mAuth.signOut()
        navController.navigate(ROUTE_HOME)
    }

    fun isSignedIn(): Boolean {
        return mAuth.currentUser != null
    }
    fun getStudentId(): String {
        // Implement this function to get the user ID from Firebase Auth
        return mAuth.currentUser!!.uid
    }

}
