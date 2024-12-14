package lib.base.backend.modules.security.jwt.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "config_auth")
public class ConfigAuthEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_user")
	private Integer idUser;
	
	@Column(name="token")
	private String token;
	
	@Column(name = "date_login")
	private LocalDateTime dateLogin;
	
	@Column(name = "date_refresh")
	private LocalDateTime dateRefresh;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", insertable = false, updatable = false)
	private UserEntity userEntity;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public LocalDateTime getDateLogin() {
		return dateLogin;
	}

	public void setDateLogin(LocalDateTime dateLogin) {
		this.dateLogin = dateLogin;
	}

	public LocalDateTime getDateRefresh() {
		return dateRefresh;
	}

	public void setDateRefresh(LocalDateTime dateRefresh) {
		this.dateRefresh = dateRefresh;
	}
}
