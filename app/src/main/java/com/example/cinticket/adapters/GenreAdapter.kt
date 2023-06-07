package com.example.cinticket.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinticket.Service
import com.example.cinticket.databinding.GenreModelBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GenreAdapter(private val navController: NavController,private val service:Service,private val context: Context) :
    RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    var genres: List<String> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int = genres.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GenreModelBinding.inflate(inflater, parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genres[position]
        with(holder.binding) {
            moviesGenre.text = genre
            val movieAdapter = MovieAdapter(navController)
            GlobalScope.launch(Dispatchers.IO) {
                val movies = service.getMovieOfGenre(genre)
                launch(Dispatchers.Main) {
                    if (movies != null) {
                        movieAdapter.movies=movies
                    }
                }
            }
            val layoutManagerMovies =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerGenre.layoutManager = layoutManagerMovies
            recyclerGenre.adapter = movieAdapter
        }
    }


    class GenreViewHolder(
        val binding: GenreModelBinding
    ) : RecyclerView.ViewHolder(binding.root)

}