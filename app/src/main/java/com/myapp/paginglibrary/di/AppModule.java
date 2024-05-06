package com.myapp.paginglibrary.di;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.myapp.paginglibrary.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module

// The @Module annotation is used in Dagger, a popular dependency injection framework, to mark a class as a module.
// Modules are responsible for providing dependencies to the application.

//  By annotating a class with @Module, you indicate that it contains methods (@Provides methods) that contribute to
//  the object graph of your application. These methods define how to create and provide instances of certain classes
//  or interfaces.

@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public RequestManager getGlide(@ApplicationContext Context context) {

//        WE ARE MAKING IT MORE SPECIFIC AND CUSTOM!

//   By defining a @Provides method for a specific type, such as RequestManager, you are instructing the
//   dependency injection framework to create an instance of that type with the specified configuration or
//   dependencies. This instance can then be injected into other classes or components that need it.

        return Glide.with(context)
                .applyDefaultRequestOptions(new RequestOptions()
//                .error(R.drawable.ic_image)
                .placeholder(R.drawable.ic_image));
    }
}
