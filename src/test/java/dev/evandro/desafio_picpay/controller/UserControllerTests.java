package dev.evandro.desafio_picpay.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.evandro.desafio_picpay.dto.UserDTO;
import dev.evandro.desafio_picpay.model.AccountType;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testeEndPointAdicionarUser() throws Exception {
		String userJson  = null;
		
		UserDTO userDTO = new UserDTO(null, "Evandro Lopes", "11848026021", "evandroc@gmail.com", "123456", AccountType.COMMON);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			userJson = objectMapper.writeValueAsString(userDTO);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(userJson);
		mockMvc.perform(MockMvcRequestBuilders.post("/user")
				.contentType("application/json")
				.content(userJson))
		.andExpect(MockMvcResultMatchers.status().is(201));
		
	}
	
	@Test
	public void testeEndPointRecuperarLista() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testeEndPointRecuperarPeloId() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();
		
		ObjectMapper objectMapper = new ObjectMapper();
		UserDTO response = objectMapper.readValue(result.getResponse().getContentAsString(), UserDTO.class);
		
		assertNotNull(response);
;	}
	
	
}
