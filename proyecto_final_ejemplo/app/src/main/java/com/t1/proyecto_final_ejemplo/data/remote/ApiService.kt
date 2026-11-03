package com.t1.proyecto_final_ejemplo.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("tips") // Endpoint de ejemplo para Retrofit
    suspend fun getVetTips(): List<VetTipResponse>

    companion object {
        const val BASE_URL = "https://api.pawsy-vet.com/"
    }
}
