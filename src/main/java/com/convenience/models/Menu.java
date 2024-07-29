package com.convenience.models;

import java.util.ArrayList;

import com.convenience.models.MenuImpl.MenuItem;

/**
 * メニュー用インターフェース
 */
public interface Menu {
	/* メニューリスト取り出し用 */
	ArrayList<MenuItem> getMenuItemList();
}