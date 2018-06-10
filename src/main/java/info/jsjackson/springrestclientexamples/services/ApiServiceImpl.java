/**
 * 
 */
package info.jsjackson.springrestclientexamples.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import info.jsjackson.api.domain.User;
import info.jsjackson.api.domain.UserData;

/**
 * @author josan
 *
 */
@Service
public class ApiServiceImpl implements ApiService {

	private RestTemplate restTemplate;
	
	private final String api_url;
	
	/**
	 * @param restTemplate
	 */
	public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String api_url) {
		this.restTemplate = restTemplate;
		this.api_url = api_url;
	}

	
	@Override
	public List<User> getUsers(Integer limit) {

		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromUriString(api_url)
				.queryParam("limit", limit);
		
		/*UserData userData = restTemplate.getForObject("http://apifaketory.com/api/user?limit=" + limit, UserData.class);*/
		
		UserData userData = restTemplate.getForObject(uriBuilder.toUriString(), UserData.class);
		
		return userData.getData();

	}

}
