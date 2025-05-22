package com.iesports.model;


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

    @NotBlank(message = "El estado del torneo es obligatorio")
    @Enumerated(EnumType.STRING)
    private StateTournamentEnum state;
    
    @NotBlank(message = "El número de equipos es obligatoria")
    private int max_equipos;
    
    //Esta variable siempre va a empezar con 0 (SE IRA ACTUALIZANDO CON UPDATES)
    private int current_equipos;

    @ManyToOne
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    private Sport sport;

    public Tournament() {
    	current_equipos = 0;
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

    public Long getId() {
        return id;
    }

    public Tournament(Long id, @NotBlank(message = "El nombre del torneo es obligatorio") String name,
		@NotNull(message = "La fecha del torneo es obligatoria") String date, int max_equipos, Sport sport) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		
		//TODO REALIZAR COMPROBACIÓN FECHA
		
		this.state = StateTournamentEnum.PENDIENTE;
		this.max_equipos = max_equipos;
		this.current_equipos = 0;
		this.sport = sport;
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

    public int getMax_equipos() {
		return max_equipos;
	}

	public void setMax_equipos(int max_equipos) {
		this.max_equipos = max_equipos;
	}

	public int getCurrent_equipos() {
		return current_equipos;
	}

	public void setCurrent_equipos(int current_equipos) {
		this.current_equipos = current_equipos;
	}

	@Override
    public String toString() {
        return "Tournament [id=" + id + ", name=" + name + ", date=" + date + ", state=" + state + ", sport=" + sport + "]";
    }
}
