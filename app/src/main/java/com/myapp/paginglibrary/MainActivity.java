package com.myapp.paginglibrary;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.RequestManager;
import com.myapp.paginglibrary.adapter.MovieAdapter;
import com.myapp.paginglibrary.adapter.MoviesLoadStateAdapter;
import com.myapp.paginglibrary.databinding.ActivityMainBinding;
import com.myapp.paginglibrary.util.GridSpace;
import com.myapp.paginglibrary.util.MovieComprator;
import com.myapp.paginglibrary.util.Util;
import com.myapp.paginglibrary.viewmodel.MovieViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    MovieViewModel viewModel;
    ActivityMainBinding binding;
    MovieAdapter movieAdapter;

    @Inject
    RequestManager requestManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Util.API_KEY == null || Util.API_KEY.isEmpty()){
            Toast.makeText(this, "Error in API Key", Toast.LENGTH_SHORT).show();
        }

        movieAdapter = new MovieAdapter(new MovieComprator(), requestManager);

        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        initRecyclerviewAndAdapter();

        // subscribe to paging data
        viewModel.moviePagingDataFlowable.subscribe(moviePagingData-> {
            movieAdapter.submitData(getLifecycle(), moviePagingData);
//            this code basically updates the adapter by giving it the new data through movie paging data.
//         Like this! "Hey, I have new data for you, I'm going to give it to you through the moviePagingData parameter.
//         Please update yourself accordingly."
    });
}
    private void initRecyclerviewAndAdapter() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        binding.recyclerViewMovies.setLayoutManager(gridLayoutManager);

        binding.recyclerViewMovies.addItemDecoration(new GridSpace(2, 20, true));

        binding.recyclerViewMovies.setAdapter(
                movieAdapter.withLoadStateFooter(
                        new MoviesLoadStateAdapter(view -> {
                            movieAdapter.retry();
                        })
                )
        );

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return movieAdapter.getItemViewType(position) == MovieAdapter.LOADING_ITEM ? 1 : 2;
            }
        });
    }

}
