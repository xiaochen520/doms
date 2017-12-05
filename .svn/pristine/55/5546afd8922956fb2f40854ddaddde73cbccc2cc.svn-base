package com.echo.util;

import java.sql.ResultSet;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;

import java.util.Hashtable;


import com.echo.util.Globals;

public class StatementManager
{
	private static Context ctx;
	private static Hashtable<String, DataSource> dss = new Hashtable<String, DataSource>();
	private Connection conn;
	private PreparedStatement preStmt;

	public StatementManager(String datasourceName)
	{
		createConn(datasourceName);
	}

	/**
	 * 在Globals.properties文件中的DefaultDatasourceName属性值指定的数据源中创建连接。
	 */
	public StatementManager()
	{
		String datasourceName = Globals.getProperty("DefaultDatasourceName");
		System.out.println(datasourceName);
		createConn(datasourceName);
	}

	/**
	 * 在参数值指定的数据源中创建连接。
	 */
	private void createConn(String datasourceName)
	{
		DataSource ds;
		try
		{
			if (ctx == null)
			{
				InitialContext initCtx = new InitialContext();
				ctx = (Context) initCtx.lookup("java:comp/env");
			}
			ds = dss.get(datasourceName);
			if (ds == null)
			{
				ds = (DataSource) ctx.lookup(datasourceName);
				if (ds != null)
				{
					dss.put(datasourceName, ds);
					System.out.println("get datasource sucsess");
				}
			}
			conn = ds.getConnection();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public PreparedStatement prepareSql(String sql)
	{
		try
		{
			preStmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return preStmt;
	}
	
	public boolean prepareSetParameter(int paraIndex,String str)
	{
		if (preStmt == null) return false;
		boolean result =false;
		try
		{
			preStmt.setString(paraIndex, str);
			result = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean prepareSetParameter(int paraIndex,Double dbl)
	{
		if (preStmt == null) return false;
		boolean result =false;
		try
		{
			preStmt.setDouble(paraIndex, dbl);
			result = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean prepareSetParameter(int paraIndex,Float flt)
	{
		if (preStmt == null) return false;
		boolean result =false;
		try
		{
			preStmt.setFloat(paraIndex, flt);
			result = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean prepareSetParameter(int paraIndex,Date dt)
	{
		if (preStmt == null) return false;
		boolean result =false;
		try
		{
			preStmt.setDate(paraIndex, dt);
			result = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet preparedExecuteQuery()
	{
		if (preStmt == null) return null;
		ResultSet rs =null;
		try
		{
			preStmt.executeQuery();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public int preparedExecuteUpdate()
	{
		if (preStmt == null) return -1;
		int rs = -1;
		try
		{
			rs=preStmt.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return rs;
		
	}
	
	public boolean commit()
	{
		boolean result = false;
		try
		{
			if (!conn.getAutoCommit())
			{
				conn.commit();
				result = true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean rollback()
	{
		boolean result = false;
		try
		{
			if (!conn.getAutoCommit())
			{
				conn.rollback();
				result = true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public void setAutoCommit(boolean auto)
	{
		try
		{
			conn.setAutoCommit(auto);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public ResultSet executeQuery(String sql)
	{
		ResultSet rs = null;
		Statement stmt;
		try
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			//stmt.close();
			/*
			System.out.println("instatementmanager");
			if (rs!=null){
				rs.first();
				System.out.println(rs.getString(1));
				System.out.println("instatementmanager");
			}
			*/
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return rs;
	}

	public void executeUpdate(String sql)
	{
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void execute(String sql)
	{
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return conn;
	}
	
	/**
	 * 使用完毕后及时关闭数据库连接。强烈建议使用完毕后调用此方法。
	 */
	public void close()
	{
		try
		{
		if(preStmt!=null)
		{
			preStmt.close();
			preStmt = null;
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
		if (conn!=null)
		{
			conn.close();
			conn = null;
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
