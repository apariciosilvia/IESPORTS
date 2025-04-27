package example.main.DAO.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import example.main.DAO.repository.RolesRepository;
import example.main.modelo.Roles;

@Service
public class RolesService {

	RolesRepository rp;
	
	
	public ArrayList<Roles> getRoles(){
		return (ArrayList<Roles>)rp.findAll();
	}
	
	
	
}
