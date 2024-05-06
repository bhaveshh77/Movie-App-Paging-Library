package com.myapp.paginglibrary.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.myapp.paginglibrary.models.Movie;

public class MovieComprator extends DiffUtil.ItemCallback<Movie> {
    @Override
    public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return oldItem.getId() == newItem.getId();
    }

//    This is a class called MovieComparator that extends DiffUtil.ItemCallback<Movie>. DiffUtil is a utility class
//    provided by the Android Jetpack Paging library that can compare two lists and calculate the difference between them.
//    MovieComparator is used to compare two Movie objects in a list to determine if they are the same item and if their
//    content has changed.
//
//The areItemsTheSame() method compares the unique identifiers of two Movie objects to check if they represent the same item.
// In this case, it checks if the id of the old and new item are the same.
//
//The areContentsTheSame() method compares the content of two Movie objects to check if they are the same. In this case,
// it also checks if the id of the old and new item are the same.
//
//This class is used in the PagingDataAdapter in the paging library app. The PagingDataAdapter uses the MovieComparator to
// compare items in the old and new lists to determine if any items have been added, removed, or changed. This helps the
// PagingDataAdapter efficiently update the RecyclerView with the new data.

//    The MovieComparator class extends the DiffUtil.ItemCallback class, which is a utility class provided by Android to
//    compute the difference between two lists using DiffUtil.
//
//The areItemsTheSame method is used to check if the two items are the same item. In the case of MovieComparator, it
// compares the id field of the Movie object. If the id of the old item and the new item is the same, it returns true.
// If not, it returns false.
//
//The areContentsTheSame method is used to check if the two items have the same content. In the case of MovieComparator,
// it also compares the id field of the Movie object. If the id of the old item and the new item is the same, it returns true.
// If not, it returns false.
//
//In the paging library app, MovieComparator is used as a parameter in the constructor of the PagingDataAdapter to compute
// the difference between the old list and the new list of movies. This helps to optimize the performance of the RecyclerView
// by minimizing the number of items that need to be re-drawn when there is a change in the list.
    @Override
    public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
