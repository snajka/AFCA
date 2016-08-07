package jwd.afca.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.afca.model.ClassifiedAd;

@Repository
public interface ClassifiedAdRepository 
	extends JpaRepository<ClassifiedAd, Long> {

//	@Query("select a from Activity a where a.name = :name")
//	List<Activity> findByName(@Param("name") String name);

	List<ClassifiedAd> findByTitle(String title);
	
	Page<ClassifiedAd> findByTitleContains(Pageable page, String title);

	Page<ClassifiedAd> findByExpirationDateAfter(Pageable page, Date date);
	
}
