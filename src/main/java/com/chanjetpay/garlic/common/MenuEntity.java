package com.chanjetpay.garlic.common;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class MenuEntity implements Comparable {
	//菜单编码
	private String code;
	//菜单标题
	private String title;
	//菜单链接
	private String url;
	//菜单图标
	private String icon;
	//提醒
	private String mask;
	//标注
	private String anot;

	//菜单层级
	private Integer level;
	//是否有子菜单
	private Boolean hasSub;

	private List<MenuEntity> subMenus = new ArrayList<>();

	private ArrayList<MenuEntity> parentLink = new ArrayList<>();

	public MenuEntity(){

	}

	public MenuEntity(String code, String title, String url){
		this.code = code;
		this.title = title;
		this.url= url;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getIcon() {
		return icon;
	}

	public MenuEntity setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public String getMask() {
		return mask;
	}

	public MenuEntity setMask(String mask) {
		this.mask = mask;
		return this;
	}

	public String getAnot() {
		return anot;
	}

	public MenuEntity setAnot(String anot) {
		this.anot = anot;
		return this;
	}

	public Boolean getHasSub() {
		return this.subMenus.size() > 0;
	}

	public Integer getLevel() {
		return this.getCode().length() % 2;
	}

	public List<MenuEntity> getSubMenus() {
		return subMenus;
	}

	public MenuEntity addSubMenu(MenuEntity menu){
		this.subMenus.add(menu);

		if(!parentLink.contains(this)){
			parentLink.add(this);
		}

		return this;
	}

	@Override
	public int compareTo(Object o) {
		MenuEntity m = (MenuEntity) o;
		return this.getCode().compareTo(m.getCode());
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, true, true);
	}
}
