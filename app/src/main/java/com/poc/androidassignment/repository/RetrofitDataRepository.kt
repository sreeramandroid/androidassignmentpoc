package com.poc.androidassignment.repository

import com.poc.androidassignment.model.CountryDetails
import com.poc.androidassignment.networkcall.RetroInstance
import com.poc.androidassignment.networkcall.WebService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RetrofitDataRepository {

 /****
 * Data Repository to initialise APi call
 * ***/
 fun makeApiCall(): Observable<CountryDetails>? {
 val apiInstance = RetroInstance.getRetroInstance().create(WebService::class.java)
 return apiInstance.getCountryDataList().subscribeOn(Schedulers.io())
 .observeOn(AndroidSchedulers.mainThread())
 }

}