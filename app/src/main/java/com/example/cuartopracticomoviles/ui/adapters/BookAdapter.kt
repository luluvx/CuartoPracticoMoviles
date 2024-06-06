package com.example.cuartopracticomoviles.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cuartopracticomoviles.databinding.LibroItemLayoutBinding
import com.example.cuartopracticomoviles.models.Libro
import com.example.cuartopracticomoviles.models.Libros


class BookAdapter(
    val bookList: Libros,
    val listener: OnLibroClickListener

):RecyclerView.Adapter<BookAdapter.LibroViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val binding = LibroItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LibroViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = bookList[position]
        holder.bind(libro, listener)
    }

    fun updateData(booksList: Libros) {
        this.bookList.clear()
        this.bookList.addAll(booksList/*.sortedByDescending { book -> book.rating}*/)
        notifyDataSetChanged()

    }

    class LibroViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(libro: Libro, listener: OnLibroClickListener) {
            val binding = LibroItemLayoutBinding.bind(itemView)
            binding.apply {
                lblNombreLibro.text = libro.nombre
                Glide.with(itemView.context)
                    .load(libro.imagen)
                    .into(imgLibro)
                root.setOnClickListener {
                    listener.onLibroClick(libro)
                }
            }
        }


    }
    interface OnLibroClickListener{
        fun onLibroClick(libro: Libro)
    }
}