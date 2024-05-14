package com.heps.studentplacementapp.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.heps.studentplacementapp.R
import com.heps.studentplacementapp.navigation.ROUTE_SELECT_COURSE
import com.heps.studentplacementapp.navigation.ROUTE_SIGNOUT
import com.heps.studentplacementapp.navigation.ROUTE_VIEW_SELECTED_COURSES


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDashboard(navController: NavHostController){
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "Home",
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
        }
    ) {
            innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(0.8f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                            ,
                            onClick = { navController.navigate(ROUTE_SELECT_COURSE) }) {
                            Image(painter = painterResource(id = R.drawable.welcome), contentDescription = "Select Courses")
                            Text(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                                ,
                                text = "Select Course",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Card(onClick = { navController.navigate(ROUTE_VIEW_SELECTED_COURSES) },
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()) {
                            Text("View Selected Courses",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun StudentDashboardPreview(){
    StudentDashboard(navController = rememberNavController())

}