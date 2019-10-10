package com.rifqit.footballlab.League.DetailLeague.Team;

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

public class FragmentTeamLiga extends Fragment {

    private String TAG = FragmentTeamLiga.class.getSimpleName();
    ArrayList<TeamObj> teamObjs = new ArrayList<>();
    ProgressDialog progressDialog;
    AdapterTeam adapterTeam;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    String idTeam,idSoccerXML,intLoved,strTeam,strTeamShort,strAlternate,intFormedYear,strSport,strLeague,idLeague,strDivision,strManager,strStadium,strKeywords,strRSS,strStadiumThumb,strStadiumDescription,strStadiumLocation,intStadiumCapacity,strWebsite,strFacebook,strTwitter,strInstagram,strDescriptionEN,strGender,strCountry,strTeamBadge,strTeamJersey,strTeamLogo,strTeamFanart1,strTeamFanart2,strTeamFanart3,strTeamFanart4,strTeamBanner,strYoutube,strLocked;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team_liga, container, false);

        progressBar = v.findViewById(R.id.progressbarTeam);

        recyclerView = v.findViewById(R.id.recyclerViewTeam);
        adapterTeam = new AdapterTeam(getActivity(),teamObjs);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapterTeam);
        recyclerView.setNestedScrollingEnabled(true);

        String id = ((DetailLiga)getActivity()).getIdLeague();
        Log.e("idLeague", id);

        getTeam(id);

        return v;
    }

    public void getTeam(String idku){
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getAllTeam(idku);
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
                        teamObjs.clear();
                        JSONArray api = jsonObj.getJSONArray("teams");
                        for (int i = 0; i < api.length(); i++){
                            JSONObject c = api.getJSONObject(i);

                            idTeam = c.getString("idTeam");
                            idSoccerXML = c.getString("idSoccerXML");
                            intLoved = c.getString("intLoved");
                            strTeam = c.getString("strTeam");
                            strTeamShort = c.getString("strTeamShort");
                            strAlternate = c.getString("strAlternate");
                            intFormedYear = c.getString("intFormedYear");
                            strSport = c.getString("strSport");
                            strLeague = c.getString("strLeague");
                            idLeague = c.getString("idLeague");
                            strDivision = c.getString("strDivision");
                            strManager = c.getString("strManager");
                            strStadium = c.getString("strStadium");
                            strKeywords = c.getString("strKeywords");
                            strRSS = c.getString("strRSS");
                            strStadiumThumb = c.getString("strStadiumThumb");
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
                            strTeamFanart1 = c.getString("strTeamFanart1");
                            strTeamFanart2 = c.getString("strTeamFanart2");
                            strTeamFanart3 = c.getString("strTeamFanart3");
                            strTeamFanart4 = c.getString("strTeamFanart4");
                            strTeamBanner = c.getString("strTeamBanner");
                            strYoutube = c.getString("strYoutube");
                            strLocked = c.getString("strLocked");

                            TeamObj p = new TeamObj();
                            p.setIdTeam(idTeam);
                            p.setIdSoccerXML(idSoccerXML);
                            p.setIntLoved(intLoved);
                            p.setStrTeam(strTeam);
                            p.setStrTeamShort(strTeamShort);
                            p.setStrAlternate(strAlternate);
                            p.setIntFormedYear(intFormedYear);
                            p.setStrSport(strSport);
                            p.setStrLeague(strLeague);
                            p.setIdLeague(idLeague);
                            p.setStrDivision(strDivision);
                            p.setStrManager(strManager);
                            p.setStrStadium(strStadium);
                            p.setStrKeywords(strKeywords);
                            p.setStrRSS(strRSS);
                            p.setStrStadiumThumb(strStadiumThumb);
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
                            p.setStrTeamLogo(strTeamLogo);
                            p.setStrTeamFanart1(strTeamFanart1);
                            p.setStrTeamFanart2(strTeamFanart2);
                            p.setStrTeamFanart3(strTeamFanart3);
                            p.setStrTeamFanart4(strTeamFanart4);
                            p.setStrTeamBanner(strTeamBanner);
                            p.setStrYoutube(strYoutube);
                            p.setStrLocked(strLocked);

                            teamObjs.add(p);
                            Log.e("idTeam", idTeam);

                        }
                        adapterTeam.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Toast.makeText(getActivity(), "Empty Result for Team", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getActivity().getApplicationContext(), "Error Connection",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                progressDialog.dismiss();
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "No Result for Team", Toast.LENGTH_SHORT).show();
            }
        });
    }

}


