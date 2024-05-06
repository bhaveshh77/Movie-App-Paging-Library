package com.myapp.paginglibrary.api;


import static com.myapp.paginglibrary.util.Util.API_KEY;
import static com.myapp.paginglibrary.util.Util.BASE_URL;

import com.myapp.paginglibrary.models.Results;

import io.reactivex.rxjava3.core.Single;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class APIClient {

    public static ApiInterface apiInterface;

//    // Retrofit Instance
    public static ApiInterface getApiInterface() {

        if(apiInterface == null) {
            OkHttpClient.Builder client = new OkHttpClient.Builder();

//            Here interceptor is used to add the api key, interceptors can be used to add a lot of stuff in the request.


////
//   Interceptors in Android are used to intercept and modify network requests and responses made by the application.
//   They provide a way to customize and add functionality to the network communication layer. Here are some reasons why and
//   how interceptors are used in Android:
//
//   Authentication: Interceptors can be used to add authentication headers or tokens to outgoing requests. This is commonly
//   used when working with APIs that require authentication or authorization.
//
//   Request Modification: Interceptors allow you to modify requests before they are sent. You can add query parameters,
//   headers, or modify the URL of the request based on specific conditions or requirements.
//
//   Response Modification: Interceptors can modify the response received from the server before it is passed to the
//   application. This can be useful for handling specific response formats, error handling, or adding additional data to
//   the response.
//
//   Logging and Debugging: Interceptors are often used for logging network requests and responses for debugging purposes.
//   They can log information such as the URL, headers, request body, and response body, helping developers troubleshoot
//   network-related issues.
//
//   Caching: Interceptors can be used to implement caching mechanisms, allowing responses to be cached and served from
//   the cache instead of making a network request for subsequent identical requests.
//
//   Error Handling and Retries: Interceptors can handle common errors or exceptions that occur during network communication
//   and provide mechanisms for retrying requests under certain conditions.

            client.addInterceptor(chain -> {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();
                HttpUrl url = originalHttpUrl.newBuilder().
                        addQueryParameter("api_key", API_KEY).
                        build();

                Request.Builder requestBuilder = original.newBuilder().
                        url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            });

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();

            apiInterface = retrofit.create(ApiInterface.class);
        }

        return apiInterface;

    }

    public interface ApiInterface {

        @GET("movie/popular")
        Single<Results> getMoviesByPage(@Query("page") int page);

//
//In the API endpoint @GET("movie/popular"), the @Query("page") int page represents a query parameter named "page".
// It allows you to specify the page number when making the API request to retrieve a specific page of movie data.
//
//   The @Query("page") annotation indicates that the value of the page parameter will be appended to the URL as a
//   query parameter. For example, if you pass page = 1, the resulting URL will be something like
//   https://api.example.com/movie/popular?page=1.
//
//   By specifying the page number as a query parameter, you can retrieve different pages of movie data from the API.
//   This is commonly used in paginated APIs, where the data is divided into multiple pages to improve performance and
//    reduce the amount of data transferred in a single request.


    }
}
