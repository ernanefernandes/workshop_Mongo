package com.ernanefernandes.workshopmongo.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ernanefernandes.workshopmongo.domain.User;
import com.ernanefernandes.workshopmongo.dto.UserDto;
import com.ernanefernandes.workshopmongo.repository.UserRepository;
import com.ernanefernandes.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
			}
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert (User obj) {
		return repo.insert(obj);
	}
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());	
	}

	public User fromDTO(UserDto objDto) {
		return new User(objDto.getId(),objDto.getName(), objDto.getEmail());
		
	}
}
