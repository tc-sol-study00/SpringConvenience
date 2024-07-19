package com.convenience.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;
/**
 * 	ＤＢ仕入先マスタ用ＤＴＯ（ShiireSakiMaster）
 */
@Data
@Entity
@Table(name = "shiire_saki_master")
public class ShiireSakiMaster {

	//仕入先コード
    @Id
    @Column(name = "shiire_saki_code", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String shiireSakiId;

    //仕入先会社
    @Column(name = "shiire_saki_kaisya", length = 30, nullable = false)
    @NotBlank
    @Size(max = 30)
    private String shiireSakiKaisya;

    //仕入先部署
    @Column(name = "shiire_saki_busho", length = 30, nullable = false)
    @NotBlank
    @Size(max = 30)
    private String shiireSakiBusho;

    //郵便番号
    @Column(name = "yubin_bango", length = 30, nullable = false)
    @NotBlank
    @Size(max = 30)
    private String yubinBango;

    //都道府県
    @Column(name = "todoufuken", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String todoufuken;

    //市区町村
    @Column(name = "shikuchoson", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String shikuchoson;

    //番地
    @Column(name = "banchi", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String banchi;

    //建物名
    @Column(name = "tatemonomei", length = 10, nullable = false)
    @NotBlank
    @Size(max = 10)
    private String tatemonomei;

	//子テーブル仕入マスタへの結合キー
    @OneToMany(mappedBy = "shiireSakiMaster",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ShiireMaster> shireMasters;

    //子テーブル注文実績への結合キー　orphanRemoval = false　-> マスタ削除の際に実績データが削除されたから困るから
    @OneToMany(mappedBy = "shiireSakiMaster",cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<ChumonJisseki> chumonJissekis;
}

