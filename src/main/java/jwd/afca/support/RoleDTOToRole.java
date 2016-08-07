package jwd.afca.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import jwd.afca.model.Role;
import jwd.afca.repository.RoleRepository;
import jwd.afca.web.dto.RoleDTO;

public class RoleDTOToRole implements Converter<RoleDTO, Role> {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role convert(RoleDTO dto) {
		Role role = null;
		if(dto.getId()==null){
			role = new Role();
		}else{
			role = roleRepository.findOne(dto.getId());
			if(role == null){
				throw new IllegalStateException("Editing non-existant Role");
			}
		}
		role.setName(dto.getName());
		
		return role;
	}
	
	public List<Role> convert(List<RoleDTO> rolesDTO){
		List<Role> ret = new ArrayList<>();
		
		for(RoleDTO dto: rolesDTO){
			ret.add(convert(dto));
		}
		
		return ret;
	}

}
