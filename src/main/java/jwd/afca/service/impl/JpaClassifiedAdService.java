package jwd.afca.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import jwd.afca.model.ClassifiedAd;
import jwd.afca.repository.ClassifiedAdRepository;
import jwd.afca.service.ClassifiedAdService;

@Service
@Transactional
public class JpaClassifiedAdService 
	implements ClassifiedAdService {
	
	@Autowired
	private ClassifiedAdRepository classifiedsRepository;

	@Override
	public ClassifiedAd findOne(Long id) {
		return classifiedsRepository.findOne(id);
	}

	@Override
	public Page<ClassifiedAd> findAll(int page, int itemsPerPage, Sort.Direction direction, String property) {
		return classifiedsRepository.findAll(new PageRequest(page, itemsPerPage, direction, property));
	}

	@Override
	public ClassifiedAd save(ClassifiedAd activity) {
		return classifiedsRepository.save(activity);
	}

	@Override
	public List<ClassifiedAd> save(List<ClassifiedAd> activities) {
		return classifiedsRepository.save(activities);
	}

	@Override
	public ClassifiedAd delete(Long id) {
		ClassifiedAd activity = classifiedsRepository.findOne(id);
		if(activity == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant activity");
		}
		classifiedsRepository.delete(activity);
		return activity;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}

	@Override
	public Page<ClassifiedAd> findByTitleContains(int page, int itemsPerPage, String search) {
		return classifiedsRepository.findByTitleContains(new PageRequest(page, itemsPerPage), search);
	}

	@Override
	public Page<ClassifiedAd> findByExpirationDateAfter(int page, int itemsPerPage, Direction direction, String property) {
		return classifiedsRepository.findByExpirationDateAfter(new PageRequest(page, itemsPerPage, direction, property), new Date());
	}
	
	//@PostConstruct
//	public void БилоШта(){
//		save(new Activity("Swimming"));
//		save(new Activity("Running"));
//	}

}
