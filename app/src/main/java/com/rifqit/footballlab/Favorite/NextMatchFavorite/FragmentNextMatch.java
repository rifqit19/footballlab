package com.rifqit.footballlab.Favorite.NextMatchFavorite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.rifqit.footballlab.Favorite.LastMatchFavorite.FavoriteLastMatchObj;
import com.rifqit.footballlab.Favorite.RealmHelper;
import com.rifqit.footballlab.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FragmentNextMatch extends Fragment {

    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    AdapterFavoriteNextMatch adapterFavoriteNextMatch;
    List<FavoriteNextMatchObj> favoriteNextMatchObjs;
    RelativeLayout emptyLayout;
    FavoriteLastMatchObj favoriteLastMatchObj;
    String dateCurrent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_next_match, container, false);

        emptyLayout = v.findViewById(R.id.relEmpty5);

        Realm.init(getContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        favoriteNextMatchObjs = new ArrayList<>();

        dateCurrent = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        recyclerView = v.findViewById(R.id.recycleFavNetxMatch);
        adapterFavoriteNextMatch = new AdapterFavoriteNextMatch(getActivity(),favoriteNextMatchObjs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterFavoriteNextMatch);
        recyclerView.setNestedScrollingEnabled(true);
        adapterFavoriteNextMatch.notifyDataSetChanged();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        favoriteNextMatchObjs = realmHelper.getFavNextMatch();

        for (int i = 0; i < favoriteNextMatchObjs.size(); i++) {

            if (favoriteNextMatchObjs.get(i).getDateEvent().compareTo(dateCurrent)< 0) {

                String idEvent =favoriteNextMatchObjs.get(i).getIdEvent();
                Log.e("idEvent11", idEvent);

                String idSoccerXML=favoriteNextMatchObjs.get(i).getIdSoccerXML();
                String strEvent=favoriteNextMatchObjs.get(i).getStrEvent();
                String strFilename=favoriteNextMatchObjs.get(i).getStrFilename();
                String strSport=favoriteNextMatchObjs.get(i).getStrSport();
                String idLeague=favoriteNextMatchObjs.get(i).getIdLeague();
                String strLeague=favoriteNextMatchObjs.get(i).getStrLeague();
                String strSeason=favoriteNextMatchObjs.get(i).getStrSeason();
                String strHomeTeam=favoriteNextMatchObjs.get(i).getStrHomeTeam();
                String strAwayTeam=favoriteNextMatchObjs.get(i).getStrAwayTeam();
                String intRound=favoriteNextMatchObjs.get(i).getIntRound();
                String intHomeScore=favoriteNextMatchObjs.get(i).getIntHomeScore();
                String intAwayScore=favoriteNextMatchObjs.get(i).getIntAwayScore();
                String intSpectators=favoriteNextMatchObjs.get(i).getIntSpectators();
                String strHomeGoalDetails=favoriteNextMatchObjs.get(i).getStrHomeGoalDetails();
                String strHomeRedCards=favoriteNextMatchObjs.get(i).getStrHomeRedCards();
                String strHomeYellowCards=favoriteNextMatchObjs.get(i).getStrHomeYellowCards();
                String strHomeLineupGoalkeeper=favoriteNextMatchObjs.get(i).getStrHomeLineupGoalkeeper();
                String strHomeLineupDefense=favoriteNextMatchObjs.get(i).getStrHomeLineupDefense();
                String strHomeLineupMidfield=favoriteNextMatchObjs.get(i).getStrHomeLineupMidfield();
                String strHomeLineupForward=favoriteNextMatchObjs.get(i).getStrHomeLineupForward();
                String strHomeLineupSubstitutes=favoriteNextMatchObjs.get(i).getStrHomeLineupSubstitutes();
                String strHomeFormation=favoriteNextMatchObjs.get(i).getStrHomeFormation();
                String strAwayRedCards=favoriteNextMatchObjs.get(i).getStrAwayRedCards();
                String strAwayYellowCards=favoriteNextMatchObjs.get(i).getStrAwayYellowCards();
                String strAwayGoalDetails=favoriteNextMatchObjs.get(i).getStrAwayGoalDetails();
                String strAwayLineupGoalkeeper=favoriteNextMatchObjs.get(i).getStrAwayLineupGoalkeeper();
                String strAwayLineupDefense=favoriteNextMatchObjs.get(i).getStrAwayLineupDefense();
                String strAwayLineupMidfield=favoriteNextMatchObjs.get(i).getStrAwayLineupMidfield();
                String strAwayLineupForward=favoriteNextMatchObjs.get(i).getStrAwayLineupForward();
                String strAwayLineupSubstitutes=favoriteNextMatchObjs.get(i).getStrAwayLineupSubstitutes();
                String strAwayFormation=favoriteNextMatchObjs.get(i).getStrAwayFormation();
                String intHomeShots=favoriteNextMatchObjs.get(i).getIntHomeShots();
                String intAwayShots=favoriteNextMatchObjs.get(i).getIntAwayShots();
                String dateEvent=favoriteNextMatchObjs.get(i).getDateEvent();
                String strLocked=favoriteNextMatchObjs.get(i).getDateEvent();
                String strDescriptionEN=favoriteNextMatchObjs.get(i).getDateEvent();
                String datetimeEventUTC=favoriteNextMatchObjs.get(i).getDateEvent();
                String strDate=favoriteNextMatchObjs.get(i).getStrDate();
                String strTime=favoriteNextMatchObjs.get(i).getStrTime();
                String strTVStation=favoriteNextMatchObjs.get(i).getStrTVStation();
                String idHomeTeam=favoriteNextMatchObjs.get(i).getIdHomeTeam();
                String idAwayTeam=favoriteNextMatchObjs.get(i).getIdAwayTeam();
                String strResult=favoriteNextMatchObjs.get(i).getStrResult();
                String strCircuit=favoriteNextMatchObjs.get(i).getStrCircuit();
                String strCountry=favoriteNextMatchObjs.get(i).getStrCountry();
                String strCity=favoriteNextMatchObjs.get(i).getStrCity();
                String strPoster=favoriteNextMatchObjs.get(i).getStrPoster();
                String strFanart=favoriteNextMatchObjs.get(i).getStrFanart();
                String strThumb=favoriteNextMatchObjs.get(i).getStrThumb();
                String strBanner=favoriteNextMatchObjs.get(i).getStrBanner();
                String strMap=favoriteNextMatchObjs.get(i).getStrMap();

                favoriteLastMatchObj = new FavoriteLastMatchObj();
                favoriteLastMatchObj.setIdEvent(idEvent);
                favoriteLastMatchObj.setIdSoccerXML(idSoccerXML);
                favoriteLastMatchObj.setStrEvent(strEvent);
                favoriteLastMatchObj.setStrFilename(strFilename);
                favoriteLastMatchObj.setStrSport(strSport);
                favoriteLastMatchObj.setIdLeague(idLeague);
                favoriteLastMatchObj.setStrLeague(strLeague);
                favoriteLastMatchObj.setStrSeason(strSeason);
                favoriteLastMatchObj.setStrHomeTeam(strHomeTeam);
                favoriteLastMatchObj.setStrAwayTeam(strAwayTeam);
                favoriteLastMatchObj.setIntRound(intRound);
                favoriteLastMatchObj.setDateEvent(dateEvent);
                favoriteLastMatchObj.setStrDate(strDate);
                favoriteLastMatchObj.setStrTime(strTime);
                favoriteLastMatchObj.setIdHomeTeam(idHomeTeam);
                favoriteLastMatchObj.setIdAwayTeam(idAwayTeam);
                favoriteLastMatchObj.setStrLocked(strLocked);
                favoriteLastMatchObj.setStrDescriptionEN(strDescriptionEN);
                favoriteLastMatchObj.setIntHomeScore(intHomeScore);
                favoriteLastMatchObj.setIntAwayScore(intAwayScore);
                favoriteLastMatchObj.setIntSpectators(intSpectators);
                favoriteLastMatchObj.setStrHomeGoalDetails(strHomeGoalDetails);
                favoriteLastMatchObj.setStrHomeRedCards(strHomeRedCards);
                favoriteLastMatchObj.setStrHomeYellowCards(strHomeYellowCards);
                favoriteLastMatchObj.setStrHomeLineupGoalkeeper(strHomeLineupGoalkeeper);
                favoriteLastMatchObj.setStrHomeLineupDefense(strHomeLineupDefense);
                favoriteLastMatchObj.setStrHomeLineupMidfield(strHomeLineupMidfield);
                favoriteLastMatchObj.setStrHomeLineupForward(strHomeLineupForward);
                favoriteLastMatchObj.setStrHomeLineupSubstitutes(strHomeLineupSubstitutes);
                favoriteLastMatchObj.setStrHomeFormation(strHomeFormation);
                favoriteLastMatchObj.setIntHomeShots(intHomeShots);
                favoriteLastMatchObj.setStrAwayGoalDetails(strAwayGoalDetails);
                favoriteLastMatchObj.setStrAwayRedCards(strAwayRedCards);
                favoriteLastMatchObj.setStrAwayYellowCards(strAwayYellowCards);
                favoriteLastMatchObj.setStrAwayLineupGoalkeeper(strAwayLineupGoalkeeper);
                favoriteLastMatchObj.setStrAwayLineupDefense(strAwayLineupDefense);
                favoriteLastMatchObj.setStrAwayLineupMidfield(strAwayLineupMidfield);
                favoriteLastMatchObj.setStrAwayLineupForward(strAwayLineupForward);
                favoriteLastMatchObj.setStrAwayLineupSubstitutes(strAwayLineupSubstitutes);
                favoriteLastMatchObj.setStrAwayFormation(strAwayFormation);
                favoriteLastMatchObj.setIntAwayShots(intAwayShots);
                favoriteLastMatchObj.setDatetimeEventUTC(datetimeEventUTC);
                favoriteLastMatchObj.setStrTVStation(strTVStation);
                favoriteLastMatchObj.setStrResult(strResult);
                favoriteLastMatchObj.setStrCountry(strCountry);
                favoriteLastMatchObj.setStrCircuit(strCircuit);
                favoriteLastMatchObj.setStrCity(strCity);
                favoriteLastMatchObj.setStrPoster(strPoster);
                favoriteLastMatchObj.setStrFanart(strFanart);
                favoriteLastMatchObj.setStrThumb(strThumb);
                favoriteLastMatchObj.setStrBanner(strBanner);
                favoriteLastMatchObj.setStrMap(strMap);
                realmHelper = new RealmHelper(realm);
                realmHelper.saveLastMatch(favoriteLastMatchObj);

                realmHelper = new RealmHelper(realm);
                realmHelper.deleteNextMatch(idEvent);

            }
        }

        if (favoriteNextMatchObjs.isEmpty()){
            emptyLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            emptyLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        show();
    }

    public void show(){
        adapterFavoriteNextMatch = new AdapterFavoriteNextMatch(getActivity(), favoriteNextMatchObjs);
        adapterFavoriteNextMatch.notifyDataSetChanged();
        recyclerView.setAdapter(adapterFavoriteNextMatch);
    }

}
