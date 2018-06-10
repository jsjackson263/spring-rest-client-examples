/**
 * 
 */
package info.jsjackson.springrestclientexamples.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import info.jsjackson.api.domain.User;
import info.jsjackson.api.domain.UserData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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


	//the Reactive way
	@Override
	public Flux<User> getUsers(Mono<Integer> limit) {

		return WebClient
				.create(api_url)
				.get()
				.uri(uriBuilder -> uriBuilder.queryParam("limit", limit.block()).build())
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMap(resp -> resp.bodyToMono(UserData.class))  //equiv to ln 49
				.flatMapIterable(UserData::getData);   //equiv to ln 51
				
	}

}
