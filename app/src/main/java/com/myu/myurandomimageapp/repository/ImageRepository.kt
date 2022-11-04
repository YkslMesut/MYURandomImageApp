package com.myu.myurandomimageapp.repository

import com.myu.myurandomimageapp.api.ImageService
import javax.inject.Inject

class ImageRepository
@Inject constructor(
    private val api : ImageService
){

    suspend fun gelAllImages() = api.getAllImages()
}