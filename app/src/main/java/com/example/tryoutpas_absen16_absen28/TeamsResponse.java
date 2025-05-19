package com.example.tryoutpas_absen16_absen28;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamsResponse {
    @SerializedName("teams")
    private List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "TeamsResponse{" +
                "teams=" + teams +
                '}';
    }
}