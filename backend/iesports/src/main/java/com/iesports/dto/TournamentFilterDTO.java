package com.iesports.dto;

public class TournamentFilterDTO {
    private Integer sport_id;
    private String date;

    public Integer getSport_id() {
        return sport_id;
    }
    public void setSport_id(Integer sport_id) {
        this.sport_id = sport_id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
