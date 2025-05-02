package com.iesports.dao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesports.dao.repository.RoleRepository;
import com.iesports.dao.service.IRoleService;
import com.iesports.model.Role;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private RoleRepository rp;
	

	@Override
	public List<Role> getRoles() {
		return rp.findAll();
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public Role getRole(Long idRole) {
		return rp.getById(idRole);
	}


	@Override
	public Role saveRole(Role role) {
		return rp.save(role);
	}


	@Override
	public Role updateRole(Role role) {
		return rp.save(role);
	}


	@Override
	public void deleteRole(Role role) {
		rp.delete(role);
	}

	
//	public ArrayList<Role> getRoles(){
//		return (ArrayList<Role>)rp.findAll();
//	}
}
