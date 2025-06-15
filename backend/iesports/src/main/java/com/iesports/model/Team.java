package com.iesports.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "teams")
@Schema(description = "Entidad que representa un equipo")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "ID único del equipo", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;
	@Column(name = "name", unique = true)
    @Schema(description = "Nombre único del equipo", example = "Advance Junior High")
	private String name;
	
	@ManyToMany
    @JoinTable(
        name = "players_team",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
	@Schema(description = "Lista de jugadores que pertenecen al equipo")
	List<Person> players;
	
	public Team(Long id, String name, List<Person> players) {
		super();
		this.id = id;
		this.name = name;
		this.players = players;
	}



	public Team() {
		super();
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

	public List<Person> getPlayers() {
		return players;
	}

	public void setPlayers(List<Person> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", players=" + players + "]";
	}
}
