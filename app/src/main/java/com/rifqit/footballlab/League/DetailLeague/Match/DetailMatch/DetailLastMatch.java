package com.rifqit.footballlab.League.DetailLeague.Match.DetailMatch;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.rifqit.footballlab.GetDataService;
import com.rifqit.footballlab.R;
import com.rifqit.footballlab.RetrofitCilentInstance;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailLastMatch extends AppCompatActivity {

    private String TAG = DetailMatch.class.getSimpleName();
    ArrayList<DetailMatchObj> detailMatches = new ArrayList<>();
    ArrayList<DetailTeamById> detailTeamByIds = new ArrayList<>();
    ProgressDialog progressDialog;
    String idEvent,  idSoccerXML,  strEvent,  strFilename, strSport, idLeague, strLeague, strSeason, strHomeTeam,  strAwayTeam,  intHomeScore,  intRound, intAwayScore, intSpectators, strHomeGoalDetails, strHomeRedCards, strHomeYellowCards, strHomeLineupGoalkeeper, strHomeLineupDefense, strHomeLineupMidfield, strHomeLineupForward, strHomeLineupSubstitutes,  strHomeFormation, strAwayRedCards, strAwayYellowCards, strAwayGoalDetails, strAwayLineupGoalkeeper, strAwayLineupDefense, strAwayLineupMidfield, strAwayLineupForward, strAwayLineupSubstitutes, strAwayFormation, intHomeShots, intAwayShots, dateEvent,strLocked;
    Object strDescriptionEN1, datetimeEventUTC1, strDate1, strTime1, strTVStation1, idHomeTeam1, idAwayTeam1, strResult1, strCircuit1, strCountry1, strCity1, strPoster1, strFanart1, strThumb1, strBanner1, strMap1;
    String strDescriptionEN, datetimeEventUTC, strDate, strTime, strTVStation, idHomeTeam, idAwayTeam, strResult, strCircuit, strCountry, strCity, strPoster, strFanart, strThumb, strBanner, strMap;
    String id;
    TextView tittle,gkHome,gkAway,dfHome,dfAway,mdHome,mdAway,fwHome,fwAway,subHome,subAway,ycHome,ycAway,rcHome,rcAway,goalHome,goalAway,shotHome,shotAway,scoreHOme,scoreAway,tanggal,nama;
    ImageView iconHome,iconAway,imgCollapse;
    ImageButton back;
    String strStadiumH,strStadiumThumbH,strTeamBadgeH;
    String strStadiumA,strStadiumThumbA,strTeamBadgeA;
    ToggleButton fav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_last_match);


        tittle = findViewById(R.id.ttlDn2);
        gkHome = findViewById(R.id.homeGk2);
        gkAway = findViewById(R.id.awayGk2);
        dfHome = findViewById(R.id.homeDefense2);
        dfAway = findViewById(R.id.awayDefense2);
        mdHome = findViewById(R.id.homeMidfield2);
        mdAway = findViewById(R.id.awayMidfield2);
        fwHome = findViewById(R.id.homeForward2);
        fwAway = findViewById(R.id.awayForward2);
        subHome = findViewById(R.id.homeSubstitutes2);
        subAway = findViewById(R.id.awaySubstitutes2);
        ycHome = findViewById(R.id.homeYc2);
        ycAway = findViewById(R.id.awayYc2);
        rcHome = findViewById(R.id.homeRc2);
        rcAway = findViewById(R.id.awayRc2);
        goalHome = findViewById(R.id.homeGoals2);
        goalAway = findViewById(R.id.awayGoals2);
        shotHome = findViewById(R.id.homeShoots2);
        shotAway = findViewById(R.id.awayShoots2);
        scoreHOme = findViewById(R.id.homeScore2);
        scoreAway = findViewById(R.id.awayScore2);
        tanggal = findViewById(R.id.tanggal2);
        nama = findViewById(R.id.eventName2);
        iconHome = findViewById(R.id.homeIcon2);
        iconAway = findViewById(R.id.awayIcon2);
        imgCollapse = findViewById(R.id.imageCollapseDn2);
        back = findViewById(R.id.btn_back_detDn2);
        fav = findViewById(R.id.btn_fav_detLastMatch);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


//        String nest = getIntent().getStringExtra("nextMatch");
//        nextMatchObj = new Gson().fromJson(nest, NextMatchObj.class);
        id = getIntent().getStringExtra("t1");

        Log.e("idEvent", id);

        getNextMatch(id);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDn2);
        setSupportActionBar(toolbar);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbarDn2);
