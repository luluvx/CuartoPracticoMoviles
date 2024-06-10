package com.example.cuartopracticomoviles.ui.activities

import android.content.Intent
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
import com.example.cuartopracticomoviles.databinding.ActivityLibrosPorGeneroBinding
import com.example.cuartopracticomoviles.models.Libro
import com.example.cuartopracticomoviles.models.Libros
import com.example.cuartopracticomoviles.ui.adapters.BookAdapter
import com.example.cuartopracticomoviles.ui.viewmodels.LibrosPorGeneroViewModel

class LibrosPorGeneroActivity : AppCompatActivity() ,BookAdapter.OnLibroClickListener{
    private var idGenero: Int = 0
    private lateinit var binding: ActivityLibrosPorGeneroBinding
    private val model: LibrosPorGeneroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLibrosPorGeneroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        idGenero = intent.getIntExtra("generoId", -1)
        if (idGenero != -1) {
            model.fetchLibrosPorGenero(idGenero)
        }
        setupRecyclerView()
        setupViewModelObservers()


    }

    private fun setupViewModelObservers() {
        model.librosPorGenero.observe(this) {librosPorGenero ->
            if (librosPorGenero == null) {
                return@observe
            }
            Log.d("LibrosPorGeneroActivity", "Libros filtrados: $librosPorGenero")
            val adapter = (binding.lstLibrosFiltrados.adapter as BookAdapter)
            adapter.updateData(librosPorGenero)
        }
    }

    private fun setupRecyclerView() {
        binding.lstLibrosFiltrados.apply {
            adapter = BookAdapter(Libros(),this@LibrosPorGeneroActivity)
            layoutManager = LinearLayoutManager(this@LibrosPorGeneroActivity)

        }
    }

    override fun onLibroClick(libro: Libro) {
        val intent = Intent(this, DetalleLibroActivity::class.java)
        intent.putExtra("libroId", libro.id)
        startActivity(intent)
    }


}