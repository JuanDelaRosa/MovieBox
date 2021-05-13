package com.backbase.assignment.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.databinding.GenreItemBinding
import com.backbase.domain.entities.Genre

class GenreAdapter(): RecyclerView.Adapter<GenreAdapter.SearchViewHolder>() {

    var genreList: List<Genre> = emptyList()
    lateinit var binding : GenreItemBinding

    fun setData(list: List<Genre>?){
        genreList = list ?: emptyList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding = GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val genre = genreList[position]
        holder.binding.gender.text = genre.name
    }

    class SearchViewHolder(val binding: GenreItemBinding): RecyclerView.ViewHolder(binding.root)
}