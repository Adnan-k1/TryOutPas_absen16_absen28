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

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewMatches;
    private MatchAdapter matchAdapter;
    private static final String BASE_URL = "https://www.thesportsdb.com/api/v1/json/3/";
    private static final String MAN_UNITED_ID = "133612";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewMatches = view.findViewById(R.id.recycler_view_matches);
        recyclerViewMatches.setLayoutManager(new LinearLayoutManager(requireContext()));
        fetchLastMatchHistory();
        return view;
    }

    private void fetchLastMatchHistory() {
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

        Call<EventsResponse> call = apiService.getLastMatch(MAN_UNITED_ID);
        call.enqueue(new Callback<EventsResponse>() {
            @Override
            public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getEvents() != null && !response.body().getEvents().isEmpty()) {
                    List<Event> recentMatches = response.body().getEvents();
                    matchAdapter = new MatchAdapter(requireContext(), recentMatches);
                    recyclerViewMatches.setAdapter(matchAdapter);
                    Log.d("API Response", "Fetched " + recentMatches.size() + " recent matches.");
                } else {
                    // Handle the case where no data is retrieved
                    Log.w("API Response", "Could not retrieve recent match history.");
                    if (response.body() != null) {
                        Log.d("API Response", "Raw JSON (in error): " + new com.google.gson.Gson().toJson(response.body()));
                    }
                    // You might want to display a message to the user here
                }
            }

            @Override
            public void onFailure(Call<EventsResponse> call, Throwable t) {
                Log.e("API Failure", "Error fetching recent matches: " + t.getMessage());
                // You might want to display an error message to the user here
            }
        });
    }
}