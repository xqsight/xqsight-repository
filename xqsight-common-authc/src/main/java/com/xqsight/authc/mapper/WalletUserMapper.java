package com.xqsight.authc.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.xqsight.authc.model.WalletUserModel;

/**
 * merchant user mapper
 * 
 * @author wanggganggang
 * 
 */
public interface WalletUserMapper {

	/**
	 * save merchant userinfo
	 * 
	 * @param walletMerUser
	 * @return
	 */
	@SelectKey(before=true, keyProperty="id", resultType=java.lang.Long.class, statement="select WALLET_USER_SEQ.nextval from dual")
	@Insert("insert into wallet_user (id, user_id, user_name, password, cell_phone, email, img_uri, create_date, locked, active, remark)values ("
			+ "#{id,jdbcType=NUMERIC}, #{userId,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, #{cellPhone,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{imgUri,jdbcType=VARCHAR}, #{createDate,jdbcType=TIMESTAMP}, #{locked,jdbcType=NUMERIC}, #{active,jdbcType=NUMERIC}, #{remark,jdbcType=VARCHAR})")
	void saveUser(WalletUserModel walletUser);
	

	/**
     * query user by id
     * @param id
     * @return
     */
	@Select("select * from wallet_user where id = #{id, jdbcType=NUMERIC}")
    WalletUserModel getById(@Param("id") long id);
    
	/**
	 * query user by userId
	 * 
	 * @param userId
	 * @return
	 */
	@Select("select * from wallet_user where id = #{userId, jdbcType=VARCHAR}")
	WalletUserModel getByUserId(@Param("userId") String userId);
	
	@Select("select * from wallet_user where email = #{email, jdbcType=VARCHAR}")
	WalletUserModel getByEmail(@Param("email") String email);

	@Select("select * from wallet_user where cell_phone = #{cellPhone, jdbcType=VARCHAR}")
	WalletUserModel getByCellPhone(@Param("cellPhone") String cellPhone);
	
	/**
	 * query merchanr user is exist by cellPhone
	 * 
	 * @param cellPhone
	 * @return
	 */
	@Select("select * from wallet_user where cell_phone = #{cellPhone, jdbcType=VARCHAR}")
	WalletUserModel isSaveExistByCellPhone(String cellPhone);
	
	/**
	 * query merchanr user is exist by email
	 * 
	 * @param email
	 * @return
	 */
	@Select("select * from wallet_user where email = #{email, jdbcType=VARCHAR}")
	WalletUserModel isSaveExistByEmail(String email);
	
	/**
	 * query merchanr user is exist by cellPhone
	 * 
	 * @param cellPhone
	 * @return
	 */
	@Select("select * from wallet_user where cell_phone = #{0} and id <> #{1}")
	WalletUserModel isUpdExistByCellPhone(String cellPhone,long id);
	
	/**
	 * query merchanr user is exist by email
	 * 
	 * @param email
	 * @return
	 */
	@Select("select * from wallet_user where email = #{0} and id <> #{1}")
	WalletUserModel isUpdExistByEmail(@Param("email") String email, @Param("id") long id);
	
	
	@Update("update wallet_user set user_name = #{userName,jdbcType=VARCHAR},cell_phone = #{cellPhone,jdbcType=VARCHAR},email = #{email,jdbcType=VARCHAR} where user_id = #{userId,jdbcType=VARCHAR}")
	void updateUser(WalletUserModel walletUser);
	
	@Update("update wallet_user set password = #{password,jdbcType=VARCHAR} where id = #{id,jdbcType=NUMERIC}")
	void updateUserPwd(@Param("id") long id,@Param("password") String password);
	
	@Update("update wallet_user set cell_phone = #{cellPhone,jdbcType=VARCHAR} where id = #{id,jdbcType=NUMERIC}")
	void updateUserCellPhone(@Param("id") long id,@Param("cellPhone") String cellPhone);
	
	@Update("update wallet_user set email = #{email,jdbcType=VARCHAR} where id = #{id,jdbcType=NUMERIC}")
	void updateUserEmail(@Param("id") long id,@Param("email") String email);
	
	@Update("update wallet_user set pay_password = #{payPassword,jdbcType=VARCHAR} where id = #{userId,jdbcType=NUMERIC}")
	void updateUserPayPwd(@Param("id") long id,@Param("payPassword") String payPassword);
	
	@Update("update wallet_user set user_name = #{userName,jdbcType=VARCHAR} where id = #{id,jdbcType=NUMERIC}")
	void updateUserName(@Param("id") long id,@Param("userName") String userName);
	
	@Update("update wallet_user set img_path = #{imgPath,jdbcType=VARCHAR} where id = #{id,jdbcType=NUMERIC}")
	void updateUserImg(@Param("id") long id,@Param("imgPath") String imgPath);
	
	@Delete("delete wallet_user where id = #{id,jdbcType=NUMERIC}")
	void deleteUser(@Param("id") long id);


}
