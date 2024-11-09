package com.example.mobileexercise99.models.apartments

data class ApartmentList(
    val address: AddressList,
    val attributes: AttributeList,
    val category: String,
    val completedAt: Int,
    val id: Int,
    val photo: String,
    val projectName: String,
    val tenure: Int
)

data class AddressList(
    val district: String,
    val streetName: String
)

data class AttributeList(
    val areaSize: Int,
    val bathrooms: Int,
    val bedrooms: Int,
    val price: Int
)
