package com.example.signupandsignin

import java.io.Serializable

class Users(val email: String?, val name:String, val mobile:String, val password:String):
    Serializable {
}