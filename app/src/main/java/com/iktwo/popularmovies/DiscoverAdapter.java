package com.iktwo.popularmovies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Comparator;
import java.util.List;

public class DiscoverAdapter extends ArrayAdapter<DiscoverResultMovie> {
    private static final String TAG = DiscoverAdapter.class.getSimpleName();

    public static Comparator<DiscoverResultMovie> MostPopularComparator
            = new Comparator<DiscoverResultMovie>() {

        public int compare(DiscoverResultMovie movie1, DiscoverResultMovie movie2) {
            return (int) (movie2.vote_count - movie1.vote_count);
        }
    };

    public static Comparator<DiscoverResultMovie> HighestRatedComparator
            = new Comparator<DiscoverResultMovie>() {

        public int compare(DiscoverResultMovie movie1, DiscoverResultMovie movie2) {
            return (int) (movie2.vote_average - movie1.vote_average);
        }
    };

    public DiscoverAdapter(Activity context, List<DiscoverResultMovie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final DiscoverResultMovie item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_delegate, parent, false);

            holder = new ViewHolder();
            holder.thumbnail = (ImageView) convertView.findViewById(R.id.image_view_thumbnail);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (item.poster_path != null) {
            String url = Urls.IMAGES_URL + item.poster_path;

            Glide.with(getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.placeholder_movie_poster)
                    .centerCrop()
                    .into(holder.thumbnail);
        } else {
            Glide.with(getContext())
                    .load(R.drawable.placeholder_movie_poster)
                    .into(holder.thumbnail);
        }

        return convertView;
    }

    public void sortBy(String type) {
        if (type.equals(getContext().getString(R.string.type_most_popular))) {
            sort(MostPopularComparator);
            notifyDataSetChanged();
        } else if (type.equals(getContext().getString(R.string.type_highest_rated))) {
            sort(HighestRatedComparator);
            notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        public ImageView thumbnail;
    }
}
