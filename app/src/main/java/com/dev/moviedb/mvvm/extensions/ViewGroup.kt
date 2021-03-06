package com.dev.moviedb.mvvm.extensions

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import petegabriel.com.yamda.R



/**
 * This file contains the various extension methods for the class @see(ViewGroup) to
 * provide a certain utility throughout this application.
 *
 * Yamda 1.0.0.
 */


/**
 *
 * Extension method to help inflate a certain view for a given parent view.
 *
 * Note that @LayoutRes denotes that an integer parameter,
 * field or method return value is expected to be a layout resource reference.
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.slideOutToLeft(context: Context){
    runSimpleAnimation(context, this, R.anim.slide_to_left)
}

private fun runSimpleAnimation(context: Context, view: View, animationId: Int) {
    view.startAnimation(AnimationUtils.loadAnimation(context, animationId))
}