package com.xqsight.authc.model;

import com.xqsight.sso.model.UserBaseModel;

/**
 * merchant user entity
 * 
 * @author wanggganggang
 * 
 */
public class WalletMerUserModel extends UserBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6759588281504059890L;
	
	private String merId;

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

}
