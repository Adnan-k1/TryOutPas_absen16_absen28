package com.example.tryoutpas_absen16_absen28;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerViewTeams;
    private TeamAdapter teamAdapter;
    private static final String BASE_URL = "https://www.thesportsdb.com/api/v1/json/3/";
    private static final String ENGLISH_PREMIER_LEAGUE = "English Premier League";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerViewTeams = view.findViewById(R.id.recycler_view_teams);
        recyclerViewTeams.setLayoutManager(new LinearLayoutManager(requireContext()));
        fetchTeams();
        return view;
    }

    private void fetchTeams() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<TeamsResponse> call = apiService.getAllTeams(ENGLISH_PREMIER_LEAGUE);
        call.enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getTeams() != null && !response.body().getTeams().isEmpty()) {
                    List<Team> teamsList = response.body().getTeams();
                    teamAdapter = new TeamAdapter(requireContext(), teamsList);
                    recyclerViewTeams.setAdapter(teamAdapter);
                    Log.d("API Response", "Fetched " + teamsList.size() + " EPL teams.");
                } else {
                    Log.w("API Response", "Could not retrieve EPL teams.");
                    if (response.body() != null) {
                        Log.d("API Response", "Raw JSON (in error): " + new com.google.gson.Gson().toJson(response.body()));
                    }
                    // Handle error: display a message to the user
                }
            }

            @Override
            public void onFailure(Call<TeamsResponse> call, Throwable t) {
                Log.e("API Failure", "Error fetching EPL teams: " + t.getMessage());
                // Handle error: display an error message to the user
            }
        });
    }
}