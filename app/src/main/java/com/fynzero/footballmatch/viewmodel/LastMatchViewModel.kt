package com.fynzero.footballmatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fynzero.footballmatch.model.LastMatch
import com.fynzero.footballmatch.retrofit.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LastMatchViewModel : ViewModel() {

    private val matchList = MutableLiveData<ArrayList<LastMatch>>()

    fun setMatch(id: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = ApiClient.instance.getLastMatch(id)
            if (response.isSuccessful) {
                matchList.postValue(response.body()?.events)
            }
        }
    }

    fun getMatch(): LiveData<ArrayList<LastMatch>> = matchList
}