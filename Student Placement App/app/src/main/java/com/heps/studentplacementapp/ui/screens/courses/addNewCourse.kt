package com.heps.studentplacementapp.ui.screens.courses

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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.heps.studentplacementapp.data.CourseViewModel
import com.heps.studentplacementapp.navigation.ROUTE_COURSE_MANAGE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewCourse(
    navController: NavController
){
    Box (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        val context = LocalContext.current
        var courseId by remember {
            mutableStateOf("")
        }
        var institutionName by remember {
            mutableStateOf("")
        }
        var institutionType by remember {
            mutableStateOf("")
        }
        var courseLevel by remember {
            mutableStateOf("")
        }
        var courseCategory by remember {
            mutableStateOf("")
        }
        var courseName by remember {
            mutableStateOf("")
        }
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Add Course")
                    },
                    navigationIcon = {
                        TextButton(
                            onClick = { navController.navigateUp() }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Cancel",
                                tint = colorScheme.onSurface
                            )
                            Text(
                                text = "Cancel",
                                color = colorScheme.onSurface
                            )
                        }
                    },
                    actions = {
                        TextButton(
                            onClick = {
                                val courseDetail = CourseViewModel(
                                    navController = NavHostController(context),
                                    context
                                )
                                courseDetail.addCourse(
                                    courseId.trim(),
                                    institutionName.trim(),
                                    institutionType.trim(),
                                    courseLevel.trim(),
                                    courseCategory.trim(),
                                    courseName.trim()
                                )
                                navController.navigate(ROUTE_COURSE_MANAGE)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Save",
                                tint = colorScheme.onSurface
                            )
                            Text(
                                text = "Save",
                                color = colorScheme.onSurface
                            )
                        }
                    }
                )
            },
        ) {
                innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    //Course ID
                    Row (
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ){
                        OutlinedTextField(
                            value = courseId,
                            onValueChange = {courseId = it },
                            label = {
                                Text(text = "Course ID")
                            },
                            placeholder = {
                                Text(text = "Course ID")
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.96f)
                                .padding(10.dp)
                        )
                    }
                    //Institution Name
                    Row (
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ){

                        val textFieldName = "Institution Name"
                        OutlinedTextField(
                            value = institutionName,
                            onValueChange = {institutionName = it },
                            label = {
                                Text(text = textFieldName)
                            },
                            placeholder = {
                                Text(text = textFieldName)
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.96f)
                                .padding(10.dp)
                        )
                    }
                    Row (
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ){
                        val textFieldName = "Institution Type"
                        OutlinedTextField(
                            value = institutionType,
                            onValueChange = {institutionType = it },
                            label = {
                                Text(text = textFieldName)
                            },
                            placeholder = {
                                Text(text = textFieldName)
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.96f)
                                .padding(10.dp)
                        )
                    }
                    Row (
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ){
                        OutlinedTextField(
                            value = courseLevel,
                            onValueChange = {courseLevel = it },
                            label = {
                                Text(text = "Course Level")
                            },
                            placeholder = {
                                Text(text = "Course Level")
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.96f)
                                .padding(10.dp)
                        )
                    }

                    //
                    Row (
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ){
                        OutlinedTextField(
                            value = courseCategory,
                            onValueChange = {courseCategory = it },
                            label = {
                                Text(text = "Course Category")
                            },
                            placeholder = {
                                Text(text = "Course Category")
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.96f)
                                .padding(10.dp)
                        )
                    }
                    //Course Name
                    Row (
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ){
                        OutlinedTextField(
                            value = courseName,
                            onValueChange = {courseName = it },
                            label = {
                                Text(text = "Course Name")
                            },
                            placeholder = {
                                Text(text = "Course Name")
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.96f)
                                .padding(10.dp)
                        )
                    }
                }
            }
        }

    }
}