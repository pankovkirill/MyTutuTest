package com.tutu.myapplication.di

import androidx.room.Room
import com.tutu.myapplication.model.data.DataModel
import com.tutu.myapplication.model.datasource.RetrofitImpl
import com.tutu.myapplication.model.datasource.RoomDataBaseImpl
import com.tutu.myapplication.model.repository.RepositoryImplLocal
import com.tutu.myapplication.model.repository.RepositoryImplRemote
import com.tutu.myapplication.model.repository.RepositoryLocal
import com.tutu.myapplication.model.repository.RepositoryRemote
import com.tutu.myapplication.room.HistoryDataBase
import com.tutu.myapplication.view.main.MainInteractor
import com.tutu.myapplication.view.main.MainViewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<RepositoryRemote<List<DataModel>>> { RepositoryImplRemote(RetrofitImpl()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplLocal(
            RoomDataBaseImpl(get())
        )
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}