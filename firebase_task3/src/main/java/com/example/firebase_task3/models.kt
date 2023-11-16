package com.example.firebase_task3

data class Product(
    val id: Int = 0,
    val Name: String = "name",
    val Cost: Int = 0,
    val Pic_url: String = "",
    var count: Int? = null,
    var is_select: Boolean? = null
)

data class Order(
    val id: Long = 0,
    val products: List<Product> = listOf(),
    val time: Int = 0,
    val sum: Int = 0
)