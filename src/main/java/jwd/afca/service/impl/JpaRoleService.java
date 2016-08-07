package jwd.afca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.afca.model.Role;
import jwd.afca.repository.RoleRepository;
import jwd.afca.service.RoleService;

@Service
public class JpaRoleService implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findOne(Long id) {
		return roleRepository.findOne(id);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void delete(Long id) {
		roleRepository.delete(id);
	}

}
