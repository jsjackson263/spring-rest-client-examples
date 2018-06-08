/**
 * 
 */
package info.jsjackson.springrestclientexamples.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import info.jsjackson.api.domain.User;
import info.jsjackson.api.domain.UserData;

/**
 * @author josan
 *
 */
@Service
public class ApiServiceImpl implements ApiService {

	private RestTemplate restTemplate;
	
	/**
	 * @param restTemplate
	 */
	public ApiServiceImpl(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	
	@Override
	public List<User> getUsers(Integer limit) {

		UserData userData = restTemplate.getForObject("http://apifaketory.com/api/user?limit=" + limit, UserData.class);
		
		return userData.getData();

	}

}
