package com.example.mobileexercise99

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobileexercise99.model.ApartmentList
import com.example.mobileexercise99.repository.ListingRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListingViewModel(private val repository: ListingRepository) : ViewModel() {
    val apartmentList = MutableLiveData<List<ApartmentList>>()
    val errorMessage = MutableLiveData<String>()

     fun getAllData() {
        val response = repository.getApartmentList()
        response.enqueue(object : Callback<List<ApartmentList>> {
            override fun onResponse(
                p0: Call<List<ApartmentList>>,
                p1: Response<List<ApartmentList>>
            ) {
                apartmentList.postValue(p1.body())
            }

            override fun onFailure(p0: Call<List<ApartmentList>>, p1: Throwable) {
                errorMessage.postValue(p1.message)
            }

        })
    }
}