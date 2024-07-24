package com.convenience.models.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * ＤＢ注文実績明細用ＤＴＯ（ChumonJissekiMeisai）
 */
@Data // getter/setter記述不要とするアノテーション
@Entity // エンティティ記述
@Table(name = "chumon_jisseki_meisai") // ＤＢ上の物理テーブル名称の記述
@IdClass(ChumonJissekiMeisaiId.class) // 複合主キーの場合は別クラスで定義が必要
public class ChumonJissekiMeisai {
	//注文コード
	@Id
	@Column(name = "chumon_code")
	@NotEmpty
	@Size(min = 1, max = 20)
	private String chumonId;

	//仕入先コード
	@Id
	@Column(name = "shiire_saki_code")
	@NotEmpty
	@Size(min = 1, max = 10)
	private String shiireSakiId;

	//仕入商品コード
	@Id
	@Column(name = "shiire_prd_code")
	@NotEmpty
	@Size(min = 1, max = 10)
	private String shiirePrdId;

	//商品コード
	@Id
	@Column(name = "shohin_code")
	@NotEmpty
	@Size(min = 1, max = 10)
	private String shohinId;

	//注文数
	@Column(name = "chumon_su")
	@NotNull( message = "入力必須項目です")
	@PositiveOrZero(message = "数値は0以上を入力してください")
	@DecimalMax(value="999.99",message = "入力は0～999.99の範囲で入力してください")
	@Digits(integer = 3, fraction = 2, message = "整数部は最大3桁、小数部は最大2桁でなければなりません")
	private BigDecimal chumonSu;

	//注文残
	@Column(name = "chumon_zan")
	private BigDecimal chumonZan;

	// 以下の項目はＤＢとマッピングしない
	@Transient
	private BigDecimal lastChumonSu;

	// 親テーブル注文実績への結合キー
	/*
	 * FetchType.LAZY
		 * 関連エンティティが最初にアクセスされたときにデータベースからロードされる遅延ロード（レイジーロード）を意味する。
		 * これにより、エンティティの初期ロード時に関連エンティティのデータをフェッチせず、必要になったときにのみデータをフェッチすることで、
		 * パフォーマンスの最適化が図られる。
	 * FetchType.EAGER
		 * 関連エンティティがソースエンティティと同時に即座にロードされることを意味する。
		 * エンティティがデータベースからフェッチされるとき、関連するすべてのエンティティも一緒にフェッチされる。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chumon_code", referencedColumnName = "chumon_code", insertable = false, updatable = false)
	private ChumonJisseki chumonJisseki;

	// 親テーブル仕入マスターへの結合キー
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "shiire_saki_code", referencedColumnName = "shiire_saki_code", insertable = false, updatable = false),
			@JoinColumn(name = "shiire_prd_code", referencedColumnName = "shiire_prd_code", insertable = false, updatable = false),
			@JoinColumn(name = "shohin_code", referencedColumnName = "shohin_code", insertable = false, updatable = false) })
	private ShiireMaster shiireMaster;

	// 排他制御用
	@Version
	@Column(name = "version")
	private long version = 0;

}
