package com.convenience.models.viewmodels;

import com.convenience.models.entities.ChumonJisseki;

import lombok.Data;

@Data
public class ChumonViewModel {
	private ChumonJisseki chumonJisseki;
	private Boolean isNormal;
	private String remark = "";
}
