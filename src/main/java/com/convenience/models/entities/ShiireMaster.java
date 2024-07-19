package com.convenience.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
/**
 * 	ＤＢ仕入マスタ用ＤＴＯ（ShiireMaster）
 */
@Data
@Entity
@Table(name = "shiire_master")
@IdClass(ShiireMasterId.class)
public class ShiireMaster {

	//仕入先コード
    @Id
    @Column(name = "shiire_saki_code", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String shiireSakiId;

    //仕入商品コード
    @Id
    @Column(name = "shiire_prd_code", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String shiirePrdId;

    //商品コード
    @Id
    @Column(name = "shohin_code", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String shohinId;

    //仕入商品名
    @Column(name = "shiire_prd_name", length = 30, nullable = false)
    @NotBlank
    @Size(max = 30)
    private String shiirePrdName;

    //仕入単位における数量
    @Column(name = "shiire_pcs_unit", nullable = false, precision = 7, scale = 2)
    @NotNull
    private BigDecimal shiirePcsPerUnit;

    //仕入単位
    @Column(name = "shiire_unit", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String shiireUnit;

    //仕入単価
    @Column(name = "shiire_tanka", nullable = false, precision = 7, scale = 2)
    @NotNull
    private BigDecimal shireTanka;

	// 親テーブル商品マスタへの結合キー
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shohin_code", insertable = false, updatable = false)
    private ShohinMaster shohinMaster;

	// 親テーブル仕入先マスタへの結合キー
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shiire_saki_code", insertable = false, updatable = false)
    private ShiireSakiMaster shiireSakiMaster;

	// 子テーブル注文実績への結合キー
    //このエンティティ削除時に、実績データの注文実績明細を消さないようにする（orphanRemoval = false）
    @OneToMany(mappedBy = "shiireMaster",cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<ChumonJissekiMeisai> chumonJissekiMeisaiis;
}
