package com.iktwo.popularmovies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class DiscoverAdapter extends ArrayAdapter<DiscoverResultMovie> {
    private static final String TAG = DiscoverAdapter.class.getSimpleName();

    public DiscoverAdapter(Activity context, List<DiscoverResultMovie> songs) {
        super(context, 0, songs);
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
            /// TODO: this is wrong, according to docs this could change, so we need to query api to get this url and cache it
            String url = "http://image.tmdb.org/t/p/w780/" + item.poster_path;

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

    static class ViewHolder {
        public ImageView thumbnail;
    }
}
