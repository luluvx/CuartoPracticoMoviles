package com.example.cuartopracticomoviles.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuartopracticomoviles.R
import com.example.cuartopracticomoviles.databinding.ActivityGeneroListBinding
import com.example.cuartopracticomoviles.models.Genero
import com.example.cuartopracticomoviles.models.Generos
import com.example.cuartopracticomoviles.ui.adapters.GenreAdapter
import com.example.cuartopracticomoviles.ui.viewmodels.GeneroListViewModel

class GeneroListActivity : AppCompatActivity() , GenreAdapter.OnGeneroClickListener{
    private lateinit var binding: ActivityGeneroListBinding
    private val model: GeneroListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGeneroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
        setupViewModelListeners()

    }

    override fun onResume() {
        super.onResume()
        model.fetchGeneroList()
    }

    private fun setupViewModelListeners() {
        model.generoList.observe(this) {
            val adapter = (binding.lstGeneros.adapter as GenreAdapter)
            adapter.updateData(it)
            Log.d("GeneroListActivity", "Generos: $it")
        }
    }

    private fun setupRecyclerView() {
        binding.lstGeneros.apply {
            this.adapter = GenreAdapter(Generos(), this@GeneroListActivity)
            layoutManager = LinearLayoutManager(this@GeneroListActivity)
        }
    }

    override fun onGeneroClick(genero: Genero) {

    }
}