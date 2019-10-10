package com.rifqit.footballlab.League.DetailLeague.Klasemen;

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

public class FragmentKlasemenLiga extends Fragment {

    private String TAG = FragmentKlasemenLiga.class.getSimpleName();
    ArrayList<KlasemenObj> klasemenObjs = new ArrayList<>();
    ProgressDialog progressDialog;
    AdapterKlasemen adapterKlasemen;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    String name,teamid;
    Integer played,goalsfor,goalsagainst,goalsdifference,win,draw,loss,total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_klasemen_liga, container, false);

        progressBar = v.findViewById(R.id.progresBarKlasemen);
        recyclerView = v.findViewById(R.id.recyclerViewKlasemen);
        adapterKlasemen = new AdapterKlasemen(getActivity(),klasemenObjs);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapterKlasemen);
        recyclerView.setNestedScrollingEnabled(true);

        String id = ((DetailLiga) getActivity()).getIdLeague();
        Log.e("idLigaKlasemen", id+"");

        getKlasemen(id);

        return v;
    }

    public void getKlasemen(String idku){
        progressBar.setVisibility(View.VISIBLE);
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getAllKlasemen(idku);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                progressDialog.dismiss();
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        JSONObject jsonObj = new JSONObject(respon);
                        klasemenObjs.clear();
                        JSONArray api = jsonObj.getJSONArray("table");
                        for (int i = 0; i < api.length(); i++){
                            JSONObject c = api.getJSONObject(i);

                            name = c.getString("name");
                            teamid = c.getString("teamid");
                            played = c.getInt("played");
                            goalsfor = c.getInt("goalsfor");
                            goalsagainst = c.getInt("goalsagainst");
                            goalsdifference = c.getInt("goalsdifference");
                            win = c.getInt("win");
                            loss = c.getInt("loss");
                            draw = c.getInt("draw");
                            total = c.getInt("total");

                            KlasemenObj o = new KlasemenObj();

                            o.setName(name);
                            o.setTeamid(teamid);
                            o.setPlayed(played);
                            o.setGoalsfor(goalsfor);
                            o.setGoalsagainst(goalsagainst);
                            o.setGoalsdifference(goalsdifference);
                            o.setDraw(draw);
                            o.setLoss(loss);
                            o.setWin(win);
                            o.setTotal(total);
                            klasemenObjs.add(o);

                        }
                        adapterKlasemen.notifyDataSetChanged();
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
