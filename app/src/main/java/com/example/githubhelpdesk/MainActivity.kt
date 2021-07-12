package com.example.githubhelpdesk

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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
        search.setOnClickListener{
            progressBar.visibility = View.VISIBLE
            if (currentFocus != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }

            var text = editText.text
            viewModel.loadListOfData(text)
            recyclerViewAdapter.notifyDataSetChanged()
            progressBar.visibility = View.GONE

        }


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


    }


}