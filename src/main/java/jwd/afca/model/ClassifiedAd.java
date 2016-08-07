package jwd.afca.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Models a classified ad.
 * 
 *
 */
@Entity
@Table(name="tblClassifiedAd")
public class ClassifiedAd {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="title",nullable=false)
	private String title;
	
	@Column(name="text")
	private String text;
	
	@Column(name="creation_date")
	private Date creationDate;
	
	@Column(name="expiration_date")
	private Date expirationDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Category category;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User author;
	

	public ClassifiedAd() {
		super();
	}

	public ClassifiedAd(String title) {
		super();
		this.title = title;
	}

	public ClassifiedAd(Long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

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
	
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
		if(author!=null && !author.getAds().contains(this)){
			author.getAds().add(this);
		}
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
		if(category!=null && !category.getAds().contains(this)){
			category.getAds().add(this);
		}
	}

	
}
