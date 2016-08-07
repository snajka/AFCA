package jwd.afca.service;

import java.util.List;

import jwd.afca.model.Role;

public interface RoleService {

	Role findOne(Long id);
	
	List<Role> findAll();
	
	Role save(Role role);
	
	void delete(Long id);
}
