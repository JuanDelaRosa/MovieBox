package com.backbase.assignment.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.app.MovieboxApp
import com.backbase.assignment.databinding.ActivityMainBinding
import com.backbase.assignment.ui.movie.MoviesAdapter
import com.backbase.assignment.ui.movie.PosterAdapter
import com.backbase.assignment.ui.movie.ScrollListener
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainActivityViewModel by lazy { MainActivityViewModel.MainActivityViewModelFactory((application as MovieboxApp)).create(MainActivityViewModel::class.java) }
    private var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.nowplayingRV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.popular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.popular.adapter = MoviesAdapter{
            /*val intent = Intent(this, PokeinfoActivity::class.java)
            intent.putExtra("id",it)
            startActivity(intent)*/
            Timber.d(it.toString())
        }
        binding.nowplayingRV.adapter = PosterAdapter{
            /*val intent = Intent(this, PokeinfoActivity::class.java)
            intent.putExtra("id",it)
            startActivity(intent)*/
            Timber.d(it.toString())
        }
        (binding.popular.adapter as MoviesAdapter).vm = viewModel
        (binding.nowplayingRV.adapter as PosterAdapter).vm = viewModel
        binding.popular.addOnScrollListener(ScrollListener(binding.popular,viewModel))
        viewModel.getMostPopular()
        viewModel.popularMovies.observe(this, { movies ->
            movies.let {
                    (binding.popular.adapter as MoviesAdapter).setData(it)
                }
        })
        viewModel.getPlayingNow()
        viewModel.nowPlaying.observe( this, { movies ->
            movies.let {
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
            Toast.makeText(this, "Ocurrio un error: ${it!!}", Toast.LENGTH_SHORT).show()
        })
    }
}
