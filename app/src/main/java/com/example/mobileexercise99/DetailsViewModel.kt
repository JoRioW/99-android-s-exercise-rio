package com.example.mobileexercise99

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobileexercise99.model.ApartmentListDetail
import com.example.mobileexercise99.repository.DetailsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel(private val repository: DetailsRepository) : ViewModel() {
    val detail = MutableLiveData<ApartmentListDetail>()
    val errorMessage = MutableLiveData<String>()

    fun getAllDetail(id : Int){
        val response = repository.getDetails(id)
        response.enqueue(object : Callback<ApartmentListDetail> {
            override fun onResponse(
                call: Call<ApartmentListDetail>,
                response: Response<ApartmentListDetail>
            ) {
                if (response.isSuccessful) {
                    Log.d("DetailsViewModel", "Data received: ${response.body()}")
                    detail.postValue(response.body()) // Menetapkan data ke LiveData
                } else {
                    Log.e("DetailsViewModel", "Error response: ${response.code()} - ${response.message()}")
                    errorMessage.postValue("Error: ${response.message()}")
                }
            }


            override fun onFailure(call: Call<ApartmentListDetail>, response: Throwable) {
                errorMessage.postValue(response.message)
            }

        })
    }
}