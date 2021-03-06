package com.dev.moviedb.mvvm.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.dev.moviedb.mvvm.extensions.formatMovieCardName
import com.dev.moviedb.mvvm.extensions.inflate
import com.dev.moviedb.mvvm.extensions.loadPosterUrl
import com.dev.moviedb.mvvm.repository.remote.dto.MovieDTO
import kotlinx.android.synthetic.main.item_movie_generic_layout.view.*
import petegabriel.com.yamda.R

/**
 *
 * Generic adapter used to display a movie item
 *
 * Yamda 1.1.0.
 */
class MovieDisplayAdapter(private var onItemClick: (MovieDTO) -> Unit = {}) : AbstractMovieItemAdapter<MovieDisplayAdapter.MovieViewHolder>() {


    override fun onBindViewHolder(vHolder: MovieViewHolder, position: Int) {
        vHolder.bind(movies?.get(position), onItemClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MovieViewHolder =
            MovieViewHolder(parent.inflate(R.layout.item_movie_generic_layout))


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MovieDTO?, onItemClick: (MovieDTO) -> Unit) = with(itemView) {
            //dto from tv shows and imageList use different props for the name
            if (item?.title?.isNotEmpty()!!){
                movieTitleTxtView.text = item.title?.formatMovieCardName()
            }else if (item.name?.isNotEmpty()!!){
                movieTitleTxtView.text = item.name?.formatMovieCardName()
            }

            itemImageFrame.loadPosterUrl(item.posterPath!!)
            movieRatingValueTextView.text = "%.1f".format(item.voteAverage)

            itemView.setOnClickListener { onItemClick(item) }
        }
    }

}