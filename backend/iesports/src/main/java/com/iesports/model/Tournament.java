package com.iesports.model;


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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.iesports.enums.StateTournamentEnum;

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
    
    @NotNull(message = "El número tiene que ser mínimo 4")
    @Column(name = "max_team")
    private int maxTeams;

    @NotNull(message = "El estado del torneo es obligatorio")
    @Enumerated(EnumType.STRING)
    private StateTournamentEnum state;

    @ManyToOne
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    private Sport sport;

    public Tournament() {
    	
    }
    
//    public Tournament(Long id, @NotBlank(message = "El nombre del torneo es obligatorio") String name,
//			@NotNull(message = "La fecha del torneo es obligatoria") String date,
//			@NotBlank(message = "El estado del torneo es obligatorio") StateTournamentEnum state, Sport sport) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.date = date;
//		this.state = state;
//		this.sport = sport;
//	}

	// Getters and setters

    public Tournament(Long id, @NotBlank(message = "El nombre del torneo es obligatorio") String name,
			@NotNull(message = "La fecha del torneo es obligatoria") String date,
			@NotBlank(message = "El estado del torneo es obligatorio") StateTournamentEnum state, Sport sport,
			@NotNull(message = "El número tiene que ser mínimo 4") int maxTeams) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.maxTeams = maxTeams;
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
    
    public int getMaxTeams() {
		return maxTeams;
	}

	public void setMaxTeams(int maxTeams) {
		this.maxTeams = maxTeams;
	}

	@Override
	public String toString() {
		return "Tournament [id=" + id + ", name=" + name + ", date=" + date + ", maxTeams=" + maxTeams + ", state="
				+ state + ", sport=" + sport + "]";
	}
	
	
}
