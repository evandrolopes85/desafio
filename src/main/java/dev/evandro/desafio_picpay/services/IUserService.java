package dev.evandro.desafio_picpay.services;

import java.util.List;

import dev.evandro.desafio_picpay.dto.UserDTO;

public interface IUserService {
	public UserDTO save(UserDTO novo);
	public List<UserDTO> listAll();
	public UserDTO findById(Integer id);
	public UserDTO update(UserDTO update); 
}
