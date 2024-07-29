package com.convenience.controllers;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.convenience.constant.UrlConst;
import com.convenience.models.viewmodels.LoginForm;
import com.convenience.services.LoginService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * ログイン画面 Controller
 * 
 *
 */

@Controller
@RequiredArgsConstructor
public class LoginController {

	/** セッション情報 */
	private final HttpSession session;

	/**
//	 * 初期表示
	 * 
	 * @param model モデル
	 * @param form  入力情報
	 * @return 表示画面
	 */
	@GetMapping(UrlConst.LOGIN)
	public String view(Model model, LoginForm form) {
		return UrlConst.LOGIN;
	}
	
	/**
	 * ログインエラー画面表示
	 * 
	 * @param model モデル
	 * @param form  入力情報
	 * @return 表示画面
	 */
	@GetMapping(value = UrlConst.LOGIN, params = "error")
	public String viewWithError(Model model, LoginForm form) {
		var errorInfo = (Exception) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		model.addAttribute("errorMsg", errorInfo.getMessage());
		return UrlConst.LOGIN;
	}
	
	
}