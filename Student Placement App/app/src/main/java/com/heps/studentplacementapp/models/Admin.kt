package com.heps.studentplacementapp.models


class Admin {
    var adminId: String = ""
    //    var adminName: String = ""
    var email: String = ""
    var password: String = ""

    constructor(
//        adminName: String,
        email: String,
        password: String,
        adminId: String
    ){
        this.adminId = adminId
        this.adminId = adminId
//        this.adminName = adminName
        this.email = email
        this.password = password

    }
    constructor()

}