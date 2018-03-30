package com.sandra.ideas.services;

import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sandra.ideas.models.Role;
import com.sandra.ideas.models.User;
import com.sandra.ideas.repositories.RoleRepository;
import com.sandra.ideas.repositories.UserRepository;

@Service
public class UserService {
	
	    private UserRepository userRepository;
	    private RoleRepository roleRepository;
	    private BCryptPasswordEncoder bCryptPasswordEncoder;
	    
	    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;
	        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	        init();
	    }
	    
	    public void init() {
		    	if(roleRepository.findAll().size() < 1) {
		    		Role user = new Role();
		    		user.setName("ROLE_USER");
		    		
		    		Role admin = new Role();
		    		admin.setName("ROLE_ADMIN");
		    		
		    		roleRepository.save(user);
		    		roleRepository.save(admin);
		    		
		    	}
	    }
	    
	    // 1
	    public void saveWithUserRole(User user) {
	        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        user.setRoles(roleRepository.findByName("ROLE_USER"));
	        userRepository.save(user);
	    }
	     
	     // 2 
	    public void saveUserWithAdminRole(User user) {
	        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
	        userRepository.save(user);
	    }    
	    
	    // 3
	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
	    
	    public ArrayList<User> all() {
			return (ArrayList<User>) userRepository.findAll();
	    }
	   
	}



