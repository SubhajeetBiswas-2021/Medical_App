package com.subhajeet.medical.models.responseModels

data class getAllProductResponse(
    val products: List<Product>,
    val status: Int
)