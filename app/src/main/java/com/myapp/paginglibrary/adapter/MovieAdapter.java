package com.myapp.paginglibrary.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.myapp.paginglibrary.databinding.SingleMovieItemBinding;
import com.myapp.paginglibrary.models.Movie;

public class MovieAdapter extends PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder> {

    public static final int LOADING_ITEM = 0;
    public static final int MOVIE_ITEM = 1;

//    the number applied in these constants are random/arbitrary and it's developers preference.!

    RequestManager glide;
//    Request Manger is the class provided by the glide library, it's used to load the image, and caching it!

    public MovieAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback, RequestManager glide) {
        super(diffCallback);
        this.glide = glide;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(SingleMovieItemBinding.inflate(LayoutInflater.from(
                parent.getContext()),
                parent, false
        ));
    }


    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie currMovie = getItem(position);

        if(currMovie != null) {
            glide.load("https://image.tmdb.org/t/p/original"+currMovie.getPoster_Path())
                    .into(holder.movieItemBinding.imageViewMovie);

            holder.movieItemBinding.textViewRating.setText(String.valueOf(currMovie.getVote_average()));

        }

    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() ? MOVIE_ITEM : LOADING_ITEM;
//        if the position == getItemCount, which means last content, it means that loading item will be
//        shown and if it's not, it means that regular movie item will be shown.
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        SingleMovieItemBinding movieItemBinding;

        public MovieViewHolder(@NonNull SingleMovieItemBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;

        }

    }

}
