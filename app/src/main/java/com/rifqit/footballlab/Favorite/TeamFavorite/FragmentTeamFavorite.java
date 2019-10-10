package com.rifqit.footballlab.Favorite.TeamFavorite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.rifqit.footballlab.Favorite.RealmHelper;
import com.rifqit.footballlab.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FragmentTeamFavorite extends Fragment {

    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    AdapterFavoriteTeam adapterFavoriteTeam;
    List<FavoriteTeamObj> favoriteTeamObjs;
    RelativeLayout emptyLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_team_favorite, container, false);

        emptyLayout = v.findViewById(R.id.relEmpty3);

        recyclerView = v.findViewById(R.id.recycleFavTeam);
        adapterFavoriteTeam = new AdapterFavoriteTeam(getActivity(),favoriteTeamObjs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterFavoriteTeam);
        recyclerView.setNestedScrollingEnabled(true);

        Realm.init(getContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        favoriteTeamObjs = new ArrayList<>();



        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        favoriteTeamObjs = realmHelper.getFavTeam();

        if (favoriteTeamObjs.isEmpty()){
            emptyLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            emptyLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        show();
    }

    public void show(){
        adapterFavoriteTeam = new AdapterFavoriteTeam(getActivity(), favoriteTeamObjs);
        adapterFavoriteTeam.notifyDataSetChanged();
        recyclerView.setAdapter(adapterFavoriteTeam);
    }
    }
