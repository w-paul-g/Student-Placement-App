package com.heps.studentplacementapp.ui.screens.account

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.heps.studentplacementapp.data.AdminAuthViewModel
import com.heps.studentplacementapp.data.StudentAuthViewModel
import com.heps.studentplacementapp.navigation.ROUTE_HOME


@Composable
fun SignOutAlert(navController: NavController){
    AlertDialog(
        onDismissRequest = {
            navController.navigateUp()
        },
        dismissButton = {
            TextButton(onClick = {
                navController.navigateUp()
            }) {
                Text("CANCEL")
            }
        },
        confirmButton = {
            val context = LocalContext.current
            TextButton(onClick = {
                val studentAccount = StudentAuthViewModel(
                    navController = NavHostController(context),
                    context
                )
                val adminAccount = AdminAuthViewModel(
                    navController = NavHostController(context),
                    context
                )
                if (studentAccount.isSignedIn()){
                    studentAccount.signOut()
                }
                else if (adminAccount.isSignedIn()){
                    adminAccount.signOut()
                }
                navController.navigate(ROUTE_HOME)
            }) {
                Text(text = "SIGN OUT")
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Sharp.ExitToApp,
                contentDescription = "Logout"
            )
        },
        title = {
            Text(text = "Sign Out")
        },
        text = {
            Text(text = "Are you sure you want to sign out?")
        },


        )

}