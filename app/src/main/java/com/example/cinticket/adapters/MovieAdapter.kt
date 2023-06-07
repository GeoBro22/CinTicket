package com.example.cinticket.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinticket.R
import com.example.cinticket.databinding.MovieModelBinding
import com.example.cinticket.entities.Movie
import com.example.cinticket.post.Appdata

class MovieAdapter(private val navController: NavController) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movies: List<Movie> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieModelBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        with(holder.binding) {
            mvname.text = movie.moviename
            genre.text = movie.genre
            Glide.with(movieImage.context).load(movie.image).into(movieImage)
            val bundle = Bundle()
            bundle.putLong("movie_id", movie.id)
            bundle.putString("movie_name", movie.moviename)
            bundle.putString("movie_video", movie.youtube)
            bundle.putString("movie_poster", movie.poster)
            bundle.putString("movie_description", movie.description)
            bundle.putString("movie_long", movie.movielong)
            bundle.putBoolean("movie_isnow", movie.isnow)

            holder.itemView.setOnClickListener {
                navController.navigate(
                    R.id.infoMovieFragment,
                    bundle
                )
            }
        }

    }


    class MovieViewHolder(
        val binding: MovieModelBinding
    ) : RecyclerView.ViewHolder(binding.root)
}