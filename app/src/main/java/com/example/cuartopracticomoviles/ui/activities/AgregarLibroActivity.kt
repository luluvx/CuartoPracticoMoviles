package com.example.cuartopracticomoviles.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cuartopracticomoviles.R
import com.example.cuartopracticomoviles.databinding.ActivityAgregarLibroBinding
import com.example.cuartopracticomoviles.models.Libro
import com.example.cuartopracticomoviles.ui.viewmodels.AgregarLibroViewModel

class AgregarLibroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAgregarLibroBinding
    private val model: AgregarLibroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAgregarLibroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupViewModelObservers()

    }

    private fun setupViewModelObservers() {
        model.closeActivity.observe(this) {
            if (it) {
                finish()
            }
        }
    }

    private fun setupEventListeners() {
        binding.btnFormAgregarLibro.setOnClickListener{
            val nombreLibro = binding.txtNombreLibro.text.toString()
            val autorLibro = binding.txtNombreAutor.text.toString()
            val editorialLibro = binding.txtEditorialLibro.text.toString()
            val imagenLibro = binding.txtImagenLibro.text.toString()
            val sinopsisLibro = binding.txtSinopsisLibro.text.toString()
            val isbnLibro = binding.txtIsbnLibro.text.toString()
            val calificacionLibro = binding.txtCalificacionLibro.text.toString().toInt()


            val libroGuardar = Libro(
                nombre = nombreLibro,
                autor = autorLibro,
                editorial = editorialLibro,
                imagen = imagenLibro,
                sinopsis = sinopsisLibro,
                isbn = isbnLibro,
                calificacion = calificacionLibro
            )
            model.guardarLibro(libroGuardar)
        }
    }
}