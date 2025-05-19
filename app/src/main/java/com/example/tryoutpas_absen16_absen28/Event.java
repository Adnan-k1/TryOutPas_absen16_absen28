package com.example.tryoutpas_absen16_absen28;

import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("strEvent")
    private String strEvent;
    @SerializedName("dateEvent")
    private String dateEvent;
    @SerializedName("intHomeScore")
    private String intHomeScore;
    @SerializedName("intAwayScore")
    private String intAwayScore;
    @SerializedName("strHomeTeamBadge")
    private String strHomeTeamBadge;
    @SerializedName("strAwayTeamBadge")
    private String strAwayTeamBadge;

    public String getStrEvent() {
        return strEvent;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public String getIntHomeScore() {
        return intHomeScore;
    }

    public String getIntAwayScore() {
        return intAwayScore;
    }

    public String getStrHomeTeamBadge() {
        return strHomeTeamBadge;
    }

    public String getStrAwayTeamBadge() {
        return strAwayTeamBadge;
    }

    @Override
    public String toString() {
        return "Event{" +
                "strEvent='" + strEvent + '\'' +
                ", dateEvent='" + dateEvent + '\'' +
                ", intHomeScore='" + intHomeScore + '\'' +
                ", intAwayScore='" + intAwayScore + '\'' +
                ", strHomeTeamBadge='" + strHomeTeamBadge + '\'' +
                ", strAwayTeamBadge='" + strAwayTeamBadge + '\'' +
                '}';
    }
}