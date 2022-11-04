package com.myu.myurandomimageapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myu.myurandomimageapp.model.ImageResultItem
import com.myu.myurandomimageapp.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FetchImagesViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel(){
    private  val TAG = "FetchImagesViewModel"
    private val _response = MutableLiveData<List<ImageResultItem>>()

    val responseImages : LiveData<List<ImageResultItem>>
    get() = _response

    init {
        getAllImages()
    }

    private fun getAllImages() = viewModelScope.launch {
        repository.gelAllImages().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d(TAG, "getAllImages: "+response.errorBody())
            }
        }
    }
}