package com.example.cuartopracticomoviles.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuartopracticomoviles.databinding.GeneroBookItemBinding
import com.example.cuartopracticomoviles.models.Genero
import com.example.cuartopracticomoviles.models.Generos

class BookGenreAdapter(
    private val bookGenreList: Generos,
    private val listener: OnBookGenreClickListener
): RecyclerView.Adapter<BookGenreAdapter.BookGenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookGenreViewHolder {
        val binding = GeneroBookItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookGenreViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return bookGenreList.size
    }

    override fun onBindViewHolder(holder: BookGenreViewHolder, position: Int) {
        val bookGenre = bookGenreList[position]
        holder.bind(bookGenre, listener)
    }

    fun updateData(generosNoLibro: Generos) {
        this.bookGenreList.clear()
        this.bookGenreList.addAll(generosNoLibro)
        notifyDataSetChanged()

    }

    class BookGenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(bookGenre: Genero, listener: OnBookGenreClickListener) {
            val binding = GeneroBookItemBinding.bind(itemView)
            binding.apply {
                lblShowGenero.text = bookGenre.nombre
                btnAgregarGeneroALibro.setOnClickListener {
                    listener.onGeneroBookClick(bookGenre)
                }
            }
        }

    }
    interface OnBookGenreClickListener {
        fun onGeneroBookClick(genero: Genero)

    }

}


