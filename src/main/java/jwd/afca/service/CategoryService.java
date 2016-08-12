package jwd.afca.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;

import jwd.afca.web.dto.CategoryDTO;

public interface CategoryService {
	/**
	 * Returns an address with specified ID.
	 * @param id ID of the address
	 * @return Address, if address with such ID
	 * exists, {@code null} if address is not found.
	 */
	CategoryDTO findOne(Long id);
	
	/**
	 *  
	 * @return List of all existing addresses.
	 */
	Map<String, Object> findAll(int page, int itemsPerPage, Sort.Direction direction, String property);
	
	/**
	 * Persists an address. If address's id is null,
	 * a new id will be assigned automatically.
	 * @param address
	 * @return Address state after persisting. 
	 */
	CategoryDTO save(CategoryDTO category);
	

	/**
	 * Deletes an address having specified ID.
	 * @param id ID of the address to be removed. 
	 */
	void delete(Long id);

	Map<String, Object> findByNameContains(int page, int itemsPerPage, String search);

	List<CategoryDTO> findAll();
	
	
//	List<CategoryDTO> findByClassifiedAd(ClassifiedAd ad);
//	
//	CategoryDTO findByIdAndClassifiedAdId(Long id, Long adId);
//
//	List<CategoryDTO> findByClassifiedAdId(Long adId);
}
