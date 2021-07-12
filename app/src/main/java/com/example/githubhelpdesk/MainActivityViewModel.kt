package com.example.githubhelpdesk

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhelpdesk.network.RecyclerData
import com.example.githubhelpdesk.network.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RetroRepository): ViewModel() {

    var liveData: MutableLiveData<List<RecyclerData>> = MutableLiveData()


    fun getLiveDataObserver():MutableLiveData<List<RecyclerData>>{
        return liveData
    }

    fun loadListOfData(){
        repository.makeApiCall("ny",liveData)
    }


}