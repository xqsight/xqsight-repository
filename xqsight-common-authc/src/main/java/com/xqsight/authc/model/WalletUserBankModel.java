package com.xqsight.authc.model;

public class WalletUserBankModel {

	private String userId;

	// 银行卡ID
	private long bankCardId;

	// 银行名称
	private String channelName;

	// 银行编号
	private String channelCode;

	// 卡号
	private String bankCardCode;

	// 01：储蓄卡02：贷记卡
	private String cardType;

	private int step;

	// 0:no valid 1:valid
	private int active;

	private String remark;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(long bankCardId) {
		this.bankCardId = bankCardId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getBankCardCode() {
		return bankCardCode;
	}

	public void setBankCardCode(String bankCardCode) {
		this.bankCardCode = bankCardCode;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
