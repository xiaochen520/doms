package com.echo.service;


import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.HibernateException;

import com.echo.dto.PcCdUserT;
import com.echo.dto.User;

public interface UserService {
	/**
	 * 添加新用户
	 * @param user
	 * @return 
	 * @throws Exception 
	 */
	public boolean addUser(PcCdUserT user) throws Exception;
	
	public boolean updateUser(PcCdUserT user) throws Exception;
	
	/**
	 * 用户登录通过用户密码查找用户
	 * @param user
	 * @return
	 */
	public List<PcCdUserT> searchUserByNamePas(String username,String password) throws Exception;
	
	/**
	 * 获取用户信息
	 * @param username 用户名
	 * @param pageNo 当前页
	 * @param sort 排序名
	 * @param order 排序方向
	 * @param rowsPerpage 当前页
	 * @return 用户json数据
	 */
	public JSONObject searchUserByName(String department,String username,int pageNo,String sort,String order,int rowsPerpage)throws Exception;
	
	public List<Object[]> searchRight(String username,String password) throws HibernateException, SQLException;
	

	public User searchUserById(String userId);
	
	public List<PcCdUserT> searchUser (String userId) throws Exception;
	
	public JSONArray searchUserByid(String userId) throws Exception;
	
	public List<Object[]> searchUserChick(String name)throws Exception;
	
	public boolean removeUser(String userId) throws Exception;
	
	public JSONArray searchUserRole() throws Exception;
	
	public JSONArray searchUserDep() throws Exception;
	
	public String searchStructureByName(String structureName);
	
	public String searchPcdByName(String pcdName);
	
	public boolean updateUserLastLogin (String userid,int flg) throws Exception;
	
	public List<Object[]> searchDatas(String sql)throws Exception;
	
}
