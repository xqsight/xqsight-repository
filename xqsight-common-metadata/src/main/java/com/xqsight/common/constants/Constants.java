/**
 * Copyright: Copyright (c) 2011 
 * Company:新启信息技术有限责任公司
 */
package com.xqsight.common.constants;

import java.math.BigDecimal;

/**
 * @Description: this is use for 
 * @author xqsight-jerry
 * @date 2016年1月8日 下午7:39:21
 */
public class Constants {
public final static BigDecimal THOUSAND_BIGDECIMAL = new BigDecimal("1000");
	
	public final static int YES = 0;
	public final static int NO = -1;
	
	public final static int ENABLE = 0;
    public final static int DISABLE = -1;
    
    public final static int ACTIVE = 0;
    public final static int INACTIVE = -1;
    
    public final static int SUCCESS = 0;
    public final static int FAILURE = -1;
    
    public final static String RETURN_CODE_SUCCESS = "0";
    public final static String RETURN_CODE_FAIL = "-1";

    
    public final static String VALIDATE_OK_MSG = "验证成功";

	public final static String VALIDATE_ERROR_MSG = "验证失败";

	public final static String FILE_NOT_EXIST = "当前文件不存在";

	public final static String SAVE_OK_MSG = "保存成功！";

	public final static String SAVE_ERROR_MSG = "保存失败！";

	public final static String UPD_OK_MSG = "修改成功！";

	public final static String UPD_ERROR_MSG = "修改失败！";

	public final static String DEL_OK_MSG = "删除成功！";

	public final static String DEL_ERROR_MSG = "删除失败！";

	public final static String USER_CELL_PHONE_EXIST_MSG = " 保存失败！手机已存在！";

	public final static String USER_EMAIL_EXIST_MSG = " 保存失败！邮箱已存在！";
	
	public final static String NU_AUTH_ACCESS_MSG = "对不起，你当前没有权限操作！";
	
	public final static String OPR_EXCEPTION_MSG = "对不起，系统出现异常，请稍后再试！";
	
	public final static String USER_LOGIN_ID_EXIST_MSG = " 保存失败！登录名已存在！";
	
	public final static String USER_RESET_PWD_MSG = " 密码重置成功！";
	
	public static final Object USER_UPD_PWD_MSG = "密码修改成功！";

	public static final String KEY_STATUS = "status";

	public static final String KEY_MESSAGE = "msg";

	public static final String KEY_DATA = "data";

}
