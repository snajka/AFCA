package jwd.afca.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.afca.model.ClassifiedAd;
import jwd.afca.service.ClassifiedAdService;
import jwd.afca.support.ClassifiedAdDTOToClassifiedAd;
import jwd.afca.support.ClassifiedAdToClassifiedAdDTO;
import jwd.afca.web.dto.ClassifiedAdDTO;

@RestController
@RequestMapping(value = "/api/classifieds")
public class ApiClassifiedAdController {

	@Autowired
	private ClassifiedAdService classifiedAdService;

	@Autowired
	private ClassifiedAdToClassifiedAdDTO toDTO;

	@Autowired
	private ClassifiedAdDTOToClassifiedAd toAd;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<ClassifiedAdDTO>> getAds(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "page", required = false, defaultValue="0") int page,
			@RequestParam(value = "itemsPerPage", required = false, defaultValue="5") int itemsPerPage,
			@RequestParam(value = "direction", required = false, defaultValue="DESC") String direction,
			@RequestParam(value = "property", required = false, defaultValue="id") String property) {

		List<ClassifiedAd> ads;
		Page<ClassifiedAd> adsPage;
		int totalPages = 0;
		long totalElements = 0;

		if (search != null) {
			adsPage = classifiedAdService.findByTitleContains(page, itemsPerPage, search);
		} else {
			adsPage = classifiedAdService.findByExpirationDateAfter(page, itemsPerPage, Sort.Direction.fromString(direction), property);
		}
		
		ads = adsPage.getContent();
		totalPages = adsPage.getTotalPages();
		totalElements = adsPage.getTotalElements();
		
		if (page > totalPages) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("total-pages", Integer.toString(totalPages));
		httpHeaders.add("total-elements", Long.toString(totalElements));

		return new ResponseEntity<>(toDTO.convert(ads), httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<ClassifiedAdDTO> getAd(@PathVariable Long id) {
		ClassifiedAd ad = classifiedAdService.findOne(id);
		if (ad == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(ad), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ClassifiedAdDTO> add(@RequestBody ClassifiedAdDTO newAd) {

		ClassifiedAd savedAd = classifiedAdService.save(toAd
				.convert(newAd));

		return new ResponseEntity<>(toDTO.convert(savedAd), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<ClassifiedAdDTO> edit(@RequestBody ClassifiedAdDTO ad,
			@PathVariable Long id) {

		if (id != ad.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		ClassifiedAd persisted = classifiedAdService.save(toAd.convert(ad));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<ClassifiedAdDTO> delete(@PathVariable Long id) {
		ClassifiedAd deleted = classifiedAdService.delete(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

}
