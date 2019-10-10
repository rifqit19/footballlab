package com.rifqit.footballlab.League.DetailLeague.Team.DetailTeam.TeamPlayer.DetailPlayer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rifqit.footballlab.GetDataService;
import com.rifqit.footballlab.League.DetailLeague.Team.DetailTeam.TeamPlayer.PlayerTeamObj;
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

public class DetailPlayer extends AppCompatActivity {

    PlayerTeamObj playerTeamObj = new PlayerTeamObj();
    ImageButton back;
    TextView tittle, namaPlayer,posPlayer;
    ImageView fotoPlayer,imgCollapse;
    private String TAG = DetailPlayer.class.getSimpleName();
    ArrayList<DetailPlayerObj> detailPlayerObjs = new ArrayList<>();
    ProgressDialog progressDialog;
    ProgressBar progressBar;
    TextView height,weight,country,salary,signign,birth,desc,instaP,fbP,twittP;
    LinearLayout lytInsta,lytFb,lytTwitt;

    Object strCollege1,strBanner1;
    String idPlayer,  idTeam,  idSoccerXML,  idPlayerManager,  strNationality,  strPlayer,  strTeam,  strSport,  intSoccerXMLTeamID,  dateBorn,  dateSigned, strSigning,  strWage, strBirthLocation, strDescriptionEN, strGender, strPosition, strFacebook, strWebsite, strTwitter, strInstagram, strYoutube, strHeight, strWeight, intLoved, strThumb, strCutout, strFanart1,  strFanart2, strFanart3, strFanart4, strLocked,strCollege,strBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_player);

        back = findViewById(R.id.btn_back_detPlayer);
        tittle = findViewById(R.id.ttlP);
        namaPlayer = findViewById(R.id.namaPlayer);
        posPlayer = findViewById(R.id.posPlayer);
        fotoPlayer = findViewById(R.id.logoPlayer);
        imgCollapse = findViewById(R.id.imageCollapseP);

        instaP = findViewById(R.id.instaPlayer);
        fbP = findViewById(R.id.fbPlayer);
        twittP = findViewById(R.id.twittPlayer);
        lytInsta = findViewById(R.id.lytInstaP);
        lytFb = findViewById(R.id.lytFbP);
        lytTwitt = findViewById(R.id.lytTwitterP);


        height = findViewById(R.id.heightPlayer);
        weight = findViewById(R.id.weightPlayer);
        country = findViewById(R.id.countryPlayer2);
        signign = findViewById(R.id.signingPlayer);
        birth = findViewById(R.id.birthPlayer);
        desc = findViewById(R.id.descPlayer);
        salary = findViewById(R.id.wagePlayer);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String team = getIntent().getStringExtra("playerplayer");
        playerTeamObj = new Gson().fromJson(team, PlayerTeamObj.class);

        String idPlayerku = playerTeamObj.getIdPlayer();
        Log.e("idPlayerKu", idPlayerku);

        getplayer(idPlayerku);

        instaP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://"+strInstagram));
                startActivity(q);
            }
        });
        fbP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent w = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://"+strFacebook));
                startActivity(w);
            }
        });
        twittP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://"+strTwitter));
                startActivity(e);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarP);
        setSupportActionBar(toolbar);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbarP);
