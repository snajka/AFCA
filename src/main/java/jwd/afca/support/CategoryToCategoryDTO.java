package jwd.afca.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import jwd.afca.model.Category;
import jwd.afca.web.dto.CategoryDTO;

@Component
public class CategoryToCategoryDTO implements Converter<Category, CategoryDTO> {

	@Override
	public CategoryDTO convert(Category category) {
		CategoryDTO dto = new CategoryDTO();
		
		dto.setId(category.getId());
		dto.setName(category.getName());
		dto.setDescription(category.getDescription());
//		dto.setAds(category.getAds());
		
		return dto;
	}
	
	public List<CategoryDTO> convert(List<Category> categories){
		List<CategoryDTO> ret = new ArrayList<>();
		
		for(Category category : categories){
			ret.add(convert(category));
		}
		
		return ret;
	}
	
	public List<CategoryDTO> convert(Page<Category> categoriesPage){
		List<Category> categories = categoriesPage.getContent();
		List<CategoryDTO> dtos = convert(categories);
		
		return dtos;
	}
	
}
