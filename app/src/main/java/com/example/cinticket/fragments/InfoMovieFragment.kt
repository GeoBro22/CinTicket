package com.example.cinticket.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.cinticket.R
import com.example.cinticket.databinding.FragmentInfoMovieBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class InfoMovieFragment : Fragment() {

    private lateinit var binding: FragmentInfoMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoMovieBinding.inflate(inflater, container, false)
        val youTubePlayerView = binding.payerYx
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = arguments?.getString("movie_video")
                if (videoId != null) {
                    youTubePlayer.cueVideo(videoId,0f)
                }
            }
        })
        val controller = findNavController()
        Glide.with(binding.poster.context).load( arguments?.getString("movie_poster")).into(binding.poster)
        binding.movieName.text = arguments?.getString("movie_name")
        binding.movieLong.text=arguments?.getString("movie_long")
        binding.description.text = "    "+arguments?.getString("movie_description")
        val bundle = Bundle()
        arguments?.getLong("movie_id")?.let { bundle.putLong("movie_id", it) }
        if (arguments?.getBoolean("movie_isnow") == true)
            binding.buyBtn.setOnClickListener {
                controller.navigate(
                    R.id.sessionDateFragment,bundle
                )
            }
        else
            binding.buyBtn.visibility=GONE
        return binding.root
    }
}