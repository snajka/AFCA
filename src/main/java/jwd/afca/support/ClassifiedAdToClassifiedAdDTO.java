package jwd.afca.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.afca.model.ClassifiedAd;
import jwd.afca.web.dto.ClassifiedAdDTO;

@Component
public class ClassifiedAdToClassifiedAdDTO 
	implements Converter<ClassifiedAd, ClassifiedAdDTO> {

	@Autowired
	private CategoryToCategoryDTO toCategoryDTO;
	
	@Autowired
	private UserToUserDTO toUserDTO;
	
	@Override
	public ClassifiedAdDTO convert(ClassifiedAd ad) {
		if(ad==null){
			return null;
		}
		
		ClassifiedAdDTO dto = new ClassifiedAdDTO();
		
		dto.setId(ad.getId());
		dto.setTitle(ad.getTitle());
		dto.setText(ad.getText());
		dto.setCreationDate(ad.getCreationDate());
		dto.setExpirationDate(ad.getExpirationDate());
		dto.setCategory(toCategoryDTO.convert(ad.getCategory()));
		dto.setAuthor(toUserDTO.convert(ad.getAuthor()));
		
		return dto;
	}
	
	public List<ClassifiedAdDTO> convert(List<ClassifiedAd> activities){
		List<ClassifiedAdDTO> ret = new ArrayList<>();
		
		for(ClassifiedAd a: activities){
			ret.add(convert(a));
		}
		
		return ret;
	}

}
