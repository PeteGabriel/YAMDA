package com.dev.moviedb.mvvm.components.movies_tab


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import petegabriel.com.yamda.R


/**
 * A simple [Fragment] subclass.
 */
class MoviesInfoFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_movies_info, container, false)
    }

}// Required empty public constructor