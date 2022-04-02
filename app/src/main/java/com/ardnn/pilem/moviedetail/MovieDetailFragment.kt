package com.ardnn.pilem.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ardnn.pilem.R
import com.ardnn.pilem.core.domain.model.Movie
import com.ardnn.pilem.core.util.Helper
import com.ardnn.pilem.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(), View.OnClickListener {

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

            // click listeners
            binding?.btnBack?.setOnClickListener(this)
            binding?.containerSynopsis?.setOnClickListener(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_back -> requireActivity().onBackPressed()
            R.id.container_synopsis -> {
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
        }
    }

}