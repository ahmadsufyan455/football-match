package com.fynzero.footballmatch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fynzero.footballmatch.model.DetailMatch
import com.fynzero.footballmatch.model.Teams
import com.fynzero.footballmatch.retrofit.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailMatchViewModel : ViewModel() {

    private val matchDetail = MutableLiveData<List<DetailMatch>>()
    private val teamHome = MutableLiveData<List<Teams>>()
    private val teamAway = MutableLiveData<List<Teams>>()

    fun setDetail(id: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = ApiClient.instance.getDetailMatch(id)
            if (response.isSuccessful) {
                Log.d("DetailMatchViewModel", response.body().toString())
                matchDetail.postValue(response.body()?.events)
            }
        }
    }

    fun setTeamHome(id: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = ApiClient.instance.getDetailTeam(id)
            if (response.isSuccessful) {
                teamHome.postValue(response.body()?.teams)
            }
        }
    }

    fun setTeamAway(id: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = ApiClient.instance.getDetailTeam(id)
            if (response.isSuccessful) {
                teamAway.postValue(response.body()?.teams)
            }
        }
    }

    fun getDetail(): LiveData<List<DetailMatch>> = matchDetail
    fun getTeamHome(): LiveData<List<Teams>> = teamHome
    fun getTeamAway(): LiveData<List<Teams>> = teamAway
}