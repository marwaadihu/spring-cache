package anil.agrawal.spring.cache.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author anil.agrawal
 *
 */
@Entity
@Table(name = "tbl_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String username;

	@NotNull
	@NotEmpty
	@Column
	private String password;

	@NotNull
	@Column
	private boolean isEnabled;

	@NotNull
	@Column
	private boolean isUserAccountExpired;

	@NotNull
	@Column
	private boolean isUserPasswordExpired;

	@NotNull
	@Column
	private boolean isUserAccountLocked;

	@NotNull
	@Column
	private String authorities;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the isEnabled
	 */
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * @param isEnabled the isEnabled to set
	 */
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * @return the isUserAccountExpired
	 */
	public boolean isUserAccountExpired() {
		return isUserAccountExpired;
	}

	/**
	 * @param isUserAccountExpired the isUserAccountExpired to set
	 */
	public void setUserAccountExpired(boolean isUserAccountExpired) {
		this.isUserAccountExpired = isUserAccountExpired;
	}

	/**
	 * @return the isUserPasswordExpired
	 */
	public boolean isUserPasswordExpired() {
		return isUserPasswordExpired;
	}

	/**
	 * @param isUserPasswordExpired the isUserPasswordExpired to set
	 */
	public void setUserPasswordExpired(boolean isUserPasswordExpired) {
		this.isUserPasswordExpired = isUserPasswordExpired;
	}

	/**
	 * @return the isUserAccountLocked
	 */
	public boolean isUserAccountLocked() {
		return isUserAccountLocked;
	}

	/**
	 * @param isUserAccountLocked the isUserAccountLocked to set
	 */
	public void setUserAccountLocked(boolean isUserAccountLocked) {
		this.isUserAccountLocked = isUserAccountLocked;
	}

	/**
	 * @return the authorities
	 */
	public String getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

}
