package com.iesports.model;

import java.util.Date;
import com.iesports.enums.RoundMatchEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "matches")
@Schema(description = "Entidad que representa un partido entre dos equipos en un torneo")
public class Match {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del partido", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;
	 @Schema(description = "Fecha y hora del partido", example = "2025-06-01T00:00:00.000Z")
	private Date date;
	@Enumerated(EnumType.STRING)
	 @Schema(description = "Ronda del partido", example = "CUARTOS_FINAL")
	private RoundMatchEnum round;
	@ManyToOne
	@JoinColumn (name = "tournament_id", referencedColumnName = "id")
	@Schema(description = "Torneo al que pertenece el partido")
	private Tournament tournament;
	@ManyToOne
	@JoinColumn (name = "team1_id", referencedColumnName = "id")
	 @Schema(description = "Equipo 1 participante en el partido")
	private Team team1;
	@ManyToOne
	@JoinColumn (name = "team2_id", referencedColumnName = "id")
	@Schema(description = "Equipo 2 participante en el partido")
	private Team team2;
	@Column(name = "points_team1")
	@Schema(description = "Puntos anotados por el equipo 1", example = "2")
	private int pointsTeam1;
	@Column(name = "points_team2")
	@Schema(description = "Puntos anotados por el equipo 2", example = "3")
	private int pointsTeam2;
	@ManyToOne
	@JoinColumn (name = "winner_team_id", referencedColumnName = "id")
	@Schema(description = "Equipo ganador del partido")
	private Team winnerTeam;
	
	public Match(Long id, Date date, RoundMatchEnum round, Tournament tournament, Team team1, Team team2,
			int pointsTeam1, int pointsTeam2, Team winnerTeam) {
		super();
		this.id = id;
		this.date = date;
		this.round = round;
		this.tournament = tournament;
		this.team1 = team1;
		this.team2 = team2;
		this.pointsTeam1 = pointsTeam1;
		this.pointsTeam2 = pointsTeam2;
		this.winnerTeam = winnerTeam;
	}
	
	public Match() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public RoundMatchEnum getRound() {
		return round;
	}

	public void setRound(RoundMatchEnum round) {
		this.round = round;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public int getPointsTeam1() {
		return pointsTeam1;
	}

	public void setPointsTeam1(int pointsTeam1) {
		this.pointsTeam1 = pointsTeam1;
	}

	public int getPointsTeam2() {
		return pointsTeam2;
	}

	public void setPointsTeam2(int pointsTeam2) {
		this.pointsTeam2 = pointsTeam2;
	}

	public Team getWinnerTeam() {
		return winnerTeam;
	}

	public void setWinnerTeam(Team winnerTeam) {
		this.winnerTeam = winnerTeam;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", date=" + date + ", round=" + round + ", tournament=" + tournament + ", team1="
				+ team1 + ", team2=" + team2 + ", pointTeam1=" + pointsTeam1 + ", pointTeam2=" + pointsTeam2
				+ ", winnerTeam=" + winnerTeam + "]";
	}
	
}
