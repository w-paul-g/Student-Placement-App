package com.heps.studentplacementapp.ui.screens.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.heps.studentplacementapp.data.AdminAuthViewModel
import com.heps.studentplacementapp.navigation.ROUTE_ADMIN_DASHBOARD
import com.heps.studentplacementapp.navigation.ROUTE_ADMIN_SIGNUP
import com.heps.studentplacementapp.navigation.ROUTE_STUDENT_SIGNIN


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInAdmin(navController: NavController){
    Box (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ){
        Scaffold(
            topBar = {
                LargeTopAppBar(
                    title = {
                        Text(text = """
                    Welcome Back Admin
                    PLease Sign In
                """.trimIndent(),
                            color = colorScheme.onBackground,
                            fontWeight = FontWeight.ExtraBold
                        )
                    },
                    navigationIcon = {
                        TextButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBackIosNew,
                                contentDescription = "Back to Previous Page"
                            )
                            Text(
                                text = "Back",
                                fontWeight = FontWeight.Bold,
                                color = colorScheme.onBackground
                            )
                        }
                    },
                    actions = {
                        TextButton(onClick = {
                            navController.navigate(ROUTE_STUDENT_SIGNIN)
                        }) {
                            Text(
                                text = "Student",
                                color = colorScheme.onBackground,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                )
            }
        ) {
                innerPadding ->

            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val context = LocalContext.current
                    var email by remember {
                        mutableStateOf("")
                    }
                    var password by rememberSaveable {
                        mutableStateOf("")
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {

                        OutlinedTextField(value = email, onValueChange = {
                            email = it
                        },
                            keyboardActions = KeyboardActions(),
                            placeholder = {
                                Text(text = "Email")
                            },
                            label = {
                                Text(text = "Email")
                            },
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        )
                    }
                    //Password
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {

                        var passwordVisibility by remember { mutableStateOf(false) }

                        val icon = if (passwordVisibility) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        }

                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            placeholder = { Text(text = "Password") },
                            label = { Text(text = "Password") },
                            trailingIcon = {
                                IconButton(onClick = {
                                    passwordVisibility = !passwordVisibility
                                }) {
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = "Visibility Icon"
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            visualTransformation = if (passwordVisibility) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                    }
                    //Sign In Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = {
                            val adminSignIn = AdminAuthViewModel(
                                navController = NavHostController(context),
                                context
                            )
                            adminSignIn.signIn(
                                email.trim(),
                                password.trim()
                            )
                            navController.navigate(ROUTE_ADMIN_DASHBOARD)
                        },
                            modifier = Modifier
                                .padding(10.dp)) {
                            Text(text = "SIGN IN")
                        }
                    }
                    //Sign Up Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Don't have an account?")
                        ElevatedButton(onClick = { navController.navigate(ROUTE_ADMIN_SIGNUP) },
                            modifier = Modifier
                                .padding(10.dp)) {
                            Text(text = "SIGN UP")
                        }
                    }


                }

            }
        }
    }
}