package com.convenience.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.convenience.models.Menu;
import com.convenience.services.ChumonService;

import com.convenience.constant.*;

@Controller
@RequestMapping(UrlConst.HOMEMAPPNG)
public class HomeController {
	@Autowired
	private ChumonService chumonService;

	@GetMapping(UrlConst.HOMEINDEX)
	public String Index(Model model) {
		var menu=new Menu();
		model.addAttribute("menuItemList",menu.getMenuItemList());
		return UrlConst.HOMEINDEXURL;
	}
}
