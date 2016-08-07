package jwd.afca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jwd.afca.model.Category;
import jwd.afca.repository.CategoryRepository;
import jwd.afca.service.CategoryService;

@Service
public class JpaCategoryService 
	implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category findOne(Long id) {
		
		return categoryRepository.findOne(id);
	}

	@Override
	public Page<Category> findAll(int page, int itemsPerPage, Sort.Direction direction, String property) {
		
		return categoryRepository.findAll(new PageRequest(page, itemsPerPage, direction, property));
	}

	@Override
	public Category save(Category category) {
		
		return categoryRepository.save(category);
	}



	@Override
	public void delete(Long id) {
		categoryRepository.delete(id);
	}

	@Override
	public Page<Category> findByNameContains(int page, int itemsPerPage, String search) {
		return categoryRepository.findByNameContains(new PageRequest(page, itemsPerPage), search);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
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
