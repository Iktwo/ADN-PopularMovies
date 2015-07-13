package com.iktwo.popularmovies;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class DetailsActivity extends AppCompatActivity implements DetailsFragment.OnFragmentInteractionListener {
    private static final String TAG = DetailsActivity.class.getSimpleName();
    private DiscoverResultMovie mMovie;
    private DetailsFragment mDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mDetailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.details_fragment);

        if (getIntent() != null) {
            mMovie = getIntent().getParcelableExtra("MOVIE");
            mDetailsFragment.setMovie(mMovie);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
