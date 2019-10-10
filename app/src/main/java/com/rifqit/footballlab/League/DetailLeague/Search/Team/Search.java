package com.rifqit.footballlab.League.DetailLeague.Search.Team;

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

public class Search extends AppCompatActivity {

    ImageButton back;
    private String TAG = Search.class.getSimpleName();
    ArrayList<SearchTeamObj> searchTeamObjs = new ArrayList<>();
    ProgressDialog progressDialog;
    AdapterSearchTeam adapterSearchTeam;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    RelativeLayout empltLyt;
    /*Team*/
    String idTeam, idSoccerXML, intLoved, strTeam, strAlternate, intFormedYear, strSport, strLeague, idLeague,strManager, strStadium, strKeywords, strRSS,strStadiumDescription, strStadiumLocation, intStadiumCapacity, strWebsite, strFacebook, strTwitter, strInstagram, strDescriptionEN, strGender, strCountry, strTeamBadge, strTeamJersey,strYoutube, strLocked,strTeamShort, strDivision,  strStadiumThumb,  strTeamLogo, strTeamFanart1, strTeamFanart2, strTeamFanart3, strTeamFanart4, strTeamBanner;
    /*Team*/
    Object strTeamShort1, strDivision1,  strStadiumThumb1,  strTeamLogo1, strTeamFanart11, strTeamFanart21, strTeamFanart31, strTeamFanart41, strTeamBanner1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        back = findViewById(R.id.backSearch);
        progressBar = findViewById(R.id.progressbarSearchTeam);
        empltLyt = findViewById(R.id.relEmpty);


//        putput = getIntent().getStringExtra("t1");
////        Log.e("putput", putput);
//        if(putput.isEmpty()){
//            Log.e("putput", "kosong");
//        }else {
//            Log.e("putput", putput);
//        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarhhhh);
        setSupportActionBar(toolbar);
        toolbar.setTitle(" ");

