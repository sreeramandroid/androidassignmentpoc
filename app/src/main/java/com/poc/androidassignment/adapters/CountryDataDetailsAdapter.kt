package com.poc.androidassignment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.poc.androidassignment.databinding.ContainerCountryDetailsBinding
import com.poc.androidassignment.model.CountryDetails
import com.poc.androidassignment.model.Row
import com.poc.androidassignment.viewmodel.HomeViewModel

class CountryDataDetailsAdapter(private val _context: Context, var vmodel: HomeViewModel) :
    RecyclerView.Adapter<CountryDataDetailsAdapter.CountryDetailViewHolder>() {

    val _arrayCountryData: ArrayList<Row> = ArrayList()

    val _arrayLiveCountryData: ArrayList<Row> = ArrayList()

    val mutableList: MutableList<CountryDetails> = ArrayList()


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

    override fun getItemCount(): Int {
        return _arrayCountryData.size
    }

    /****
     * Consuming Data and bind to views in onBindViewHolder
     * ******/

    override fun onBindViewHolder(holder: CountryDetailViewHolder, position: Int) {
        val _countryDetail = _arrayCountryData.get(position)
        holder.binding.dataModel = _countryDetail
    }
}