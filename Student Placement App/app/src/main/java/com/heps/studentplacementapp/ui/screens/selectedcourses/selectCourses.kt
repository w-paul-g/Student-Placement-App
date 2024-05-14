package com.heps.studentplacementapp.ui.screens.selectedcourses

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.sharp.Cancel
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import com.heps.studentplacementapp.data.CourseViewModel
import com.heps.studentplacementapp.data.SelectedCoursesViewModel
import com.heps.studentplacementapp.models.Course
import com.heps.studentplacementapp.navigation.ROUTE_MANAGE_SELECTED_COURSES


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCoursesScreen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val selectedCoursesViewModel = SelectedCoursesViewModel(
        navController = NavHostController(context),
        context = context
    )
    val courseViewModel = CourseViewModel(
        navController = NavHostController(context),
        context = context
    )

    // Retrieve all courses from the database
    var selectedCourses by remember { mutableStateOf(emptyList<Course>()) }

    // Retrieve all courses from the database
    LaunchedEffect(Unit) {
        courseViewModel.viewCourses{ courses ->
            selectedCourses = courses
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Select Courses")
                },
                navigationIcon = {
                    TextButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Clear"
                        )
                        Text(text = "Cancel")

                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            selectedCoursesViewModel.addSelectedCourses(selectedCourses)
                            navController.navigate(ROUTE_MANAGE_SELECTED_COURSES)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Save"
                        )
                        Text(text = "Save")
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
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Exposed dropdown menus for selecting courses
                        repeat(4) { index ->
                            CourseDropdownMenu(
                                selectedCourses,
                                selectedCoursesViewModel = SelectedCoursesViewModel(
                                    navController = NavHostController(context),
                                    context
                                ),
                                index = index
                            ) { course ->
                                selectedCourses = selectedCourses.toMutableList().apply {
                                    this[index] = course
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun CourseDropdownMenu(
    courses: List<Course>,
    index: Int,
    selectedCoursesViewModel: SelectedCoursesViewModel,
    onCourseSelected: (Course) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCourse by remember { mutableStateOf<Course?>(null) }

    Column{
        Text(
            text = "Select Course ${index + 1}",
//            style = MaterialTheme.typography.subtitle1
        )
        Box(modifier = Modifier.padding(top = 8.dp)) {
            OutlinedTextField(
                value = selectedCourse?.courseName ?: "",
                onValueChange = { selectedCourse?.courseName = it },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true }),
                leadingIcon = {
                    IconButton(onClick = {
                        selectedCoursesViewModel.removeSelectedCourse(selectedCourse!!.courseId)
                    }) {
                        Icon(
                            imageVector = Icons.Sharp.Cancel,
                            contentDescription = "Remove"
                        )
                    }
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Expand",
                        modifier = Modifier.clickable { expanded = true }
                    )
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                courses.forEach { course ->
                    DropdownMenuItem(
                        text = {
                            Text(text = """
                                ${course.courseId} - ${course.courseLevel} ${course.courseCategory} 
                                ${course.courseName} 
                                @ ${course.institutionName}
                            """.trimIndent())
                        },
                        onClick = { selectedCourse = course
                            onCourseSelected(course)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}