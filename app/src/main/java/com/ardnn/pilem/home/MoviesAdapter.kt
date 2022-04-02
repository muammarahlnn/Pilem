package com.ardnn.pilem.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ardnn.pilem.core.domain.model.Movie
import com.ardnn.pilem.core.util.Helper
import com.ardnn.pilem.databinding.ItemMovieBinding

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
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


    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

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
}