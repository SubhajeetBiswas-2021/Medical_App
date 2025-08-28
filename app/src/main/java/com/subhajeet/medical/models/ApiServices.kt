package com.subhajeet.medical.models

import com.subhajeet.medical.models.responseModels.LoginResponse
import com.subhajeet.medical.models.responseModels.getAllProductResponse
import com.subhajeet.medical.models.responseModels.getProductByProductIdResponse
import com.subhajeet.medical.models.responseModels.getUserByIdResponse
import retrofit2.Response

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {

        @FormUrlEncoded
        @POST("login")
        suspend fun login(

            @Field("email") email:String,
            @Field("password") password:String
        ): Response<LoginResponse>

        @FormUrlEncoded
        @POST("getUserById")
        suspend fun getUserById(
            @Field("userId") userId: String
        ):Response<getUserByIdResponse>


        @GET("getAllProduct")
        suspend fun getAllProduct():Response<getAllProductResponse>

        @FormUrlEncoded
        @POST("getProductById")
        suspend fun getProductById(
            @Field("productId") productId:String
        ):Response<getProductByProductIdResponse>
}