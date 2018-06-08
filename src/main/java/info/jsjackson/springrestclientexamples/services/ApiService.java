/**
 * 
 */
package info.jsjackson.springrestclientexamples.services;

import java.util.List;

import org.springframework.stereotype.Service;

import info.jsjackson.api.domain.User;

/**
 * @author josan
 *
 */
public interface ApiService {

	List<User> getUsers(Integer limit);
	
}
