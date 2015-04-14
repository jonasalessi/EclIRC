package com.github.eclirc.core;

public class UserIRC {

	private final String nick;
	private final String realName;
	private final String password;
	private final String userName;
	
	public UserIRC(String nick, String realName, String password, String userName) {
		this.nick = nick;
		this.realName = realName;
		this.password = password;
		this.userName = userName;
	}

	public String getNick() {
		return nick;
	}
	
	public String getRealName() {
		return realName;
	}

	public String getPassword() {
		return password;
	}
	
	public String getUserName() {
		return userName;
	}
}
