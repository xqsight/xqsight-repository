package com.xqsight.authc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.xqsight.authc.model.WalletMerUserModel;
import com.xqsight.authc.model.WalletRoleModel;

/**
 * merchant user mapper
 * 
 * @author wanggganggang
 * 
 */
public interface WalletMerUserMapper {

	/**
	 * save merchant userinfo
	 * 
	 * @param walletMerUser
	 * @return
	 */
	@SelectKey(before=true, keyProperty="id", resultType=java.lang.Long.class, statement="select WALLET_MER_USER_SEQ.nextval from dual")
	@Insert("insert into wallet_mer_user (id, mer_id, user_id, user_name, password, cell_phone, email, create_date, active, remark)values (" 
	+ "#{id,jdbcType=NUMERIC}, #{merId,jdbcType=VARCHAR}, #{userId,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, #{cellPhone,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{createDate,jdbcType=TIMESTAMP}, #{active,jdbcType=NUMERIC}, #{remark,jdbcType=VARCHAR})")
	void saveMerUser(WalletMerUserModel walletMerUser);
	
	/**
	 * save merchant user role relation
	 * @param userId
	 * @param roleId
	 * @return
	 */
	@Insert("insert into mer_user_role(user_id,role_id)values(#{0},#{1})")
	void saveMerUserRole(long userId,String roleId);
	

	/**
	 * query merchanr user
	 * 
	 * @param userId
	 * @return
	 */
	@Select("select * from wallet_mer_user where id = #{id, jdbcType=NUMERIC}")
	WalletMerUserModel getByUserId(long id);

	/**
	 * query merchanr user is exist by cellPhone
	 * 
	 * @param cellPhone
	 * @return
	 */
	@Select("select * from wallet_mer_user where cell_phone = #{cellPhone, jdbcType=VARCHAR}")
	WalletMerUserModel isSaveExistByCellPhone(String cellPhone);
	
	/**
	 * query merchanr user is exist by email
	 * 
	 * @param email
	 * @return
	 */
	@Select("select * from wallet_mer_user where email = #{email, jdbcType=VARCHAR}")
	WalletMerUserModel isSaveExistByEmail(String email);
	
	/**
	 * query merchanr user is exist by cellPhone
	 * 
	 * @param cellPhone
	 * @return
	 */
	@Select("select * from wallet_mer_user where cell_phone = #{0} and id <> #{1}")
	WalletMerUserModel isUpdExistByCellPhone(String cellPhone,long id);
	
	/**
	 * query merchanr user is exist by email
	 * 
	 * @param email
	 * @return
	 */
	@Select("select * from wallet_mer_user where email = #{0} and id <> #{1}")
	WalletMerUserModel isUpdExistByEmail(String email,long id);
	
	
	/**
	 * query user role data
	 * 
	 * @param userId
	 * @return
	 */
	@Select("select wr.* from wallet_role wr left join mer_user_role mur on wr.role_id = mur.role_id where mur.id = #{id,jdbcType=NUMERIC}")
	List<WalletRoleModel> getUserRoleByUserId(long id);

	/**
	 * query current merchant all user
	 * 
	 * 
	 * @param merId
	 * @return
	 */
	@Select("select * from wallet_mer_user  where active = 1 and mer_id = #{merId, jdbcType=VARCHAR}")
	List<WalletMerUserModel> queryMerUser(String merId);

	@Update("update wallet_mer_user set password = #{password,jdbcType=VARCHAR} where id = #{id,jdbcType=NUMERIC}")
	void updateMerUserPwd(@Param("userId") long userId, @Param("password") String password);

	
	@Update("update wallet_mer_user set cell_phone = #{cellPhone,jdbcType=VARCHAR} where id = #{id,jdbcType=NUMERIC}")
	void updateUserCellPhone(@Param("id") long userId,@Param("cellPhone") String cellPhone);
	
	@Update("update wallet_mer_user set email = #{email,jdbcType=VARCHAR} where id = #{id,jdbcType=NUMERIC}")
	void updateUserEmail(@Param("id") long id,@Param("email") String email);
	
	@Update("update wallet_mer_user set user_name = #{userName,jdbcType=VARCHAR} where id = #{id,jdbcType=NUMERIC}")
	void updateUserName(@Param("id") long id,@Param("userName") String userName);
	
	@Update("update wallet_mer_user set img_uri = #{imgUri,jdbcType=VARCHAR} where id = #{id,jdbcType=NUMERIC}")
	void updateUserImg(@Param("id") long id,@Param("imgUri") String imgUri);
	
	@Update("update wallet_mer_user set user_name = #{userName,jdbcType=VARCHAR},cell_phone = #{cellPhone,jdbcType=VARCHAR},email = #{email,jdbcType=VARCHAR},remark = #{remark,jdbcType=VARCHAR} where user_id = #{userId,jdbcType=VARCHAR}")
	void updateMerUser(WalletMerUserModel walletMerUser);
	
	@Delete("delete mer_user_role where id = #{id,jdbcType=NUMERIC}")
	void deleteMerUserRoleById(@Param("id") long id);

	@Delete("delete wallet_mer_user where id = #{id,jdbcType=NUMERIC}")
	void deleteMerUser(@Param("id") long id);
}
