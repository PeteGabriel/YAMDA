package com.example.ricardopeixe.moviedb.storage.repo.messenger;

import com.example.ricardopeixe.moviedb.YamdaApplication;
import com.example.ricardopeixe.moviedb.fragment.options.AbstractSearchOptionsListFragment;
import com.example.ricardopeixe.moviedb.model.Movie;
import com.example.ricardopeixe.moviedb.storage.repo.Repository;
import com.example.ricardopeixe.moviedb.storage.repo.db.DataProvider;
import com.example.ricardopeixe.moviedb.utils.ServicesUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.List;

/**
 * The broadcast receiver that listens for messages sent after results get ready to be handled.
 * It listens for messages that imply the search for a list of movies currently in theaters is done.
 *
 * @version 0.0.2
 */
public class FetchInTheatersMoviesResultsReceiver extends BroadcastReceiver {

    /**
     * The action accepted by this receiver.
     */
    public static final String IN_THEATERS_M_DATA_ACTION = "action.intheaters.movies.list";

    /**
     * The key used by this receiver
     */
    public static final String IN_THEATERS_LIST_DATA_KEY = "repo.intheaters.list.data";


    public FetchInTheatersMoviesResultsReceiver() { }

    @Override
    public void onReceive(Context context, Intent intent) {
        List<Movie> inTheatersList = intent.getParcelableArrayListExtra(IN_THEATERS_LIST_DATA_KEY);
        YamdaApplication app =((YamdaApplication) context.getApplicationContext());

        //try to insert
        Uri uri = Repository.makeUri(DataProvider.THEATERS_TABLE_NAME);
        app.getRepository()
                .insertSetOfMovies(uri, inTheatersList);

        //alert for data available
        context.sendBroadcast(new Intent(AbstractSearchOptionsListFragment.DATA_AVAILABLE_ACTION));

        //ask for details when possible
        for (Movie m : inTheatersList) {
            ServicesUtils.askForDetails(app, m);
        }
    }
}
