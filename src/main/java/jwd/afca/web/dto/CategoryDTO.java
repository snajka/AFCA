package jwd.afca.web.dto;

public class CategoryDTO {
	private Long id;
	private String name;
	private String description;
//	private List<ClassifiedAd> ads;

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
//	public List<ClassifiedAd> getAds() {
//		return ads;
//	}
//	public void setAds(List<ClassifiedAd> ads) {
//		this.ads = ads;
//	}

}
