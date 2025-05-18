package com.iesports.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del torneo es obligatorio")
    private String name;

    @NotNull(message = "La fecha del torneo es obligatoria")
    private String date;

    @NotBlank(message = "El estado del torneo es obligatorio")
    private String state;

    @ManyToOne
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    private Sport sport;

    public Tournament() {
    	
    }

    public Tournament(Long id, String name, String date, String state, Sport sport) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.state = state;
        this.sport = sport;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Override
    public String toString() {
        return "Tournament [id=" + id + ", name=" + name + ", date=" + date + ", state=" + state + ", sport=" + sport + "]";
    }
}
