package com.example.mobileexercise99

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileexercise99.adapter.ListingAdapter
import com.example.mobileexercise99.network.ListingService
import com.example.mobileexercise99.repository.ListingRepository


class MainActivity : AppCompatActivity() {
    private lateinit var mainRV: RecyclerView
    private lateinit var adapter: ListingAdapter
    private lateinit var viewModel: ListingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var listingService : ListingService = ListingService.getInstance()
        mainRV = findViewById(R.id.mainRV)
        mainRV.layoutManager = LinearLayoutManager(this)
        adapter = ListingAdapter()
        mainRV.adapter = adapter

        viewModel = ViewModelProvider(this, ListingViewModelFactory(ListingRepository(listingService))).get(ListingViewModel::class.java)
        viewModel.apartmentList.observe(this, Observer {
            if (it != null) {
                Log.d("MainAct", "onCreate: $it")
                adapter.apartmentLists(it)
            }else {
                Log.e("MainAct", "Received null data in apartmentList")
            }
        })
        viewModel.getAllData()
    }
}