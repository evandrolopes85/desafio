package dev.evandro.desafio_picpay.dto;

import dev.evandro.desafio_picpay.model.AccountType;
import dev.evandro.desafio_picpay.model.User;

public record UserDTO(Integer id, String fullName, String cpfCnpj, String email, String password, AccountType accoutType) {

	public User toUser() {
		User user = new User();
		user.setIdUser(id);
		user.setFullName(fullName);
		user.setCpfCnpj(cpfCnpj);
		user.setEmail(email);
		user.setPassword(password);
		user.setAccountType(accoutType);
		
		return user;
	}
}
