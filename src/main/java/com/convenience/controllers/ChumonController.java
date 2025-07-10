package com.convenience.controllers;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.convenience.constant.UrlConst;
import com.convenience.models.viewmodels.ChumonKeysViewModel;
import com.convenience.models.viewmodels.ChumonViewModel;
import com.convenience.services.ChumonService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 注文コントローラ
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(UrlConst.CHUMONMAPPNG)
public class ChumonController {

	private final ChumonService _chumonService;

	/*
	 * @RequiredArgsConstructorを書いたから、ＤＩ注入記述不要となった public
	 * ChumonController(ChumonService chumonService) { // 注文サービスDI
	 * this._chumonService=chumonService; }
	 */
	/**
	 * キー入力初期表示
	 * 
	 * @param model
	 * @return "Chumon/KeyInput" 注文入力キー入力ビュー
	 */
	@GetMapping(UrlConst.CHUMONKEYINPUT)
	public String KeyInput(Model model) {
		/* キー入力画面初期設定 */
		ChumonKeysViewModel chumonKeysViewModel = _chumonService.SetChumonKeysViewModel();
		/* 画面に初期設定内容をバインド */
		model.addAttribute("ChumonKeysViewModel", chumonKeysViewModel);
		/* キー入力用ビューの表示 */
		return UrlConst.CHUMONKEYINPUTURL;
	}

	/**
	 * キー入力後の注文明細データ初期表示
	 * 
	 * @param viewModel     Postされたデータ
	 * @param bindingResult Post結果
	 * @param model         モデル
	 * @return 注文明細用ビュー
	 */
	@PostMapping(UrlConst.CHUMONKEYINPUT)
	public String KeyInput(@Valid @ModelAttribute("ChumonKeysViewModel") ChumonKeysViewModel viewModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		// エラーがある場合の処理
		if (bindingResult.hasErrors()) {
			ChumonKeysViewModel chumonKeysViewModel = _chumonService.SetChumonKeysViewModel();
			ChumonKeysViewModel postedviewModel = (ChumonKeysViewModel) model.getAttribute("ChumonKeysViewModel");
			postedviewModel.setShiireSakiList(chumonKeysViewModel.getShiireSakiList());
			/* 画面に初期設定内容をバインド */
			model.addAttribute("ChumonKeysViewModel", postedviewModel);

			return UrlConst.CHUMONKEYINPUTURL;
		}

		/*
		 * 注文実績データが新規なら新規に注文を作成。 すでに存在していれば注文実績データから注文を作成する
		 */
		String postedShiireSakiId = viewModel.getShiireSakiId();
		LocalDate postedChumonDate = viewModel.getChumonDate();
		ChumonViewModel chumonViewModel = _chumonService.ChumonReception(postedShiireSakiId, postedChumonDate);

		/* 注文データ表示表に注文データをセットする */
		model.addAttribute("chumonViewModel", chumonViewModel);
		/* アコーデオン表示の対応 */
		model.addAttribute("handlingFlg", "FirstDisplay");
		/* 注文明細ビューの表示 */
		return UrlConst.CHUMONMEISAIURL;
	}

	/**
	 * 注文明細データの受信
	 * 
	 * @param viewModel     注文明細用ビューモデル
	 * @param bindingResult Ｐｏｓｔ結果
	 * @param model         モデル
	 * @return 注文明細用ビュー
	 */
	@PostMapping(UrlConst.CHUMONMEISAI)
	// 下の@ModelAttribute("chumonViewModel")は、ThymeleadのFormのth:objectの内容とあわせないと、エラーが表示されない
	public String ChumonMeisai(@ModelAttribute("chumonViewModel") @Valid ChumonViewModel viewModel,
			BindingResult bindingResult, Model model) {
		ChumonViewModel chumonViewModel;
		boolean isNormal;

		/*
		 * Ｐｏｓｔデータにエラーがあるか
		 */
		if (bindingResult.hasErrors()) {

			/* コンソールに表示する */
			bindingResult.getAllErrors().forEach(error -> {
				if (error instanceof FieldError) {
					FieldError fieldError = (FieldError) error;
					String fieldName = fieldError.getField(); // フィールド名
					String errorMessage = error.getDefaultMessage(); // エラーメッセージ
					System.out.println("Field: " + fieldName + ", Error: " + errorMessage);
				} else if (error instanceof ObjectError) {
					// ObjectError の場合も対応
					String objectName = error.getObjectName();
					String errorMessage = error.getDefaultMessage();
					System.out.println("Object: " + objectName + ", Error: " + errorMessage);
				}
			});

			/* 注文明細画面を再表示・エラー表示をする */
			viewModel.setRemark("");
			model.addAttribute("chumonViewModel", viewModel);
			model.addAttribute("handlingFlg", "SecondDisplay");

			return UrlConst.CHUMONMEISAIURL; // バリデーションエラーがある場合、フォームページに戻る
		}

		try {
			/* 注文残の調整と注文実績の登録（ＤＢ更新） */
			isNormal = _chumonService.AdjustChumonZanToUpdate(viewModel.getChumonJisseki());
		} catch (Exception e) {
			/* DB更新した場合にエラー→ロールバック例外発生したら */
			isNormal = false;
		}
		if (isNormal) {
			/* 注文登録が正常 */
			String postedShiireSakiId = viewModel.getChumonJisseki().getShiireSakiId();
			LocalDate postedChumonDate = viewModel.getChumonJisseki().getChumonDate();
			chumonViewModel = _chumonService.ChumonReception(postedShiireSakiId, postedChumonDate);
			chumonViewModel.setIsNormal(true);
			chumonViewModel.setRemark("更新しました");
		} else {
			/* 注文残登録以上 */
			chumonViewModel = viewModel;
			chumonViewModel.setIsNormal(false);
			chumonViewModel.setRemark("更新できませんでした");
		}
		/* 注文明細ビューのバインド */
		model.addAttribute("chumonViewModel", chumonViewModel);
		/* アコーデオン対応 */
		model.addAttribute("handlingFlg", "SecondDisplay");
		/* 注文明細ビューの表示 */
		return UrlConst.CHUMONMEISAIURL;
	}
}
