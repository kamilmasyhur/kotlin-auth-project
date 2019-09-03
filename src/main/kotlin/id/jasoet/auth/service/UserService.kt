package id.jasoet.auth.service

import io.ebean.Database

class UserService(private val database: Database) {

    private val dummyUserName = "daniel"
    private val dummyUserPassword = "password"

    fun isLoginSuccess(username: String, password: String): Boolean {
        println("username $username == user.name $dummyUserName")
        println("password $password == user.password $dummyUserPassword")

        return dummyUserName == username && dummyUserPassword == password
    }
}