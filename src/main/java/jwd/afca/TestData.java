package jwd.afca;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.afca.model.Category;
import jwd.afca.model.ClassifiedAd;
import jwd.afca.model.User;
import jwd.afca.repository.CategoryRepository;
import jwd.afca.repository.ClassifiedAdRepository;
import jwd.afca.repository.UserRepository;
@Component
public class TestData {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ClassifiedAdRepository classifiedAdRepository;
		
	@PostConstruct
	public void init(){

		for (int i = 1; i <= 14; i++) {
			Category category = new Category();
            User user = new User();
            ClassifiedAd ad = new ClassifiedAd();
            
            user.setUsername("user" + i);
            user.setTelephoneNumber("1111" + i);
            user.setEmail("email" + i + "@example.com");
            user.setPassword("123");
            if (i < 4) {
            	user.setRole(User.Role.ADMIN);
            	user.setEnabled(true);
            } else {
            	user.setRole(User.Role.USER);
            	if (i < 6)
            		user.setEnabled(true);
            }
            userRepository.save(user);
            
            category.setName("kategorija" + i);
            category.setDescription("decepticon" + i);
            categoryRepository.save(category);
            
            ad.setTitle("naslov" + i);
            ad.setText("tekst" + i);
            ad.setCreationDate(new Date());
            ad.setExpirationDate(new Date());
            ad.setAuthor(user);
            ad.setCategory(category);
            classifiedAdRepository.save(ad);
		}
	}
}
