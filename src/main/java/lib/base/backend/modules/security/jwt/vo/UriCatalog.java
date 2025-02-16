package lib.base.backend.modules.security.jwt.vo;

public class UriCatalog {
	
	private UriCatalog() {}

	public static final String AUTH_LOGIN = "/auth/login";
	public static final String AUTH_LOGOUT = "/auth/logout";
	public static final String AUTH_REFRESH = "/auth/refresh";
	public static final String AUTH_VALIDATE_SESSION = "/auth/validateSession";
	public static final String API_USER_DATA_GET = "/api/user/getUserData";
	
}
