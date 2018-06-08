/**
 * 
 */
package info.jsjackson.api.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author josan
 *
 */
public class UserData {

	private List<User> data = new ArrayList<>();

	/**
	 * @return the data
	 */
	public List<User> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<User> data) {
		this.data = data;
	}
}
