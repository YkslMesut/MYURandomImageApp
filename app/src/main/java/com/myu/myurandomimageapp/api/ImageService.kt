package com.myu.myurandomimageapp.api

import com.myu.myurandomimageapp.utils.Constants.CLIENT_ID
import com.myu.myurandomimageapp.model.ImageResultItem
import com.myu.myurandomimageapp.utils.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {

    @Headers("Accept-Version: v1","Authorization: Client-ID $CLIENT_ID")
    @GET(END_POINT)
    suspend fun getAllImages() : Response<List<ImageResultItem>>
}