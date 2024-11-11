package com.example.mobileexercise99.repository

import com.example.mobileexercise99.model.ApartmentListDetail
import com.example.mobileexercise99.network.DetailsService
import retrofit2.Call

class DetailsRepository(private val detailsService: DetailsService) {
    fun getDetails(id: Int) : Call<ApartmentListDetail> = detailsService.getDetails(id)
}