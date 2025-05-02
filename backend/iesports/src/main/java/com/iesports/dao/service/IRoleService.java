package com.iesports.dao.service;

import java.util.List;

import com.iesports.model.Role;

public interface IRoleService {

	public List<Role> getRoles();

	public Role getRole(Long idRole);

	public Role saveRole(Role role);

	public Role updateRole(Role role);

	public void deleteRole(Role role);
}
