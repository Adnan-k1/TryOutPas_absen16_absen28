package com.example.tryoutpas_absen16_absen28;

import com.google.gson.annotations.SerializedName;

public class Team {
    @SerializedName("strTeam")
    private String strTeam;
    @SerializedName("strBadge")
    private String strBadge;
    @SerializedName("strLeague")
    private String strLeague;

    public String getStrTeam() {
        return strTeam;
    }

    public String getStrBadge() {
        return strBadge;
    }

    public String getStrLeague() {
        return strLeague;
    }

    @Override
    public String toString() {
        return "Team{" +
                "strTeam='" + strTeam + '\'' +
                ", strBadge='" + strBadge + '\'' +
                ", strLeague='" + strLeague + '\'' +
                '}';
    }
}