package com.example.tryoutpas_absen16_absen28;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("eventslast.php")
    Call<EventsResponse> getLastMatch(@Query("id") String teamId);

    @GET("search_all_teams.php")
    Call<TeamsResponse> getAllTeams(@Query("l") String leagueName);
}