package com.rifqit.footballlab.Favorite.LastMatchFavorite;

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

public class FragmentLastMatch extends Fragment {

    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    AdapterFavoriteLastMatch adapterFavoriteLastMatch;
    List<FavoriteLastMatchObj> favoriteLastMatchObjs;
    RelativeLayout emptyLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_last_match, container, false);
        emptyLayout = v.findViewById(R.id.relEmpty4);

        recyclerView = v.findViewById(R.id.recycleFavLastMatch);
        adapterFavoriteLastMatch = new AdapterFavoriteLastMatch(getActivity(),favoriteLastMatchObjs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterFavoriteLastMatch);
        recyclerView.setNestedScrollingEnabled(true);
        adapterFavoriteLastMatch.notifyDataSetChanged();



        Realm.init(getContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        favoriteLastMatchObjs = new ArrayList<>();

        return v;
    }

    public void show(){
        adapterFavoriteLastMatch = new AdapterFavoriteLastMatch(getActivity(), favoriteLastMatchObjs);
        adapterFavoriteLastMatch.notifyDataSetChanged();
        recyclerView.setAdapter(adapterFavoriteLastMatch);
    }

    @Override
    public void onResume() {
        favoriteLastMatchObjs = realmHelper.getFavLastMatch();

        if (favoriteLastMatchObjs.isEmpty()){
            emptyLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            emptyLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        show();
        super.onResume();
    }

}
