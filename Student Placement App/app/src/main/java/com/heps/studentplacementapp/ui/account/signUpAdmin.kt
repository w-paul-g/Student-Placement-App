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
import com.heps.studentplacementapp.navigation.ROUTE_ADMIN_SIGNIN
import com.heps.studentplacementapp.navigation.ROUTE_STUDENT_SIGNUP


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpAdmin(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Scaffold(
            topBar = {
                LargeTopAppBar(
                    title = {
                        Text(
                            text = """
                                Welcome Admin
                                PLease Sign Up
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
                            navController.navigate(ROUTE_STUDENT_SIGNUP)
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
        ) { innerPadding ->

            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val context = LocalContext.current
//                    var adminName by remember {
//                        mutableStateOf("")
//                    }
                    var email by remember {
                        mutableStateOf("")
                    }
                    var password by rememberSaveable {
                        mutableStateOf("")
                    }
                    var confirmPassword by rememberSaveable {
                        mutableStateOf("")
                    }


//                    //Admin Name
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(10.dp)
//                    ) {
//                        val adminNameLabel = "Admin Name"
//                        OutlinedTextField(
//                            value = adminName,
//                            onValueChange = {
//                                adminName = it
//                            },
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(10.dp),
//                            placeholder = {
//                                Text(text = adminNameLabel)
//                            },
//                            label = {
//                                Text(text = adminNameLabel)
//                            }
//                            //KeyboardType = KeyboardType.Text
//                        )
//                    }
                    //Admin Email
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {

                        OutlinedTextField(
                            value = email, onValueChange = {
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
                    //Admin Password
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {

                        var passwordVisibility by remember {
                            mutableStateOf(false)
                        }

                        val icon = if (passwordVisibility)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff

                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            placeholder = { Text(text = "Enter Password") },
                            label = { Text(text = "Enter Password") },
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
                                .padding(10.dp)
                                .fillMaxWidth()
                        )
                    }

                    //Confirm Password
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {

                        var passwordVisibility by remember {
                            mutableStateOf(false)
                        }

                        val icon = if (passwordVisibility)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff

                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = {
                                confirmPassword = it
                            },
                            placeholder = { Text(text = "Confirm Password") },
                            label = { Text(text = "Confirm Password") },
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
                                .padding(10.dp)
                                .fillMaxWidth()
                        )
                    }
                    //Sign Up Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Button(
                            onClick = {
                                val adminSignUp = AdminAuthViewModel(
                                    navController = NavHostController(context),
                                    context
                                )
                                adminSignUp.signup(
//                                    adminName.trim(),
                                    email.trim(),
                                    password.trim(),
                                    confirmPassword.trim()
                                )
                                navController.navigate(ROUTE_ADMIN_DASHBOARD)
                            },
                            modifier = Modifier
                                .padding(10.dp)
                        ) {
                            Text(text = "SIGN UP")
                        }
                    }
                    //Sign in Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Already have an account?")
                        ElevatedButton(
                            onClick = {
                                navController.navigate(ROUTE_ADMIN_SIGNIN)
                            },
                            modifier = Modifier
                                .padding(10.dp)
                        ) {
                            Text(text = "SIGN IN")
                        }
                    }


                }

            }
        }
    }
}