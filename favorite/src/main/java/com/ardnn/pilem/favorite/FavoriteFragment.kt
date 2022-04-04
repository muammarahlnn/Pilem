package com.ardnn.pilem.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ardnn.pilem.di.FavoriteModuleDependencies
import com.ardnn.pilem.favorite.databinding.FragmentFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            // set favorite movies adapter
            val adapter = FavoriteMoviesAdapter()
            adapter.onItemClick = { selectedData ->
                val toMovieDetail =
                    FavoriteFragmentDirections.actionFavoriteFragmentToMovieDetailFragment(
                        selectedData
                    )
                findNavController().navigate(toMovieDetail)
            }

            viewModel.favoriteMovies.observe(viewLifecycleOwner, { favoriteMovies ->
                adapter.setData(favoriteMovies)
            })

            with(binding?.rvMovie) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}