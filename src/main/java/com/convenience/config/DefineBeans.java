package com.convenience.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Bean定義クラス
 * 
 *
 */
@Configuration
public class DefineBeans {

	/**
	 * パスワードエンコーダーBean定義
	 * 
	 * @return パスワードエンコーダー(BCrypt形式)
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}