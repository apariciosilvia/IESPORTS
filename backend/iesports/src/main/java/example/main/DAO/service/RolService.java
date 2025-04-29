package example.main.DAO.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.main.DAO.repository.RolRepository;
import example.main.modelo.Rol;

@Service
public class RolService {

	@Autowired
	private RolRepository rp;
	
	
	public ArrayList<Rol> getRoles(){
		return (ArrayList<Rol>)rp.findAll();
	}
	
	
	
}
