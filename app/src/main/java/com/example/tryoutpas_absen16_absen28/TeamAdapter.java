package com.example.tryoutpas_absen16_absen28;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private Context context;
    private List<Team> teams;

    public TeamAdapter(Context context, List<Team> teams) {
        this.context = context;
        this.teams = teams;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_team, parent, false);
        return new TeamViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        Team currentTeam = teams.get(position);
        holder.teamNameTextView.setText(currentTeam.getStrTeam());
        holder.teamLeagueTextView.setText(currentTeam.getStrLeague());

        String badgeUrl = currentTeam.getStrBadge();
        Log.d("TeamAdapter", "Team: " + currentTeam.getStrTeam() + ", Badge URL: " + badgeUrl);

        // EXPLICIT NULL CHECK BEFORE LOADING WITH GLIDE
        if (badgeUrl != null && !badgeUrl.isEmpty()) {
            Glide.with(context)
                    .load(badgeUrl)
                    .placeholder(R.drawable.ic_image_placeholder) // MAKE SURE THIS EXISTS in res/drawable
                    .error(R.drawable.ic_image_error)       // MAKE SURE THIS EXISTS in res/drawable
                    .into(holder.teamBadgeImageView);
        } else {
            // If the URL is null or empty, load the error or placeholder
            Glide.with(context)
                    .load(R.drawable.ic_image_error) // Or R.drawable.ic_image_placeholder
                    .into(holder.teamBadgeImageView);
            Log.w("TeamAdapter", "Null or empty badge URL for: " + currentTeam.getStrTeam());
        }
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        public ImageView teamBadgeImageView;
        public TextView teamNameTextView;
        public TextView teamLeagueTextView;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            teamBadgeImageView = itemView.findViewById(R.id.team_badge);
            teamNameTextView = itemView.findViewById(R.id.team_name);
            teamLeagueTextView = itemView.findViewById(R.id.team_league);
        }
    }
}