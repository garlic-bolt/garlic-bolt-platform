package com.chanjetpay.garlic.pojo;

import org.springframework.util.StringUtils;

public class EnrollForm {

	private String operatorId;
	private String blockCode;
	private String blockName;
	private String wardenEmail;
	private String inviteCode;

	private String wardenName;
	private String wardenIdNo;
	private String password;
	private String rePassword;

	private String address;

	private String industry;

	public EnrollForm(){

	}

	public EnrollForm(String inviteCode, String blockCode, String blockName, String wardenEmail) {
		this.inviteCode = inviteCode;
		this.blockCode = blockCode;
		this.blockName = blockName;
		this.wardenEmail = wardenEmail;
		this.operatorId = blockCode + "01";
	}

	public boolean validate() {
		return !StringUtils.isEmpty(wardenName) && !StringUtils.isEmpty(address) && this.password.equals(this.rePassword);
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getWardenEmail() {
		return wardenEmail;
	}

	public void setWardenEmail(String wardenEmail) {
		this.wardenEmail = wardenEmail;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getWardenName() {
		return wardenName;
	}

	public void setWardenName(String wardenName) {
		this.wardenName = wardenName;
	}

	public String getWardenIdNo() {
		return wardenIdNo;
	}

	public void setWardenIdNo(String wardenIdNo) {
		this.wardenIdNo = wardenIdNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
}
