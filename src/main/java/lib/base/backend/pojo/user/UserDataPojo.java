package lib.base.backend.pojo.user;

import java.util.ArrayList;
import java.util.List;

public class UserDataPojo {

	private String userName;
	
	private List<String> userRols = new ArrayList<String>();
	
	public UserDataPojo(String userName, List<String> userRols) {
		super();
		this.userName = userName;
		this.userRols = userRols;
	}
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public List<String> getUserRols() {
		return userRols;
	}
	
	public void setUserRols(List<String> userRols) {
		this.userRols = userRols;
	}
}
