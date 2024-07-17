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

import com.convenience.services.ChumonService;

import jakarta.transaction.Transactional;

import com.convenience.models.entities.ChumonJisseki;
import com.convenience.models.viewmodels.*;

@Controller
public class ChumonController {
	@Autowired
	private ChumonService chumonService;

//	@GetMapping("/Chumonjisseki")
//	public String Chumon(Model model) {
//		List<ChumonJisseki> chumonJissekis = chumonService.getAllChumonJissekis();
//		model.addAttribute("ChumonJissekis", new ArrayList<ChumonJisseki>(chumonJissekis));
//
//		return "Chumon";
//	}

	@GetMapping("/KeyInput")
	public String KeyInput(Model model) {
		ChumonKeysViewModel chumonKeysViewModel = chumonService.SetChumonKeysViewModel();
		model.addAttribute("ChumonKeysViewModel", chumonKeysViewModel);
		return "Chumon/KeyInput";
	}

	@PostMapping("/submitForm")
	public String KeyInput(@ModelAttribute("ChumonKeysViewModel") ChumonKeysViewModel viewModel,
			BindingResult bindingResult, Model model) {
		// エラーがある場合の処理
		if (bindingResult.hasErrors()) {
			return "formPage"; // エラー表示を含んだフォームページを再表示
		}

		String postedShiireSakiId = viewModel.getShiireSakiId();
		LocalDate postedChumonDate = viewModel.getChumonDate();
		ChumonViewModel chumonViewModel = chumonService.ChumonReception(postedShiireSakiId, postedChumonDate);
		model.addAttribute("chumonViewModel", chumonViewModel);

		return "Chumon/ChumonMeisai";
	}

	@PostMapping("/ChumonMeisai")
	public String ChumonMeisai(@ModelAttribute("ChumonViewModel") ChumonViewModel viewModel,
			BindingResult bindingResult, Model model) {
		ChumonViewModel chumonViewModel;

		if (chumonService.AdjustChumonZanToUpdate(viewModel.getChumonJisseki())) {
			String postedShiireSakiId = viewModel.getChumonJisseki().getShiireSakiId();
			LocalDate postedChumonDate = viewModel.getChumonJisseki().getChumonDate();
			chumonViewModel = chumonService.ChumonReception(postedShiireSakiId, postedChumonDate);
			chumonViewModel.setIsNormal(true);
			chumonViewModel.setRemark("更新しました");
		} else {
			chumonViewModel = viewModel;
			chumonViewModel.setIsNormal(false);
			chumonViewModel.setRemark("更新できませんでした");
		}
		model.addAttribute("chumonViewModel", chumonViewModel);
		return "Chumon/ChumonMeisai";

	}
}
