package com.backbase.assignment.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.databinding.MovieItemBinding
import com.backbase.assignment.ui.MainActivityViewModel
import com.backbase.domain.entities.Movie


class MoviesAdapter(val movieClick: (Movie)-> Unit): RecyclerView.Adapter<MoviesAdapter.SearchViewHolder>() {

    var movieList: ArrayList<Movie> = arrayListOf()
    lateinit var binding : MovieItemBinding
    lateinit var vm : MainActivityViewModel

    fun setData(list: List<Movie>?){
        if (movieList.isNullOrEmpty() && list!!.isNotEmpty())
            movieList = list as ArrayList<Movie>
        else if(!list.isNullOrEmpty()) {
            list.forEach {
                if(!movieList.contains(it))
                    movieList.add(it)
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val movie = movieList[position]
        val context = holder.binding.root.context
        vm.usePicasso(holder.binding.poster,movie.imageUrl())
        holder.binding.title.text = movie.TitleFix()
        holder.binding.releaseDate.text = movie.ReleaseDateFix()
        holder.binding.runtime.text = movie.DurationFix()
        val ranking = (movie.voteAverage*10).toInt()
        if(ranking<50)
            holder.binding.progressBar.progressDrawable = ContextCompat.getDrawable(context,R.drawable.circle_yellow)
        else
            holder.binding.progressBar.progressDrawable = ContextCompat.getDrawable(context,R.drawable.circle)
        holder.binding.progressBar.progress = ranking
        val rankingS = "${ranking}%"
        holder.binding.textViewProgress.text = rankingS
        holder.binding.root.setOnClickListener{ movieClick(movie)}
    }
    class SearchViewHolder(val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root)
}