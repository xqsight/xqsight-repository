package com.xqsight.authc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.authc.mapper.WalletUserBankMapper;
import com.xqsight.authc.model.WalletUserBankModel;
import com.xqsight.authc.service.WalletUserBankService;

@Service("walletUserBankService")
public class WalletUserBankServiceImpl implements WalletUserBankService {
	
	@Autowired(required=false)
	WalletUserBankMapper walletuserBankMappper;

	@Override
	public String saveUserBank(WalletUserBankModel walletUserBank) {
		return walletuserBankMappper.saveUserBank(walletUserBank) + "";
	}

	@Override
	public void updateUserBank(WalletUserBankModel walletUserBank) {
		walletuserBankMappper.updateUserBank(walletUserBank);
	}

	@Override
	public void updateUserBankStatus(String bankCardId, String active) {
		walletuserBankMappper.updateUserBankStatus(bankCardId, active);
	}

	@Override
	public void deleteUserBank(String bankCardId) {
		walletuserBankMappper.deleteUserBank(bankCardId);

	}

	@Override
	public List<WalletUserBankModel> queryUserBankByUserId(String userId) {
		return walletuserBankMappper.getUserBankByUserId(userId);
	}

	@Override
	public List<WalletUserBankModel> queryValidUserBankByUserId(String userId) {
		return walletuserBankMappper.getValidUserBankByUserId(userId);
	}

	@Override
	public WalletUserBankModel queryUserBankByBankCardId(String bankCardId) {
		return walletuserBankMappper.getByBankCardId(bankCardId);
	}

	@Override
	public WalletUserBankModel queryUserBankByBankCardCode(String bankCardCode) {
		// TODO Auto-generated method stub
		return walletuserBankMappper.getByBankCardCode(bankCardCode);
	}

}
