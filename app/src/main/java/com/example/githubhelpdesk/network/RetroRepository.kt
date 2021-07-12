package com.example.githubhelpdesk.network

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(private val retroServiceInstance: RetroServiceInstance) {


    fun makeApiCall(query:String, liveData: MutableLiveData<List<RecyclerData>>){
        val call: Call<RecyclerList> = retroServiceInstance.getDataFromAPI(query)
        call?.enqueue(object : Callback<RecyclerList>{


            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                liveData.postValue(null)
            }

            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                liveData.postValue(response.body()?.items!!)
            }


        })


    }


}