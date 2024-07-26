package com.convenience.constant;

/**
 * URL&ビュー定数クラス
 * 
 *
 */
public class UrlConst {
	
	/** ルート画面 */
	public static final String ROOT = "/";
	
	/** ログイン画面 */
	public static final String LOGIN = "/Login/login";
	
	/** ログアウトUrl */
	public static final String LOGOUT = "/logout";

	/** ログイン成功後の遷移先メニュー画面 */
	public static final String MENU = "/Home/Index";
	
	/* ホームコントローラリクエストマッピング */
	public static final String HOMEMAPPNG = "/Home";
	
	/* ホームページ */
	public static final String HOMEINDEX = "/Index";
	/* ホームページURL */
	public static final String HOMEINDEXURL = HOMEMAPPNG + HOMEINDEX;
	/** 注文コントローラリクエストマッピング */
	public static final String CHUMONMAPPNG = "/Chumon";
	
	/** 注文キー入力画面 */
	public static final String CHUMONKEYINPUT = "/KeyInput";
	
	/** 注文キー入力画面URL */
	public static final String CHUMONKEYINPUTURL = CHUMONMAPPNG + CHUMONKEYINPUT;

	/** 注文明細画面 */
	public static final String CHUMONMEISAI = "/ChumonMeisai";

	/** 注文明細画面URL */
	public static final String CHUMONMEISAIURL = CHUMONMAPPNG + CHUMONMEISAI;

	

	/** 認証不要画面 */
	public static final String[] NO_AUTHENTICATION = { LOGIN, ROOT, "/js/**", "/css/**", "/lib/**", "/UserInfo/**", "/Shared/**" };
}