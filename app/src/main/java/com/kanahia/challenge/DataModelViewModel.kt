package com.kanahia.challenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kanahia.challenge.models.DataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DataModelViewModel(
    application: Application,
    private val repository: DataModelRepository)
    : AndroidViewModel(application) {

    lateinit var pagedList: LiveData<PagedList<DataModel>>

    init {
        viewModelScope.launch(Dispatchers.IO){
            fetchData()
        }
    }

    private suspend fun fetchData(){
            val config = PagedList.Config.Builder()
                .setPageSize(16)
                .setPrefetchDistance(42)
                .setEnablePlaceholders(false)
                .build()
            val factory: DataSource.Factory<Integer, DataModel> = repository.getData()
            val livePagedList = LivePagedListBuilder(factory, config)
            pagedList = livePagedList.build()
    }
}