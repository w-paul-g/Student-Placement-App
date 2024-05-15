package com.heps.studentplacementapp.ui.screens.courses

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.heps.studentplacementapp.navigation.ROUTE_SELECT_COURSE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewCourses(navController: NavHostController){
    val context = LocalContext.current
//    var course = Course()
    val courseViewModel =  CourseViewModel(
        navController = NavHostController(context),
        context = context
    )

    var listOfCourses by remember { mutableStateOf(emptyList<Course>()) }
    val course = remember { mutableStateOf(Course()) }
    val courses = remember { mutableStateListOf<Course>() }
    LaunchedEffect(Unit){
        courseViewModel.viewCourses(
            course,
            courses
        )
    }

//    LaunchedEffect(Unit) {
//        courseViewModel.viewCourses{ courses ->
//            listOfCourses = courses
//        }
//    }

    Scaffold(
        topBar ={
            CenterAlignedTopAppBar(
                title = {
                    //Search Bar
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
                    TextButton(onClick = {
                        navController.navigate(ROUTE_SELECT_COURSE)
                    }) {
                        Text(
                            text = "APPLY",
                            fontWeight = FontWeight.Bold,
                            color = colorScheme.onSurface
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
                    .fillMaxWidth(0.96f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn {
                    items(courses) { course ->
                        CourseCard(course = Course(), navController, courseViewModel)
                    }
                }
//                listOfCourses.forEach {  course ->
//                    CourseCardViewer(
//                        navController,
//                        course = course
//                    )
//                }

            }
        }
    }
}

@Composable
fun CourseCardViewer(
    navController: NavHostController,
    course: Course
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

        }
    }

}