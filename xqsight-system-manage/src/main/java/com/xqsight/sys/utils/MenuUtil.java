/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.xqsight.sys.model.SysMenu;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年1月4日 上午9:25:17
 */
public class MenuUtil {
  
	/**
	 * 
	 * treeMenuList 遍历递归菜单
	 *
	 * @Title: treeMenuList
	 * @Description: TODO
	 * @param @param list
	 * @param @param parentId
	 * @param @return    设定文件
	 * @return List<SysMenu>    返回类型
	 * @throws
	 */
    public static List<SysMenu> treeMenuList(List<SysMenu> list, int parentId) {  
    	List<SysMenu> sysMenus = new ArrayList<>();  
        for (SysMenu sysMenu : list) {  
            int menuId = sysMenu.getMenuId();
            int pid = sysMenu.getParentId();
            if (parentId == pid) {  
            	List<SysMenu> childrenList = treeMenuList(list, menuId);  
            	sysMenu.setChildren(childrenList);
            	sysMenus.add(sysMenu);  
            }  
        }  
        return sysMenus;  
    }  
    
    /**
     * 
     * treeMenuList 递归菜单生成树
     *
     * @Title: treeMenuList
     * @Description: TODO
     * @param @param list
     * @param @return    设定文件
     * @return List<SysMenu>    返回类型
     * @throws
     */
    public static  List<SysMenu> treeMenuList(List<SysMenu> list) {
    	int nodeMinPartentId  = getNodeMinParentId(list);
    	return treeMenuList(list,nodeMinPartentId);
    }
    
    /**
     * 
     * getNodeMinParentId获取根的parentId
     *
     * @Title: getNodeMinParentId
     * @Description: TODO
     * @param @param list
     * @param @return    设定文件
     * @return int    返回类型
     * @throws
     */
    private static int getNodeMinParentId(List<SysMenu> list){
        if(list != null && list.size() > 0){
            int temp = list.get(0).getParentId();
            for(int i = 0; i < list.size(); i ++){
                if(list.get(i).getParentId() < temp){
                    temp = list.get(i).getParentId();
                }
            }
            return temp;
        }else{
            return 0;
        }
    }
    
    public static void main(String args[]) {
		List<SysMenu> sysMenus = new ArrayList<>();
		SysMenu sysMenu = new SysMenu();
		sysMenu.setMenuId(1);
		sysMenu.setMenuName("系统管理");
		sysMenu.setParentId(0);
		sysMenus.add(sysMenu);
		
		SysMenu sysMenu2 = new SysMenu();
		sysMenu2.setMenuId(2);
		sysMenu2.setParentId(1);
		sysMenu2.setMenuName("权限管理");
		sysMenus.add(sysMenu2);
		
		SysMenu sysMenu3 = new SysMenu();
		sysMenu3.setMenuId(3);
		sysMenu3.setParentId(2);
		sysMenu3.setMenuName("新增");
		sysMenus.add(sysMenu3);
		
		SysMenu sysMenu4 = new SysMenu();
		sysMenu4.setMenuId(4);
		sysMenu4.setParentId(2);
		sysMenu4.setMenuName("修改");
		sysMenus.add(sysMenu4);
		
		System.out.println(JSON.toJSONString(treeMenuList(sysMenus,0)));
	}
}
