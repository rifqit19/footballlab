package com.rifqit.footballlab.League.DetailLeague.Team.DetailTeam.TeamPlayer;

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
import com.rifqit.footballlab.League.DetailLeague.Team.DetailTeam.DetailTeam;
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

public class FragmentTeamPlayer extends Fragment {

    private String TAG = FragmentTeamPlayer.class.getSimpleName();
    ArrayList<PlayerTeamObj> playerTeamObjs = new ArrayList<>();
    ProgressDialog progressDialog;
    AdapterPlayerTeam adapterPlayerTeam;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    String idPlayer, idTeam,idSoccerXML, strNationality, strPlayer, strTeam, strSport,dateBorn,strSigning,strWage,strBirthLocation, strDescriptionEN, strGender, strPosition, strFacebook,  strWebsite, strTwitter, strInstagram, strYoutube, strHeight, strWeight,  intLoved, strThumb,strLocked;
    Object idPlayerManager1,intSoccerXMLTeamID1,dateSigned1,strCollege1, strCutout1,strBanner1,strFanart11,strFanart21,strFanart31,strFanart41;
    String idPlayerManager,intSoccerXMLTeamID,dateSigned,strCollege, strCutout,strBanner,strFanart1,strFanart2,strFanart3,strFanart4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team_player, container, false);

        progressBar = v.findViewById(R.id.progressbarTeamPlayer);

        recyclerView = v.findViewById(R.id.recyclerViewPlayerTeam);
        adapterPlayerTeam = new AdapterPlayerTeam(getActivity(),playerTeamObjs);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapterPlayerTeam);
        recyclerView.setNestedScrollingEnabled(true);

        String id = ((DetailTeam)getActivity()).getIdTeam();
        Log.e("idTeam", id);

        getplayer(id);

        return v;
    }

    public void getplayer(String idku){
        progressBar.setVisibility(View.VISIBLE);
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getAllPlayer(idku);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                progressDialog.dismiss();
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        JSONObject jsonObj = new JSONObject(respon);
                        playerTeamObjs.clear();
                        JSONArray api = jsonObj.getJSONArray("player");
                        for (int i = 0; i < api.length(); i++){
                            JSONObject c = api.getJSONObject(i);

                            idPlayer = c.getString("idPlayer");
                            idTeam = c.getString("idTeam");
                            idSoccerXML = c.getString("idSoccerXML");
                            strNationality = c.getString("strNationality");
                            strPlayer = c.getString("strPlayer");
                            strTeam = c.getString("strTeam");
                            strSport = c.getString("strSport");
                            dateBorn = c.getString("dateBorn");
                            strSigning = c.getString("strSigning");
                            strWage = c.getString("strWage");
                            strBirthLocation = c.getString("strBirthLocation");
                            strDescriptionEN = c.getString("strDescriptionEN");
                            strGender = c.getString("strGender");
                            strPosition = c.getString("strPosition");
                            strFacebook = c.getString("strFacebook");
                            strWebsite = c.getString("strWebsite");
                            strTwitter = c.getString("strTwitter");
                            strInstagram = c.getString("strInstagram");
                            strYoutube = c.getString("strYoutube");
                            strHeight = c.getString("strHeight");
                            strWeight = c.getString("strWeight");
                            intLoved = c.getString("intLoved");
                            strThumb = c.getString("strThumb");
                            strLocked = c.getString("strLocked");

                            idPlayerManager = c.getString("idPlayerManager");
                            idPlayerManager1 = idPlayerManager;
                            intSoccerXMLTeamID = c.getString("intSoccerXMLTeamID");
                            intSoccerXMLTeamID1 = intSoccerXMLTeamID;
                            dateSigned = c.getString("dateSigned");
                            dateSigned1 = dateSigned;
                            strCollege = c.getString("strCollege");
                            strCollege1 = strCollege;
                            strCutout = c.getString("strCutout");
                            strCutout1 = strCutout;
                            strBanner = c.getString("strBanner");
                            strBanner1 = strBanner;
                            strFanart1 = c.getString("strFanart1");
                            strFanart11 = strFanart1;
                            strFanart2 = c.getString("strFanart2");
                            strFanart21 = strFanart2;
                            strFanart3 = c.getString("strFanart3");
                            strFanart31 = strFanart3;
                            strFanart4 = c.getString("strFanart4");
                            strFanart41 = strFanart4;


                            PlayerTeamObj p = new PlayerTeamObj();

                            p.setIdPlayer(idPlayer);
                            p.setIdTeam(idTeam);
                            p.setIdSoccerXML(idSoccerXML);
                            p.setStrNationality(strNationality);
                            p.setStrPlayer(strPlayer);
                            p.setStrTeam(strTeam);
                            p.setStrSport(strSport);
                            p.setDateBorn(dateBorn);
                            p.setStrSigning(strSigning);
                            p.setStrWage(strWage);
                            p.setStrBirthLocation(strBirthLocation);
                            p.setStrDescriptionEN(strDescriptionEN);
                            p.setStrGender(strGender);
                            p.setStrPosition(strPosition);
                            p.setStrFacebook(strFacebook);
                            p.setStrWebsite(strWebsite);
                            p.setStrTwitter(strTwitter);
                            p.setStrInstagram(strInstagram);
                            p.setStrYoutube(strYoutube);
                            p.setStrHeight(strHeight);
                            p.setStrWeight(strWeight);
                            p.setIntLoved(intLoved);
                            p.setStrThumb(strThumb);
                            p.setStrLocked(strLocked);

                            p.setIdPlayerManager(idPlayerManager1);
                            p.setIntSoccerXMLTeamID(intSoccerXMLTeamID1);
                            p.setDateSigned(dateSigned1);
                            p.setStrCollege(strCollege1);
                            p.setStrCutout(strCutout1);
                            p.setStrBanner(strBanner1);
                            p.setStrFanart1(strFanart11);
                            p.setStrFanart2(strFanart21);
                            p.setStrFanart3(strFanart31);
                            p.setStrFanart4(strFanart41);

                            playerTeamObjs.add(p);
                            Log.e("idTeam", idTeam);

                        }
                        adapterPlayerTeam.notifyDataSetChanged();
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
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
