package com.example.mobileexercise99

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobileexercise99.model.ApartmentList
import com.example.mobileexercise99.repository.ListingRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListingViewModel(private val repository: ListingRepository) : ViewModel() {
    val apartmentList = MutableLiveData<ArrayList<ApartmentList>>()
    val errorMessage = MutableLiveData<String>()

     fun getAllList() {
        val response = repository.getApartmentList()
        response.enqueue(object : Callback<ArrayList<ApartmentList>> {
            override fun onResponse(
                call: Call<ArrayList<ApartmentList>>,
                response: Response<ArrayList<ApartmentList>>
            ) {
                apartmentList.postValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<ApartmentList>>, response: Throwable) {
                errorMessage.postValue(response.message)
            }

        })
    }
}