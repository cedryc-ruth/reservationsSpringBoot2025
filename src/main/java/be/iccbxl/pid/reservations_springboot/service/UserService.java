package be.iccbxl.pid.reservations_springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservations_springboot.model.User;
import be.iccbxl.pid.reservations_springboot.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		
		userRepository.findAll().forEach(users::add);
		
		return users;
	}
	
	public User getUser(long id) {
		return userRepository.findById(id);
	}

	public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
 
	public void addUser(User user) {
		userRepository.save(user);
	}
 
	public void updateUser(long id, User user) {
		userRepository.save(user);
	}
 
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}
}