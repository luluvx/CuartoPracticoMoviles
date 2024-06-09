package com.example.cuartopracticomoviles.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuartopracticomoviles.databinding.GeneroListItemBinding
import com.example.cuartopracticomoviles.models.Genero
import com.example.cuartopracticomoviles.models.Generos

class GenreAdapter(
    private val generosList: Generos,
    private val listener: OnGeneroClickListener
): RecyclerView.Adapter<GenreAdapter.GeneroViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneroViewHolder {
        val binding = GeneroListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GeneroViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return generosList.size
    }

    override fun onBindViewHolder(holder: GeneroViewHolder, position: Int) {
        val genero = generosList[position]
        holder.bind(genero, listener)
    }

    fun updateData(generosList: Generos) {
        this.generosList.clear()
        this.generosList.addAll(generosList)
        notifyDataSetChanged()

    }

    class GeneroViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(genero: Genero, listener: OnGeneroClickListener) {
            val binding = GeneroListItemBinding.bind(itemView)
            binding.apply {
                lblNombreGenero.text = genero.nombre
                btnEditarGenero.setOnClickListener {
                    listener.onEditarGeneroClick(genero)
                }
                btnEliminarGenero.setOnClickListener {
                    listener.onEliminarGeneroClick(genero)
                }
                root.setOnClickListener {
                    listener.onGeneroClick(genero)
                }
            }
        }
    }


    interface OnGeneroClickListener {
        fun onGeneroClick(genero: Genero)
        fun onEditarGeneroClick(genero: Genero)
        fun onEliminarGeneroClick(genero: Genero)
    }
}