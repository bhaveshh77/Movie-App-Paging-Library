package com.myapp.paginglibrary.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.myapp.paginglibrary.models.Movie;
import com.myapp.paginglibrary.paging.MoviePagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MovieViewModel extends ViewModel {

    public Flowable<PagingData<Movie>> moviePagingDataFlowable;

//  Flowable is used to represent a stream of data that can be observed and consumed by subscribers. It is similar to
//  LiveData in that it provides an asynchronous and reactive approach to data handling. Flowable is capable of handling
//  backpressure, which means it can control the rate at which data is emitted to downstream consumers, ensuring that they
//  can handle the data effectively without being overwhelmed.
//
//PagingData, on the other hand, is a specific data structure provided by the Paging 3 library for handling paginated data.
// It represents a particular page of data, including the list of items on that page and associated metadata. PagingData
// works in conjunction with the PagingSource and PagingDataAdapter to facilitate efficient loading and displaying of large
// datasets in a paginated manner. It manages the loading of data from network or database sources, handles data updates and
// invalidation, and provides a smooth scrolling experience by loading data incrementally as needed.
//
//So, Flowable is used to observe and handle the stream of paginated data represented by PagingData, allowing for efficient
// and reactive data loading and consumption while maintaining control over backpressure.

    public MovieViewModel() {
        init();
    }

    private void init() {

        // Define Paging Source
        MoviePagingSource moviePagingSource = new MoviePagingSource();

//  The Pager class in the Paging library is responsible for creating a PagingData stream based on a PagingSource and a
//  PagingConfig.
        Pager<Integer, Movie> pager = new Pager(
                new PagingConfig(
// PagingConfig: This is the configuration object that defines various properties of the paging functionality, such as
// page size, initial load size, placeholders, prefetch distance, and maximum size. The PagingConfig instance created earlier
// is passed as a parameter.

                        20,
                        20,
                        false,
                        20,
                        20 * 499
                ),
                () -> moviePagingSource);

        // Flowable
        moviePagingDataFlowable = PagingRx.getFlowable(pager);
//      IN SIMPLER TERMS, PagingRx.getFlowable(pager) combines the pager and flowable.


//     The line moviePagingDataFlowable = PagingRx.getFlowable(pager); creates a "stream" of data called moviePagingDataFlowable. This stream emits objects of type PagingData<Movie>.
//
//Think of moviePagingDataFlowable as a continuous flow of data that represents a collection of movies. As new movies become available or existing movies change, the stream emits updated collections of movies.
//
//To make this happen, we use the Pager object named pager. It's responsible for managing the loading and handling of the movie data. It takes a configuration (PagingConfig) that specifies how many items to load at a time, how to handle placeholders and errors, and other settings. Additionally, it needs a PagingSource object, which knows how to load data from a data source (e.g., network, database).
//
//The PagingRx.getFlowable(pager) call combines the pager and the data stream (Flowable) together. It creates a connection between the Pager and the Flowable, so that whenever the Pager loads or updates data, the Flowable emits the updated collection of movies as PagingData<Movie> objects.
//
//In simpler terms, it's like setting up a pipeline where the Pager fetches and manages the data, and the Flowable listens to the changes and emits the updated data collections for you to consume and display in your app.

        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);

//        Above, we're using Coroutines, Coroutines are the advanced versions of the CallBacks that we used previously.
//        It can manage data in a more structured way.! So, we're using that. And we've used ViewModelScope, to ensure that
//        the coroutines are launched within the ViewModel scope to avoid the memory leak and stuff.

        PagingRx.cachedIn(moviePagingDataFlowable, coroutineScope);

//So, whatever data flowable gives to this method, it'll return the cache object, so that it doesn't need to retrieve data
// continuously by fetching from the api??

//        The PagingRx.cachedIn() function returns a new Flowable that wraps the original moviePagingDataFlowable and adds
//        caching functionality to it. This means that subsequent subscriptions to the returned Flowable will receive data
//        from the cache instead of triggering new data loading operations.
//
//In summary, the Flowable (moviePagingDataFlowable) receives data from the Pager object, and the PagingRx.cachedIn()
// function is used to create a new Flowable with caching capabilities. The caching is performed within the provided
// CoroutineScope (coroutineScope), ensuring proper lifecycle management and coroutine cancellation.

    }
}