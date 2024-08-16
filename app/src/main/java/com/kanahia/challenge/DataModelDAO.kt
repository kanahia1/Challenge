package com.kanahia.challenge

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kanahia.challenge.models.DataModel

@Dao
interface DataModelDAO {

    @Insert
    suspend fun save(data: DataModel)

    @Insert
    suspend fun insertAll(data: List<DataModel>)

    @Query("SELECT * FROM datamodel")
    fun getTotalData() : LiveData<List<DataModel>>
}