package jwd.afca.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.afca.model.Category;
import jwd.afca.service.CategoryService;
import jwd.afca.support.CategoryDTOToCategory;
import jwd.afca.support.CategoryToCategoryDTO;
import jwd.afca.web.dto.CategoryDTO;

@RestController
@RequestMapping("/api/categories")
public class ApiCategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryDTOToCategory toCategory;

	@Autowired
	private CategoryToCategoryDTO toDTO;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> get(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "page", required = false, defaultValue="0") int page,
			@RequestParam(value = "itemsPerPage", required = false, defaultValue="5") int itemsPerPage,
			@RequestParam(value = "direction", required = false, defaultValue="DESC") String direction,
			@RequestParam(value = "property", required = false, defaultValue="id") String property) {

		List<Category> categories;
		Page<Category> categoriesPage;
		int totalPages = 0;
		long totalElements = 0;
		
		if (page == -1) {
			categories = categoryService.findAll();
			
			return new ResponseEntity<>(toDTO.convert(categories), HttpStatus.OK);
		}
		
		if (search != null) {
			categoriesPage = categoryService.findByNameContains(page, itemsPerPage, search);
		} else {
			categoriesPage = categoryService.findAll(page, itemsPerPage, Sort.Direction.fromString(direction), property);
		}
		
		categories = categoriesPage.getContent();
		totalPages = categoriesPage.getTotalPages();
		totalElements = categoriesPage.getTotalElements();
		
		if (page > totalPages) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("total-pages", Integer.toString(totalPages));
		httpHeaders.add("total-elements", Long.toString(totalElements));

		return new ResponseEntity<>(toDTO.convert(categories), httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoryDTO> get(@PathVariable Long id) {

		Category category = categoryService.findOne(id);

		if (category == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(category), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<CategoryDTO> add(
			@RequestBody @Validated CategoryDTO newCategory) {

		Category persisted = categoryService.save(toCategory.convert(newCategory));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<CategoryDTO> edit(@PathVariable Long id,
			@RequestBody @Validated CategoryDTO editedCategory) {

		if (id == null || !id.equals(editedCategory.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Category oldCategory = categoryService.findOne(id);
		if (oldCategory == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Category persisted = categoryService.save(toCategory
				.convert(editedCategory));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CategoryDTO> delete(@PathVariable Long id) {

		categoryService.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
