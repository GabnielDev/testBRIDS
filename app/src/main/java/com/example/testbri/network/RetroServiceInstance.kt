package com.example.testbri.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RetroServiceInstance {

    @POST("yDZ0D96qnktG9yjj9KNe")
    suspend fun postKandidat(
        @Body kandidat: HashMap<String, Any>
    ): Response<com.example.testbri.data.Response>

}