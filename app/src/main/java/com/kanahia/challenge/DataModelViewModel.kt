package com.kanahia.challenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanahia.challenge.models.DataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataModelViewModel(
    application: Application,
    private val repository: DataModelRepository)
    : AndroidViewModel(application) {

    var data: LiveData<List<DataModel>>? = null

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
           data = repository.getData()
        }
    }
}