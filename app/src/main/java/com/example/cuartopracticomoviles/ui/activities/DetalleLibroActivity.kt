package com.example.cuartopracticomoviles.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.cuartopracticomoviles.R
import com.example.cuartopracticomoviles.databinding.ActivityDetalleLibroBinding
import com.example.cuartopracticomoviles.models.Libro
import com.example.cuartopracticomoviles.ui.viewmodels.DetalleLibroViewModel

class DetalleLibroActivity : AppCompatActivity() {
    private var idLibro: Int = 0
    private lateinit var binding: ActivityDetalleLibroBinding
    private val model: DetalleLibroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalleLibroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        idLibro = intent.getIntExtra("libroId", -1)
        if (idLibro != -1) {
            model.loadLibro(idLibro)
        }
        setupEventListeners()
        setupViewModelObservers()

    }

    override fun onResume() {
        super.onResume()
        model.loadLibro(idLibro)
    }

    private fun setupEventListeners() {
        binding.btnEditarLibro.setOnClickListener {
            val intent = Intent(this, EditarLibroActivity::class.java)
            intent.putExtra("libroId", idLibro)
            startActivity(intent)

        }
        binding.btnEliminarLibro.setOnClickListener {
            model.eliminarLibro(idLibro)
            finish()
        }
        binding.btnAgregarLibroAgenero.setOnClickListener {
            val intent = Intent(this, AgregarLibroAGeneroActivity::class.java)
            intent.putExtra("libroId", idLibro)
            startActivity(intent)
        }
    }


    private fun setupViewModelObservers() {
        model.libroObtenido.observe(this) {libroObtenido ->
            if (libroObtenido == null) {
                return@observe
            }
            cargarLibro(libroObtenido)

        }

    }

    private fun cargarLibro(libroObtenido: Libro) {
        binding.apply {
            lblShowNombreLibro.text = libroObtenido.nombre
            lblShowAutorLibro.text = libroObtenido.autor
            lblShowEditorialLibro.text = libroObtenido.editorial
            lblShowCalificacionLibro.text = libroObtenido.calificacion.toString()
            lblShowIsbnLibro.text = libroObtenido.isbn
            lblShowGeneros.text = libroObtenido.generos?.joinToString(", "){ it.nombre}
            lblShowSinopsisLibro.text = libroObtenido.sinopsis
            Glide.with(this@DetalleLibroActivity)
                .load(libroObtenido.imagen)
                .into(imgShowImagenLibro)
        }
    }
}