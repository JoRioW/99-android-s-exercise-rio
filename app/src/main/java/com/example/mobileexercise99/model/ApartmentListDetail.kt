package com.example.mobileexercise99.model

data class ApartmentListDetail(
    val address: AddressDetails,
    val attributes: AttributeDetails,
    val description: String,
    val id: Int,
    val photo: String,
    val project_name: String,
    val property_details: List<PropertyDetails>
)
data class AddressDetails(
    val map_coordinates: MapCoordinates,
    val subtitle: String,
    val title: String
)

data class MapCoordinates(
    val latitude: Double,
    val longitude: Double
)

data class AttributeDetails(
    val area_size: Int,
    val bathroom: Int,
    val bedrooms: Int,
    val price: Int
)

data class PropertyDetails(
    val label: String,
    val text: String
)
