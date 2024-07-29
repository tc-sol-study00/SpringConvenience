package com.convenience.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.convenience.constant.UrlConst;
import com.convenience.models.Menu;

/**
 * メニュー表クラス
 */
@Controller
@RequestMapping(UrlConst.HOMEMAPPNG)
public class HomeController {

	/* メニュークラス用 */
	private final Menu menu;

	/* メニュークラスＤＩ注入 */
	HomeController(Menu menu) {
		this.menu = menu;
	}

	/* メニュー表示 */
	@GetMapping(UrlConst.HOMEINDEX)
	public String Index(Model model) {
		model.addAttribute("menuItemList", menu.getMenuItemList());
		return UrlConst.HOMEINDEXURL;
	}
}
