package com.example.cuartopracticomoviles.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuartopracticomoviles.R
import com.example.cuartopracticomoviles.databinding.ActivityAgregarLibroAgeneroBinding
import com.example.cuartopracticomoviles.models.Genero
import com.example.cuartopracticomoviles.models.Generos
import com.example.cuartopracticomoviles.models.Pivot
import com.example.cuartopracticomoviles.ui.adapters.BookGenreAdapter
import com.example.cuartopracticomoviles.ui.adapters.GenreAdapter
import com.example.cuartopracticomoviles.ui.viewmodels.AgregarLibroAGeneroViewModel

class AgregarLibroAGeneroActivity : AppCompatActivity(), BookGenreAdapter.OnBookGenreClickListener {
    private var idLibro: Int = 0
    private lateinit var binding: ActivityAgregarLibroAgeneroBinding
    private val model: AgregarLibroAGeneroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAgregarLibroAgeneroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        idLibro = intent.getIntExtra("libroId", -1)
        if (idLibro != -1) {
            model.fetchNoGenerosLibro(idLibro)
        }
        setupViewModelObservers()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.lstGenerosNoLibro.apply {
            adapter = BookGenreAdapter(Generos(), this@AgregarLibroAGeneroActivity)
            layoutManager = LinearLayoutManager(this@AgregarLibroAGeneroActivity)
        }
    }

    private fun setupViewModelObservers() {
        model.generosNoLibro.observe(this) {generosNoLibro ->
            if (generosNoLibro == null) {
                return@observe
            }
            val adapter = (binding.lstGenerosNoLibro.adapter as BookGenreAdapter)
            adapter.updateData(generosNoLibro)
            /*Log.d("LibrosPorGeneroActivity", "Libros filtrados: $librosPorGenero")
            val adapter = (binding.lstLibrosFiltrados.adapter as BookAdapter)
            adapter.updateData(librosPorGenero)*/
        }
        model.closeActivity.observe(this) {
            if (it) {
                finish()
            }
        }
    }

    override fun onGeneroBookClick(genero: Genero) {
        model.agregarLibroAGenero(idLibro, genero.id)

    }


}