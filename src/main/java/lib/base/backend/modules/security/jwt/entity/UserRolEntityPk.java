package lib.base.backend.modules.security.jwt.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserRolEntityPk implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="id_user")
	private int idIssue;

	@Column(name="id_rol")
	private int idRol;

	public UserRolEntityPk() {
		super();
	}

	public UserRolEntityPk(int idIssue, int idRol) {
		super();
		this.idIssue = idIssue;
		this.idRol = idRol;
	}
	
	public int getIdIssue() {
		return idIssue;
	}
	public void setIdIssue(int idIssue) {
		this.idIssue = idIssue;
	}
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
}