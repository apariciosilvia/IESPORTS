package com.iesports.model;
import com.iesports.enums.StateTournamentEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tournaments")
@Schema(description = "Entidad que representa un torneo")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del torneo", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "El nombre del torneo es obligatorio")
    @Schema(description = "Nombre del torneo", example = "Ajedrez Winter")
    private String name;

    @NotNull(message = "La fecha del torneo es obligatoria")
    @Schema(description = "Fecha del torneo en formato YYYY/YY", example = "2022/23")
    private String date;

    @NotNull(message = "El estado del torneo es obligatorio")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Estado actual del torneo", example = "FINALIZADO")
    private StateTournamentEnum state;

    @ManyToOne
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    private Sport sport;

    public Tournament() {
    	
    }

    public Tournament(Long id,String name, String date, StateTournamentEnum state, Sport sport) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.state = state;
		this.sport = sport;
	}

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

    public StateTournamentEnum getState() {
		return state;
	}
	public void setState(StateTournamentEnum state) {
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
		return "Tournament [id=" + id + ", name=" + name + ", date=" + date + ", maxTeams=" + ", state="
				+ state + ", sport=" + sport + "]";
	}
	
	
}
