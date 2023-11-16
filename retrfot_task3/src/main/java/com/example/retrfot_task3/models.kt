package com.example.retrfot_task3

data class Weight(
    val imperial: String,
    val metric: String
)
data class Breed(
    val weight: Weight,
    val name: String,
)

data class Cat(
    var url: String,
    var breeds: List<Breed>
)
data class Image(
    var id: String
)