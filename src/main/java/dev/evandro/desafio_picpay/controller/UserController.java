package dev.evandro.desafio_picpay.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.evandro.desafio_picpay.dto.UserDTO;
import dev.evandro.desafio_picpay.services.IUserService;

@RestController
public class UserController {

	@Autowired
	private IUserService service;
	
	@PostMapping("/user")
	public ResponseEntity<UserDTO> save(@RequestBody UserDTO user){
		
		UserDTO response = service.save(user);
		
		if(Objects.nonNull(response)) {
			return ResponseEntity.status(201).body(response);
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<UserDTO> list = service.listAll();
		
		if(Objects.nonNull(list)) {
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDTO> findbyId(@PathVariable Integer id){
		
		UserDTO user = service.findById(id);
		
		if(Objects.nonNull(user)) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.badRequest().build();
	}
	
	
}
