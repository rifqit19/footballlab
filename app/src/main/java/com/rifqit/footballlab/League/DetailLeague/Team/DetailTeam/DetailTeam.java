package com.rifqit.footballlab.League.DetailLeague.Team.DetailTeam;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.rifqit.footballlab.AdapterTabLayout;
import com.rifqit.footballlab.Favorite.RealmHelper;
import com.rifqit.footballlab.Favorite.TeamFavorite.FavoriteTeamObj;
import com.rifqit.footballlab.League.DetailLeague.Team.DetailTeam.TeamInfo.FragmentTeamInfo;
import com.rifqit.footballlab.League.DetailLeague.Team.DetailTeam.TeamPlayer.FragmentTeamPlayer;
import com.rifqit.footballlab.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailTeam extends AppCompatActivity {

    ImageButton back;
    private AdapterTabLayout adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
//    TeamObj teamObjs = new TeamObj();
    ToggleButton fav;
    Realm realm;
    FavoriteTeamObj favObj;
    RealmHelper realmHelper;

    ImageView imageCollapse,imageLogo;
    TextView nama,web,tittl;
//    String id;
    String idTeam,idSoccerXML,intLoved,strTeam,strTeamShort,strAlternate,intFormedYear,strSport,strLeague,idLeague,strDivision,strManager,strStadium,strKeywords,strRSS,strStadiumThumb,strStadiumDescription,strStadiumLocation,intStadiumCapacity,strWebsite,strFacebook,strTwitter,strInstagram,strDescriptionEN,strGender,strCountry,strTeamBadge,strTeamJersey,strTeamLogo,strTeamFanart1,strTeamFanart2,strTeamFanart3,strTeamFanart4,strTeamBanner,strYoutube,strLocked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_team);

        back = findViewById(R.id.btn_back_detTeam);
        tabLayout = findViewById(R.id.tabLayout1T);
        viewPager = findViewById(R.id.viewPager1T);
        fav = findViewById(R.id.btn_fav_detTeam);
        imageCollapse = findViewById(R.id.imageCollapseT);
        imageLogo = findViewById(R.id.logoTeam);
        nama = findViewById(R.id.namaTeam);
        web = findViewById(R.id.webTeam);
        tittl = findViewById(R.id.ttl);

        Realm.init(DetailTeam.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressed();
                Intent BackIntent = new Intent();
                setResult(RESULT_OK,BackIntent);
                finish();
            }
        });

        idTeam  = getIntent().getStringExtra("t1");
        idSoccerXML = getIntent().getStringExtra("t2");
        intLoved = getIntent().getStringExtra("t3");
        strTeam = getIntent().getStringExtra("t4");
        strTeamShort = getIntent().getStringExtra("t5");
        strAlternate = getIntent().getStringExtra("t6");
        intFormedYear = getIntent().getStringExtra("t7");
        strSport = getIntent().getStringExtra("t8");
        strLeague  = getIntent().getStringExtra("t9");
        idLeague = getIntent().getStringExtra("t10");
        strDivision = getIntent().getStringExtra("t12");
        strManager = getIntent().getStringExtra("t13");
        strStadium = getIntent().getStringExtra("t14");
        strKeywords = getIntent().getStringExtra("t15");
        strRSS = getIntent().getStringExtra("t16");
        strStadiumThumb = getIntent().getStringExtra("t17");
        strStadiumDescription = getIntent().getStringExtra("t18");
        strStadiumLocation = getIntent().getStringExtra("t19");
        intStadiumCapacity = getIntent().getStringExtra("t20");
        strWebsite       = getIntent().getStringExtra("t21");
        strFacebook      = getIntent().getStringExtra("t22");
        strTwitter       = getIntent().getStringExtra("t23");
        strInstagram     = getIntent().getStringExtra("t24");
        strDescriptionEN = getIntent().getStringExtra("t25");
        strGender        = getIntent().getStringExtra("t26");
        strCountry       = getIntent().getStringExtra("t27");
        strTeamJersey    = getIntent().getStringExtra("t28");
        strTeamLogo      = getIntent().getStringExtra("t29");
        strTeamFanart1   = getIntent().getStringExtra("t30");
        strTeamFanart2   = getIntent().getStringExtra("t31");
        strTeamFanart3   = getIntent().getStringExtra("t32");
        strTeamFanart4   = getIntent().getStringExtra("t33");
        strTeamBanner    = getIntent().getStringExtra("t34");
        strTeamBadge    = getIntent().getStringExtra("t35");
        strYoutube       = getIntent().getStringExtra("t36");
        strLocked        = getIntent().getStringExtra("t37");

        final FavoriteTeamObj favObj1 = realm.where(FavoriteTeamObj.class).equalTo("idTeam",idTeam).findFirst();

        if (favObj1 == null) {
            fav.setChecked(false);
            fav.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_fav_wth));

        }else{
            fav.setChecked(true);
            fav.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_fav_gold));
        }
        fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fav.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_fav_gold));

                    favObj = new FavoriteTeamObj();

                    favObj.setIdTeam(idTeam);
                    favObj.setIdSoccerXML(idSoccerXML);
                    favObj.setIntLoved(intLoved);
                    favObj.setStrTeam(strTeam);
                    favObj.setStrTeamShort(strTeamShort);
                    favObj.setStrAlternate(strAlternate);
                    favObj.setIntFormedYear(intFormedYear);
                    favObj.setStrSport(strSport);
                    favObj.setStrLeague(strLeague);
                    favObj.setIdLeague(idLeague);
                    favObj.setStrDivision(strDivision);
                    favObj.setStrManager(strManager);
                    favObj.setStrStadium(strStadium);
                    favObj.setStrKeywords(strKeywords);
                    favObj.setStrRSS(strRSS);
                    favObj.setStrStadiumThumb(strStadiumThumb);
                    favObj.setStrStadiumDescription(strStadiumDescription);
                    favObj.setStrStadiumLocation(strStadiumLocation);
                    favObj.setIntStadiumCapacity(intStadiumCapacity);
                    favObj.setStrWebsite(strWebsite);
                    favObj.setStrFacebook(strFacebook);
                    favObj.setStrTwitter(strTwitter);
                    favObj.setStrDescriptionEN(strDescriptionEN);
                    favObj.setStrGender(strGender);
                    favObj.setStrCountry(strCountry);
                    favObj.setStrTeamBadge(strTeamBadge);
                    favObj.setStrTeamJersey(strTeamJersey);
                    favObj.setStrTeamLogo(strTeamLogo);
                    favObj.setStrTeamFanart1(strTeamFanart1);
                    favObj.setStrTeamFanart2(strTeamFanart2);
                    favObj.setStrTeamFanart3(strTeamFanart3);
                    favObj.setStrTeamFanart4(strTeamFanart4);
                    favObj.setStrTeamBanner(strTeamBanner);
                    favObj.setStrYoutube(strYoutube);
                    favObj.setStrLocked(strLocked);

                    realmHelper = new RealmHelper(realm);
                    realmHelper.saveTeam(favObj);

                    Toast.makeText(DetailTeam.this, "Disimpan ke Favorite!", Toast.LENGTH_SHORT).show();
                }
                else {
                    fav.setBackgroundResource(R.drawable.ic_fav_wth);
                    realmHelper = new RealmHelper(realm);
                    realmHelper.deleteTeam(idTeam);
                    Log.e("iddd",idTeam);
                    Toast.makeText(DetailTeam.this,"Item dihapus",Toast.LENGTH_SHORT).show();
                }
            }
        });

        nama.setText(strTeam);
        web.setText(strWebsite);
        Picasso.with(this).load(strTeamBadge).placeholder(R.drawable.ic_placeholder).into(imageLogo);

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent w = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://"+strWebsite));
                startActivity(w);
            }
        });

        Calendar date = Calendar.getInstance();
        Integer day = date.get(Calendar.DAY_OF_WEEK);

        if (day.equals(1)){
            Picasso.with(this).load(strStadiumThumb).into(imageCollapse);
        }else if (day.equals(2)){
            Picasso.with(this).load(strTeamFanart1).into(imageCollapse);
        }else if (day.equals(3)){
            Picasso.with(this).load(strTeamFanart2).into(imageCollapse);
        }else if (day.equals(4)){
            Picasso.with(this).load(strTeamFanart3).into(imageCollapse);
        }else if (day.equals(5)){
            Picasso.with(this).load(strTeamFanart4).into(imageCollapse);
        }else if (day.equals(6)){
            Picasso.with(this).load(strStadiumThumb).into(imageCollapse);
        }else if (day.equals(7)){
            Picasso.with(this).load(strTeamFanart2).into(imageCollapse);
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarT);
        setSupportActionBar(toolbar);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbarT);

        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white0));

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.appbarT);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
//                    back.setVisibility(View.VISIBLE);
//                    fav.setVisibility(View.VISIBLE);
//                    collapsingToolbarLayout.setTitle("Detail Team");
                    tittl.setVisibility(View.VISIBLE);
                } else if (isShow) {
                    isShow = true;
//                    back.setVisibility(View.VISIBLE);
//                    fav.setVisibility(View.VISIBLE);
                    tittl.setVisibility(View.GONE);
//                    collapsingToolbarLayout.setTitle("");
                }
            }
        });

        adapter = new AdapterTabLayout(getSupportFragmentManager());
        adapter.addFragment(new FragmentTeamInfo(), "TEAM INFO");
        adapter.addFragment(new FragmentTeamPlayer(), "PLAYER");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        changeTabsFont(this, tabLayout);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                int position = tab.getPosition();
                Log.e("position", position+" ");
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
    public String getIdTeam(){
        return idTeam;
    }

}
