package com.convenience.models.viewmodels;

import com.convenience.models.entities.ChumonJisseki;

import jakarta.validation.Valid;
import lombok.Data;

/**
 * 	注文実績＋明細表示・入力用Ｖｉｅｗモデル(ChumonViewModel)
 */
@Data	//getter setter省略
public class ChumonViewModel {
	@Valid
	private ChumonJisseki chumonJisseki;	//注文実績
	private Boolean isNormal = true;		//処理正常・異常
	private String remark = "";				//表示メッセージ
}
