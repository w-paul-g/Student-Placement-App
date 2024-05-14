package com.heps.studentplacementapp.ui.screens.selectedcourses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.heps.studentplacementapp.data.SelectedCoursesViewModel
import com.heps.studentplacementapp.models.Course


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageSelectedCourses(
    navController: NavHostController
) {
    val context = LocalContext.current
    val selectedCoursesViewModel = SelectedCoursesViewModel(
        navController = NavHostController(context),
        context = context
    )
    var selectedCourses by remember { mutableStateOf(emptyList<Course>()) }

    // Retrieve selected courses from the database
    LaunchedEffect(Unit) {
        selectedCoursesViewModel.getSelectedCourses { courses ->
            selectedCourses = courses
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Selected Courses",
                    )
                },
                navigationIcon = {
                    TextButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIos,
                            contentDescription = "Back"
                        )
                        Text(text = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Filled.MoreHoriz,
                            contentDescription = "More"
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
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Display selected courses
                        selectedCourses.forEach { course ->
                            SelectedCourseCard(
                                navController,
                                selectedCoursesViewModel,
                                course
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SelectedCourseCard(
    navController: NavHostController,
    selectedCoursesViewModel: SelectedCoursesViewModel,
    course: Course
){
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(onClick = {
                    selectedCoursesViewModel.removeSelectedCourse(courseId = String())
                }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Delete Selected Course"
                    )
                }
            }
            Text(text = """
                                            Course ID: ${course.courseId}
                                            Institution Name: ${course.institutionName}
                                            Institution Type: ${course.institutionType}
                                            Course Level: ${course.courseLevel}
                                            Course Category: ${course.courseCategory}
                                            Course Name: ${course.courseName}
                                        """.trimIndent())
            // Add more course details as needed

        }
    }
}