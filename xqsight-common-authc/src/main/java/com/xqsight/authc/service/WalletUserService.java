package com.xqsight.authc.service;

import com.xqsight.authc.model.WalletUserModel;
import com.xqsight.sso.model.UserBaseModel;

/**
 * merchant user info manage interface
 * 
 * @author wanggganggang
 * 
 */
public interface WalletUserService {

	WalletUserModel queryUserById(long id);
	
	/**
     * 根据手机查找用户
     * @param cellPhone
     * @return
     */
    UserBaseModel getByCellPhone(String cellPhone);

    /**
     * 根据邮箱查找用户
     * @param email
     * @return
     */
    UserBaseModel getByEmail(String email);

    /**
     * 根据用户名查找用户
     * @param userId
     * @return
     */
    UserBaseModel getByUserId(String userId);

	void updateUser(WalletUserModel walletUser);

	void saveUser(WalletUserModel walletUser);

	void deleteUser(long id);

	void updateUserPwd(long id, String password);
	
	void updateUserPayPwd(long id, String payPassword);
	
	void updateUserName(long id, String userName);
	
	void updateEmail(long id, String email);
	
	void updateCellPhone(long id, String cellPhone);
	
	void updateUserImg(long id, String imgPath);

	boolean isSaveExistMerUserByCellPhone(String cellPhone);

	boolean isSaveExistMerUserByEmail(String email);
	
	boolean isUpdExistMerUserByCellPhone(String cellPhone,long id);

	boolean isUpdExistMerUserByEmail(String email,long id);

}
