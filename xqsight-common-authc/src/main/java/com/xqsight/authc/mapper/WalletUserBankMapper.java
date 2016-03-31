package com.xqsight.authc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.xqsight.authc.model.WalletUserBankModel;

/**
 * person bank info
 * 
 * @author wanggganggang
 * 
 */
public interface WalletUserBankMapper {

	/**
	 * save user bank info
	 * 
	 * @param walletUserBank
	 * @return
	 */
	@SelectKey(before = true, keyProperty = "bankCardId", resultType = java.lang.Long.class, statement = "select WALLET_USER_BANK_SEQ.nextval from dual")
	@Insert("insert into wallet_user_bank(user_id, bank_card_id, channel_name, channel_code, bank_card_code, card_type, step, active, remark)values ("
			+ "#{userId,jdbcType=VARCHAR}, #{bankCardId,jdbcType=NUMERIC}, #{channelName,jdbcType=VARCHAR}, #{channelCode,jdbcType=VARCHAR}, #{bankCardCode,jdbcType=VARCHAR}, #{cardType,jdbcType=VARCHAR}, #{step,jdbcType=NUMERIC}, #{active,jdbcType=NUMERIC}, #{remark,jdbcType=VARCHAR})")
	long saveUserBank(WalletUserBankModel walletUserBank);

	/**
	 * query bank info
	 * 
	 * @param bankCardId
	 * @return
	 */
	@Select("select * from wallet_user_bank where bank_card_id = #{bankCardId, jdbcType=VARCHAR}")
	WalletUserBankModel getByBankCardId(@Param("bankCardId") String bankCardId);

	/**
	 * query bank info
	 * 
	 * @param bankCardCode
	 * @return
	 */
	@Select("select * from wallet_user_bank where bank_card_code = #{bankCardCode, jdbcType=VARCHAR}")
	WalletUserBankModel getByBankCardCode(@Param("bankCardCode") String bankCardCode);

	
	/**
	 * by current user query bank info
	 * 
	 * @param userId
	 * @return
	 */
	@Select("select * from wallet_user_bank where user_id = #{userId ,jdbcType=VARCHAR}")
	List<WalletUserBankModel> getUserBankByUserId(@Param("userId") String userId);
	
	/**
	 * by current user query valid bank info
	 * 
	 * @param userId
	 * @return
	 */
	@Select("select * from wallet_user_bank where active=1 and user_id = #{userId ,jdbcType=VARCHAR}")
	List<WalletUserBankModel> getValidUserBankByUserId(@Param("userId") String userId);
	

	@Update("update wallet_user_bank set card_type = #{cardType,jdbcType=VARCHAR}, channel_name = #{channelName,jdbcType=VARCHAR}, channel_code = #{channelCode,jdbcType=VARCHAR},active = #{active,jdbcType=VARCHAR},step = #{step,jdbcType=NUMERIC} where user_id = #{userId,jdbcType=VARCHAR} and bank_card_id = #{bankCardId,jdbcType=VARCHAR}")
	void updateUserBank(WalletUserBankModel walletUserBank);

	@Update("update wallet_user_bank set active = #{active,jdbcType=VARCHAR} where bank_card_id = #{bankCardId,jdbcType=VARCHAR}")
	void updateUserBankStatus(@Param("bankCardId") String bankCardId, @Param("active") String active);

	@Delete("delete wallet_user_bank where bank_card_id = #{bankCardId,jdbcType=VARCHAR}")
	void deleteUserBank(@Param("bankCardId") String bankCardId);

}
