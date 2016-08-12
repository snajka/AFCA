package jwd.afca.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import jwd.afca.support.ClassifiedAdDTOToClassifiedAd;
import jwd.afca.support.ClassifiedAdToClassifiedAdDTO;
import jwd.afca.web.dto.ClassifiedAdDTO;

@Service
@Transactional
public class JpaClassifiedAdService 
	implements ClassifiedAdService {
	
	@Autowired
	private ClassifiedAdRepository classifiedsRepository;

	@Autowired
	private ClassifiedAdToClassifiedAdDTO toDTO;

	@Autowired
	private ClassifiedAdDTOToClassifiedAd toAd;
	
	@Override
	public ClassifiedAdDTO findOne(Long id) {
		return toDTO.convert(classifiedsRepository.findOne(id));
	}

	@Override
	public Map<String, Object> findAll(int page, int itemsPerPage, Sort.Direction direction, String property) {
		Page<ClassifiedAd> adsPage = classifiedsRepository.findAll(new PageRequest(page, itemsPerPage, direction, property));
		Map<String, Object> map = new HashMap<>();
		map.put("totalPages", adsPage.getTotalPages());
		map.put("totalElements", adsPage.getTotalElements());
		map.put("dtos", toDTO.convert(adsPage));
		
		return map;
	}

	@Override
	public ClassifiedAdDTO save(ClassifiedAdDTO activity) {
		return toDTO.convert(classifiedsRepository.save(toAd.convert(activity)));
	}

	@Override
	public List<ClassifiedAdDTO> save(List<ClassifiedAdDTO> activities) {
		return toDTO.convert(classifiedsRepository.save(toAd.convert(activities)));
	}

	@Override
	public ClassifiedAdDTO delete(Long id) {
		ClassifiedAd ad = classifiedsRepository.findOne(id);
		if(ad == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant activity");
		}
		classifiedsRepository.delete(ad);
		return toDTO.convert(ad);
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}

	@Override
	public Map<String, Object> findByTitleContains(int page, int itemsPerPage, String search) {
		Page<ClassifiedAd> adsPage = classifiedsRepository.findByTitleContains(new PageRequest(page, itemsPerPage), search);
		Map<String, Object> map = new HashMap<>();
		map.put("totalPages", adsPage.getTotalPages());
		map.put("totalElements", adsPage.getTotalElements());
		map.put("dtos", toDTO.convert(adsPage));
		
		return map;
	}

	@Override
	public Map<String, Object> findByExpirationDateAfter(int page, int itemsPerPage, Direction direction, String property) {
		Page<ClassifiedAd> adsPage = classifiedsRepository.findByExpirationDateAfter(new PageRequest(page, itemsPerPage, direction, property), new Date());
		Map<String, Object> map = new HashMap<>();
		map.put("totalPages", adsPage.getTotalPages());
		map.put("totalElements", adsPage.getTotalElements());
		map.put("dtos", toDTO.convert(adsPage));
		
		return map;
	}

}
