package com.fynzero.footballmatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fynzero.footballmatch.model.NextMatch
import com.fynzero.footballmatch.retrofit.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NextMatchViewModel : ViewModel() {

    private val matchList = MutableLiveData<ArrayList<NextMatch>>()

    fun setMatch(id: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = ApiClient.instance.getNextMatch(id)
            if (response.isSuccessful) {
                matchList.postValue(response.body()?.events)
            }
        }
    }

    fun getMatch(): LiveData<ArrayList<NextMatch>> = matchList
}