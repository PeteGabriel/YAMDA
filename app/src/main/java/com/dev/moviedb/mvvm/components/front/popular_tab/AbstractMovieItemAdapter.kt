package com.dev.moviedb.mvvm.components.front.popular_tab

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import com.dev.moviedb.model.dto.MovieCollectionDto

/**
 * An abstract class that helps reusing behavior between adapters.
 *
 * Yamda 1.0.0.
 */
abstract class AbstractMovieItemAdapter<T : ViewHolder?>: RecyclerView.Adapter<T>(){

    protected var movies: MovieCollectionDto? = null

    fun adNewData(movies: MovieCollectionDto){
        this.movies = movies
    }

    override fun getItemCount(): Int = if (movies == null) 0 else movies?.results?.size!!

}