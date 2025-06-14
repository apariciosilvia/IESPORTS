package com.iesports.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TournamentModifyDTO {

	@NotNull(message = "El torneo es obligatorio")
	private Long tournamentId;
	
	@NotBlank(message = "El nombre del torneo es obligatorio")
	private String tournamentNameModified;
	
	@NotNull(message = "El deporte es obligatorio")
	private Long sportModifiedId;
	
	@NotNull(message = "Debe haber algun partido")
	private List<MatchModifyTournamentDTO> matches;

	public TournamentModifyDTO(long tournamentId, String tournamentNameModified, Long sportModifiedId,
			List<MatchModifyTournamentDTO> matches) {
		super();
		this.tournamentId = tournamentId;
		this.tournamentNameModified = tournamentNameModified;
		this.sportModifiedId = sportModifiedId;
		this.matches = matches;
	}

	public long getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(long tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getTournamentNameModified() {
		return tournamentNameModified;
	}

	public void setTournamentNameModified(String tournamentNameModified) {
		this.tournamentNameModified = tournamentNameModified;
	}

	public Long getSportModifiedId() {
		return sportModifiedId;
	}

	public void setSportModifiedId(Long sportModifiedId) {
		this.sportModifiedId = sportModifiedId;
	}

	public List<MatchModifyTournamentDTO> getMatches() {
		return matches;
	}

	public void setMatches(List<MatchModifyTournamentDTO> matches) {
		this.matches = matches;
	}

	@Override
	public String toString() {
		return "TournamentModifyDTO [tournamentId=" + tournamentId + ", tournamentNameModified="
				+ tournamentNameModified + ", sportModifiedId=" + sportModifiedId + ", matches=" + matches + "]";
	}

}
