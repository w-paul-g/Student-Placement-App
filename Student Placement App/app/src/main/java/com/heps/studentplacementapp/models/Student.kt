package com.heps.studentplacementapp.models



class Student{
    var studentId: String = ""
    var studentName: String = ""
    var email: String = ""
    var password: String = ""

    constructor(
        studentId: String,
        studentName: String,
        email: String,
        password: String,
    ){
        this.studentId =studentId
        this.studentName = studentName
        this.email = email
        this.password = password

    }
    constructor()

}