        recyclerView = findViewById(R.id.recyclerViewSearchTeam);
        adapterSearchTeam = new AdapterSearchTeam(this,searchTeamObjs);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterSearchTeam);
        recyclerView.setNestedScrollingEnabled(true);

    }
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final SearchView searchView = (SearchView) findViewById(R.id.searchhhh);
        searchView.setQueryHint("Team Name");
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
                getTeam(query);
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

    public void getTeam(String query){
        progressBar.setVisibility(View.VISIBLE);
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getResultTeam(query);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                progressDialog.dismiss();
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        JSONObject jsonObj = new JSONObject(respon);
                        searchTeamObjs.clear();
                        boolean isSave = true;
                        JSONArray api = jsonObj.getJSONArray("teams");
                        for (int i = 0; i < api.length(); i++){
                            JSONObject c = api.getJSONObject(i);

                            idTeam = c.getString("idTeam");
                            idSoccerXML = c.getString("idSoccerXML");
                            intLoved = c.getString("intLoved");
                            strTeam = c.getString("strTeam");
                            strTeamShort = c.getString("strTeamShort");
                            strTeamShort1 = strTeamShort;
                            strAlternate = c.getString("strAlternate");
                            intFormedYear = c.getString("intFormedYear");
                            strSport = c.getString("strSport");
                            strLeague = c.getString("strLeague");
                            idLeague = c.getString("idLeague");
                            strDivision = c.getString("strDivision");
                            strDivision1 = strDivision;
                            strManager = c.getString("strManager");
                            strStadium = c.getString("strStadium");
                            strKeywords = c.getString("strKeywords");
                            strRSS = c.getString("strRSS");
                            strStadiumThumb = c.getString("strStadiumThumb");
                            strStadiumThumb1 = strStadiumThumb;
                            strStadiumDescription = c.getString("strStadiumDescription");
                            strStadiumLocation = c.getString("strStadiumLocation");
                            intStadiumCapacity = c.getString("intStadiumCapacity");
                            strWebsite = c.getString("strWebsite");
                            strFacebook = c.getString("strFacebook");
                            strTwitter = c.getString("strTwitter");
                            strInstagram = c.getString("strInstagram");
                            strDescriptionEN = c.getString("strDescriptionEN");
                            strGender = c.getString("strGender");
                            strCountry = c.getString("strCountry");
                            strTeamBadge = c.getString("strTeamBadge");
                            strTeamJersey = c.getString("strTeamJersey");
                            strTeamLogo = c.getString("strTeamLogo");
                            strTeamLogo1 = strTeamLogo;
                            strTeamFanart1 = c.getString("strTeamFanart1");
                            strTeamFanart11 = strTeamFanart1;
                            strTeamFanart2 = c.getString("strTeamFanart2");
                            strTeamFanart21 = strTeamFanart2;
                            strTeamFanart3 = c.getString("strTeamFanart3");
                            strTeamFanart31 =strTeamFanart3 ;
                            strTeamFanart4 = c.getString("strTeamFanart4");
                            strTeamFanart41 = strTeamFanart4;
                            strTeamBanner = c.getString("strTeamBanner");
                            strTeamBanner1 = strTeamBanner;
                            strYoutube = c.getString("strYoutube");
                            strLocked = c.getString("strLocked");

                            SearchTeamObj p = new SearchTeamObj();
                            p.setIdTeam(idTeam);
                            p.setIdSoccerXML(idSoccerXML);
                            p.setIntLoved(intLoved);
                            p.setStrTeam(strTeam);
                            p.setStrTeamShort(strTeamShort1);
                            p.setStrAlternate(strAlternate);
                            p.setIntFormedYear(intFormedYear);
                            p.setStrSport(strSport);
                            p.setStrLeague(strLeague);
                            p.setIdLeague(idLeague);
                            p.setStrDivision(strDivision1);
                            p.setStrManager(strManager);
                            p.setStrStadium(strStadium);
                            p.setStrKeywords(strKeywords);
                            p.setStrRSS(strRSS);
                            p.setStrStadiumThumb(strStadiumThumb1);
                            p.setStrStadiumDescription(strStadiumDescription);
                            p.setStrStadiumLocation(strStadiumLocation);
                            p.setIntStadiumCapacity(intStadiumCapacity);
                            p.setStrWebsite(strWebsite);
                            p.setStrFacebook(strFacebook);
                            p.setStrTwitter(strTwitter);
                            p.setStrDescriptionEN(strDescriptionEN);
                            p.setStrGender(strGender);
                            p.setStrCountry(strCountry);
                            p.setStrTeamBadge(strTeamBadge);
                            p.setStrTeamJersey(strTeamJersey);
                            p.setStrTeamLogo(strTeamLogo1);
                            p.setStrTeamFanart1(strTeamFanart11);
                            p.setStrTeamFanart2(strTeamFanart21);
                            p.setStrTeamFanart3(strTeamFanart31);
                            p.setStrTeamFanart4(strTeamFanart41);
                            p.setStrTeamBanner(strTeamBanner1);
                            p.setStrYoutube(strYoutube);
                            p.setStrLocked(strLocked);

                            if (strSport.equalsIgnoreCase("Soccer")){
                                isSave = true;
                            }else {
                                isSave = false;
                                continue;
                            }
                            if (isSave = true)
                                searchTeamObjs.add(p);


                            Log.e("idTeam", idTeam);

                        }
                        adapterSearchTeam.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Toast.makeText(Search.this, "No Result", Toast.LENGTH_SHORT).show();
                        recyclerView.setVisibility(View.GONE);
                        empltLyt.setVisibility(View.VISIBLE);
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Souldn't get json from server.1");
                    Search.this.runOnUiThread(new Runnable() {
                        @Override
                        public void
                        run() {
                            Toast.makeText(Search.this, "Couldn't get json from server. Check LoCat for possible errors!1",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                progressDialog.dismiss();
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Search.this, "No Result", Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(View.GONE);
                empltLyt.setVisibility(View.VISIBLE);
            }
        });
    }


}
