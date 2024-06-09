package com.example.cuartopracticomoviles.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cuartopracticomoviles.R
import com.example.cuartopracticomoviles.databinding.ActivityEditarGeneroBinding
import com.example.cuartopracticomoviles.databinding.ActivityEditarLibroBinding
import com.example.cuartopracticomoviles.databinding.ActivityGeneroListBinding
import com.example.cuartopracticomoviles.ui.viewmodels.EditarGeneroViewModel
import com.example.cuartopracticomoviles.ui.viewmodels.EditarLibroViewModel

class EditarGeneroActivity : AppCompatActivity() {
    private var idGenero: Int = 0
    private lateinit var binding: ActivityEditarGeneroBinding
    private val model: EditarGeneroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditarGeneroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupViewModelObservers()
        idGenero = intent.getIntExtra("generoId", -1)
        if (idGenero != -1) {
            model.loadGenero(idGenero)
        } else {
            Log.d("EditarGeneroActivity", "idGenero is -1")
        }
    }
    private fun setupViewModelObservers() {
        model.closeActivity.observe(this) {
            if (it) {
                finish()
            }
        }
        model.genero.observe(this) { genero ->
            if (genero == null) {
                Log.d("EditarGeneroActivity", "Genero es null")
                return@observe
            }
            Log.d("EditarGeneroActivity", "Genero: $genero")
            binding.txtEditarNombreGenero.setText(genero.nombre)
        }
    }
    private fun setupEventListeners() {
        binding.btnGuardarGenero.setOnClickListener {
            val idGenero = intent.getIntExtra("generoId", -1)
            if (idGenero != -1) {
                model.guardarGeneroEditado(
                    idGenero,
                    binding.txtEditarNombreGenero.text.toString()
                )
            }
        }
        /*binding.btnGuardarCambiosLibro.setOnClickListener {
            model.guardarGeneroEditado(
                idGenero,
                binding.txtEditarNombreGenero.text.toString()
            )
        }*/
    }



}