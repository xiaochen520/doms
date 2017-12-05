package com.echo.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.dto.User;
import com.echo.service.LogService;
import com.echo.util.AuthorityUtil;
import com.echo.util.PageVariableUtil;
import com.echo.util.StringUtil;

public class LogAction{
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	
	//根据权限进行页面初始化
	@SuppressWarnings("unused")
	public String pageInit()throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		JSONArray jsonArr = new JSONArray();
		JSONObject tjo = new JSONObject();
		//根据用户权限生成用户权限生成页面布局格式
		User user = (User) session.getAttribute("userInfo");
		String outCode = "1";
		
		String gridJson = null;
		try {
			gridJson = AuthorityUtil.getGridJson("日志信息", user, PageVariableUtil.LOGINFO_PAGE_GRID);
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-16001";
			
		}
		
		tjo.put("GRIDINFO", gridJson);
		List<Object[]> userlist =  null;
		try {
			userlist = logService.searchUsername();
		} catch (Exception e) {
			e.printStackTrace();
			outCode = "-15801";
		}
		
		Object[] obj = null;
		if(userlist != null && userlist.size()>0){
			obj = new Object[userlist.size()];
			for(int i =0 ;i <userlist.size() ; i ++){
				if(userlist.get(i) != null && userlist.get(i)[3] != null ){
					obj[i] = String.valueOf(userlist.get(i)[3]);
				}else{
					obj[i] = "";
				}
				
			}
			
		}else{
			
			obj = new Object[]{""};
		}
		tjo.put("USERNAMES", obj);
		jsonArr.add(tjo);
		
		if(jsonArr != null && "1".equals(outCode)){
			out.print(jsonArr);
		}else{
			out.print(outCode);
		}
		
		return null;
	}

	public String seachLog() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			String outCode = "1";
            
            String	username =""; //用戶名
            String	logtype = ""; //日志类型
            String	logdate = ""; //时间 
    		int pageNo = 1; //页索引参数名当前页
    		String sort = "";		//页排序列名
    		String order = "";//页排序方向
    		int rowsPerpage = 0; //每页显示条数
    		if(request.getParameter("username") != null){
    			username = StringUtil.toStr(request.getParameter("username").trim());	
    		}
    		
    		if(request.getParameter("logtype") != null){
    			logtype = StringUtil.toStr(request.getParameter("logtype").trim());	
    		}
    		
    		if(request.getParameter("logdate") != null){
    			logdate = request.getParameter("logdate").trim();	
    		}
    		
    		if(request.getParameter("pageNo") != null && !"".equals(request.getParameter("pageNo"))){
    			pageNo = Integer.parseInt(request.getParameter("pageNo").trim());	
    		}
    		
    		if(request.getParameter("sort") != null){
    			sort = request.getParameter("sort").trim();
    		}
    		
    		if(request.getParameter("order") != null){
    			order = request.getParameter("order").trim();
    		}
    		
    		if( request.getParameter("rowsPerpage") != null && !"".equals( request.getParameter("rowsPerpage"))){
    			rowsPerpage = Integer.parseInt(request.getParameter("rowsPerpage").trim());
    		}
			// user.setUserPhoto(name)
    		JSONObject jsonobj = null;
    		try {
    			jsonobj = logService.searchLog(username,logtype,logdate,pageNo,sort,order,rowsPerpage);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
			if(jsonobj != null && "1".equals(outCode)){
				out.print(jsonobj);
			}else{
				out.print(outCode);
			}
			
		return null;
	}

}