//        collapsingToolbarLayout.setTitle("Detail Pemain");

        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white0));

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.appbarDn2);
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
                    tittle.setVisibility(View.VISIBLE);
                } else if (isShow) {
                    isShow = true;
                    tittle.setVisibility(View.GONE);
                }
            }
        });
//        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
//        try {
//            Date date = format.parse(strDate);
//            Log.e("date", date.toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
    public void getNextMatch(String idku){
        progressDialog = new ProgressDialog(DetailLastMatch.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getDetailEvent(idku);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
//                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        JSONObject jsonObj = new JSONObject(respon);
                        detailMatches.clear();
                        JSONArray api = jsonObj.getJSONArray("events");
                        for (int i = 0; i < api.length(); i++){
                            JSONObject c = api.getJSONObject(i);

                            idEvent = c.getString("idEvent");
                            idSoccerXML = c.getString("idSoccerXML");
                            strEvent = c.getString("strEvent");
                            strFilename = c.getString("strFilename");
                            strSport = c.getString("strSport");
                            idLeague = c.getString("idLeague");
                            strLeague = c.getString("strLeague");
                            strSeason = c.getString("strSeason");
                            strHomeTeam = c.getString("strHomeTeam");
                            strAwayTeam = c.getString("strAwayTeam");
                            intRound = c.getString("intRound");
                            dateEvent = c.getString("dateEvent");
                            strDate = c.getString("strDate");
                            strDate1 = strDate;
                            strTime = c.getString("strTime");
                            strTime1 = strTime;
                            idHomeTeam = c.getString("idHomeTeam");
                            idHomeTeam1 = idHomeTeam;

                            idAwayTeam = c.getString("idAwayTeam");
                            idAwayTeam1 = idAwayTeam;
                            strLocked = c.getString("strLocked");
                            strDescriptionEN = c.getString("strDescriptionEN");
                            strDescriptionEN1 = strDescriptionEN;
                            intHomeScore = c.getString("intHomeScore");
                            intAwayScore = c.getString("intAwayScore");
                            intSpectators = c.getString("intSpectators");
                            strHomeGoalDetails = c.getString("strHomeGoalDetails");
                            strHomeRedCards = c.getString("strHomeRedCards");
                            strHomeYellowCards = c.getString("strHomeYellowCards");
                            strHomeLineupGoalkeeper = c.getString("strHomeLineupGoalkeeper");
                            strHomeLineupDefense = c.getString("strHomeLineupDefense");
                            strHomeLineupMidfield = c.getString("strHomeLineupMidfield");
                            strHomeLineupForward = c.getString("strHomeLineupForward");
                            strHomeLineupSubstitutes = c.getString("strHomeLineupSubstitutes");
                            strHomeFormation = c.getString("strHomeFormation");
                            strAwayRedCards = c.getString("strAwayRedCards");
                            strAwayYellowCards = c.getString("strAwayYellowCards");
                            strAwayGoalDetails = c.getString("strAwayGoalDetails");
                            strAwayLineupGoalkeeper = c.getString("strAwayLineupGoalkeeper");
                            strAwayLineupDefense = c.getString("strAwayLineupDefense");
                            strAwayLineupMidfield = c.getString("strAwayLineupMidfield");
                            strAwayLineupForward = c.getString("strAwayLineupForward");
                            strAwayLineupSubstitutes = c.getString("strAwayLineupSubstitutes");
                            strAwayFormation = c.getString("strAwayFormation");
                            intHomeShots = c.getString("intHomeShots");
                            intAwayShots = c.getString("intAwayShots");
                            datetimeEventUTC = c.getString("datetimeEventUTC");
                            datetimeEventUTC1 = datetimeEventUTC;
                            strTVStation = c.getString("strTVStation");
                            strTVStation1 = strTVStation;
                            strResult = c.getString("strResult");
                            strResult1 = strResult;
                            strCircuit = c.getString("strCircuit");
                            strCircuit1 = strCircuit;
                            strCountry = c.getString("strCountry");
                            strCountry1 = strCountry;
                            strCity = c.getString("strCity");
                            strCity1 = strCity;
                            strPoster = c.getString("strPoster");
                            strPoster1 = strPoster;
                            strFanart = c.getString("strFanart");
                            strFanart1 = strFanart;
                            strThumb = c.getString("strThumb");
                            strThumb1 = strThumb;
                            strBanner = c.getString("strBanner");
                            strBanner1 = strBanner;
                            strMap = c.getString("strMap");
                            strMap1 = strMap;

                            DetailMatchObj o = new DetailMatchObj();

                            o.setIdEvent(idEvent);
                            o.setIdSoccerXML(idSoccerXML);
                            o.setStrEvent(strEvent);
                            o.setStrFilename(strFilename);
                            o.setStrSport(strSport);
                            o.setIdLeague(idLeague);
                            o.setStrLeague(strLeague);
                            o.setStrSeason(strSeason);
                            o.setStrHomeTeam(strHomeTeam);
                            o.setStrAwayTeam(strAwayTeam);
                            o.setIntRound(intRound);
                            o.setDateEvent(dateEvent);
                            o.setStrDate(strDate);
                            o.setStrTime(strTime);
                            o.setIdHomeTeam(idHomeTeam);
                            o.setIdAwayTeam(idAwayTeam);
                            o.setStrLocked(strLocked);
                            o.setStrDescriptionEN(strDescriptionEN1);
                            if (intHomeScore.equals("null")){
                                o.setIntHomeScore("-");
                            }else {
                                o.setIntHomeScore(intHomeScore);
                            }

                            if (intAwayScore.equals("null")){
                                o.setIntAwayScore("-");
                            }else {
                                o.setIntAwayScore(intAwayScore);
                            }

                            o.setIntSpectators(intSpectators);
                            o.setStrHomeGoalDetails(strHomeGoalDetails);
                            o.setStrHomeRedCards(strHomeRedCards);
                            o.setStrHomeYellowCards(strHomeYellowCards);
                            o.setStrHomeLineupGoalkeeper(strHomeLineupGoalkeeper);
                            o.setStrHomeLineupDefense(strHomeLineupDefense);
                            o.setStrHomeLineupMidfield(strHomeLineupMidfield);
                            o.setStrHomeLineupForward(strHomeLineupForward);
                            o.setStrHomeLineupSubstitutes(strHomeLineupSubstitutes);
                            o.setStrHomeFormation(strHomeFormation);
                            o.setIntHomeShots(intHomeShots);
                            o.setStrAwayGoalDetails(strAwayGoalDetails);
                            o.setStrAwayRedCards(strAwayRedCards);
                            o.setStrAwayYellowCards(strAwayYellowCards);
                            o.setStrAwayLineupGoalkeeper(strAwayLineupGoalkeeper);
                            o.setStrAwayLineupDefense(strAwayLineupDefense);
                            o.setStrAwayLineupMidfield(strAwayLineupMidfield);
                            o.setStrAwayLineupForward(strAwayLineupForward);
                            o.setStrAwayLineupSubstitutes(strAwayLineupSubstitutes);
                            o.setStrAwayFormation(strAwayFormation);
                            o.setIntAwayShots(intAwayShots);
//                            o.setDatetimeEventUTC(datetimeEventUTC1);
                            o.setStrTVStation(strTVStation1);
                            o.setStrResult(strResult1);
                            o.setStrCircuit(strCircuit1);
                            o.setStrCountry(strCountry1);
                            o.setStrCity(strCity1);
                            o.setStrPoster(strPoster1);
                            o.setStrFanart(strFanart1);
                            o.setStrThumb(strThumb1);
                            o.setStrBanner(strBanner1);
                            o.setStrMap(strMap1);
                            detailMatches.add(o);

                            gkHome.setText(strHomeLineupGoalkeeper);
                            gkAway.setText(strAwayLineupGoalkeeper);
                            dfHome.setText(strHomeLineupDefense);
                            dfAway.setText(strAwayLineupDefense);
                            mdHome.setText(strHomeLineupMidfield);
                            mdAway.setText(strAwayLineupMidfield);
                            fwHome.setText(strHomeLineupForward);
                            fwAway.setText(strAwayLineupForward);
                            subHome.setText(strHomeLineupSubstitutes);
                            subAway.setText(strAwayLineupSubstitutes);
                            ycHome.setText(strHomeYellowCards);
                            ycAway.setText(strAwayYellowCards);
                            rcHome.setText(strHomeRedCards);
                            rcAway.setText(strAwayRedCards);
                            goalHome.setText(strHomeGoalDetails);
                            goalAway.setText(strAwayLineupGoalkeeper);
                            shotHome.setText(intHomeShots);
                            shotAway.setText(intAwayShots);
                            scoreHOme.setText("-");
                            scoreAway.setText("-");
                            tanggal.setText(dateEvent);
                            nama.setText(strEvent);

                            Log.e("nextMatch", strEvent);
                            Log.e("nextThumb",strThumb1.toString());

                            getDetHomeTeam(idHomeTeam);
                            getDetAwayTeam(idAwayTeam);

                            DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//                            String datenow= dateEvent;
                            try {
                                Date date2 = (Date) formatter.parse(dateEvent);
                                Log.e("date",date2.toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                        }
                    } catch (JSONException e) {
                        Toast.makeText(DetailLastMatch.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Souldn't get json from server.1");
                    DetailLastMatch.this.runOnUiThread(new Runnable() {
                        @Override
                        public void
                        run() {
                            Toast.makeText(DetailLastMatch.this.getApplicationContext(), "Couldn't get json from server. Check LoCat for possible errors!1",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailLastMatch.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDetHomeTeam(String idku){
//        progressDialog = new ProgressDialog(DetailMatch.this);
//        progressDialog.setMessage("Loading...");
        progressDialog.show();
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getDetailTeamById(idku);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
//                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        JSONObject jsonObj = new JSONObject(respon);
                        detailTeamByIds.clear();
                        JSONArray api = jsonObj.getJSONArray("teams");
                        for (int i = 0; i < api.length(); i++){
                            JSONObject c = api.getJSONObject(i);

                            strStadiumH = c.getString("strStadium");
                            strStadiumThumbH = c.getString("strStadiumThumb") ;
                            strTeamBadgeH = c.getString("strTeamBadge");

                            DetailTeamById m = new DetailTeamById();
                            m.setStrStadium(strStadiumH);
                            m.setStrStadiumThumb(strStadiumThumbH);
                            m.setStrTeamBadge(strTeamBadgeH);
                            detailTeamByIds.add(m);

                            Picasso.with(DetailLastMatch.this).load(strTeamBadgeH).placeholder(R.drawable.ic_placeholder_wth).into(iconHome);
                            Picasso.with(DetailLastMatch.this).load(strStadiumThumbH).placeholder(R.drawable.ic_placeholder_wth).into(imgCollapse);


                            Log.e("std", strStadiumH);
                            Log.e("std",strStadiumThumbH);
                            Log.e("std",strTeamBadgeH);


                        }
                    } catch (JSONException e) {
                        Toast.makeText(DetailLastMatch.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Souldn't get json from server.1");
                    DetailLastMatch.this.runOnUiThread(new Runnable() {
                        @Override
                        public void
                        run() {
                            Toast.makeText(DetailLastMatch.this.getApplicationContext(), "Couldn't get json from server. Check LoCat for possible errors!1",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailLastMatch.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDetAwayTeam(String idku){
//        progressDialog = new ProgressDialog(DetailMatch.this);
//        progressDialog.setMessage("Loading...");
        progressDialog.show();
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getDetailTeamById(idku);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
//                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        JSONObject jsonObj = new JSONObject(respon);
                        detailTeamByIds.clear();
                        JSONArray api = jsonObj.getJSONArray("teams");
                        for (int i = 0; i < api.length(); i++){
                            JSONObject c = api.getJSONObject(i);

                            strStadiumA = c.getString("strStadium");
                            strStadiumThumbA = c.getString("strStadiumThumb") ;
                            strTeamBadgeA = c.getString("strTeamBadge");

                            DetailTeamById m = new DetailTeamById();
                            m.setStrStadium(strStadiumA);
                            m.setStrStadiumThumb(strStadiumThumbA);
                            m.setStrTeamBadge(strTeamBadgeA);
                            detailTeamByIds.add(m);

                            Picasso.with(DetailLastMatch.this).load(strTeamBadgeA).placeholder(R.drawable.ic_placeholder_wth).into(iconAway);
                            Log.e("std", strStadiumA);
                            Log.e("std",strStadiumThumbA);
                            Log.e("std",strTeamBadgeA);


                        }
                    } catch (JSONException e) {
                        Toast.makeText(DetailLastMatch.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Souldn't get json from server.1");
                    DetailLastMatch.this.runOnUiThread(new Runnable() {
                        @Override
                        public void
                        run() {
                            Toast.makeText(DetailLastMatch.this.getApplicationContext(), "Couldn't get json from server. Check LoCat for possible errors!1",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailLastMatch.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
