package com.example.mobileexercise99.network

import com.example.mobileexercise99.model.ApartmentList
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ListingService {
    @GET("listings.json")
    fun getApartmentList() : Call<List<ApartmentList>>

    companion object{
        var listingService: ListingService? = null
        val BASE_URL =  "https://ninetyninedotco-b7299.asia-southeast1.firebasedatabase.app/"

        fun getInstance() : ListingService{
            if (listingService == null) {
                listingService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .build()
                    .create(ListingService::class.java)
            }
            return listingService!!
        }
    }
}