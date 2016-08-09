package jwd.afca.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_categories")
public class Category {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@OneToMany(mappedBy="category",cascade=CascadeType.REMOVE)
	private List<ClassifiedAd> ads = new ArrayList<>();
		
	public void addAds(ClassifiedAd ad){
		this.ads.add(ad);
		
		if(ad.getCategory()!=this){
			ad.setCategory(this);
		}
	}
	
	public void removeAds(ClassifiedAd ad){
		ad.setCategory(null);
		ads.remove(ad);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ClassifiedAd> getAds() {
		return ads;
	}
	public void setAds(List<ClassifiedAd> ads) {
		this.ads = ads;
	}

}
