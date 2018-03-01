package com.chanjetpay.garlic.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuEntity implements Comparable<MenuEntity> {
	private String menuId;
	private String title;
	private String url;

	private List<MenuEntity> subMenus = new ArrayList<>();

	public Boolean hasSubMenus(){
		return subMenus.size() > 0;
	}

	public MenuEntity(String menuId, String title, String url) {
		this.menuId = menuId;
		this.title = title;
		this.url = url;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuEntity> getSubMenus() {
		return subMenus;
	}

	public void addSubMenus(MenuEntity subMenu) {
		this.subMenus.add(subMenu);
		Collections.sort(this.subMenus);
	}

	@Override
	public int compareTo(MenuEntity o) {
		return this.menuId.compareTo(o.getMenuId());
	}
}
