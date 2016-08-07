package jwd.afca.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.afca.model.ClassifiedAd;
import jwd.afca.service.ClassifiedAdService;
import jwd.afca.web.dto.ClassifiedAdDTO;

@Component
public class ClassifiedAdDTOToClassifiedAd 
	implements Converter<ClassifiedAdDTO, ClassifiedAd> {
	
	@Autowired
	ClassifiedAdService classifiedAdService;

	@Autowired
	private CategoryDTOToCategory toCategory;
	
	@Autowired
	private UserDTOToUser toUser;
	
	@Override
	public ClassifiedAd convert(ClassifiedAdDTO dto) {
		ClassifiedAd ad = new ClassifiedAd();
		
		if(dto.getId()!=null){
			ad = classifiedAdService.findOne(dto.getId());
			
			if(ad == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant ClassifiedAd");
			}
		}
		
		ad.setId(dto.getId());
		ad.setTitle(dto.getTitle());
		ad.setText(dto.getText());
		ad.setCreationDate(dto.getCreationDate());
		ad.setExpirationDate(dto.getExpirationDate());
		ad.setCategory(toCategory.convert(dto.getCategory()));
		ad.setAuthor(toUser.convert(dto.getAuthor()));
		
		return ad;
	}
	
	public List<ClassifiedAd> convert (List<ClassifiedAdDTO> dtoAds){
		List<ClassifiedAd> ads = new ArrayList<>();
		
		for(ClassifiedAdDTO dto : dtoAds){
			ads.add(convert(dto));
		}
		
		return ads;
	}

}
