package com.myu.myurandomimageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.myu.myurandomimageapp.adapter.ImageAdapter
import com.myu.myurandomimageapp.databinding.ActivityMainBinding
import com.myu.myurandomimageapp.viewmodel.FetchImagesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var imageAdapter: ImageAdapter
    private val viewModel : FetchImagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupRecyclerView()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.responseImages.observe(this,{ response ->
            if (response != null) {
                imageAdapter.submitList(response)
            }
        })
    }

    private fun setupRecyclerView() {
        imageAdapter = ImageAdapter()

        binding.rvImages.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }
}