package com.heps.studentplacementapp.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.heps.studentplacementapp.models.Student
import com.heps.studentplacementapp.navigation.ROUTE_ADMIN_DASHBOARD
import com.heps.studentplacementapp.navigation.ROUTE_ADMIN_SIGNIN
import com.heps.studentplacementapp.navigation.ROUTE_ADMIN_SIGNUP
import com.heps.studentplacementapp.navigation.ROUTE_HOME
import com.heps.studentplacementapp.navigation.ROUTE_STUDENT_DASHBOARD
import com.heps.studentplacementapp.navigation.ROUTE_STUDENT_SIGNIN
import com.heps.studentplacementapp.navigation.ROUTE_STUDENT_SIGNUP


class AdminAuthViewModel(
    var navController: NavHostController,
    var context: Context
) {
    var mAuth: FirebaseAuth
    val progress: ProgressDialog

    init {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("PLease Wait a moment.....")
    }

    fun signup(
        email: String,
        password: String,
        confirmPassword: String,
    ) {
//        progress.show()
        if (
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
            progress.show()
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Registered Successfully",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(ROUTE_ADMIN_DASHBOARD)
                } else {
                    Toast.makeText(
                        context,
                        "${it.exception!!.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(ROUTE_ADMIN_SIGNUP)
                }

            }
        }
    }


    fun signIn(
        email: String,
        password: String
    ) {
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(
                context, "Fields cannot be blank",
                Toast.LENGTH_LONG
            ).show()
            return
        }else{
            progress.show()
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Successfully Logged in",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(ROUTE_ADMIN_DASHBOARD)
                    //navController.navigate(ROUTE_REGISTER) TO TAKE YOU TO A DIFFERENT PAGE
                } else {
                    Toast.makeText(
                        context, "${it.exception!!.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(ROUTE_ADMIN_SIGNIN)
                }
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
    fun getAdminId(): String {
        // Implement this function to get the admin ID from Firebase Auth
        return mAuth.currentUser!!.uid
    }

}




class StudentAuthViewModel(
    var navController: NavHostController,
    var context: Context
) {
    var mAuth: FirebaseAuth
    val progress: ProgressDialog

    init {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("PLease Wait a moment.....")
    }
    var student = Student()

    fun signup(
//        studentName: String,
        email: String,
        password: String,
        confirmPassword: String,
    ) {
//        progress.show()
        if (
//            studentName.isBlank()||
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
            val studentId = mAuth.currentUser!!.uid
            val studentDetail = Student(
//                        studentName,
                email, password, studentId,
            )
            FirebaseDatabase.getInstance().getReference()
                .child("Students/$studentId")
                .setValue(studentDetail).addOnCompleteListener {
                    if (it.isSuccessful) {
                        progress.show()
                        mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener {
                                progress.dismiss()
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
                    } else {
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
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(
                context, "Fields cannot be blank",
                Toast.LENGTH_LONG
            ).show()
            return
        }
        else{
            progress.show()
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                progress.dismiss()
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
