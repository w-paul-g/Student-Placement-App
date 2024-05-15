package com.heps.studentplacementapp.ui.screens.adminDashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.heps.studentplacementapp.R
import com.heps.studentplacementapp.navigation.ROUTE_COURSE_ADD
import com.heps.studentplacementapp.navigation.ROUTE_COURSE_MANAGE
import com.heps.studentplacementapp.navigation.ROUTE_MANAGE_SELECTED_COURSES
import com.heps.studentplacementapp.navigation.ROUTE_SIGNOUT


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboard(navController: NavController){
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "Home",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(0.96f)
                    )
                },
                actions = {
                    var moreMenu by remember {
                        mutableStateOf(false)
                    }
                    val icon = if (moreMenu) {
                        Icons.Outlined.MoreHoriz
                    } else {
                        Icons.Filled.MoreHoriz
                    }
                    IconButton(onClick = {moreMenu =! moreMenu}) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "More"
                        )
                    }
                    DropdownMenu(
                        expanded = moreMenu,
                        onDismissRequest = { moreMenu = false },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(10.dp)
                    ) {
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Logout,
                                    contentDescription
                                    = "Logout"
                                )
                            },
                            text = {
                                Text(text = "SIGN OUT")
                            },
                            onClick = {
                                navController.navigate(ROUTE_SIGNOUT)
                            }
                        )
                    }
                }
            )
        },

        ) {
            innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize(0.8f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row {
                        Card(
                            onClick = { navController.navigate(ROUTE_COURSE_ADD) },
                            modifier = Modifier
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "Add New Course",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                    Row {
                        Card(
                            onClick = { navController.navigate(ROUTE_COURSE_MANAGE) },
                            modifier = Modifier
                                .padding(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.welcome),
                                contentDescription = "",
                            )
                            Text(
                                text = "Manage Courses",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                    Row {
                        Card(onClick = {
                            navController.navigate(ROUTE_MANAGE_SELECTED_COURSES)
                        },
                            modifier = Modifier
                                .padding(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.welcome),
                                contentDescription = "",
                            )
                            Text(
                                text = "Manage Selected Courses",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            )

                        }
                    }
                }
            }
        }
    }
}
