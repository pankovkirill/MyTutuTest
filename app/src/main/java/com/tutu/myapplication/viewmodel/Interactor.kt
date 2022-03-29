package com.tutu.myapplication.viewmodel

interface Interactor<T> {
    suspend fun getData(fromRemoteSource: Boolean): T
}