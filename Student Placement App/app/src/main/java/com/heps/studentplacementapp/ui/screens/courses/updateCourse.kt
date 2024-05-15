package com.heps.studentplacementapp.ui.screens.courses

import android.widget.Toast
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.heps.studentplacementapp.data.CourseViewModel
import com.heps.studentplacementapp.models.Course


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateCourse(
    navController: NavHostController,
    courseId: String
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ){
        val context = LocalContext.current
        val courseViewModel = CourseViewModel(
            navController = NavHostController(context),
            context = context
        )
        var courseIdState by remember {
            mutableStateOf("")
        }
        var institutionNameState by remember {
            mutableStateOf("")
        }
        var institutionTypeState by remember {
            mutableStateOf("")
        }
        var courseLevelState by remember {
            mutableStateOf("")
        }
        var courseCategoryState by remember {
            mutableStateOf("")
        }
        var courseNameState by remember {
            mutableStateOf("")
        }
        var currentDataRef = FirebaseDatabase.getInstance().getReference()
            .child("Courses/$courseId")
        currentDataRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val course = snapshot.getValue(Course::class.java)
                courseIdState = course!!.courseId
                institutionNameState = course.institutionName
                institutionTypeState = course.institutionType
                courseLevelState = course.courseLevel
                courseCategoryState = course.courseCategory
                courseNameState = course.courseName
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    context,
                    error.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
//        LaunchedEffect(courseId) {
//            courseViewModel.updateCourse(
//                courseIdState = course.courseId,
//                institutionNameState = course.institutionName,
//                institutionTypeState = course.institutionType,
//                courseLevelState = course.courseLevel,
//                courseCategoryState = course.courseCategory,
//                courseNameState = course.courseName
//            )
//        }
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Add Course")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = ""
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            courseViewModel.updateCourse(
                                courseIdState.trim(),
                                institutionNameState.trim(),
                                institutionTypeState.trim(),
                                courseLevelState.trim(),
                                courseCategoryState.trim(),
                                courseNameState.trim()
                            )
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = ""
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
                        .fillMaxSize(),
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
                            value = courseIdState,
                            onValueChange = {courseIdState = it },
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
                            value = institutionNameState,
                            onValueChange = {institutionNameState = it },
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
                            value = institutionTypeState,
                            onValueChange = {institutionTypeState = it },
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
                            value = courseLevelState,
                            onValueChange = {courseLevelState = it },
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
                            value = courseCategoryState,
                            onValueChange = {courseCategoryState = it },
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
                            value = courseNameState,
                            onValueChange = {courseNameState = it },
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