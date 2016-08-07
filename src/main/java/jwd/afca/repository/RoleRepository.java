package jwd.afca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.afca.model.Role;

@Repository
public interface RoleRepository 
	extends JpaRepository<Role, Long> {
	

}
