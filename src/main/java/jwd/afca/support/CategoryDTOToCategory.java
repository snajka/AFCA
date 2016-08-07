package jwd.afca.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.afca.model.Category;
import jwd.afca.service.CategoryService;
import jwd.afca.web.dto.CategoryDTO;
@Component
public class CategoryDTOToCategory 
	implements Converter<CategoryDTO, Category> {
	
	@Autowired
	CategoryService categoryService;

	@Override
	public Category convert(CategoryDTO dto) {
		Category category = new Category();
		
		if(dto.getId()!=null){
			category = categoryService.findOne(dto.getId());
			
			if(category == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant category");
			}
		}
		
		category.setId(dto.getId());
		category.setName(dto.getName());
		category.setDescription(dto.getDescription());
//		category.setAds(dto.getAds());
		
		return category;
	}
	
	public List<Category> convert (List<CategoryDTO> dtoCategories){
		List<Category> categories = new ArrayList<>();
		
		for(CategoryDTO dto : dtoCategories){
			categories.add(convert(dto));
		}
		
		return categories;
	}

}
