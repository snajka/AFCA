package jwd.afca.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import jwd.afca.model.Category;

public interface CategoryService {
	/**
	 * Returns an address with specified ID.
	 * @param id ID of the address
	 * @return Address, if address with such ID
	 * exists, {@code null} if address is not found.
	 */
	Category findOne(Long id);
	
	/**
	 *  
	 * @return List of all existing addresses.
	 */
	Page<Category> findAll(int page, int itemsPerPage, Sort.Direction direction, String property);
	
	/**
	 * Persists an address. If address's id is null,
	 * a new id will be assigned automatically.
	 * @param address
	 * @return Address state after persisting. 
	 */
	Category save(Category category);
	

	/**
	 * Deletes an address having specified ID.
	 * @param id ID of the address to be removed. 
	 */
	void delete(Long id);

	Page<Category> findByNameContains(int page, int itemsPerPage, String search);

	List<Category> findAll();
	
	
//	List<Category> findByClassifiedAd(ClassifiedAd ad);
//	
//	Category findByIdAndClassifiedAdId(Long id, Long adId);
//
//	List<Category> findByClassifiedAdId(Long adId);
}
