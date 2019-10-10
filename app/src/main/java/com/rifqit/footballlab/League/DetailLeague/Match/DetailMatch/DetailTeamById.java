package com.rifqit.footballlab.League.DetailLeague.Match.DetailMatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailTeamById {

    @SerializedName("strStadium")
    @Expose
    private String strStadium;
    @SerializedName("strStadiumThumb")
    @Expose
    private String strStadiumThumb;
//    @SerializedName("strCountry")
//    @Expose
//    private String strCountry;
    @SerializedName("strTeamBadge")
    @Expose
    private String strTeamBadge;

    public String getStrStadium() {
        return strStadium;
    }

    public void setStrStadium(String strStadium) {
        this.strStadium = strStadium;
    }

    public String getStrStadiumThumb() {
        return strStadiumThumb;
    }

    public void setStrStadiumThumb(String strStadiumThumb) {
        this.strStadiumThumb = strStadiumThumb;
    }

//    public String getStrCountry() {
//        return strCountry;
//    }
//
//    public void setStrCountry(String strCountry) {
//        this.strCountry = strCountry;
//    }
//
    public String getStrTeamBadge() {
        return strTeamBadge;
    }

    public void setStrTeamBadge(String strTeamBadge) {
        this.strTeamBadge = strTeamBadge;
    }
}
