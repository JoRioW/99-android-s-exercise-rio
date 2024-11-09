package com.example.mobileexercise99.models.apartments

data class ApartmentListDetail(
    val address: AddressDetails,
    val attribute: AttributeDetails,
    val description: String,
    val id: Int,
    val photo: String,
    val projectName: String,
    val propertyDetails: PropertyDetails
)
data class AddressDetails(
    val mapCoordinates: MapCoordinates,
    val subtitle: String,
    val title: String
)

data class MapCoordinates(
    val latitude: Double,
    val longitude: Double
)

data class AttributeDetails(
    val areaSize: Int,
    val bathroom: Int,
    val bedrooms: Int,
    val price: Int
)

data class PropertyDetails(
    val label: String,
    val text: String
)
