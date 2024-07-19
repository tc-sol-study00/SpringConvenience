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

/**
 * 注文クラス
 */
@Component
public class Chumon {

	/**
	 * 注文実績プロパティ
	 */
	private ChumonJisseki chumonJisseki;

	/**
	 * 注文実績Getter
	 * 
	 * @return 注文実績プロパティ
	 */
	public ChumonJisseki getChumonJisseki() {
		return chumonJisseki;
	}

	/**
	 * 注文実績Setter
	 * 
	 * @param 注文実績 chumonJisseki
	 */
	public void setChumonJisseki(ChumonJisseki chumonJisseki) {
		this.chumonJisseki = chumonJisseki;
	}

	// 仕入先マスタレポジトリ
	@Autowired
	private ShiireSakiMasterRepository shiireSakiMasterRepository;

	// 仕入マスタレポジトリ
	@Autowired
	private ShiireMasterRepository shiireMasterRepository;

	// 注文実績レポジトリ
	@Autowired
	private ChumonJissekiRepository chumonJissekiRepository;

	/**
	 * 注文新規作成
	 * @param 仕入先コード	inShiireSakiId
	 * @param 注文日		inChumonDate
	 * @return			注文実績プロパティ
	 */
	public ChumonJisseki ChumonSakusei(String inShiireSakiId, LocalDate inChumonDate) throws IllegalArgumentException{

		// 引数チェック（仕入先コード有無）
		if (!shiireSakiMasterRepository.existsById(inShiireSakiId)) {
			throw new IllegalArgumentException("指定された仕入先IDが見つかりません: " + inShiireSakiId);
		}
		/*
		 * 仕入先より注文実績データ（親）を生成する(a)
		 */
		setChumonJisseki(new ChumonJisseki());
		
		/*
		 * 注文実績プロパティにセット
		 */
		getChumonJisseki().setChumonId(ChumonIdHatsuban(inChumonDate));	//注文コード発番→注文コード
		getChumonJisseki().setShiireSakiId(inShiireSakiId);				//注文先コード
		getChumonJisseki().setChumonDate(inChumonDate);					//注文日

		/*
		 * 注文実績明細データ（子）を作るために仕入マスタを読み込む(b)
		 */
		var shiireMasters = shiireMasterRepository.findByShiireSakiIdOrderByShohinId(inShiireSakiId);
		getChumonJisseki().setChumonJissekiMeisais(new ArrayList<ChumonJissekiMeisai>());

		/*
		 * (b)のデータから注文実績明細を作成する
		 */
		for (var shiire : shiireMasters) {
			
			if (shiire == null || shiire.getShohinMaster() == null)
				continue;
	
			shiire.getShohinMaster().setShiireMasters(null);	//商品マスタのリレーションに念のためnullを入れておく
			
			ChumonJissekiMeisai meisai = new ChumonJissekiMeisai();	//注文実績明細オブジェクト作成
			meisai.setChumonId(getChumonJisseki().getChumonId());	//注文コードのセット
			meisai.setShiireSakiId(getChumonJisseki().getShiireSakiId()); // 仕入先コードを注文実績からセット(aより)
			meisai.setShiirePrdId(shiire.getShiirePrdId()); // 仕入商品コードのセット(bより）
			meisai.setShohinId(shiire.getShohinId()); // 仕入マスタから商品コード（bより）
			meisai.setChumonSu(BigDecimal.ZERO); // 初期値として注文数０をセット
			meisai.setChumonZan(BigDecimal.ZERO); // 初期値として注文残０をセット
			meisai.setShiireMaster(shiire); // 仕入マスタに対するリレーション情報のセット
			meisai.setLastChumonSu(BigDecimal.valueOf(0));

			getChumonJisseki().getChumonJissekiMeisais().add(meisai);
		}

		/*
		 * 注文実績（プラス注文実績明細）を戻り値とする
		　*/
		return getChumonJisseki();
	}

	/**
	 * 注文コード発番
	 * @param	注文日	InTheDate
	 * @return	発番された注文コード
	 */
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

	/**
	 * 注文問い合わせ
	 * @param 仕入先コード	inShiireSakiId
	 * @param 注文日		inChumonDate
	 * @return	注文実績プロパティ
	 */
	public ChumonJisseki ChumonToiawase(String inShiireSakiId, LocalDate inChumonDate) {

		/*
		 *注文実績問い合わせ
		 *検索キー：仕入先コード・注文日 
		 */
		ChumonJisseki queriedchumonJisseki = chumonJissekiRepository.findByShiireSakiIdAndChumonDate(inShiireSakiId,
				inChumonDate);
		/*
		 * 上記問い合わせでデータがあれば、注文実績明細をセットする
		 * なければ、nullを返す
		　*/
		if (queriedchumonJisseki != null) {
			for (ChumonJissekiMeisai chumonJissekiMeisai : queriedchumonJisseki.getChumonJissekiMeisais()) {
				chumonJissekiMeisai.setLastChumonSu(chumonJissekiMeisai.getChumonSu());
			}
		}

		//処理後、注文実績プロパティにセット
		setChumonJisseki(queriedchumonJisseki);

		//注文実績プロパティを戻り値としてセット
		return getChumonJisseki();
	}

	/**
	 * 注文更新
	 * @param 注文実績	inchumonJisseki
	 */
	public void ChumonUpdate(ChumonJisseki inchumonJisseki) {
		/*
		 * 注文実績＋注文実績明細の更新
		 */
		chumonJissekiRepository.save(inchumonJisseki);
	}

}