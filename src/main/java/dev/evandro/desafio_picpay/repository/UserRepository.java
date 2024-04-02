package dev.evandro.desafio_picpay.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.evandro.desafio_picpay.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	public User findByCpfCnpj(String cpfCnpj);
	public User findByEmail(String email);
}
