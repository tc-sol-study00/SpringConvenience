package com.convenience.services;

import com.convenience.models.Chumon;
import com.convenience.models.SelectListItem;
import com.convenience.models.entities.ChumonJisseki;
import com.convenience.models.entities.ChumonJissekiMeisai;
import com.convenience.models.entities.ShiireSakiMaster;
import com.convenience.models.viewmodels.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.convenience.repositories.ChumonJissekiRepository;
import com.convenience.repositories.ShiireSakiMasterRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


/**
 *	仕入サービスクラス 
 */
@Service
public class ChumonService {

	//注文実績Jpaレポジトリ
	@Autowired
	private ChumonJissekiRepository chumonJissekiRepository;
	//仕入先マスタJpaレポジトリ
	@Autowired
	private ShiireSakiMasterRepository ShiireSakiMasterRepository;
	//注文クラス用オブジェクト変数
	@Autowired
	private Chumon chumon;

	//Spring コンテナによってエンティティマネージを作成
	//キャッシュクリアの際に使う
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * キー入力用ビューモデルのハンドリング
	 * @return	注文キー入力用ビューモデル
	 */
	public ChumonKeysViewModel SetChumonKeysViewModel() {

		/*
		 *	仕入先マスタの一覧取得と、セレクトアイテムリストの作成
		 */
		//仕入先マスタ一覧取得
		List<ShiireSakiMaster> list = ShiireSakiMasterRepository.findAllByOrderByShiireSakiId();

		//セレクトアイテムリストの作成
		List<SelectListItem> selectListItemList = new ArrayList<SelectListItem>();
		for (ShiireSakiMaster item : list) {
			SelectListItem selectlistitem = new SelectListItem();
			selectlistitem.setText(item.getShiireSakiKaisya());
			selectlistitem.setValue(item.getShiireSakiId());
			selectListItemList.add(selectlistitem);
		}

		/*
		 * キー入力用ビューモデルの初期作成
		 */
		ChumonKeysViewModel viewModel = new ChumonKeysViewModel();
		viewModel.setShiireSakiId(null);			//仕入先コード（指定なし）
		viewModel.setChumonDate(LocalDate.now());	//注文日（今日をセット）
		viewModel.setShiireSakiList(selectListItemList);	//セレクトアイテムリスト
		
		//キー入力用ビューモデルを戻す
		return viewModel;
	}

	/**
	 * 注文受付（新規注文作成・既存注文問い合わせ）
	 * @param 注文先コード	inShiireSakiId
	 * @param 注文日		inChumondate
	 * @return			注文実績明細ビューモデル
	 */
	public ChumonViewModel ChumonReception(String inShiireSakiId, LocalDate inChumondate) {

		ChumonJisseki setChumonJisseki, queriedChumonJisseki;

		//Jpaキャッシュクリア（こうしないとＤＢ更新後のデータが取り込めない
		entityManager.clear();

		/*
		 * 注文問い合わせ
		 */
		queriedChumonJisseki = chumon.ChumonToiawase(inShiireSakiId, inChumondate);

		/* 
		 * 問い合わせ結果で、データがない場合、注文作成
		 */
		if (queriedChumonJisseki == null) {
			//問い合わせ結果がない場合注文作成により新規作成
			setChumonJisseki = chumon.ChumonSakusei(inShiireSakiId, inChumondate);
		} else {
			//問い合わせ結果がある場合は注文問い合わせの結果を適用
			setChumonJisseki = queriedChumonJisseki;
		}

		/*
		 * 注文明細ビューモデルの作成
		 */
		ChumonViewModel chumonViewModel = new ChumonViewModel();
		chumonViewModel.setChumonJisseki(setChumonJisseki);

		//注文明細ビューモデルを戻り値にセット
		return chumonViewModel;

	}

	/**
	 * 注文残調整＆注文実績作成
	 * @param inchumonJisseki
	 * @return
	 */
	@Transactional
	public boolean AdjustChumonZanToUpdate(ChumonJisseki inchumonJisseki) {
		Boolean isResultNormal;

		/*
		 *	注文残調整
		 */
		
		//注文実績明細のレコード単位に注文残調整を行う
		for (ChumonJissekiMeisai chumonJissekiMeisai : inchumonJisseki.getChumonJissekiMeisais()) {
			BigDecimal chumonSu = chumonJissekiMeisai.getChumonSu();	//注文数取り出し
			BigDecimal chumonZan = chumonJissekiMeisai.getChumonZan();	//注文残取り出し
			BigDecimal lastChumonSu = chumonJissekiMeisai.getLastChumonSu();	//Post前データの取り出し
			chumonZan = chumonZan.add(chumonSu.subtract(lastChumonSu));		//注文残調整
			
			//もし調整後の注文残が0未満なら、0に強制設定
			if (chumonZan.compareTo(BigDecimal.ZERO) < 0) {
				chumonZan = BigDecimal.ZERO;
			}
			//注文実績明細プロパティの注文残にセット
			chumonJissekiMeisai.setChumonZan(chumonZan);
		}

		/*
		 * 注文ＤＢ登録
		 */
		try {
			//注文ＤＢ更新
			chumon.ChumonUpdate(inchumonJisseki);
			isResultNormal = true;	//処理結果＝処理正常
		} catch (Exception e) {
			isResultNormal = false;	//例外発生時、処理結果＝処理異常
		}
		return isResultNormal;		//処理結果の返却
	}

}
