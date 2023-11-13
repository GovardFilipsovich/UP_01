package com.example.employers

class EmployerModel(
    val empName: String = "",
    val empSurname: String = "",
    val empPatronymic: String = "",
    val avatarURL: String = "",
    val commandName: String = "",
    val rate: Int = 0,
    val lines: Int = 0,
    val numberOfProjects: Int = 0,
    val phone: String = "",
    val mail: String = ""
){
    fun full_name() = empSurname + " " + empName + " " + empSurname
}