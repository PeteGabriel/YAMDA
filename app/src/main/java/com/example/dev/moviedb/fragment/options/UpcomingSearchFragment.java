package com.example.dev.moviedb.fragment.options;

import com.example.dev.moviedb.YamdaApplication;
import com.example.dev.moviedb.storage.repo.Repository;
import com.example.dev.moviedb.storage.repo.db.DataProvider;
import com.example.dev.moviedb.storage.repo.db.DataUnit;
import com.example.dev.moviedb.views.adapters.UpcomingItemListAdapter;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A {@link android.support.v4.app.ListFragment} that displays the information
 * related to the search of the movies that are soon to be released in theaters accordingly
 * to the TMDB api.
 *
 * Using the ViewPager design with FragmentPagerAdapter means the fragments are created when the
 * ViewPager
 * is instantiated and stay in memory. What if the fragment can't or does not need to be recreated,
 * but needs to be updated anyway?
 * Updates to a living fragment are best handled by the fragment itself.
 * That's the advantage of having a fragment, after all it is its own controller.
 * A fragment can add a listener or an observer to another object in onCreate(),
 * and then remove it in onDestroy(), thus managing the updates itself.
 *
 * @version 0.0.2
 */
public class UpcomingSearchFragment extends AbstractSearchOptionsListFragment {

  /*
  What to do when new data arrives into the storage
   */
  public void onNewData() {
    Log.d(TAG, "OnNewData invoked.");
    Uri uri = Repository.makeUri(DataProvider.UPCOMING_TABLE_NAME);
    ((YamdaApplication) getActivity().getApplication()).getRepository()
        .getSetOfRecords(uri, populateListAdapterCallback(getActivity()));
  }

  /**
   * Variable used as key to save/obtain state information.
   */
  public static final String SAVE_ITEM_LIST_KEY = "UpcomingSearchFragment.item.key";

  @Override protected void setupListAdapter(Bundle state) {
    mItems = (state != null) ? state.getParcelableArrayList(SAVE_ITEM_LIST_KEY)
        : new ArrayList<>();
    setListAdapter(new UpcomingItemListAdapter(getActivity().getApplication(), mItems));
  }

  /** {@inheritDoc} */
  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    // Save the current article selection in case we need to recreate the fragment
    outState.putParcelableArrayList(SAVE_ITEM_LIST_KEY, (ArrayList<? extends Parcelable>) mItems);
  }

  /** {@inheritDoc} */
  @Override public void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    Log.d(TAG, "Item clicked at position #" + position);

    //notify subscriber
    if (mClickedItemCallback != null) {
      mClickedItemCallback.onItemSelected(DataUnit.Tables.UPCOMING_TABLE,
              mItems.get(position));
    }
  }
}