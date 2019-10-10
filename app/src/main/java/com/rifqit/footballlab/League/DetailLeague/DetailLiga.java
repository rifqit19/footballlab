package com.rifqit.footballlab.League.DetailLeague;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rifqit.footballlab.AdapterTabLayout;
import com.rifqit.footballlab.GetDataService;
import com.rifqit.footballlab.League.DaftarLiga;
import com.rifqit.footballlab.League.DetailLeague.Klasemen.FragmentKlasemenLiga;
import com.rifqit.footballlab.League.DetailLeague.Match.FragmentMatchLiga;
import com.rifqit.footballlab.League.DetailLeague.Search.Team.Search;
import com.rifqit.footballlab.League.DetailLeague.Search.Match.SearchMatch;
import com.rifqit.footballlab.League.DetailLeague.Team.FragmentTeamLiga;
import com.rifqit.footballlab.League.LeagueObj;
import com.rifqit.footballlab.R;
import com.rifqit.footballlab.RetrofitCilentInstance;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailLiga extends AppCompatActivity {

    LeagueObj leagueObjs = new LeagueObj();
    Menu menu;
    ImageButton back;
    private AdapterTabLayout adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String TAG = DaftarLiga.class.getSimpleName();
    ArrayList<DetailLeagueObj> detailLeagueObjs = new ArrayList<>();
    ProgressDialog progressDialog;
    ImageView imgTrhopy,banner,imageCollaps,searchBtn;
    TextView tittle,desc,moreDesc,txtWeb,txtYoutube,txtFacebook,txtTwitter,tittleToolbar;
    LinearLayout lytFcb,lytTwt,lytWeb,lytYtb,lytSearch,linearSrc,linearInfo;
    CollapsingToolbarLayout collapsingToolbarLayout;
    CoordinatorLayout coordinatorLayout;
    String id;
    RecyclerView recyclerViewSearch;
    int position;
    Intent search;

    String idLeague,idSoccerXML,strSport,strLeague,strLeagueAlternate,strDivision,idCup,intFormedYear,dateFirstEvent,strGender,strCountry,strWebsite,strFacebook,strTwitter,strYoutube,strDescriptionEN,strFanart1,strFanart2,strFanart3,strFanart4,strBanner,strBadge,strLogo,strPoster,strTrophy,strNaming,strComplete,strLocked,strRRS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_liga);

