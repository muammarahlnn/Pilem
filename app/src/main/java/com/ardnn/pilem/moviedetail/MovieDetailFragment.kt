package com.ardnn.pilem.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.ardnn.pilem.R
import com.ardnn.pilem.core.domain.model.Movie
import com.ardnn.pilem.core.util.Helper
import com.ardnn.pilem.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by viewModels()

    private var _binding: FragmentMovieDetailBinding? = null

    private val binding get() = _binding

    private var isSynopsisExtended = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            // get movie detail and show it
            val movie = MovieDetailFragmentArgs.fromBundle(arguments as Bundle).movie
            showMovieDetail(movie)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showMovieDetail(movie: Movie) {
        binding?.run {
            // set images
            Helper.setImageGlide(
                requireContext(),
                Helper.getPosterTMDB(movie.posterUrl),
                ivPoster
            )
            Helper.setImageGlide(
                requireContext(),
                Helper.getWallpaperTMDB(movie.wallpaperUrl),
                ivWallpaper
            )

            // set detail
            tvTitle.text = Helper.setTextString(movie.title)
            tvReleaseDate.text = Helper.setTextDate(movie.releaseDate)
            tvRating.text = Helper.setTextFloat(movie.rating)
            tvSynopsis.text = Helper.setTextString(movie.overview)

            // click listeners
            // button back
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            // button favorite
            var isFavorite = movie.isFavorite
            setButtonFavorite(isFavorite)
            btnFavorite.setOnClickListener {
                isFavorite = !isFavorite
                viewModel.setFavoriteMovie(movie, isFavorite)
                setButtonFavorite(isFavorite)

                if (isFavorite) {
                    Toast.makeText(
                        context,
                        "${movie.title} has added to favorite movies",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "${movie.title} has removed from favorite movies",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            // container synopsis
            containerSynopsis.setOnClickListener {
                isSynopsisExtended = !isSynopsisExtended
                if (isSynopsisExtended) {
                    binding?.tvSynopsis?.maxLines = Int.MAX_VALUE
                    binding?.tvMore?.text = getString(R.string.less)
                } else {
                    binding?.tvSynopsis?.maxLines = 2
                    binding?.tvMore?.text = getString(R.string.more)
                }
            }
        }

    }

    private fun setButtonFavorite(isFavorite: Boolean) {
        binding?.btnFavorite?.setImageDrawable(
            if (isFavorite) ContextCompat.getDrawable(requireActivity(), R.drawable.ic_favorite_true)
            else ContextCompat.getDrawable(requireActivity(), R.drawable.ic_favorite_false)
        )
    }

}