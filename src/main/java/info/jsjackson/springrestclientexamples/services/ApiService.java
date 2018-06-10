/**
 * 
 */
package info.jsjackson.springrestclientexamples.services;

import java.util.List;

import org.springframework.stereotype.Service;

import info.jsjackson.api.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author josan
 *
 */
public interface ApiService {

	List<User> getUsers(Integer limit);
	
	Flux<User> getUsers(Mono<Integer> limit);
}
