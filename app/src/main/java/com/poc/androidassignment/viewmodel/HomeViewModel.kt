package com.poc.androidassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.poc.androidassignment.dbutils.AppDB
import com.poc.androidassignment.model.CountryDetails
import com.poc.androidassignment.model.Row
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


    lateinit var allUsers: MutableLiveData<List<Row>>


    var dataRepository = RetrofitDataRepository()
    var countryListRx: MutableLiveData<CountryDetails>
    var isProgessDisplay = MutableLiveData<Boolean>()
    var strTitle = MutableLiveData<String>()

    init {
        allUsers = MutableLiveData()
        countryListRx = MutableLiveData()
    }

    fun gerAllUsers() {
        val userDao = AppDB.getAppDatabase((getApplication()))?.userDao()
        val list = userDao?.getAllUserInfo()
        countryResponseData.postValue(list)
    }

    /**
     * Adding data to db
     *
     ****/
    fun addData(user: List<Row>) {
        val userDao = AppDB.getAppDatabase((getApplication()))?.userDao()
        userDao?.deleteUser(user)
        userDao?.saveUserData(user)
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
                var dbref = AppDB.getAppDatabase((getApplication()))?.userDao()
                val list = dbref?.getAllUserInfo()
                getApplication<Application>().applicationContext.setStringPreferenceAsynchronous(
                    "title",
                    t.title
                )
                dbref?.deleteUser(list)
                addData(t.rows)
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