package com.convenience.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.convenience.models.ChumonJisseki;
import com.convenience.services.ChumonService;

@Controller
public class ChumonController {
    @Autowired
    private ChumonService chumonService;
	@GetMapping("/Chumonjisseki")
	public String Chumon(Model model) {
		List<ChumonJisseki> chumonJissekis = chumonService.getAllChumonJissekis();
		model.addAttribute("ChumonJissekis", new ArrayList<ChumonJisseki>(chumonJissekis));
		return "Chumon";
	}
}
