package com.example.cuartopracticomoviles.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cuartopracticomoviles.R
import com.example.cuartopracticomoviles.databinding.ActivityEditarLibroBinding
import com.example.cuartopracticomoviles.ui.viewmodels.EditarLibroViewModel

class EditarLibroActivity : AppCompatActivity() {
    private var idLibro: Int = 0
    private lateinit var binding: ActivityEditarLibroBinding
    private val model: EditarLibroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditarLibroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupViewModelObservers()

        idLibro = intent.getIntExtra("libroId", -1)
        if (idLibro != -1) {
            model.loadLibro(idLibro)
        }


    }

    /*override fun onResume() {
        super.onResume()
        model.loadLibro(idLibro)

    }*/

    private fun setupEventListeners() {
        binding.btnGuardarCambiosLibro.setOnClickListener {
            val idLibro = intent.getIntExtra("libroId", -1)
            if (idLibro != -1) {
                model.guardarLibroEditado(
                    idLibro,
                    binding.txtEditarNombreLibro.text.toString(),
                    binding.txtEditarNombreAutor.text.toString(),
                    binding.txtEditarEditorialLibro.text.toString(),
                    binding.txtEditarImagenLibro.text.toString(),
                    binding.txtEditarSinopsisLibro.text.toString(),
                    binding.txtEditarIsbnLibro.text.toString(),
                    binding.txtEditarCalificacionLibro.text.toString().toInt()
                )
            }
        }
    }

    private fun setupViewModelObservers() {
        model.closeActivity.observe(this) {
            if (it) {
                finish()
            }
        }
        model.libro.observe(this) {libro ->
            if (libro == null) {
                return@observe
            }
            binding.txtEditarNombreLibro.setText(libro.nombre)
            binding.txtEditarNombreAutor.setText(libro.autor)
            binding.txtEditarEditorialLibro.setText(libro.editorial)
            binding.txtEditarImagenLibro.setText(libro.imagen)
            binding.txtEditarSinopsisLibro.setText(libro.sinopsis)
            binding.txtEditarIsbnLibro.setText(libro.isbn)
            binding.txtEditarCalificacionLibro.setText(libro.calificacion.toString())

        }
    }
}