//        coordinatorLayout = findViewById(R.id.coordinator1);
//        lytSearch = findViewById(R.id.linearSearch);
//        linearSrc = findViewById(R.id.srcsrc);
        back = findViewById(R.id.btn_back_detliga);
        tabLayout = findViewById(R.id.tabLayout1);
        viewPager = findViewById(R.id.viewPager1);
        imgTrhopy = findViewById(R.id.imageTrophy);
        tittle = findViewById(R.id.titleLiga);
        desc = findViewById(R.id.descLiga);
        moreDesc = findViewById(R.id.moreDescLiga);
        txtWeb = findViewById(R.id.txtWeb);
        txtYoutube = findViewById(R.id.txtYoutube);
        txtFacebook = findViewById(R.id.txtFaceBook);
        txtTwitter = findViewById(R.id.txtTwitter);
        lytFcb = findViewById(R.id.lytFcb);
        lytTwt = findViewById(R.id.lytTwt);
        lytWeb = findViewById(R.id.lytWeb);
        lytYtb = findViewById(R.id.lytYtb);
        imageCollaps = findViewById(R.id.imageCollapse);
        linearInfo = findViewById(R.id.linearInfo);
        recyclerViewSearch = findViewById(R.id.recyclerSearch);
        searchBtn = findViewById(R.id.searchIcon);
        tittleToolbar = findViewById(R.id.ttlLeague);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String liga = getIntent().getStringExtra("ligaList");
        leagueObjs = new Gson().fromJson(liga, LeagueObj.class);

        id = leagueObjs.getIdLeague();

        Log.e("liga", id);

        getDetailLeague(id);


        lytWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://"+strWebsite));
                startActivity(q);
            }
        });
        lytFcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent w = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://"+strFacebook));
                startActivity(w);
            }
        });
        lytTwt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://"+strTwitter));
                startActivity(e);
            }
        });
        lytYtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://"+strYoutube));
                startActivity(r);
            }
        });
        moreDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(DetailLiga.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                View view = getLayoutInflater().inflate(R.layout.dialog_more_desc,null);
                Button buttonKeluar = (Button)view.findViewById(R.id.buttonDialog);
                TextView ttl = (TextView)view.findViewById(R.id.tittleLeague);
                TextView dsc = (TextView)view.findViewById(R.id.moreLeague);
                ImageView logo = (ImageView)view.findViewById(R.id.logoLeague);
                dialog.setContentView(view);
                ttl.setText(strLeague);
                dsc.setText(strDescriptionEN);
                Picasso.with(DetailLiga.this).load(strLogo).placeholder(R.drawable.ic_placeholder_wth).into(logo);
                dialog.show();
                buttonKeluar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(" ");

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setTitle("Detail Liga");
        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.white0));

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar);
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
//                    showOption(R.id.search);
                    tittleToolbar.setVisibility(View.VISIBLE);

                } else if (isShow) {
                    isShow = true;
//                    showOption(R.id.search);
                    tittleToolbar.setVisibility(View.GONE);
                }
            }
        });

        adapter = new AdapterTabLayout(getSupportFragmentManager());
        adapter.addFragment(new FragmentMatchLiga(), "MATCH");
        adapter.addFragment(new FragmentKlasemenLiga(), "KLASEMEN");
        adapter.addFragment(new FragmentTeamLiga(),"TEAM");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);


        changeTabsFont(this, tabLayout);



        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = new Intent(DetailLiga.this, SearchMatch.class);
                startActivity(search);
            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                position = tab.getPosition();
                Log.e("position", position+" ");
                searchBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == 0 ){
                            search = new Intent(DetailLiga.this, SearchMatch.class);
                        }else if (position == 1 ){
                            search = new Intent(DetailLiga.this, Search.class);
                        }else if (position == 2 ){
                            search = new Intent(DetailLiga.this, Search.class);
                        }else {
                            search = new Intent(DetailLiga.this, SearchMatch.class);
                        }
                        startActivity(search);
                    }
                });

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });







    }
    public  void getDetailLeague(final String idku){
        progressDialog = new ProgressDialog(DetailLiga.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getDetailLiga(idku);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        JSONObject jsonObj = new JSONObject(respon);
                        JSONArray api = jsonObj.getJSONArray("leagues");
                        for (int i = 0; i < api.length(); i++){
                            JSONObject c = api.getJSONObject(i);

                            idLeague = c.getString("idLeague");
                            idSoccerXML = c.getString("idSoccerXML");
                            strSport = c.getString("strSport");
                            strLeague = c.getString("strLeague");
                            strLeagueAlternate = c.getString("strLeagueAlternate");
                            strDivision= c.getString("strDivision");
                            idCup = c.getString("idCup");
                            intFormedYear = c.getString("intFormedYear");
                            dateFirstEvent = c.getString("dateFirstEvent");
                            strGender = c.getString("strGender");
                            strCountry = c.getString("strCountry");
                            strWebsite = c.getString("strWebsite");
                            strFacebook = c.getString("strFacebook");
                            strTwitter = c.getString("strTwitter");
                            strYoutube = c.getString("strYoutube");
                            strRRS = c.getString("strRSS");
                            strDescriptionEN = c.getString("strDescriptionEN");
                            strFanart1 = c.getString("strFanart1");
                            strFanart2 = c.getString("strFanart2");
                            strFanart3 = c.getString("strFanart3");
                            strFanart4 = c.getString("strFanart4");
                            strBadge = c.getString("strBadge");
                            strBanner = c.getString("strBanner");
                            strLogo = c.getString("strLogo");
                            strPoster = c.getString("strPoster");
                            strTrophy = c.getString("strTrophy");
                            strLocked = c.getString("strNaming");
                            strNaming = c.getString("strComplete");
                            strComplete = c.getString("strLocked");

                            DetailLeagueObj p = new DetailLeagueObj();
                            p.setIdLeague(idLeague);
                            p.setIdSoccerXML(idSoccerXML);
                            p.setStrSport(strSport);
                            p.setStrLeague(strLeague);
                            p.setStrLeagueAlternate(strLeagueAlternate);
                            p.setStrDivision(strDivision);
                            p.setIdCup(idCup);
                            p.setIntFormedYear(intFormedYear);
                            p.setDateFirstEvent(dateFirstEvent);
                            p.setStrGender(strGender);
                            p.setStrCountry(strCountry);
                            p.setStrWebsite(strWebsite);
                            p.setStrFacebook(strFacebook);
                            p.setStrTwitter(strTwitter);
                            p.setStrYoutube(strYoutube);
                            p.setStrRSS(strRRS);
                            p.setStrDescriptionEN(strDescriptionEN);
                            p.setStrFanart1(strFanart1);
                            p.setStrFanart2(strFanart2);
                            p.setStrFanart3(strFanart3);
                            p.setStrFanart4(strFanart4);
                            p.setStrBadge(strBadge);
                            p.setStrBanner(strBanner);
                            p.setStrLogo(strLogo);
                            p.setStrPoster(strPoster);
                            p.setStrTrophy(strTrophy);
                            p.setStrLocked(strLocked);
                            p.setStrNaming(strNaming);
                            p.setStrComplete(strComplete);
                            detailLeagueObjs.add(p);

                            Picasso.with(DetailLiga.this).load(strTrophy).placeholder(R.drawable.ic_placeholder_wth).into(imgTrhopy);
                            tittle.setText(strLeague);
                            desc.setText(strDescriptionEN);
                            txtWeb.setText(strLeague);
                            txtFacebook.setText(strLeague);
                            txtTwitter.setText(strLeague);
                            txtYoutube.setText(strLeague);

                            Calendar date = Calendar.getInstance();
                            Integer day = date.get(Calendar.DAY_OF_WEEK);

                            if (day.equals(1)){
                                Picasso.with(DetailLiga.this).load(strFanart1).into(imageCollaps);
                            }else if (day.equals(2)){
                                Picasso.with(DetailLiga.this).load(strFanart2).into(imageCollaps);
                            }else if (day.equals(3)){
                                Picasso.with(DetailLiga.this).load(strFanart3).into(imageCollaps);
                            }else if (day.equals(4)){
                                Picasso.with(DetailLiga.this).load(strFanart4).into(imageCollaps);
                            }else if (day.equals(5)){
                                Picasso.with(DetailLiga.this).load(strFanart1).into(imageCollaps);
                            }else if (day.equals(6)){
                                Picasso.with(DetailLiga.this).load(strFanart2).into(imageCollaps);
                            }else if (day.equals(7)){
                                Picasso.with(DetailLiga.this).load(strFanart3).into(imageCollaps);
                            }

                            if (strWebsite.isEmpty()){
                                lytWeb.setVisibility(View.GONE);
                            }else if (strYoutube.isEmpty()){
                                lytYtb.setVisibility(View.GONE);
                            }else if (strFacebook.isEmpty()){
                                lytFcb.setVisibility(View.GONE);
                            }else if (strTwitter.isEmpty()){
                                lytTwt.setVisibility(View.GONE);
                            }

                        }
                    } catch (JSONException e) {
                        Toast.makeText(DetailLiga.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Souldn't get json from server.1");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void
                        run() {
                            Toast.makeText(getApplicationContext(), "Couldn't get json from server. Check LoCat for possible errors!1",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailLiga.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
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

    public String getIdLeague(){
        return id;
    }

}
