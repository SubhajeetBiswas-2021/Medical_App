package com.subhajeet.medical.models.responseModels

data class getOrderDetailsByIdResponse(
    val orderDetails: List<OrderDetails>,
    val status: Int
)