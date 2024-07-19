package com.convenience.models.entities;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;
/**
 * 	ＤＢ注文実績明細用主キー定義（ChumonJissekiMeisaiId）
 */
@Data
public class ChumonJissekiMeisaiId implements Serializable {

	/*
	 * 主キー項目
	 */	
    private String chumonId;		//注文コード
    private String shiireSakiId;	//仕入先コード
    private String shiirePrdId;		//仕入商品コード
    private String shohinId;		//商品コード

    // equals() メソッドの実装
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChumonJissekiMeisaiId that = (ChumonJissekiMeisaiId) o;
        return Objects.equals(chumonId, that.chumonId) &&
                Objects.equals(shiireSakiId, that.shiireSakiId) &&
                Objects.equals(shiirePrdId, that.shiirePrdId) &&
                Objects.equals(shohinId, that.shohinId);
    }

    // hashCode() メソッドの実装
    @Override
    public int hashCode() {
        return Objects.hash(chumonId, shiireSakiId, shiirePrdId, shohinId);
    }
}
