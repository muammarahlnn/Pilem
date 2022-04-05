package com.ardnn.pilem.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ardnn.pilem.core.domain.model.Movie
import com.ardnn.pilem.core.util.Helper
import com.ardnn.pilem.databinding.ItemMovieBinding

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>(), Filterable {

    private val listDataFull = ArrayList<Movie>() // to save all movies

    private val listData = ArrayList<Movie>() // to save current query movies

    var onItemClick: ((Movie) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        listDataFull.addAll(listData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.onBind(data)
    }

    override fun getItemCount(): Int = listData.size


    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

        fun onBind(movie: Movie) {
            with(binding) {
                Helper.setImageGlide(
                    itemView.context,
                    Helper.getPosterTMDB(movie.posterUrl),
                    ivPoster
                )
                tvTitle.text = Helper.setTextString(movie.title)
                tvYear.text = Helper.setTextYear(movie.releaseDate)
                tvRating.text = Helper.setTextFloat(movie.rating)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constrain: CharSequence?): FilterResults {
                val filteredList = ArrayList<Movie>()
                if (constrain.isNullOrEmpty()) {
                    filteredList.addAll(listDataFull)
                } else {
                    val query = constrain.toString().trim()
                    for (movie in listDataFull) {
                        val title = movie.title
                        val year = Helper.setTextYear(movie.releaseDate)
                        if (title.startsWith(query, true) || year.startsWith(query, true)) {
                            filteredList.add(movie)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constrain: CharSequence?, results: FilterResults?) {
                listData.clear()
                listData.addAll(results?.values as List<Movie>)
                notifyDataSetChanged()
            }
        }
    }
}