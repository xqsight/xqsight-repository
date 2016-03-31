package com.xqsight.authc.service;

import java.util.List;

import com.xqsight.authc.model.WalletUserBankModel;

public interface WalletUserBankService {

	String saveUserBank(WalletUserBankModel walletUserBank);

	void updateUserBank(WalletUserBankModel walletUserBank);

	void updateUserBankStatus(String bankCardId, String active);

	void deleteUserBank(String bankCardId);

	List<WalletUserBankModel> queryUserBankByUserId(String userId);

	List<WalletUserBankModel> queryValidUserBankByUserId(String userId);

	WalletUserBankModel queryUserBankByBankCardId(String bankCardId);
	
	WalletUserBankModel queryUserBankByBankCardCode(String bankCardCode);

}
