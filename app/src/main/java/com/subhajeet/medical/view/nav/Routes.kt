package com.subhajeet.medical.view.nav

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    object LoginRoutes

    @Serializable
    object SignUpRoutes


    @Serializable
    data class WaitingRoutes(val userId: String) //// Now it's a data class so we can pass userId


    @Serializable
    object HomeRoutes

    @Serializable
    data class OrderRoutes(val productId: String)
}