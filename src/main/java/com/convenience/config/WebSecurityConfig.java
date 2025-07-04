package com.convenience.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.convenience.constant.UrlConst;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

	/** ユーザー名のname属性 */
	private final String USERNAME_PARAMETER = "loginId";

	/**
	 * Spring Securityカスタマイズ用
	 * 
	 * @param http カスタマイズパラメータ
	 * @return カスタマイズ結果
	 * @throws Exception 予期せぬ例外
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
				authorize -> authorize.requestMatchers(HttpMethod.POST, "/UserInfo/**", "Shared/**").permitAll() // POSTリクエストを許可
						.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll().anyRequest().authenticated())
				.csrf((csrf) -> csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository()))
				.formLogin(login -> login.loginPage(UrlConst.LOGIN) // 自作ログイン画面(Controller)を使うための設定
						.usernameParameter(USERNAME_PARAMETER) // ユーザ名パラメータのname属性
						.defaultSuccessUrl(UrlConst.MENU,true)) // ログイン成功後のリダイレクトURL
				.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher(UrlConst.LOGOUT))
						.logoutSuccessUrl(UrlConst.LOGIN));
		return http.build();
	}
}