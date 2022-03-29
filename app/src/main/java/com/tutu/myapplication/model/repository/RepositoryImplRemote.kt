package com.tutu.myapplication.model.repository

import com.tutu.myapplication.model.data.DataModel
import com.tutu.myapplication.model.datasource.DataSourceRemote

class RepositoryImplRemote(private val dataSourceRemote: DataSourceRemote<List<DataModel>>) :
    RepositoryRemote<List<DataModel>> {
    override suspend fun getData(): List<DataModel> {
        return dataSourceRemote.getData()
    }
}