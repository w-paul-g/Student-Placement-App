package com.heps.studentplacementapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.heps.studentplacementapp.R
import com.heps.studentplacementapp.navigation.ROUTE_STUDENT_SIGNIN


@Composable
fun HomeScreen (navController: NavController){

    Scaffold {
            innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ){
            Box(Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.welcome),
                    contentDescription = "Welcome",
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text("Welcome",
                        color = Color.White,
                        fontSize = 40.sp,)
                }

                Row(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(text = """
                            To HEPS App
                        """.trimIndent(),
                        color = Color.White,
                        fontSize = 20.sp,)

                }
                Row(
                    modifier = Modifier
                        .padding(10.dp),
                ) {
                    ElevatedButton(onClick = {
                        navController.navigate(ROUTE_STUDENT_SIGNIN)
                    },
                        modifier = Modifier
                            .height(64.dp)
                            .width(150.dp)
                    ) {
                        Text(text = "SIGN IN")
                    }
                }
            }
        }
    }
}



@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun WelcomePreview() {
    HomeScreen(navController = rememberNavController())
}