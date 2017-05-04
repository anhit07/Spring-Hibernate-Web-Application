package com.anh.spring.web.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.anh.spring.web.validation.ValidEmail;

@Entity
// Tell hibernate this class considered a bean
@Table(name = "users")
// Tell hibernate what table matches this class
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1602739197436408559L;

	@NotBlank(message = "Username cannot be blank", 
			groups = {PersistenceValidationGroup.class, FormValidatonGroup.class })
	@Size(min = 8, max = 15, message = "Invalid Username length", 
			groups = {PersistenceValidationGroup.class, FormValidatonGroup.class })
	@Pattern(regexp = "^\\w{8,}$", message = "Invalid Username character",
			groups = {PersistenceValidationGroup.class, FormValidatonGroup.class })
	@Id// for Hibernate
	@Column(name = "username")
	private String username;

	@NotBlank(groups = { PersistenceValidationGroup.class, FormValidatonGroup.class })
	@Pattern(regexp = "^\\S+$", message = "Invalid Password character", 
			groups = {PersistenceValidationGroup.class, FormValidatonGroup.class })
	@Size(min = 8, max = 15, message = "Invalid Password length", groups = {FormValidatonGroup.class })
	private String password;

	@ValidEmail(groups = {PersistenceValidationGroup.class,FormValidatonGroup.class })
	private String email;

	private boolean enabled = false;

	private String authority;

	public User() {

	}

	public User(String username, String password, String email,
			boolean enabled, String authority) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
