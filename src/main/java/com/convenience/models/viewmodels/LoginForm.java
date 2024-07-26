package com.convenience.models.viewmodels;

import lombok.Data;

/**
 * ログイン画面Formクラス
 * 
 *
 */
@Data
public class LoginForm {

	/** ログインID */
	private String loginId;

	/** パスワード */
	private String password;
}
