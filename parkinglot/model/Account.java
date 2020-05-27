package parkinglot.model;

import parkinglot.enums.AccountStatus;

public abstract class Account {

	private String username;
	private String password;
	private AccountStatus status;

	public Account(AccountStatus status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

}
