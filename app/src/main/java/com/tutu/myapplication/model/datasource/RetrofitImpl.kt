package com.tutu.myapplication.model.datasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tutu.myapplication.model.data.DataModel
import com.tutu.myapplication.model.data.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl : DataSourceRemote<List<DataModel>> {
    override suspend fun getData(): List<DataModel> {
        return getService().searchAsync().await()
    }

    private fun getService(): ApiService {
        return createRetrofit().create(ApiService::class.java)

    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    companion object {
        private const val BASE_URL = "https://api.github.com"
    }
}