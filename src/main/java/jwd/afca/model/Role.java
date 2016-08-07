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
@Table(name="tblRole")
public class Role {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(name="name",nullable=false)
	private String name;

	@OneToMany(mappedBy="role",cascade=CascadeType.REMOVE)
	private List<User> users = new ArrayList<>();
	
	public void addUsers(User user){
		this.users.add(user);
		
		if(user.getRole()!=this){
			user.setRole(this);
		}
	}
	
	public void removeUsers(User user){
		user.setRole(null);
		users.remove(user);
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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
