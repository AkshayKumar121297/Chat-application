package com.example.myapplication

//we are not using data class here because in Firebase Authentication we need an empty constructor
class User {
    var name: String? = null
    var email: String? = null
    var uid: String? = null

    constructor(){}

    constructor(name: String?, email: String?, uid: String?){
        this.name = name
        this.email = email
        this.uid = uid
    }
}