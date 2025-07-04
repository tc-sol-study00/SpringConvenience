package com.convenience.services;

import java.time.LocalDate;

import com.convenience.models.entities.ChumonJisseki;
import com.convenience.models.viewmodels.ChumonKeysViewModel;
import com.convenience.models.viewmodels.ChumonViewModel;

import jakarta.transaction.Transactional;
/**
 * 仕入サービスインターフェース
 */
public interface ChumonService {

	/**
	 * キー入力用ビューモデルのハンドリング
	 * 
	 * @return 注文キー入力用ビューモデル
	 */
	public ChumonKeysViewModel SetChumonKeysViewModel();

	/**
	 * 注文受付（新規注文作成・既存注文問い合わせ）
	 * 
	 * @param 注文先コード inShiireSakiId
	 * @param 注文日    inChumondate
	 * @return 注文実績明細ビューモデル
	 */
	public ChumonViewModel ChumonReception(String inShiireSakiId, LocalDate inChumondate);

	/**
	 * 注文残調整＆注文実績作成
	 * 
	 * @param inchumonJisseki
	 * @return
	 */
	@Transactional
	public boolean AdjustChumonZanToUpdate(ChumonJisseki inchumonJisseki);
}
