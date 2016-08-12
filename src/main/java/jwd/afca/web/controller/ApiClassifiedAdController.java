package jwd.afca.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.afca.service.ClassifiedAdService;
import jwd.afca.service.UserService;
import jwd.afca.web.dto.ClassifiedAdDTO;
import jwd.afca.web.dto.UserDTO;

@RestController
@RequestMapping(value = "/api/classifieds")
public class ApiClassifiedAdController {

	@Autowired
	private ClassifiedAdService classifiedAdService;
	
	@Autowired
	private UserService userService;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<ClassifiedAdDTO>> getAds(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "page", required = false, defaultValue="0") int page,
			@RequestParam(value = "itemsPerPage", required = false, defaultValue="5") int itemsPerPage,
			@RequestParam(value = "direction", required = false, defaultValue="ASC") String direction,
			@RequestParam(value = "property", required = false, defaultValue="id") String property) {

		List<ClassifiedAdDTO> ads;
		int totalPages = 0;
		long totalElements = 0;
		HashMap<String, Object> map;

		if (search != null) {
			map = (HashMap<String, Object>) classifiedAdService.findByTitleContains(page, itemsPerPage, search);
		} else {
			map = (HashMap<String, Object>) classifiedAdService.findByExpirationDateAfter(page, itemsPerPage, Sort.Direction.fromString(direction), property);
		}
		
		ads = (List<ClassifiedAdDTO>) map.get("dtos");
		totalPages = (int) map.get("totalPages");
		totalElements = (long) map.get("totalElements");
		
		if (page > totalPages) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("total-pages", Integer.toString(totalPages));
		httpHeaders.add("total-elements", Long.toString(totalElements));

		return new ResponseEntity<>(ads, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<ClassifiedAdDTO> getAd(@PathVariable Long id) {
		ClassifiedAdDTO ad = classifiedAdService.findOne(id);
		if (ad == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(ad, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ClassifiedAdDTO> add(@RequestBody ClassifiedAdDTO newAd) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	UserDTO currentUser = userService.findByUsername(username);	  
    	newAd.setAuthor(currentUser);
    	newAd.setCreationDate(new Date());
		ClassifiedAdDTO savedAd = classifiedAdService.save(newAd);

		return new ResponseEntity<>(savedAd, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<ClassifiedAdDTO> edit(@RequestBody ClassifiedAdDTO ad,
			@PathVariable Long id) {

		if (id != ad.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		ClassifiedAdDTO persisted = classifiedAdService.save(ad);

		return new ResponseEntity<>(persisted, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<ClassifiedAdDTO> delete(@PathVariable Long id) {
		ClassifiedAdDTO deleted = classifiedAdService.delete(id);

		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}

}
