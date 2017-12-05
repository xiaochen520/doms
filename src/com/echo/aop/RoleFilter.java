package com.echo.aop;
/**
 * 对用户操作数据进行权限校验，用户所要进行操作的数据对象，必须是用户所在权限
 * @author yanlong
 *
 */
public class RoleFilter {
	/**
	 * 判断是否拥有数据操作权限
	 * @param pcd 操作数据所在排采队
	 * @param jc 操作数据所在井场
	 * @param qj 操作数据所在井口
	 * @param userOfPcd 用户所在排采队（如果用户所在排长队为空，则拥有所以操作数据权限）
	 * @return  flag true:拥有操作权限，false:没有操作权限
	 */
	public static boolean  OperateOfRole(String pcd,String jc,String qj,String userOfPcd){
		//是否拥有数据操作权限
		boolean flag = true;
		
		
		return flag;
	}
}
