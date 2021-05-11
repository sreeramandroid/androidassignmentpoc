package com.poc.androidassignment.networkcall

import com.poc.androidassignment.model.CountryDetails
import io.reactivex.Observable
import retrofit2.http.GET

interface WebService {

    /**
     * Get method for country details
     * **/
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getCountryDataList(): Observable<CountryDetails>
}