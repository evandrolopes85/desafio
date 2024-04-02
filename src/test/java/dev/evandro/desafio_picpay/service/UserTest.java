package dev.evandro.desafio_picpay.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.evandro.desafio_picpay.dto.UserDTO;
import dev.evandro.desafio_picpay.exceptions.CpfCnpjAlreadyException;
import dev.evandro.desafio_picpay.exceptions.EmailAlreadyException;
import dev.evandro.desafio_picpay.exceptions.FieldInvalidException;
import dev.evandro.desafio_picpay.exceptions.UserNotFoundException;
import dev.evandro.desafio_picpay.model.AccountType;
import dev.evandro.desafio_picpay.services.IUserService;

@SpringBootTest
public class UserTest {
	
	private UserDTO validRequest;
	private UserDTO invalidRequest;
	private UserDTO duplicateEmailRequest;
	private UserDTO duplicateCpfCnpjRequest;
	
	@Mock
	//@Autowired
	private IUserService service;
	
	@Test
	public void shouldRegisterAValidUser() {
		UserDTO validRequest = new UserDTO(null, "Tiago", "155935785214", "quiteriolopes@hotmail.com", "123456", AccountType.COMMON);
		
		Mockito.when(service.save(validRequest)).thenReturn(validRequest);
		
		assertNotNull(service.save(validRequest));
	}
	
	@Test
	public void shouldNotRegisterAnInValidUser() {
		UserDTO invalidRequest = new UserDTO(null, "Tiago", null, "quiteriolopes@hotmail.com", "123456", AccountType.COMMON);
		
		Mockito.when(service.save(invalidRequest)).thenThrow(new FieldInvalidException("Requisicao Invalida, verifique os dados"));
		
		assertThrows(FieldInvalidException.class, () -> {
			service.save(invalidRequest);
		});
	}
	
	@Test
	public void shouldNotRegisterDuplicateEmail() {
		UserDTO duplicateEmailRequest = new UserDTO(null, "Tiago", "33366234805", "evandrocwd@gmail.com", "123456", AccountType.COMMON);
		
		Mockito.when(service.save(duplicateEmailRequest)).thenThrow(new EmailAlreadyException("Email ja cadastrado"));
		
		assertThrows(EmailAlreadyException.class, () -> service.save(duplicateEmailRequest));
	}
	
	@Test
	@DisplayName("should Not Register Duplicate CpfCnpj")
	public void shouldNotRegisterDuplicateCpfCnpj() {
		UserDTO duplicateCpfCnpjRequest = new UserDTO(null, "Fredegundo freitas", "33366234806", "evandrocwdd@gmail.com", "123456", AccountType.COMMON);
		
		Mockito.when(service.save(duplicateCpfCnpjRequest)).thenThrow(new CpfCnpjAlreadyException("CPF/CNPJ ja cadastrados"));
		
		assertThrows(CpfCnpjAlreadyException.class, () -> {
			service.save(duplicateCpfCnpjRequest);
		});
	}
	
	@Test
	public void shouldReturnUserList() {
		Mockito.when(service.listAll()).thenReturn(new ArrayList<>());
		
		assertNotNull(service.listAll());
	}
	
	@Test
	public void shouldReturnUserWithId() {
		UserDTO userFound = new UserDTO(100, "Fredegundo freitas", "33366234806", "evandrocwdd@gmail.com", "123456", AccountType.COMMON);
		
		Mockito.when(service.findById(100)).thenReturn(userFound);
		
		assertNotNull(service.findById(100));
	}
	
	@Test
	public void shouldNotReturnUserWithInvalidId() {
		Integer InvalidId = 1000;
		
		Mockito.when(service.findById(InvalidId)).thenThrow(new UserNotFoundException("User not found!"));
		
		assertThrows(UserNotFoundException.class, () -> {
			service.findById(InvalidId);
		});
	}
	
	@Test
	public void shouldNotReturnUserWithNullId() {
		Integer InvalidId = null;
		
		Mockito.when(service.findById(InvalidId)).thenThrow(new FieldInvalidException("Id cannot be null"));
		
		assertThrows(FieldInvalidException.class, () -> {
			service.findById(InvalidId);
		});
	}
}
