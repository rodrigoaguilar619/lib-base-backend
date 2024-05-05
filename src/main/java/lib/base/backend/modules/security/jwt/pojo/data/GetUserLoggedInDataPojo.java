package lib.base.backend.modules.security.jwt.pojo.data;

public class GetUserLoggedInDataPojo {

	private String userName;
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
