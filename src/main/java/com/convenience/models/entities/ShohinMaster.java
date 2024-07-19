package com.convenience.models.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

/**
 * 	ＤＢ商品マスタ用ＤＴＯ（ShohinMaster）
 */
@Data
@Entity
@Table(name = "shohin_master")
public class ShohinMaster {
	//商品コード
    @Id
    @Column(name = "shohin_code", length = 10, nullable = false)
    private String shohinId;

    //商品名称
    @Column(name = "shohin_name", length = 50, nullable = false)
    @NotBlank
    @Size(max = 50)
    private String shohinName;

    //消費税率
    @Column(name = "shohi_zeiritsu", nullable = false, precision = 15, scale = 2)
    @NotNull
    private BigDecimal shohiZeiritsu;

    //消費税率（外食）
    @Column(name = "shohi_zeiritsu_gaisyoku", nullable = false, precision = 15, scale = 2)
    @NotNull
    private BigDecimal shohiZeiritsuGaishoku;
	// 子テーブル仕入マスタへの結合キー
    //このエンティティ削除時に、仕入マスタが削除されないようにする（orphanRemoval = false）
    @OneToMany(mappedBy = "shohinMaster",cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<ShiireMaster> shiireMasters;
}
