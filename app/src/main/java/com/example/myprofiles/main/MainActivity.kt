package com.example.myprofiles.main

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myprofiles.User
import com.example.myprofiles.databinding.ActivityMainBinding
import com.example.myprofiles.details.DetailReposAdapter
import com.example.myprofiles.details.DetailsActivity

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel


    private val mainAdapter = MainAdapter {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.USERNAME, it.login)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Github"

        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        viewModel.setUser()
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this, {
            showLoading(it)
        })

        viewModel.listUser.observe(this, { listUser ->
            showListUser(listUser)
        })

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showListUser(listUser: List<User>) {
        mainAdapter.setList(listUser)
        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity, RecyclerView.VERTICAL, false
            )
            adapter = mainAdapter

            Log.d(TAG, "showListUser: $listUser")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}