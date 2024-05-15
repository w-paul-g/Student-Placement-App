package com.heps.studentplacementapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.heps.studentplacementapp.ui.screens.account.SignInAdmin
import com.heps.studentplacementapp.ui.screens.account.SignInStudent
import com.heps.studentplacementapp.ui.screens.account.SignOutAlert
import com.heps.studentplacementapp.ui.screens.account.SignUpAdmin
import com.heps.studentplacementapp.ui.screens.account.SignUpStudent
import com.heps.studentplacementapp.ui.screens.adminDashboard.AdminDashboard
import com.heps.studentplacementapp.ui.screens.courses.AddNewCourse
import com.heps.studentplacementapp.ui.screens.courses.ManageCourses
import com.heps.studentplacementapp.ui.screens.courses.UpdateCourse
import com.heps.studentplacementapp.ui.screens.courses.ViewCourses
import com.heps.studentplacementapp.ui.screens.dashboard.StudentDashboard
import com.heps.studentplacementapp.ui.screens.home.HomeScreen
import com.heps.studentplacementapp.ui.screens.selectedcourses.ManageSelectedCourses
import com.heps.studentplacementapp.ui.screens.selectedcourses.SelectCoursesScreen
import com.heps.studentplacementapp.ui.screens.selectedcourses.ViewSelectedCourses


@Composable
fun RouteNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String ,
){
    var context = LocalContext.current
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination) {
        composable(ROUTE_HOME){
            HomeScreen(navController)
        }
        composable(ROUTE_SIGNOUT){
            SignOutAlert(navController)
        }
        //Admin
        composable(ROUTE_ADMIN_SIGNIN){
            SignInAdmin(navController)
        }
        composable(ROUTE_ADMIN_SIGNUP){
            SignUpAdmin(navController)
        }
        composable(ROUTE_ADMIN_DASHBOARD){
            AdminDashboard(navController)
        }
        //Student
        composable(ROUTE_STUDENT_SIGNIN){
            SignInStudent(navController)
        }
        composable(ROUTE_STUDENT_SIGNUP){
            SignUpStudent(navController)
        }
        composable(ROUTE_STUDENT_DASHBOARD){
            StudentDashboard(navController)
        }
        //Courses
        composable(ROUTE_COURSE_ADD){
            AddNewCourse(navController)
        }
        composable(ROUTE_COURSE_MANAGE){
            ManageCourses(navController)
        }
        composable("$ROUTE_COURSE_UPDATE/{courseId}"){
            passedData ->
            UpdateCourse(
                navController,
                passedData.arguments?.getString("courseId")!!
            )
//                backStackEntry ->
//            val courseId = backStackEntry.arguments?.getString("courseId") ?: ""
//            UpdateCourse(navController, courseId)
        }
        composable(ROUTE_COURSE_VIEW){
            ViewCourses(navController)
        }
        composable(ROUTE_SELECT_COURSE){
            SelectCoursesScreen(navController)
        }
        composable(ROUTE_MANAGE_SELECTED_COURSES){
            ManageSelectedCourses(navController)
        }
        composable(ROUTE_VIEW_SELECTED_COURSES){
            ViewSelectedCourses(navController)
        }
    }
}