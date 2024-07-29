package com.convenience.models;

import java.time.LocalDate;

import com.convenience.models.entities.ChumonJisseki;

/**
 * 注文インターフェース
 */
public interface Chumon {
	
	/**
	 * 注文実績Getter
	 * 
	 * @return 注文実績プロパティ
	 */
	public ChumonJisseki getChumonJisseki();

	/**
	 * 注文実績Setter
	 * 
	 * @param 注文実績 chumonJisseki
	 */
	public void setChumonJisseki(ChumonJisseki inChumonJisseki);

	/**
	 * 注文新規作成
	 * 
	 * @param 仕入先コード inShiireSakiId
	 * @param 注文日    inChumonDate
	 * @return 注文実績プロパティ
	 */
	public ChumonJisseki ChumonSakusei(String inShiireSakiId, LocalDate inChumonDate) throws IllegalArgumentException;
	
	/**
	 * 注文問い合わせ
	 * 
	 * @param 仕入先コード inShiireSakiId
	 * @param 注文日    inChumonDate
	 * @return 注文実績プロパティ
	 */
	public ChumonJisseki ChumonToiawase(String inShiireSakiId, LocalDate inChumonDate);

	/**
	 * 注文更新
	 * 
	 * @param 注文実績 inchumonJisseki
	 */
	public void ChumonUpdate(ChumonJisseki inchumonJisseki);
}