package com.tutu.myapplication.model.repository

interface RepositoryRemote<T> {
    suspend fun getData(): T
}