package com.backbase.assignment.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.databinding.MovieItemBinding
import com.backbase.assignment.ui.MainActivityViewModel
import com.backbase.domain.entities.Movie


class MoviesAdapter(val movieClick: (Int)-> Unit): RecyclerView.Adapter<MoviesAdapter.SearchViewHolder>() {

    var movieList: ArrayList<Movie> = arrayListOf()
    lateinit var binding : MovieItemBinding
    lateinit var vm : MainActivityViewModel

    fun setData(list: List<Movie>?){
        if (movieList.isNullOrEmpty())
            movieList = list as ArrayList<Movie>
        else if(!list.isNullOrEmpty())
            movieList.addAll(list)
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
        vm.UsePicasso(holder.binding.poster,movie.posterPath)
        holder.binding.title.text = TitleFix(movie.title)
        holder.binding.releaseDate.text = ReleaseDateFix(movie.releaseDate)
        holder.binding.runtime.text = DurationFix(movie.runtime)
        val ranking = (movie.voteAverage*10).toInt()
        if(ranking<50)
            holder.binding.progressBar.progressDrawable = holder.binding.root.resources.getDrawable(R.drawable.circle_yellow)
        else
            holder.binding.progressBar.progressDrawable = holder.binding.root.resources.getDrawable(R.drawable.circle)
        holder.binding.progressBar.progress = ranking
        holder.binding.textViewProgress.text = "$ranking%"
        holder.binding.root.setOnClickListener{ movieClick(position + 1)}
    }

    private fun TitleFix(title: String): String {
        return if(title.length >28){
            title.substring(0,25)+"..."
        }else title
    }
    private fun DurationFix(runtime : Int?) : String {
        return if(runtime!=null && runtime>0){
            val horas : Int = runtime/60
            val min : Int = runtime - horas*60
            "${horas}h ${min}m"
        }else "-"
    }
    private fun ReleaseDateFix(d: String): String {
        if (!d.contains("-"))
            return "-"
        val dates = d.split("-")
        if(dates.count()!=3)
            return "-"
        val month = when (dates[1]) {
            "01" -> "January"
            "02" -> "February"
            "03" -> "March"
            "04" -> "April"
            "05" -> "May"
            "06" -> "June"
            "07" -> "July"
            "08" -> "August"
            "09" -> "September"
            "10" -> "October"
            "11" -> "November"
            "12" -> "December"
            else -> ""
        }
        return "$month ${dates[2]}, ${dates[0]}"
    }

    class SearchViewHolder(val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root)
}