package com.subhajeet.medical.models.responseModels

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    //val message:String,
    @SerializedName("message")  // This matches your API response
    val userId: String , // Changed from 'message' to 'userId' for clarity


    val status:Int
)