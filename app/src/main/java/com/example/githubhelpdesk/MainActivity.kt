package com.example.githubhelpdesk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.daggerroomdbdemo.RecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var viewModel: MainActivityViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        initRecyclerView()
        initViewModel()


    }

    private fun initRecyclerView(){
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerview.adapter = recyclerViewAdapter
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, {
            if (it != null){

                recyclerViewAdapter.setlistData(it)
                recyclerViewAdapter.notifyDataSetChanged()

            }
            else{
                Toast.makeText(this, "error in getting data", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.loadListOfData()


    }


}