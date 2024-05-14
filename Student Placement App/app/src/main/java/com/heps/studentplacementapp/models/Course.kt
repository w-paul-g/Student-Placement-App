package com.heps.studentplacementapp.models

class Course {
    var courseId: String = ""
    var institutionName: String = ""
    var institutionType: String = ""
    var courseLevel: String = ""
    var courseCategory: String = ""
    var courseName: String = ""
    constructor(
        courseId: String,
        institutionName: String,
        institutionType: String,
        courseLevel: String,
        courseCategory: String,
        courseName: String
    ){
        this.courseId = courseId
        this.institutionName = institutionName
        this.institutionName = institutionName
        this.institutionType = institutionType
        this.courseLevel = courseLevel
        this.courseCategory = courseCategory
        this.courseName = courseName
    }
    constructor()
}