package com.rifqit.footballlab.League;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.rifqit.footballlab.Favorite.Favorite;
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

public class DaftarLiga extends AppCompatActivity {

    private String TAG = DaftarLiga.class.getSimpleName();
    ArrayList<LeagueObj> leagueObjs = new ArrayList<>();
//    ProgressBar progressBar;
    ProgressDialog progressDialog;
    AdapterDaftarLiga adapterDaftarLiga;
    RecyclerView recyclerView;
    ImageButton btnFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_liga);

        btnFav = findViewById(R.id.btnFav);
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(DaftarLiga.this, Favorite.class);
                startActivity(f);
            }
        });

        generateDataList();
        getliga();
    }

    public void getliga(){
        progressDialog = new ProgressDialog(DaftarLiga.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
//        progressBar.setVisibility(View.VISIBLE);
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getAllLiga();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
//                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        JSONObject jsonObj = new JSONObject(respon);
                        JSONArray api = jsonObj.getJSONArray("leagues");
                        boolean isSave = true;
                        for (int i = 0; i < api.length(); i++){
                            JSONObject c = api.getJSONObject(i);

                            String idLeague = c.getString("idLeague");
                            String strLeague = c.getString("strLeague");
                            String strSport = c.getString("strSport");
                            String strLeagueAlternate = c.getString("strLeagueAlternate");
                            Object strLeagueAlternates = strLeagueAlternate;

                            final LeagueObj s = new LeagueObj();
                            s.setIdLeague(idLeague);
                            s.setStrLeague(strLeague);
                            s.setStrSport(strSport);
                            s.setStrLeagueAlternate(strLeagueAlternates);

                            if (strSport.equalsIgnoreCase("Soccer")){
                                isSave = true;
                            }else {
                                isSave = false;
                                continue;
                            }
                            if (isSave = true)
                                leagueObjs.add(s);

                            Log.e("liga", strLeague);
                        }
                        adapterDaftarLiga.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Toast.makeText(DaftarLiga.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getApplicationContext(), "Error Connection",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
//                progressBar.setVisibility(View.GONE);
                Toast.makeText(DaftarLiga.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList() {
        recyclerView = findViewById(R.id.recyclerViewLiga);
        adapterDaftarLiga = new AdapterDaftarLiga(DaftarLiga.this,leagueObjs);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(DaftarLiga.this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mGridLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapterDaftarLiga);
        recyclerView.setMotionEventSplittingEnabled(false);
    }
}
