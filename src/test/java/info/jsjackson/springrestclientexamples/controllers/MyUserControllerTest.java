/**
 * 
 */
package info.jsjackson.springrestclientexamples.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

import info.jsjackson.api.domain.Name;
import info.jsjackson.api.domain.User;
import info.jsjackson.springrestclientexamples.services.ApiService;

/**
 * @author josan
 *
 */
@Ignore
public class MyUserControllerTest {

	@Autowired
	ApplicationContext applicationContext;
	
	@Mock
	ApiService apiService;
	
	MockMvc mockMvc;
	
	UserController userController;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		userController = new UserController(apiService);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		
	}
	
	@Test
	public void testIndexPage() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andReturn();
	}
	
	@Test
	public void testFormPost() throws Exception {
		mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("limit", "3"))
		.andExpect(status().isOk())
		.andReturn();
		
	}
	
	

	@Test
	public void test1() {
	
		//Given
		List<User> users = new ArrayList<User>();
		Name name1 = new Name();
		name1.setFirst("FirstName1");
		name1.setLast("LastName1");
		name1.setTitle("Ms1");
		
		Name name2 = new Name();
		name2.setFirst("FirstName2");
		name2.setLast("LastName2");
		name2.setTitle("Ms2");

		User user1 = new User();
		user1.setName(name1);
		user1.setGender("Female");
		user1.setPhone("123456");
		
		User user2 = new User();
		user2.setName(name2);
		user2.setGender("Female");
		user2.setPhone("123456");
		
		users.add(user1);
		users.add(user2);
		
		when(apiService.getUsers(2)).thenReturn(users);
		
		//mockMvc.perform(get(""))
		
		//When
		
		
		
		//Then
		

	}

}
