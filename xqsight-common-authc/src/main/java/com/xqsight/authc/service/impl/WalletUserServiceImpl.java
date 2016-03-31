package com.xqsight.authc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.authc.mapper.WalletUserMapper;
import com.xqsight.authc.model.WalletUserModel;
import com.xqsight.authc.service.WalletUserService;
import com.xqsight.sso.model.UserBaseModel;

@Service("walletUserService")
public class WalletUserServiceImpl implements WalletUserService {

	@Autowired(required=false)
	WalletUserMapper walletUserMapper;

	@Override
	public WalletUserModel queryUserById(long id) {
		return walletUserMapper.getById(id);
	}
	
    /** 
     * @see com.xqsight.authc.service.WalletUserService#getByCellPhone(java.lang.String)
     */
    @Override
    public UserBaseModel getByCellPhone(String cellPhone) {
        return walletUserMapper.getByCellPhone(cellPhone);
    }

    /** 
     * @see com.xqsight.authc.service.WalletUserService#getByEmail(java.lang.String)
     */
    @Override
    public UserBaseModel getByEmail(String email) {
        return walletUserMapper.getByEmail(email);
    }

    /** 
     * @see com.xqsight.authc.service.WalletUserService#getByUserId(java.lang.String)
     */
    @Override
    public UserBaseModel getByUserId(String userId) {
        return walletUserMapper.getByUserId(userId);
    }

	@Override
	public void updateUser(WalletUserModel walletUser) {
		walletUserMapper.updateUser(walletUser);
	}

	@Override
	public void saveUser(WalletUserModel walletUser) {
		 walletUserMapper.saveUser(walletUser);
	}

	@Override
	public void deleteUser(long id) {
		walletUserMapper.deleteUser(id);
	}

	@Override
	public void updateUserPwd(long id, String password) {
		walletUserMapper.updateUserPwd(id, password);
	}

	@Override
	public void updateUserName(long id, String userName) {
		walletUserMapper.updateUserName(id, userName);
	}

	@Override
	public void updateUserImg(long id, String imgPath) {
		walletUserMapper.updateUserImg(id, imgPath);
	}

	@Override
	public void updateUserPayPwd(long id, String payPassword) {
		walletUserMapper.updateUserPayPwd(id, payPassword);
	}

	@Override
	public boolean isSaveExistMerUserByCellPhone(String cellPhone) {
		WalletUserModel walletUser = walletUserMapper.isSaveExistByCellPhone(cellPhone);

		return walletUser == null ? false : true;
	}

	@Override
	public boolean isSaveExistMerUserByEmail(String email) {
		WalletUserModel walletUser = walletUserMapper.isSaveExistByEmail(email);

		return walletUser == null ? false : true;
	}

	@Override
	public boolean isUpdExistMerUserByCellPhone(String cellPhone, long id) {
		WalletUserModel walletUser = walletUserMapper.isUpdExistByCellPhone(cellPhone, id);

		return walletUser == null ? false : true;
	}

	@Override
	public boolean isUpdExistMerUserByEmail(String email, long id) {
		WalletUserModel walletUser = walletUserMapper.isUpdExistByEmail(email, id);
		return walletUser == null ? false : true;
	}

	@Override
	public void updateEmail(long id, String email) {
		walletUserMapper.updateUserEmail(id, email);
	}

	@Override
	public void updateCellPhone(long id, String cellPhone) {
		walletUserMapper.updateUserCellPhone(id, cellPhone);
	}

}
