package com.rifqit.footballlab.League;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeagueObj {
    @SerializedName("idLeague")
    @Expose
    private String idLeague;
    @SerializedName("strLeague")
    @Expose
    private String strLeague;
    @SerializedName("strSport")
    @Expose
    private String strSport;
    @SerializedName("strLeagueAlternate")
    @Expose
    private Object strLeagueAlternate;

    public String getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(String idLeague) {
        this.idLeague = idLeague;
    }

    public String getStrLeague() {
        return strLeague;
    }

    public void setStrLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public String getStrSport() {
        return strSport;
    }

    public void setStrSport(String strSport) {
        this.strSport = strSport;
    }

    public Object getStrLeagueAlternate() {
        return strLeagueAlternate;
    }

    public void setStrLeagueAlternate(Object strLeagueAlternate) {
        this.strLeagueAlternate = strLeagueAlternate;
    }
}
