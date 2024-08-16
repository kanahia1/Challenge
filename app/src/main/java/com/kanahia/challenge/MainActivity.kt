package com.kanahia.challenge

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kanahia.challenge.models.DataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        checkFile()
    }

    private fun checkFile(){
        lifecycleScope.launch(Dispatchers.IO) {
            val db = DataModelDatabase.getDatabase(this@MainActivity)
            val dao = db.getDao()
            var size = 0

            dao.getTotalData().observe(this@MainActivity){
                size = it.size
            }

            if (size == 0){
                val fileName = "data.json"
                val jsonString = application.assets.open(fileName).bufferedReader().use{
                    it.readText()
                }
                val typeToken = TypeToken.getParameterized(
                    MutableList::class.java,
                    DataModel::class.java
                )
                val list: List<DataModel> = Gson().fromJson(jsonString, typeToken.type)
                dao.insertAll(list)
            }else{

            }
        }
    }
}