package com.ardnn.pilem.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ardnn.pilem.R
import com.ardnn.pilem.core.data.Resource
import com.ardnn.pilem.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding

    private lateinit var adapter: MoviesAdapter

    private var section = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
//            // set action bar
//            (activity as AppCompatActivity).apply {
//                setSupportActionBar(binding?.toolbar?.root)
//                supportActionBar?.setDisplayShowTitleEnabled(false)
//            }

            // set movies adapter
            adapter = MoviesAdapter()
            adapter.onItemClick = { selectedData ->
                val toMovieDetail = HomePagerFragmentDirections
                    .actionHomePagerFragmentToMovieDetailFragment(selectedData)
                findNavController().navigate(toMovieDetail)
            }

            // get section and set in on view model
            section = arguments?.getInt(ARG_SECTION, 0) ?: 0
            Timber.d(section.toString())
            viewModel.setSection(section)
            viewModel.movies.observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> {
                            showProgressBar(true)
                        }
                        is Resource.Success -> {
                            showProgressBar(false)
                            adapter.setData(movies.data)
                        }
                        is Resource.Error -> {
                            showProgressBar(false)
                            showViewError()

                            Timber.d(movies.message.toString())
                            Toast.makeText(context, "Oops something went wrong.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding?.rvMovie) {
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).setSupportActionBar(null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home, menu)

        // set search view
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                adapter.filter.filter(query)
                return false
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                val toFavoriteMovies = HomePagerFragmentDirections
                    .actionHomePagerFragmentToFavoriteFragment()
                findNavController().navigate(toFavoriteMovies)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showProgressBar(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showViewError() {
        binding?.viewError?.root?.visibility = View.VISIBLE
    }

    companion object {
        const val ARG_SECTION = "section"
    }
}