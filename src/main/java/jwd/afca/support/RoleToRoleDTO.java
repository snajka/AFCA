package jwd.afca.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import jwd.afca.model.Role;
import jwd.afca.web.dto.RoleDTO;

public class RoleToRoleDTO implements Converter<Role, RoleDTO> {

	@Override
	public RoleDTO convert(Role role) {
		RoleDTO dto = new RoleDTO();
		
		dto.setId(role.getId());
		dto.setName(role.getName());
		
		return dto;
	}
	
	public List<RoleDTO> convert(List<Role> roles){
		List<RoleDTO> ret = new ArrayList<>();
		
		for(Role role : roles){
			ret.add(convert(role));
		}
		
		return ret;
	}

}