package com.convenience.models;

public class SelectListItem {
    private String value;
    private String text;
    
    public SelectListItem() {}

    public SelectListItem(String value, String text) {
        this.value = value;
        this.text = text;
    }

    // ゲッターとセッター
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}