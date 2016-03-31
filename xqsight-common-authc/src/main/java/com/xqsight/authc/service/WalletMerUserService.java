package com.xqsight.authc.service;

import java.util.List;

import com.xqsight.authc.model.WalletMerUserModel;
import com.xqsight.authc.model.WalletRoleModel;

/**
 * merchant user info manage interface
 * 
 * @author wanggganggang
 * 
 */
public interface WalletMerUserService {

	List<WalletMerUserModel> queryMerUserByMerId(String merId);
	
	WalletMerUserModel queryMerUserById(long userId);

	void updateMerUser(WalletMerUserModel walletMerUser,List<String> roleIdList);

	void saveMerUser(WalletMerUserModel walletMerUser,List<String> roleIdList);

	void deleteMerUser(long userId);

	void updateMerUserPwd(long userId, String password);
	
	List<WalletRoleModel> queryWalletRoleByUserId(long userId);
	
	void updateUserName(long userId, String userName);
	
	void updateEmail(long userId, String email);
	
	void updateCellPhone(long userId, String cellPhone);
	
	void updateUserImg(long userId, String imgPath);
	
	boolean isSaveExistMerUserByCellPhone(String cellPhone);

	boolean isSaveExistMerUserByEmail(String email);
	
	boolean isUpdExistMerUserByCellPhone(String cellPhone,long userId);

	boolean isUpdExistMerUserByEmail(String email,long userId);

}
