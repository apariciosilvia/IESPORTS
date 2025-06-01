package com.iesports.dto;

import java.util.Date;

public class MatchModifyTournamentDTO {
	private long matchId;
	private Date matchDate;
	private int scoreTeam1;
	private int scoreTeam2;
	
	public MatchModifyTournamentDTO(long matchId, Date matchDate, int scoreTeam1, int scoreTeam2) {
		super();
		this.matchId = matchId;
		this.matchDate = matchDate;
		this.scoreTeam1 = scoreTeam1;
		this.scoreTeam2 = scoreTeam2;
	}

	public long getMatchId() {
		return matchId;
	}
	
	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}
	public Date getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	public int getScoreTeam1() {
		return scoreTeam1;
	}
	public void setScoreTeam1(int scoreTeam1) {
		this.scoreTeam1 = scoreTeam1;
	}
	public int getScoreTeam2() {
		return scoreTeam2;
	}
	public void setScoreTeam2(int scoreTeam2) {
		this.scoreTeam2 = scoreTeam2;
	}
	
	
}
