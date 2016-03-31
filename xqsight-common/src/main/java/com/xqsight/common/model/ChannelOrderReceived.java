/**
 * 
 */
package com.xqsight.common.model;

import java.util.Date;

/**
 * 一张小表，记录连接id和渠道订单号，还有接收时间，连接id和渠道订单号是联合主键
 * 用于判断订单是否由渠道通知过
 * @author linhaoran
 *
 */
public class ChannelOrderReceived {
	
	private String connId;
	
	private String channelOrderNo;
	
	private Date receivedTime;

	public String getConnId() {
		return connId;
	}

	public void setConnId(String connId) {
		this.connId = connId;
	}

	public String getChannelOrderNo() {
		return channelOrderNo;
	}

	public void setChannelOrderNo(String channelOrderNo) {
		this.channelOrderNo = channelOrderNo;
	}

	public Date getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	} 

}