//        collapsingToolbarLayout.setTitle("Detail Pemain");

        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white0));

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.appbarP);
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
    }

    public void getplayer(final String idku){
//        progressBar.setVisibility(View.VISIBLE);
        progressDialog = new ProgressDialog(DetailPlayer.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getDetailPlayer(idku);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
//                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        JSONObject jsonObj = new JSONObject(respon);
                        detailPlayerObjs.clear();
                        JSONArray api = jsonObj.getJSONArray("players");
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
                            intSoccerXMLTeamID = c.getString("intSoccerXMLTeamID");
                            dateSigned = c.getString("dateSigned");
                            strCollege = c.getString("strCollege");
                            strCollege1 = strCollege;
                            strCutout = c.getString("strCutout");
                            strBanner = c.getString("strBanner");
                            strBanner1 = strBanner;
                            strFanart1 = c.getString("strFanart1");
                            strFanart2 = c.getString("strFanart2");
                            strFanart3 = c.getString("strFanart3");
                            strFanart4 = c.getString("strFanart4");


                            DetailPlayerObj p = new DetailPlayerObj();

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

                            p.setIdPlayerManager(idPlayerManager);
                            p.setIntSoccerXMLTeamID(intSoccerXMLTeamID);
                            p.setDateSigned(dateSigned);
                            p.setStrCollege(strCollege1);
                            p.setStrCutout(strCutout);
                            p.setStrBanner(strBanner1);
                            p.setStrFanart1(strFanart1);
                            p.setStrFanart2(strFanart2);
                            p.setStrFanart3(strFanart3);
                            p.setStrFanart4(strFanart4);

                            detailPlayerObjs.add(p);
                            Log.e("idTeam", idTeam);

                            namaPlayer.setText(strPlayer);
                            posPlayer.setText(strPosition);
//                            Picasso.with(DetailPlayer.this).load(strCutout).into(fotoPlayer);

                            if (strCutout.equals("null")){
                                Picasso.with(DetailPlayer.this).load("https://cdn.iconscout.com/icon/free/png-256/football-player-1426973-1208513.png").placeholder(R.drawable.placeholder_black).into(fotoPlayer);
                            }else {
                                Picasso.with(DetailPlayer.this).load(strCutout).placeholder(R.drawable.placeholder_black).into(fotoPlayer);
                            }

                            Calendar date = Calendar.getInstance();
                            Integer day = date.get(Calendar.DAY_OF_WEEK);

                            if (day.equals(1)){
                                Picasso.with(DetailPlayer.this).load(strThumb).placeholder(R.drawable.ic_placeholder).into(imgCollapse);
                            }else if (day.equals(2)){
                                Picasso.with(DetailPlayer.this).load(strFanart1).placeholder(R.drawable.ic_placeholder).into(imgCollapse);
                            }else if (day.equals(3)){
                                Picasso.with(DetailPlayer.this).load(strThumb).placeholder(R.drawable.ic_placeholder).into(imgCollapse);
                            }else if (day.equals(4)){
                                Picasso.with(DetailPlayer.this).load(strFanart1).placeholder(R.drawable.ic_placeholder).into(imgCollapse);
                            }else if (day.equals(5)){
                                Picasso.with(DetailPlayer.this).load(strThumb).placeholder(R.drawable.ic_placeholder).into(imgCollapse);
                            }else if (day.equals(6)){
                                Picasso.with(DetailPlayer.this).load(strFanart1).placeholder(R.drawable.ic_placeholder).into(imgCollapse);
                            }else if (day.equals(7)){
                                Picasso.with(DetailPlayer.this).load(strThumb).placeholder(R.drawable.ic_placeholder).into(imgCollapse);
                            }

                            desc.setText(strDescriptionEN);
                            height.setText(strHeight);
                            weight.setText(strWeight);
                            country.setText(strNationality);
                            if (strWage.isEmpty()){
                                salary.setText("Unknown");
                            }else {
                                salary.setText(strWage);
                            }
                            signign.setText(dateSigned);
                            birth.setText(dateBorn);

                            instaP.setText(strInstagram);
                            twittP.setText(strTwitter);
                            fbP.setText(strFacebook);

                            if (strInstagram.isEmpty()){
                                lytInsta.setVisibility(View.GONE);
                            }else if (strFacebook.isEmpty()){
                                lytFb.setVisibility(View.GONE);
                            }else if (strTwitter.isEmpty()){
                                lytTwitt.setVisibility(View.GONE);
                            }



                        }
//                        adapterPlayerTeam.notifyDataSetChanged();
                    } catch (JSONException e) {
                    Toast.makeText(DetailPlayer.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
//                progressBar.setVisibility(View.GONE);
            Toast.makeText(DetailPlayer.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    }


}
