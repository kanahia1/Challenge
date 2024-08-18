package com.kanahia.challenge

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kanahia.challenge.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dao = DataModelDatabase.getDatabase(this).getDao()
        val repository = DataModelRepository(dao, application)
        val thisViewModel = ViewModelProvider(this, ViewModelFactory(application, repository)).get(DataModelViewModel::class.java)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.setLayoutManager(GridLayoutManager(this@MainActivity, 3))
        val adapter = DataRecyclerAdapter()

        thisViewModel.pagedList.observe(this){
            adapter.submitList(it)
            binding.recyclerView.adapter = adapter
        }

    }
}