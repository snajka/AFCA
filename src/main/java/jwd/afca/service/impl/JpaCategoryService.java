package jwd.afca.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jwd.afca.model.Category;
import jwd.afca.repository.CategoryRepository;
import jwd.afca.service.CategoryService;
import jwd.afca.support.CategoryDTOToCategory;
import jwd.afca.support.CategoryToCategoryDTO;
import jwd.afca.web.dto.CategoryDTO;

@Service
public class JpaCategoryService 
	implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryDTOToCategory toCategory;

	@Autowired
	private CategoryToCategoryDTO toDTO;
	
	@Override
	public CategoryDTO findOne(Long id) {
		
		return toDTO.convert(categoryRepository.findOne(id));
	}

	@Override
	public Map<String, Object> findAll(int page, int itemsPerPage, Sort.Direction direction, String property) {
		Page<Category> categoriesPage = categoryRepository.findAll(new PageRequest(page, itemsPerPage, direction, property));
		Map<String, Object> map = new HashMap<>();
		map.put("totalPages", categoriesPage.getTotalPages());
		map.put("totalElements", categoriesPage.getTotalElements());
		map.put("dtos", toDTO.convert(categoriesPage));
		
		return map;
	}

	@Override
	public CategoryDTO save(CategoryDTO category) {
		
		return toDTO.convert(categoryRepository.save(toCategory.convert(category)));
	}



	@Override
	public void delete(Long id) {
		categoryRepository.delete(id);
	}

	@Override
	public Map<String, Object> findByNameContains(int page, int itemsPerPage, String search) {
		Page<Category> categoriesPage = categoryRepository.findByNameContains(new PageRequest(page, itemsPerPage), search);
		Map<String, Object> map = new HashMap<>();
		map.put("totalPages", categoriesPage.getTotalPages());
		map.put("totalElements", categoriesPage.getTotalElements());
		map.put("dtos", toDTO.convert(categoriesPage));
			
		return map;
	}

	@Override
	public List<CategoryDTO> findAll() {
		return toDTO.convert(categoryRepository.findAll());
	}

//	@Override
//	public List<Category> findByClassifiedAd(ClassifiedAd ad) {
//		return categoryRepository.findByClassifiedAd(ad);
//	}
//
//	@Override
//	public Category findByIdAndClassifiedAdId(Long id, Long userId) {
//		
//		return categoryRepository.findByIdAndClassifiedAdId(id, userId);
//	}
//
//	@Override
//	public List<Category> findByClassifiedAdId(Long userId) {
//		
//		return categoryRepository.findByClassifiedAdId(userId);
//	}


}
