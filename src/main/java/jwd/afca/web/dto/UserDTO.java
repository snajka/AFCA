package jwd.afca.web.dto;

public class UserDTO {
	private Long id;
	private String email;
	private String username;
	private String telephoneNumber;
	private RoleDTO role;
//	private List<ClassifiedAd> ads;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public RoleDTO getRole() {
		return role;
	}
	public void setRole(RoleDTO role) {
		this.role = role;
	}
//	public List<ClassifiedAd> getAds() {
//		return ads;
//	}
//	public void setAds(List<ClassifiedAd> ads) {
//		this.ads = ads;
//	}
	
}
