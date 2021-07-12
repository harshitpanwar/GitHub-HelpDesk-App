package com.example.githubhelpdesk

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhelpdesk.network.RecyclerData
import com.example.githubhelpdesk.network.RecyclerList
import com.example.githubhelpdesk.network.RetroRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val repository: RetroRepository): ViewModel() {

    lateinit var liveData: MutableLiveData<List<RecyclerData>>

    init {
        liveData = MutableLiveData()
    }


    fun getLiveDataObserver():MutableLiveData<List<RecyclerData>>{
        return liveData
    }

    fun loadListOfData(){
        repository.makeApiCall("ny",liveData)
    }


}