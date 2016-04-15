package com.example.xusoku.dragtopviewdemo;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ScrollView;

/**
 * Created by chenupt@gmail.com on 1/30/15.
 * Description : Attach top helper
 */
public class AttachUtil {

    public static boolean isAdapterViewAttach(AbsListView listView){
        if (listView != null && listView.getChildCount() > 0) {
            if (listView.getChildAt(0).getTop() < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isRecyclerViewAttach(RecyclerView recyclerView){
        if (recyclerView != null && recyclerView.getChildCount() > 0) {
            if (recyclerView.getChildAt(0).getTop() < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isScrollViewAttach(ScrollView scrollView){
        if (scrollView != null) {
            if (scrollView.getScrollY() > 0) {
                return false;
            }
        }
        return true;
    }
    public static boolean isMyScrollViewAttach(NestedScrollView scrollView){
        if (scrollView != null) {
//            Log.e("scrollView.getScrollY()","scrollView.getScrollY()="+scrollView.getScrollY()
//                    +"    scrollView.getHeight()="+scrollView.getHeight()
//                    +"    scrollView.computeVerticalScrollRange()="+scrollView.computeVerticalScrollRange()
//                    +"    scrollView.getMeasuredHeight()="+scrollView.getMeasuredHeight());

            if(scrollView.getScrollY() + scrollView.getHeight() >= scrollView.computeVerticalScrollRange())
            {
                return true;
            }
            else
            {
                return false;
            }


        }
        return false;

    }

    public static boolean isWebViewAttach(WebView webView){
        if (webView != null) {
            if (webView.getScrollY() > 0) {
                return false;
            }
        }
        return true;
    }
}
