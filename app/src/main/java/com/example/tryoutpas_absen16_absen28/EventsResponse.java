package com.example.tryoutpas_absen16_absen28;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventsResponse {
    @SerializedName("results")
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "EventsResponse{" +
                "events=" + events +
                '}';
    }
}