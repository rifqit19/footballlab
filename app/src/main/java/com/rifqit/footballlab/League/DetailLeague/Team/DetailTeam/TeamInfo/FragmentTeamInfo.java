package com.rifqit.footballlab.League.DetailLeague.Team.DetailTeam.TeamInfo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.rifqit.footballlab.GetDataService;
import com.rifqit.footballlab.League.DetailLeague.Team.AdapterTeam;
import com.rifqit.footballlab.League.DetailLeague.Team.DetailTeam.DetailTeam;
import com.rifqit.footballlab.R;
import com.rifqit.footballlab.RetrofitCilentInstance;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTeamInfo extends Fragment {

    TextView league,stadium,coach,desc,destStdm,insta,yt,twitter,fb;
    private String TAG = FragmentTeamInfo.class.getSimpleName();
    ArrayList<DetailInfoTeamObj> detailInfoTeamObjs = new ArrayList<>();
    ProgressDialog progressDialog;
    AdapterTeam adapterTeam;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayout linearLayout,lytInsta,lytFb,lytYt,lyttwitter;
    RoundedImageView imgStadium;

    String idTeam,idSoccerXML,intLoved,strTeam,strTeamShort,strAlternate,intFormedYear,strSport,strLeague,idLeague,strDivision,strManager,strStadium,strKeywords,strRSS,strStadiumThumb,strStadiumDescription,strStadiumLocation,intStadiumCapacity,strWebsite,strFacebook,strTwitter,strInstagram,strDescriptionEN,strGender,strCountry,strTeamBadge,strTeamJersey,strTeamLogo,strTeamFanart1,strTeamFanart2,strTeamFanart3,strTeamFanart4,strTeamBanner,strYoutube,strLocked;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team_info, container, false);

        league = v.findViewById(R.id.leagueTeam);
        stadium = v.findViewById(R.id.stadiumTeam);
        coach = v.findViewById(R.id.coachTeam);
        desc = v.findViewById(R.id.descTeam);
        progressBar = v.findViewById(R.id.progressbarTeamInfo);
        linearLayout = v.findViewById(R.id.lineaTeamInfo);
        imgStadium = v.findViewById(R.id.stadiumImg);
        destStdm = v.findViewById(R.id.descStadiumTeam);
        insta = v.findViewById(R.id.instaTeam);
        yt = v.findViewById(R.id.youtubeTeam);
        twitter = v.findViewById(R.id.twittTeam);
        fb = v.findViewById(R.id.fbTeam);
        lytFb = v.findViewById(R.id.lytFb);
        lytInsta = v.findViewById(R.id.lytInsta);
        lytYt = v.findViewById(R.id.lytYt);
        lyttwitter = v.findViewById(R.id.lytTwitter);

        String id = ((DetailTeam) getActivity()).getIdTeam();

        getTeam(id);

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://"+strInstagram));
                startActivity(q);
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent w = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://"+strFacebook));
                startActivity(w);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://"+strTwitter));
                startActivity(e);
            }
        });
        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://"+strYoutube));
                startActivity(r);
            }
        });

        return v;
    }

    public void getTeam(String idku){
        progressBar.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getInfoTeam(idku);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                progressDialog.dismiss();
                progressBar.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        JSONObject jsonObj = new JSONObject(respon);
                        detailInfoTeamObjs.clear();
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

                            DetailInfoTeamObj p = new DetailInfoTeamObj();
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

                            detailInfoTeamObjs.add(p);
                            Log.e("TeamInfo", strStadium);

                            if (strInstagram.isEmpty()){
                                lytInsta.setVisibility(View.GONE);
                            }else if (strYoutube.isEmpty()){
                                lytYt.setVisibility(View.GONE);
                            }else if (strFacebook.isEmpty()){
                                lytFb.setVisibility(View.GONE);
                            }else if (strTwitter.isEmpty()){
                                lyttwitter.setVisibility(View.GONE);
                            }

                            league.setText(strLeague);
                            coach.setText(strManager);
                            stadium.setText(strStadium);
                            desc.setText(strDescriptionEN);
                            Picasso.with(getActivity()).load(strStadiumThumb).placeholder(R.drawable.ic_placeholder).into(imgStadium);
                            destStdm.setText(strStadiumDescription);
                            insta.setText(strInstagram);
                            fb.setText(strFacebook);
                            yt.setText(strYoutube);
                            twitter.setText(strTwitter);

                        }
//                        adapterTeam.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getActivity().getApplicationContext(), "Couldn't get json from server. Check LoCat for possible errors!1",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                progressDialog.dismiss();
                progressBar.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
