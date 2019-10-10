package com.rifqit.footballlab.League.DetailLeague.Match.DetailMatch;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.rifqit.footballlab.Favorite.LastMatchFavorite.FavoriteLastMatchObj;
import com.rifqit.footballlab.Favorite.NextMatchFavorite.FavoriteNextMatchObj;
import com.rifqit.footballlab.Favorite.RealmHelper;
import com.rifqit.footballlab.GetDataService;
import com.rifqit.footballlab.League.DetailLeague.Match.LastMatchObj;
import com.rifqit.footballlab.League.DetailLeague.Match.NextMatchObj;
import com.rifqit.footballlab.R;
import com.rifqit.footballlab.RetrofitCilentInstance;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMatch extends AppCompatActivity {

    LastMatchObj lastMatchObj = new LastMatchObj();
    NextMatchObj nextMatchObj = new NextMatchObj();
    private String TAG = DetailMatch.class.getSimpleName();
    ArrayList<DetailMatchObj> detailMatches = new ArrayList<>();
    ArrayList<DetailTeamById> detailTeamByIds = new ArrayList<>();
    ProgressDialog progressDialog;
//    String idEvent,idSoccerXML,strEvent,strFilename,strSport,idLeague,strLeague,strSeason,strHomeTeam,strAwayTeam,intRound,dateEvent,strDate,strTime,idHomeTeam,idAwayTeam,strLocked,strDescriptionEN,intHomeScore,intAwayScore,intSpectators,strHomeGoalDetails,strHomeRedCards,strHomeYellowCards,strHomeLineupGoalkeeper,strHomeLineupDefense,strHomeLineupMidfield,strHomeLineupForward,strHomeLineupSubstitutes,strHomeFormation,strAwayRedCards,strAwayYellowCards,strAwayGoalDetails,strAwayLineupGoalkeeper,strAwayLineupDefense,strAwayLineupMidfield,strAwayLineupForward,strAwayLineupSubstitutes,strAwayFormation,intHomeShots,intAwayShots,datetimeEventUTC,strTVStation,strResult,strCircuit,strCountry,strCity,strPoster,strFanart,strThumb,strBanner,strMap;
    String idEvent2,idSoccerXML2,strEvent2,strFilename2,strSport2,idLeague2,strLeague2,strSeason2,strHomeTeam2,strAwayTeam2,intRound2,dateEvent2,strDate2,strTime2,idHomeTeam2,idAwayTeam2,strLocked2,strDescriptionEN2,intHomeScore2,intAwayScore2,intSpectators2,strHomeGoalDetails2,strHomeRedCards2,strHomeYellowCards2,strHomeLineupGoalkeeper2,strHomeLineupDefense2,strHomeLineupMidfield2,strHomeLineupForward2,strHomeLineupSubstitutes2,strHomeFormation2,strAwayRedCards2,strAwayYellowCards2,strAwayGoalDetails2,strAwayLineupGoalkeeper2,strAwayLineupDefense2,strAwayLineupMidfield2,strAwayLineupForward2,strAwayLineupSubstitutes2,strAwayFormation2,intHomeShots2,intAwayShots2,datetimeEventUTC2,strTVStation2,strResult2,strCircuit2,strCountry2,strCity2,strPoster2,strFanart2,strThumb2,strBanner2,strMap2;
    String id;
    TextView tittle,gkHome,gkAway,dfHome,dfAway,mdHome,mdAway,fwHome,fwAway,subHome,subAway,ycHome,ycAway,rcHome,rcAway,goalHome,goalAway,shotHome,shotAway,scoreHOme,scoreAway,tanggal,nama;
    ImageView iconHome,iconAway,imgCollapse;
    ImageButton back;
    String strStadiumH,strStadiumThumbH,strTeamBadgeH;
    String strStadiumA,strStadiumThumbA,strTeamBadgeA,dateCurrent1;
    ToggleButton fav;
    Date date, currentDate;
    Realm realm;
    FavoriteLastMatchObj favoriteLastMatchObj;
    FavoriteNextMatchObj favoriteNextMatchObj;
    RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_match);

        tittle = findViewById(R.id.ttlDn);
        gkHome = findViewById(R.id.homeGk);
        gkAway = findViewById(R.id.awayGk);
        dfHome = findViewById(R.id.homeDefense);
        dfAway = findViewById(R.id.awayDefense);
        mdHome = findViewById(R.id.homeMidfield);
        mdAway = findViewById(R.id.awayMidfield);
        fwHome = findViewById(R.id.homeForward);
        fwAway = findViewById(R.id.awayForward);
        subHome = findViewById(R.id.homeSubstitutes);
        subAway = findViewById(R.id.awaySubstitutes);
        ycHome = findViewById(R.id.homeYc);
        ycAway = findViewById(R.id.awayYc);
        rcHome = findViewById(R.id.homeRc);
        rcAway = findViewById(R.id.awayRc);
        goalHome = findViewById(R.id.homeGoals);
        goalAway = findViewById(R.id.awayGoals);
        shotHome = findViewById(R.id.homeShoots);
        shotAway = findViewById(R.id.awayShoots);
        scoreHOme = findViewById(R.id.homeScore);
        scoreAway = findViewById(R.id.awayScore);
        tanggal = findViewById(R.id.tanggal);
        nama = findViewById(R.id.eventName);
        iconHome = findViewById(R.id.homeIcon);
        iconAway = findViewById(R.id.awayIcon);
        imgCollapse = findViewById(R.id.imageCollapseDn);
         back = findViewById(R.id.btn_back_detDn);
         fav = findViewById(R.id.btn_fav_detMatch);

        Realm.init(DetailMatch.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);


        back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 onBackPressed();
             }
         });

         id = getIntent().getStringExtra("t1");

        Log.e("idEvent", id);

        getNextMatch(id);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDn);
        setSupportActionBar(toolbar);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbarDn);

        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white0));

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.appbarDn);
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

        dateCurrent1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

    }
    public void getNextMatch(String idku){
        progressDialog = new ProgressDialog(DetailMatch.this);
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

//                            idEvent = c.getString("idEvent");
//                            idSoccerXML = c.getString("idSoccerXML");
//                            strEvent = c.getString("strEvent");
//                            strFilename = c.getString("strFilename");
//                            strSport = c.getString("strSport");
//                            idLeague = c.getString("idLeague");
//                            strLeague = c.getString("strLeague");
//                            strSeason = c.getString("strSeason");
//                            strHomeTeam = c.getString("strHomeTeam");
//                            strAwayTeam = c.getString("strAwayTeam");
//                            intRound = c.getString("intRound");
//                            dateEvent = c.getString("dateEvent");
//                            strDate = c.getString("strDate");
//                            strDate1 = strDate;
//                            strTime = c.getString("strTime");
//                            strTime1 = strTime;
//                            idHomeTeam = c.getString("idHomeTeam");
//                            idHomeTeam1 = idHomeTeam;
//
//                            idAwayTeam = c.getString("idAwayTeam");
//                            idAwayTeam1 = idAwayTeam;
//                            strLocked = c.getString("strLocked");
//                            strDescriptionEN = c.getString("strDescriptionEN");
//                            strDescriptionEN1 = strDescriptionEN;
//                            intHomeScore = c.getString("intHomeScore");
//                            intAwayScore = c.getString("intAwayScore");
//                            intSpectators = c.getString("intSpectators");
//                            strHomeGoalDetails = c.getString("strHomeGoalDetails");
//                            strHomeRedCards = c.getString("strHomeRedCards");
//                            strHomeYellowCards = c.getString("strHomeYellowCards");
//                            strHomeLineupGoalkeeper = c.getString("strHomeLineupGoalkeeper");
//                            strHomeLineupDefense = c.getString("strHomeLineupDefense");
//                            strHomeLineupMidfield = c.getString("strHomeLineupMidfield");
//                            strHomeLineupForward = c.getString("strHomeLineupForward");
//                            strHomeLineupSubstitutes = c.getString("strHomeLineupSubstitutes");
//                            strHomeFormation = c.getString("strHomeFormation");
//                            strAwayRedCards = c.getString("strAwayRedCards");
//                            strAwayYellowCards = c.getString("strAwayYellowCards");
//                            strAwayGoalDetails = c.getString("strAwayGoalDetails");
//                            strAwayLineupGoalkeeper = c.getString("strAwayLineupGoalkeeper");
//                            strAwayLineupDefense = c.getString("strAwayLineupDefense");
//                            strAwayLineupMidfield = c.getString("strAwayLineupMidfield");
//                            strAwayLineupForward = c.getString("strAwayLineupForward");
//                            strAwayLineupSubstitutes = c.getString("strAwayLineupSubstitutes");
//                            strAwayFormation = c.getString("strAwayFormation");
//                            intHomeShots = c.getString("intHomeShots");
//                            intAwayShots = c.getString("intAwayShots");
//                            datetimeEventUTC = c.getString("datetimeEventUTC");
//                            datetimeEventUTC1 = datetimeEventUTC;
//                            strTVStation = c.getString("strTVStation");
//                            strTVStation1 = strTVStation;
//                            strResult = c.getString("strResult");
//                            strResult1 = strResult;
//                            strCircuit = c.getString("strCircuit");
//                            strCircuit1 = strCircuit;
//                            strCountry = c.getString("strCountry");
//                            strCountry1 = strCountry;
//                            strCity = c.getString("strCity");
//                            strCity1 = strCity;
//                            strPoster = c.getString("strPoster");
//                            strPoster1 = strPoster;
//                            strFanart = c.getString("strFanart");
//                            strFanart1 = strFanart;
//                            strThumb = c.getString("strThumb");
//                            strThumb1 = strThumb;
//                            strBanner = c.getString("strBanner");
//                            strBanner1 = strBanner;
//                            strMap = c.getString("strMap");
//                            strMap1 = strMap;
                            idEvent2 = c.getString("idEvent");
                            idSoccerXML2 = c.getString("idSoccerXML");
                            strEvent2 = c.getString("strEvent");
                            strFilename2 = c.getString("strFilename");
                            strSport2 = c.getString("strSport");
                            idLeague2 = c.getString("idLeague");
                            strLeague2 = c.getString("strLeague");
                            strSeason2 = c.getString("strSeason");
                            strHomeTeam2 = c.getString("strHomeTeam");
                            strAwayTeam2 = c.getString("strAwayTeam");
                            intRound2 = c.getString("intRound");
                            dateEvent2 = c.getString("dateEvent");
                            strDate2 = c.getString("strDate");
                            strTime2 = c.getString("strTime");
                            idHomeTeam2 = c.getString("idHomeTeam");
                            idAwayTeam2 = c.getString("idAwayTeam");
                            strLocked2 = c.getString("strLocked");

                            strDescriptionEN2 = c.getString("strDescriptionEN");
                            final Object strDescriptionEN3 = strDescriptionEN2;
                            intHomeScore2 = c.getString("intHomeScore");
                            Object intHomeScore3 = intHomeScore2;
                            intAwayScore2 = c.getString("intAwayScore");
                            Object intAwayScore3 = intAwayScore2;
                            intSpectators2 = c.getString("intSpectators");
                            Object  intSpectators3 = intSpectators2;
                            strHomeGoalDetails2 = c.getString("strHomeGoalDetails");
                            Object strHomeGoalDetails3 = strHomeGoalDetails2;
                            strHomeRedCards2 = c.getString("strHomeRedCards");
                            Object strHomeRedCards3 = strHomeRedCards2;
                            strHomeYellowCards2 = c.getString("strHomeYellowCards");
                            Object strHomeYellowCards3 = strHomeYellowCards2;
                            strHomeLineupGoalkeeper2 = c.getString("strHomeLineupGoalkeeper");
                            Object  strHomeLineupGoalkeeper3 = strHomeLineupGoalkeeper2;
                            strHomeLineupDefense2 = c.getString("strHomeLineupDefense");
                            Object strHomeLineupDefense3 = strHomeLineupDefense2;
                            strHomeLineupMidfield2 = c.getString("strHomeLineupMidfield");
                            Object strHomeLineupMidfield3 = strHomeLineupMidfield2;
                            strHomeLineupForward2 = c.getString("strHomeLineupForward");
                            Object strHomeLineupForward3 = strHomeLineupForward2;
                            strHomeLineupSubstitutes2 = c.getString("strHomeLineupSubstitutes");
                            Object strHomeLineupSubstitutes3 = strHomeLineupSubstitutes2;
                            strHomeFormation2 = c.getString("strHomeFormation");
                            Object strHomeFormation3 = strHomeFormation2;
                            strAwayRedCards2 = c.getString("strAwayRedCards");
                            Object strAwayRedCards3 = strAwayRedCards2;
                            strAwayYellowCards2 = c.getString("strAwayYellowCards");
                            Object strAwayYellowCards3 = strAwayYellowCards2;
                            strAwayGoalDetails2 = c.getString("strAwayGoalDetails");
                            Object strAwayGoalDetails3 = strAwayGoalDetails2;
                            strAwayLineupGoalkeeper2 = c.getString("strAwayLineupGoalkeeper");
                            Object strAwayLineupGoalkeeper3 = strAwayLineupGoalkeeper2;
                            strAwayLineupDefense2 = c.getString("strAwayLineupDefense");
                            Object strAwayLineupDefense3 = strAwayLineupDefense2;
                            strAwayLineupMidfield2 = c.getString("strAwayLineupMidfield");
                            Object strAwayLineupMidfield3 = strAwayLineupMidfield2;
                            strAwayLineupForward2 = c.getString("strAwayLineupForward");
                            Object strAwayLineupForward3 = strAwayLineupForward2;
                            strAwayLineupSubstitutes2 = c.getString("strAwayLineupSubstitutes");
                            Object  strAwayLineupSubstitutes3 = strAwayLineupSubstitutes2;
                            strAwayFormation2 = c.getString("strAwayFormation");
                            Object strAwayFormation3 = strAwayFormation2;
                            intHomeShots2 = c.getString("intHomeShots");
                            Object intHomeShots3 = intHomeShots2;
                            intAwayShots2 = c.getString("intAwayShots");
                            Object intAwayShots3 = intAwayShots2;
                            datetimeEventUTC2 = c.getString("dateEventLocal");
                            final Object datetimeEventUTC3 = datetimeEventUTC2;
                            strTVStation2 = c.getString("strTVStation");
                            final Object strTVStation3 = strTVStation2;
                            strResult2 = c.getString("strResult");
                            final Object strResult3 = strResult2;
                            strCircuit2 = c.getString("strCircuit");
                            final Object strCircuit3 = strCircuit2;
                            strCountry2 = c.getString("strCountry");
                            final Object strCountry3 = strCountry2;
                            strCity2 = c.getString("strCity");
                            final Object strCity3 = strCity2;
                            strPoster2 = c.getString("strPoster");
                            final Object strPoster3 = strPoster2;
                            strFanart2 = c.getString("strFanart");
                            final Object strFanart3 = strFanart2;
                            strThumb2 = c.getString("strThumb");
                            final Object strThumb3 = strThumb2;
                            strBanner2 = c.getString("strBanner");
                            final Object strBanner3 = strBanner2;
                            strMap2 = c.getString("strMap");
                            final Object strMap3 = strMap2;

                            String timeLocal = c.getString("strTimeLocal");
                            String tweet1 = c.getString("strTweet1");
                            String tweet2 = c.getString("strTweet2");
                            String tweet3 = c.getString("strTweet3");
                            String video = c.getString("strVideo");


                            DetailMatchObj o = new DetailMatchObj();

                            o.setIdEvent(idEvent2);
                            o.setIdSoccerXML(idSoccerXML2);
                            o.setStrEvent(strEvent2);
                            o.setStrFilename(strFilename2);
                            o.setStrSport(strSport2);
                            o.setIdLeague(idLeague2);
                            o.setStrLeague(strLeague2);
                            o.setStrSeason(strSeason2);
                            o.setStrHomeTeam(strHomeTeam2);
                            o.setStrAwayTeam(strAwayTeam2);
                            o.setIntRound(intRound2);
                            o.setDateEvent(dateEvent2);
                            o.setStrDate(strDate2);
                            o.setStrTime(strTime2);
                            o.setIdHomeTeam(idHomeTeam2);
                            o.setIdAwayTeam(idAwayTeam2);
                            o.setStrLocked(strLocked2);
                            o.setStrDescriptionEN(strDescriptionEN2);
//                            o.setIntHomeScore(intHomeScore3);
//                            o.setIntAwayScore(intAwayScore3);
                            if (intHomeScore3.equals("null")){
                                o.setIntHomeScore("-");
                            }else {
                                o.setIntHomeScore(intHomeScore2);
                            }

                            if (intAwayScore3.equals("null")){
                                o.setIntAwayScore("-");
                            }else {
                                o.setIntAwayScore(intAwayScore2);
                            }
                            o.setIntSpectators(intSpectators3);
                            o.setStrHomeGoalDetails(strHomeGoalDetails2);
                            o.setStrHomeRedCards(strHomeRedCards2);
                            o.setStrHomeYellowCards(strHomeYellowCards2);
                            o.setStrHomeLineupGoalkeeper(strHomeLineupGoalkeeper2);
                            o.setStrHomeLineupDefense(strHomeLineupDefense2);
                            o.setStrHomeLineupMidfield(strHomeLineupMidfield2);
                            o.setStrHomeLineupForward(strHomeLineupForward2);
                            o.setStrHomeLineupSubstitutes(strHomeLineupSubstitutes2);
                            o.setStrHomeFormation(strHomeFormation3);
                            o.setIntHomeShots(intHomeShots3);
                            o.setStrAwayGoalDetails(strAwayGoalDetails2);
                            o.setStrAwayRedCards(strAwayRedCards2);
                            o.setStrAwayYellowCards(strAwayYellowCards2);
                            o.setStrAwayLineupGoalkeeper(strAwayLineupGoalkeeper2);
                            o.setStrAwayLineupDefense(strAwayLineupDefense2);
                            o.setStrAwayLineupMidfield(strAwayLineupMidfield2);
                            o.setStrAwayLineupForward(strAwayLineupForward2);
                            o.setStrAwayLineupSubstitutes(strAwayLineupSubstitutes2);
                            o.setStrAwayFormation(strAwayFormation3);
                            o.setIntAwayShots(intAwayShots3);
                            o.setDateEventLocal(datetimeEventUTC2);
                            o.setStrTVStation(strTVStation3);
                            o.setStrResult(strResult2);
                            o.setStrCircuit(strCircuit3);
                            o.setStrCountry(strCountry3);
                            o.setStrCity(strCity3);
                            o.setStrPoster(strPoster3);
                            o.setStrFanart(strFanart3);
                            o.setStrThumb(strThumb2);
                            o.setStrBanner(strBanner3);
                            o.setStrMap(strMap3);
                            o.setStrTimeLocal(timeLocal);
                            o.setStrTweet1(tweet1);
                            o.setStrTweet2(tweet2);
                            o.setStrTweet3(tweet3);
                            o.setStrVideo(video);
                            detailMatches.add(o);


                            if (strHomeLineupGoalkeeper2.equals("null")){
                                gkHome.setText("-");
                            }else {
                                String strHomeLineupGoalkeeper1 = strHomeLineupGoalkeeper2.replace(";"," ");
                                gkHome.setText(strHomeLineupGoalkeeper1);
                            }

                            if (strAwayLineupGoalkeeper2.equals("null")){
                                gkAway.setText("-");
                            }else {
                                String strAwayLineupGoalkeeper1 = strAwayLineupGoalkeeper2.replace(";"," ");
                                gkAway.setText(strAwayLineupGoalkeeper1);
                            }

                            if (strHomeLineupDefense2.equals("null")){
                                dfHome.setText("-");
                            }else {
                                String strHomeLineupDefense1 = strHomeLineupDefense2.replace(";","\n");
                                dfHome.setText(strHomeLineupDefense1);
                            }

                            if (strAwayLineupDefense2.equals("null")){
                                dfAway.setText("-");
                            }else {
                                String strAwayLineupDefense1 = strAwayLineupDefense2.replace(";","\n");
                                dfAway.setText(strAwayLineupDefense1);
                            }

                            if (strHomeLineupMidfield2.equals("null")){
                                mdHome.setText("-");
                            }else {
                                String strHomeLineupMidfield1 = strHomeLineupMidfield2.replace(";","\n");
                                mdHome.setText(strHomeLineupMidfield1);
                            }

                            if (strAwayLineupMidfield2.equals("null")){
                                mdAway.setText("-");
                            }else {
                                String strAwayLineupMidfield1 = strAwayLineupMidfield2.replace(";","\n");
                                mdAway.setText(strAwayLineupMidfield1);
                            }

                            if (strHomeLineupForward2.equals("null")){
                                fwHome.setText("-");
                            }else {
                                String strHomeLineupForward1 = strHomeLineupForward2.replace(";","\n");
                                fwHome.setText(strHomeLineupForward1);
                            }

                            if (strAwayLineupForward2.equals("null")){
                                fwAway.setText("-");
                            }else {
                                String strAwayLineupForward1= strAwayLineupForward2.replace(";","\n");
                                fwAway.setText(strAwayLineupForward1);
                            }

                            if (strHomeLineupSubstitutes2.equals("null")){
                                subHome.setText("-");
                            }else {
                                String strHomeLineupSubstitutes1 = strHomeLineupSubstitutes2.replace(";","\n");
                                subHome.setText(strHomeLineupSubstitutes1);
                            }

                            if (strAwayLineupSubstitutes2.equals("null")){
                                subAway.setText("-");
                            }else {
                                String strAwayLineupSubstitutes1 = strAwayLineupSubstitutes2.replace(";","\n");
                                subAway.setText(strAwayLineupSubstitutes1);
                            }

                            if (strHomeYellowCards2.equals("null")){
                                ycHome.setText("-");
                            }else {
                                String strHomeYellowCards1 = strHomeYellowCards2.replace(";","\n");
                                ycHome.setText(strHomeYellowCards1);
                            }

                            if (strAwayYellowCards2.equals("null")){
                                ycAway.setText("-");
                            }else {
                                String strAwayYellowCards1 = strAwayYellowCards2.replace(";","\n");
                                ycAway.setText(strAwayYellowCards1);
                            }

                            if (strHomeRedCards2.equals("null")){
                                rcHome.setText("-");
                            }else {
                                String strHomeRedCards1 = strHomeRedCards2.replace(";","\n");
                                rcHome.setText(strHomeRedCards1);
                            }

                            if (strAwayRedCards2.equals("null")){
                                rcAway.setText("-");
                            }else {
                                String strAwayRedCards1 = strAwayRedCards2.replace(";","\n");
                                rcAway.setText(strAwayRedCards1);
                            }

                            if (strHomeGoalDetails2.equals("null")){
                                goalHome.setText("-");
                            }else {
                                String strHomeGoalDetails1 = strHomeGoalDetails2.replace(";","\n");
                                goalHome.setText(strHomeGoalDetails1);
                            }

                            if (strAwayLineupGoalkeeper2.equals("null")){
                                goalAway.setText("-");
                            }else {
                                String strAwayGoalDetails1 = strAwayGoalDetails2.replace(";","\n");
                                goalAway.setText(strAwayGoalDetails1);
                            }

                            if (intHomeShots2.equals("null")){
                                shotHome.setText("-");
                            }else {
                                String intHomeShots1 = intHomeShots2.replace(";","\n");
                                shotHome.setText(intHomeShots1);
                            }

                            if (intAwayShots2.equals("null")){
                                shotAway.setText("-");
                            }else {
                                String intAwayShots1 = intAwayShots2.replace(";","\n");
                                shotAway.setText(intAwayShots1);
                            }

                            if (intHomeScore2.equals("null")){
                                scoreHOme.setText("-");
                            }else {
                                String intHomeScore1 = intHomeScore2.replace(";","\n");
                                scoreHOme.setText(intHomeScore1);
                            }

                            if (intAwayScore2.equals("null")){
                                scoreAway.setText("-");
                            }else {
                                String intAwayScore1 = intAwayScore2.replace(";","\n");
                                scoreAway.setText(intAwayScore1);
                            }

                            if (dateEvent2.equals("null")){
                                tanggal.setText("-");
                            }else {
                                tanggal.setText(dateEvent2);
                            }
                            nama.setText(strEvent2);

                            Log.e("nextMatch", strEvent2);
                            Log.e("nextThumb",strThumb3.toString());

                            getDetHomeTeam(idHomeTeam2);
                            getDetAwayTeam(idAwayTeam2);

                            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            date = format.parse (dateEvent2);

                            if (/*date.before(currentDate)*/dateEvent2.compareTo(dateCurrent1) < 0){
                                final FavoriteLastMatchObj favObj1 = realm.where(FavoriteLastMatchObj.class).equalTo("idEvent",idEvent2).findFirst();
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

                                            favoriteLastMatchObj = new FavoriteLastMatchObj();
                                            favoriteLastMatchObj.setIdEvent(idEvent2);
                                            favoriteLastMatchObj.setIdEvent(idEvent2);
                                            favoriteLastMatchObj.setIdSoccerXML(idSoccerXML2);
                                            favoriteLastMatchObj.setStrEvent(strEvent2);
                                            favoriteLastMatchObj.setStrFilename(strFilename2);
                                            favoriteLastMatchObj.setStrSport(strSport2);
                                            favoriteLastMatchObj.setIdLeague(idLeague2);
                                            favoriteLastMatchObj.setStrLeague(strLeague2);
                                            favoriteLastMatchObj.setStrSeason(strSeason2);
                                            favoriteLastMatchObj.setStrHomeTeam(strHomeTeam2);
                                            favoriteLastMatchObj.setStrAwayTeam(strAwayTeam2);
                                            favoriteLastMatchObj.setIntRound(intRound2);
                                            favoriteLastMatchObj.setDateEvent(dateEvent2);
                                            favoriteLastMatchObj.setStrDate(strDate2);
                                            favoriteLastMatchObj.setStrTime(strTime2);
                                            favoriteLastMatchObj.setIdHomeTeam(idHomeTeam2);
                                            favoriteLastMatchObj.setIdAwayTeam(idAwayTeam2);
                                            favoriteLastMatchObj.setStrLocked(strLocked2);
                                            favoriteLastMatchObj.setStrDescriptionEN(strDescriptionEN3.toString());
                                            favoriteLastMatchObj.setIntHomeScore(intHomeScore2);
                                            favoriteLastMatchObj.setIntAwayScore(intAwayScore2);
                                            favoriteLastMatchObj.setIntSpectators(intSpectators2);
                                            favoriteLastMatchObj.setStrHomeGoalDetails(strHomeGoalDetails2);
                                            favoriteLastMatchObj.setStrHomeRedCards(strHomeRedCards2);
                                            favoriteLastMatchObj.setStrHomeYellowCards(strHomeYellowCards2);
                                            favoriteLastMatchObj.setStrHomeLineupGoalkeeper(strHomeLineupGoalkeeper2);
                                            favoriteLastMatchObj.setStrHomeLineupDefense(strHomeLineupDefense2);
                                            favoriteLastMatchObj.setStrHomeLineupMidfield(strHomeLineupMidfield2);
                                            favoriteLastMatchObj.setStrHomeLineupForward(strHomeLineupForward2);
                                            favoriteLastMatchObj.setStrHomeLineupSubstitutes(strHomeLineupSubstitutes2);
                                            favoriteLastMatchObj.setStrHomeFormation(strHomeFormation2);
                                            favoriteLastMatchObj.setIntHomeShots(intHomeShots2);
                                            favoriteLastMatchObj.setStrAwayGoalDetails(strAwayGoalDetails2);
                                            favoriteLastMatchObj.setStrAwayRedCards(strAwayRedCards2);
                                            favoriteLastMatchObj.setStrAwayYellowCards(strAwayYellowCards2);
                                            favoriteLastMatchObj.setStrAwayLineupGoalkeeper(strAwayLineupGoalkeeper2);
                                            favoriteLastMatchObj.setStrAwayLineupDefense(strAwayLineupDefense2);
                                            favoriteLastMatchObj.setStrAwayLineupMidfield(strAwayLineupMidfield2);
                                            favoriteLastMatchObj.setStrAwayLineupForward(strAwayLineupForward2);
                                            favoriteLastMatchObj.setStrAwayLineupSubstitutes(strAwayLineupSubstitutes2);
                                            favoriteLastMatchObj.setStrAwayFormation(strAwayFormation2);
                                            favoriteLastMatchObj.setIntAwayShots(intAwayShots2);
                                            favoriteLastMatchObj.setDatetimeEventUTC(datetimeEventUTC3.toString());
                                            favoriteLastMatchObj.setStrTVStation(strTVStation3.toString());
                                            favoriteLastMatchObj.setStrResult(strResult3.toString());
                                            favoriteLastMatchObj.setStrCircuit(strCircuit3.toString());
                                            favoriteLastMatchObj.setStrCountry(strCountry3.toString());
                                            favoriteLastMatchObj.setStrCity(strCity3.toString());
                                            favoriteLastMatchObj.setStrPoster(strPoster3.toString());
                                            favoriteLastMatchObj.setStrFanart(strFanart3.toString());
                                            favoriteLastMatchObj.setStrThumb(strThumb3.toString());
                                            favoriteLastMatchObj.setStrBanner(strBanner3.toString());
                                            favoriteLastMatchObj.setStrMap(strMap3.toString());

                                            realmHelper = new RealmHelper(realm);
                                            realmHelper.saveLastMatch(favoriteLastMatchObj);

                                            Toast.makeText(DetailMatch.this, "Disimpan ke Favorite!", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            fav.setBackgroundResource(R.drawable.ic_fav_wth);
                                            realmHelper = new RealmHelper(realm);
                                            realmHelper.deleteLastMatch(idEvent2);
                                            Log.e("id event",idEvent2);
                                            Toast.makeText(DetailMatch.this,"Item dihapus",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                            }else if (/*date.after(currentDate*/dateEvent2.compareTo(dateCurrent1) > 0){

                                final FavoriteNextMatchObj favObj1 = realm.where(FavoriteNextMatchObj.class).equalTo("idEvent",idEvent2).findFirst();

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

                                            favoriteNextMatchObj = new FavoriteNextMatchObj();

                                            favoriteNextMatchObj.setIdEvent(idEvent2);

                                            favoriteNextMatchObj.setIdEvent(idEvent2);
                                            favoriteNextMatchObj.setIdSoccerXML(idSoccerXML2);
                                            favoriteNextMatchObj.setStrEvent(strEvent2);
                                            favoriteNextMatchObj.setStrFilename(strFilename2);
                                            favoriteNextMatchObj.setStrSport(strSport2);
                                            favoriteNextMatchObj.setIdLeague(idLeague2);
                                            favoriteNextMatchObj.setStrLeague(strLeague2);
                                            favoriteNextMatchObj.setStrSeason(strSeason2);
                                            favoriteNextMatchObj.setStrHomeTeam(strHomeTeam2);
                                            favoriteNextMatchObj.setStrAwayTeam(strAwayTeam2);
                                            favoriteNextMatchObj.setIntRound(intRound2);
                                            favoriteNextMatchObj.setDateEvent(dateEvent2);
                                            favoriteNextMatchObj.setStrDate(strDate2);
                                            favoriteNextMatchObj.setStrTime(strTime2);
                                            favoriteNextMatchObj.setIdHomeTeam(idHomeTeam2);
                                            favoriteNextMatchObj.setIdAwayTeam(idAwayTeam2);
                                            favoriteNextMatchObj.setStrLocked(strLocked2);
                                            favoriteNextMatchObj.setStrDescriptionEN(strDescriptionEN3.toString());
                                            if (intHomeScore2.equals("null")){
                                                favoriteNextMatchObj.setIntHomeScore("-");
                                            }else {
                                                favoriteNextMatchObj.setIntHomeScore(intHomeScore2);
                                            }

                                            if (intAwayScore2.equals("null")){
                                                favoriteNextMatchObj.setIntAwayScore("-");
                                            }else {
                                                favoriteNextMatchObj.setIntAwayScore(intAwayScore2);
                                            }
                                            favoriteNextMatchObj.setIntSpectators(intSpectators2);
                                            favoriteNextMatchObj.setStrHomeGoalDetails(strHomeGoalDetails2);
                                            favoriteNextMatchObj.setStrHomeRedCards(strHomeRedCards2);
                                            favoriteNextMatchObj.setStrHomeYellowCards(strHomeYellowCards2);
                                            favoriteNextMatchObj.setStrHomeLineupGoalkeeper(strHomeLineupGoalkeeper2);
                                            favoriteNextMatchObj.setStrHomeLineupDefense(strHomeLineupDefense2);
                                            favoriteNextMatchObj.setStrHomeLineupMidfield(strHomeLineupMidfield2);
                                            favoriteNextMatchObj.setStrHomeLineupForward(strHomeLineupForward2);
                                            favoriteNextMatchObj.setStrHomeLineupSubstitutes(strHomeLineupSubstitutes2);
                                            favoriteNextMatchObj.setStrHomeFormation(strHomeFormation2);
                                            favoriteNextMatchObj.setIntHomeShots(intHomeShots2);
                                            favoriteNextMatchObj.setStrAwayGoalDetails(strAwayGoalDetails2);
                                            favoriteNextMatchObj.setStrAwayRedCards(strAwayRedCards2);
                                            favoriteNextMatchObj.setStrAwayYellowCards(strAwayYellowCards2);
                                            favoriteNextMatchObj.setStrAwayLineupGoalkeeper(strAwayLineupGoalkeeper2);
                                            favoriteNextMatchObj.setStrAwayLineupDefense(strAwayLineupDefense2);
                                            favoriteNextMatchObj.setStrAwayLineupMidfield(strAwayLineupMidfield2);
                                            favoriteNextMatchObj.setStrAwayLineupForward(strAwayLineupForward2);
                                            favoriteNextMatchObj.setStrAwayLineupSubstitutes(strAwayLineupSubstitutes2);
                                            favoriteNextMatchObj.setStrAwayFormation(strAwayFormation2);
                                            favoriteNextMatchObj.setIntAwayShots(intAwayShots2);
                                            favoriteNextMatchObj.setDatetimeEventUTC(datetimeEventUTC3.toString());
                                            favoriteNextMatchObj.setStrTVStation(strTVStation3.toString());
                                            favoriteNextMatchObj.setStrResult(strResult3.toString());
                                            favoriteNextMatchObj.setStrCircuit(strCircuit3.toString());
                                            favoriteNextMatchObj.setStrCountry(strCountry3.toString());
                                            favoriteNextMatchObj.setStrCity(strCity3.toString());
                                            favoriteNextMatchObj.setStrPoster(strPoster3.toString());
                                            favoriteNextMatchObj.setStrFanart(strFanart3.toString());
                                            favoriteNextMatchObj.setStrThumb(strThumb3.toString());
                                            favoriteNextMatchObj.setStrBanner(strBanner3.toString());
                                            favoriteNextMatchObj.setStrMap(strMap3.toString());

                                            realmHelper = new RealmHelper(realm);
                                            realmHelper.saveNextMatch(favoriteNextMatchObj);

                                            Toast.makeText(DetailMatch.this, "Disimpan ke Favorite!", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            fav.setBackgroundResource(R.drawable.ic_fav_wth);
                                            realmHelper = new RealmHelper(realm);
                                            realmHelper.deleteNextMatch(idEvent2);
                                            Log.e("id event",idEvent2);
                                            Toast.makeText(DetailMatch.this,"Item dihapus",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                            else if (dateEvent2.compareTo(dateCurrent1) == 0){
                                final FavoriteNextMatchObj favObj1 = realm.where(FavoriteNextMatchObj.class).equalTo("idEvent",idEvent2).findFirst();

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

                                            favoriteNextMatchObj = new FavoriteNextMatchObj();

                                            favoriteNextMatchObj.setIdEvent(idEvent2);

                                            favoriteNextMatchObj.setIdEvent(idEvent2);
                                            favoriteNextMatchObj.setIdSoccerXML(idSoccerXML2);
                                            favoriteNextMatchObj.setStrEvent(strEvent2);
                                            favoriteNextMatchObj.setStrFilename(strFilename2);
                                            favoriteNextMatchObj.setStrSport(strSport2);
                                            favoriteNextMatchObj.setIdLeague(idLeague2);
                                            favoriteNextMatchObj.setStrLeague(strLeague2);
                                            favoriteNextMatchObj.setStrSeason(strSeason2);
                                            favoriteNextMatchObj.setStrHomeTeam(strHomeTeam2);
                                            favoriteNextMatchObj.setStrAwayTeam(strAwayTeam2);
                                            favoriteNextMatchObj.setIntRound(intRound2);
                                            favoriteNextMatchObj.setDateEvent(dateEvent2);
                                            favoriteNextMatchObj.setStrDate(strDate2);
                                            favoriteNextMatchObj.setStrTime(strTime2);
                                            favoriteNextMatchObj.setIdHomeTeam(idHomeTeam2);
                                            favoriteNextMatchObj.setIdAwayTeam(idAwayTeam2);
                                            favoriteNextMatchObj.setStrLocked(strLocked2);
                                            favoriteNextMatchObj.setStrDescriptionEN(strDescriptionEN3.toString());
                                            if (intHomeScore2.equals("null")){
                                                favoriteNextMatchObj.setIntHomeScore("-");
                                            }else {
                                                favoriteNextMatchObj.setIntHomeScore(intHomeScore2);
                                            }

                                            if (intAwayScore2.equals("null")){
                                                favoriteNextMatchObj.setIntAwayScore("-");
                                            }else {
                                                favoriteNextMatchObj.setIntAwayScore(intAwayScore2);
                                            }
                                            favoriteNextMatchObj.setIntSpectators(intSpectators2);
                                            favoriteNextMatchObj.setStrHomeGoalDetails(strHomeGoalDetails2);
                                            favoriteNextMatchObj.setStrHomeRedCards(strHomeRedCards2);
                                            favoriteNextMatchObj.setStrHomeYellowCards(strHomeYellowCards2);
                                            favoriteNextMatchObj.setStrHomeLineupGoalkeeper(strHomeLineupGoalkeeper2);
                                            favoriteNextMatchObj.setStrHomeLineupDefense(strHomeLineupDefense2);
                                            favoriteNextMatchObj.setStrHomeLineupMidfield(strHomeLineupMidfield2);
                                            favoriteNextMatchObj.setStrHomeLineupForward(strHomeLineupForward2);
                                            favoriteNextMatchObj.setStrHomeLineupSubstitutes(strHomeLineupSubstitutes2);
                                            favoriteNextMatchObj.setStrHomeFormation(strHomeFormation2);
                                            favoriteNextMatchObj.setIntHomeShots(intHomeShots2);
                                            favoriteNextMatchObj.setStrAwayGoalDetails(strAwayGoalDetails2);
                                            favoriteNextMatchObj.setStrAwayRedCards(strAwayRedCards2);
                                            favoriteNextMatchObj.setStrAwayYellowCards(strAwayYellowCards2);
                                            favoriteNextMatchObj.setStrAwayLineupGoalkeeper(strAwayLineupGoalkeeper2);
                                            favoriteNextMatchObj.setStrAwayLineupDefense(strAwayLineupDefense2);
                                            favoriteNextMatchObj.setStrAwayLineupMidfield(strAwayLineupMidfield2);
                                            favoriteNextMatchObj.setStrAwayLineupForward(strAwayLineupForward2);
                                            favoriteNextMatchObj.setStrAwayLineupSubstitutes(strAwayLineupSubstitutes2);
                                            favoriteNextMatchObj.setStrAwayFormation(strAwayFormation2);
                                            favoriteNextMatchObj.setIntAwayShots(intAwayShots2);
                                            favoriteNextMatchObj.setDatetimeEventUTC(datetimeEventUTC3.toString());
                                            favoriteNextMatchObj.setStrTVStation(strTVStation3.toString());
                                            favoriteNextMatchObj.setStrResult(strResult3.toString());
                                            favoriteNextMatchObj.setStrCircuit(strCircuit3.toString());
                                            favoriteNextMatchObj.setStrCountry(strCountry3.toString());
                                            favoriteNextMatchObj.setStrCity(strCity3.toString());
                                            favoriteNextMatchObj.setStrPoster(strPoster3.toString());
                                            favoriteNextMatchObj.setStrFanart(strFanart3.toString());
                                            favoriteNextMatchObj.setStrThumb(strThumb3.toString());
                                            favoriteNextMatchObj.setStrBanner(strBanner3.toString());
                                            favoriteNextMatchObj.setStrMap(strMap3.toString());

                                            realmHelper = new RealmHelper(realm);
                                            realmHelper.saveNextMatch(favoriteNextMatchObj);

                                            Toast.makeText(DetailMatch.this, "Disimpan ke Favorite!", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            fav.setBackgroundResource(R.drawable.ic_fav_wth);
                                            realmHelper = new RealmHelper(realm);
                                            realmHelper.deleteNextMatch(idEvent2);
                                            Log.e("id event",idEvent2);
                                            Toast.makeText(DetailMatch.this,"Item dihapus",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }

                            Log.e("date", date.toString());

                        }
                    } catch (JSONException e) {
                        Toast.makeText(DetailMatch.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Souldn't get json from server.1");
                    DetailMatch.this.runOnUiThread(new Runnable() {
                        @Override
                        public void
                        run() {
                            Toast.makeText(DetailMatch.this.getApplicationContext(), "Couldn't get json from server. Check LoCat for possible errors!1",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailMatch.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDetHomeTeam(String idku){
//        progressDialog = new ProgressDialog(DetailMatch.this);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
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

                            Picasso.with(DetailMatch.this).load(strTeamBadgeH).placeholder(R.drawable.ic_placeholder_wth).into(iconHome);
                            Picasso.with(DetailMatch.this).load(strStadiumThumbH).placeholder(R.drawable.ic_placeholder_wth).into(imgCollapse);


                            Log.e("std", strStadiumH);
                            Log.e("std",strStadiumThumbH);
                            Log.e("std",strTeamBadgeH);


                        }
                    } catch (JSONException e) {
                        Toast.makeText(DetailMatch.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Souldn't get json from server.1");
                    DetailMatch.this.runOnUiThread(new Runnable() {
                        @Override
                        public void
                        run() {
                            Toast.makeText(DetailMatch.this.getApplicationContext(), "Couldn't get json from server. Check LoCat for possible errors!1",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailMatch.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDetAwayTeam(String idku){
//        progressDialog = new ProgressDialog(DetailMatch.this);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
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

                            Picasso.with(DetailMatch.this).load(strTeamBadgeA).placeholder(R.drawable.ic_placeholder_wth).into(iconAway);
                            Log.e("std", strStadiumA);
                            Log.e("std",strStadiumThumbA);
                            Log.e("std",strTeamBadgeA);


                        }
                    } catch (JSONException e) {
                        Toast.makeText(DetailMatch.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Souldn't get json from server.1");
                    DetailMatch.this.runOnUiThread(new Runnable() {
                        @Override
                        public void
                        run() {
                            Toast.makeText(DetailMatch.this.getApplicationContext(), "Couldn't get json from server. Check LoCat for possible errors!1",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailMatch.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
