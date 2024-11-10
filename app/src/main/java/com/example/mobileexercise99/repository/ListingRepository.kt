package com.example.mobileexercise99.repository

import com.example.mobileexercise99.network.ListingService

class ListingRepository (private val listingService: ListingService){
    fun getApartmentList() = listingService.getApartmentList()
}