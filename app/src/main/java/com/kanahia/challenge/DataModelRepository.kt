package com.kanahia.challenge

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kanahia.challenge.models.DataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DataModelRepository(private val dao: DataModelDAO, private val application: Application) {

    suspend fun getData(): LiveData<List<DataModel>> {
        val totalData = dao.getTotalData()

        if (totalData.value.isNullOrEmpty()) {
            val fileName = "data.json"
            val jsonString = withContext(Dispatchers.IO) {
                application.assets.open(fileName).bufferedReader().use {
                    it.readText()
                }
            }
            val typeToken = TypeToken.getParameterized(
                MutableList::class.java,
                DataModel::class.java
            )
            val list: List<DataModel> = Gson().fromJson(jsonString, typeToken.type)
            dao.insertAll(list)
        }

        return totalData
    }
}

