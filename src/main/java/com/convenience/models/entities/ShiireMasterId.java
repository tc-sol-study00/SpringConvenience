package com.convenience.models.entities;

import java.io.Serializable;
import java.util.Objects;
/**
 * 	ＤＢ仕入マスタ用主キー定義（ShiireMasterId）
 */
public class ShiireMasterId implements Serializable {
	/*
	 * 主キー項目
	 */	
    private String shiireSakiId;	//仕入先コード
    private String shiirePrdId;		//仕入商品コード
    private String shohinId;		//商品コード

    public ShiireMasterId() {
    }

    public ShiireMasterId(String shiireSakiId, String shiirePrdId, String shohinId) {
        this.shiireSakiId = shiireSakiId;
        this.shiirePrdId = shiirePrdId;
        this.shohinId = shohinId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShiireMasterId that = (ShiireMasterId) o;
        return Objects.equals(shiireSakiId, that.shiireSakiId) &&
               Objects.equals(shiirePrdId, that.shiirePrdId) &&
               Objects.equals(shohinId, that.shohinId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shiireSakiId, shiirePrdId, shohinId);
    }
}
