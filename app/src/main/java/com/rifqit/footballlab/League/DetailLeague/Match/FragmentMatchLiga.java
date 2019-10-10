package com.rifqit.footballlab.League.DetailLeague.Match;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rifqit.footballlab.GetDataService;
import com.rifqit.footballlab.League.DetailLeague.DetailLiga;
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

public class FragmentMatchLiga extends Fragment {

    private String TAG = FragmentMatchLiga.class.getSimpleName();
    ArrayList<NextMatchObj> nextMatchObjs = new ArrayList<>();
    ProgressDialog progressDialog;
    AdapterNextMatch adapterMatch;
    ArrayList<LastMatchObj> lastMatchObjs = new ArrayList<>();
    AdapterLastMatch adapterLastMatch;
    RecyclerView recyclerView, recyclerView2;
    ProgressBar progressBar,progressBar2;
    String idEvent,idSoccerXML,strEvent,strFilename,strSport,idLeague,strLeague,strSeason,strHomeTeam,strAwayTeam,intRound,dateEvent,strDate,strTime,idHomeTeam,idAwayTeam,strLocked,strDescriptionEN,intHomeScore,intAwayScore,intSpectators,strHomeGoalDetails,strHomeRedCards,strHomeYellowCards,strHomeLineupGoalkeeper,strHomeLineupDefense,strHomeLineupMidfield,strHomeLineupForward,strHomeLineupSubstitutes,strHomeFormation,strAwayRedCards,strAwayYellowCards,strAwayGoalDetails,strAwayLineupGoalkeeper,strAwayLineupDefense,strAwayLineupMidfield,strAwayLineupForward,strAwayLineupSubstitutes,strAwayFormation,intHomeShots,intAwayShots,datetimeEventUTC,strTVStation,strResult,strCircuit,strCountry,strCity,strPoster,strFanart,strThumb,strBanner,strMap;
    String idEvent2,idSoccerXML2,strEvent2,strFilename2,strSport2,idLeague2,strLeague2,strSeason2,strHomeTeam2,strAwayTeam2,intRound2,dateEvent2,strDate2,strTime2,idHomeTeam2,idAwayTeam2,strLocked2,strDescriptionEN2,intHomeScore2,intAwayScore2,intSpectators2,strHomeGoalDetails2,strHomeRedCards2,strHomeYellowCards2,strHomeLineupGoalkeeper2,strHomeLineupDefense2,strHomeLineupMidfield2,strHomeLineupForward2,strHomeLineupSubstitutes2,strHomeFormation2,strAwayRedCards2,strAwayYellowCards2,strAwayGoalDetails2,strAwayLineupGoalkeeper2,strAwayLineupDefense2,strAwayLineupMidfield2,strAwayLineupForward2,strAwayLineupSubstitutes2,strAwayFormation2,intHomeShots2,intAwayShots2,datetimeEventUTC2,strTVStation2,strResult2,strCircuit2,strCountry2,strCity2,strPoster2,strFanart2,strThumb2,strBanner2,strMap2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_match_liga, container, false);

        progressBar = v.findViewById(R.id.progressbarMatch);
        progressBar2 = v.findViewById(R.id.progressbarMatch2);
        String id = ((DetailLiga) getActivity()).getIdLeague();
        Log.e("idLigaMatch", id+"");
        recyclerView = v.findViewById(R.id.recyclerNextMatch);
        recyclerView2 = v.findViewById(R.id.recyclerLastMatch);

        adapterMatch = new AdapterNextMatch(getActivity(),nextMatchObjs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity() ,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(adapterMatch);


        adapterLastMatch = new AdapterLastMatch(getActivity(),lastMatchObjs);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity() ,LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setNestedScrollingEnabled(true);
        recyclerView2.setAdapter(adapterLastMatch);

        getNextMatch(id);

        getLastMatch(id);

        return v;
    }

    public void getNextMatch(String idku){
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getNextMatch(idku);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                progressDialog.dismiss();
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        JSONObject jsonObj = new JSONObject(respon);
                        nextMatchObjs.clear();
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
                            strTime = c.getString("strTime");
                            idHomeTeam = c.getString("idHomeTeam");
                            idAwayTeam = c.getString("idAwayTeam");
                            strLocked = c.getString("strLocked");

                            strDescriptionEN = c.getString("strDescriptionEN");
                            Object strDescriptionEN1 = strDescriptionEN;
                            intHomeScore = c.getString("intHomeScore");
                            Object intHomeScore1 = intHomeScore;
                            intAwayScore = c.getString("intAwayScore");
                            Object intAwayScore1 = intAwayScore;
                            intSpectators = c.getString("intSpectators");
                            Object  intSpectators1 = intSpectators;
                            strHomeGoalDetails = c.getString("strHomeGoalDetails");
                            Object strHomeGoalDetails1 = strHomeGoalDetails;
                            strHomeRedCards = c.getString("strHomeRedCards");
                            Object strHomeRedCards1 = strHomeRedCards;
                            strHomeYellowCards = c.getString("strHomeYellowCards");
                            Object strHomeYellowCards1 = strHomeYellowCards;
                            strHomeLineupGoalkeeper = c.getString("strHomeLineupGoalkeeper");
                            Object  strHomeLineupGoalkeeper1 = strHomeLineupGoalkeeper;
                            strHomeLineupDefense = c.getString("strHomeLineupDefense");
                            Object strHomeLineupDefense1 = strHomeLineupDefense;
                            strHomeLineupMidfield = c.getString("strHomeLineupMidfield");
                            Object strHomeLineupMidfield1 = strHomeLineupMidfield;
                            strHomeLineupForward = c.getString("strHomeLineupForward");
                            Object strHomeLineupForward1 = strHomeLineupForward;
                            strHomeLineupSubstitutes = c.getString("strHomeLineupSubstitutes");
                            Object strHomeLineupSubstitutes1 = strHomeLineupSubstitutes;
                            strHomeFormation = c.getString("strHomeFormation");
                            Object strHomeFormation1 = strHomeFormation;
                            strAwayRedCards = c.getString("strAwayRedCards");
                            Object strAwayRedCards1 = strAwayRedCards;
                            strAwayYellowCards = c.getString("strAwayYellowCards");
                            Object strAwayYellowCards1 = strAwayYellowCards;
                            strAwayGoalDetails = c.getString("strAwayGoalDetails");
                            Object strAwayGoalDetails1 = strAwayGoalDetails;
                            strAwayLineupGoalkeeper = c.getString("strAwayLineupGoalkeeper");
                            Object strAwayLineupGoalkeeper1 = strAwayLineupGoalkeeper;
                            strAwayLineupDefense = c.getString("strAwayLineupDefense");
                            Object strAwayLineupDefense1 = strAwayLineupDefense;
                            strAwayLineupMidfield = c.getString("strAwayLineupMidfield");
                            Object strAwayLineupMidfield1 = strAwayLineupMidfield;
                            strAwayLineupForward = c.getString("strAwayLineupForward");
                            Object strAwayLineupForward1 = strAwayLineupForward;
                            strAwayLineupSubstitutes = c.getString("strAwayLineupSubstitutes");
                            Object  strAwayLineupSubstitutes1 = strAwayLineupSubstitutes;
                            strAwayFormation = c.getString("strAwayFormation");
                            Object strAwayFormation1 = strAwayFormation;
                            intHomeShots = c.getString("intHomeShots");
                            Object intHomeShots1 = intHomeShots;
                            intAwayShots = c.getString("intAwayShots");
                            Object intAwayShots1 = intAwayShots;
                            datetimeEventUTC = c.getString("dateEventLocal");
                            Object datetimeEventUTC1 = datetimeEventUTC;
                            strTVStation = c.getString("strTVStation");
                            Object strTVStation1 = strTVStation;
                            strResult = c.getString("strResult");
                            Object strResult1 = strResult;
                            strCircuit = c.getString("strCircuit");
                            Object strCircuit1 = strCircuit;
                            strCountry = c.getString("strCountry");
                            Object strCountry1 = strCountry;
                            strCity = c.getString("strCity");
                            Object strCity1 = strCity;
                            strPoster = c.getString("strPoster");
                            Object strPoster1 = strPoster;
                            strFanart = c.getString("strFanart");
                            Object strFanart1 = strFanart;
                            strThumb = c.getString("strThumb");
                            Object strThumb1 = strThumb;
                            strBanner = c.getString("strBanner");
                            Object strBanner1 = strBanner;
                            strMap = c.getString("strMap");
                            Object strMap1 = strMap;
                            String timeLocal = c.getString("strTimeLocal");
                            String tweet1 = c.getString("strTweet1");
                            String tweet2 = c.getString("strTweet2");
                            String tweet3 = c.getString("strTweet3");
                            String video = c.getString("strVideo");

                            NextMatchObj o = new NextMatchObj();

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
                            o.setIntHomeScore("-");
                            o.setIntAwayScore("-");
                            o.setIntSpectators(intSpectators1);
                            o.setStrHomeGoalDetails(strHomeGoalDetails);
                            o.setStrHomeRedCards(strHomeRedCards);
                            o.setStrHomeYellowCards(strHomeYellowCards);
                            o.setStrHomeLineupGoalkeeper(strHomeLineupGoalkeeper);
                            o.setStrHomeLineupDefense(strHomeLineupDefense);
                            o.setStrHomeLineupMidfield(strHomeLineupMidfield);
                            o.setStrHomeLineupForward(strHomeLineupForward);
                            o.setStrHomeLineupSubstitutes(strHomeLineupSubstitutes);
                            o.setStrHomeFormation(strHomeFormation1);
                            o.setIntHomeShots(intHomeShots1);
                            o.setStrAwayGoalDetails(strAwayGoalDetails);
                            o.setStrAwayRedCards(strAwayRedCards);
                            o.setStrAwayYellowCards(strAwayYellowCards);
                            o.setStrAwayLineupGoalkeeper(strAwayLineupGoalkeeper);
                            o.setStrAwayLineupDefense(strAwayLineupDefense);
                            o.setStrAwayLineupMidfield(strAwayLineupMidfield);
                            o.setStrAwayLineupForward(strAwayLineupForward);
                            o.setStrAwayLineupSubstitutes(strAwayLineupSubstitutes);
                            o.setStrAwayFormation(strAwayFormation1);
                            o.setIntAwayShots(intAwayShots1);
                            o.setDateEventLocal(datetimeEventUTC1);
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
                            o.setStrTimeLocal(timeLocal);
                            o.setStrTweet1(tweet1);
                            o.setStrTweet2(tweet2);
                            o.setStrTweet3(tweet3);
                            o.setStrVideo(video);
                            nextMatchObjs.add(o);

                            Log.e("nextMatch", strEvent);
                            Log.e("nextThumb",strThumb1.toString());


                        }
                        adapterMatch.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Toast.makeText(getActivity(), "No Result for Next Match", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Souldn't get json from server.1");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void
                        run() {
                            Toast.makeText(getActivity().getApplicationContext(), "Error Connection"/*"Couldn't get json from server. Check LoCat for possible errors!1"*/,Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                progressDialog.dismiss();
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "No Result for Next Match", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getLastMatch(String idku){
        progressBar2.setVisibility(View.VISIBLE);
        recyclerView2.setVisibility(View.GONE);
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getLastMatch(idku);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                progressDialog.dismiss();
                recyclerView2.setVisibility(View.VISIBLE);
                progressBar2.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        JSONObject jsonObj = new JSONObject(respon);
                        lastMatchObjs.clear();
                        JSONArray api = jsonObj.getJSONArray("events");
                        for (int i = 0; i < api.length(); i++){
                            JSONObject c = api.getJSONObject(i);

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
                            Object strDescriptionEN3 = strDescriptionEN2;
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
                            Object datetimeEventUTC3 = datetimeEventUTC2;
                            strTVStation2 = c.getString("strTVStation");
                            Object strTVStation3 = strTVStation2;
                            strResult2 = c.getString("strResult");
                            Object strResult3 = strResult2;
                            strCircuit2 = c.getString("strCircuit");
                            Object strCircuit3 = strCircuit2;
                            strCountry2 = c.getString("strCountry");
                            Object strCountry3 = strCountry2;
                            strCity2 = c.getString("strCity");
                            Object strCity3 = strCity2;
                            strPoster2 = c.getString("strPoster");
                            Object strPoster3 = strPoster2;
                            strFanart2 = c.getString("strFanart");
                            Object strFanart3 = strFanart2;
                            strThumb2 = c.getString("strThumb");
                            Object strThumb3 = strThumb2;
                            strBanner2 = c.getString("strBanner");
                            Object strBanner3 = strBanner2;
                            strMap2 = c.getString("strMap");
                            Object strMap3 = strMap2;

                            String timeLocal = c.getString("strTimeLocal");
                            String tweet1 = c.getString("strTweet1");
                            String tweet2 = c.getString("strTweet2");
                            String tweet3 = c.getString("strTweet3");
                            String video = c.getString("strVideo");


                            LastMatchObj o = new LastMatchObj();

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

                            lastMatchObjs.add(o);


                        }
                        adapterLastMatch.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Toast.makeText(getActivity(), "No Result for Last Match", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Souldn't get json from server.1");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void
                        run() {
                            Toast.makeText(getActivity().getApplicationContext(), "Error Connection"/*"Couldn't get json from server. Check LoCat for possible errors!1"*/,Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                progressDialog.dismiss();
                recyclerView2.setVisibility(View.VISIBLE);
                progressBar2.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "No Result Last for Match", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
