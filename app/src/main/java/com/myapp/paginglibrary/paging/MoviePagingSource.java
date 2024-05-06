package com.myapp.paginglibrary.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.myapp.paginglibrary.api.APIClient;
import com.myapp.paginglibrary.models.Movie;
import com.myapp.paginglibrary.models.Results;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MoviePagingSource extends RxPagingSource<Integer, Movie> {
    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Movie> pagingState) {
        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, Movie>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        try{

            int page = loadParams.getKey() != null ? loadParams.getKey() : 1;
//        loadParams is used for the page number here in this method, and page is used here to ensure
//        the current number of the page itself, if the page is null, then it is assigned the number 1 page
//        if the page is not null, then it has got the curr page number!

            return APIClient.getApiInterface()
                    .getMoviesByPage(page)
//                    here, schedulers.io makes sure that the main thread is not interrupted while calling for the
//                    api request in the app. So, it is assigned the dedicated thread for the backend stuff.
                    .subscribeOn(Schedulers.io())

//            .map(Results::getMovies):
//
//This line takes the emitted data, which is assumed to be of type Results, and extracts the list of movies from it.
//It's like saying, "Give me the movies from the Results object."
//.map(movies -> toLoadResult(movies, page)):
//
//This line takes the list of movies from the previous step and applies a transformation to create a LoadResult object.
//It's like saying, "Transform the list of movies into a LoadResult object using a method called toLoadResult()
// and providing the movies and page number."
//.onErrorReturn(LoadResult.Error::new):
//
//This line handles any errors that may occur during the previous steps. If an error occurs, it provides a fallback value.
//It's like saying, "If there is an error, return a new LoadResult.Error object."
//} catch (Exception e) { return Single.just(new LoadResult.Error(e)); }:
//
//This part catches any exceptions that occur in the code block and returns a LoadResult.Error object encapsulating
// the exception.
//It's like saying, "If an exception occurs, create a LoadResult.Error object with the exception and return it."
                    .map(Results::getMovies)
                    .map(movies -> toLoadResult(movies, page))
//
//          here, if it finds the error, it's gonna create the new LoadResult without the error....
                    .onErrorReturn(LoadResult.Error::new);
        } catch (Exception e) {
            return Single.just(new LoadResult.Error(e));
//            And same here, new load result with the error exception...
        }
    }

//

//    Just so you know, In RxJava, the map operator is used to transform the items emitted by an Observable
//    (or other reactive types) by applying a function to each item. It operates on each item individually and emits
//    the transformed item as a result.



    private LoadResult<Integer, Movie> toLoadResult(List<Movie> movies, int page) {
//
//LoadResult.Page is a class that represents a page of loaded data in a paging system. It is typically used in
// conjunction with the Paging library in Android.
//
//The LoadResult.Page class extends the base LoadResult class and adds specific information about a loaded page.
// It contains the loaded data for the page, as well as details about the adjacent pages for pagination purposes
        return new LoadResult.Page(movies, page == 1 ? null : page -1, page +1);
//        return new LoadResult.Page(movies, page == 1 ? null : page -1, page +1);
//This line creates a new instance of LoadResult.Page by passing the following parameters:
//movies: The list of movies representing the data loaded for the current page.
//page == 1 ? null : page - 1: The position of the previous page. If the current page is the
// first page (page number equals 1), it sets the previous page position to null. Otherwise, it subtracts 1 from
// the current page number to get the previous page position.
//page + 1: The position of the next page. It adds 1 to the current page number to get the next page position.
    }

//
}
//    In simpler terms, the toLoadResult method takes a list of movies and a page number. It creates a LoadResult.Page
//  object using the movies as the loaded data and determines the positions of the previous and next pages based on
//  the given page number. The result is a LoadResult object that represents a page of loaded movies with pagination
//  information.