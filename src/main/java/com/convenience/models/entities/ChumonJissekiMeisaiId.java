package com.convenience.models.entities;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

@Data
public class ChumonJissekiMeisaiId implements Serializable {

    private String chumonId;
    private String shiireSakiId;
    private String shiirePrdId;
    private String shohinId;

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
