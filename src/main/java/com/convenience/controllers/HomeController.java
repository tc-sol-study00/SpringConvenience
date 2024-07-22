package com.convenience.controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.convenience.services.ChumonService;

import jakarta.transaction.Transactional;

import com.convenience.models.Menu;
import com.convenience.models.entities.ChumonJisseki;
import com.convenience.models.viewmodels.*;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private ChumonService chumonService;

	@GetMapping("/")
	public String Index(Model model) {
		var menu=new Menu();
		model.addAttribute("menuItemList",menu.getMenuItemList());
		return "Home/Index";
	}
}
