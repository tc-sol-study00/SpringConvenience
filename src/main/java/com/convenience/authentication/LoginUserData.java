package com.convenience.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.util.Assert;

/**
 * User情報を拡張
 * ユーザフル名称を管理
 */
public class LoginUserData extends User {

	private final String userfullname;

	public LoginUserData(String username, String password, Collection<? extends GrantedAuthority> authorities, String userfullname) {
			super(username, password, true, true, true, true, authorities);
			this.userfullname=userfullname;
		}

	public String getUserfullname() {
		return userfullname;
	}

}
