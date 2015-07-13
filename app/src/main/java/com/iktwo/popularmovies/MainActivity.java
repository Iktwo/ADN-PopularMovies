package com.iktwo.popularmovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DiscoverFragment.OnMovieSelectedListener {
    private boolean mTwoPane;
    private DiscoverFragment mDiscoverFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDiscoverFragment = (DiscoverFragment) getSupportFragmentManager().findFragmentById(R.id.discover_fragment);

        if (findViewById(R.id.frame_layout_details) != null) {
            mTwoPane = true;

            /*FragmentManager fragmentManager = getSupportFragmentManager();
            mTopTracksFragment = new TopTracksFragment();
            fragmentManager.beginTransaction().replace(R.id.frame_layout_details, mTopTracksFragment).commit();

            SearchResultsFragment mSearchResultsFragment = new SearchResultsFragment();
            fragmentManager.beginTransaction().replace(R.id.frame_layout_search, mSearchResultsFragment, SEARCH_RESULTS_FRAGMENT_TAG).commit();*/
        }
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//
//        if (mTwoPane) {
//
//        } else {
//
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sort_by) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle(getString(R.string.action_sort_by));
            String[] types = {getString(R.string.most_popular), getString(R.string.highest_rated)};
            b.setItems(types, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    switch (which) {
                        case 0:
                            mDiscoverFragment.sortBy(getString(R.string.type_most_popular));
                            break;
                        case 1:
                            mDiscoverFragment.sortBy(getString(R.string.type_highest_rated));
                            break;
                    }
                }

            });

            b.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieSelected(DiscoverResultMovie movie) {
        if (mTwoPane) {
            /// TODO: implement this in stage 2
            Toast.makeText(this,
                    movie.title,
                    Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("MOVIE", movie);
            startActivity(intent);
        }
    }
}
