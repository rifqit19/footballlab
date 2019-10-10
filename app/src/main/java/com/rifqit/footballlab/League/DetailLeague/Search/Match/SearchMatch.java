package com.rifqit.footballlab.League.DetailLeague.Search.Match;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rifqit.footballlab.GetDataService;
import com.rifqit.footballlab.R;
import com.rifqit.footballlab.RetrofitCilentInstance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMatch extends AppCompatActivity {

    ImageButton back;
    private String TAG = SearchMatch.class.getSimpleName();
    ArrayList<SearchMatchObj> searchMatchObjs = new ArrayList<>();
    ProgressDialog progressDialog;
    AdapterSearchMatch adapterSearchMatch;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    RelativeLayout empltLyt;

    String idEvent, strEvent, strFilename, strSport, idLeague, strLeague, strSeason, strDescriptionEN, strHomeTeam, strAwayTeam, intHomeScore, intRound, intAwayScore,dateEvent,strTime, idHomeTeam, idAwayTeam, strResult,strThumb,strLocked, idSoccerXML,intSpectators, strHomeGoalDetails, strHomeRedCards, strHomeYellowCards, strHomeLineupGoalkeeper, strHomeLineupDefense, strHomeLineupMidfield, strHomeLineupForward, strHomeLineupSubstitutes, strHomeFormation, strAwayRedCards, strAwayYellowCards, strAwayGoalDetails, strAwayLineupGoalkeeper, strAwayLineupDefense, strAwayLineupMidfield, strAwayLineupForward, strAwayLineupSubstitutes, strAwayFormation, intHomeShots, intAwayShots,datetimeEventUTC, strDate,strTVStation, strCircuit, strCountry, strCity, strPoster, strFanart,strBanner, strMap;

    Object idSoccerXML1,intSpectators1, strHomeGoalDetails1, strHomeRedCards1, strHomeYellowCards1, strHomeLineupGoalkeeper1, strHomeLineupDefense1, strHomeLineupMidfield1, strHomeLineupForward1, strHomeLineupSubstitutes1, strHomeFormation1, strAwayRedCards1, strAwayYellowCards1, strAwayGoalDetails1, strAwayLineupGoalkeeper1, strAwayLineupDefense1, strAwayLineupMidfield1, strAwayLineupForward1, strAwayLineupSubstitutes1, strAwayFormation1, intHomeShots1, intAwayShots1,datetimeEventUTC1, strDate1,strTVStation1, strCircuit1, strCountry1, strCity1, strPoster1, strFanart1,strBanner1, strMap1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_match);

        back = findViewById(R.id.backSearch2);
        progressBar = findViewById(R.id.progressbarSearchTeam2);
        empltLyt = findViewById(R.id.relEmpty2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarhhhh2);
        setSupportActionBar(toolbar);
        toolbar.setTitle(" ");

        recyclerView = findViewById(R.id.recyclerViewSearchMatch);
        adapterSearchMatch = new AdapterSearchMatch(this,searchMatchObjs);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterSearchMatch);
        recyclerView.setNestedScrollingEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final SearchView searchView = (SearchView) findViewById(R.id.searchhhh2);
        searchView.setQueryHint("Team Match Name");
        searchView.setIconified(false);
        searchView.setFocusable(true);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                onBackPressed();
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String query) {
                getMatch(query);
                empltLyt.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String s) {
//                getTeam(s);
//                empltLyt.setVisibility(View.GONE);
//                recyclerView.setVisibility(View.VISIBLE);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    public void getMatch(String query){

        progressBar.setVisibility(View.VISIBLE);
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getResultMatch(query);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                progressDialog.dismiss();
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        JSONObject jsonObj = new JSONObject(respon);
                        searchMatchObjs.clear();
                        JSONArray api = jsonObj.getJSONArray("event");
                        boolean isSave = true;
                        for (int i = 0; i < api.length(); i++){
                            JSONObject c = api.getJSONObject(i);

                            idEvent = c.getString("idEvent");
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
                            strTime = c.getString("strTime");
                            idHomeTeam = c.getString("idHomeTeam");
                            idAwayTeam = c.getString("idAwayTeam");
                            strLocked = c.getString("strLocked");
                            strDescriptionEN = c.getString("strDescriptionEN");
                            intHomeScore = c.getString("intHomeScore");
                            intAwayScore = c.getString("intAwayScore");
                            strResult = c.getString("strResult");
                            strThumb = c.getString("strThumb");

                            strDate = c.getString("strDate");
                            strDate1 = strDate;
                            idSoccerXML = c.getString("idSoccerXML");
                            idSoccerXML1 = idSoccerXML;
                            intSpectators = c.getString("intSpectators");
                            intSpectators1 = intSpectators;
                            strHomeGoalDetails = c.getString("strHomeGoalDetails");
                            strHomeGoalDetails1 = strHomeGoalDetails;
                            strHomeRedCards = c.getString("strHomeRedCards");
                            strHomeRedCards1 = strHomeRedCards;
                            strHomeYellowCards = c.getString("strHomeYellowCards");
                            strHomeYellowCards1 = strHomeYellowCards;
                            strHomeLineupGoalkeeper = c.getString("strHomeLineupGoalkeeper");
                            strHomeLineupGoalkeeper1 = strHomeLineupGoalkeeper;
                            strHomeLineupDefense = c.getString("strHomeLineupDefense");
                            strHomeLineupDefense1 = strHomeLineupDefense;
                            strHomeLineupMidfield = c.getString("strHomeLineupMidfield");
                            strHomeLineupMidfield1 = strHomeLineupMidfield;
                            strHomeLineupForward = c.getString("strHomeLineupForward");
                            strHomeLineupForward1 = strHomeLineupForward;
                            strHomeLineupSubstitutes = c.getString("strHomeLineupSubstitutes");
                            strHomeLineupSubstitutes1 = strHomeLineupSubstitutes;
                            strHomeFormation = c.getString("strHomeFormation");
                            strHomeFormation1 = strHomeFormation;
                            strAwayRedCards = c.getString("strAwayRedCards");
                            strAwayRedCards1 = strAwayRedCards;
                            strAwayYellowCards = c.getString("strAwayYellowCards");
                            strAwayYellowCards1 = strAwayYellowCards;
                            strAwayGoalDetails = c.getString("strAwayGoalDetails");
                            strAwayGoalDetails1 = strAwayGoalDetails;
                            strAwayLineupGoalkeeper = c.getString("strAwayLineupGoalkeeper");
                            strAwayLineupGoalkeeper1 = strAwayLineupGoalkeeper;
                            strAwayLineupDefense = c.getString("strAwayLineupDefense");
                            strAwayLineupDefense1 = strAwayLineupDefense;
                            strAwayLineupMidfield = c.getString("strAwayLineupMidfield");
                            strAwayLineupMidfield1 = strAwayLineupMidfield;
                            strAwayLineupForward = c.getString("strAwayLineupForward");
                            strAwayLineupForward1 = strAwayLineupForward;
                            strAwayLineupSubstitutes = c.getString("strAwayLineupSubstitutes");
                            strAwayLineupSubstitutes1 = strAwayLineupSubstitutes;
                            strAwayFormation = c.getString("strAwayFormation");
                            strAwayFormation1 = strAwayFormation;
                            intHomeShots = c.getString("intHomeShots");
                            intHomeShots1 = intHomeShots;
                            intAwayShots = c.getString("intAwayShots");
                            intAwayShots1 = intAwayShots;
                            datetimeEventUTC = c.getString("datetimeEventUTC");
                            datetimeEventUTC1 = datetimeEventUTC;
                            strTVStation = c.getString("strTVStation");
                            strTVStation1 = strTVStation;
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
                            strBanner = c.getString("strBanner");
                            strBanner1 = strBanner;
                            strMap = c.getString("strMap");
                            strMap1 = strMap;

                            SearchMatchObj o = new SearchMatchObj();

                            o.setIdEvent(idEvent);
                            o.setIdSoccerXML(idSoccerXML1);
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
                            o.setStrDate(strDate1);
                            o.setStrTime(strTime);
                            o.setIdHomeTeam(idHomeTeam);
                            o.setIdAwayTeam(idAwayTeam);
                            o.setStrLocked(strLocked);
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
                            o.setIntSpectators(intSpectators1);
                            o.setStrHomeGoalDetails(strHomeGoalDetails1);
                            o.setStrHomeRedCards(strHomeRedCards1);
                            o.setStrHomeYellowCards(strHomeYellowCards1);
                            o.setStrHomeLineupGoalkeeper(strHomeLineupGoalkeeper1);
                            o.setStrHomeLineupDefense(strHomeLineupDefense1);
                            o.setStrHomeLineupMidfield(strHomeLineupMidfield1);
                            o.setStrHomeLineupForward(strHomeLineupForward1);
                            o.setStrHomeLineupSubstitutes(strHomeLineupSubstitutes1);
                            o.setStrHomeFormation(strHomeFormation1);
                            o.setIntHomeShots(intHomeShots1);
                            o.setStrAwayGoalDetails(strAwayGoalDetails1);
                            o.setStrAwayRedCards(strAwayRedCards1);
                            o.setStrAwayYellowCards(strAwayYellowCards1);
                            o.setStrAwayLineupGoalkeeper(strAwayLineupGoalkeeper1);
                            o.setStrAwayLineupDefense(strAwayLineupDefense1);
                            o.setStrAwayLineupMidfield(strAwayLineupMidfield1);
                            o.setStrAwayLineupForward(strAwayLineupForward1);
                            o.setStrAwayLineupSubstitutes(strAwayLineupSubstitutes1);
                            o.setStrAwayFormation(strAwayFormation1);
                            o.setIntAwayShots(intAwayShots1);
                            o.setDatetimeEventUTC(datetimeEventUTC1);
                            o.setStrTVStation(strTVStation1);
                            o.setStrResult(strResult);
                            o.setStrCircuit(strCircuit1);
                            o.setStrCountry(strCountry1);
                            o.setStrCity(strCity1);
                            o.setStrPoster(strPoster1);
                            o.setStrFanart(strFanart1);
                            o.setStrThumb(strThumb);
                            o.setStrBanner(strBanner1);
                            o.setStrMap(strMap1);

                            if (strSport.equalsIgnoreCase("Soccer")){
                                isSave = true;
                            }else {
                                isSave = false;
                                continue;
                            }
                            if (isSave = true)
                                searchMatchObjs.add(o);

                        }
                        adapterSearchMatch.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Toast.makeText(SearchMatch.this, "No Result", Toast.LENGTH_SHORT).show();
                        recyclerView.setVisibility(View.GONE);
                        empltLyt.setVisibility(View.VISIBLE);
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Souldn't get json from server.1");
                    SearchMatch.this.runOnUiThread(new Runnable() {
                        @Override
                        public void
                        run() {
                            Toast.makeText(SearchMatch.this.getApplicationContext(), "Couldn't get json from server. Check LoCat for possible errors!1",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                progressDialog.dismiss();
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SearchMatch.this, "No Result", Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(View.GONE);
                empltLyt.setVisibility(View.VISIBLE);
            }
        });
    }
}
