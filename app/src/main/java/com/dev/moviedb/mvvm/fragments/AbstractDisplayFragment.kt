package com.dev.moviedb.mvvm.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dev.moviedb.mvvm.adapters.AbstractMovieItemAdapter
import com.dev.moviedb.mvvm.adapters.MovieDisplayAdapter
import com.dev.moviedb.mvvm.extensions.prependCallLocation
import com.dev.moviedb.mvvm.repository.remote.dto.MovieDTO
import kotlinx.android.synthetic.main.include_item_spotlight.*
import petegabriel.com.yamda.R
import timber.log.Timber

/**
 * This class contains common behavior used to display
 * lists of movies/series.
 *
 * Yamda 1.0.0.
 */
abstract class AbstractDisplayFragment : Fragment() {


    /**
     * A reference to the RecyclerView widget used to display the most
     * popular movies.
     */
    protected var popularRecyclerView: RecyclerView? = null

    /**
     * A reference to the RecyclerView widget used to display the most
     * top rated movies.
     */
    protected var topRatedRecyclerView: RecyclerView? = null

    /**
     * A reference to the RecyclerView widget used to display a list of movies in theaters.
     */
    protected var nowPlayingRecyclerView: RecyclerView? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_movies_tab_layout, container, false)

        configFirstRecViewAdapter(view)
        configSecondRecViewAdapter(view)
        configThirdRecViewAdapter(view)

        return view
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!showSpotlightWidget()){
            item_spotlight_widget_frame.visibility = View.GONE
        }
    }


    /**
     * Send data to the popular movies adapter
     */
    protected fun addNewDataToFirstAdapter(): (List<MovieDTO>?) -> Unit {
        return { col: List<MovieDTO>? ->
            (popularRecyclerView?.adapter as AbstractMovieItemAdapter).adNewData(col)
            (popularRecyclerView?.adapter as AbstractMovieItemAdapter).notifyDataSetChanged()
        }
    }

    /**
     * Send data to the Top Rated movies adapter
     */
    protected fun addNewDataToSecondAdapter(): (List<MovieDTO>?) -> Unit {
        return { col: List<MovieDTO>? ->
            (topRatedRecyclerView?.adapter as AbstractMovieItemAdapter).adNewData(col)
            (topRatedRecyclerView?.adapter as AbstractMovieItemAdapter).notifyDataSetChanged()
        }
    }

    protected fun addNewDataToThirdAdapter(): (List<MovieDTO>?) -> Unit {
        return { col: List<MovieDTO>? ->
            (nowPlayingRecyclerView?.adapter as AbstractMovieItemAdapter).adNewData(col)
            (nowPlayingRecyclerView?.adapter as AbstractMovieItemAdapter).notifyDataSetChanged()
        }
    }

    /**
     * Handle error after requesting data from ViewModel
     */
    protected fun handleError(throwable: Throwable) = Timber.d(getLoggingTag(), throwable.message!!.toString().prependCallLocation())


    abstract fun getLoggingTag(): String

    open fun getFirstCardTitle(): String =  getString(R.string.popular_card_title)
    open fun getSecondCardTitle(): String =  getString(R.string.toprated_card_title)
    open fun getThirdCardTitle(): String =  getString(R.string.incinemas_card_title)


    open fun showSpotlightWidget() = true

    /**
     * Configuration of the recycler view's adapter
     */
    protected open fun configFirstRecViewAdapter(view: View) {
        val tempView: View = view.findViewById(R.id.firstCardView)
        tempView.findViewById<TextView>(R.id.cardviewDescriptionText).text = getFirstCardTitle()
        popularRecyclerView = tempView.findViewById(R.id.movieListRecyclerView)

        popularRecyclerView?.setHasFixedSize(true)
        popularRecyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        popularRecyclerView?.adapter = MovieDisplayAdapter()
        popularRecyclerView?.itemAnimator = DefaultItemAnimator()
    }
    /**
     * Configuration of the recycler view's adapter
     */
    protected open fun configSecondRecViewAdapter(view: View) {
        val tempView = view.findViewById<View>(R.id.secondCardView)
        tempView.findViewById<TextView>(R.id.cardviewDescriptionText).text = getSecondCardTitle()
        topRatedRecyclerView = tempView.findViewById(R.id.movieListRecyclerView)

        topRatedRecyclerView?.setHasFixedSize(true)
        topRatedRecyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        topRatedRecyclerView?.adapter = MovieDisplayAdapter()
        topRatedRecyclerView?.itemAnimator = DefaultItemAnimator()
    }

    /**
     * Configuration of the recycler view's adapter
     */
    protected open fun configThirdRecViewAdapter(view: View) {
        val tempView = view.findViewById<View>(R.id.thirdCardView)
        tempView.findViewById<TextView>(R.id.cardviewDescriptionText).text = getThirdCardTitle()
        nowPlayingRecyclerView = tempView.findViewById(R.id.movieListRecyclerView)

        nowPlayingRecyclerView?.setHasFixedSize(true)
        nowPlayingRecyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        nowPlayingRecyclerView?.adapter = MovieDisplayAdapter()
        nowPlayingRecyclerView?.itemAnimator = DefaultItemAnimator()
    }
}