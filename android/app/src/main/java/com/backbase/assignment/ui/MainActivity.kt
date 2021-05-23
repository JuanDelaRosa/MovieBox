package com.backbase.assignment.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.R
import com.backbase.assignment.app.MovieboxApp
import com.backbase.assignment.databinding.ActivityMainBinding
import com.backbase.assignment.databinding.FragmentDetailBinding
import com.backbase.assignment.ui.movie.GenreAdapter
import com.backbase.assignment.ui.movie.MoviesAdapter
import com.backbase.assignment.ui.movie.PosterAdapter
import com.backbase.assignment.ui.movie.ScrollListener
import com.backbase.domain.entities.Movie


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainActivityViewModel by lazy { MainActivityViewModel.MainActivityViewModelFactory((application as MovieboxApp)).create(MainActivityViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.nowplayingRV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.popular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.popular.adapter = MoviesAdapter{
            showDetail(it)
        }
        binding.nowplayingRV.adapter = PosterAdapter{
            showDetail(it)
        }
        (binding.popular.adapter as MoviesAdapter).vm = viewModel
        (binding.nowplayingRV.adapter as PosterAdapter).vm = viewModel
        binding.popular.addOnScrollListener(ScrollListener(binding.popular,viewModel))
        viewModel.getMostPopular()
        viewModel.popularMovies.observe(this, { movies ->
            movies?.let {
                    (binding.popular.adapter as MoviesAdapter).setData(it)
                }
        })
        viewModel.getPlayingNow()
        viewModel.nowPlaying.observe( this, { movies ->
            movies?.let {
                (binding.nowplayingRV.adapter as PosterAdapter).setData(it)
            }
        })

        viewModel.dataLoading.observe(this, {
            binding.pbLoading.visibility = if(it==true){
                 View.VISIBLE
            }else
                View.GONE
        })

        viewModel.error.observe(this, {
            Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
        })
    }
    private fun showDetail(movie : Movie){
        movie.let {
            val dialog = Dialog(this)
            val fragmentBinding = FragmentDetailBinding.bind(layoutInflater.inflate(R.layout.fragment_detail, null))
            fragmentBinding.movie = movie
            fragmentBinding.lifecycleOwner = this
            dialog.window?.let {
                it.requestFeature(Window.FEATURE_NO_TITLE)
                it.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
                fragmentBinding.back.setOnClickListener {
                    dialog.dismiss()
                }
                fragmentBinding.genre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                fragmentBinding.genre.adapter = GenreAdapter()
                (fragmentBinding.genre.adapter as GenreAdapter).setData(movie.genre)
                viewModel.usePicasso(fragmentBinding.poster, movie.imageUrl("w500"))
                dialog.setContentView(fragmentBinding.root)
                it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()
            }
        }
    }
}
