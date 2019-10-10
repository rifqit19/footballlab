package com.rifqit.footballlab.Favorite;

import com.rifqit.footballlab.Favorite.LastMatchFavorite.FavoriteLastMatchObj;
import com.rifqit.footballlab.Favorite.NextMatchFavorite.FavoriteNextMatchObj;
import com.rifqit.footballlab.Favorite.TeamFavorite.FavoriteTeamObj;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm){

        this.realm = realm;
    }

    /*Team*/
    public void saveTeam(final FavoriteTeamObj favoriteTeamObj){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                FavoriteTeamObj model = realm.copyToRealm(favoriteTeamObj);
            }
        });
    }
    public List<FavoriteTeamObj> getFavTeam(){
        RealmResults<FavoriteTeamObj> results = realm.where(FavoriteTeamObj.class).findAll();
        return results;
    }
    public void deleteTeam(String idTeam){
        final RealmResults<FavoriteTeamObj> model = realm.where(FavoriteTeamObj.class).equalTo("idTeam", idTeam).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }
    public void deleteAllTeam(){
        final RealmResults<FavoriteTeamObj> model = realm.where(FavoriteTeamObj.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteAllFromRealm();

            }
        });
    }

    /*LastMatch*/
    public void saveLastMatch(final FavoriteLastMatchObj favoriteLastMatchObj){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                FavoriteLastMatchObj model = realm.copyToRealm(favoriteLastMatchObj);
            }
        });
    }

    public List<FavoriteLastMatchObj> getFavLastMatch(){
        RealmResults<FavoriteLastMatchObj> results = realm.where(FavoriteLastMatchObj.class).findAll();
        return results;
    }

    public void deleteLastMatch(String idEvent){
        final RealmResults<FavoriteLastMatchObj> model = realm.where(FavoriteLastMatchObj.class).equalTo("idEvent", idEvent).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

    public void deleteAllLastMatch(){
        final RealmResults<FavoriteLastMatchObj> model = realm.where(FavoriteLastMatchObj.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteAllFromRealm();

            }
        });
    }

    /*NextMatch*/
    public void saveNextMatch(final FavoriteNextMatchObj favoriteNextMatchObj){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                FavoriteNextMatchObj model = realm.copyToRealm(favoriteNextMatchObj);
            }
        });
    }

    public List<FavoriteNextMatchObj> getFavNextMatch(){
        RealmResults<FavoriteNextMatchObj> results = realm.where(FavoriteNextMatchObj.class).findAll();
        return results;
    }

    public void deleteNextMatch(String idEvent){
        final RealmResults<FavoriteNextMatchObj> model = realm.where(FavoriteNextMatchObj.class).equalTo("idEvent", idEvent).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

    public void deleteAllNextMatch(){
        final RealmResults<FavoriteNextMatchObj> model = realm.where(FavoriteNextMatchObj.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteAllFromRealm();

            }
        });
    }
}
