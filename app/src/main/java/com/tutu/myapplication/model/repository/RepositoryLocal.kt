package com.tutu.myapplication.model.repository

import com.tutu.myapplication.model.data.AppState

interface RepositoryLocal<T>: RepositoryRemote<T> {
    suspend fun saveToDB(appState: AppState)
}