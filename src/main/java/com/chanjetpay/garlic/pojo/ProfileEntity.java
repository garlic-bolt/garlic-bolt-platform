package com.chanjetpay.garlic.pojo;

import com.chanjetpay.garlic.dto.AuthorityDto;
import com.chanjetpay.garlic.dto.OperatorDto;
import com.chanjetpay.garlic.enums.AuthorityTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

public class ProfileEntity implements Serializable{
	private static final long serialVersionUID = 6835026061434461681L;

	private String userName;
	private String avatarUrl;
	private List<NoticeEntity> alertMessages;

	private OperatorDto operator;
	private Map<String, AuthorityDto> authorities = new HashMap<>();
	private List<MenuEntity> menus = new ArrayList<>();

	public Integer getAlertCount(){
		return alertMessages == null ? null : alertMessages.size();
	}

	public List<MenuEntity> getMenus() {
		return menus;
	}

	public void setMenus(List<AuthorityDto> authorityList) {
		for (AuthorityDto authority : authorityList) {
			if(authority.getType() == AuthorityTypeEnum.MENU) {
				MenuEntity menu = new MenuEntity(authority.getAuthCode(), authority.getTitle(), authority.getUri());
				for(AuthorityDto subAuthority : authority.getSubAuthorityList()){
					if(subAuthority.getType() == AuthorityTypeEnum.MENU){
						menu.addSubMenus(new MenuEntity(subAuthority.getAuthCode(), subAuthority.getTitle(), subAuthority.getUri()));
					}
				}
				menus.add(menu);
			}
		}

		Collections.sort(menus);
	}

	public Map<String, AuthorityDto> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<AuthorityDto> authorityList) {
		for (AuthorityDto authorityDto : authorityList) {
			if (!StringUtils.isEmpty(authorityDto.getUri())) {
				authorities.put(authorityDto.getUri(), authorityDto);
			} else {
				authorities.put(authorityDto.getAuthCode(), authorityDto);
			}
		}
	}

	public OperatorDto getOperator() {
		return operator;
	}

	public void setOperator(OperatorDto operator) {
		this.operator = operator;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public List<NoticeEntity> getAlertMessages() {
		return alertMessages;
	}

	public void setAlertMessages(List<NoticeEntity> alertMessages) {
		this.alertMessages = alertMessages;
	}
}
