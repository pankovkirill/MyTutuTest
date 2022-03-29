package com.tutu.myapplication.model.data.api

import com.tutu.myapplication.model.data.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {
    @GET("/users")
    fun searchAsync(): Deferred<List<DataModel>>
}