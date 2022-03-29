package com.tutu.myapplication.utils

import com.tutu.myapplication.model.data.AppState
import com.tutu.myapplication.model.data.DataModel
import com.tutu.myapplication.room.HistoryEntity

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModel> {
    val searchResult = ArrayList<DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(DataModel(entity.id, entity.login, entity.avatarUrl, entity.type))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: AppState): List<HistoryEntity>? {
    val entityList = ArrayList<HistoryEntity>()
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty()) {
                null
            } else {
                for (data in searchResult) {
                    entityList.add(HistoryEntity(data.id, data.login, data.avatar_url, data.type))
                }
                entityList
            }
        }
        else -> null
    }
}