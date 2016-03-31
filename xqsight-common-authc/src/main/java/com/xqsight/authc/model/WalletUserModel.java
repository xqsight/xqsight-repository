package com.xqsight.authc.model;

import com.xqsight.sso.model.UserBaseModel;

public class WalletUserModel extends UserBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1694919119735197462L;
	
	private String payPassword;

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

}
