package com.heps.studentplacementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.heps.studentplacementapp.navigation.RouteNavHost
import com.heps.studentplacementapp.ui.theme.StudentPlacementAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentPlacementAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RouteNavHost(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}
