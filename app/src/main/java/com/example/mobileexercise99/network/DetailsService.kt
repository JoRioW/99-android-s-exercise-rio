package com.example.mobileexercise99.network

import com.example.mobileexercise99.model.ApartmentListDetail
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsService {
    @GET("details/{id}.json")
    fun getDetails(@Path("id") id:Int) : Call<ApartmentListDetail>

    companion object{
        var detailsService: DetailsService? = null
        val BASE_URL =  "https://ninetyninedotco-b7299.asia-southeast1.firebasedatabase.app/"

        fun getInstance() : DetailsService{
            if (detailsService == null) {
                detailsService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DetailsService::class.java)
            }
            return detailsService!!
        }
    }
}