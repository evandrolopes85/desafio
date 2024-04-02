package dev.evandro.desafio_picpay.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.evandro.desafio_picpay.dto.UserDTO;
import dev.evandro.desafio_picpay.exceptions.CpfCnpjAlreadyException;
import dev.evandro.desafio_picpay.exceptions.EmailAlreadyException;
import dev.evandro.desafio_picpay.exceptions.FieldInvalidException;
import dev.evandro.desafio_picpay.exceptions.UserNotFoundException;
import dev.evandro.desafio_picpay.model.User;
import dev.evandro.desafio_picpay.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		// TODO Auto-generated constructor stub
		this.userRepository = userRepository;
	}

	@Override
	public UserDTO save(UserDTO novo) {
		// TODO Auto-generated method stub

		if (novo.fullName() == null || novo.email() == null || novo.password() == null || novo.cpfCnpj() == null
				|| novo.accoutType() == null) {
			throw new FieldInvalidException("Requisicao Invalida, verifique os dados");
		}

		User user = userRepository.findByCpfCnpj(novo.cpfCnpj());

		if (Objects.nonNull(user)) {
			throw new CpfCnpjAlreadyException("CPF/CNPJ ja cadastrados");
		} else {
			user = userRepository.findByEmail(novo.email());
			if (Objects.nonNull(user)) {
				throw new EmailAlreadyException("Email ja cadastrado");
			}
		}

		if (Objects.isNull(user)) {
			User userSaved = userRepository.save(novo.toUser());
			return new UserDTO(userSaved.getIdUser(), userSaved.getFullName(), userSaved.getCpfCnpj(),
					userSaved.getEmail(), userSaved.getPassword(), userSaved.getAccountType());
		}

		return null;
	}

	@Override
	public List<UserDTO> listAll() {

		List<User> list = (List<User>) userRepository.findAll();

		if (Objects.nonNull(list)) {
			return list.stream().map(user -> new UserDTO(user.getIdUser(), user.getFullName(), user.getCpfCnpj(),
					user.getEmail(), user.getPassword(), user.getAccountType())).collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public UserDTO findById(Integer id) {
		if(Objects.isNull(id)) {
			throw new FieldInvalidException("Requisicao Invalida, verifique os dados");
		}
		
		User user = userRepository.findById(id).orElse(null);
		
		if(Objects.isNull(user)){
			throw new UserNotFoundException("User not found");
		}
		return new UserDTO(user.getIdUser(), user.getFullName(), user.getCpfCnpj(), user.getEmail(), "", user.getAccountType());
	}

	@Override
	public UserDTO update(UserDTO update) {
		// TODO Auto-generated method stub
		return null;
	}

}
