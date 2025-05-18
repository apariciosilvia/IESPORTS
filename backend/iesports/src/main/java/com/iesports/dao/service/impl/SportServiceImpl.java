package com.iesports.dao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesports.dao.repository.SportRepository;
import com.iesports.dao.service.ISportService;
import com.iesports.model.Sport;

@Service
public class SportServiceImpl implements ISportService{

	@Autowired
	private SportRepository sr;
	@Override
	public List<Sport> getSports() {
		return sr.findAll();
	}

	@Override
	public Sport getSportById(Long idSport) {
		return sr.findById(idSport).orElse(null);
	}

	@Override
	public Sport saveSport(Sport sport) {
		return sr.save(sport);
	}

	@Override
	public Sport updateSport(Sport sport) {
		return sr.save(sport);
	}

	@Override
	public void deleteSport(Sport sport) {
		sr.delete(sport);
	}
	
	@Override
	public boolean sportExists(String name) {
		return sr.sportExists(name) != 0;
	}

}
