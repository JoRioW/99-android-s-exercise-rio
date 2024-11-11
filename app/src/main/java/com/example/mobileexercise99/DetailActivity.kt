package com.example.mobileexercise99

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mobileexercise99.model.ApartmentListDetail
import com.example.mobileexercise99.network.DetailsService
import com.example.mobileexercise99.repository.DetailsRepository
import com.example.mobileexercise99.repository.ListingRepository

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backBtn = findViewById<ImageView>(R.id.detailBackBtn)
        backBtn.setOnClickListener {
            finish()
        }

        val id = intent.getIntExtra("id", -1)
        var detailsService : DetailsService = DetailsService.getInstance()

        viewModel = ViewModelProvider(this, DetailsViewModelFactory(DetailsRepository(detailsService))).get(DetailsViewModel::class.java)
        if (id != -1) {
            viewModel.getAllDetail(id)
            viewModel.detail.observe(this, Observer { detail ->
                if (detail != null) {
                    setVar(detail)
                }else{
                    Log.e("DetailAct", "Data Null")
                }
            })

            viewModel.errorMessage.observe(this) { error ->
                Log.e("DetailAct", "Failed")
            }
        }

    }

    private fun setVar(detail: ApartmentListDetail) {
        val detailIV = findViewById<ImageView>(R.id.detailIV)
        val detailPriceTV = findViewById<TextView>(R.id.detailPriceTV)
        val detailNameTV = findViewById<TextView>(R.id.detailNameTV)
        val detailTitleTV = findViewById<TextView>(R.id.detailTitleTV)
        val detailSubtitleTV = findViewById<TextView>(R.id.detailSubtitleTV)
        val detailSize = findViewById<TextView>(R.id.detailSize)
        val detailPricesqftTV = findViewById<TextView>(R.id.detailPricesqftTV)
        val detailFloorLevelTV = findViewById<TextView>(R.id.detailFloorLevelTV)
        val detailFacingTV = findViewById<TextView>(R.id.detailFacingTV)
        val detailYearTV = findViewById<TextView>(R.id.detailYearTV)
        val detailTenureTV = findViewById<TextView>(R.id.detailTenureTV)
        val detailtTypeTV = findViewById<TextView>(R.id.detailtTypeTV)
        val detailtLastUpdatedTV = findViewById<TextView>(R.id.detailtLastUpdatedTV)
        val detailtDescTV = findViewById<TextView>(R.id.detailtDescTV)

        Glide.with(this)
            .load(detail.photo)
            .into(detailIV)

        detailPriceTV.text = "$" + String.format("%,d", detail.attributes.price)
        detailNameTV.text = detail.project_name
        detailTitleTV.text = detail.address.title
        detailSubtitleTV.text = detail.address.subtitle
        detailSize.text = detail.attributes.bedrooms.toString() + " Beds " + detail.attributes.bathroom.toString() + " Baths " + detail.attributes.area_size.toString() + " sqft"
        detailPricesqftTV.text = detail.property_details[0].text
        detailFloorLevelTV.text = detail.property_details[1].text
        detailFacingTV.text = detail.property_details[3].text
        detailYearTV.text = detail.property_details[4].text
        detailTenureTV.text = detail.property_details[5].text
        detailtTypeTV.text = detail.property_details[6].text
        detailtLastUpdatedTV.text = detail.property_details[7].text
        detailtDescTV.text = detail.description
    }


}