package com.heps.studentplacementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.heps.studentplacementapp.data.AdminAuthViewModel
import com.heps.studentplacementapp.data.StudentAuthViewModel
import com.heps.studentplacementapp.navigation.ROUTE_ADMIN_DASHBOARD
import com.heps.studentplacementapp.navigation.ROUTE_HOME
import com.heps.studentplacementapp.navigation.RouteNavHost
import com.heps.studentplacementapp.ui.theme.StudentPlacementAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentPlacementAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    var context = this
                    var studentAccount = StudentAuthViewModel(
                        navController = NavHostController(context),
                        context
                    )
                    var adminAccount = AdminAuthViewModel(
                        navController = NavHostController(context),
                        context
                    )
                    RouteNavHost(
                        startDestination =
                        if (adminAccount.isSignedIn()){
                            ROUTE_ADMIN_DASHBOARD
                        }
                        else{
                            ROUTE_HOME
                        }
                    )
                }
            }
        }
    }
}
