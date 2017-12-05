package com.echo.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;

import com.echo.dto.PcCdUserT;
import com.echo.dto.User;

public interface UserDao {
	/**
	 * 添加新用户
	 * @param user
	 * @return 
	 * @throws Exception 
	 */
	public boolean save(PcCdUserT user) throws Exception;
	
	public boolean updateUser(PcCdUserT user)throws Exception;
	/**
	 * 用户登录通过用户密码查找用户
	 * @param user
	 * @return
	 */
	public List<PcCdUserT> searchUser(String hql) throws Exception;
	
	
	public List<User> searchUserByName(String username);
	
	public List<Object[]> searchUserName(String username)throws Exception;
	
	public List<Object[]> searchUserNames(String sql,int start,int pagesize) throws  Exception;
	
	public List<Object[]> searchRight(String username,String password,String hql) throws HibernateException, SQLException;
	
	
	public boolean removeUser(String[] sqls) throws Exception;
	
	public List<Object[]> searchRole(String hql) throws Exception;
	
	
	public List<Object[]> searchDep(String hql) throws Exception;
//	
	public boolean updateUserLastLogin(String userid,int flg)throws Exception;

}