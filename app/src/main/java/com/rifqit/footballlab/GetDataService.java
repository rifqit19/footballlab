package com.rifqit.footballlab;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("all_leagues.php")
    Call<ResponseBody> getAllLiga(
    );
    @GET("lookupleague.php")
    Call<ResponseBody> getDetailLiga(
            @Query("id") String id
    );
    @GET("lookup_all_teams.php")
    Call<ResponseBody> getAllTeam(
            @Query("id") String id
    );

    @GET("lookuptable.php")
    Call<ResponseBody> getAllKlasemen(
            @Query("l") String l
    );

    @GET("eventsnextleague.php")
    Call<ResponseBody> getNextMatch(
            @Query("id") String id
    );

    @GET("eventspastleague.php")
    Call<ResponseBody> getLastMatch(
            @Query("id") String id
    );

    @GET("lookupteam.php")
    Call<ResponseBody> getInfoTeam(
            @Query("id") String id
    );

    @GET("lookup_all_players.php")
    Call<ResponseBody> getAllPlayer(
            @Query("id") String id
    );

    @GET("searchplayers.php")
    Call<ResponseBody> getAllSearchPlayer(
            @Query("t") String t
    );

    @GET("lookupplayer.php")
    Call<ResponseBody> getDetailPlayer(
            @Query("id") String id
    );

    @GET("lookupevent.php")
    Call<ResponseBody> getDetailEvent(
            @Query("id") String id
    );
    @GET("lookupteam.php")
    Call<ResponseBody> getDetailTeamById(
            @Query("id") String id
    );

    @GET("searchteams.php")
    Call<ResponseBody> getResultTeam(
            @Query("t") String query
    );

    @GET("searchevents.php")
    Call<ResponseBody> getResultMatch(
            @Query("e") String query
    );

}
