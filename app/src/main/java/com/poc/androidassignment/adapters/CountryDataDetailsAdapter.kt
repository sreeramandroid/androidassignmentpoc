package com.poc.androidassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.poc.androidassignment.databinding.ContainerCountryDetailsBinding
import com.poc.androidassignment.model.Row

class CountryDataDetailsAdapter() :
    RecyclerView.Adapter<CountryDataDetailsAdapter.CountryDetailViewHolder>() {

    val arrayCountryData: ArrayList<Row> = ArrayList()


    inner class CountryDetailViewHolder(val binding: ContainerCountryDetailsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryDetailViewHolder {

        return CountryDetailViewHolder(
            ContainerCountryDetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = arrayCountryData.size

    /****
     * Consuming Data and bind to views in onBindViewHolder
     * ******/

    override fun onBindViewHolder(holder: CountryDetailViewHolder, position: Int) {
        val countryDetail = arrayCountryData[position]
        holder.binding.dataModel = countryDetail
    }
}