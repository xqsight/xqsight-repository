package com.xqsight.authc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.authc.mapper.WalletMerUserMapper;
import com.xqsight.authc.model.WalletMerUserModel;
import com.xqsight.authc.model.WalletRoleModel;
import com.xqsight.authc.service.WalletMerUserService;

@Service("walletMerUserService")
public class WalletMerUserServiceImpl implements WalletMerUserService {

	@Autowired(required=false)
	private WalletMerUserMapper walletMerUserMapper;

	@Override
	public List<WalletMerUserModel> queryMerUserByMerId(String merId) {
		return walletMerUserMapper.queryMerUser(merId);
	}

	@Override
	public WalletMerUserModel queryMerUserById(long userId) {
		// TODO Auto-generated method stub
		return walletMerUserMapper.getByUserId(userId);
	}

	@Override
	public void updateMerUser(WalletMerUserModel walletMerUser, List<String> roleIdList) {
		// TODO Auto-generated method stub
		walletMerUserMapper.updateMerUser(walletMerUser);
		walletMerUserMapper.deleteMerUserRoleById(walletMerUser.getId());
		for (String roleId : roleIdList) {
			walletMerUserMapper.saveMerUserRole(walletMerUser.getId(), roleId);
		}
	}

	@Override
	public void saveMerUser(WalletMerUserModel walletMerUser, List<String> roleIdList) {
		// TODO Auto-generated method stub
		walletMerUserMapper.saveMerUser(walletMerUser);

		walletMerUserMapper.deleteMerUserRoleById(walletMerUser.getId());
		for (String roleId : roleIdList) {
			walletMerUserMapper.saveMerUserRole(walletMerUser.getId(), roleId);
		}
	}

	@Override
	public void deleteMerUser(long id) {
		// TODO Auto-generated method stub
		walletMerUserMapper.deleteMerUser(id);
		walletMerUserMapper.deleteMerUserRoleById(id);
	}

	@Override
	public void updateMerUserPwd(long userId, String password) {
		// TODO Auto-generated method stub
		walletMerUserMapper.updateMerUserPwd(userId, password);
	}

	@Override
	public List<WalletRoleModel> queryWalletRoleByUserId(long userId) {
		return walletMerUserMapper.getUserRoleByUserId(userId);
	}

	@Override
	public boolean isSaveExistMerUserByCellPhone(String cellPhone) {
		WalletMerUserModel walletMerUser = walletMerUserMapper.isSaveExistByCellPhone(cellPhone);
		return walletMerUser == null ? false : true;
	}

	@Override
	public boolean isSaveExistMerUserByEmail(String email) {
		WalletMerUserModel walletMerUser = walletMerUserMapper.isSaveExistByEmail(email);
		return walletMerUser == null ? false : true;
	}

	@Override
	public boolean isUpdExistMerUserByCellPhone(String cellPhone, long userId) {
		WalletMerUserModel walletMerUser = walletMerUserMapper.isUpdExistByCellPhone(cellPhone, userId);
		return walletMerUser == null ? false : true;
	}

	@Override
	public boolean isUpdExistMerUserByEmail(String email, long userId) {
		WalletMerUserModel walletMerUser = walletMerUserMapper.isUpdExistByEmail(email, userId);
		return walletMerUser == null ? false : true;
	}

	@Override
	public void updateUserName(long userId, String userName) {
		// TODO Auto-generated method stub
		walletMerUserMapper.updateUserName(userId, userName);
	}

	@Override
	public void updateEmail(long userId, String email) {
		// TODO Auto-generated method stub
		walletMerUserMapper.updateUserEmail(userId, email);
	}

	@Override
	public void updateCellPhone(long userId, String cellPhone) {
		// TODO Auto-generated method stub
		walletMerUserMapper.updateUserCellPhone(userId, cellPhone);
	}

	@Override
	public void updateUserImg(long userId, String imgPath) {
		// TODO Auto-generated method stub
		walletMerUserMapper.updateUserImg(userId, imgPath);
	}

}
