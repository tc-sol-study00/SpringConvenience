package com.convenience.models.viewmodels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import com.convenience.models.SelectListItem;

import java.time.LocalDate;
import java.util.List;

/**
 *	注文キー入力用Ｖｉｅｗモデル(ChumonKeysViewModel)
 */
@Data	//Getter Setter自動セット
public class ChumonKeysViewModel {

	//仕入先コード
    @NotBlank(message = "仕入先コードは必須です")
    @Size(max = 10, message = "仕入先コードは最大10文字です")
    private String shiireSakiId;

    //注文日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate chumonDate;

    //仕入先セレクトリスト
    private List<SelectListItem> shiireSakiList;
}