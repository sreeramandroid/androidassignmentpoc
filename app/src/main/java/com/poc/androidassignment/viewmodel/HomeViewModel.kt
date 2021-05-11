package com.poc.androidassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.poc.androidassignment.model.CountryDetails
import com.poc.androidassignment.model.Row
import com.poc.androidassignment.repository.DatabaseRepository
import com.poc.androidassignment.repository.RetrofitDataRepository
import com.poc.androidassignment.utils.setStringPreferenceAsynchronous
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class HomeViewModel(application: Application) :
    AndroidViewModel(application) {

    /***
     * Calling Api from data repository
     ****/
    var countryResponseData = MutableLiveData<List<Row>>()


    private var dataRepository = RetrofitDataRepository()
    private var databaseRepository = DatabaseRepository()
    var isProgessDisplay = MutableLiveData<Boolean>()
    var strTitle = MutableLiveData<String>()


    fun gerAllUsers() {
        val list = databaseRepository.fetchUsersFromDb(getApplication())
        countryResponseData.postValue(list)
    }


    fun makeApiCall() {
        isProgessDisplay.postValue(true)
        dataRepository.makeApiCall()?.subscribe((getCountryListFromWs()))
    }

    private fun getCountryListFromWs(): Observer<CountryDetails> {
        return object : Observer<CountryDetails> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: CountryDetails) {
                getApplication<Application>().applicationContext.setStringPreferenceAsynchronous(
                    "title",
                    t.title
                )
                databaseRepository.deleteDbList(getApplication())
                databaseRepository.addData(getApplication(), t.rows)
                countryResponseData.postValue(t.rows)
                strTitle.postValue(t.title)
            }

            override fun onError(e: Throwable) {
                isProgessDisplay.postValue(false)
                countryResponseData.postValue(null)
            }

            override fun onComplete() {
                isProgessDisplay.postValue(false)
            }
        }
    }
}