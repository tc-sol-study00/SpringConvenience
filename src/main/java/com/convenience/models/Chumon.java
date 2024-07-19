package com.convenience.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Sort;

import com.convenience.models.entities.*;
import com.convenience.repositories.ChumonJissekiRepository;
import com.convenience.repositories.ShiireMasterRepository;
import com.convenience.repositories.ShiireSakiMasterRepository;

@Component
public class Chumon {
	private ChumonJisseki chumonJisseki;

	public ChumonJisseki getChumonJisseki() {
		return chumonJisseki;
	}

	public void setChumonJisseki(ChumonJisseki chumonJisseki) {
		this.chumonJisseki = chumonJisseki;
	}

	@Autowired
	private ShiireSakiMasterRepository shiireSakiMasterRepository;

	@Autowired
	private ShiireMasterRepository shiireMasterRepository;

	@Autowired
	private ChumonJissekiRepository chumonJissekiRepository;

	public ChumonJisseki ChumonSakusei(String inShiireSakiId, LocalDate inChumonDate) {

		// 引数チェック（仕入先コード有無）
		if (!shiireSakiMasterRepository.existsById(inShiireSakiId)) {

		}
		// 仕入先より注文実績データ（親）を生成する(a)

		setChumonJisseki(new ChumonJisseki());

		getChumonJisseki().setChumonId(ChumonIdHatsuban(inChumonDate));
		getChumonJisseki().setShiireSakiId(inShiireSakiId);
		getChumonJisseki().setChumonDate(inChumonDate);

		// 注文実績明細データ（子）を作るために仕入マスタを読み込む(b)

		var shiireMasters = shiireMasterRepository.findByShiireSakiIdOrderByShohinId(inShiireSakiId);

		getChumonJisseki().setChumonJissekiMeisais(new ArrayList<ChumonJissekiMeisai>());

		// (b)のデータから注文実績明細を作成する
		for (var shiire : shiireMasters) {

			if (shiire == null || shiire.getShohinMaster() == null)
				continue;

			shiire.getShohinMaster().setShiireMasters(null);

			ChumonJissekiMeisai meisai = new ChumonJissekiMeisai();
			meisai.setChumonId(getChumonJisseki().getChumonId());
			meisai.setShiireSakiId(getChumonJisseki().getShiireSakiId()); // 仕入先コードを注文実績からセット(aより)
			meisai.setShiirePrdId(shiire.getShiirePrdId()); // 仕入商品コードのセット(bより）
			meisai.setShohinId(shiire.getShohinId()); // 仕入マスタから商品コード（bより）
			meisai.setChumonSu(BigDecimal.ZERO); // 初期値として注文数０をセット
			meisai.setChumonZan(BigDecimal.ZERO); // 初期値として注文残０をセット
			meisai.setShiireMaster(shiire); // 仕入マスタに対するリレーション情報のセット
			meisai.setLastChumonSu(BigDecimal.valueOf(0));

			getChumonJisseki().getChumonJissekiMeisais().add(meisai);
		}

		// 注文実績（プラス注文実績明細）を戻り値とする
		return getChumonJisseki();
	}

	private String ChumonIdHatsuban(LocalDate InTheDate) {
		int seqNumber;
		String dateArea;
		// 今日の日付
		dateArea = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		// 今日の日付からすでに今日の分の注文コードがないか調べる
		String chumonId = chumonJissekiRepository.findLatestChumonId(dateArea);

		// 上記以外の場合、 //注文コードの右３桁の数値を求め＋１にする
		seqNumber = (chumonId == null || chumonId.isEmpty()) ? 1 : Integer.parseInt(chumonId.substring(9, 12)) + 1;

		//// ３桁の数値が999以内（ＯＫ） それを超過するとnull
		return seqNumber <= 999 ? String.format("%s-%03d", dateArea, seqNumber) : null;

	}

	public ChumonJisseki ChumonToiawase(String inShiireSakiId, LocalDate inChumonDate) {

		ChumonJisseki queriedchumonJisseki = chumonJissekiRepository.findByShiireSakiIdAndChumonDate(inShiireSakiId,
				inChumonDate);

		if (queriedchumonJisseki != null) {
			for (ChumonJissekiMeisai chumonJissekiMeisai : queriedchumonJisseki.getChumonJissekiMeisais()) {
				chumonJissekiMeisai.setLastChumonSu(chumonJissekiMeisai.getChumonSu());
			}
		}

		setChumonJisseki(queriedchumonJisseki);

		return getChumonJisseki();
	}

	public void ChumonUpdate(ChumonJisseki inchumonJisseki) {
		chumonJissekiRepository.save(inchumonJisseki);
	}

}
