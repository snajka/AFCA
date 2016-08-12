package jwd.afca.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import jwd.afca.web.dto.ClassifiedAdDTO;

public interface ClassifiedAdService {
	/**
	 * Returns an activity with specified ID.
	 * @param id ID of the activity
	 * @return Activity, if activity with such ID
	 * exists, {@code null} if activity is not found.
	 */
	ClassifiedAdDTO findOne(Long id);
	
	/**
	 *  
	 * @return List of all existing activities.
	 */
	Map<String, Object> findAll(int page, int itemsPerPage, Sort.Direction direction, String property);
	
	/**
	 * Persists an activity. If activity's id is null,
	 * a new id will be assigned automatically.
	 * @param activity
	 * @return Activity state after persisting. 
	 */
	ClassifiedAdDTO save(ClassifiedAdDTO ad);
	
	/**
	 * Persist a list of activities
	 * @param activities
	 * @return
	 */
	List<ClassifiedAdDTO> save(List<ClassifiedAdDTO> ads);
	
	/**
	 * Deletes an activity having specified ID.
	 * @param id ID of the activity to be removed. 
	 * @return Removed activity if removal is successful. 
	 * If the activity was not found, an {@link IllegalArgumentException}
	 * is thrown.
	 */
	ClassifiedAdDTO delete(Long id);
	
	/**
	 * Remove a list of activities.
	 * @param ids A {@link List} of activity IDs (each ID is of type {@link Long})
	 */
	void delete(List<Long> ids);
	
	/**
	 * 
	 * @param itemsPerPage 
	 * @param title
	 * @return List of Activities who's name equals the string
	 * given in the {@code name} parameter.
	 */
	Map<String, Object> findByTitleContains(int page, int itemsPerPage, String title);

	Map<String, Object> findByExpirationDateAfter(int page, int itemsPerPage, Direction direction, String property);
		
}
