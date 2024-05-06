package com.myapp.paginglibrary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.paginglibrary.R;
import com.myapp.paginglibrary.databinding.LoadStateItemBinding;

public class MoviesLoadStateAdapter extends LoadStateAdapter<MoviesLoadStateAdapter.LoadStateViewHolder> {

    private View.OnClickListener mRetryCallback;

    public MoviesLoadStateAdapter(View.OnClickListener mRetryCallback){
        this.mRetryCallback = mRetryCallback;
    }

    @Override
    public void onBindViewHolder(@NonNull LoadStateViewHolder loadStateViewHolder, @NonNull LoadState loadState) {
        loadStateViewHolder.bind(loadState);
    }

    @NonNull
    @Override
    public LoadStateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull LoadState loadState) {
        return new LoadStateViewHolder(parent, mRetryCallback);
    }
    public class LoadStateViewHolder extends RecyclerView.ViewHolder {

        private TextView mError;
        private ProgressBar mProgressBar;
        private Button mRetry;

        public LoadStateViewHolder(@NonNull ViewGroup parent,
                                   @NonNull View.OnClickListener retryCallback) {
            super(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.load_state_item,
                    parent,
                    false));

            LoadStateItemBinding binding = LoadStateItemBinding.bind(itemView);
            mProgressBar = binding.progressBar;
            mError = binding.errorMsg;
            mRetry = binding.retryButton;
            mRetry.setOnClickListener(retryCallback);
        }

        public void bind(LoadState loadState) {
            if (loadState instanceof LoadState.Error) {
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                mError.setText(loadStateError.getError().getLocalizedMessage());
            }

//   If the LoadState is an instance of LoadState.Error, it means that an error occurred during the data loading process.
//   The bind() method extracts the error from the LoadState object and sets the localized error message to the mError
//   TextView.
//

//The visibility of the progress bar (mProgressBar) is determined based on whether the LoadState is an instance of
// LoadState.Loading. If it is, the progress bar is set to View.VISIBLE, indicating that the data is currently being loaded.
// Otherwise, it is set to View.GONE, hiding the progress bar.
//

//The visibility of the retry button (mRetry) and error message (mError) views is controlled based on whether the
// LoadState is an instance of LoadState.Error. If it is, both the retry button and error message views are set to
// View.VISIBLE, indicating that there was an error and giving the user the option to retry. Otherwise, they are set to
// View.GONE, hiding them.

            mProgressBar.setVisibility(
                    loadState instanceof LoadState.Loading ?View.VISIBLE :View.GONE);

            mRetry.setVisibility(
                    loadState instanceof LoadState.Error ?View.VISIBLE :View.GONE);

            mError.setVisibility(
                    loadState instanceof LoadState.Error ?View.VISIBLE :View.GONE);

        }
    }
}
