package com.tutu.myapplication.model.datasource

import com.tutu.myapplication.model.data.AppState
import com.tutu.myapplication.model.data.DataModel
import com.tutu.myapplication.room.HistoryDao
import com.tutu.myapplication.utils.convertDataModelSuccessToEntity
import com.tutu.myapplication.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImpl(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.delete()
            historyDao.insert(it)
        }
    }
}