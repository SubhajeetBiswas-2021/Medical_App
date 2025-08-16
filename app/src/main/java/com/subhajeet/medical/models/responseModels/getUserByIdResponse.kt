package com.subhajeet.medical.models.responseModels

import com.google.gson.annotations.SerializedName

data class getUserByIdResponse(
    val status: Int,
    @SerializedName("user")
    val user: User
)