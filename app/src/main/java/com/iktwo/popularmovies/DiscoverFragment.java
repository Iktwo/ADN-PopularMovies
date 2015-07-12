package com.iktwo.popularmovies;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DiscoverFragment extends Fragment implements HttpAsyncRequest.AsyncResponse {
    private static final String TAG = DiscoverFragment.class.getSimpleName();

    private DiscoverAdapter discoverAdapter;

    private OnMovieSelectedListener mListener;

    private GridView gridView;
    private ProgressBar busyIndicator;

    private List<DiscoverResultMovie> mMovies;

    public DiscoverFragment() {
    }

    @Override
    public void processFinish(ArrayList<String> reply) {
        if (reply != null && !reply.isEmpty() && reply.get(0).equals(Urls.DISCOVER_URL)) {
            final DiscoverResultsMovies discoverResults = new Gson().fromJson(reply.get(1), DiscoverResultsMovies.class);

            mMovies = discoverResults.results;
            gridView.setAdapter(new DiscoverAdapter(getActivity(), mMovies));

            busyIndicator.setVisibility(View.GONE);
        } else {
            busyIndicator.setVisibility(View.GONE);

            Toast.makeText(getActivity(),
                    R.string.error_getting_movies,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mMovies == null)
            new HttpAsyncRequest(this).execute(Urls.DISCOVER_URL);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        gridView = (GridView) view.findViewById(R.id.grid_view);

        busyIndicator = (ProgressBar) view.findViewById(R.id.busy_indicator);

        if (mMovies != null) {
            gridView.setAdapter(new DiscoverAdapter(getActivity(), mMovies));
            busyIndicator.setVisibility(View.GONE);
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMovie((DiscoverResultMovie) gridView.getAdapter().getItem(i));
            }
        });

        return view;
    }

    public void selectedMovie(DiscoverResultMovie movie) {
        if (mListener != null) {
            mListener.onMovieSelected(movie);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMovieSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMovieSelectedListener {
        public void onMovieSelected(DiscoverResultMovie movie);
    }

    private class DiscoverResultsMovies {
        public List<DiscoverResultMovie> results = new ArrayList<>();
    }
}
