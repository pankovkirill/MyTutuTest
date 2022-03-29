package com.tutu.myapplication.model.repository

import com.tutu.myapplication.model.data.AppState
import com.tutu.myapplication.model.data.DataModel
import com.tutu.myapplication.model.datasource.DataSourceLocal

class RepositoryImplLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {
    override suspend fun getData(): List<DataModel> {
        return dataSource.getData()
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}