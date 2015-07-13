package com.iktwo.popularmovies;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DetailsFragment extends Fragment {
    private static final String ARG_MOVIE = "MOVIE";
    private DiscoverResultMovie mMovie;
    private TextView mTextViewTitle;
    private ImageView mImageViewThumbnail;
    private TextView mTextViewReleaseDate;
    private TextView mTextViewVoteAverage;
    private TextView mTextViewSynopsis;

    private OnFragmentInteractionListener mListener;

    public DetailsFragment() {
    }

    public static DetailsFragment newInstance(DiscoverResultMovie movie) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    public void setMovie(DiscoverResultMovie movie) {
        mMovie = movie;

        mTextViewTitle.setText(mMovie.title);
        mTextViewReleaseDate.setText(mMovie.release_date);
        mTextViewVoteAverage.setText(Double.toString(mMovie.vote_average));
        mTextViewSynopsis.setText(mMovie.overview);

        Glide.with(getActivity())
                .load(Urls.IMAGES_URL + mMovie.poster_path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder_movie_poster)
                .centerCrop()
                .into(mImageViewThumbnail);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovie = getArguments().getParcelable(ARG_MOVIE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        mTextViewTitle = (TextView) view.findViewById(R.id.text_view_title);
        mTextViewReleaseDate = (TextView) view.findViewById(R.id.text_view_release_date);
        mTextViewVoteAverage = (TextView) view.findViewById(R.id.text_view_vote_average);
        mTextViewSynopsis = (TextView) view.findViewById(R.id.text_view_synopsis);
        mImageViewThumbnail = (ImageView) view.findViewById(R.id.image_view_thumbnail);

        if (mMovie != null) {
            mTextViewTitle.setText(mMovie.title);
            mTextViewReleaseDate.setText(mMovie.release_date);
            mTextViewVoteAverage.setText(Double.toString(mMovie.vote_average));
            mTextViewSynopsis.setText(mMovie.overview);

            Glide.with(getActivity())
                    .load(Urls.IMAGES_URL + mMovie.poster_path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.placeholder_movie_poster)
                    .centerCrop()
                    .into(mImageViewThumbnail);
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
