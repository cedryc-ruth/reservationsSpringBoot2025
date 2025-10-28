package be.iccbxl.pid.reservations_springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.reservations_springboot.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByLogin(String login);
	List<User> findByLastname(String lastname);
	 
	User findById(long id);
	Optional<User> findByEmail(String email);
}