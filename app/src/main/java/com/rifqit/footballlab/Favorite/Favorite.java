package com.rifqit.footballlab.Favorite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rifqit.footballlab.AdapterTabLayout;
import com.rifqit.footballlab.Favorite.LastMatchFavorite.FragmentLastMatch;
import com.rifqit.footballlab.Favorite.NextMatchFavorite.FragmentNextMatch;
import com.rifqit.footballlab.Favorite.TeamFavorite.FragmentTeamFavorite;
import com.rifqit.footballlab.R;

public class Favorite extends AppCompatActivity {

    ImageButton back;
    AdapterTabLayout adapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        back = findViewById(R.id.btn_back_fav);
        tabLayout = findViewById(R.id.tabLayoutFav);
        viewPager = findViewById(R.id.viewPagerFav);
        toolbar = findViewById(R.id.toolbarFav);
        toolbar.setTitleTextColor(getColor(R.color.white));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        adapter = new AdapterTabLayout(getSupportFragmentManager());
        adapter.addFragment(new FragmentNextMatch(), "NEXT MATCH");
        adapter.addFragment(new FragmentLastMatch(), "LAST MATCH");
        adapter.addFragment(new FragmentTeamFavorite(),"TEAM");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);

        changeTabsFont(this, tabLayout);

        int po = tabLayout.getSelectedTabPosition();
        if (po == 0 ){
            toolbar.setTitle("Favorite Match");
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                int position = tab.getPosition();
                Log.e("positionFav", position+" ");
                if (position == 0 ){
                    toolbar.setTitle("Favorite Match");
                }else if (position == 1 ){
                    toolbar.setTitle("Favorite Match");
                }else if (position == 2 ){
                    toolbar.setTitle("Favorite Team");
                }else {
                    toolbar.setTitle("Favorite Match");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void changeTabsFont(Context context, TabLayout tabLayout) {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);

        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(ResourcesCompat.getFont(context,R.font.helvetica_neu_bold));
                    ((TextView) tabViewChild).setAllCaps(false);
                }
            }
        }
    }
}
