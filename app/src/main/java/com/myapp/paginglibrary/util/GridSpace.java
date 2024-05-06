package com.myapp.paginglibrary.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpace extends RecyclerView.ItemDecoration {

//    NOTE, ITEMDECORATION IS THE CLASS IN THE RECYCLERVIEW CLASS THAT HELPS IN CUSTOMIZATION OF THE LAYOUT
//    LIKE WE'RE DOING HERE, WE CAN DO WHATEVER WE WANT WITH THIS, WITH OUR LAYOUT, BY CALCULATING AND SHIT!
    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpace(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position

//    int column = position % spanCount; calculates the column index of the current item being drawn in the grid. Here,
//    position is the position of the current item in the adapter, and spanCount is the number of columns in the grid layout.
//
//    The % operator in Java returns the remainder of dividing position by spanCount. For example, if spanCount is 3
//    and position is 5, then position % spanCount would be 2 because the remainder of dividing 5 by 3 is 2.
//
//    By calculating the column index of the item, the ItemDecoration can apply the
//    appropriate spacing for the item based on its position within the grid.
        int column = position % spanCount; // item column

//     Rect is a class in Android that represents a rectangle with integer coordinates. In the getItemOffsets() method
//     of RecyclerView.ItemDecoration, the outRect parameter is used to define the offset of the item view's bounds from
//     its parent view. The method modifies the outRect object to add padding/margin to the item view's bounds based on the
//     item's position, column, and the values of the spanCount, spacing, and includeEdge parameters.
//
//     RecyclerView.State is a class in Android that represents the current state of the RecyclerView, such as whether it is
//     currently being scrolled, whether it has focus, etc. The state parameter of getItemOffsets() method is used to get the
//     current state of the RecyclerView.

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }

//        if (includeEdge) checks if the includeEdge parameter is true, meaning that spacing should be included at the
//        edges of the grid.
//outRect.left and outRect.right are the left and right margins for each item. If includeEdge is true, then the margin
// values are calculated to include spacing at the edges. If includeEdge is false, then the margin values are calculated to
// exclude spacing at the edges.
//column is the column number of the item in the grid, calculated as the remainder of the position divided by the spanCount.
// This is used to determine the left and right margins of the item.
//(column + 1) * spacing / spanCount calculates the right margin for the item. If includeEdge is true, then the value
// is calculated to include spacing at the edge. If includeEdge is false, then the value is calculated to exclude spacing
// at the edge.
//spacing - column * spacing / spanCount calculates the left margin for the item. If includeEdge is true, then the value
// is calculated to include spacing at the edge. If includeEdge is false, then the value is calculated to exclude spacing
// at the edge.
//outRect.top and outRect.bottom are the top and bottom margins for each item. If includeEdge is true, then the top and
// bottom margins are set to spacing, meaning that spacing is included at the top and bottom of the grid. If includeEdge
// is false, then the top and bottom margins are only set for items that are not in the first row of the grid.
//
//
//
//Bhavesh Chimnani
//if (includeEdge) checks if the includeEdge parameter is true, meaning that spacing should be included at the edges of the
// grid. how and who checks that ?
//In the code you provided earlier, the includeEdge parameter is passed as an argument to the GridSpacingItemDecoration
// constructor. When you call the constructor and pass true as the includeEdge parameter, it means that you want to include
// spacing at the edges of the grid.
//
//In the GridSpacingItemDecoration class, the getItemOffsets() method checks the value of includeEdge to determine whether
// or not to include spacing at the edges of the grid. If includeEdge is true, the method calculates and adds spacing to
// the outRect object for each item in the grid. If includeEdge is false, the method calculates and adds spacing to the
// outRect object for each item in the grid, except for the items at the edges.
//
//
//
//
//Bhavesh Chimnani
//What do you margins of the each item?
//The outRect variable is used to specify the margins for each item in the RecyclerView. The left, top, right, and bottom
// fields of the outRect variable specify the number of pixels to add as margin on the corresponding side of the item.
//
//For example, if we want to add a 16 pixel margin to the left and right sides of each item, we can set outRect.left = 16
// and outRect.right = 16. Similarly, if we want to add a 8 pixel margin to the top and bottom sides of each item, we can
// set outRect.top = 8 and outRect.bottom = 8.
//
//These margins are used by the LayoutManager to position each item in the RecyclerView.
//
//
//
//
//Bhavesh Chimnani
//margin is like the spacing ?
//Margin is similar to spacing but it is the distance between the edges of a view and its parent container or between
// adjacent views in a layout. Margins can be used to create space between views, add padding to a layout, or adjust
// the position of a view within its parent.
//
//Spacing, on the other hand, refers to the distance between the content of a view and its edges, or between adjacent
// views in a layout. It is usually used to create visual separation between views, and can be set using padding or
// by adding empty space between views.
    }
}

