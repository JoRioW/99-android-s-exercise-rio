package com.example.mobileexercise99.model

data class ApartmentList(
    val address: AddressList,
    val attributes: AttributeList,
    val category: String,
    val completed_at: Int,
    val id: Int,
    val photo: String,
    val project_name: String,
    val tenure: Int
)

data class AddressList(
    val district: String,
    val street_name: String
)

data class AttributeList(
    val area_size: Int,
    val bathrooms: Int,
    val bedrooms: Int,
    val price: Int
)
