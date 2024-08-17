package com.kanahia.challenge

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val application: Application,
    private val repository: DataModelRepository)
    : ViewModelProvider.Factory {

    public override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DataModelViewModel(application, repository) as T
    }

}