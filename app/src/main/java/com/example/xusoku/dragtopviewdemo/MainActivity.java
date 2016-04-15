package com.example.xusoku.dragtopviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.xusoku.dragtopviewdemo.fragments.GridViewFragment;
import com.example.xusoku.dragtopviewdemo.fragments.ListViewFragment;
import com.example.xusoku.dragtopviewdemo.fragments.RecyclerFragment;
import com.example.xusoku.dragtopviewdemo.fragments.ScrollViewFragment;
import com.example.xusoku.dragtopviewdemo.fragments.WebViewFragment;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;

public class MainActivity extends AppCompatActivity {

    private DragTopLayout dragLayout;
    private ModelPagerAdapter adapter;
    private ViewPager viewPager;
    private PagerSlidingTabStrip pagerSlidingTabStrip;

    private ImageView topImageView;

    NestedScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dragLayout = (DragTopLayout) findViewById(R.id.drag_layout);
        topImageView = (ImageView) findViewById(R.id.image_view);
        pagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);



        PagerModelManager factory = new PagerModelManager();
        factory.addCommonFragment(getFragments(), getTitles());
        adapter = new ModelPagerAdapter(getSupportFragmentManager(), factory);
        viewPager.setAdapter(adapter);
        pagerSlidingTabStrip.setViewPager(viewPager);


        dragLayout.setOverDrag(false)
                .listener(new DragTopLayout.SimplePanelListener() {
                    @Override
                    public void onSliding(float ratio) {
                        super.onSliding(ratio);
                    }

                });
//
        scrollView = (NestedScrollView) findViewById(R.id.top_view);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.top_linear);

        // Scroll view does not have scroll listener
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                EventBus.getDefault().post(AttachUtil.isMyScrollViewAttach(scrollView));

                return false;
            }
        });
    }

    private List<String> getTitles(){
        List<String> list=new ArrayList<>();
        list.add("ListView");
        list.add("RecyclerView");
        list.add( "GridView");
        list.add( "ScrollView");
        list.add("WebView");
        return list;
    }

    private List<Fragment> getFragments(){
        List<Fragment> list = new ArrayList<>();
        Fragment listFragment = new ListViewFragment();
        Fragment recyclerFragment = new RecyclerFragment();
        Fragment gridViewFragment = new GridViewFragment();
        Fragment scrollViewFragment = new ScrollViewFragment();
        Fragment webViewFragment = new WebViewFragment();
        list.add(listFragment);
        list.add(recyclerFragment);
        list.add(gridViewFragment);
        list.add(scrollViewFragment);
        list.add(webViewFragment);
        return list;
    }




    // Handle scroll event from fragments
    public void onEvent(Boolean b){
//        Log.e("vvvvv",""+b);
        dragLayout.setTouchMode(b);

    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_menu_icon) {
//            if(topImageView.getVisibility() == View.GONE){
//                topImageView.setVisibility(View.VISIBLE);
//            }else{
//                topImageView.setVisibility(View.GONE);
//            }
//            return true;
//        } else if(id == R.id.action_toggle){
//            dragLayout.toggleTopView();
//            return true;
//        } else if(id == R.id.action_over_drag){
//            dragLayout.setOverDrag(!dragLayout.isOverDrag());
//            Toast.makeText(this, "overDrag:" + dragLayout.isOverDrag(), Toast.LENGTH_SHORT).show();
//            return true;
//        } else if(id == R.id.action_offset){
//            if (dragLayout.getCollapseOffset() == 0){
//                dragLayout.openTopView(true);
//                dragLayout.setCollapseOffset(200);
//            } else {
//                dragLayout.setCollapseOffset(0);
//            }
//            Toast.makeText(this, "offset:" + dragLayout.getCollapseOffset(), Toast.LENGTH_SHORT).show();
//            return true;
//        } else if(id == R.id.action_pulltorefresh){
//            Intent intent = new Intent(this, PullToRefreshActivity.class);
//            startActivity(intent);
//            return true;
//        } else if(id == R.id.action_about){
//            Intent intent = new Intent(this, AboutActivity.class);
//            startActivity(intent);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
