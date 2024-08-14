package com.dag.myapplication.services

import com.dag.myapplication.data.register.RegisterRequest
import com.dag.myapplication.data.register.RegisterResponse
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/register")
    suspend fun register(
        @Body registerBody: RegisterRequest,
    ): Flow<RegisterResponse>
}