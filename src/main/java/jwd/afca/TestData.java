package jwd.afca;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.afca.model.Category;
import jwd.afca.model.ClassifiedAd;
import jwd.afca.model.User;
import jwd.afca.service.CategoryService;
import jwd.afca.service.ClassifiedAdService;
import jwd.afca.service.UserService;
@Component
public class TestData {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
//	@Autowired
//	private RoleService roleService;
	
	@Autowired
	private ClassifiedAdService classifiedAdService;
		
	@PostConstruct
	public void init(){
//		Role role1 = new Role();
//		role1.setName("subscriber");
//		roleService.save(role1);
//		Role role2 = new Role();
//		role2.setName("administrator");
//		roleService.save(role2);
		for (int i = 1; i <= 14; i++) {
			Category category = new Category();
            User user = new User();
            ClassifiedAd ad = new ClassifiedAd();
            
            user.setUsername("user " + i);
            user.setTelephoneNumber("1111" + i);
            user.setEmail("email" + i + "@example.com");
            user.setPassword("123");
            user.setRole(User.Role.USER);
            userService.save(user);
            
            category.setName("kategorija" + i);
            category.setDescription("decepticon" + i);
            categoryService.save(category);
            
            ad.setTitle("naslov" + i);
            ad.setText("tekst" + i);
            ad.setCreationDate(new Date());
            ad.setExpirationDate(new Date());
            ad.setAuthor(user);
            ad.setCategory(category);
            classifiedAdService.save(ad);
		}
	}
}
