package com.convenience.models.viewmodels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import com.convenience.models.SelectListItem;

import java.time.LocalDate;
import java.util.List;

public class ChumonKeysViewModel {
	
    @NotBlank(message = "仕入先コードは必須です")
    @Size(max = 10, message = "仕入先コードは最大10文字です")
    private String shiireSakiId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate chumonDate;

    private List<SelectListItem> shiireSakiList;

    // ゲッターとセッター
    public String getShiireSakiId() {
        return shiireSakiId;
    }

    public void setShiireSakiId(String shiireSakiId) {
        this.shiireSakiId = shiireSakiId;
    }

    public LocalDate getChumonDate() {
        return chumonDate;
    }

    public void setChumonDate(LocalDate chumonDate) {
        this.chumonDate = chumonDate;
    }

    public List<SelectListItem> getShiireSakiList() {
        return shiireSakiList;
    }

    public void setShiireSakiList(List<SelectListItem> shiireSakiList) {
        this.shiireSakiList = shiireSakiList;
    }
}





