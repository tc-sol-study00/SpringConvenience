package com.convenience.models;

import java.util.ArrayList;

import com.convenience.constant.*;

import lombok.Data;

@Data
public class Menu {

	private ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();

	@Data
	public class MenuItem {
	    private String name;
	    private String url;
	    private String description;
	    
	    MenuItem(String inName, String inUrl, String inDescription){
	    	name=inName;
	    	url=inUrl;
	    	description=inDescription;
	    }
	}
	
	public Menu(){
		menuItemList.add(new MenuItem("ホーム",UrlConst.HOMEINDEXURL,"このページに戻ってきます"));
		menuItemList.add(new MenuItem("商品注文",UrlConst.CHUMONKEYINPUTURL,"仕入先毎に注文を作成します。一日一回注文を起こし仕入先単位で注文コードを発番します。注文番号を指定して注文内容を修正することもできます。"));
	}

}