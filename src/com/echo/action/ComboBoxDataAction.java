package com.echo.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.echo.service.ComboBoxService;
import com.echo.util.StringUtil;

public class ComboBoxDataAction {

	private ComboBoxService comboBoxService;
	public void setComboBoxService(ComboBoxService comboBoxService) {
		this.comboBoxService = comboBoxService;
	}
	
	
	

	

	HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
	/**
	 * 获取下拉列表标识数据
	 * args 参数为ALL时， 获取全部数据并带有全部字段用于收索条件，否则获取全部数据不带全部字段
	 * @return
	 * @throws Exception
	 */
	public String searchSwitchInflg() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
		HttpServletResponse response=ServletActionContext.getResponse();//响应对象
		response.setCharacterEncoding("utf-8");
		response.setHeader("ContentType","text/xml");
		PrintWriter out = response.getWriter();
		String args = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("args")));
		JSONArray arr = new JSONArray();
		try {
			arr = comboBoxService.searchSwitchInflg();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if(args != null && !"".equals(args) && "ALL".equals(args)){
			JSONObject obj = new JSONObject();
			obj.put("id", "999");
			obj.put("text", "全部");
			arr.add(obj);
		}
		out.println(arr);
		return null;

	}
}
