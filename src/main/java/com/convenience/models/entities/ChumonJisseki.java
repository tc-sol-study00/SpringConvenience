package com.convenience.models.entities;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * 	ＤＢ注文実績用ＤＴＯ（ChumonJisseki）
 **/
@Data							//getter/setter記述不要とするアノ
@Entity                         //エンティティ記述
@Table(name = "chumon_jisseki") //ＤＢ上の物理テーブル名称の記述       
public class ChumonJisseki {   

	//注文コード
	@Id
	@Column(name = "chumon_code", length = 20, nullable = false)
	@NotNull
	@Size(min = 1, max = 20)
	private String chumonId;

	//注文先コード
	@Column(name = "shiire_saki_code", length = 10, nullable = false)
	private String shiireSakiId;

	//注文日
	@Column(name = "chumon_date", nullable = false)
	private LocalDate chumonDate;

	// 親テーブル注文実績への結合キー
	/*
		* insertable -> 該当する外部キー列がSQLのINSERTステートメントに含まれないことを示す
		* updatable  -> 該当する外部キー列がSQLのUPDATEステートメントに含まれないことを示す
	*/
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "shiire_saki_code", insertable = false, updatable = false)
	 private ShiireSakiMaster shiireSakiMaster;

	// 子テーブル注文実績への結合キー
	/*	CascadeType.ALL
	 * 		親エンティティに対するすべての操作（PERSIST, MERGE, REMOVE, REFRESH, DETACH）が子エンティティにも伝播されることを意味する。
	 * 		親エンティティが保存されるとき、関連するすべての子エンティティも保存され、親エンティティが削除されるとき、関連するすべての子エンティティも削除される。
	 * 	orphanRemoval
	 * 		親エンティティから子エンティティが削除された場合、子エンティティも自動的にデータベースから削除されることを意味する。
	 * 		この設定は、子エンティティが他の親エンティティに再割り当てされない限り、親エンティティから孤立している（オーファン）子エンティティを削除するのに有用
	 */
	@OneToMany(mappedBy = "chumonJisseki", cascade = CascadeType.ALL, orphanRemoval = true)
	//コレクションまたはリストの順序を指定する
	@OrderBy("shiirePrdId ASC") 
	@Valid
	private List<ChumonJissekiMeisai> chumonJissekiMeisais;

	// 排他制御用
    @Version
    @Column(name = "version")
    private long version = 0;
}
