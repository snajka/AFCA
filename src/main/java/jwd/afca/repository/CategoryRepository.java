package jwd.afca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.afca.model.Category;

@Repository
public interface CategoryRepository 
	extends JpaRepository<Category, Long>{

	Page<Category> findAll(Pageable page);

	Page<Category> findByNameContains(Pageable page, String search);
	
//	List<Category> findByClassifiedAd(ClassifiedAd ad);
//	
//	List<Category> findByClassifiedAdId(Long adId);
//	
//	Category findByIdAndClassifiedAdId(Long id, Long adId);
}
