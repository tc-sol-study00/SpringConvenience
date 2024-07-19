package com.convenience.models;

import lombok.Data;

@Data	//Getter/Setter自動設定
/**
 * セレクトリストアイテム(JavaBean)
 */
public class SelectListItem {
    private String value;
    private String text;

    //コンストラクタ
    public SelectListItem() {}

    /**
     * @param select選択値	value
     * @param select表示内容	text
     */
    public SelectListItem(String value, String text) {
        this.value = value;
        this.text = text;
    }
}