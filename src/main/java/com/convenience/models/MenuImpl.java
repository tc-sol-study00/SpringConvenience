package com.convenience.models;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.convenience.constant.*;

import lombok.Data;

/**
 * メニュークラス
 */

@Component
@Data
public class MenuImpl implements Menu {

	/* メニューリスト管理用 */
	private ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();

	/**
	 *メニュー内容クラス
	 */
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
	
	/**
	 *　メニュー設定コンストラクター 
	 */
	public MenuImpl(){
		menuItemList.add(new MenuItem("ホーム",UrlConst.HOMEINDEXURL,"このページに戻ってきます"));
		menuItemList.add(new MenuItem("商品注文",UrlConst.CHUMONKEYINPUTURL,"仕入先毎に注文を作成します。一日一回注文を起こし仕入先単位で注文コードを発番します。注文番号を指定して注文内容を修正することもできます。"));
	}

}