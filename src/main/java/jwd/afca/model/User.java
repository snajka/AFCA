package jwd.afca.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tblUser")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="username",nullable=false)
	private String username;
	
	@Column(name="telephone_number")
	private String telephoneNumber;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Role role;
	
	@OneToMany(mappedBy="author",cascade=CascadeType.REMOVE)
	private List<ClassifiedAd> ads = new ArrayList<>();
	
	public void addAds(ClassifiedAd ad){
		this.ads.add(ad);
		
		if(ad.getAuthor()!=this){
			ad.setAuthor(this);
		}
	}
	
	public void removeAds(ClassifiedAd ad){
		ad.setAuthor(null);
		ads.remove(ad);
	}
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public List<ClassifiedAd> getAds() {
		return ads;
	}
	public void setAds(List<ClassifiedAd> ads) {
		this.ads = ads;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
		if(role!=null && !role.getUsers().contains(this)){
			role.getUsers().add(this);
		}
	}
	
}
