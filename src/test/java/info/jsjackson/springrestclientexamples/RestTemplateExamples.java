/**
 * 
 */
package info.jsjackson.springrestclientexamples;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author josan
 *
 */
public class RestTemplateExamples {
	
	public static final String API_ROOT = "https://api.predic8.de/shop";

	
	@Test
	public void getCategories() throws Exception {
		String api_url = API_ROOT + "/categories/";
		
		RestTemplate restTemplate = new RestTemplate();
		
		JsonNode jsonNode = restTemplate.getForObject(api_url, JsonNode.class);
		
		System.out.println("\nCategories Response:");
		System.out.println(jsonNode.toString());
	}
	
	@Test
	public void getCustomers() throws Exception {
		String api_url = API_ROOT + "/customers/";
		
		RestTemplate restTemplate = new RestTemplate();
		
		JsonNode jsonNode = restTemplate.getForObject(api_url, JsonNode.class);
		
		System.out.println("\nCustomers Response:");
		System.out.println(jsonNode.toString());
	}
	
	@Test
	public void createCustomer() throws Exception {
		String api_url = API_ROOT + "/customers/";
		
		//Java object to parse to Json
		Map<String, Object> postMap = new HashMap<>();
		postMap.put("firstname", "Joe");
		postMap.put("lastname", "Blog");
		
		RestTemplate restTemplate = new RestTemplate();
		
		JsonNode jsonNode = restTemplate.postForObject(api_url, postMap, JsonNode.class);
		
		System.out.println("\nCustomer Post Response:");
		System.out.println(jsonNode.toString());
	}
	
	@Test
	public void getCustomerById() throws Exception {
		String api_url = API_ROOT + "/customers/343";  //Joe Blog
		
		RestTemplate restTemplate = new RestTemplate();
		
		JsonNode jsonNode = restTemplate.getForObject(api_url, JsonNode.class);
		
		System.out.println("\nCustomer ID Response:");
		System.out.println(jsonNode.toString());
	}
	
	@Test
	public void updateCustomer() throws Exception {
		String api_url = API_ROOT + "/customers/";
		
		//Java object to parse to Json
		Map<String, Object> postMap = new HashMap<>();
		postMap.put("firstname", "Michael");
		postMap.put("lastname", "Weston");
		
		RestTemplate restTemplate = new RestTemplate();
		
		JsonNode jsonNode = restTemplate.postForObject(api_url, postMap, JsonNode.class);
		
		System.out.println("\nUpdate Customer Response:");
		System.out.println(jsonNode.toString());
		
		String curomerUrl = jsonNode.get("customer_url").textValue();
		String id = curomerUrl.split("/")[3];
		
		System.out.println("\nCustomer Id to Update: " + id + " to this url: " + api_url + id);
		
		postMap.put("firstname", "Michael 2");
		postMap.put("lastname", "Weston 2");
		restTemplate.put(api_url + id, postMap);
		
		JsonNode jsonUpdateNode = restTemplate.getForObject(api_url + id, JsonNode.class);
		
		System.out.println("\nUpdated Customer Id: " + id);
		System.out.println(jsonUpdateNode.toString());
		
	}
	
	
	 @Test(expected = ResourceAccessException.class)
	    public void updateCustomerUsingPatchSunHttp() throws Exception {

	        //create customer to update
	        String apiUrl = API_ROOT + "/customers/";

	        RestTemplate restTemplate = new RestTemplate();

	        //Java object to parse to JSON
	        Map<String, Object> postMap = new HashMap<>();
	        postMap.put("firstname", "Sam");
	        postMap.put("lastname", "Axe");

	        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);

	        System.out.println("\nPatch SunHttp Response:");
	        System.out.println(jsonNode.toString());

	        String customerUrl = jsonNode.get("customer_url").textValue();

	        String id = customerUrl.split("/")[3];

	        System.out.println("Created Patch SunHttp customer id: " + id);

	        postMap.put("firstname", "Sam 2");
	        postMap.put("lastname", "Axe 2");

	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(postMap, headers);

	        //fails due to sun.net.www.protocol.http.HttpURLConnection not supporting patch
	        JsonNode updatedNode = restTemplate.patchForObject(apiUrl + id, entity, JsonNode.class);

	        System.out.println(updatedNode.toString());

	    }
	 
	 @Test
	    public void updateCustomerUsingPatch() throws Exception {

	        //create customer to update
	        String apiUrl = API_ROOT + "/customers/";

	        // Use Apache HTTP client factory
	        //see: https://github.com/spring-cloud/spring-cloud-netflix/issues/1777
	        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	        RestTemplate restTemplate = new RestTemplate(requestFactory);

	        //Java object to parse to JSON
	        Map<String, Object> postMap = new HashMap<>();
	        postMap.put("firstname", "Sam");
	        postMap.put("lastname", "Axe");

	        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);

	        System.out.println("Response Using Patch:");
	        System.out.println(jsonNode.toString());

	        String customerUrl = jsonNode.get("customer_url").textValue();

	        String id = customerUrl.split("/")[3];

	        System.out.println("Created customer id (Using Patch): " + id);

	        postMap.put("firstname", "Sam 2");
	        postMap.put("lastname", "Axe 2");

	        //example of setting headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(postMap, headers);

	        JsonNode updatedNode = restTemplate.patchForObject(apiUrl + id, entity, JsonNode.class);

	        System.out.println(updatedNode.toString());
	    }

	 @Test(expected = HttpClientErrorException.class)
		public void deleteCustomerById() throws Exception {
			
		 	//create customer to update
		 	String api_url = API_ROOT + "/customers/";
		 
		 	RestTemplate restTemplate = new RestTemplate();
			
		 	//Java object to parse to JSON
		 	Map<String, Object> postMap = new HashMap<>();
	       postMap.put("firstname", "Les");
	       postMap.put("lastname", "Claypool");
	        
	        JsonNode jsonNode = restTemplate.postForObject(api_url, postMap, JsonNode.class);
			
			System.out.println("\nDelete Response:");
			System.out.println(jsonNode.toString());
			
			String curomerUrl = jsonNode.get("customer_url").textValue();
			String id = curomerUrl.split("/")[3];
			
			System.out.println("\nCustomer Id to Delete: " + id + " to this url: " + api_url + id);
			
			restTemplate.delete(api_url + id);  //expects 200 status
			
			System.out.println("\nDeleted Customer Id: " + id);
			
			//should go boom on 404
			restTemplate.getForObject(api_url + id, JsonNode.class);
			
			
		}
	
}
