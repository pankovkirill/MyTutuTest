package com.tutu.myapplication.view.main

import com.tutu.myapplication.model.data.AppState
import com.tutu.myapplication.model.data.DataModel
import com.tutu.myapplication.model.repository.RepositoryLocal
import com.tutu.myapplication.model.repository.RepositoryRemote
import com.tutu.myapplication.viewmodel.Interactor

class MainInteractor(
    private val repositoryRemote: RepositoryRemote<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {
    override suspend fun getData(fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData())
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData())
        }
        return appState
    }
}