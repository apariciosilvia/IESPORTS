package com.iesports.dao.service;

import java.util.List;

import com.iesports.model.Sport;

public interface ISportService {

	public List<Sport> getSports();

	public Sport getSportById(Long idPerson);

	public Sport saveSport(Sport person);

	public Sport updateSport(Sport person);

	public void deleteSport(Sport person);

	public boolean sportExists(String name);
	
}
