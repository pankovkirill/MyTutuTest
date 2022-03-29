package com.tutu.myapplication.model.datasource

interface DataSourceRemote<T> {
    suspend fun getData(): T
}