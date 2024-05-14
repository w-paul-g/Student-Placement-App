package com.heps.studentplacementapp.data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.heps.studentplacementapp.models.Admin
import com.heps.studentplacementapp.navigation.ROUTE_ADMIN_DASHBOARD
import com.heps.studentplacementapp.navigation.ROUTE_ADMIN_SIGNIN
import com.heps.studentplacementapp.navigation.ROUTE_ADMIN_SIGNUP
import com.heps.studentplacementapp.navigation.ROUTE_HOME

class AdminAuthViewModel(
    var navController: NavHostController,
    var context: Context
) {
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var admin = Admin()

    fun signup(
//        adminName: String,
        email: String,
        password: String,
        confirmPassword: String,
    ) {
//        progress.show()
        if (
//            adminName.isBlank() ||
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
                    val adminId = mAuth.currentUser!!.uid
                    val adminData = Admin(
                        email,
                        password,
                        adminId,
//                        adminName,
                    )
                    FirebaseDatabase.getInstance().getReference()
                        .child("Admins/" + adminId)
                        .setValue(adminData)
                        .addOnCompleteListener {
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
                }else {
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
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
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