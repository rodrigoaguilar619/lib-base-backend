package lib.base.backend.pojo.rest.security;

public class LoginRequest extends UserRequestPojo {

	private String pwd;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
