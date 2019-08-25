package com.ernanefernandes.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ernanefernandes.workshopmongo.domain.User;
import com.ernanefernandes.workshopmongo.dto.UserDto;
import com.ernanefernandes.workshopmongo.services.UserServices;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserServices service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDto>> findAll(){
		
		List<User> list = service.findAll();
		List<UserDto> listDto = list.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDto> findById(@PathVariable String id){
		
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDto(obj));
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDto objDto){
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}

}
