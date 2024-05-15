package com.heps.studentplacementapp.ui.screens.courses

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.heps.studentplacementapp.data.CourseViewModel
import com.heps.studentplacementapp.models.Course
import com.heps.studentplacementapp.navigation.ROUTE_COURSE_ADD
import com.heps.studentplacementapp.navigation.ROUTE_COURSE_UPDATE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageCourses(navController: NavHostController,
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){

        val context = LocalContext.current

        val courseViewModel =  CourseViewModel(
            navController = NavHostController(context),
            context = context
        )
        val emptyCourseState = remember {
            mutableStateOf(
                Course(
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
                )
            )
        }
        val emptyCoursesListState = remember {
            mutableStateListOf<Course>()
        }
        val courses = courseViewModel.viewCourses(
            emptyCourseState,
            emptyCoursesListState
        )


        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Courses")
                    },
                    navigationIcon = {
                        TextButton(onClick = {navController.navigateUp()}) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBackIos,
                                contentDescription = "Back",
                                tint = colorScheme.onSurface
                            )
                            Text(text = "Back",
                                fontWeight = FontWeight.Bold,
                                color = colorScheme.onSurface
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                navController.navigate(ROUTE_COURSE_ADD)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add New Courses"
                            )
                        }
                    }
                )
            }
        ) {
                innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ){
                Column {
                    LazyColumn {
                        items(courses) {
                            CourseCard(course = Course(), navController, courseViewModel)
                        }
                    }
//                    listOfCourses.forEach { course ->
//                        CourseCard(
//                            course, navController = NavHostController(context),
//                            courseViewModel = CourseViewModel(
//                                NavHostController(context),
//                                context
//                            )
//                        )
//                    }
                }
            }
        }
    }
}

@Composable
fun CourseCard(
    course: Course,
    navController: NavHostController,
    courseViewModel: CourseViewModel
){
    Card (
        modifier = Modifier
            .padding(10.dp)
            .background(color = colorScheme.background)
            .border(
                shape = RoundedCornerShape(10.dp), width = 10.dp,
                brush = Brush.horizontalGradient()
            )
            .shadow(10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)

        ) {

            Text(text = """
                                    Course ID: ${course.courseId}
                                    Institution Name: ${course.institutionName}
                                    Institution Type: ${course.institutionType}
                                    Course Level: ${course.courseLevel}
                                    Course Category: ${course.courseCategory}
                                    Course Name: ${course.courseName}
                                """.trimIndent(),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(onClick = {
                    navController.navigate(ROUTE_COURSE_UPDATE + "/${course.courseId}")
                }) {
                    Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit")
                    Text(text = "Edit")
                }
                TextButton(onClick = {
                    courseViewModel.deleteCourse(courseId = String())
                }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
                    Text("Delete")
                }
            }

        }
    }
}