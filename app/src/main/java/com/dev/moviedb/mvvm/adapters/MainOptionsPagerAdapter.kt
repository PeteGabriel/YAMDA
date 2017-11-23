package com.dev.moviedb.mvvm.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dev.moviedb.mvvm.fragments.MoviesInfoFragment
import com.dev.moviedb.mvvm.movies_tab.MoviesTabFragment
import com.dev.moviedb.mvvm.fragments.TvSeriesTabFragment
import com.dev.moviedb.mvvm.fragments.UserAccountInfoFragment

/**
 * The adapter used to resolve the fragments that
 * the user can navigate through.
 *
 * @see{FragmentPagerAdapter}
 *
 * @author PeteGabriel on 12/10/2017
 * Copyright (c) 2017
 * All rights reserved.
 */
class MainOptionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    //The amount fo fragments managed by this adapter.
    private val TOTAL_FRAGS = 4


    override fun getItem(position: Int): Fragment =
            when(position){
                0 -> MoviesTabFragment()
                1 -> MoviesInfoFragment()
                2 -> TvSeriesTabFragment()
                3 -> UserAccountInfoFragment()
                else -> MoviesInfoFragment()
            }

    override fun getCount(): Int = TOTAL_FRAGS



}