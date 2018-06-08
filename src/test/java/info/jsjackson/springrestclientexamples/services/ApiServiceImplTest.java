/**
 * 
 */
package info.jsjackson.springrestclientexamples.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import info.jsjackson.api.domain.User;

/**
 * @author josan
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiServiceImplTest {

	@Autowired
	ApiService apiService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetUsers() {

		//Given
		
		//When
		List<User> users = apiService.getUsers(3);
		
		//Then
		assertEquals(4, users.size());
		
		
	
	}

}
