package com.convenience.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.convenience.repositories.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報生成
 * 
 *
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	/** ユーザー情報テーブルRepository */
	private final UserInfoRepository repository;

	/**
	 * ユーザー情報生成
	 * 
	 * @param username ログインID
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var userInfo = repository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		/*
		 * UserDetails型を返すが、User情報を拡張したLoginUserDataで返却している
		 * これにより、ログインユーザのフルネームがとれるようになった
		 */
		return new LoginUserData(userInfo.getLoginId(), userInfo.getPassword(), authorities, userInfo.getUserfullname());

		/*
		 * 以下はフルネーム管理する前のモノ
		 * return User.withUsername(userInfo.getLoginId())
		 * .password(userInfo.getPassword()) .roles("USER") .build();
		 */
	}

}