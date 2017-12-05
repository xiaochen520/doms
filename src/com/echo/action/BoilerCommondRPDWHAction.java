package com.echo.action;


	import com.opensymphony.xwork2.ActionSupport;

public class BoilerCommondRPDWHAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	/*private static final long serialVersionUID = 1L;
	private  BoilerCommondRPDWHService boilerCommondRPDWHService;

	public BoilerCommondRPDWHService getBoilerCommondRPDWHService() {
		return boilerCommondRPDWHService;
	}

	public void setBoilerCommondRPDWHService(
			BoilerCommondRPDWHService boilerCommondRPDWHService) {
		this.boilerCommondRPDWHService = boilerCommondRPDWHService;
	}
	

		private InputStream excelFile = null;
		private LogService logService;

		public void setLogService(LogService logService) {
			this.logService = logService;
		}
		
		public String getFileName() {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
			String downloadFileName = (sf.format(new Date()).toString())+ "普通湿热锅炉日报.xls";
			try {
				downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return downloadFileName;
		}

		public InputStream getExcelFile() {
			return excelFile;
		}
		HttpSession session = ServletActionContext.getRequest().getSession(true);// 返回会话
		@SuppressWarnings("unchecked")
		public String searchSRGLRB() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			String oil = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("oilationname")));
			String areablock = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("areablock")));
			String station = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("blockstationname")));
			String ghname = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ghname")));
			String boilersName = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilersName")));
			String stime=StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("date")));
			String etime=StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("date1")));
			String totalNum = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("totalNum")));
			if("".equals(oil)  && "".equals(areablock) 
					 && "".equals(station) && "".equals(ghname) && "".equals(boilersName)
					 && "".equals(stime) && "".equals(etime) && "".equals(totalNum)){
				out.println("");
				return null;
			}
			if(stime.equals("")||stime.equals("undefined")||stime==null){
				Date date=new Date();
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				stime=sf.format(date);
				
			}
			if(etime.equals("")||etime.equals("undefined")||etime==null){
				Date date=new Date();
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				etime=sf.format(date); 
				
			}
			
			int pageNo = 1; //页索引参数名当前页
			String sort = "";		//页排序列名
			String order = "";//页排序方向
			int rowsPerpage = 0; //每页显示条数
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
			HashMap<String,Object> dataMap = null;
			try {
				dataMap = boilerCommondRPDWHService.searchRPData(oil,areablock,station,ghname,boilersName,stime,etime,pageNo,sort,order,rowsPerpage,totalNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//返回条数
			if ("totalNum".equals(totalNum)) {
				Object total = dataMap.get("totalNum");
				out.println(total);
				out.flush();
				out.close();
				return null;
			}
			
			Object jsonobj = dataMap.get("json");
			//返回显示数据
			if(jsonobj != null && "".equals(totalNum)){
				out.println(jsonobj);
				out.flush();
				out.close();
			}
			else {//导出报表
				response.resetBuffer();
				response.reset();
				List<Object[]> dataLsit = (List<Object[]>) dataMap.get("list");
				String templetFilePath = request.getSession().getServletContext().getRealPath("/") + "exceltemplet\\日报数据.xls";
//				将用户对象列表输出到workbook的流中，ExcelUtil类进行了封装，后续分析！
				java.io.ByteArrayOutputStream baos = DynamicDataExportUtil.dailyDataExporReport(dataLsit,templetFilePath,"普通湿热锅炉日报");
				if(baos != null){
					byte[] ba = baos.toByteArray();
					excelFile = new ByteArrayInputStream(ba);
					baos.flush();
					baos.close();
				}
				return "excel";
			}
			
			return null;

		}
		
		public String searchPTglview() throws Exception{
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			String outCode = "1";
			String rbid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rbid")));
			String[] cloumnsName = {"BOILER_NAME","CJSJS","GLBH","GRZH","OILDRILLING_STATION_NAME","BLOCKSTATION_NAME","YXZ","AREABLOCK",
					"HL","SL","LJSL_4","LJSL_5","RHYYL","LTYL","NQYL","YZWD","GBWD","YQWD","RFWD","KRQTBJ","BJKYL","BCKYL","BCKWD","DLDJY",
					"DLDJW","DLDCY","DLDCW","FSDJY","FSDJW","ZQWD","ZQYL","TRQFQ","TRQFH","JLYL","TRQLL","LLRQL","BJKWD","LHQND","PZGYL",
					"DAILY_CUMULATIVE_GAS","DAILY_CUMULATIVE_WATER"
					};
			List<Object[]> datas = new ArrayList<Object[]>();
			if(!"".equals(rbid)){
				try {
					datas  = boilerCommondRPDWHService.searchboilerRPDview(rbid,cloumnsName);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-10004";
				}
			//数据错误
			}else{
				
			}
			JSONObject gridJson = null;
			
//			JSONArray arr = new JSONArray();
			if("1".equals(outCode) && datas != null && datas.size()>0){
				gridJson = new JSONObject();
				for(int i = 0; i <cloumnsName.length;i++){
					
					if(datas.get(0)[i] != null ){
//						if("NO1CONTROL_STATUS".equals(cloumnsName[i])){
//							if("0".equals(datas.get(0)[i].toString())){
//								gridJson.put(cloumnsName[i],"手动");
//							}else{
//								gridJson.put(cloumnsName[i],"自动");
//							}
//						}else{
							gridJson.put(cloumnsName[i],datas.get(0)[i].toString());
//						}
						
					}else{
						gridJson.put(cloumnsName[i],"");
					}
					
				}
				
			}
			
			if(gridJson != null && "1".equals(outCode)){
				//System.out.println(gridJson);
				out.print(gridJson);
			}else{
				out.print(outCode);
			}
			return null;
			
		}
		
		public String getline()throws Exception{
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			String outCode = "1";
			
			String glid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("glid")));
			String prvarid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("prvarid")));
			
			String startDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("startDate")));
			String endDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("endDate")));
			
			List<Object[]> pointline = null;
			try {
				pointline = boilerCommondRPDWHService.searchboilerLine(glid,prvarid,startDate,endDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
//			JSONObject gridJson = null;
			JSONArray arr = null;
			if(pointline != null && pointline.size()>0){
				arr = new JSONArray();
				for(Object[] obj :pointline){
//					gridJson.put(obj[1], obj[0]);
//					arr.add(gridJson);
//					gridJson = new JSONObject();
					arr.add(obj);
//					gridJson.put("TUXINGS", pointline);
				}
				
			}
			 
			if("1".equals(outCode)){
				out.print(arr);
			}else{
				out.print(outCode);
			}
			return null;
		}
		
		//根据权限进行页面初始化
		public String pageInit()throws Exception{
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			String outCode = "1";
			//根据用户权限生成用户权限生成页面布局格式
			User user = (User) session.getAttribute("userInfo");
//			
			String gridJson = null;
			try {
				gridJson = AuthorityUtil.getGridJson("过热锅炉日报维护", user, PageVariableUtil.SRGLCOMMOND_RPD_PAGE_GRID);
			} catch (Exception e) {
				e.printStackTrace();
				outCode = "-10004";
			}
			
			if(gridJson != null && "1".equals(outCode)){
				out.print(gridJson);
			}else{
				out.print(outCode);
			}
			return null;
		}
		
		
		public String pageInitSearchRPDWH()throws Exception{
			
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			
			//根据用户权限生成用户权限生成页面布局格式
			User user = (User) session.getAttribute("userInfo");
			//普通湿热锅炉日报维护数据
			String gridJson = AuthorityUtil.getGridJson("过热锅炉日报维护", user, PageVariableUtil.SRGLCOMMONDWH_RPD_PAGE_GRID);
//			System.out.println(gridJson);
			out.print(gridJson);
			return null;
		}
		
		public String SaveOrUpadateBoilerCommondRPDWH()throws Exception{
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			List<PcRpdBoilerCommondT> pbcList = null;
			Object[] rpdobj = null;
			PcRpdBoilerCommondT psgl = null;
			
			String outCode = "1";
			boolean addflg = true;
			String commondid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boiler_commondid")));
			String boilerName  =StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("glmc")));
			String boilerid = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("boilerid")));
			String reportDate = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("report_date")));
			if( reportDate !=null && !"".equals(reportDate)){
			}
			//修改
			if(commondid !=null && !"".equals(commondid)){
				try {
					pbcList = boilerCommondRPDWHService.searchCommobs("" ,boilerid, "",reportDate );
					if(pbcList != null && pbcList.size()>0){
						if(pbcList.size() == 1){
							//要修改为本身数据运行修改
							if(commondid.equals(pbcList.get(0).getBoilerCommondid())){
								psgl = pbcList.get(0);
							//数据库中存在另一条该锅炉日报记录不许修改
							}else{
								outCode = "-14206";
							}
						//要修改数据数据库中存在多条
						}else if(pbcList.size() >1){
							
							outCode = "-14207";
						}
						
					//要修改数据，数据库中不存在允许修改
					}else{
						pbcList = boilerCommondRPDWHService.searchCommobs(commondid ,"", "","" );
						psgl = pbcList.get(0);
						
					}
				} catch (Exception e) {
					e.printStackTrace();
					//普通湿热锅炉日报维护信息获取失败
					outCode = "-14201";
				}

			//添加	
			}else{
				try {
					pbcList = boilerCommondRPDWHService.searchCommobs("" ,boilerid, "",reportDate);
					//数据库中存在此记录不允许用户添加新纪录
					if(pbcList != null && pbcList.size()>0){
						outCode = "-14208";
					//数据库中不存在记录允许用户添加新纪录
					}else{
						psgl = new PcRpdBoilerCommondT();
					}
				} catch (Exception e) {
					e.printStackTrace();
					//普通湿热锅炉日报维护信息获取失败
					outCode = "-14201";
				}
			}
			
			  if("1".equals(outCode)){
				psgl.setBoilerid(boilerid);
				psgl.setBlockstationName(StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("grzh2").trim())));
				psgl.setBoilerName(boilerName);
				//psgl.setReportDate(DateBean.getStrDate(request.getParameter("report_date").trim()));
				psgl.setReportDate(DateBean.getStrDate(reportDate));
				
				String hl  = StringUtil.toStr(StringUtil.toStr(request.getParameter("hl")));
				if(hl !=null && !"".equals(hl)){
					psgl.setHl(Byte.parseByte(hl)); //火量
				}else{
					psgl.setHl(Byte.parseByte(null));
				}
				
				String sl = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("sl")));
				if( sl !=null && !"".equals(sl) ){
					psgl.setSl(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("sl").trim()))); //进水流量
				}else{
					psgl.setSl(null); 
				}

				String ljsl4 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ljsl_4")));
				if(ljsl4 !=null && !"".equals(ljsl4) ){
					psgl.setLjsl4(java.math.BigDecimal.valueOf(Double.parseDouble(ljsl4))); //累计水量低四位
				}else{
					psgl.setLjsl4(null);
				}
				
				String ljsl5 = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ljsl_5")));
				if(ljsl5 !=null && !"".equals(ljsl5) ){
					psgl.setLjsl5(Integer.parseInt(ljsl5)); //累计水量高五位
				}else{
					psgl.setLjsl5(null);
				}

				String  rhyyl= StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rhyyl")));
				if( rhyyl!=null && !"".equals(rhyyl) ){
					psgl.setRhyyl(java.math.BigDecimal.valueOf(Double.parseDouble(rhyyl)));  //润滑油压力
				}else{
					psgl.setRhyyl(null);  
				}

				String  ltyl= StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("ltyl")));
				if(ltyl !=null && !"".equals(ltyl) ){
					psgl.setLtyl(java.math.BigDecimal.valueOf(Double.parseDouble(ltyl)));   //炉膛压力
				}else{
					psgl.setLtyl(null); 
				}
				String nqyl = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("nqyl")));
				if(nqyl !=null && !"".equals(nqyl) ){
					psgl.setNqyl(java.math.BigDecimal.valueOf(Double.parseDouble(nqyl)));  //暖气压力
				}else{
					psgl.setNqyl(null); 
				}

				String yzwd = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("yzwd")));
				if( yzwd!=null && !"".equals(yzwd) ){
					psgl.setYzwd(java.math.BigDecimal.valueOf(Double.parseDouble(yzwd)));  //油嘴温度
				}else{
					psgl.setYzwd(null); 
				}

				String gbwd = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("gbwd")));
				if(gbwd !=null && !"".equals(gbwd) ){
					psgl.setGbwd(java.math.BigDecimal.valueOf(Double.parseDouble(gbwd)));  //管壁温度
				}else{
					psgl.setGbwd(null); 
				}

				String yqwd = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("yqwd")));
				if(yqwd !=null && !"".equals(yqwd) ){
					psgl.setYqwd(java.math.BigDecimal.valueOf(Double.parseDouble(yqwd))); //烟气温度
				}else{
					psgl.setYqwd(null); 
				}

				String rfwd = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("rfwd")));
				if(rfwd !=null && !"".equals(rfwd) ){
					psgl.setRfwd(java.math.BigDecimal.valueOf(Double.parseDouble(rfwd)));  //热风温度
				}else{
					psgl.setRfwd(null); 
				}

				String krqtbj = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("krqtbj")));
				if(krqtbj !=null && !"".equals(krqtbj) ){
					psgl.setKrqtbj(java.math.BigDecimal.valueOf(Double.parseDouble(krqtbj))); //可燃气体报警值
				}else{
					psgl.setKrqtbj(null);
				}

				String bjkyl = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("bjkyl")));
				if( bjkyl!=null && !"".equals(bjkyl) ){
					psgl.setBjkyl(java.math.BigDecimal.valueOf(Double.parseDouble(bjkyl)));    //泵进口压力
				}else{
					psgl.setBjkyl(null);
				}

				String bckyl = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("bckyl")));
				if(bckyl !=null && !"".equals(bckyl) ){
					psgl.setBckyl(java.math.BigDecimal.valueOf(Double.parseDouble(bckyl)));  //泵出口压力
				}else{
					psgl.setBckyl(null);
				}
				String bckwd = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("bckwd")));
				if(bckwd !=null && !"".equals(bckwd) ){
					psgl.setBckwd(java.math.BigDecimal.valueOf(Double.parseDouble(bckwd)));   //泵出口温度
				}else{
					psgl.setBckwd(null);  
				}
				String dldjy = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dldjy")));
				if(dldjy !=null && !"".equals(dldjy) ){
					psgl.setDldjy(java.math.BigDecimal.valueOf(Double.parseDouble(dldjy)));  //对流段进口压力
				}else{
					psgl.setDldjy(null); 
				}

				String dldjw = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dldjw")));
				if(dldjw !=null && !"".equals(dldjw) ){
					psgl.setDldjw(java.math.BigDecimal.valueOf(Double.parseDouble(dldjw)));   //对流段进口温度
				}else{
					psgl.setDldjw(null);
				}

				String dldcy = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dldcy")));
				if(dldcy !=null && !"".equals(dldcy) ){
					psgl.setDldcy(java.math.BigDecimal.valueOf(Double.parseDouble(dldcy)));  //对流段出口压力
				}else{
					psgl.setDldcy(null);
				}

				String dldcw = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("dldcw")));
				if(dldcw !=null && !"".equals(dldcw) ){
					psgl.setDldcw(java.math.BigDecimal.valueOf(Double.parseDouble(dldcw)));  //对流段出口温度
				}else{
					psgl.setDldcw(null);
				}

				String fsdjy = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("fsdjy")));
				if(fsdjy !=null && !"".equals(fsdjy) ){
					psgl.setFsdjy(java.math.BigDecimal.valueOf(Double.parseDouble(fsdjy))); //辐射段进口压力
				}else{
					psgl.setFsdjy(null);
				}

				String fsdjw = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("fsdjw")));
				if(fsdjw !=null && !"".equals(fsdjw) ){
					psgl.setFsdjw(java.math.BigDecimal.valueOf(Double.parseDouble(fsdjw)));  //辐射段进口温度
				}else{
					psgl.setFsdjw(null);
				}

				String zqwd = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("zqwd")));
				if(zqwd !=null && !"".equals(zqwd) ){
					psgl.setZqwd(java.math.BigDecimal.valueOf(Double.parseDouble(zqwd)));    //蒸汽温度
				}else{
					psgl.setZqwd(null); 
				}

				String zqyl = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("zqyl")));
				if( zqyl!=null && !"".equals(zqyl) ){
					psgl.setZqyl(java.math.BigDecimal.valueOf(Double.parseDouble(zqyl)));   //蒸汽压力
				}else{
					psgl.setZqyl(null);
				}

				String trqfq = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("trqfq")));
				if(trqfq !=null && !"".equals(trqfq) ){
					psgl.setTrqfq(java.math.BigDecimal.valueOf(Double.parseDouble(trqfq)));  //天然气阀前压力
				}else{
					psgl.setTrqfq(null); 
				}

				String trqfh = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("trqfh")));
				if(trqfh !=null && !"".equals(trqfh) ){
					psgl.setTrqfh(java.math.BigDecimal.valueOf(Double.parseDouble(trqfh))); //天然气阀后压力
				}else{
					psgl.setTrqfh(null);
				}

				String  jlyl= StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("jlyl")));
				if( jlyl!=null && !"".equals(jlyl) ){
					psgl.setJlyl(java.math.BigDecimal.valueOf(Double.parseDouble(jlyl)));   //检漏压力
				}else{
					psgl.setJlyl(null); 
				}

				String  trqll= StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("trqll")));
				if(trqll !=null && !"".equals(trqll) ){
					psgl.setTrqll(java.math.BigDecimal.valueOf(Double.parseDouble(trqll)));  //天然气流量
				}else{
					psgl.setTrqll(null);
				}

				String llrql = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("llrql")));
				if( llrql!=null && !"".equals(llrql) ){
					psgl.setLlrql(java.math.BigDecimal.valueOf(Double.parseDouble(llrql))); //累计气量
				}else{
					psgl.setLlrql(null);
				}

				String  bjkwd= StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("bjkwd")));
				if(bjkwd !=null && !"".equals(bjkwd) ){
					psgl.setBjkwd(java.math.BigDecimal.valueOf(Double.parseDouble(bjkwd)));  //泵进口温度
				}else{
					psgl.setBjkwd(null);
				}

				String lhqnd = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("lhqnd")));
				if(lhqnd !=null && !"".equals(lhqnd)){
					psgl.setLhqnd(java.math.BigDecimal.valueOf(Double.parseDouble(lhqnd)));//硫化氢浓度
				}else{
					psgl.setLhqnd(null);
				}

				String pzgyl = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("pzgyl")));
				if(pzgyl !=null && !"".equals(pzgyl) ){
					psgl.setPzgyl(java.math.BigDecimal.valueOf(Double.parseDouble(pzgyl))); //膨胀管压力
				}else{
					psgl.setPzgyl(null);
				}

				String dailyCumulativeGas = StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("daily_cumulative_gas")));
				if(dailyCumulativeGas !=null && !"".equals(dailyCumulativeGas) ){
					psgl.setDailyCumulativeGas(java.math.BigDecimal.valueOf(Double.parseDouble(dailyCumulativeGas))); //燃气日累计
				}else{
					psgl.setDailyCumulativeGas(null);
				}

				String  dailyCumulativeWater= StringUtil.toStr(StringUtil.isNullUtil(request.getParameter("")));
				if(dailyCumulativeWater !=null && !"".equals(dailyCumulativeWater) ){
					psgl.setDailyCumulativeWater(java.math.BigDecimal.valueOf(Double.parseDouble(dailyCumulativeWater)));//给水日累计量
				}else{
					psgl.setDailyCumulativeWater(null);
				}
				
//				psgl.setHl(Byte.parseByte(request.getParameter("hl").trim())); //火量
//				psgl.setSl(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("sl").trim())); //进水流量
//				psgl.setLjsl4(Short.parseShort(request.getParameter("ljsl_4").trim())); //累计水量低四位
//				psgl.setLjsl5(Integer.parseInt(request.getParameter("ljsl_5").trim())); //累计水量高五位
//				psgl.setRhyyl(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("rhyyl").trim()));  //润滑油压力
//				psgl.setLtyl(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("ltyl").trim()));   //炉膛压力
//				psgl.setNqyl(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("nqyl").trim()));  //暖气压力
//				psgl.setYzwd(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("yzwd").trim()));  //油嘴温度
//				psgl.setGbwd(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("gbwd").trim()));  //管壁温度
//				psgl.setYqwd(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("yqwd").trim())); //烟气温度
//				psgl.setRfwd(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("rfwd").trim()));  //热风温度
//				psgl.setKrqtbj(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("krqtbj").trim())); //可燃气体报警值
//				psgl.setBjkyl(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("bjkyl").trim()));    //泵进口压力
//				psgl.setBckyl(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("bckyl").trim()));   //泵出口压力
//				psgl.setBckwd(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("bckwd").trim()));   //泵出口温度
//				psgl.setDldjy(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("dldjy").trim()));  //对流段进口压力
//				psgl.setDldjw(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("dldjw").trim()));   //对流段进口温度
//				psgl.setDldcy(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("dldcy").trim()));  //对流段出口压力
//				psgl.setDldcw(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("dldcw").trim()));  //对流段出口温度
//				psgl.setFsdjy(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("fsdjy").trim()));  //辐射段进口压力
//				psgl.setFsdjw(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("fsdjw").trim()));  //辐射段进口温度
//				psgl.setZqwd(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("zqwd").trim()));    //蒸汽温度
//				psgl.setZqyl(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("zqyl").trim()));   //蒸汽压力
//				psgl.setTrqfq(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("trqfq").trim()));  //天然气阀前压力
//				psgl.setTrqfh(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("trqfh").trim())); //天然气阀后压力
//				psgl.setJlyl(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("jlyl").trim()));   //检漏压力
//				psgl.setTrqll(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("trqll").trim()));  //天然气流量
//				psgl.setLlrql(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("llrql").trim())); //累计气量
//				psgl.setBjkwd(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("bjkwd").trim()));  //泵进口温度
//				psgl.setLhqnd(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("lhqnd").trim()));  //硫化氢浓度
//				psgl.setPzgyl(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("pzgyl").trim()));  //膨胀管压力
//				psgl.setDailyCumulativeGas(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("daily_cumulative_gas").trim())); //燃气日累计
//				psgl.setDailyCumulativeWater(java.math.BigDecimal.valueOf(Double.parseDouble(request.getParameter("daily_cumulative_water").trim())); //给水日累计量
					try {
						if(commondid != null && !"".equals(commondid)){
							addflg = boilerCommondRPDWHService.updatePsgl(psgl);
							
						}else{
							addflg = boilerCommondRPDWHService.addPsgl(psgl);
							
						}
					} catch (Exception e) {
						e.printStackTrace();
						if(commondid != null && !"".equals(commondid)){
//							//普通湿热锅炉日报维护信息更新失败
							outCode = "-14203";
						}else{
//							//普通湿热锅炉日报维护信息添加失败
							outCode = "-14204";
						}
					}
				}
			out.print(outCode);
			return null;
	}
		public String removeBoilerCommondRP() throws Exception {
			HttpServletRequest request=ServletActionContext.getRequest(); //请求对象
			HttpServletResponse response=ServletActionContext.getResponse();//响应对象
			response.setCharacterEncoding("utf-8");
			response.setHeader("ContentType","text/xml");
			PrintWriter out = response.getWriter();
			String outCode = "1";
			//String sagddid = StringUtil.toStr(request.getParameter("sagddid"));
			String commondid = StringUtil.toStr(request.getParameter("commondid"));
			boolean deleteflg = false;
			try {
				deleteflg = boilerCommondRPDWHService.removeBoilerCommondRP(commondid);
			} catch (Exception e) {
				//this.addFieldError("errermsg", "删除采油站信息失败~");
				e.printStackTrace();
				//普通湿热锅炉日报维护信息删除失败
				outCode = "-14211";
			}
			if(deleteflg){
				
				//添加系统LOG
				try {
					User user1 = (User) session.getAttribute("userInfo");
					PcRdLoginfoT log = CommonsUtil.getLogInfo(user1, "2", "普通湿热锅炉基础信息", commondid);
					logService.addLog(log);
				} catch (Exception e) {
					e.printStackTrace();
					outCode = "-10003";
				}
			}
			out.print(outCode);
			return null;
		}*/
	}

