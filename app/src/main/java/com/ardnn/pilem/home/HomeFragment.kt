package com.ardnn.pilem.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ardnn.pilem.core.data.Resource
import com.ardnn.pilem.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val adapter = MoviesAdapter()
            adapter.onItemClick = { selectedData ->
                val toMovieDetail = HomeFragmentDirections
                    .actionHomeFragmentToMovieDetailFragment(selectedData)
                findNavController().navigate(toMovieDetail)
            }

            viewModel.movies.observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            adapter.setData(movies.data)
                        }
                        is Resource.Error -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Oops something went wrong.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding?.rvMovie) {
                this?.layoutManager = GridLayoutManager(context, 2)
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