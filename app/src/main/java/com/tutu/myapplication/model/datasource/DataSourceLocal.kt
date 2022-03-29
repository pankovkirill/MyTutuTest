package com.tutu.myapplication.model.datasource

import com.tutu.myapplication.model.data.AppState

interface DataSourceLocal<T> : DataSourceRemote<T> {
    suspend fun saveToDB(appState: AppState)
}