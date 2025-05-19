package com.example.tryoutpas_absen16_absen28;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    private Context context;
    private List<Event> matchEvents;

    public MatchAdapter(Context context, List<Event> matchEvents) {
        this.context = context;
        this.matchEvents = matchEvents;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_match, parent, false);
        return new MatchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Event currentMatch = matchEvents.get(position);
        holder.eventNameTextView.setText(currentMatch.getStrEvent());
        holder.eventDateTextView.setText(currentMatch.getDateEvent());
        String score = (currentMatch.getIntHomeScore() != null ? currentMatch.getIntHomeScore() : "?") +
                " - " +
                (currentMatch.getIntAwayScore() != null ? currentMatch.getIntAwayScore() : "?");
        holder.scoreTextView.setText(score);

        Glide.with(context)
                .load(currentMatch.getStrHomeTeamBadge())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(holder.homeBadgeImageView);

        Glide.with(context)
                .load(currentMatch.getStrAwayTeamBadge())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(holder.awayBadgeImageView);
    }

    @Override
    public int getItemCount() {
        return matchEvents.size();
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        public TextView eventNameTextView;
        public TextView eventDateTextView;
        public TextView scoreTextView;
        public ImageView homeBadgeImageView;
        public ImageView awayBadgeImageView;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameTextView = itemView.findViewById(R.id.event_name);
            eventDateTextView = itemView.findViewById(R.id.event_date);
            scoreTextView = itemView.findViewById(R.id.score);
            homeBadgeImageView = itemView.findViewById(R.id.home_badge);
            awayBadgeImageView = itemView.findViewById(R.id.away_badge);
        }
    }
}