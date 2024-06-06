package com.example.cuartopracticomoviles.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuartopracticomoviles.R
import com.example.cuartopracticomoviles.databinding.ActivityMainBinding
import com.example.cuartopracticomoviles.models.Libro
import com.example.cuartopracticomoviles.models.Libros
import com.example.cuartopracticomoviles.ui.adapters.BookAdapter
import com.example.cuartopracticomoviles.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() ,BookAdapter.OnLibroClickListener{
    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
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
        setupEventListeners()
        setupRecyclerView()
        setupViewModelListeners()
    }

    private fun setupEventListeners() {
        binding.fabAddBook.setOnClickListener {
            val intent = Intent(this, AgregarLibroActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        model.fetchBooks()
    }

    private fun setupViewModelListeners() {
        model.librosList.observe(this) {
            val adapter = (binding.lstBooks.adapter as BookAdapter)
            adapter.updateData(it)
        }
    }

    private fun setupRecyclerView() {
        binding.lstBooks.apply {
            this.adapter = BookAdapter(Libros(), this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onLibroClick(libro: Libro) {
        val intent = Intent(this, DetalleLibroActivity::class.java)
        intent.putExtra("libroId", libro.id)
        startActivity(intent)

    }
}