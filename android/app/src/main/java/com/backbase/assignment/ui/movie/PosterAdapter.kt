package com.backbase.assignment.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.databinding.PosterItemBinding
import com.backbase.assignment.ui.MainActivityViewModel
import com.backbase.domain.entities.Movie

class PosterAdapter(val movieClick: (Movie)-> Unit): RecyclerView.Adapter<PosterAdapter.SearchViewHolder>() {

    var movieList: List<Movie> = emptyList()
    lateinit var binding : PosterItemBinding
    lateinit var vm : MainActivityViewModel

    fun setData(list: List<Movie>?){
        movieList = list ?: emptyList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding = PosterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val movie = movieList[position]
        vm.UsePicasso(holder.binding.poster,movie.imageUrl())
        holder.binding.root.setOnClickListener{ movieClick(movie)}
    }

    class SearchViewHolder(val binding: PosterItemBinding): RecyclerView.ViewHolder(binding.root)
}