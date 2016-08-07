package jwd.afca.web.dto;

import java.util.Date;

public class ClassifiedAdDTO {
	private Long id;
	private String title;
	private String text;
	private Date creationDate;
	private Date expirationDate;
	private CategoryDTO category;
	private UserDTO author;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public CategoryDTO getCategory() {
		return category;
	}
	public void setCategory(CategoryDTO category) {
		this.category = category;
	}
	public UserDTO getAuthor() {
		return author;
	}
	public void setAuthor(UserDTO author) {
		this.author = author;
	}
	
}
