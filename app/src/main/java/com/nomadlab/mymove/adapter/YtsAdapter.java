package com.nomadlab.mymove.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nomadlab.mymove.R;
import com.nomadlab.mymove.repository.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class YtsAdapter extends RecyclerView.Adapter<YtsAdapter.MyViewHolder> {

    List<Movie> list = new ArrayList<>();

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView posterIv;
        private TextView titleTv;
        private TextView ratingTv;
        private RatingBar ratingBar;

        public MyViewHolder(View movieItemView) {
            super(movieItemView);
            posterIv = movieItemView.findViewById(R.id.posterIv);
            titleTv = movieItemView.findViewById(R.id.titleTv);
            ratingTv = movieItemView.findViewById(R.id.ratingTv);
            ratingBar = movieItemView.findViewById(R.id.ratingBar);
        }

        public void setItem(Movie movie) {
            titleTv.setText(movie.getTitle());
            ratingTv.setText(movie.getRating() + "");
            Picasso.with(posterIv.getContext())
                    .load(movie.getMediumCoverImage())
                    .placeholder(R.drawable.round_image)
                    .into(posterIv);
            Log.d("TAG", "movie.getRating() : " + movie.getRating());
            Log.d("TAG", "movie.getRating() / 2 : " + Math.round(movie.getRating() / 2));

            ratingBar.setRating((float) Math.floor(movie.getRating()));
        }
    }

    public void addItem(Movie movie) {
        list.add(movie);
    }

    public void addItems(List<Movie> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public YtsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View movieItemView = inflater.inflate(R.layout.item_card, viewGroup, false);

        return new MyViewHolder(movieItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull YtsAdapter.MyViewHolder myViewHolder, int i) {
        Movie movie = list.get(i);
        myViewHolder.setItem(movie);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
