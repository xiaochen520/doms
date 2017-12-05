package com.echo.util;

/**
 * 系统页面表格默认格式常量类
 * 
 * @author yanlong
 * 
 */
public class PageVariableUtil {
	// 用户信息管理
	public static final String USERINFO_PAGE_GRID = " columns: [ "+
								"{ display: '用户ID', name: 'USERID', align: 'left', width: 200, minWidth: 60 ,hide: true}, "+
								"{ display: '部门ID', name: 'DEPID', align: 'left', width: 200, minWidth: 60 ,hide: true}, "+
								"{ display: '角色ID', name: 'ROLEID', align: 'left', width: 200, minWidth: 60 ,hide: true}, "+
								"{ display: '操作员', name: 'OPER_CODE',align: 'left', minWidth: 120 ,frozen:true}, "+
								"{ display: '操作员姓名', name: 'OPER_NAME',align: 'left', minWidth: 120}, "+
								"{ display: '部门', name: 'DEPARTMENT'  ,align: 'center', minWidth: 120},  "+
								"{ display: '角色', name: 'ROLES'  ,align: 'center', minWidth: 120},"+
								"{ display: '口令', name: 'OPER_PASS',align: 'left', minWidth: 120 ,hide: true},"+
								"{ display: '当前状态', name: 'OPER_SDBSADBSE',align: 'center', minWidth: 60,hide:true}, "+
								"{ display: '注册日期 ', name: 'AUDBSHOR_DATE' ,align: 'center', format:'yyyy-MM-dd hh:mm:ss',minWidth: 170}, "+
								"{ display: '状态改变日期', name: 'SDBSADBSE_DATE'  ,align: 'center', format:'yyyy-MM-dd hh:mm:ss', minWidth: 170},"+
								"{ display: '最后登录时间', name: 'L_LOGIN_DATE'  ,align: 'center', format:'yyyy-MM-dd hh:mm:ss', minWidth: 170},"+
								"{ display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', format:'yyyy-MM-dd hh:mm:ss', minWidth: 120}, "+
								"{ display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', format:'yyyy-MM-dd hh:mm:ss', minWidth: 170},"+
								"{ display: '备注', name: 'REMARK',align: 'center',minWidth: 150} ],"+
								"height:'100%',"+
								"dataAction: 'server',"+
								"url:'user_seachUser.action',"+
								"pageParmName :'pageNo',"+
								"sortnameParmName:'sort',"+
								"sortorderParmName: 'order',  "+
								"pagesizeParmName:'rowsPerpage', "+
								"selectRowButtonOnly:true,"+
								"sePaper:true,"+
								"pageSize:30 ,"+
								"rownumbers:true ,"+
								"frozen:true, "+
								"checkbox :false, "+
								"onSelectRow: function (rowdata, rowindex) "+
				 "{fromAu(rowdata);} ";

	// 角色信息管理
	public static final String ROLEINFO_PAGE_GRID = " columns: [ "+
								"{ display: '角色编码', name: 'ROLEID', align: 'left', width: 200, minWidth: 60 ,hide: true}, "+
								"{ display: '角色名称', name: 'ROLE_NAME',align: 'left', minWidth: 120 ,frozen:true}, "+
								"{ display: '角色描述', name: 'ROLE_DESCRIBED',align: 'left', minWidth: 120}, "+
								"{ display: '修改时间', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 160},  "+
								"{ display: '修改人', name: 'RLAST_OPER'  ,align: 'center', minWidth: 120},"+
								"{ display: '备注', name: 'REMARK',align: 'center',minWidth: 150} ],"+
								"height:'100%',"+
								"data:'',"+
								"pageSize:30 ,"+
								"rownumbers:true,"+
								"frozen:true,"+
								"checkbox :false, "+
								"onSelectRow: function (rowdata, rowindex)"+
				 "{fromAu(rowdata);}";
	// 权限信息管理
	public static final String RIGHTINFO_PAGE_GRID = "height:'100%',"
			+ " columns: ["
			+ "  { display: '权限ID', name: 'RIGHTID', align: 'left', width: 200, minWidth: 60 ,hide: true},"
			+ " { display: '显示功能菜单', name: 'MENUID', align: 'left', minWidth: 160},"
			+ " { display: '权限名称', name: 'RIGHTNAME', minWidth: 120},"
			+ " { display: '所在页面', name: 'INPAGE', minWidth: 160},"
			+ " { display: '不显示列名', name: 'DISPALYCOLUMN', minWidth: 200},"
			+ " { display: '组织结构节点类型', name: 'STRUCTURENODE' , minWidth: 70},"
			+ " { display: '权限说明', name: 'RIGHTDESCRIBE'  , align: 'center', minWidth: 170},"
			+ " { display: '备注', name: 'BZ', minWidth: 150}"
			+ " ],pageSize:30 ,rownumbers:true,checkbox :false,data:'',"+
			" onDblClickRow: function(data, rowindex, rowobj) {" +

			"		$.ligerDialog.open({" +
			" 			title: '查看权限信息'," +
			"  			url:'viewright.jsp?rightid='+data.RIGHTID," +
			"  			width: 650," +
			"  			height: 300" +
			"  			});" +
		 " } ";
	// 日志信息管理
	public static final String LOGINFO_PAGE_GRID = " columns: ["
			+ "  { display: '日志ID', name: 'LOGID', align: 'left', width: 200, minWidth: 60 ,hide: true},"
			+ " { display: '操作人', name: 'RLAST_OPER',align: 'left', minWidth: 120 ,frozen:true},"
			+ " { display: '操作类别', name: 'OPERATING_CLASS',align: 'center', minWidth: 100},"
			+ " { display: '操作对象', name: 'OPERAND',align: 'center', minWidth: 120},"
			+ " { display: '操作时间 ', name: 'OPERATION_TIME' ,align: 'center', minWidth: 170},"
			+ " { display: 'IP地址', name: 'IP_ADDRESS'  ,align: 'center', minWidth: 120},"
			+ " { display: '信息ID', name: 'INFOID',align: 'center',minWidth: 360},"
			+ " { display: '修改前信息', name: 'MINFORMATION_BEF',align: 'center',minWidth: 360},"
			+ " { display: '修改后信息', name: 'MINFORMATION_AFT',align: 'center',minWidth: 360}"
			+ " ],"
			+ "height:'100%',dataAction: 'server',"
			+ "url:'log_seachLog.action',"
			+ "frozen:true, "
			+ " pageParmName :'pageNo',  sortnameParmName:'sort', sortorderParmName: 'order',  pagesizeParmName:'rowsPerpage', "
			+ "selectRowButtonOnly:true,sePaper:true,pageSize:30 ,rownumbers:true,checkbox :false";
	// 班组基础信息
	public static final String TEAM_PAGE_GRID = " columns: [ "+
								"{ display: '班组名称', name: 'TEAM',align: 'left', minWidth: 120}, "+
								"{ display: '所属机构', name: 'DEPARTMENT_NAME',align: 'left', minWidth: 120}, "+
								"{ display: '备注', name: 'REMARK',align: 'center',minWidth: 150}, " +
								"{ display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center',  minWidth: 100}, "+
								"{ display: '上次操作日期 ', name: 'RLAST_ODATE' ,align: 'center', format:'yyyy-MM-dd hh:mm:ss',minWidth: 130}, "+
								"{ display: '班组ID', name: 'TEAM_ID', align: 'left', width: 200, minWidth: 60 ,hide: true}, "+
								"{ display: '组织结构ID', name: 'ORG_ID', align: 'left', width: 200, minWidth: 60 ,hide: true},"+
								"{ display: '所属机构ID', name: 'PID', align: 'left', width: 200, minWidth: 60 ,hide: true}"+
								"],"+
								"height:'100%',"+
								"dataAction: 'server',"+
								"url:'team_seachDeps.action',"+
								"pageParmName :'pageNo',"+
								"sortnameParmName:'sort',"+
								"sortorderParmName: 'order',  "+
								"pagesizeParmName:'rowsPerpage', "+
								"selectRowButtonOnly:true,"+
								"sePaper:true,"+
								"pageSize:30 ,"+
								"rownumbers:true ,"+
								//"frozen:true, "+
								"checkbox :false, "+
								"onSelectRow: function (rowdata, rowindex) "+
				 "{fromAu(rowdata);} ";
	// 部门基础信息
	public static final String DEPARTMENT_PAGE_GRID = " columns: [ "+
								"{ display: '部门ID', name: 'DEPARTMENTID', align: 'left', width: 200, minWidth: 60 ,hide: true}, "+
								"{ display: '组织结构ID', name: 'ORG_ID', align: 'left', width: 200, minWidth: 60 ,hide: true}, "+
								"{ display: '部门名称', name: 'DEPARTMENT_NAME',align: 'left', minWidth: 120 ,frozen:true}, "+
								"{ display: '部门领导', name: 'DEPARTMENT_HEADER',align: 'left', minWidth: 120}, "+
								"{ display: '部门地址', name: 'DEPARTMENT_ADDRESS'  ,align: 'center', minWidth: 120},  "+
								"{ display: '部门电话', name: 'DEPARTMENT_PHONE'  ,align: 'center', minWidth: 120},"+
								"{ display: '上次操作日期 ', name: 'RLAST_ODATE' ,align: 'center', format:'yyyy-MM-dd hh:mm:ss',minWidth: 170}, "+
								"{ display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', format:'yyyy-MM-dd hh:mm:ss', minWidth: 120}, "+
								"{ display: '备注', name: 'REMARK',align: 'center',minWidth: 150} ],"+
								"height:'100%',"+
								"dataAction: 'server',"+
								"url:'dep_seachDeps.action',"+
								"pageParmName :'pageNo',"+
								"sortnameParmName:'sort',"+
								"sortorderParmName: 'order',  "+
								"pagesizeParmName:'rowsPerpage', "+
								"selectRowButtonOnly:true,"+
								"sePaper:true,"+
								"pageSize:30 ,"+
								"rownumbers:true ,"+
								"frozen:true, "+
								"checkbox :false, "+
								"onSelectRow: function (rowdata, rowindex) "+
				 "{fromAu(rowdata);} ";
	public static final String PCD_PAGE_GRID = "height:'100%',"
			+ "  columns: [	"
			+ " { display: 'PCDID', name: 'PCDID', minWidth: 120 , minWidth: 100,hide: true},"
			+ "  { display: '排采队名称', name: 'P01', minWidth: 120 , minWidth: 100},"
			+ " { display: '排采队描述', name: 'P02', minWidth: 150 , minWidth: 100},"
			+ "  { display: '负责人', name: 'P04', minWidth: 110 , minWidth: 80},"
			+ "{ display: '电话', name: 'P05', minWidth: 110 , minWidth: 80},"
			+ "  { display: '备注', name: 'BZ', minWidth: 80}"
			+ "], pageSize:30 ,rownumbers:true,checkbox :false,data:'',"
			+ " onDblClickRow: function(data, rowindex, rowobj) {"
			+ " $.ligerDialog.open({ title: '查看排采队信息',"
			+ " 			url:'viewpcd.jsp?pcdid='+data.PCDID,width: 600,"
			+ " 			height: 450" + " 		}); } ";

	public static final String OIL_PAGE_GRID = " columns: ["
			+ " { display: '油井ID', name: 'structureid', align: 'left', width: 100, minWidth: 360,hide: true},"
			+ " { display: '井号', name: 'P01',width: 80, minWidth: 70 ,align:'left',frozen:true},"
			+ "{ display: '层位', name: 'P03',width: 80, minWidth: 70,align:'center'},"
			+ "{ display: '煤层号', name: 'P64',width: 120, minWidth: 100 ,align:'center'},"
			+ "{ display: '生产井段', name: 'P65',width: 80, minWidth: 380 ,align:'center'},"
			+

			" { display: '投产日期', name: 'P08',width: 120, minWidth: 170 ,align:'center'},"
			+

			" { display: '泵深', name: 'P17',width: 80, minWidth: 60 ,align:'center'},"
			+

			" { display: '电机型号', name: 'P59',width: 100, minWidth: 60}"
			+ " ], "
			+ "height:'100%',dataAction: 'server',"
			+ "url:'qj_searchQjInfo.action',"
			+ " pageParmName :'pageNo',  sortnameParmName:'sort', sortorderParmName: 'order',  pagesizeParmName:'rowsPerpage', "
			+ "selectRowButtonOnly:true,sePaper:true,pageSize:30 ,rownumbers:true,fixedCellHeight:true,frozen:true,checkbox :false,"
			+ " onDblClickRow: function(data, rowindex, rowobj) {"

			+ " $.ligerDialog.open({" + " 			title: '查看气井信息',"
			+ " 			url:'viewqj.jsp?pcdid='+data.structureid," + " 			width: 1020,"
			+ " 			height: 500" + " 		});} ";

	public static final String JC_PAGE_GRID = " columns: [ "
			+ " { display: '组织', name: 'structureid', align: 'left', width: 100, minWidth: 60,hide: true},"
			+ "  { display: '井场名称', name: 'P01', minWidth: 120},"
			+ "  { display: '字母编码', name: 'P02', minWidth: 140},"
			+ "  { display: '所属集气站', name: 'jqz'},"
			+ "  { display: '所属排采队', name: 'pcd'},"
			+ "  { display: '计量模式', name: 'P04'},"
			+ "  { display: '井场语音', name: 'P11', minWidth: 140},"
			+ "  { display: '历史数据保存相位', name: 'P12' ,minWidth: 160},"
			+ "{ display: '备注', name: 'BZ'}"
			+

			"  ], "
			+ "height:'100%',dataAction: 'server',"
			+ "url:'jc_searchJcInfo.action',"
			+ " pageParmName :'pageNo',  sortnameParmName:'sort', sortorderParmName: 'order',  pagesizeParmName:'rowsPerpage', "
			+ "selectRowButtonOnly:true,sePaper:true,pageSize:30 ,rownumbers:true,checkbox :false,"
			+ " onDblClickRow: function(data, rowindex, rowobj) {"
			+ " $.ligerDialog.open({" + " 			title: '查看井场基本信息',"
			+ " 			url:'viewJc.jsp?structureid='+data.structureid,"
			+ " 			width: 450," + " 			height: 450" + " 		});} ";
	public static final String JQZ_PAGE_GRID = " height:'100%',"
			+ " columns: ["
			+ "  { display: 'Id', name: 'structureid', minWidth: 120 , minWidth: 100,hide: true},"
			+ " { display: '集气站名称', name: 'P01', minWidth: 120 , minWidth: 100 ,align:'center'},"
			+ " { display: '集气站描述', name: 'P02', minWidth: 150 , minWidth: 100},"
			+
			// " { display: '字母编码', name: 'P03', minWidth: 150 , minWidth: 100},"+
			" { display: '负责人', name: 'P04', minWidth: 150 , minWidth: 100},"
			+ " { display: '电话',name: 'P05', minWidth: 100 , minWidth: 60 ,align:'right'},"
			+ "  { display: '备注', name: 'BZ', minWidth: 150 , minWidth: 100}"
			+ "  ], pageSize:30 ,rownumbers:true,checkbox :false,data:'',"
			+ " onDblClickRow: function(data, rowindex, rowobj) {"

			+ " $.ligerDialog.open({" + " 			title: '查看集气站信息',"
			+ " 			url:'viewzxz.jsp?pcdid='+data.structureid," + " 			width: 350,"
			+ " 			height: 350" + " 		});} ";
	public static final String YB_PAGE_GRID = "  columns: ["
			+ "  { display: 'Id', name: 'YBID', hide: true},"
			+ "  { display: '所属单位', name: 'dw', minWidth: 80 ,align:'left',frozen:true},"
			+ "  { display: '仪表编号', name: 'P01', minWidth: 80},"
			+ "  { display: '仪表名称', name: 'P02', minWidth: 80},"
			+ "  { display: '规格型号', name: 'P03', minWidth: 80},"
			+ "  { display: '制造厂家', name: 'P04', minWidth: 80},"
			+ "  { display: '出场日期', name: 'P05',align: 'center', format:'yyyy-MM-dd hh:mm:ss', minWidth: 170},"
			+ "  { display: '投产日期', name: 'P06',align: 'center', format:'yyyy-MM-dd hh:mm:ss', minWidth: 170},"
			+ "  { display: '出厂编号', name: 'P07', minWidth: 80},"
			+ "  { display: '创建时间', name: 'P08',align: 'center', format:'yyyy-MM-dd hh:mm:ss', minWidth: 170},"
			+ "  { display: '使能', name: 'P09', minWidth: 80},"
			+ "  { display: '报警使能', name: 'P10', minWidth: 80},"
			+ "  { display: '量程上限', name: 'P11', minWidth: 80,align:'center'},"
			+ "  { display: '量程下限', name: 'P12', minWidth: 80 ,align:'center'},"
			+ "  { display: '报警上限', name: 'P13', minWidth: 80 ,align:'center'},"
			+ "  { display: '报警下限', name: 'P14', minWidth: 80 ,align:'center'},"
			+ "  { display: '单位', name: 'P15', minWidth: 80 ,align:'center'},"
			+ "  { display: '报警轨迹上限', name: 'P16', minWidth: 80 ,align:'center'},"
			+ " { display: '报警轨迹下限',name: 'P17', minWidth: 80 ,align:'center'}"
			+ "  ],"
			+ "height:'100%',dataAction: 'server',"
			+ "url:'yb_searchYb.action',"
			+ " pageParmName :'pageNo',  sortnameParmName:'sort', sortorderParmName: 'order',  pagesizeParmName:'rowsPerpage', "
			+ "selectRowButtonOnly:true,sePaper:true,pageSize:30 ,fixedCellHeight:true,frozen:true,rownumbers:true,checkbox :false,"
			+ " onDblClickRow: function(data, rowindex, rowobj) {"
			+ " $.ligerDialog.open({" + " 			title: '查看仪表信息',"
			+ " 			url:'viewyb.jsp?pcdid='+data.YBID," + " 			width: 600,"
			+ " 			height: 450" + " 		});} ";

	public static final String TXXX_PAGE_GRID = " columns: ["
			+ " { display: 'Id', name: 'TXXXID', minWidth: 120 , minWidth: 100,hide: true},"
			+ " { display: '设备厂家', name: 'P01', minWidth: 120 , minWidth: 100},"
			+ " { display: '设备类型', name: 'P02', minWidth: 150 , minWidth: 100},"
			+ "  { display: '所在单位', name: 'dw', minWidth: 150 , minWidth: 100 ,align:'center'},"
			+ "  { display: '通信方式', name: 'P03', minWidth: 150 , minWidth: 100},"
			+ "  { display: '设备IP', name: 'P04', minWidth: 150 , minWidth: 100},"
			+ "{ display: '实际通讯地址', name: 'P05', minWidth: 150 , minWidth: 100},"
			+ "  { display: 'DTUID', name: 'P06', minWidth: 150 , minWidth: 100},"
			+ "  { display: '通讯使能', name: 'P07', minWidth: 150 , minWidth: 100},"
			+ "  { display: '备注',name: 'BZ', minWidth: 100 , minWidth: 60}"
			+ "  ],"
			+ "height:'100%',dataAction: 'server',"
			+ "url:'txxx_searchTxxx.action',"
			+ " pageParmName :'pageNo',  sortnameParmName:'sort', sortorderParmName: 'order',  pagesizeParmName:'rowsPerpage', "
			+ "selectRowButtonOnly:true,sePaper:true,pageSize:30 ,rownumbers:true,checkbox :false,"
			+ " onDblClickRow: function(data, rowindex, rowobj) {"
			+ " $.ligerDialog.open({" + " 			title: '查看通讯信息',"
			+ " 			url:'viewtxxx.jsp?pcdid='+data.TXXXID," + " 			width: 600,"
			+ " 			height: 450" + " 		});} ";
	public static final String PCD_PF_PAGE_GRID = "columns: ["
			+ "{ display: 'ID', name: 'qjdtid',hide: true, columns:[]},"
			+ "{ display: '井号', name: 'p01', align: 'left', width: 80,frozen:true,columns:[]},"
			+ "{ display: '生产日期', name: 'p02', align: 'center', width: 130, columns:[]},"
			+ "{ display: '生产井段', columns:[{display:'(m)', name: 'p93', align: 'center', width: 500}]},"
			+ "{ display: '泵径/泵型',columns:[{display:'(mm)', name: 'p94', align: 'center', width: 160}]},"
			+ "{ display: '泵挂深度', columns:[{display:'(m)', name: 'p95', align: 'center', width: 80}]},"
			+ "{ display: '排采时间', columns:[{display:'(d)', name: 'p03', align: 'center', width: 80}]},"
			+ "{ display: '当日生产时间', columns:[{display:'(h)', name: 'p06', align: 'center', width: 80}]},"
			+ "{ display: '抽油机型号', name: 'p96', align: 'center', width: 140,columns:[]},"
			+ "{ display: '抽油机电机功率', columns:[{display:'(kw)', name: 'p97', align: 'center', width: 100}]},"
			+ "{ display: '冲程', columns:[{display:'(m)', name: 'p40', align: 'center', width: 80}]},"
			+ "{ display: '冲次(转数)', columns:[{display:'(r/min)', name: 'p41', align: 'center', width: 80}]},"
			+ "{ display: '泵效', columns:[{display:'(%)', name: 'p55', align: 'center', width: 80}]},"
			+ "{ display: '井底压力', columns:[{display:'(MPa)', name: 'p08', align: 'center', width: 100}]},"
			+ "{ display: '套压', columns:[{display:'(MPa)', name: 'p07', align: 'center', width: 80}]},"
			+ "{ display: '动液面', columns:[{display:'(m)', name: 'p09', align: 'center', width: 80}]},"
			+ "{ display: '日产气量', columns:[{display:'(m³)', name: 'p13', align: 'center', width: 80}]},"
			+ "{ display: '日产水量', columns:[{display:'(m³)', name: 'p19', align: 'center', width: 80}]},"
			+ "{ display: '月累计产气', columns:[{display:'(m³)', name: 'p49', align: 'center', width: 100}]},"
			+ "{ display: '月累计产水', columns:[{display:'(m³)', name: 'p52', align: 'center', width: 100}]},"
			+ "{ display: '年产气', columns:[{display:'(m³)', name: 'p50', align: 'center', width: 80}]},"
			+ "{ display: '年产水', columns:[{display:'(m³)', name: 'p53', align: 'center', width: 80}]},"
			+ "{ display: '累产气', columns:[{display:'(m³)', name: 'p51', align: 'center', width: 80}]},"
			+ "{ display: '累产水', columns:[{display:'(m³)', name: 'p54', align: 'center', width: 80}]},"
			+ "{ display: '管网回压(Mpa)', columns:" +
			"	[" +
				"	{ display: '最大值', name: 'p59', align: 'center', width: 80}," + 
				"	{ display: '最小值', name: 'p60', width: 80, align: 'center'}" +
			"	]" +
			"	}," +
			"{ display: '井台自用气', columns:[{display:'m3/d', name: 'p66', align: 'center', width: 120}]}," +
			"{ display: '氯离子', columns:[{display:'(mg/L)', name: 'p56', align: 'center', width: 80}]},"
			+ "{ display: 'pH', name: 'p57', align: 'center', width: 80,columns:[]},"
			+ "{ display: '备注', name: 'bz', align: 'center', width: 650,columns:[]}"
			+ " ],height:'100%',dataAction: 'server',"
			+ "url:'productionTeam_queryProductionTeamInfo.action',"
			+ " pageParmName :'pageNo',  sortnameParmName:'sort', sortorderParmName: 'order',  pagesizeParmName:'rowsPerpage', "
			+ "selectRowButtonOnly:true,sePaper:true,pageSize:30 ,fixedCellHeight:true,frozen:true,rownumbers:true,width: '100%',data:'',checkbox: false";

	public static final String PCD_SINGLE_PF_PAGE_GRID = "columns: ["
			+ "{ display: 'ID', name: 'qjdtid',hide: true, columns:[]},"
			+ "{ display: '日期', name: 'p02', align: 'center',frozen:true, width: 130,columns:[]},"
			+ "{ display: '工作制度', columns:"
			+ "	["
			+ "		{ display: '冲次', name: 'p41', align: 'center', width: 80}, "
			+ "		{ display: '井组电流', name: 'p39', width: 100, align: 'center'}"
			+ "	]"
			+ "	},"
			+ "{ display: '动液面(m)', name: 'p09', align: 'center', width: 60, columns:[]},"
			+ "{ display: '液面变化(m)', name: 'p61', align: 'center', width: 80,columns:[]},"
			+ "{ display: '日 产 量', columns:"
			+ "	["
			+ "		{ display: '气(m³)', name: 'p13', align: 'center', width: 100}, "
			+ "		{ display: '水(m³)', name: 'p19', width: 100, align: 'center'}"
			+ "	]"
			+ "	},"
			+ "{ display: '压 力(Mpa)', columns:"
			+ "	["
			+ "		{ display: '井底压力', name: 'p08', align: 'center', width: 100},"
			+ "		{ display: '流压变化', name: 'p62', width: 100, align: 'center'},"
			+ "		{ display: '套压', name: 'p07', width: 80, align: 'center'}"
			+ "	]"
			+ "	},"
			+ " { display: '累计产量', columns:"
			+ "	["
			+ "		{ display: '气 (m³)', name: 'p51', align: 'center', width: 100},"
			+ "		{ display: '水 (m³)', name: 'p54', width: 100, align: 'center'}"
			+ "	]"
			+ "},"
			+ "{ display: '管网回压(Mpa)', columns:" +
			"	[" +
				"	{ display: '最大值', name: 'p59', align: 'center', width: 80}," + 
				"	{ display: '最小值', name: 'p60', width: 80, align: 'center'}" +
			"	]" +
			"	}," +
			"{ display: '备注', name: 'bz', align: 'center', width:650 ,columns:[]}"
			+ "],height:'100%',dataAction: 'server',"
			+ "url:'singleWell_querySingleWellInfo.action',"
			+ " pageParmName :'pageNo',  sortnameParmName:'sort', sortorderParmName: 'order',  pagesizeParmName:'rowsPerpage', "
			+ "selectRowButtonOnly:true,sePaper:true,pageSize:30 ,fixedCellHeight:true,frozen:true,rownumbers:true,data:'',width: '100%',checkbox: false";
	public static final String QJBL_PAGE_GRID = " columns: [ "
			+ " { display: 'ID', name: 'qjdtid', align: 'left', width: 100, minWidth: 60,hide: true},"
			+ " { display: '气井号', name: 'p01', minWidth: 100 ,frozen:true},"
			+ "{ display: '采集时间', name: 'p02', minWidth: 180 ,frozen:true},"
			+

			" { display: '通讯状态', name: 'p03', minWidth: 80},"
			+ " { display: '运行状态', name: 'p04', minWidth: 80},"
			+ " { display: '今运行时(h)', name: 'p05', minWidth: 80},"
			+ " { display: '昨运行时(h)', name: 'p06' ,minWidth: 110},"
			+ " { display: '套压(MPa)', name: 'p07' , minWidth: 80},"
			+ " { display: '井底流压(MPa)', name: 'p08' , minWidth: 110},"
			+ "{ display: '动液面(m)', name: 'p09' , minWidth: 100},"
			+ " { display: '产气瞬时流量(Nm^3/h)', name: 'p12' , minWidth: 160},"
			+ " { display: '产气当日累计(Nm^3)', name: 'p13' , minWidth: 160},"
			+ " { display: '产气昨日累计(Nm^3)', name: 'p14', minWidth: 160},"
			+ " { display: '气表表头累计(Nm^3)', name: 'p15' , minWidth: 160},"
			+ " { display: '气表采集温度(℃)', name: 'p16' , minWidth: 160},"
			+ " { display: '气表采集压力(MPa)', name: 'p17' , minWidth: 160},"
			+ " { display: '产水瞬时流量(m^3/h)', name: 'p18' , minWidth: 160},"
			+ " { display: '产水当日累计(m^3)', name: 'p19' , minWidth: 160},"
			+ " { display: '产水昨日累计(m^3)', name: 'p20' , minWidth: 160},"
			+ " { display: '水表表头累计(m^3)', name: 'p21' , minWidth: 160},"
			+ "	{ display: '电网频率(Hz)', name: 'p36' , minWidth: 100},"
			+ "	{ display: '输出频率(Hz)', name: 'p37' , minWidth: 100},"
			+ "	{ display: '输出电压(V)', name: 'p38' , minWidth: 100},"
			+ "	{ display: '输出电流(A)', name: 'p39' , minWidth: 100},"
			+ "	{ display: '冲程(m)', name: 'p40' , minWidth: 80},"
			+ " 	{ display: '冲次(r/min)', name: 'p41' , minWidth: 80},"
			+ " 	{ display: '下行呈最大电流(A)', name: 'p42' , minWidth: 160},"
			+ " 	{ display: '上行呈最大电流(A)', name: 'p43' , minWidth: 160},"
			+ " 	{ display: '平衡率', name: 'p44' , minWidth: 80},"
			+ " 	{ display: '扭矩(N.m)', name: 'p45' , minWidth: 80},"
			+ " 	{ display: '转速(r/min)', name: 'p46' , minWidth: 80},"
			+ " 	{ display: '套压阀开度(%)', name: 'p47' , minWidth: 140}"
			+ " ], "
			+ "height:'100%',dataAction: 'server',"
			+ "url:'welldyn_searchWellDynInfo.action',"
			+ " pageParmName :'pageNo',  sortnameParmName:'sort', sortorderParmName: 'order',  pagesizeParmName:'rowsPerpage', "
			+ "selectRowButtonOnly:true,sePaper:true,pageSize:30 ,rownumbers:true,fixedCellHeight:true,frozen:true,checkbox :false,"
			+ "parms: [{ name: 'pcd', value: $('#selecttree').val()},{ name: 'jc', value: $('#well_site').val()},{ name: 'qj', value: $('#gas_well').val()},{ name: 'date', value: $('#txtDate').val()} ,{ name: 'date1', value: $('#txtDate1').val()}],"
			+ " onDblClickRow: function(data, rowindex, rowobj) {"
			+ " $.ligerDialog.open({" + " 			title: '查看气井动态信息',"
			+ " 			url:'viewSjBl.jsp?qjdtid='+data.qjdtid," + " 			width: 800,"
			+ " 			height: 550" + " 		});} ";

	public static final String QJSJ_PAGE_GRID = " columns: [ "
			+ " { display: 'ID', name: 'qjdtid', align: 'left', width: 100, minWidth: 60,hide: true},"
			+ " { display: '气井号', name: 'p01', minWidth: 100 ,frozen:true},"
			+ "{ display: '采集时间', name: 'p02', minWidth: 180 ,frozen:true},"
			+

			" { display: '通讯状态', name: 'p03', minWidth: 80},"
			+ " { display: '运行状态', name: 'p04', minWidth: 80},"
			+ " { display: '今运行时(h)', name: 'p05', minWidth: 80},"
			+ " { display: '昨运行时(h)', name: 'p06' ,minWidth: 110},"
			+ " { display: '套压(MPa)', name: 'p07' , minWidth: 80},"
			+ " { display: '井底流压(MPa)', name: 'p08' , minWidth: 110},"
			+ "{ display: '动液面(m)', name: 'p09' , minWidth: 100},"
			+ " { display: '产气瞬时流量(Nm^3/h)', name: 'p12' , minWidth: 160},"
			+ " { display: '产气当日累计(Nm^3)', name: 'p13' , minWidth: 160},"
			+ " { display: '产气昨日累计(Nm^3)', name: 'p14', minWidth: 160},"
			+ " { display: '气表表头累计(Nm^3)', name: 'p15' , minWidth: 160},"
			+ " { display: '气表采集温度(℃)', name: 'p16' , minWidth: 160},"
			+ " { display: '气表采集压力(℃)', name: 'p17' , minWidth: 160},"
			+ " { display: '产水瞬时流量(m^3/h)', name: 'p18' , minWidth: 160},"
			+ " { display: '产水当日累计(m^3)', name: 'p19' , minWidth: 160},"
			+ " { display: '产水昨日累计(m^3)', name: 'p20' , minWidth: 160},"
			+ " { display: '水表表头累计(m^3)', name: 'p21' , minWidth: 160},"
			+ "	{ display: '电网频率(Hz)', name: 'p36' , minWidth: 100},"
			+ "	{ display: '输出频率(Hz)', name: 'p37' , minWidth: 100},"
			+ "	{ display: '输出电压(V)', name: 'p38' , minWidth: 100},"
			+ "	{ display: '输出电流(A)', name: 'p39' , minWidth: 100},"
			+ "	{ display: '冲程(m)', name: 'p40' , minWidth: 80},"
			+ " 	{ display: '冲次(r/min)', name: 'p41' , minWidth: 80},"
			+ " 	{ display: '下行呈最大电流(A)', name: 'p42' , minWidth: 160},"
			+ " 	{ display: '上行呈最大电流(A)', name: 'p43' , minWidth: 160},"
			+ " 	{ display: '平衡率', name: 'p44' , minWidth: 80},"
			+ " 	{ display: '扭矩(N.m)', name: 'p45' , minWidth: 80},"
			+ " 	{ display: '转速(r/min)', name: 'p46' , minWidth: 80},"
			+ " 	{ display: '套压阀开度(%)', name: 'p47' , minWidth: 140}"
			+ " ],"
			+ "height:'100%',dataAction: 'server',"
			+ "url:'welldyn_searchWellDynInfo.action',"
			+ " pageParmName :'pageNo',  sortnameParmName:'sort', sortorderParmName: 'order',  pagesizeParmName:'rowsPerpage', "
			+ "selectRowButtonOnly:true,sePaper:true,pageSize:30 ,rownumbers:true,checkbox :false,fixedCellHeight:true,frozen:true";

	public static final String JQFDJ_PAGE_GRID = ""
			+ "columns: [ "
			+ "  { display: 'ID', name: 'jcjzdtid', align: 'left', width: 100, minWidth: 60,hide: true},"
			+ "  { display: '井场', name: 'strid', minWidth: 100 ,frozen:true},"
			+ "  { display: '采集时间', name: 'p02', minWidth: 180 ,frozen:true},"
			+ "  { display: '机组编号', name: 'p01', minWidth: 100},"
			
			+

			" { display: '相电压(V)', name: 'p03', minWidth: 80},"
			+ "  { display: '线电压(V)', name: 'p04', minWidth: 80},"
			+ "  { display: '相电流(A)', name: 'p05', minWidth: 80},"
			+ "  { display: '发电频率(Hz)', name: 'p06' ,minWidth: 100},"
			+ "  { display: '功率(KW)', name: 'p07' , minWidth: 80},"
			+ "  { display: '转速(RPM)', name: 'p08' , minWidth: 80},"
			+ "  { display: '油压(Bar)', name: 'p09' , minWidth: 80},"
			+ "  { display: '水温(℃)', name: 'p10' , minWidth: 80},"
			+ "  { display: '电池电压(V)', name: 'p11' , minWidth: 100},"
			+ "  { display: '发电机类型', name: 'p12', minWidth: 100}"
			+

			"  ],"
			+ "height:'100%',dataAction: 'server',"
			+ "url:'jcfdj_searchWellDynInfo.action',"
			+ " pageParmName :'pageNo',  sortnameParmName:'sort', sortorderParmName: 'order',  pagesizeParmName:'rowsPerpage', "
			+ "selectRowButtonOnly:true,sePaper:true,pageSize:30 ,fixedCellHeight:true,frozen:true,rownumbers:true,checkbox :false";
	public static final String JLJG_PAGE_GRID = "	columns: [ "
			+ " { display: 'ID', name: 'jljgid', align: 'left', width: 100, minWidth: 60,hide: true},"
			+ " { display: '气井', name: 'qjname', minWidth: 100},"
			+

			" { display: '计量起始时间', name: 'p02', minWidth: 200,align: 'center', format:'yyyy-MM-dd hh:mm:ss'},"
			+

			" { display: '持续时长(分钟)', name: 'p03', minWidth: 80},"
			+ "  { display: '有效时长(分钟)', name: 'p04', minWidth: 80},"
			+ " { display: '本次累计气量(Nm^3)', name: 'p05', minWidth: 130},"
			+ " { display: '日产气量(Nm^3/D)', name: 'p06' ,minWidth: 120},"
			+ " { display: '备注', name: 'bz' , minWidth: 200}"
			+

			"],"
			+ "height:'100%',dataAction: 'server',"
			+ "url:'jljg_searchJljg.action',"
			+ " pageParmName :'pageNo',  sortnameParmName:'sort', sortorderParmName: 'order',  pagesizeParmName:'rowsPerpage', "
			+ "selectRowButtonOnly:true,sePaper:true,pageSize:30 ,rownumbers:true,checkbox :false";

	public static final String QJRZBL_PAGE_GRID = " columns: [ "
			+ " { display: 'ID', name: 'qjrzid', align: 'left', width: 100, minWidth: 60,hide: true},"
			+ " { display: '气井号', name: 'p01', minWidth: 100 ,frozen:true},"
			+ " { display: '排采日期', name: 'p02', minWidth: 180 ,frozen:true},"
			+ " { display: '排采时间(d)', name: 'p03', minWidth: 80},"
			+ " { display: '运行状态', name: 'p04', minWidth: 80},"
			+ " { display: '今运行时间(h)', name: 'p05', minWidth: 80},"
			+ " { display: '生产时间', name: 'p06' ,minWidth: 110},"
			+ " { display: '套压(MPa)', name: 'p07' , minWidth: 80},"
			+ " { display: '井底流压(MPa)', name: 'p08' , minWidth: 110},"
			+ "{ display: '动液面(m)', name: 'p09' , minWidth: 100},"
			+ " { display: '产气瞬时流量(Nm^3/h)', name: 'p12' , minWidth: 160},"
			+ " { display: '产气当日累计(Nm^3)', name: 'p13' , minWidth: 160},"
			+ " { display: '气表表头累计(Nm^3)', name: 'p15' , minWidth: 160},"
			+ " { display: '气表采集温度(℃)', name: 'p16' , minWidth: 160},"
			+ " { display: '气表采集压力(MPa)', name: 'p17' , minWidth: 160},"
			+ " { display: '产水瞬时流量(m^3/h)', name: 'p18' , minWidth: 160},"
			+ " { display: '产水当日累计(m^3)', name: 'p19' , minWidth: 160},"
			+ "	{ display: '电网频率(Hz)', name: 'p36' , minWidth: 100},"
			+ "	{ display: '输出频率(Hz)', name: 'p37' , minWidth: 100},"
			+ "	{ display: '输出电压(v)', name: 'p38' , minWidth: 100},"
			+ "	{ display: '输出电流(a)', name: 'p39' , minWidth: 100},"
			+ "	{ display: '冲程(m)', name: 'p40' , minWidth: 80},"
			+ " 	{ display: '冲次(r/min)', name: 'p41' , minWidth: 80},"
			+ " 	{ display: '下行呈最大电流(A)', name: 'p42' , minWidth: 160},"
			+ " 	{ display: '上行呈最大电流(A)', name: 'p43' , minWidth: 160},"
			+ " 	{ display: '平衡率', name: 'p44' , minWidth: 80},"
			+ " 	{ display: '扭矩(N.m)', name: 'p45' , minWidth: 80},"
			+ " 	{ display: '转速(r/min)', name: 'p46' , minWidth: 80},"
			+ " 	{ display: '套压阀开度(%)', name: 'p47' , minWidth: 140},"
			+ " 	{ display: '产气月累计(Nm^3)', name: 'p49' , minWidth: 120},"
			+ " 	{ display: '产气年累计(Nm^3)', name: 'p50' , minWidth: 120},"
			+ " 	{ display: '产气总累计(Nm^3)', name: 'p51' , minWidth: 120},"
			+ " 	{ display: '产水月累计(m^3)', name: 'p52' , minWidth: 120},"
			+ " 	{ display: '产水年累计(m^3)', name: 'p53' , minWidth: 120},"
			+ " 	{ display: '产水总累计(m^3)', name: 'p54' , minWidth: 120}"
			+ " ], "
			+ "height:'100%',dataAction: 'server',"
			+ "url:'wellrz_searchWellRzInfo.action',"
			+ " pageParmName :'pageNo',  sortnameParmName:'sort', sortorderParmName: 'order',  pagesizeParmName:'rowsPerpage', "
			+ "selectRowButtonOnly:true,sePaper:true,pageSize:30 ,fixedCellHeight:true,frozen:true,rownumbers:true,checkbox :false,"
			+ "parms: [{ name: 'pcd', value: $('#selecttree').val()},{ name: 'jc', value: $('#well_site').val()},{ name: 'qj', value: $('#gas_well').val()},{ name: 'date', value: $('#txtDate').val()} ,{ name: 'date1', value: $('#txtDate1').val()}],"
			+ " onDblClickRow: function(data, rowindex, rowobj) {"
			+ " $.ligerDialog.open({" + " 			title: '查看气井日志信息',"
			+ " 			url:'viewRzBl.jsp?qjrzid='+data.qjrzid," + " 			width: 800,"
			+ " 			height: 550" + " 		});} ";
	
	// 观察井基础信息
		public static final String OBSERVATION_PAGE_GRID = " columns: ["
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 80,hide: true},"
			+ "  { display: '井区块ID', name: 'QKID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '井区块', name: 'QKMC' ,align: 'center', minWidth:60},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '所属注转站Id', name: 'GH_ID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 60,hide: true},"
			+ "  { display: '观察井ID', name: 'OBSERVATION_WELLID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			//+ "  { display: '区块ID', name: 'QKID',align: 'left', minWidth: 120,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '观察井井号', name: 'WELL_NAME',align: 'center', minWidth: 70,frozen:true},"
			+ "  { display: '观察井编码', name: 'WELL_COLE',align: 'center', minWidth: 70},"
			+ "  { display: '曾用井号', name: 'BEWELL_NAME',align: 'center', minWidth: 60},"
			+ "  { display: '接入标志', name: 'JRBZ',align: 'center', minWidth: 60},"
			+ "  { display: 'SCADA服务器逻辑节点名ID', name: 'LJJDID', align: 'center', width: 200, minWidth: 150,hide: true},"
			+ "  { display: 'SCADA服务器逻辑节点名', name: 'LJJD_S', align: 'center',minWidth: 150},"
			+ "  { display: '投产日期', name: 'COMMISSIONING_DATE',align: 'center', minWidth: 80},"
		//	+ "  { display: '报废日期', name: 'SCRAPPED_DATE',align: 'left', minWidth: 120},"
			+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 80},"
			+ "  { display: '建设生产状态CODE ', name: 'STATUS_VALUE' ,align: 'center', minWidth: 120 ,hide: true},"
			+ "  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ "  { display: '备注', name: 'REMARK',align: 'center', minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'obswell_searchObservation.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
	//联合站基础信息
	
	public static final String COMBINATION_PAGE_GRID = " columns: ["
			+ "  { display: '联合站ID', name: 'COMBINATION_STATIONID', align: 'left', width: 200, minWidth: 60 ,hide: true},"
			+ "  	{ display: '组织结构ID', name: 'ORG_ID', align: 'left', width: 200, minWidth: 60,hide: true},"
			+ "  	{ display: 'A2ID', name: 'A2ID', align: 'left', width: 200, minWidth: 60 ,hide: true},"
			+ "  	{ display: '联合站名称', name: 'COMBINATION_STATION_NAME', align: 'center',minWidth: 80},"
			+ " { display: '联合站类型', name: 'COMBINATION_STATION_TYPE',align: 'center', minWidth: 80},"
			+ " { display: '联合站编码', name: 'COMBINATION_STATION_CODE',align: 'center', minWidth: 80},"
			//+ "  { display: '接入标志', name: 'JRBZ',align: 'left', minWidth: 120},"
			//+ "  { display: '服务器逻辑节点名', name: 'LJJD_S',align: 'left', minWidth: 140},"
			//+ "  { display: '投产日期', name: 'COMMISSIONING_DATE',align: 'left', minWidth: 120},"
			//+ "  { display: '报废日期', name: 'SCRAPPED_DATE',align: 'left', minWidth: 120},"
			+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 80},"
			+ "  { display: '建设生产状态CODE ', name: 'STATUS_VALUE' ,align: 'center', minWidth: 120 ,hide: true},"
			+ " { display: '上次操作者 ', name: 'RLAST_OPER' ,align: 'center', minWidth: 80},"
			+ " { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ " { display: '备注', name: 'REMARK',align: 'center',minWidth: 120}"
			+ " ],"+
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'combination_searchCombination.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
	// 注转站基础信息
	
	public static final String STATIONT_PAGE_GRID = " columns: ["
			+ "  { display: '注转站ID', name: 'BLOCKSTATIONID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '采油站ID', name: 'GH_ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  	{ display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  	{ display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ " { display: '注转站名称', name: 'BLOCKSTATION_NAME',align: 'center', minWidth: 80  ,frozen:true},"
			+ " { display: '注转站编码', name: 'BLOCKSTATION_CODE',align: 'center', minWidth: 80},"
			+ " { display: '注转站类型', name: 'BS_TYPE'  ,align: 'center', minWidth: 80},"
			+ " { display: '区块', name: 'QKMC',align: 'center', minWidth: 60},"
			+ " { display: '区块ID', name: 'QKID',align: 'center', minWidth: 60 ,hide: true},"
			+ " { display: '采油站', name: 'OILDRILLING_NAME',align: 'center', minWidth: 60},"
			+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 80},"
			+ "  { display: '建设生产状态CODE ', name: 'STATUS_VALUE' ,align: 'center', minWidth: 120 ,hide: true},"
			+ "  { display: '投产日期', name: 'COMMISSIONING_DATE',align: 'left', minWidth: 80},"
			+ "  { display: '设计产能年', name: 'DYEAR',align: 'center', minWidth: 80},"
			+ " { display: '管汇数量', name: 'GHS',align: 'center', minWidth: 60},"
			+ " { display: '储油罐数量', name: 'CYGS',align: 'center', minWidth: 60},"
			+ " { display: '储油罐规格', name: 'CYGGG',align: 'center', minWidth: 70},"
			+ " { display: '转油泵数量 ', name: 'ZYBS' ,align: 'center', minWidth: 60},"
			+ " { display: '注汽单位', name: 'YXZ',align: 'center', minWidth: 60},"
			+ " { display: '锅炉数量', name: 'BOILER_QTY',align: 'center', minWidth: 60},"
			+ " { display: '运行组ID', name: 'YXZID',align: 'center', minWidth: 60 ,hide: true},"
//			+ " { display: '联合站', name: 'LHZ',align: 'center', minWidth: 60},"
//			+ " { display: '联合站ID', name: 'LHZID',align: 'center', minWidth: 60 ,hide: true},"
			+ " { display: '注汽站备注', name: 'STEAM_REMARK',align: 'center',minWidth: 120},"
			+ " { display: '接入标志', name: 'JRBZ'  ,align: 'center', minWidth: 60},"
			+ "  { display: 'SCADA服务器逻辑节点名ID', name: 'LJJDID', align: 'left', width: 200, minWidth: 150,hide: true},"
			+ "  { display: 'SCADA服务器逻辑节点名', name: 'LJJD_S', align: 'left',minWidth: 90},"
			//+ "  { display: '报废日期', name: 'SCRAPPED_DATE',align: 'left', minWidth: 120},"
			+ " { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70},"
			+ " { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ " { display: '备注', name: 'REMARK',align: 'center',minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'station_searchStation.action',"+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";

	// 气站基础信息
	
	public static final String GASSTATIONT_PAGE_GRID = " columns: ["
			+ "  { display: '气站ID', name: 'BLOCKSTATIONID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '采油站ID', name: 'GH_ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  	{ display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  	{ display: '气井类型', name: 'BS_TYPE', align: 'center',minWidth: 60,hide: true},"
			+ "  	{ display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ " { display: '气站名称', name: 'BLOCKSTATION_NAME',align: 'center', minWidth: 60  ,frozen:true},"
			+ " { display: '气站编码', name: 'CODE',align: 'center', minWidth: 60  ,frozen:true},"
			+ " { display: '区块', name: 'QKMC',align: 'center', minWidth: 60},"
			+ " { display: '区块ID', name: 'QKID',align: 'center', minWidth: 60 ,hide: true},"
			+ " { display: '采油站', name: 'OILDRILLING_NAME',align: 'center', minWidth: 60},"
			+ " { display: '管汇数', name: 'GHS',align: 'center', minWidth: 60},"
			+ " { display: '储油罐数', name: 'CYGS',align: 'center', minWidth: 60},"
			+ " { display: '储油罐规格', name: 'CYGGG',align: 'center', minWidth: 70},"
			+ " { display: '转油泵数 ', name: 'ZYBS' ,align: 'center', minWidth: 60},"
			+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 80},"
			+ "  { display: '建设生产状态CODE ', name: 'STATUS_VALUE' ,align: 'center', minWidth: 120 ,hide: true},"
			+ "  { display: '投产日期', name: 'COMMISSIONING_DATE',align: 'center', minWidth: 80},"
			+ "  { display: '产能设计年', name: 'DYEAR',align: 'center', minWidth: 80},"
			+ "  { display: '接入标志', name: 'JRBZ',align: 'center', minWidth: 80},"
			+ "  { display: '服务器逻辑节点名', name: 'LJJD_S',align: 'center', minWidth: 90},"
			+ " { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70},"
			+ " { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ " { display: '备注', name: 'REMARK',align: 'center',minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'gasstation_searchStation.action',"+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
	
	
//阀池基础信息
	
	public static final String FCJCXX_PAGE_GRID = " columns: ["
			+ " { display: '阀池名称', name: 'CLIQUE_POOL_NAME',align: 'center', minWidth: 80  ,frozen:true},"
			+ " { display: '阀池编号', name: 'CODE',align: 'center', minWidth: 80},"
			+ " { display: '阀池类型', name: 'CLIQUE_TYPE',align: 'center', minWidth: 80},"
			+ " { display: '注转站', name: 'STATION_NAME',align: 'center', minWidth: 90},"
			+ " { display: '注转站编码', name: 'STATION_CODE',align: 'center', minWidth: 80},"
			+ " { display: '采油站', name: 'OIL_STATION_NAME',align: 'center', minWidth: 90},"
			+ " { display: '区块(注转站)', name: 'AREA_NAME',align: 'center', minWidth: 80},"
			+ " { display: '区类名称', name: 'AREA_CLASS_NAME',align: 'center', minWidth: 80},"
			+ " { display: 'RTU编号 ', name: 'RTU_CODE' ,align: 'center', minWidth: 60},"
			//+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 80},"
			+ "  { display: '接入标志', name: 'JRBZ',align: 'center', minWidth: 80},"
			+ "  { display: '服务器逻辑节点名', name: 'LJJD_S',align: 'center', minWidth: 90},"
			+ "  { display: '安装日期', name: 'INSTALL_DATE',align: 'center', minWidth: 80},"
//			+ "  { display: 'IP', name: 'IP',align: 'center', minWidth: 80,hide: true},"
//			+ "  { display: '站号', name: 'STATION_NO',align: 'center', minWidth: 80,hide: true},"
			
			+ " { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70},"
			+ " { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ " { display: '备注', name: 'REMARK',align: 'center',minWidth: 120},"
			//+ " { display: '建设生产状态ID', name: 'PROD_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ " { display: '接入标志ID', name: 'JRBZ_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ " { display: '服务器ID', name: 'LJJD_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ " { display: '注转站组织结构ID', name: 'STATION_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ " { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ " { display: '阀池ID', name: 'CLIQUE_ID', align: 'center', width: 200, minWidth: 60 ,hide: true}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'fcjcxx_searchDatas.action',"+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
//水源井基础信息

			public static final String WATERSOWELL_PAGE_GRID = " columns: ["
				+ "  { display: '水源井号', name: 'WELL_NAME',align: 'center', minWidth: 60,frozen:true},"
				+ "  { display: '所属联合站', name: 'COMBINATION' ,align: 'center', minWidth: 80},"
				+ "  { display: '所属站号', name: 'STATIONNUM' ,align: 'center', minWidth: 60},"
				+ "  { display: '井号编码', name: 'WELL_CODE',align: 'center', minWidth: 60},"
				+ "  { display: '曾用井号', name: 'BEWELL_NAME',align: 'center', minWidth: 60},"
				+ "  { display: '建设生产状态CODE ', name: 'STATUS_VALUE' ,align: 'center', minWidth: 120 ,hide: true},"
				+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 70},"
				+ "  { display: '投产日期', name: 'COMMISSIONING_DATE',align: 'center', minWidth: 80},"
				+ "  { display: '设计产能年', name: 'DYEAR',align: 'center', minWidth: 120},"
				+ "  { display: '接入标志', name: 'JRBZ',align: 'center', minWidth: 60},"
				+ "  { display: '服务器逻辑节点名', name: 'LJJD_S',align: 'center', minWidth: 90},"
				+ "  { display: '服务器逻辑节点名ID', name: 'LJJDID',align: 'center', minWidth: 140,hide: true},"
				+ "  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70},"
				+ "  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
				+ "  { display: '备注', name: 'REMARK',align: 'center', minWidth: 120},"
				+ "  { display: '所属站ID', name: 'GH_ID' ,align: 'center', minWidth: 120,hide: true},"
				+ "  { display: '水源井ID', name: 'WATER_SOURCE_WELLID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
				+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
				+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true}"
				+ " ],"+
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'waterSoWell_searchWaterSoWell.action',"+	
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"selectRowButtonOnly:true,"+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"rownumbers:true ,"+
				"frozen:true, "+
				"checkbox :false, "+
				"onSelectRow: function (rowdata, rowindex) "+
			 "{fromAu(rowdata);} ";

// 采油站基础信息
	
	public static final String OILDRILLING_PAGE_GRID = " columns: ["
			+ "  { display: '采油站ID', name: 'OILDRILLING_STATIONID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '采油站名称', name: 'OILDRILLING_STATION_NAME',align: 'center', minWidth: 120  ,frozen:true},"
			//+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 120},"
			+ "  { display: '建设生产状态CODE ', name: 'STATUS_VALUE' ,align: 'center', minWidth: 120 ,hide: true},"
			//+ "  { display: '投产日期', name: 'COMMISSIONING_DATE',align: 'center', minWidth: 120},"
			//+ "  { display: '报废日期', name: 'SCRAPPED_DATE',align: 'center', minWidth: 120},"
			+ "  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 120},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ "  { display: '备注', name: 'REMARK',align: 'center',minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'oildrilling_searchOilStation.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";

// 区块基础信息
	
	public static final String AREAINFO_PAGE_GRID = " columns: ["
			+ "  { display: '区块ID', name: 'QKID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '区类ID', name: 'ZONE_CODE', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: '区块名称', name: 'QKMC',align: 'center', minWidth: 120,frozen:true},"
			+ "  { display: '区块代码', name: 'QKMC_S',align: 'center', minWidth: 120},"
			+ "  { display: '区类代码', name: 'ZONE_NAME',align: 'center', minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'areainfo_searchAreaInfo.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
//SAGD井基础信息
	public static final String SAGD_PAGE_GRID = " columns: ["
			+ "  { display: '井区块ID', name: 'QKID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth:80,hide: true},"
			+ "  { display: 'SAGD井ID', name: 'SAGDID', align: 'center', width: 50, minWidth: 60 ,hide: true},"
			+ "  { display: '注转站ID', name: 'ZZZ_ID', align: 'center', width: 50, minWidth: 60,hide: true},"
			+ "  { display: '管汇ID', name: 'GH_ID', align: 'center', width: 50, minWidth: 60,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 50, minWidth: 60,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 50, minWidth: 60 ,hide: true},"
			+ "  { display: '井口蒸气控制器接入标志', name: 'WSRTU_CNF',align: 'center', minWidth: 70,hide: true},"
			+ "  { display: '建设生产状态CODE ', name: 'STATUS_VALUE' ,align: 'center', minWidth: 120 ,hide: true},"
			+ "  { display: '井号名称', name: 'JHMC',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 40},"
			+ "  { display: '生产阶段', name: 'PROD_STAGES'  ,align: 'center', minWidth: 80},"
			+ "  { display: 'P井生产方式', name: 'PMODENAME'  ,align: 'center', minWidth: 80},"
			+ "  { display: 'I井生产方式', name: 'IMODENAME'  ,align: 'center', minWidth: 80},"
			+ "  { display: '投产日期', name: 'COMMISSIONING_DATE',align: 'center', minWidth: 80},"
			+ "  { display: '设计产能年', name: 'DYEAR',align: 'center', minWidth: 40},"
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '井区块', name: 'QKMC' ,align: 'center', minWidth: 80},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth:60},"
			+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 60},"
			+ "  { display: '井号编码', name: 'JHMC_S',align: 'center', minWidth: 60},"
			+ "  { display: '多通阀编码', name: 'DTFMC_S',align: 'center', minWidth: 40},"
			+ "  { display: '通道号', name: 'TDH',align: 'center', minWidth: 40},"
			+ "  { display: '施工井标志', name: 'WWORK_FLAG',align: 'center', minWidth: 40},"
			+ "  { display: '施工开始日期', name: 'WWORK_DATE',align: 'center', minWidth: 80},"
			+ "  { display: '异常井标志', name: 'WABNORMAL_FLAG',align: 'center', minWidth: 40},"
			+ "  { display: '异常井开始时间', name: 'WABNORMAL_DATE',align: 'center', minWidth: 90},"
			+ "  { display: '加密井标志', name: 'INCREASE_FREQ_FLAG',align: 'center', minWidth: 90},"
			+ "  { display: '加密起始时间', name: 'START_INCREASE_FREQ_TIME',align: 'center', minWidth: 150},"
			+ "  { display: '加密结束时间', name: 'END_INCREASE_FREQ_TIME',align: 'center', minWidth: 150},"
			+ "  { display: '加密间隔', name: 'INCREASE_INTERVAL',align: 'center', minWidth: 90},"
			//+ "  { display: '上自动化标志', name: 'AUF',align: 'center', minWidth:40},"
			+ "  { display: '井口控制器接入标志', name: 'WHRTU_CNF',align: 'center', minWidth: 60},"
			+ "  { display: '井下控制器接入标志', name: 'P_UGRTU_CNF',align: 'center', minWidth: 60},"
			+ "  { display: '井下温度点数', name: 'P_UGTEM_NUM',align: 'center', minWidth: 40},"
			+ "  { display: '抽油机控制器接入标志', name: 'P_PURTU_CNF',align: 'center', minWidth: 60},"
			+ "  { display: '井口数', name: 'WELLS_NUM',align: 'center', minWidth: 40},"
			+ "  { display: '井口名称列举', name: 'WELLS_NAM',align: 'center', minWidth: 40},"
			+ "  { display: 'IFIX触发器扫描时间', name: 'SCAN_TIME',align: 'center', minWidth: 60},"
			+ "  { display: 'IFIX触发器相位时间', name: 'PHASE',align: 'center', minWidth: 60},"
			+ "  { display: '有无电流图标志', name: 'P_DLT_CNF',align: 'center', minWidth: 40},"
			+ "  { display: '井口RTU地址', name: 'WELL_RTU_ADR',align: 'center', minWidth: 40},"
			+ "  { display: 'SCADA服务器逻辑节点名', name: 'LJJD_S', align: 'center',minWidth:70},"
			+ "  { display: '接入标识', name: 'JRBZ',align: 'center', minWidth: 60},"
			+ "  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 90},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ "  { display: '分产系数', name: 'OUTPUT_COEFFICIENT'  ,align: 'center', minWidth: 80},"
			+ "  { display: 'SCADA服务器逻辑节点名ID', name: 'LJJDID', align: 'center',minWidth: 70,hide: true},"
			+ "  { display: 'P井生产方式ID', name: 'P_PROD_MODE'  ,align: 'center', minWidth: 80,hide: true},"
			+ "  { display: 'I井生产方式ID', name: 'I_PROD_MODE'  ,align: 'center', minWidth: 80,hide: true},"
			+ "  { display: '备注', name: 'REMARK',align: 'center', minWidth: 120},"
			+ "  { display: '报表顺序', name: 'BBSX',align: 'center', minWidth: 120},"
			+ "  { display: '班组划分', name: 'BZHF',align: 'center', minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sagd_searchSagdBaisc.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false,headerRowHeight:50,"+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
	

	
	//SAGD井动态数据-全部
	public static final String SAGDRDALL_PAGE_GRID = " columns: ["
			+ "  { display: '井号名称', name: 'JHMC',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '采集时间', name: 'ACQUISITION_TIME',align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '井号编码', name: 'JHMC_S',align: 'center', minWidth: 60},"
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 60},"
			+ "  { display: 'SAGD井ID', name: 'SAGDID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
		
			
			+ " { display: '抽油机参数', columns:"
			+ "	["
			+ "		{ display: '电机状态', name: 'MOTORSTATUS', align: 'center', width: 60},"
			+ "		{ display: '电机A相电压(V)', name: 'MOTOR_PRESS_A', width: 100, align: 'center'},"
			+ "		{ display: '电机B相电压(V)', name: 'MOTOR_PRESS_B', width: 100, align: 'center'},"
			+ "		{ display: '电机C相电压(V)', name: 'MOTOR_PRESS_C', width: 100, align: 'center'},"
			+ "		{ display: '电机A相电流(A)', name: 'MOTOR_TEMP_A', width: 100, align: 'center'},"
			+ "		{ display: '电机B相电流(A)', name: 'MOTOR_TEMP_B', width: 100, align: 'center'},"
			+ "		{ display: '电机C相电流(A)', name: 'MOTOR_TEMP_C', width: 100, align: 'center'},"
			+ "		{ display: '有功电量(kWh)', name: 'ACTIVE_ENERGY', width: 90, align: 'center'},"
			+ "		{ display: '无功电量(kVar)', name: 'IDLE_ENERGY', width: 90, align: 'center'},"
			+ "		{ display: '功率因素(%)', name: 'POWER_FACTOR', width: 90, align: 'center'},"
			+ "		{ display: '变频电压(V)', name: 'CONVERSION_PRESS', width: 90, align: 'center'},"
			+ "		{ display: '变频电流(A)', name: 'CONVERSION_ELECTRICITY', width: 90, align: 'center'},"
			+ "		{ display: '当前频率(HZ)', name: 'NOW_FREQUENCY', width: 90, align: 'center'},"
			+ "		{ display: '冲程(m)', name: 'STROKE', width: 60, align: 'center'},"
			+ "		{ display: '冲次(次/分)', name: 'JIG_FREQUENCY', width: 80, align: 'center'},"
			//+ "		{ display: '脱抽饱和方式', name: 'BHFS', width: 100, align: 'center'}," //数据字典中没有该字段
			+ "		{ display: '报警状态', name: 'ALMSTATE', width: 60, align: 'center'},"
			//+ "		{ display: '最新频率(HZ)', name: 'ZXPL', width: 100, align: 'center'},"
			//+ "		{ display: '正常频率(HZ)', name: 'ZCPL', width: 100, align: 'center'},"
			
			+ "		{ display: '当前载荷', name: 'NOW_LOAD', width: 60, align: 'center'},"
			+ "		{ display: '电机运行模式', name: 'MOTOR_MODE', width: 100, align: 'center'},"
			+ "		{ display: '抽油机运行状态', name: 'PUMPING_RUNNING_STATE', width: 100, align: 'center'}"
			
			+ "	]},"
			
			
			+ " { display: '井口数据', columns:"
			+ "	["
			+ "		{ display: 'P井主管压力(MPa)', name: 'P_PRESSURE_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'P井主管温度(℃)', name: 'P_PRESSURE_TEMP', width: 100, align: 'center'},"
			+ "		{ display: 'P井副管压力(MPa)', name: 'P_LOOPED_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'P井副管温度(℃)', name: 'P_LOOPED_TEMP', width: 100, align: 'center'},"
			+ "		{ display: 'P井套管压力(MPa)', name: 'P_DRIVEPIPE_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'I井主管压力(MPa)', name: 'I_PRESSURE_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'I井主管温度(℃)', name: 'I_PRESSURE_TEMP', width: 100, align: 'center'},"
			+ "		{ display: 'I井副管压力(MPa)', name: 'I_LOOPED_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'I井副管温度(℃)', name: 'I_LOOPED_TEMP', width: 100, align: 'center'},"
			+ "		{ display: 'I井套管压力(MPa)', name: 'I_DRIVEPIPE_PRESS', width: 100, align: 'center'}"
			+ "	]},"
			
			+ " { display: '井下数据', columns:"
			+ "	["
			+ "		{ display: 'P井压力1(MPa)', name: 'P_PRESS1', align: 'center', width: 80},"
			+ "		{ display: 'P井压力2(MPa)', name: 'P_PRESS2', width: 80, align: 'center'},"
			+ "		{ display: 'P井温度1(℃)', name: 'P_TEMP1', align: 'center', width: 80},"
			+ "		{ display: 'P井温度2(℃)', name: 'P_TEMP2', width: 80, align: 'center'},"
			+ "		{ display: 'P井温度3(℃)', name: 'P_TEMP3', width: 80, align: 'center'},"
			+ "		{ display: 'P井温度4(℃)', name: 'P_TEMP4', align: 'center', width: 80},"
			+ "		{ display: 'P井温度5(℃)', name: 'P_TEMP5', width: 80, align: 'center'},"
			+ "		{ display: 'P井温度6(℃)', name: 'P_TEMP6', align: 'center', width: 80},"
			+ "		{ display: 'P井温度7(℃)', name: 'P_TEMP7', width: 80, align: 'center'},"
			+ "		{ display: 'P井温度8(℃)', name: 'P_TEMP8', width: 80, align: 'center'},"
			+ "		{ display: 'P井温度9(℃)', name: 'P_TEMP9', align: 'center', width: 80},"
			+ "		{ display: 'P井温度10(℃)', name: 'P_TEMP10', width: 80, align: 'center'},"
			+ "		{ display: 'P井温度11(℃)', name: 'P_TEMP11', align: 'center', width: 80},"
			+ "		{ display: 'P井温度12(℃)', name: 'P_TEMP12', width: 80, align: 'center'}"
			+ "	]},"
			
			+ " { display: '蒸汽计量数据', columns:"
			+ "	["
			+ "		{ display: '蒸汽压力1#(mPa)', name: 'NO1STEAM_PRESS', align: 'center', width: 100},"
			+ "		{ display: '蒸汽压力2#(mPa)', name: 'NO2STEAM_PRESS', width: 100, align: 'center'},"
			+ "		{ display: '蒸汽温度1#(℃)', name: 'NO1STEAM_TEMP', align: 'center', width: 100},"
			+ "		{ display: '蒸汽温度2#(℃)', name: 'NO2STEAM_TEMP', width: 100, align: 'center'},"
			+ "		{ display: '蒸汽差压1#(mPa)', name: 'NO1STEAM_DIFP', width: 100, align: 'center'},"
			+ "		{ display: '蒸汽差压2#(mPa)', name: 'NO2STEAM_DIFP', align: 'center', width: 100},"
			+ "		{ display: '瞬时干度1# (%)', name: 'NO1INSTANT_DRYNESS', width: 100, align: 'center'},"
			+ "		{ display: '瞬时干度2# (%)', name: 'NO2INSTANT_DRYNESS', align: 'center', width: 100},"
			+ "		{ display: '瞬时流量1#(T/H)', name: 'NO1INSTANT_FLOW', width: 100, align: 'center'},"
			+ "		{ display: '瞬时流量2#(T/H)', name: 'NO2INSTANT_FLOW', width: 100, align: 'center'},"
			+ "		{ display: '调节阀开度1#(%)', name: 'NO1CONTROL_APERTURE', align: 'center', width: 100},"
			+ "		{ display: '调节阀开度2#(%)', name: 'NO2CONTROL_APERTURE', width: 100, align: 'center'},"
			+ "		{ display: '阀控制状态1#', name: 'NO1CONTROL_STATUS', align: 'center', width: 100},"//阀控制状态1#
			+ "		{ display: '阀控制状态2#', name: 'NO2CONTROL_STATUS', width: 100, align: 'center'},"
			+ "		{ display: '阀控制开度1#(%)', name: 'CLIQUE_CONTROL_APERTURE1', width: 100, align: 'center'},"
			+ "		{ display: '阀控制开度2#(%)', name: 'CLIQUE_CONTROL_APERTURE2', align: 'center', width: 100},"
			+ "		{ display: '流量设定1#(T/H)', name: 'NO1FLOW_SET', width: 100, align: 'center'},"
			+ "		{ display: '流量设定2#(T/H)', name: 'NO2FLOW_SET', align: 'center', width: 100},"
//			+ "		{ display: '比例系数P 1#', name: 'BLXS1', width: 100, align: 'center'},"
//			+ "		{ display: '比例系数P 2#', name: 'BLXS2', width: 100, align: 'center'},"
//			+ "		{ display: '积分系数T 2#', name: 'JFXS1', width: 100, align: 'center'},"
//			+ "		{ display: '积分系数T 2#', name: 'JFXS2', align: 'center', width: 100},"
			+ "		{ display: '昨日累积流量1# (T)', name: 'YCUMULATIVE_FLOW1', width: 100, align: 'center'},"
			+ "		{ display: '昨日累积流量2# (T)', name: 'YCUMULATIVE_FLOW2', width: 100, align: 'center'},"
			+ "		{ display: '今日累积流量1# (T)', name: 'TCUMULATIVE_FLOW1', width: 100, align: 'center'},"
			+ "		{ display: '今日累积流量2# (T)', name: 'TCUMULATIVE_FLOW2', align: 'center', width: 100}"
			//+ "		{ display: '总累积流量1# (T)', name: 'SUM_FLOW1', width: 100, align: 'center'},"
			//+ "		{ display: '总累积流量2# (T)', name: 'SUM_FLOW2', align: 'center', width: 100}"
			+ "	]},"
			+ "  { display: '备注', name: 'REMARK', align: 'center', align: 'center',width: 255,minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sagdrd_searchSagdRD.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
	
	
	

	//SAGD井动态数据-全部
	public static final String SAGDRDALL_PAGE_GRID2 = " columns: ["
			+ "  { display: '井号名称', name: 'JHMC',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '采集时间', name: 'ACQUISITION_TIME',align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '井号编码', name: 'JHMC_S',align: 'center', minWidth: 60},"
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 60},"
			+ "  { display: 'SAGD井ID', name: 'SAGDID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
		
			
			+ " { display: '抽油机参数', columns:"
			+ "	["
			+ "		{ display: '电机状态', name: 'MOTORSTATUS', align: 'center', width: 60},"
			+ "		{ display: '电机A相电压(V)', name: 'MOTOR_PRESS_A', width: 100, align: 'center'},"
			+ "		{ display: '电机B相电压(V)', name: 'MOTOR_PRESS_B', width: 100, align: 'center'},"
			+ "		{ display: '电机C相电压(V)', name: 'MOTOR_PRESS_C', width: 100, align: 'center'},"
			+ "		{ display: '电机A相电流(A)', name: 'MOTOR_TEMP_A', width: 100, align: 'center'},"
			+ "		{ display: '电机B相电流(A)', name: 'MOTOR_TEMP_B', width: 100, align: 'center'},"
			+ "		{ display: '电机C相电流(A)', name: 'MOTOR_TEMP_C', width: 100, align: 'center'},"
			+ "		{ display: '有功电量(kWh)', name: 'ACTIVE_ENERGY', width: 80, align: 'center'},"
			+ "		{ display: '无功电量(kVar)', name: 'IDLE_ENERGY', width: 80, align: 'center'},"
			+ "		{ display: '功率因素(%)', name: 'POWER_FACTOR', width: 80, align: 'center'},"
			+ "		{ display: '变频电压(V)', name: 'CONVERSION_PRESS', width: 80, align: 'center'},"
			+ "		{ display: '变频电流(A)', name: 'CONVERSION_ELECTRICITY', width: 80, align: 'center'},"
			+ "		{ display: '当前频率(HZ)', name: 'NOW_FREQUENCY', width: 80, align: 'center'},"
			+ "		{ display: '冲程(m)', name: 'STROKE', width: 60, align: 'center'},"
			+ "		{ display: '冲次(次/分)', name: 'JIG_FREQUENCY', width: 70, align: 'center'},"
			//+ "		{ display: '脱抽饱和方式', name: 'BHFS', width: 100, align: 'center'}," //数据字典中没有该字段
			+ "		{ display: '报警状态', name: 'ALMSTATE', width: 60, align: 'center'},"
			//+ "		{ display: '最新频率(HZ)', name: 'ZXPL', width: 100, align: 'center'},"
			//+ "		{ display: '正常频率(HZ)', name: 'ZCPL', width: 100, align: 'center'},"
			
			+ "		{ display: '当前载荷', name: 'NOW_LOAD', width: 60, align: 'center'},"
			+ "		{ display: '电机运行模式', name: 'MOTOR_MODE', width: 80, align: 'center'},"
			+ "		{ display: '抽油机运行状态', name: 'PUMPING_RUNNING_STATE', width: 100, align: 'center'}"
			
			+ "	]},"
			+ "  { display: '备注', name: 'REMARK', align: 'center', align: 'center',width: 255, minWidth: 100}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sagdrd_searchSagdRD.action',"+	
			"pageParmName :'pageNo',"+
			"delayLoad :true, "+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, ";
	
	//SAGD井动态数据-全部
	public static final String SAGDRDALL_PAGE_GRID3 = " columns: ["
			+ "  { display: '井号名称', name: 'JHMC',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '采集时间', name: 'ACQUISITION_TIME',align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '井号编码', name: 'JHMC_S',align: 'center', minWidth: 60},"
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 60},"
			+ "  { display: 'SAGD井ID', name: 'SAGDID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			
			+ " { display: '井口数据', columns:"
			+ "	["
			+ "		{ display: 'P井主管压力(MPa)', name: 'P_PRESSURE_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'P井主管温度(℃)', name: 'P_PRESSURE_TEMP', width: 100, align: 'center'},"
			+ "		{ display: 'P井副管压力(MPa)', name: 'P_LOOPED_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'P井副管温度(℃)', name: 'P_LOOPED_TEMP', width: 100, align: 'center'},"
			+ "		{ display: 'P井套管压力(MPa)', name: 'P_DRIVEPIPE_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'I井主管压力(MPa)', name: 'I_PRESSURE_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'I井主管温度(℃)', name: 'I_PRESSURE_TEMP', width: 100, align: 'center'},"
			+ "		{ display: 'I井副管压力(MPa)', name: 'I_LOOPED_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'I井副管温度(℃)', name: 'I_LOOPED_TEMP', width: 100, align: 'center'},"
			+ "		{ display: 'I井套管压力(MPa)', name: 'I_DRIVEPIPE_PRESS', width: 100, align: 'center'}"
			+ "	]},"
			+ "  { display: '备注', name: 'REMARK', align: 'center', align: 'center',width: 255, minWidth: 100}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sagdrd_searchSagdRD.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
	
	//SAGD井动态数据-全部
	public static final String SAGDRDALL_PAGE_GRID4 = " columns: ["
			+ "  { display: '井号名称', name: 'JHMC',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '采集时间', name: 'ACQUISITION_TIME',align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '井号编码', name: 'JHMC_S',align: 'center', minWidth: 60},"
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 60},"
			+ "  { display: 'SAGD井ID', name: 'SAGDID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			
			+ " { display: '井下数据', columns:"
			+ "	["
			+ "		{ display: 'P井压力1(MPa)', name: 'P_PRESS1', align: 'center', width: 100},"
			+ "		{ display: 'P井压力2(MPa)', name: 'P_PRESS2', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度1(℃)', name: 'P_TEMP1', align: 'center', width: 100},"
			+ "		{ display: 'P井温度2(℃)', name: 'P_TEMP2', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度3(℃)', name: 'P_TEMP3', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度4(℃)', name: 'P_TEMP4', align: 'center', width: 100},"
			+ "		{ display: 'P井温度5(℃)', name: 'P_TEMP5', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度5(℃)', name: 'P_TEMP6', align: 'center', width: 100},"
			+ "		{ display: 'P井温度7(℃)', name: 'P_TEMP7', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度8(℃)', name: 'P_TEMP8', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度9(℃)', name: 'P_TEMP9', align: 'center', width: 100},"
			+ "		{ display: 'P井温度10(℃)', name: 'P_TEMP10', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度11(℃)', name: 'P_TEMP11', align: 'center', width: 100},"
			+ "		{ display: 'P井温度12(℃)', name: 'P_TEMP12', width: 100, align: 'center'}"
			+ "	]},"
			+ "  { display: '备注', name: 'REMARK', align: 'center', align: 'center',width: 255, minWidth: 100}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sagdrd_searchSagdRD.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
	
	//SAGD井动态数据-全部
	public static final String SAGDRDALL_PAGE_GRID5 = " columns: ["
			+ "  { display: '井号名称', name: 'JHMC',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '采集时间', name: 'ACQUISITION_TIME',align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '井号编码', name: 'JHMC_S',align: 'center', minWidth: 60},"
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 60},"
			+ "  { display: 'SAGD井ID', name: 'SAGDID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			
			+ " { display: '蒸汽计量数据', columns:"
			+ "	["
			+ "		{ display: '蒸汽压力1#(mPa)', name: 'NO1STEAM_PRESS', align: 'center', width: 100},"
			+ "		{ display: '蒸汽压力2#(mPa)', name: 'NO2STEAM_PRESS', width: 100, align: 'center'},"
			+ "		{ display: '蒸汽温度1#(℃)', name: 'NO1STEAM_TEMP', align: 'center', width: 100},"
			+ "		{ display: '蒸汽温度2#(℃)', name: 'NO2STEAM_TEMP', width: 100, align: 'center'},"
			+ "		{ display: '蒸汽差压1#(mPa)', name: 'NO1STEAM_DIFP', width: 100, align: 'center'},"
			+ "		{ display: '蒸汽差压2#(mPa)', name: 'NO2STEAM_DIFP', align: 'center', width: 100},"
			+ "		{ display: '瞬时干度1# (%)', name: 'NO1INSTANT_DRYNESS', width: 100, align: 'center'},"
			+ "		{ display: '瞬时干度2# (%)', name: 'NO2INSTANT_DRYNESS', align: 'center', width: 100},"
			+ "		{ display: '瞬时流量1#(T/H)', name: 'NO1INSTANT_FLOW', width: 100, align: 'center'},"
			+ "		{ display: '瞬时流量2#(T/H)', name: 'NO2INSTANT_FLOW', width: 100, align: 'center'},"
			+ "		{ display: '调节阀开度1#(%)', name: 'NO1CONTROL_APERTURE', align: 'center', width: 100},"
			+ "		{ display: '调节阀开度2#(%)', name: 'NO2CONTROL_APERTURE', width: 100, align: 'center'},"
			+ "		{ display: '阀控制状态1#', name: 'NO1CONTROL_STATUS', align: 'center', width: 100},"//阀控制状态1#
			+ "		{ display: '阀控制状态2#', name: 'NO2CONTROL_STATUS', width: 100, align: 'center'},"
			+ "		{ display: '阀控制开度1#(%)', name: 'CLIQUE_CONTROL_APERTURE1', width: 100, align: 'center'},"
			+ "		{ display: '阀控制开度2#(%)', name: 'CLIQUE_CONTROL_APERTURE2', align: 'center', width: 100},"
			+ "		{ display: '流量设定1#(T/H)', name: 'NO1FLOW_SET', width: 100, align: 'center'},"
			+ "		{ display: '流量设定2#(T/H)', name: 'NO2FLOW_SET', align: 'center', width: 100},"
//			+ "		{ display: '比例系数P 1#', name: 'BLXS1', width: 100, align: 'center'},"
//			+ "		{ display: '比例系数P 2#', name: 'BLXS2', width: 100, align: 'center'},"
//			+ "		{ display: '积分系数T 2#', name: 'JFXS1', width: 100, align: 'center'},"
//			+ "		{ display: '积分系数T 2#', name: 'JFXS2', align: 'center', width: 100},"
			+ "		{ display: '昨日累积流量1# (T)', name: 'YCUMULATIVE_FLOW1', width: 100, align: 'center'},"
			+ "		{ display: '昨日累积流量2# (T)', name: 'YCUMULATIVE_FLOW2', width: 100, align: 'center'},"
			+ "		{ display: '今日累积流量1# (T)', name: 'TCUMULATIVE_FLOW1', width: 100, align: 'center'},"
			+ "		{ display: '今日累积流量2# (T)', name: 'TCUMULATIVE_FLOW2', align: 'center', width: 100},"
			+ "		{ display: '总累积流量1# (T)', name: 'SUM_FLOW1', width: 100, align: 'center'},"
			+ "		{ display: '总累积流量2# (T)', name: 'SUM_FLOW2', align: 'center', width: 100}"
			+ "	]},"
			+ "  { display: '备注', name: 'REMARK', align: 'center', align: 'center',width: 255, minWidth: 100}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sagdrd_searchSagdRD.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";

	//常规油井基础信息
	public static final String RULE_WELL_PAGE_GRID = " columns: ["
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 80},"
			//+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 120},"
			+ "  { display: '井区块ID', name: 'QKID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '井区块', name: 'QKMC' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属接转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属班组', name: 'teamGroup' ,align: 'center', minWidth: 100},"
			+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
			+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 80},"
			+ "  { display: '建设生产状态CODE ', name: 'STATUS_VALUE' ,align: 'center', minWidth: 120 ,hide: true},"
			+ "  { display: '投产日期', name: 'COMMISSIONING_DATE',align: 'center', minWidth: 80},"
			+ "  { display: '产能设计年', name: 'DYEAR',align: 'center', minWidth: 80},"
			+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 60},"
			+ "  { display: '井号编码', name: 'WELL_COLE',align: 'center', minWidth: 60},"
			+ "  { display: '曾用井号', name: 'BEWELL_NAME',align: 'center', minWidth: 60},"
			+ "  { display: '加密标志', name: 'INCREASE_FREQ_FLAG',align: 'center', minWidth: 60},"
			+ "  { display: '加密开始时间', name: 'START_INCREASE_FREQ_TIME',align: 'center', minWidth: 120},"
			+ "  { display: '加密结束时间', name: 'END_INCREASE_FREQ_TIME',align: 'center', minWidth: 120},"
			+ "  { display: '加密间隔', name: 'INCREASE_INTERVAL',align: 'center', minWidth: 60},"
			+ "  { display: '接入标志', name: 'JRBZ',align: 'center', minWidth: 60},"
			+ "  { display: '接入标志ID', name: 'SWITCH_IN_FLAG',align: 'center', minWidth: 60,hide: true},"
			+ "  { display: '服务器逻辑节点名ID', name: 'LJJDID',align: 'center', minWidth: 140,hide: true},"
			+ "  { display: '服务器逻辑节点名', name: 'LJJD_S',align: 'center', minWidth: 100},"
			+ "  { display: '多通阀编码 ', name: 'VALVE_CODING' ,align: 'center', minWidth:70},"
			+ "  { display: '通道号 ', name: 'CHANNEL_NO' ,align: 'center', minWidth: 40},"
			+ "  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ "  { display: '分产系数', name: 'OUTPUT_COEFFICIENT'  ,align: 'center', minWidth: 100},"
			+ "  { display: '备注', name: 'REMARK',align: 'center', minWidth: 120},"
			+ "  { display: '常规油井ID', name: 'RULE_WELLID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: '注转站ID', name: 'ZZZ_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: '管汇ID', name: 'GH_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			//+ "  { display: '区块ID', name: 'QKID',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '常规井号', name: 'WELL_NAME',align: 'center', minWidth: 60,frozen:true}"
			//+ "  { display: '报废日期', name: 'SCRAPPED_DATE',align: 'center', minWidth: 120},"
			
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'rulewell_searchRullWell.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
//稀油井基础信息
	
	public static final String THINOIL_WELL_PAGE_GRID = " columns: ["
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '稀油井号', name: 'WELL_NAME',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '井区块', name: 'QKMC' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属班组', name: 'teamGroup' ,align: 'center', minWidth: 100},"
			+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
			+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 70},"
			+ "  { display: '建设生产状态CODE ', name: 'STATUS_VALUE' ,align: 'center', minWidth: 120 ,hide: true},"
			+ "  { display: '投产日期', name: 'COMMISSIONING_DATE',align: 'center', minWidth: 80},"
			+ "  { display: '产能设计年', name: 'DYEAR',align: 'center', minWidth: 50},"
			+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 60},"
			+ "  { display: '稀油编码', name: 'WELL_COLE',align: 'center', minWidth: 60},"
			+ "  { display: '曾用井号', name: 'BEWELL_NAME',align: 'center', minWidth: 60},"
			+ "  { display: '加密标志', name: 'INCREASE_FREQ_FLAG',align: 'center', minWidth: 60},"
			+ "  { display: '加密起始时间', name: 'START_INCREASE_FREQ_TIME',align: 'center', minWidth: 120},"
			+ "  { display: '加密结束时间', name: 'END_INCREASE_FREQ_TIME',align: 'center', minWidth: 120},"
			+ "  { display: '加密间隔', name: 'INCREASE_INTERVAL',align: 'center', minWidth: 60},"
			+ "  { display: '接入标志', name: 'JRBZ',align: 'center', minWidth: 60},"
			+ "  { display: '服务器逻辑节点名ID', name: 'LJJDID',align: 'center', minWidth: 140,hide: true},"
			+ "  { display: '服务器逻辑节点名', name: 'LJJD_S',align: 'center', minWidth: 90},"
			+ "  { display: '多通阀编码 ', name: 'VALVE_CODING' ,align: 'center', minWidth: 70},"
			+ "  { display: '通道号 ', name: 'CHANNEL_NO' ,align: 'center', minWidth: 40},"
			+ "  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ "  { display: '分产系数', name: 'OUTPUT_COEFFICIENT'  ,align: 'center', minWidth: 100},"
			+ "  { display: '备注', name: 'REMARK',align: 'center', minWidth: 120},"
			+ "  { display: '稀油油井ID', name: 'WELLID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '井区块ID', name: 'QKID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: '注转站ID', name: 'ZZZ_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: '管汇ID', name: 'GH_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			//+ "  { display: '区块ID', name: 'QKID',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true}"
			
		
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'thinoil_searchThinOil.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
//气井基础信息
	
	public static final String GAS_WELL_PAGE_GRID = " columns: ["
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60,hide: true},"
			+ "  { display: '井区块ID', name: 'QKID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '井区块', name: 'QKMC' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属气站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属气站ID', name: 'GH_ID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '气井ID', name: 'GAS_WELLID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			//+ "  { display: '区块ID', name: 'QKID',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '气井井号', name: 'WELL_NAME',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '井号编码', name: 'WELL_COLE',align: 'center', minWidth: 60},"
			+ "  { display: '曾用井号', name: 'BEWELL_NAME',align: 'center', minWidth: 60},"
			+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 70},"
			+ "  { display: '建设生产状态CODE ', name: 'STATUS_VALUE' ,align: 'center', minWidth: 120 ,hide: true},"
			+ "  { display: '投产日期', name: 'COMMISSIONING_DATE',align: 'center', minWidth: 80},"
			+ "  { display: '产能设计年', name: 'DYEAR',align: 'center', minWidth: 50},"
			+ "  { display: '接入标志', name: 'JRBZ',align: 'center', minWidth: 60},"
			+ "  { display: '接入标志ID', name: 'SWITCH_IN_FLAG',align: 'center', minWidth: 60,hide: true},"
			+ "  { display: '服务器逻辑节点名ID', name: 'LJJDID',align: 'center', minWidth: 90,hide: true},"
			+ "  { display: '服务器逻辑节点名', name: 'LJJD_S',align: 'center', minWidth: 90},"
		
		
			
			+ "  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ "  { display: '备注', name: 'REMARK',align: 'center', minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'gaswell_searchGasWell.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
//注水撬基础信息
	
	public static final String WATER_INJECTIONTOPRID_PAGE_GRID = " columns: ["
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '所属班组', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 80},"
			+ "  { display: '所属采油站ID', name: 'GH_ID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '注水撬ID', name: 'WATER_INJECTIONTOPRID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: '区块ID', name: 'QKID',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '注水撬名称', name: 'WATER_INJECTIONTOPR_NAME',align: 'center', minWidth: 70,frozen:true},"
			+ "  { display: '注水撬编码', name: 'CODE',align: 'center', minWidth: 60},"
			+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 70},"
			+ "  { display: '建设生产状态CODE ', name: 'STATUS_VALUE' ,align: 'center', minWidth: 120 ,hide: true},"
			+ "  { display: '投产日期', name: 'COMMISSIONING_DATE',align: 'center', minWidth: 80},"
			+ "  { display: '产能设计年', name: 'DYEAR',align: 'center', minWidth: 120},"
			+ "  { display: '接入标志', name: 'JRBZ',align: 'center', minWidth: 60},"
			+ "  { display: '服务器逻辑节点名ID', name: 'LJJDID',align: 'center', minWidth: 140,hide: true},"
			+ "  { display: '服务器逻辑节点名', name: 'LJJD_S',align: 'center', minWidth: 90},"
			+ "  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ "  { display: '备注', name: 'REMARK',align: 'center', minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'waterIT_searchwaterIT.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
//注水井基础信息
	
	public static final String WATERFLOODING_PAGE_GRID = " columns: ["
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '所属班组', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60,hide: true},"
			+ "  { display: '所属注水撬ID', name: 'GH_ID' ,align: 'center', minWidth: 60,hide: true},"
			+ "  { display: '井区块ID', name: 'QKID' ,align: 'center', minWidth: 60,hide: true},"
			+ "  { display: '井区块', name: 'QKMC' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注水撬 ', name: 'WATERINJECTIONTOPR' ,align: 'center', minWidth: 70},"
			+ "  { display: '注水井ID', name: 'WATERFLOODING_WELLID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			//+ "  { display: '区块ID', name: 'QKID',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '注水井名称', name: 'WATERFLOODING_NAME',align: 'center', minWidth: 70,frozen:true},"
			+ "  { display: '井号编码', name: 'WATERFLOODING_CODE',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '通道号', name: 'CHANNEL_NUMBER',align: 'center', minWidth: 40},"
			+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 80},"
			+ "  { display: '建设生产状态CODE ', name: 'STATUS_VALUE' ,align: 'center', minWidth: 120 ,hide: true},"
			+ "  { display: '投产日期', name: 'COMMISSIONING_DATE',align: 'center', minWidth: 80},"
			+ "  { display: '产能设计年', name: 'DYEAR',align: 'center', minWidth: 80},"
			+ "  { display: '接入标志', name: 'JRBZ',align: 'center', minWidth: 60},"
			+ "  { display: '服务器逻辑节点名ID', name: 'LJJDID',align: 'center', minWidth: 140,hide: true},"
			+ "  { display: '服务器逻辑节点名', name: 'LJJD_S',align: 'center', minWidth: 90},"
			+ "  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ "  { display: '备注', name: 'REMARK',align: 'center', minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'waterFL_searchwaterFL.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
//锅炉基础信息
	
	public static final String BOILER_PAGE_GRID = " columns: ["
			//+ "  { display: '所属联合站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '锅炉名称', name: 'BOILER_NAME',align: 'center', minWidth: 80,frozen:true},"
			+ "  { display: '锅炉ID', name: 'BOILERID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 180, minWidth: 60,hide: true},"
			+ "  { display: '供气单位', name: 'YXZ',align: 'center', minWidth: 70},"
			+ "  { display: '运行组ID', name: 'YXZID',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '供热站', name: 'BLOCKSTATIONNAME',align: 'center', minWidth: 90},"
			+ "  { display: '供热站ID', name: 'BLOCKSTATIONNAMEID',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '锅炉编号', name: 'BOILER_CODE',align: 'center', minWidth: 60},"
			+ "  { display: '锅炉类型', name: 'BOILER_TYPE',align: 'center', minWidth: 60},"
			+ "  { display: '额定水量', name: 'RATING_CAPACITY',align: 'center', minWidth: 60},"
			+ "  { display: '配注量', name: 'INJECTION_ALLOCATION_RATE',align: 'center', minWidth: 60},"
			+ "  { display: '受汽区块', name: 'AREABLOCK',align: 'center', minWidth: 80},"
			+ "  { display: '受汽区块ID', name: 'QKID',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '受汽单位ID', name: 'ACCEPT_UNIT',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '受汽单位', name: 'SQDW',align: 'center', minWidth: 80},"
			
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			
			
			
			
			+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 80},"
			+ "  { display: '建设生产状态CODE ', name: 'STATUS_VALUE' ,align: 'center', minWidth: 120 ,hide: true},"
			+ "  { display: '投产日期', name: 'COMMISSIONING_DATE',align: 'center', minWidth: 80},"
			+ "  { display: '设计产能年', name: 'DYEAR',align: 'center', minWidth: 80},"
			
			+ "  { display: '厂家ID', name: 'FACTORY_UC',align: 'center', minWidth: 80,hide: true},"
			+ "  { display: '厂家', name: 'FACTORY_NF',align: 'center', minWidth: 80},"
			+ "  { display: '接入标志', name: 'JRBZ',align: 'center', minWidth: 60},"
			+ "  { display: '服务器逻辑节点名ID', name: 'LJJDID',align: 'left', minWidth: 140,hide: true},"
			+ "  { display: '服务器逻辑节点名', name: 'LJJD_S',align: 'left', minWidth: 90},"
			+ "  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ "  { display: '备注', name: 'REMARK',align: 'center', minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'boilerBasicInfo_queryBoilerBasicInfo.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
//管汇点基础信息
	
	public static final String MANIFOLD_PAGE_GRID = " columns: ["
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '所属区块', name: 'AREABLOCK',align: 'center', minWidth: 60,hide: true},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME',align: 'center', minWidth: 80},"
			+ "  { display: '所属班组', name: 'teamGroup',align: 'center', minWidth: 80},"
			+ "  { display: '分线', name: 'BRANCHING' ,align: 'center', minWidth: 60},"
			+ "  { display: '站号id', name: 'GRZHID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '管汇ID', name: 'MANIFOLDID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '分线ID', name: 'BRANCHINGID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: '区块ID', name: 'QKID',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '管汇名称', name: 'GHMC',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '管汇代码', name: 'GHDM',align: 'center', minWidth: 60},"
			+ "  { display: '所属计量注转站', name: 'JLATIONNAME',align: 'center', minWidth: 80},"
			+ "  { display: '所属计量注转站ID', name: 'PID2',align: 'center', minWidth: 80,hide: true},"
			+ "  { display: '站内编码', name: 'CODE',align: 'left', minWidth: 90},"
			+ "  { display: '多通阀数', name: 'DTFS',align: 'center', minWidth: 60},"
			+ "  { display: '1#多通阀名称', name: 'DTFMC1',align: 'center', minWidth: 80},"
			+ "  { display: '1#多通阀通道数', name: 'DTFTDS1',align: 'center', minWidth: 90},"
			+ "  { display: '2#多通阀名称', name: 'DTFMC2',align: 'center', minWidth: 80},"
			+ "  { display: '2#多通阀通道数', name: 'DTFTDS2',align: 'center', minWidth: 90},"
			+ "  { display: '建设生产状态', name: 'PROD_SNS',align: 'center', minWidth: 80},"
			+ "  { display: '建设生产状态CODE ', name: 'STATUS_VALUE' ,align: 'center', minWidth: 120 ,hide: true},"
			+ "  { display: '投产日期', name: 'COMMISSIONING_DATE',align: 'center', minWidth: 80},"
			+ "  { display: '产能设计年', name: 'DYEAR',align: 'center', minWidth: 120},"
			+ "  { display: '计量器状态', name: 'INSTRU_STVA',align: 'center', minWidth: 80},"
			+ "  { display: '接入标志', name: 'JRBZ',align: 'center', minWidth: 60},"
			+ "  { display: '服务器逻辑节点名ID', name: 'LJJDID',align: 'left', minWidth: 140,hide: true},"
			+ "  { display: '服务器逻辑节点名', name: 'LJJD_S',align: 'left', minWidth: 90},"
			
			+ "  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ "  { display: '备注', name: 'REMARK',align: 'center', minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'manifoldBasicInfo_queryManifoldBasicInfo.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
//分线基础信息
	
	public static final String BRANCHING_PAGE_GRID = " columns: ["
			+ "  { display: '所属联合站', name: 'COMBINATION_STATION' ,align: 'center', minWidth: 120},"
			+ "  { display: '分线id', name: 'BRANCHINGID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '联合站id', name: 'COMSTATIONID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: '分线名称', name: 'BRANCHING_NAME',align: 'center', minWidth: 120,frozen:true},"
			+ "  { display: '分线编码', name: 'BRANCHING_CODE',align: 'center', minWidth: 120},"
			+ "  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 120},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ "  { display: '备注', name: 'REMARK',align: 'center', minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'branchingBasicInfo_queryBranchingBasicInfo.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
//控制器通信信息
	
	public static final String CONTROLLER_PAGE_GRID = " columns: ["
			+ "  { display: '设备名称', name: 'FACILITY_NAME',align: 'center', minWidth: 130,hide:true},"
			+ "  { display: '所属单位', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 100},"
			+ "  { display: '组织结构类型', name: 'STRUCTURETYPE', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: '所属类型', name: 'STRUCTURENAME' ,align: 'center', minWidth: 100,hide:true},"
			+ "  { display: '对象类型编码', name: 'OBJECT_TYPE_NAME' ,align: 'center', minWidth: 90},"
			+ "  { display: '仪表设备类型', name: 'INSTRUMENTATION_TYPE',align: 'center', minWidth: 80},"
			+ "  { display: '所属仪表', name: 'YBNAME' ,align: 'center', minWidth: 100,frozen:true},"
			/*+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属井口', name: 'WELLNUM' ,align: 'center', minWidth: 60},"*/
			+ "  { display: '通讯信息ID', name: 'CONTROLLERID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: '仪表设备ID', name: 'INSTRUMENTATIONID',align: 'center', minWidth: 120,hide: true},"
			
			+ "  { display: '驱动类型', name: 'COMMUNICATION_TYPE',align: 'center', minWidth: 60},"
			+ "  { display: 'IP地址', name: 'IP_ADDRESS',align: 'center', minWidth: 100},"
			+ "  { display: '通讯站号', name: 'STATION_NO',align: 'center', minWidth: 60},"
			+ "  { display: '通讯端口', name: 'COMMUNICATION_PORT',align: 'center', minWidth: 60},"
			+ "  { display: '设备状态', name: 'EQUIPMENT_STATUS',align: 'center', minWidth: 60,hide: true},"
			+ "  { display: '设备状态', name: 'STATUS',align: 'center', minWidth: 60},"
			+ "  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 90},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ "  { display: '备注', name: 'REMARK',align: 'center', minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'control_searchControl.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
//仪表设备基础信息
	
	public static final String INSTRU_MENTATION_PAGE_GRID = " columns: ["
			+ "  { display: '所属对象', name: 'STRUCTURENAME' ,align: 'center', minWidth: 100,frozen:true},"
			+ "  { display: '所属对象类型', name: 'OBJECT_TYPE_NAME',align: 'center', minWidth: 80},"
			+ "  { display: '对象所属单位', name: 'DWNAME',align: 'center', minWidth: 120},"
			+ "  { display: '区块名', name: 'QKNAME',align: 'center', minWidth: 80},"
			+ "  { display: '站号名', name: 'ZHNAME',align: 'center', minWidth: 100},"
			
			+ "  { display: '对象接入标志', name: 'SWITCH_IN_DESC',align: 'center', minWidth: 80},"
			+ "  { display: '仪表设备名称', name: 'INSTRU_CLN',align: 'center', minWidth: 120},"
			+ "  { display: '仪表状态', name:'INSTRU_STNS',align: 'center', minWidth: 100},"
			+ "  { display: '供货厂家', name:'SFACTORY_NS',align: 'center', minWidth: 100},"
			+ "  { display: '制造厂家', name:'MFACTORY_NS',align: 'center', minWidth: 100},"
			+ "  { display: '仪表设备型号', name:'INSTRU_C3N',align: 'center', minWidth: 100},"
			
			+ "  { display: '上级设备', name: 'SJRTU_CLN',align: 'center', minWidth: 80},"
			+ "  { display: '通讯IP地址', name: 'IP',align: 'center', minWidth: 120},"
			+ "  { display: 'IP设置设备名', name:'IPINSTRU_CLN',align: 'center', minWidth: 100},"
			+ "  { display: '通讯站号', name:'STATION_NO',align: 'center', minWidth: 100},"
			+ "  { display: '通讯端口', name:'COMMUNICATION_PORT',align: 'center', minWidth: 100},"
			
			+ "  { display: '备注', name:'REMARK',align: 'center', minWidth: 100},"
			
			+ "  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 90},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			
			+ "  { display: '仪表大类名', name: 'INSTRU_1TN',align: 'center', minWidth: 80},"
			+ "  { display: '仪表供货ID', name: 'INSTRUS_ID',align: 'center', minWidth: 120},"
			+ "  { display: '仪表供货ID描述', name:'INSTRUS_IDN',align: 'center', minWidth: 100},"
			
			+ "  { display: '数据提供方式代码', name:'DATA_AMC',align: 'center', minWidth: 100},"
			
			
			+ "  { display: '对象投产日期', name:'COMMISSIONING_DATE',align: 'center', minWidth: 100},"
			+ "  { display: 'SCADA服务器逻辑节点', name:'SCADA_NODE',align: 'center', minWidth: 100},"
			+ "  { display: 'SCADA对象代码', name:'CODE',align: 'center', minWidth: 100},"
			+ "  { display: 'SCADA对象接入代码', name:'INSCODE',align: 'center', minWidth: 100},"
			+ "  { display: '供货厂家全名', name:'SFACTORY_NF',align: 'center', minWidth: 100},"
			+ "  { display: '仪表设备ID', name:'INSTRUMENT_ID',align: 'center', minWidth: 100},"
			
			+ "  { display: '对象所属单位ID', name:'DWID',align: 'center', minWidth: 100 , hide :true},"
			+ "  { display: '区块ID', name:'QKID',align: 'center', minWidth: 100, hide :true},"
			+ "  { display: '站号ID', name:'ZHID',align: 'center', minWidth: 100, hide :true},"
			+ "  { display: '所属对象ID', name:'	',align: 'center', minWidth: 100, hide :true},"
			+ "  { display: '所属对象类型代码', name:'OBJECT_CODE',align: 'center', minWidth: 100, hide :true},"
			+ "  { display: '仪表设备名称代码', name:'INSTRU_CLC',align: 'center', minWidth: 100, hide :true},"
			+ "  { display: '仪表大类代码', name:'INSTRU_1TC',align: 'center', minWidth: 100, hide :true},"
			+ "  { display: '上级设备代码', name:'SJRTU_CLC',align: 'center', minWidth: 100, hide :true},"
			+ "  { display: '仪表状态代码', name:'INSTRU_STVA',align: 'center', minWidth: 100, hide :true}"
			
			
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'instru_searchInstru.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
	
//稀油油井动态数据信息
	
	public static final String THINOILWELL_RD_PAGE_GRID = " columns: ["
			+ "  { display: '稀油井号', name: 'WELLNAME' ,align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '所属采油站', name: 'oilName' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属区块', name: 'qkmc' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'jieStation' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属管汇', name: 'guanhui' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属分线', name: 'branching_name' ,align: 'center', minWidth: 60},"
			+ "  { display: '稀油油井动态数据ID', name: 'THINOIL_WELLDSID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '稀油油井ID', name: 'WELLID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '井号代码', name: 'daima' ,align: 'center', minWidth: 60},"
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '当前载荷', name: 'QLD' ,align: 'center', minWidth: 60},"
			+ "  { display: '电机工作电压A相', name: 'QUA' ,align: 'center', minWidth: 100},"
			+ "  { display: '电机工作电流A相', name: 'QIA', align: 'center', minWidth: 100},"
			+ "  { display: '电机工作电压B相', name: 'QUB', align: 'center', minWidth: 100},"
			+ "  { display: '电机工作电流B相', name: 'QIB' ,align: 'center', minWidth: 100},"
			+ "  { display: '电机工作电压C相', name: 'QUC',align: 'center', minWidth: 100},"
			+ "  { display: '电机工作电流C相', name: 'QIC', align: 'center',minWidth: 100},"
			+ "  { display: '电机有功用电量', name: 'QAP',align: 'center', minWidth: 100},"
			+ "  { display: '电机无功用电量', name: 'QQP',align: 'center', minWidth: 100},"
			+ "  { display: '电机功率因数', name: 'QCQ',align: 'center', minWidth: 100},"
			+ "  { display: '冲次', name: 'QCC',align: 'center', minWidth: 30},"
			+ "  { display: '冲程', name: 'QST',align: 'center', minWidth: 30},"
			+ "  { display: '抽油机运行状态', name: 'QUS',align: 'center', minWidth: 100},"
			+ "  { display: '手/自动状态', name: 'QMA',align: 'center', minWidth: 80},"
			+ "  { display: '变频电压', name: 'QFU'  ,align: 'center', minWidth: 60},"
			+ "  { display: '变频运行频率', name: 'QFF'  ,align: 'center', minWidth: 80},"
			+ "  { display: '变频电流', name: 'QFI',align: 'center', minWidth: 60},"
			+ "  { display: '电机运行模式', name: 'QMO',align: 'center', minWidth: 80},"
			+ "  { display: '井口压力', name: 'WELL_PRES',align: 'center', minWidth: 60}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'thinoilrd_searchInstru.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true,"+
			"checkbox :false";
//常规油井动态数据信息(待补)
	
	public static final String RULLWELL_RD_PAGE_GRID = " columns: ["
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 120},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 120},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 120},"
			+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 120},"
			+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 120},"
			+ "  { display: '常规油井动态ID', name: 'RULE_WELLRID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '常规油井ID', name: 'RULE_WELLID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '常规井号', name: 'WELL_NAME' ,align: 'center', minWidth: 120,frozen:true},"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'thinoilrd_searchInstru.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";

//气井动态数据信息
	
	public static final String GASWELL_RD_PAGE_GRID = " columns: ["
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属气站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 60},"
			+ "  { display: '气井动态数据ID', name: 'GAS_WELLRID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '气井ID', name: 'GAS_WELLID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '井号代码', name: 'JHS' ,align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '井口压力', name: 'WELL_PRES' ,align: 'center', minWidth: 60},"
			+ "  { display: '井口温度', name: 'WELL_TEMP' ,align: 'center', minWidth: 60},"
			+ "  { display: '瞬时流量', name: 'INSTANTANEOUS_DELIVERY', align: 'center', minWidth: 60},"
			+ "  { display: '总流量', name: 'CUMULATIVE_DISCHARGE', align: 'center',minWidth: 60}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'gasrd_searchInstru.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
//水源井动态数据信息
	
	public static final String WATERSOURCEWELL_RD_PAGE_GRID = " columns: ["
			+ "  { display: '所属联合站', name: 'CNAME' ,align: 'center', minWidth: 120},"
			+ "  { display: '所属站号', name: 'ZNAME' ,align: 'center', minWidth: 100},"
			+ "  { display: '水源井码', name: 'WELL_NAME' ,align: 'center', minWidth: 80},"
			//+ "  { display: '井号代码', name: 'well_code' ,align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '水源井动态数据ID', name: 'WATER_SOURCE_WELLRID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '水源井ID', name: 'WATER_SOURCE_WELLID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '瞬时流量', name: 'INSTANTANEOUS_DELIVERY' ,align: 'center', minWidth: 60},"
			+ "  { display: '液位', name: 'LIQUID_LEVEL' ,align: 'center', minWidth: 40},"
			+ "  { display: '压力', name: 'PRES', align: 'center',minWidth: 40},"
			+ "  { display: '电流A', name: 'CURRENTA' ,align: 'center', minWidth: 60},"
			+ "  { display: '电流B', name: 'CURRENTB' ,align: 'center', minWidth: 60},"
			+ "  { display: '电流C', name: 'CURRENTC' ,align: 'center', minWidth: 60},"
			+ "  { display: '频率', name: 'FREQUENCY' ,align: 'center', minWidth: 60},"
			+ "  { display: '昨日累积流量', name: 'YAC_FLOW' ,align: 'center', minWidth: 70},"
			+ "  { display: '今日累积流量', name: 'TAC_FLOW' ,align: 'center', minWidth: 70},"
			+ "  { display: '总累积流量', name: 'CUMULATIVE_DISCHARGE' ,align: 'center', minWidth: 70},"
			+ "  { display: '累积运行时间', name: 'CUMULATIVE_TIME' ,align: 'center', minWidth:80},"
			+ "  { display: '启停状态', name: 'REV_STOP_STATE', align: 'center',minWidth: 60}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'waterswrd_searchInstru.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
//注转站(转油)动态数据信息
	public static final String BSTATION_TURNOILRD_PAGE_GRID = " columns: ["
			+ "  { display: '接转站', name: 'BLOCKSTATION_NAME' ,align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '采集时间', name: 'ACQUISITION_TIME' ,align: 'center', minWidth: 130,frozen:true},"
			+ "  { display: '接转站转油动态ID', name: 'BSTATION_TURNOILRID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '接转站ID', name: 'BLOCKSTATIONID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '接转站类型', name: 'BLOCKSTATIONTYPE', align: 'center', width: 70, minWidth: 60 },"
			+ "  { display: '采油站', name: 'CYZ' ,align: 'center', minWidth: 90},"
			+ "  { display: '供气单位', name: 'YXZ' ,align: 'center', minWidth: 60},"
			+ "  { display: '区块', name: 'QKMC' ,align: 'center', minWidth: 100},"
			+ "  { display: '外输压力', name: 'EFFLUX_PRES' ,align: 'center', minWidth: 60},"
			+ "  { display: '外输温度', name: 'EFFLUX_TEMP' ,align: 'center', minWidth: 40},"
			+ "  { display: '外输泵频率', name: 'EFFLUX_PUMP_FREQ', align: 'center',minWidth: 40},"
			+ "  { display: '1#罐液位', name: 'TANK_LIQUID_LEVEL_1' ,align: 'center', minWidth: 60},"
			+ "  { display: '2#罐液位', name: 'TANK_LIQUID_LEVEL_2' ,align: 'center', minWidth: 60},"
			+ "  { display: '罐间压力', name: 'AMONGST_TANKS_PRES' ,align: 'center', minWidth: 60},"
			+ "  { display: '1#硫化氢浓度', name: 'H2S_CONCENTRATION_1' ,align: 'center', minWidth: 60},"
			+ "  { display: '2#硫化氢浓度', name: 'H2S_CONCENTRATION_2' ,align: 'center', minWidth: 70},"
			+ "  { display: '1#泵运行状态', name: 'EFFLUX_PUMP_STATE_1' ,align: 'center', minWidth: 70},"
			+ "  { display: '2#泵运行状态', name: 'EFFLUX_PUMP_STATE_2' ,align: 'center', minWidth: 70},"
			+ "  { display: '日产量计算系数', name: 'DAILY_OUTPUT_COEFFICIENT' ,align: 'center', minWidth:80}"
//			+ "  { display: '启停状态', name: 'REV_STOP_STATE', align: 'center',minWidth: 60}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'stationrd_searchBlockstationData.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
	
	
	//锅炉房气体监测数据
	public static final String BOILER_ROOM_GAS_MONITORING_DATA=" columns: ["
		+ "  { display: '采集时间', name: 'ACQUISITION_TIME' ,align: 'center', minWidth: 150,frozen:true},"
		+ "  { display: '注转站名称', name: 'BLOCKSTATION_NAME' ,align: 'center', minWidth: 70,frozen:true},"
		+ "  { display: '可燃气体浓度%', columns:["
		+ "  { display: '1#', name: 'KRQT_ND_1' ,align: 'center', minWidth: 100},"
		+ "	 { display: '2#', name: 'KRQT_ND_2' ,align: 'center', minWidth: 100},"
		+ "	 { display: '3#', name: 'KRQT_ND_3' ,align: 'center', minWidth: 100},"
		+ "  { display: '4#', name: 'KRQT_ND_4' ,align: 'center', minWidth: 100},"
		+ "  { display: '5#', name: 'KRQT_ND_5' ,align: 'center', minWidth: 100}"
		+ "	]},"
		+ "  { display: 'H2S气体浓度PPM',columns: ["
		+ "  { display: '1#', name: 'H2S_ND_1' ,align: 'center', minWidth: 100},"
		+ "  { display: '2#', name: 'H2S_ND_2' ,align: 'center', minWidth: 100},"
		+ "  { display: '3#', name: 'H2S_ND_3' ,align: 'center', minWidth: 100},"
		+ "  { display: '4#', name: 'H2S_ND_4' ,align: 'center', minWidth: 100},"
		+ "  { display: '5#', name: 'H2S_ND_5' ,align: 'center', minWidth: 100}"
		+ "	]}"  
		+ " ],"+
		"height:'100%',"+
		"dataAction: 'server',"+
		"url:'boilerGasMonitor_searchBlockstationData.action',"+	
		"delayLoad :true, "+
		"pageParmName :'pageNo',"+
		"sortnameParmName:'sort',"+
		"sortorderParmName: 'order',  "+
		"pagesizeParmName:'rowsPerpage', "+
		"selectRowButtonOnly:true,"+
		"sePaper:true,"+
		"pageSize:30 ,"+
		"rownumbers:true ,"+
		"frozen:true, "+
		"checkbox :false";
	
	
	
	//注转站(转油)日报信息
	public static final String BSTATION_TURNOILRPD_PAGE_GRID = " columns: ["
			+ "  { display: '接转站', name: 'BLOCKSTATION_NAME' ,align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '日期', name: 'REPORT_DATE' ,align: 'center', minWidth: 100,frozen:true},"
			+ "  { display: '接转站转油日报ID', name: 'BSTATION_TURNOILDID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '接转站ID', name: 'BLOCKSTATIONID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '接转站类型', name: 'BLOCKSTATIONTYPE', align: 'center', width: 70, minWidth: 60 },"
			+ "  { display: '采油站', name: 'CYZ' ,align: 'center', minWidth: 90},"
			+ "  { display: '供气单位', name: 'YXZ' ,align: 'center', minWidth: 60},"
			+ "  { display: '区块', name: 'QKMC' ,align: 'center', minWidth: 100},"
			+ "  { display: '外输压力', name: 'EFFLUX_PRES' ,align: 'center', minWidth: 60},"
			+ "  { display: '外输温度', name: 'EFFLUX_TEMP' ,align: 'center', minWidth: 40},"
			+ "  { display: '外输泵频率', name: 'EFFLUX_PUMP_FREQ', align: 'center',minWidth: 40},"
			+ "  { display: '1#罐液位', name: 'TANK_LIQUID_LEVEL_1' ,align: 'center', minWidth: 60},"
			+ "  { display: '2#罐液位', name: 'TANK_LIQUID_LEVEL_2' ,align: 'center', minWidth: 60},"
			+ "  { display: '罐间压力', name: 'AMONGST_TANKS_PRES' ,align: 'center', minWidth: 60},"
			+ "  { display: '1#硫化氢浓度', name: 'H2S_CONCENTRATION_1' ,align: 'center', minWidth: 60},"
			+ "  { display: '2#硫化氢浓度', name: 'H2S_CONCENTRATION_2' ,align: 'center', minWidth: 70},"
			+ "  { display: '1#泵运行状态', name: 'EFFLUX_PUMP_STATE_1' ,align: 'center', minWidth: 70},"
			+ "  { display: '2#泵运行状态', name: 'EFFLUX_PUMP_STATE_2' ,align: 'center', minWidth: 70},"
			+ "  { display: '日产量', name: 'DAILY_OUTPUT' ,align: 'center', minWidth:80}"
//			+ "  { display: '启停状态', name: 'REV_STOP_STATE', align: 'center',minWidth: 60}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'stationrpd_searchBlockstationData.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
//湿蒸汽锅炉动态数据信息
	
	public static final String SRGL_RD_PAGE_GRID = " columns: ["
			+ "  { display: '区块', name: 'BLOCK_NAME' ,align: 'center', minWidth: 70,frozen:true},"
			+ "  { display: '接转战名称 ', name: 'BLOCKSTATION_NAME' ,align: 'center', minWidth: 70,hide:true},"
			+ "  { display: '锅炉名称', name: 'BOILER_NAME' ,align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '采集时间', name: 'ACQUISITION_TIME' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '运行状态', name: 'RUN_STATUS' ,align: 'center', minWidth: 70,frozen:true},"
			+ "  { display: '天然气', columns:"
			+ "	["
			+ "  { display: '天然气分离器压差(KPa)', name: 'GAS_INTO_PRESS' ,align: 'center', minWidth: 130},"
			+ "  { display: '天然气阀前压力(MPa)', name: 'GAS1_PRESS' ,align: 'center', minWidth: 130},"
			+ "	 { display: '火量(%)', name: 'FIRE_MEASURE' ,align: 'center', minWidth: 60},"
			+ "  { display: '天然气流量(m3/h)', name: 'GAS_FLOW' ,align: 'center', minWidth: 110}"
			+ "	]},"
			+ "  { display: '锅炉给水',columns: ["
			+ "  { display: '给水压力(MPa)', name: 'PUMPIN_PRESS' ,align: 'center', minWidth: 100},"
			+ "  { display: '给水温度(℃)', name: 'PUMPOUT_TEMP' ,align: 'center', minWidth: 120},"
			+ "  { display: '柱塞泵变频频率(Hz)', name: 'PUMPFC_FREQUENCY' ,align: 'center', minWidth: 130},"
			+ "  { display: '给水流量(t/h)', name: 'WATERSUPPLY_FLOW' ,align: 'center', minWidth: 90}"
			+"]},"  
			+ "{display:'蒸汽性质', columns:["
			+ "  { display: '蒸汽出口压力(MPa)', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 120},"
			+ "  { display: '蒸汽出口温度(℃)', name: 'STEAMOUT_TEMP' ,align: 'center', minWidth: 110},"
			+ "  { display: '蒸汽出口干度(%)', name: 'STEAM_DRYNESS' ,align: 'center', minWidth: 110}"
			+"]},"
			+ "  { display: '柱塞泵出口压力(MPa)', name: 'PUMPOUT_PRESS' ,align: 'center', minWidth: 120},"
			+" {display:'对流段',columns:["
			+ "  { display: '对流段入口压力(MPa)', name: 'CSIN_PRESS' ,align: 'center', minWidth: 130},"
			+ "  { display: '对流段入口温度(℃)', name: 'CSIN_TEMP' ,align: 'center', minWidth: 130},"
			+ "  { display: '对流段出口压力(MPa)', name: 'CSOUT_PRESS' ,align: 'center', minWidth: 130},"
			+ "  { display: '对流段出口温度(℃)', name: 'CSOUT_TEMP' ,align: 'center', minWidth: 130},"
			+ "  { display: '对流段压降(MPa)', name: 'CONVECTIONR_PRESS_REDUCTION' ,align: 'center', minWidth: 130}"
			+"]},"
			+"{display :'辐射段', columns:["
			+ "  { display: '辐射段入口压力(MPa)', name: 'RSIN_PRESS' ,align: 'center', minWidth: 130},"
			+ "  { display: '辐射段入口温度(℃)', name: 'RSIN_TEMP' ,align: 'center', minWidth: 130},"
			+ "  { display: '辐射段压降(MPa)', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 120},"
			+ "  { display: '辐射段出口管温(℃)', name: 'RS_TEMP' ,align: 'center', minWidth: 120}"
			+"]},"
			+"{display:'天然气', columns:["
			+ "  { display: '天然气阀后压力(MPa)', name: 'GAS2_PRESS' ,align: 'center', minWidth: 140},"
			+ "  { display: '膨胀管压力(MPa)', name: 'GAS3_PRESS' ,align: 'center', minWidth: 120}"
			+"]},"
			+ "  { display: '润滑油压力(MPa)', name: 'LUBE_PRESS' ,align: 'center', minWidth: 110},"
			+ "  { display: '润滑油温度(℃)', name: 'LUBE_TEMP' ,align: 'center', minWidth: 110},"
			+ "  { display: '鼓风机进风温度(℃)', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 120},"
			+ "  { display: '燃烧器喉温(℃)', name: 'BURNER_TEMP' ,align: 'center', minWidth: 110},"
			+ "  { display: '炉膛压力(Pa)', name: 'HEARTH_PRESS' ,align: 'center', minWidth: 110},"
			+ "  { display: '排烟温度(℃)', name: 'EJECTSMOKE_TEMP' ,align: 'center', minWidth: 110},"
			+"{display:'电机参数',columns:["
			+ "  { display: '电网电压(V)', name: 'SYSTEM_VOLTAGE' ,align: 'center', minWidth: 110},"
			+ "  { display: '柱塞泵电流(A)', name: 'PUMP_MOTOR_CURRENT' ,align: 'center', minWidth: 110},"
			+ "  { display: '柱塞泵电机温度(℃)', name: 'PUMP_MOTOR_TEMP' ,align: 'center', minWidth: 120},"
			+ "  { display: '鼓风机电流(A)', name: 'FAN_MOTOR_CURRENT' ,align: 'center', minWidth: 110}"
			+"]},"
			+ "  { display: '锅炉房可燃气体浓度(%)', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 120},"
			+ "  { display: '锅炉房H2S气体浓度(ppm)', name: 'H2S_DENSITY' ,align: 'center', minWidth: 120},"
			+ "  { display: '污水缓冲罐液位（m）', name: 'SEWAGE_BUFFER_TANK' ,align: 'center', minWidth: 120},"
			+ "  { display: '供气单位', name: 'STEAM_WORK_GROUP' ,align: 'center', minWidth: 70},"
			//+ "  { display: '采油站', name: 'OILDRILLING_STATION_NAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '受汽单位', name: 'OILDRILLING_STATION_NAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '注汽单位', name: 'STEAM_INJE_UNIT' ,align: 'center', minWidth: 70}"
			//+ "  { display: '湿蒸汽锅炉动态数据ID', name: 'RD_BOILER_COMMON_ID',align: 'center', minWidth: 120,hide: true},"
			//+ "  { display: '锅炉ID', name: 'BOILERID',align: 'center', minWidth: 120,hide: true}"
			
			/*+ "  { display: '接转站名称', name: 'BLOCKSTATION_NAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '累积水量低四位', name: 'WATERSUPPLY_TOTAL_L' ,align: 'center', minWidth: 90},"
			+ "  { display: '累积水量高五位', name: 'WATERSUPPLY_TOTAL_H' ,align: 'center', minWidth: 90},"
			+ "  { display: '润滑油压力', name: 'LUBE_PRESS' ,align: 'center', minWidth: 70},"
			+ "  { display: '暖气压力', name: 'CENTRAL_HEAT_PRESS' ,align: 'center', minWidth: 60},"
			+ "  { display: '辐射段出口管温', name: 'RS_TEMP' ,align: 'center', minWidth: 70},"
			+ "  { display: '热风温度', name: 'HOTBLAST_TEMP' ,align: 'center', minWidth: 70},"
			+ "  { display: '泵进口温度', name: 'PUMPIN_TEMP' ,align: 'center', minWidth: 70},"
			+ "  { display: '对流段入口温度', name: '	CSIN_TEMP' ,align: 'center', minWidth: 70},"
			+ "  { display: '蒸汽出口压力', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 70},"
			
			+ "  { display: '膨胀管压力', name: 'GAS3_PRESS' ,align: 'center', minWidth: 70},"
			+ "  { display: '检漏压力', name: 'LEAK_HUNT_PRESS' ,align: 'center', minWidth: 70},"
			+ "  { display: '天然气累积量', name: 'GAS_TOTAL' ,align: 'center', minWidth: 70},"
			+ "  { display: '硫化氢浓度', name: 'H2S_DENSITY' ,align: 'center', minWidth: 70},"
			+ "  { display: '可燃气体浓度', name: 'FUEL GAS_DENSITY' ,align: 'center', minWidth: 70},"
			+ "  { display: '蒸汽出口干度', name: 'STEAM_DRYNESS' ,align: 'center', minWidth: 70},"
			+ "  { display: '辐射段压降', name: 'RS_PRESS_REDUCTION	' ,align: 'center', minWidth: 70},"*/
			
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'srglrd_searchSRGLRD.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
//过热锅炉动态数据信息
	
	public static final String BOILER_CROSS_RD_PAGE_GRID = " columns: ["
			+ "  { display: '所属联合站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属区块', name: 'AREABLOCK',align: 'center', minWidth: 60},"
			+ "  { display: '所属供热站', name: 'BLOCKSTATIONNAME',align: 'center', minWidth: 70},"
			+ "  { display: '所属供热站ID', name: 'GR_ID',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '供气单位', name: 'YXZ',align: 'center', minWidth: 70},"
			+ "  { display: '运行组ID', name: 'YXZID',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '过热锅炉动态数ID', name: 'BOILER_CROSSRID',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '锅炉', name: 'BOILER_NAME',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '锅炉编号', name: 'GLBH' ,align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '站号', name: 'STATIONNO' ,align: 'center', minWidth: 40},"
			+ "  { display: '蒸汽出口温度', name: 'STEAMOUT_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '蒸汽出口压力', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '排烟温度', name: 'EJECTSMOKE_TEMP' ,align: 'center', minWidth: 60},"
			+ "  { display: '燃烧器温度', name: 'BURNER_TEMP', align: 'center',minWidth: 70},"
			+ "  { display: '对流段进口压力', name: 'CSIN_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段出口压力', name: 'CSOUT_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段进口温度', name: 'CSIN_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段出口温度', name: 'CSOUT_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '分离器液位', name: 'SL_LEVEL' ,align: 'center', minWidth: 70},"
			+ "  { display: '过热段管温', name: 'SUPERHEAT_TEMP' ,align: 'center', minWidth: 70},"
			+ "  { display: '过热段压阻', name: 'SUPERHEAT_PIEZORESISTANCE' ,align: 'center', minWidth: 70},"
			+ "  { display: '过热入口温度', name: 'SUPERHEATIN_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '过热出口温度', name: 'SUPERHEATOUT_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '过热入口压力', name: 'SUPERHEATIN_PRESS' ,align: 'center', minWidth: 80},"
			+ "  { display: '过热出口压力', name: 'SUPERHEATOUT_PRESS' ,align: 'center', minWidth: 80},"
			+ "  { display: '过热入口流量', name: 'SUPERHEATIN_FLOW' ,align: 'center', minWidth: 80},"
			+ "  { display: '炉膛压力', name: 'HEARTH_PRESS' ,align: 'center', minWidth: 60},"
			+ "  { display: '燃气一级压力', name: 'GAS1_PRESS' ,align: 'center', minWidth: 80},"
			+ "  { display: '燃气二级压力', name: 'GAS2_PRESS' ,align: 'center', minWidth: 80},"
			+ "  { display: '燃气三级压力', name: 'GAS3_PRESS' ,align: 'center', minWidth: 80},"
			+ "  { display: '辐射段管温', name: 'RS_TEMP' ,align: 'center', minWidth: 70},"
			+ "  { display: '辐射段干度', name: 'RS_DRYNESS' ,align: 'center', minWidth: 70},"
			+ "  { display: '辐射段压阻', name: 'RS_PIEZORESISTANCE' ,align: 'center', minWidth: 70},"
			+ "  { display: '辐射段进口压力', name: 'RSIN_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段出口压力', name: 'RS_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段进口温度', name: 'RSIN_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段出口温度', name: 'RSOUT_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '泵A相流量', name: 'PUMPA_FLOW' ,align: 'center', minWidth: 70},"
			+ "  { display: '泵B相流量', name: 'PUMPB_FLOW' ,align: 'center', minWidth: 70},"
			+ "  { display: '泵C相流量', name: 'PUMPC_FLOW' ,align: 'center', minWidth: 70},"
			+ "  { display: '泵AC电压', name: 'PUMPA_PRESS' ,align: 'center', minWidth: 70},"
			+ "  { display: '泵AB电压', name: 'PUMPB_PRESS' ,align: 'center', minWidth: 70},"
			+ "  { display: '泵BC电压', name: 'PUMPC_PRESS' ,align: 'center', minWidth: 70},"
			+ "  { display: '风机A相电流', name: 'FANA_ELECTRICITY' ,align: 'center', minWidth: 80},"
			+ "  { display: '风机B相电流', name: 'FANB_ELECTRICITY' ,align: 'center', minWidth: 80},"
			+ "  { display: '风机C相电流', name: 'FANC_ELECTRICITY' ,align: 'center', minWidth: 80},"
			+ "  { display: '泵变频频率', name: 'PUMPFC_FREQUENCY' ,align: 'center', minWidth: 80},"
			+ "  { display: '泵进口压力', name: 'PUMPIN_PRESS' ,align: 'center', minWidth: 70},"
			+ "  { display: '泵出口压力', name: 'PUMPOUT_PRESS' ,align: 'center', minWidth: 70},"
			+ "  { display: '泵出口温度', name: 'PUMPIN_TEMP' ,align: 'center', minWidth: 70},"
			+ "  { display: '泵入口水流量', name: 'PUMPOUT_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '给水流量', name: 'WATERSUPPLY_FLOW' ,align: 'center', minWidth: 60},"
			+ "  { display: '给水量累计', name: 'WATERSUPPLY_TOTAL' ,align: 'center', minWidth: 70},"
			+ "  { display: '燃气流量', name: 'GAS_FLOW' ,align: 'center', minWidth: 60},"
			+ "  { display: '燃气量累计', name: 'GAS_TOTAL' ,align: 'center', minWidth: 70},"
			+ "  { display: '注汽量累计', name: 'STEAMINJECTION_TOTAL', align: 'center', width: 200, minWidth: 70}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'boilercrossrd_searchBoilerCrossRD.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
	
//过热锅炉动态数据信息
	
	public static final String BOILER_SUPERHEAT_RD_PAGE_GRID = " columns: ["
		+ "  { display: '区块', name: 'BLOCK_NAME' ,align: 'center', minWidth: 90,frozen:true},"
		+ "  { display: '锅炉名称', name: 'BOILER_NAME' ,align: 'center', minWidth: 100,frozen:true},"
		+ "  { display: '采集时间', name: 'ACQUISITION_TIME' ,align: 'center', minWidth: 150,frozen:true},"
		+ "  { display: '运行状态', name: 'RUN_STATUS' ,align: 'center', minWidth: 70,frozen:true},"
		+ "  { display: '天然气', columns:"
		+ "	["
		+ "  { display: '站区来气压力(MPa)', name: 'GAS_INTO_PRESS' ,align: 'center', minWidth: 110},"
		+ "	 { display: '阀前压力(MPa)', name: 'GAS1_PRESS' ,align: 'center', minWidth: 100},"
		+ "	 { display: '火量(%)', name: 'FIRE_MEASURE' ,align: 'center', minWidth: 100},"
		+ "  { display: '天然气流量(m3/h)', name: 'GAS_FLOW' ,align: 'center', minWidth: 100}"
		+ "	]},"
		+ "  { display: '锅炉给水',columns: ["
		+ "  { display: '给水压力(MPa)', name: 'PUMPIN_PRESS' ,align: 'center', minWidth: 100},"
		+ "  { display: '给水温度(℃)', name: 'PUMPOUT_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '柱塞泵变频频率(Hz)', name: 'PUMPFC_FREQUENCY' ,align: 'center', minWidth: 110},"
		+ "  { display: '给水流量(t/h)', name: 'WATERSUPPLY_FLOW' ,align: 'center', minWidth: 100}"
		+"	]},"  
		+ "{display:'蒸汽性质', columns:["
		+ "  { display: '蒸汽出口压力(MPa)', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 110},"
		+ "  { display: '蒸汽出口温度(℃)', name: 'STEAMOUT_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '过热度(℃)', name: 'SUPERHEAT_DEGREE' ,align: 'center', minWidth: 100}"
		+"]},"
		+" {display:'运行关键参数',columns:["
		+ "  { display: '过热段出口温度(℃)', name: 'SUPERHEATOUT_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '分离器液位(mm)', name: 'SL_LEVEL' ,align: 'center', minWidth: 100},"
		+ "  { display: '液位调节阀开度', name: 'LEVEL_TJFKD' ,align: 'center', minWidth: 100},"		
		+ "  { display: '辐射段压降(MPa)', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 100},"
		+ "  { display: '过热段压降(MPa)', name: 'SUPERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 100},"
		+ "  { display: '掺混器压降(MPa)', name: 'MIXER_PRESS_REDUCTION' ,align: 'center', minWidth: 100}"
		+"]},"
		+ "  { display: '柱塞泵出口压力(MPa)', name: 'PUMPOUT_PRESS' ,align: 'center', minWidth: 0100},"
		+" {display:'对流段',columns:["
		+ "  { display: '对流段入口压力(MPa)', name: 'CSIN_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '对流段入口温度(℃)', name: 'CSIN_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '对流段出口压力(MPa)', name: 'CSOUT_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '对流段出口温度(℃)', name: 'CSOUT_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '对流段压降(MPa)', name: 'CONVECTIONR_PRESS_REDUCTION' ,align: 'center', minWidth: 100}"
		+"]},"
		+"{display :'辐射段', columns:["
		+ "  { display: '辐射段入口压力(MPa)', name: 'RSIN_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '辐射段入口温度(℃)', name: 'RSIN_TEMP' ,align: 'center', minWidth:120},"
		+ "  { display: '辐射段出口压力(MPa)', name: 'RS_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '辐射段出口温度(℃)', name: 'RSOUT_TEMP' ,align: 'center', minWidth:120},"
		//+ "  { display: '辐射段压降(MPa)', name: 'RS_PRESS_REDUCTION	' ,align: 'center', minWidth: 100},"
		+ "  { display: '辐射段出口管温(℃)', name: 'RS_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '辐射段出口干度(%)', name: 'RS_DRYNESS' ,align: 'center', minWidth: 120}"
		+"]},"
		+ "  { display: '分离器出口压力(MPa)', name: 'SEPARATOR_PRESS_EXPORT' ,align: 'center', minWidth: 120},"
		+"{display :'过热段', columns:["
		+ "  { display: '过热段入口压力(MPa)', name: 'SUPERHEATIN_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '过热段入口温度(℃)', name: 'SUPERHEATIN_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '过热段出口压力(MPa)', name: 'SUPERHEATOUT_PRESS' ,align: 'center', minWidth: 120},"
		//+ "  { display: '过热段出口温度(℃)', name: 'SUPERHEATOUT_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '过热段出口管温(℃)', name: 'SUPERHEAT_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '过热段蒸汽流量(t/h)', name: 'SUPERHEATIN_FLOW' ,align: 'center', minWidth: 120}"
		+"]},"
		+ "  { display: '掺混水压力(MPa)', name: 'MIXER_PRESS_WATER' ,align: 'center', minWidth: 120},"
		+"{display:'天然气', columns:["
		+ "  { display: '天然气阀后压力(MPa)', name: 'GAS2_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '膨胀管压力(MPa)', name: 'GAS3_PRESS' ,align: 'center', minWidth: 100}"
		+"]},"
		+ "  { display: '润滑油温度(℃)', name: 'LUBE_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '助燃风压力(MPa)', name: 'FAN_AIR_EXPORT_PRESS' ,align: 'center', minWidth: 100},"
		+ "  { display: '鼓风机进风温度(℃)', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '燃烧器喉温(℃)', name: 'BURNER_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '炉膛压力(MPa)', name: 'HEARTH_PRESS' ,align: 'center', minWidth: 60},"
		+ "  { display: '排烟温度(℃)', name: 'EJECTSMOKE_TEMP' ,align: 'center', minWidth: 100},"
		+"{display:'电机参数',columns:["
		+ "  { display: '电网电压(V)', name: 'SYSTEM_VOLTAGE' ,align: 'center', minWidth: 100},"
		+ "  { display: '柱塞泵电流(A)', name: 'PUMP_MOTOR_CURRENT' ,align: 'center', minWidth: 100},"
		+ "  { display: '柱塞泵电机温度(℃)', name: 'PUMP_MOTOR_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '鼓风机电流(A)', name: 'FAN_MOTOR_CURRENT' ,align: 'center', minWidth: 100}"
		+"]},"
		+ "  { display: '锅炉房可燃气体浓度(%)', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 110},"
		+ "  { display: '锅炉房H2S气体浓度(ppm)', name: 'H2S_DENSITY' ,align: 'center', minWidth: 110},"
		+ "  { display: '污水缓冲罐液位（m）', name: 'SEWAGE_BUFFER_TANK' ,align: 'center', minWidth: 120},"
		+ "  { display: '供气单位', name: 'STEAM_WORK_GROUP' ,align: 'center', minWidth: 100},"
		+ "  { display: '受汽单位', name: 'OILDRILLING_STATION_NAME' ,align: 'center', minWidth: 90},"
		+ "  { display: '注汽单位', name: 'STEAM_INJE_UNIT' ,align: 'center', minWidth: 90}"
		
		+ " ],"+

		"height:'100%',"+
		"dataAction: 'server',"+
		"url:'boilersuperheatrd_searchBoilerCrossRD.action',"+
		"delayLoad :true, "+
		"pageParmName :'pageNo',"+
		"sortnameParmName:'sort',"+
		"sortorderParmName: 'order',  "+
		"pagesizeParmName:'rowsPerpage', "+
		"selectRowButtonOnly:true,"+
		"sePaper:true,"+
		"pageSize:30 ,"+
		"rownumbers:true ,"+
		"frozen:true, "+
		"checkbox :false";
	//高干度锅炉动态数据
	public static final String GGDBOILER_RD_PAGE_GRID = " columns: ["
		+ "  { display: '受汽区块', name: 'BLOCK_NAME' ,align: 'center', minWidth: 100,frozen:true},"
		+ "  { display: '锅炉名称', name: 'BOILER_NAME' ,align: 'center', minWidth: 100,frozen:true},"
		+ "  { display: '采集时间', name: 'ACQUISITION_TIME' ,align: 'center', minWidth: 130,frozen:true},"
		+ "  { display: '运行状态', name: 'RUN_STATUS' ,align: 'center', minWidth: 80,frozen:true},"
		//+ "  { display: '运行时间', name: 'RUNTIME' ,align: 'center', minWidth: 80},"
		//+ "  { display: '减少掺混器压降', name: 'MIXER_PRESS_REDUCTION' ,align: 'center', minWidth: 90},"
		+ "  { display: '天然气', columns:"
		+ "	["
		+ "  { display: '天然气分离器压差(kPa)', name: 'GAS_INTO_PRESS' ,align: 'center', minWidth: 80},"
		+ "  { display: '阀前压力(MPa)', name: 'GAS1_PRESS' ,align: 'center', minWidth: 60},"
		+ "	 { display: '火量(%)', name: 'FIRE_MEASURE' ,align: 'center', minWidth: 40},"
		+ "  { display: '天然气流量(m3/h)', name: 'GAS_FLOW' ,align: 'center', minWidth: 80}"
		+ "	]},"
		+ "  { display: '锅炉给水',columns: ["
		+ "  { display: '给水压力(MPa)', name: 'PUMPIN_PRESS' ,align: 'center', minWidth: 80},"
		+ "  { display: '给水温度(℃)', name: 'PUMPOUT_TEMP' ,align: 'center', minWidth: 80},"
		+ "  { display: '柱塞泵变频频率(Hz)', name: 'PUMPFC_FREQUENCY' ,align: 'center', minWidth: 90},"
		+ "  { display: '给水流量(t/h)', name: 'WATERSUPPLY_FLOW' ,align: 'center', minWidth: 60}"
		+"]},"  
		+ "{display:'蒸汽性质', columns:["
		+ "  { display: '蒸汽出口压力(MPa)', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 80},"
		+ "  { display: '蒸汽出口温度(℃)', name: 'STEAMOUT_TEMP' ,align: 'center', minWidth: 80},"
		+ "  { display: '蒸汽出口干度(%)', name: 'STEAM_DRYNESS' ,align: 'center', minWidth: 80}"
		+"]},"
//		+ "{display:'运行关键参数', columns:["
//		+ "  { display: '辐射段压降(MPa)', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
//		+ "  { display: '过热段压降(MPa)', name: 'SUPERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
//		+ "  { display: '再热段压降(MPa)', name: 'RERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
//		+ "  { display: '掺混器压降(MPa)', name: 'MIXER_PRESS_REDUCTION' ,align: 'center', minWidth: 80}"
//		+"]},"
		+ "  { display: '柱塞泵出口压力(MPa)', name: 'PUMPOUT_PRESS' ,align: 'center', minWidth: 90},"
		+" {display:'对流段',columns:["
		+ "  { display: '对流段入口压力(MPa)', name: 'CSIN_PRESS' ,align: 'center', minWidth: 90},"
		+ "  { display: '对流段入口温度(℃)', name: 'CSIN_TEMP' ,align: 'center', minWidth: 90},"
		+ "  { display: '对流段出口压力(MPa)', name: 'CSOUT_PRESS' ,align: 'center', minWidth: 90},"
		+ "  { display: '对流段出口温度(℃)', name: 'CSOUT_TEMP' ,align: 'center', minWidth: 90},"
		+ "  { display: '对流段压降(MPa)', name: 'CONVECTIONR_PRESS_REDUCTION' ,align: 'center', minWidth: 80}"
		+"]},"
		+"{display :'辐射段', columns:["
		+ "  { display: '辐射段入口压力(MPa)', name: 'RSIN_PRESS' ,align: 'center', minWidth: 90},"
		+ "  { display: '辐射段入口温度(℃)', name: 'RSIN_TEMP' ,align: 'center', minWidth: 90},"
		+ "  { display: '辐射段出口压力(MPa)', name: 'RS_PRESS' ,align: 'center', minWidth: 90},"
		+ "  { display: '辐射段出口温度(℃)', name: 'RSOUT_TEMP' ,align: 'center', minWidth: 90},"
		+ "  { display: '辐射段压降(Mpa)', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 90},"
		+ "  { display: '辐射段出口管温(℃)', name: 'RS_TEMP' ,align: 'center', minWidth: 90}"
		+"]},"
		+"{display :'再热段', columns:["
		+ "  { display: '再热段入口压力(MPa)', name: 'REHEAT_PRESS_ENTRANCE' ,align: 'center', minWidth: 90},"
		+ "  { display: '再热段入口温度(℃)', name: 'REHEAT_TEMP_ENTRANCE' ,align: 'center', minWidth: 90},"
		+ "  { display: '再热段出口管温(℃)', name: 'REHEAT_TEMP_EXPORT' ,align: 'center', minWidth: 90},"
		+ "  { display: '再热段压降(℃)', name: 'RERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 90}"
		+"]},"
		+"{display:'天然气', columns:["
		+ "  { display: '天然气阀后压力(MPa)', name: 'GAS2_PRESS' ,align: 'center', minWidth: 90},"
		+ "  { display: '膨胀管压力(MPa)', name: 'GAS3_PRESS' ,align: 'center', minWidth: 80}"
		+"]},"
		+ "  { display: '润滑油温度(℃)', name: 'LUBE_TEMP' ,align: 'center', minWidth: 80},"
		+ "  { display: '鼓风机进风温度', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 90},"
		+ "  { display: '燃烧器喉温(℃)', name: 'BURNER_TEMP' ,align: 'center', minWidth: 80},"
		+ "  { display: '炉膛压力(Pa)', name: 'HEARTH_PRESS' ,align: 'center', minWidth: 60},"
		+ "  { display: '排烟温度(℃)', name: 'EJECTSMOKE_TEMP' ,align: 'center', minWidth: 60},"
		+"{display:'电机参数',columns:["
		+ "  { display: '电网电压(V)', name: 'SYSTEM_VOLTAGE' ,align: 'center', minWidth: 60},"
		+ "  { display: '柱塞泵电流(A)', name: 'PUMP_MOTOR_CURRENT' ,align: 'center', minWidth: 80},"
		+ "  { display: '柱塞泵电机温度(℃)', name: 'PUMP_MOTOR_TEMP' ,align: 'center', minWidth: 90},"
		+ "  { display: '鼓风机电流(A)', name: 'FAN_MOTOR_CURRENT' ,align: 'center', minWidth: 80}"
		+"]},"
		+ "  { display: '锅炉房可燃气体浓度(%)', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 110},"
		+ "  { display: 'H2s气体浓度(ppm)', name: 'H2S_DENSITY' ,align: 'center', minWidth: 80},"
		+ "  { display: '污水缓冲罐液位（m）', name: 'SEWAGE_BUFFER_TANK' ,align: 'center', minWidth: 120},"
		//+ "  { display: '鼓风机进风温度', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 90},"
		//+ "  { display: '锅炉房可燃气体浓度', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 110},"
		//+ "  { display: 'H2s气体浓度', name: 'H2S_DENSITY' ,align: 'center', minWidth: 80},"
		//+ "  { display: '系统总压降', name: 'SYSTEM_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
		+ "  { display: '供气单位', name: 'STEAM_WORK_GROUP' ,align: 'center', minWidth: 50},"
		+ "  { display: '采油站', name: 'OILDRILLING_STATION_NAME' ,align: 'center', minWidth: 80},"
		+ "  { display: '注汽单位', name: 'STEAM_INJE_UNIT' ,align: 'center', minWidth: 80}"
		+ " ],"+
		"height:'100%',"+
		"dataAction: 'server',"+
		"url:'ggdglrd_searchGGDRD.action',"+
		"delayLoad :true, "+
		"pageParmName :'pageNo',"+
		"sortnameParmName:'sort',"+
		"sortorderParmName: 'order',  "+
		"pagesizeParmName:'rowsPerpage', "+
		"selectRowButtonOnly:true,"+
		"sePaper:true,"+
		"pageSize:30 ,"+
		"rownumbers:true ,"+
		"frozen:true, "+
		"checkbox :false";
	
//管汇点动态数据信息
	
	public static final String Manifold_RD_PAGE_GRID = " columns: ["
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属区块', name: 'AREABLOCK',align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME',align: 'center', minWidth: 70},"
			+ "  { display: '分线', name: 'BRANCHINGNAME' ,align: 'center', minWidth: 40},"
			+ "  { display: '站号id', name: 'GRZHID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '分线ID', name: 'BRANCHINGID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '区块ID', name: 'QKID',align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '管汇ID', name: 'MANIFOLDID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: 'MANIFOLDRID', name: 'MANIFOLDRID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '管汇点', name: 'PIPE_SINK' ,align: 'center', minWidth: 40,frozen:true},"
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '区块', name: 'BLOCK' ,align: 'center', minWidth: 40},"
			+ "  { display: '站号', name: 'STATIONNO' ,align: 'center', minWidth: 40},"
			+ "  { display: '温度', name: 'TEMP' ,align: 'center', minWidth: 40},"
			+ "  { display: '压力', name: 'PRESS', align: 'center', minWidth: 40}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'manifoldrd_searchMainfoldRD.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
//采出液动态数据信息
	
	public static final String PRODUCED_METERING_RD_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 130,frozen:true},"
			+ "  { display: '管汇点号', name: 'GHD' ,align: 'center', minWidth: 100},"
			+ "  { display: '管汇代码', name: 'GHDH',align: 'center', minWidth: 50},"
			+ "  { display: '测量井号', name: 'WELL_NAME' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'ZHZH' ,align: 'center', minWidth: 100},"
			+ "  { display: '计量站号', name: 'JLZH' ,align: 'center', minWidth: 50},"
			+ "  { display: '所属采油站', name: 'CYZH' ,align: 'center', minWidth: 90},"
			+ "  { display: '所属区块', name: 'QK',align: 'center', minWidth: 60},"
			
			//+ "  { display: '采油站ID', name: 'OIL_ORGID' ,align: 'center', minWidth: 120,hide: true},"
			//+ "  { display: '分线ID', name: 'BRANCHINGID' ,align: 'center', minWidth: 120,hide: true},"
			//+ "  { display: '区块ID', name: 'QKID',align: 'center', minWidth: 120,hide: true},"
			//+ "  { display: '管汇ID', name: 'ORG_ID' ,align: 'center', minWidth: 120,hide: true},"
			//+ "  { display: '计量站ID', name: 'STATION_ORGID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '采出液计量动态数ID', name: 'LIQUID' ,align: 'center', minWidth: 120,hide: true},"
			+ "  { display: '多通阀号', name: 'DTFH', align: 'center', width: 20, minWidth: 50},"
			+ "  { display: '对应通道号', name: 'GHH', align: 'center',minWidth: 40},"
			+ "  { display: '产量', name: 'CL' ,align: 'center', minWidth: 40},"
			+ "  { display: '测量时间', name: 'CLSJ' ,align: 'center', minWidth: 40},"
			+ "  { display: '设定时间', name: 'SDSJ' ,align: 'center', minWidth: 40},"
			//+ "  { display: '井号名称', name: 'WELL_NAME' ,align: 'center', minWidth: 150},"
			+ "  { display: '下位机计量出值时间', name: 'JLCZSJ' ,align: 'center', minWidth: 130},"
			+ "  { display: '设定计量次数', name: 'PLAN_METERING_TIMES' ,align: 'center', minWidth: 70},"
			+ "  { display: '计量运行次数', name: 'RUN_METERING_TIMES' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属单位', name: 'DWMC' ,align: 'center', minWidth: 90},"
			+ "  { display: '分线', name: 'BRANCHING_NAME' ,align: 'center', minWidth: 60}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'producedrd_searchProducedRD.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
//观察井动态数据信息(待补)
	
//注水井动态数据信息
	 
	public static final String WATERFLOODING_RD_PAGE_GRID = " columns: ["
		+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 90},"
		+ "  { display: '所属班组', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 110},"
		+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
		+ "  { display: '所属注水撬ID', name: 'GH_ID' ,align: 'center', minWidth: 120,hide: true},"
		+ "  { display: '所属注水撬 ', name: 'WATERINJECTIONTOPR' ,align: 'center', minWidth: 70},"
		+ "  { display: '注水井ID', name: 'WATERFLOODING_WELLID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
		+ "  { display: '注水井动态数据ID', name: 'WATERFLOODING_WELLRID', align: 'center', width: 200, minWidth: 60,hide: true},"
		+ "  { display: '区块ID', name: 'QKID',align: 'center', minWidth: 120,hide: true},"
		+ "  { display: '井号代码', name: 'JHS',align: 'center', minWidth: 60,frozen:true},"
		+ "  { display: '采集时间', name: 'CJSJ',align: 'center', minWidth: 150,frozen:true},"
		+ "  { display: '总线压力', name: 'BUS_PRES',align: 'center', minWidth: 60},"
		+ "  { display: '压力', name: 'PRES',align: 'center', minWidth: 40},"
		+ "  { display: '瞬时流量', name: 'INSTANTANEOUS_DELIVERY'  ,align: 'center', minWidth: 60},"
		+ "  { display: '累积流量', name: 'CUMULATIVE_DISCHARGE',align: 'center', minWidth: 60}"
		+ " ],"+

		"height:'100%',"+
		"dataAction: 'server',"+
		"url:'waterFLRD_searchwaterFLRD.action',"+	
		"delayLoad :true, "+
		"pageParmName :'pageNo',"+
		"sortnameParmName:'sort',"+
		"sortorderParmName: 'order',  "+
		"pagesizeParmName:'rowsPerpage', "+
		"selectRowButtonOnly:true,"+
		"sePaper:true,"+
		"pageSize:30 ,"+
		"rownumbers:true ,"+
		"frozen:true, "+
		"checkbox :false";
//异常井动态数据信息
	
	public static final String YCWELL_RD_PAGE_GRID = " columns: ["
			+ "  { display: '井号名称', name: 'JHMC' ,align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '日期', name: 'RQ', align: 'center', width: 200,align: 'center', minWidth: 150 ,frozen:true},"
			+ "  { display: '井号代码', name: 'JHDM' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
			//+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 120},"
			
			+ "  { display: '单位名称', name: 'DWMC' ,align: 'center', minWidth: 60},"
			+ "  { display: '单位代码', name: 'DWDM' ,align: 'center', minWidth: 60},"
			+ "  { display: '站库名称', name: 'ZKMC', align: 'center', minWidth: 60},"
			
			+ "  { display: '日产液量', name: 'RCYL' ,align: 'center', minWidth: 60},"
			+ "  { display: '日注汽量', name: 'RZQL',align: 'center', minWidth: 60},"
			+ "  { display: '井口主管平均压力', name: 'ZGYL_PJ', align: 'center', minWidth: 100},"
			+ "  { display: '区块名称', name: 'QJMC',align: 'center', minWidth: 60},"
			+ "  { display: '区块代码', name: 'QKDM',align: 'center', minWidth: 60},"
			+ "  { display: '层位名称', name: 'CWMC',align: 'center', minWidth: 60},"
			+ "  { display: '层位代码', name: 'CWDM',align: 'center', minWidth: 60},"
			+ "  { display: '队别名称', name: 'DBMC',align: 'center', minWidth: 60},"
			+ "  { display: '队别代码', name: 'DBDM',align: 'center', minWidth: 60},"
			+ "  { display: '站库代码', name: 'ZKDM',align: 'center', minWidth: 60}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'ycwellRD_searchYCWell.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true,"+
			"checkbox :false";

	//SAGD井日报数据-全部
	public static final String SAGDDRPDALL_PAGE_GRID = " columns: ["
			+ "  { display: '井号名称', name: 'JHMC',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '采集时间', name: 'ACQUISITION_TIME',align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '井号编码', name: 'JHMC_S',align: 'center', minWidth: 60},"
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 60},"
			+ "  { display: 'SAGD井ID', name: 'SAGDID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
		
			
			+ " { display: '抽油机参数', columns:"
			+ "	["
			+ "		{ display: '电机状态', name: 'MOTORSTATUS', align: 'center', width: 100},"
			+ "		{ display: '电机A相电压(V)', name: 'MOTOR_PRESS_A', width: 100, align: 'center'},"
			+ "		{ display: '电机B相电压(V)', name: 'MOTOR_PRESS_B', width: 100, align: 'center'},"
			+ "		{ display: '电机C相电压(V)', name: 'MOTOR_PRESS_C', width: 100, align: 'center'},"
			+ "		{ display: '电机A相电流(A)', name: 'MOTOR_TEMP_A', width: 100, align: 'center'},"
			+ "		{ display: '电机B相电流(A)', name: 'MOTOR_TEMP_B', width: 100, align: 'center'},"
			+ "		{ display: '电机C相电流(A)', name: 'MOTOR_TEMP_C', width: 100, align: 'center'},"
			+ "		{ display: '有功电量(kWh)', name: 'ACTIVE_ENERGY', width: 90, align: 'center'},"
			+ "		{ display: '无功电量(kVar)', name: 'IDLE_ENERGY', width: 90, align: 'center'},"
			+ "		{ display: '功率因素(%)', name: 'POWER_FACTOR', width: 90, align: 'center'},"
			+ "		{ display: '变频电压(V)', name: 'CONVERSION_PRESS', width: 90, align: 'center'},"
			+ "		{ display: '变频电流(A)', name: 'CONVERSION_ELECTRICITY', width: 90, align: 'center'},"
			+ "		{ display: '当前频率(HZ)', name: 'NOW_FREQUENCY', width: 90, align: 'center'},"
			+ "		{ display: '冲程(m)', name: 'STROKE', width: 50, align: 'center'},"
			+ "		{ display: '冲次(次/分)', name: 'JIG_FREQUENCY', width: 50, align: 'center'},"
			//+ "		{ display: '脱抽饱和方式', name: 'BHFS', width: 100, align: 'center'}," //数据字典中没有该字段
			+ "		{ display: '报警状态', name: 'ALMSTATE', width: 60, align: 'center'},"
			//+ "		{ display: '最新频率(HZ)', name: 'ZXPL', width: 100, align: 'center'},"
			//+ "		{ display: '正常频率(HZ)', name: 'ZCPL', width: 100, align: 'center'},"
			
			+ "		{ display: '当前载荷', name: 'NOW_LOAD', width: 60, align: 'center'},"
			+ "		{ display: '电机运行模式', name: 'MOTOR_MODE', width: 80, align: 'center'},"
			+ "		{ display: '抽油机运行状态', name: 'PUMPING_RUNNING_STATE', width: 90, align: 'center'}"
			
			+ "	]},"
			
			
			+ " { display: '井口数据', columns:"
			+ "	["
			+ "		{ display: 'P井主管压力(MPa)', name: 'P_PRESSURE_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'P井主管温度(℃)', name: 'P_PRESSURE_TEMP', width: 100, align: 'center'},"
			+ "		{ display: 'P井副管压力(MPa)', name: 'P_LOOPED_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'P井副管温度(℃)', name: 'P_LOOPED_TEMP', width: 100, align: 'center'},"
			+ "		{ display: 'P井套管压力(MPa)', name: 'P_DRIVEPIPE_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'I井主管压力(MPa)', name: 'I_PRESSURE_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'I井主管温度(℃)', name: 'I_PRESSURE_TEMP', width: 100, align: 'center'},"
			+ "		{ display: 'I井副管压力(MPa)', name: 'I_LOOPED_PRESS', width: 100, align: 'center'},"
			+ "		{ display: 'I井副管温度(℃)', name: 'I_LOOPED_TEMP', width: 100, align: 'center'},"
			+ "		{ display: 'I井套管压力(MPa)', name: 'I_DRIVEPIPE_PRESS', width: 100, align: 'center'}"
			+ "	]},"
			
			+ " { display: '井下数据', columns:"
			+ "	["
			+ "		{ display: 'P井压力1(MPa)', name: 'P_PRESS1', align: 'center', width: 80},"
			+ "		{ display: 'P井压力2(MPa)', name: 'P_PRESS2', width: 80, align: 'center'},"
			+ "		{ display: 'P井温度1(℃)', name: 'P_TEMP1', align: 'center', width: 80},"
			+ "		{ display: 'P井温度2(℃)', name: 'P_TEMP2', width: 80, align: 'center'},"
			+ "		{ display: 'P井温度3(℃)', name: 'P_TEMP3', width: 80, align: 'center'},"
			+ "		{ display: 'P井温度4(℃)', name: 'P_TEMP4', align: 'center', width: 80},"
			+ "		{ display: 'P井温度5(℃)', name: 'P_TEMP5', width: 80, align: 'center'},"
			+ "		{ display: 'P井温度5(℃)', name: 'P_TEMP6', align: 'center', width: 80},"
			+ "		{ display: 'P井温度7(℃)', name: 'P_TEMP7', width: 80, align: 'center'},"
			+ "		{ display: 'P井温度8(℃)', name: 'P_TEMP8', width: 80, align: 'center'},"
			+ "		{ display: 'P井温度9(℃)', name: 'P_TEMP9', align: 'center', width: 80},"
			+ "		{ display: 'P井温度10(℃)', name: 'P_TEMP10', width: 80, align: 'center'},"
			+ "		{ display: 'P井温度11(℃)', name: 'P_TEMP11', align: 'center', width: 80},"
			+ "		{ display: 'P井温度12(℃)', name: 'P_TEMP12', width: 80, align: 'center'}"
			+ "	]},"
			
			+ " { display: '蒸汽计量数据', columns:"
			+ "	["
			+ "		{ display: '蒸汽压力1#(mPa)', name: 'NO1STEAM_PRESS', align: 'center', width: 100},"
			+ "		{ display: '蒸汽压力2#(mPa)', name: 'NO2STEAM_PRESS', width: 100, align: 'center'},"
			+ "		{ display: '蒸汽温度1#(℃)', name: 'NO1STEAM_TEMP', align: 'center', width: 100},"
			+ "		{ display: '蒸汽温度2#(℃)', name: 'NO2STEAM_TEMP', width: 100, align: 'center'},"
			+ "		{ display: '蒸汽差压1#(mPa)', name: 'NO1STEAM_DIFP', width: 100, align: 'center'},"
			+ "		{ display: '蒸汽差压2#(mPa)', name: 'NO2STEAM_DIFP', align: 'center', width: 100},"
			+ "		{ display: '瞬时干度1# (%)', name: 'NO1INSTANT_DRYNESS', width: 100, align: 'center'},"
			+ "		{ display: '瞬时干度2# (%)', name: 'NO2INSTANT_DRYNESS', align: 'center', width: 100},"
			+ "		{ display: '瞬时流量1#(T/H)', name: 'NO1INSTANT_FLOW', width: 100, align: 'center'},"
			+ "		{ display: '瞬时流量2#(T/H)', name: 'NO2INSTANT_FLOW', width: 100, align: 'center'},"
			+ "		{ display: '调节阀开度1#(%)', name: 'NO1CONTROL_APERTURE', align: 'center', width: 100},"
			+ "		{ display: '调节阀开度2#(%)', name: 'NO2CONTROL_APERTURE', width: 100, align: 'center'},"
			+ "		{ display: '阀控制状态1#', name: 'NO1CONTROL_STATUS', align: 'center', width: 100},"//阀控制状态1#
			+ "		{ display: '阀控制状态2#', name: 'NO2CONTROL_STATUS', width: 100, align: 'center'},"
			+ "		{ display: '阀控制开度1#(%)', name: 'CLIQUE_CONTROL_APERTURE1', width: 100, align: 'center'},"
			+ "		{ display: '阀控制开度2#(%)', name: 'CLIQUE_CONTROL_APERTURE2', align: 'center', width: 100},"
			+ "		{ display: '流量设定1#(T/H)', name: 'NO1FLOW_SET', width: 100, align: 'center'},"
			+ "		{ display: '流量设定2#(T/H)', name: 'NO2FLOW_SET', align: 'center', width: 100},"
//			+ "		{ display: '比例系数P 1#', name: 'BLXS1', width: 100, align: 'center'},"
//			+ "		{ display: '比例系数P 2#', name: 'BLXS2', width: 100, align: 'center'},"
//			+ "		{ display: '积分系数T 2#', name: 'JFXS1', width: 100, align: 'center'},"
//			+ "		{ display: '积分系数T 2#', name: 'JFXS2', align: 'center', width: 100},"
			+ "		{ display: '昨日累积流量1# (T)', name: 'YCUMULATIVE_FLOW1', width: 100, align: 'center'},"
			+ "		{ display: '昨日累积流量2# (T)', name: 'YCUMULATIVE_FLOW2', width: 100, align: 'center'},"
			+ "		{ display: '今日累积流量1# (T)', name: 'TCUMULATIVE_FLOW1', width: 100, align: 'center'},"
			+ "		{ display: '今日累积流量2# (T)', name: 'TCUMULATIVE_FLOW2', align: 'center', width: 100},"
			+ "		{ display: '总累积流量1# (T)', name: 'SUM_FLOW1', width: 100, align: 'center'},"
			+ "		{ display: '总累积流量2# (T)', name: 'SUM_FLOW2', align: 'center', width: 100}"
			+ "	]},"
			+ "  { display: '备注', name: 'REMARK', align: 'center', align: 'center',width: 255, minWidth: 100}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sagddrpd_searchSagdRPD.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
	
	//SAGD井日报注气井报表
	public static final String SAGDDRPDZQJ_PAGE_GRID = " columns: ["
			+ "{display: '井号名称', name: 'JHMC',align: 'center', minWidth: 80,frozen:true," +
						"totalSummary:" +
				        "{" +
				        "	type:'count'" + 
				        "}" +
					"},"
			+ "{display: '采集时间', name: 'REPORT_DATE',align: 'center', minWidth: 90,frozen:true},"
			/*+ "{ display: '井号编码', name: 'JHMC_S',align: 'center', minWidth: 60},"
			+ "{ display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "{ display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
			+ "{ display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "{ display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
			+ "{ display: '所属分线', name: 'BRANCHING_NAME' ,align: 'center', minWidth: 60},"*/
			
			//+ "{ display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			//+ "{ display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "{display: '注汽井', columns:"
			+ "	["
			+ "{ display: '注汽井注汽时率', name: 'I_INJE_TIME_RATION', align: 'center', width: 60, minWidth: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '注汽井(I井）生产时率', name: 'I_PROC_TIME_RATION', align: 'center', width: 75," +
					"totalSummary:" +
		            "{" +
		            "	type:'sum,avg'" + 
		            "}" +
		       "},"
			+ "{ display: '主管', columns:"
			+ "	["
			+ "		{display: '油嘴(mm)', name: 'I_P_FLOW_NIPPLE', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '油压(Mpa)', name: 'I_PRESSURE_PRESS', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '温度(℃)', name: 'I_PRESSURE_TEMP', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"}"
//			+ "		{display: '回压(Mpa)', name: 'ZGHY', width: 60, align: 'center'}"
			+ "	]},"
			+ "{ display: '副管', columns:"
			+ "	["
			+ "		{display: '油嘴(mm)', name: 'I_I_FLOW_NIPPLE', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '油压(Mpa)', name: 'I_LOOPED_PRESS', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '温度(℃)', name: 'I_LOOPED_TEMP', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"}"
//			+ "		{display: '回压(Mpa)', name: 'FGHY', width: 60, align: 'center'}"
			+ "	]},"
			+ "{display: 'I井回压(Mpa)', name: 'I_BACK_PRES' ,align: 'center', minWidth: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '套压(Mpa)', name: 'I_DRIVEPIPE_PRESS' ,align: 'center', minWidth: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '液量(t/d)', name: 'I_DAILY_OUTPUT' ,align: 'center', minWidth: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '油量(t/d)', name: 'I_DAILY_OIL_OUTPUT' ,align: 'center', minWidth: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '含水(%)', name: 'I_WATER_RATION' ,align: 'center', minWidth: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '瞬时流量(m3/h)', name: 'I_STEAM_FLOW', width: 70, align: 'center'," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{ display: '注汽量(t/d)', name: 'I_DAILY_CUMULATIVE_STEAM', align: 'center',minWidth: 70," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
           // + "{display: '关井原因', name: 'I_SHUTIN_CODE', align: 'center', align: 'center',width: 150, minWidth: 100},"
           // + "{display: '关井时间', name: 'I_SHUTIN_TIME', align: 'center', align: 'center',width: 150, minWidth: 100},"
           // + "{display: '开井时间', name: 'I_OPEN_TIME', align: 'center', align: 'center',width: 150, minWidth: 100},"
           // + "{display: '措施', name: 'I_STIMU_CODE', align: 'center', align: 'center',width: 150, minWidth: 100},"
			+ "{display: '备注', name: 'I_REMARK', align: 'center', align: 'center',width: 255, minWidth: 100}"
			+ "	]},"
			+ "{display: '生产井', columns:"
			+ "	["
			+ "{display: '生产井注汽时率', name: 'P_INJE_TIME_RATION', width: 70, align: 'center'," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '生产井(P井）生产时率', name: 'P_PROC_TIME_RATION', align: 'center', width: 80," +
					"totalSummary:" +
		            "{" +
		            "	type:'sum,avg'" + 
		            "}" +
			  "},"
			+ "{display: '抽油机运转时间', name: 'RUNTIME', width: 70, align: 'center'},"
			+ "{display: '抽油机故障时间', name: 'PUMP_ERROR_TIME', width: 70, align: 'center'},"
			+ "{ display: '主管', columns:"
			+ "	["
			+ "		{display: '泵径 (mm)', name: 'PUMP_DIAMETER', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '冲程 (m)', name: 'STROKE', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '冲次 (次)', name: 'JIG_FREQUENCY', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '油嘴(mm)', name: 'P_P_FLOW_NIPPLE', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '油压(Mpa)', name: 'P_PRESSURE_PRESS', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '温度(℃)', name: 'P_PRESSURE_TEMP', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"}"
//			+ "		{display: '回压(Mpa)', name: 'ZGHY', width: 60, align: 'center'}"
			+ "	]},"
			+ "{ display: '副管', columns:"
			+ "	["
			+ "		{display: '油嘴(mm)', name: 'P_I_FLOW_NIPPLE', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '油压(Mpa)', name: 'P_LOOPED_PRESS', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '温度(℃)', name: 'P_LOOPED_TEMP', align: 'center', width: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"}"
//			+ "		{display: '回压(Mpa)', name: 'FGHY', width: 60, align: 'center'}"
			+ "	]},"
			+ "{display: 'P井回压 (Mpa)', name: 'P_BACK_PRES' ,align: 'center', minWidth: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '套压(Mpa)', name: 'P_DRIVEPIPE_PRESS' ,align: 'center', minWidth: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '液量(t/d)', name: 'P_DAILY_OUTPUT' ,align: 'center', minWidth: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '油量(t/d)', name: 'P_DAILY_OIL_OUTPUT' ,align: 'center', minWidth: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '含水(%)', name: 'P_WATER_RATION' ,align: 'center', minWidth: 60," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '瞬时流量(m3/h)', name: 'P_STEAM_FLOW', width: 70, align: 'center'," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{ display: '注汽量(t/d)', name: 'P_DAILY_CUMULATIVE_STEAM', align: 'center',minWidth: 70," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{ display: '井下数据监控', columns:"
			+ "	["
			+ "		{display: '井温度1(℃)', name: 'P_TEMP1', align: 'center', width: 80," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度2(℃)', name: 'P_TEMP2', width: 80, align: 'center'," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度3(℃)', name: 'P_TEMP3', width: 80, align: 'center'," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度4(℃)', name: 'P_TEMP4', align: 'center', width: 80," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度5(℃)', name: 'P_TEMP5', width: 80, align: 'center'," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度6(℃)', name: 'P_TEMP6', align: 'center', width: 80," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度7(℃)', name: 'P_TEMP7', width: 80, align: 'center'," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度8(℃)', name: 'P_TEMP8', width: 80, align: 'center'," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度9(℃)', name: 'P_TEMP9', align: 'center', width: 80," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度10(℃)', name: 'P_TEMP10', width: 80, align: 'center'," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度11(℃)', name: 'P_TEMP11', align: 'center', width: 80," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度12(℃)', name: 'P_TEMP12', width: 80, align: 'center'," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"}"
//			+ "		{display: '井压力1(MPa)', name: 'P_PRESS1', align: 'center', width: 80," +
//			"totalSummary:" +
//            "{" +
//            "	type:'sum,avg'" + 
//            "}" +
//					"},"
//			+ "		{display: '井压力2(MPa)', name: 'P_PRESS2', width: 80, align: 'center'," +
//			"totalSummary:" +
//            "{" +
//            "	type:'sum,avg'" + 
//            "}" +
//					"}"
			+ "	]},"
			//+ "{display: '关井原因', name: 'P_SHUTIN_CODE', align: 'center', align: 'center',width: 150, minWidth: 100},"
            //+ "{display: '关井时间', name: 'P_SHUTIN_TIME', align: 'center', align: 'center',width: 150, minWidth: 100},"
            //+ "{display: '开井时间', name: 'P_OPEN_TIME', align: 'center', align: 'center',width: 150, minWidth: 100},"
           // + "{display: '措施', name: 'P_STIMU_CODE', align: 'center', align: 'center',width: 150, minWidth: 100},"
			+ "{display: '备注', name: 'REMARK', align: 'center', align: 'center',width: 255, minWidth: 100}"
			+ "	]},"
			+" { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70}," 
			+ "{ display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ "{ display: '审核人', name: 'VERIFIER', align: 'center',  minWidth: 90},"
			+ "{ display: '审核时间', name: 'VERIFY_TIME', align: 'center', minWidth: 150},"
			+ "{ display: '功图', name: 'WELLEROM', align: 'center', minWidth: 100, render: renderButton},"
			+ "{ display: 'SAGD日报ID', name: 'SAGDDID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "{ display: 'SAGD井ID', name: 'SAGDID', align: 'center', width: 200, minWidth: 60 ,hide: true}"
			+ "],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sagddrpd_searchSagdRPD.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			" onDblClickRow: function(data, rowindex, rowobj) {" +
			"		$.ligerDialog.open({" +
			" 			title: '显示SAGD日报详细信息'," +
			"  			url:'sagdrpdview.jsp?sagdid='+data.SAGDID+'&sagddid='+data.SAGDDID+'&jhmc='+data.JHMC," +
			"  			width: 1000," +
			"  			height: 500" +
			"  			});" +
			"  } ";
	
	//SAGD井日报生产井报表
	public static final String SAGDDRPDSCJ_PAGE_GRID = " columns: ["
			+ "  { display: '井号名称', name: 'JHMC',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '采集时间', name: 'ACQUISITION_TIME',align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '井号编码', name: 'JHMC_S',align: 'center', minWidth: 60},"
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 60},"
			+ "  { display: 'SAGD井ID', name: 'SAGDID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			
			+ "  { display: '巡检时间', name: 'XJTINME' ,align: 'center', minWidth: 150},"
			+ "  { display: '生产小时(h)', name: 'SCHOUR' ,align: 'center', minWidth: 60},"
		
			
			+ " { display: '主管', columns:"
			+ "	["
			+ "		{ display: '油压(Mpa)', name: 'ZGYY', align: 'center', width: 60},"
			+ "		{ display: '回压(Mpa)', name: 'ZGHY', width: 60, align: 'center'},"
			+ "		{ display: '温度(℃)', name: 'ZGWD', width: 60, align: 'center'}"
			+ "	]},"
			
			+ " { display: '副管', columns:"
			+ "	["
			+ "		{ display: '油咀(mm)', name: 'FGYZ', align: 'center', width: 60},"
			+ "		{ display: '油压(Mpa)', name: 'FGYY', align: 'center', width: 60},"
			+ "		{ display: '回压(Mpa)', name: 'FGHY', width: 60, align: 'center'},"
			+ "		{ display: '温度(℃)', name: 'FGWD', width: 60, align: 'center'}"
			+ "	]},"
			
			+ "  { display: '套压(Mpa)', name: 'TY' ,align: 'center', minWidth: 60},"
			+ "  { display: '液量(t)', name: 'YL' ,align: 'center', minWidth: 60},"
			
			+ " { display: '蒸汽计量', columns:"
			+ "	["
			+ "		{ display: '干度(%)', name: 'GD', align: 'center', width: 60},"
			+ "		{ display: '压力(MPa)', name: 'YL', align: 'center', width: 60},"
			+ "		{ display: '瞬时流量(t/h)', name: 'SSLL', width: 80, align: 'center'}"
			+ "	]},"
			
			+ " { display: '井下检测报表', columns:"
			+ "	["
			+ "		{ display: 'P井温度1(℃)', name: 'P_TEMP1', align: 'center', width: 100},"
			+ "		{ display: 'P井温度2(℃)', name: 'P_TEMP2', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度3(℃)', name: 'P_TEMP3', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度4(℃)', name: 'P_TEMP4', align: 'center', width: 100},"
			+ "		{ display: 'P井温度5(℃)', name: 'P_TEMP5', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度5(℃)', name: 'P_TEMP6', align: 'center', width: 100},"
			+ "		{ display: 'P井温度7(℃)', name: 'P_TEMP7', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度8(℃)', name: 'P_TEMP8', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度9(℃)', name: 'P_TEMP9', align: 'center', width: 100},"
			+ "		{ display: 'P井温度10(℃)', name: 'P_TEMP10', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度11(℃)', name: 'P_TEMP11', align: 'center', width: 100},"
			+ "		{ display: 'P井温度12(℃)', name: 'P_TEMP12', width: 100, align: 'center'}"
			+ "	]},"
			
			+ "  { display: '备注', name: 'REMARK', align: 'center', align: 'center',width: 255, minWidth: 100}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sagddrpd_searchSagddRPD.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
	
	//SAGD井日报维护数据-全部
	public static final String SAGDDRPDWHALL_PAGE_GRID = " columns: ["
		+ "{display: '井号名称', name: 'JHMC',align: 'center', minWidth: 80,frozen:true," +
				"totalSummary:" +
		        "{" +
		        "	type:'count'" + 
		        "}" +
		  "},"
		+ "{display: '日报日期', name: 'REPORT_DATE', minWidth: 90,align: 'center',frozen:true},"
		+ "{display: '井号名称', name: 'JHMC',align: 'center', minWidth: 80,editor: { type: 'select', data: wellData, valueField: 'text', textField: 'text'}},"
			
			//+ "{ display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "{display: '注汽井', columns:"
			+ "	["
			+ "{ display: '注汽井注汽时率', name: 'I_INJE_TIME_RATION', align: 'center', width: 60, minWidth: 60, editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
            + "{ display: '注汽井(I井）生产时率', name: 'I_PROC_TIME_RATION', align: 'center', width: 75, minWidth: 75, editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
			"},"
			+ "{ display: '主管', columns:"
			+ "	["
			+ "		{display: '油嘴(mm)', name: 'I_P_FLOW_NIPPLE', align: 'center', width: 60,editor: { type: 'float' }," +
						"totalSummary:" +
			            "{" +
			            "	type:'sum,avg'" + 
			            "}" +
					"},"
			+ "		{display: '油压(Mpa)', name: 'I_PRESSURE_PRESS', align: 'center', width: 60,editor: { type: 'float' }," +
						"totalSummary:" +
			            "{" +
			            "	type:'sum,avg'" + 
			            "}" +
					"},"
			+ "		{display: '温度(℃)', name: 'I_PRESSURE_TEMP', align: 'center', width: 60,editor: { type: 'float' }," +
						"totalSummary:" +
			            "{" +
			            "	type:'sum,avg'" + 
			            "}" +
					"}"
//			+ "		{display: '回压(Mpa)', name: 'ZGHY', width: 60, align: 'center'}"
			+ "	]},"
			+ "{ display: '副管', columns:"
			+ "	["
			+ "		{display: '油嘴(mm)', name: 'I_I_FLOW_NIPPLE', align: 'center', width: 60,editor: { type: 'float' }," +
						"totalSummary:" +
			            "{" +
			            "	type:'sum,avg'" + 
			            "}" +
					"},"
			+ "		{display: '油压(Mpa)', name: 'I_LOOPED_PRESS', align: 'center', width: 60,editor: { type: 'float' }," +
						"totalSummary:" +
			            "{" +
			            "	type:'sum,avg'" + 
			            "}" +
					"},"
			+ "		{display: '温度(℃)', name: 'I_LOOPED_TEMP', align: 'center', width: 60,editor: { type: 'float' }," +
						"totalSummary:" +
			            "{" +
			            "	type:'sum,avg'" + 
			            "}" +
					"}"
//			+ "		{display: '回压(Mpa)', name: 'FGHY', width: 60, align: 'center'}"
			+ "	]},"
			+ "{display: 'I井回压(Mpa)', name: 'I_BACK_PRES' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
						"totalSummary:" +
			            "{" +
			            "	type:'sum,avg'" + 
			            "}" +
					"},"
			+ "{display: '套压(Mpa)', name: 'I_DRIVEPIPE_PRESS' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
						"totalSummary:" +
			            "{" +
			            "	type:'sum,avg'" + 
			            "}" +
					"},"
			+ "{display: '液量(t/d)', name: 'I_DAILY_OUTPUT' ,align: 'center', minWidth: 60,editor: {type: 'float'}," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '油量(t/d)', name: 'I_DAILY_OIL_OUTPUT' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
						"totalSummary:" +
			            "{" +
			            "	type:'sum,avg'" + 
			            "}" +
					"},"
			+ "{display: '含水(%)', name: 'I_WATER_RATION' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
						"totalSummary:" +
			            "{" +
			            "	type:'sum,avg'" + 
			            "}" +
					"},"
			+ "{display: '瞬时流量(m3/h)', name: 'I_STEAM_FLOW', width: 70, align: 'center',editor: { type: 'float' }," +
						"totalSummary:" +
			            "{" +
			            "	type:'sum,avg'" + 
			            "}" +
					"},"
			+ "{ display: '注汽量(t/d)', name: 'I_DAILY_CUMULATIVE_STEAM', align: 'center',minWidth: 70,editor: { type: 'float' }," +
						"totalSummary:" +
			            "{" +
			            "	type:'sum,avg'" + 
			            "}" +
					"},"
//            + "{display: '关井原因', name: 'I_SHUTIN_CODE', align: 'center', align: 'center',width: 150, minWidth: 100,editor: { type: 'select', data: gjData, valueField: 'id', textField: 'text'}," +
//            		"render: function (item)"+
//                    "{"+
//                    "    for (var i = 0; i < gjData.length; i++)"+
//                    "    {"+
//                    "        if (gjData[i]['id'] == item.I_SHUTIN_CODE)"+
//                    "            return gjData[i]['text']"+
//                    "    }"+
//                    "    return item.I_SHUTIN_CODE;"+
//                    "}"+
//            		"},"
//                    // { display: '入职日期', name: 'IncomeDay', type: 'date', format: 'yyyy年MM月dd', width: 100, editor: { type: 'date'} },
//            + "{display: '关井时间', name: 'I_SHUTIN_TIME',type: 'text', width: 130, editor: { type: 'text'}},"
//            + "{display: '开井时间', name: 'I_OPEN_TIME', type: 'text', width: 130, editor: { type: 'text'}},"
//            + "{display: '措施', name: 'I_STIMU_CODE', align: 'center', align: 'center',width: 150, minWidth: 100,editor: { type: 'select', data: csData, valueField: 'id', textField: 'text'}," +
//		            "render: function (item)"+
//		            "{"+
//		            "    for (var i = 0; i < csData.length; i++)"+
//		            "    {"+
//		            "        if (csData[i]['id'] == item.I_STIMU_CODE)"+
//		            "            return csData[i]['text']"+
//		            "    }"+
//		            "    return item.I_STIMU_CODE;"+
//		            "}"+
//            		"},"
			+ "{display: '备注', name: 'I_REMARK', align: 'center', align: 'center',width: 255, minWidth: 100, editor: { type: 'text' }}"
			+ "	]},"
			+ "{display: '生产井', columns:"
			+ "	["
			+ "{display: '生产井注汽时率', name: 'P_INJE_TIME_RATION', width: 70, align: 'center', editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '生产井(P井）生产时率', name: 'P_PROC_TIME_RATION', align: 'center', width: 80,editor: { type: 'float' }," +
					"totalSummary:" +
		            "{" +
		            "	type:'sum,avg'" + 
		            "}" +
				"},"
			+ "{display: '抽油机运转时间', name: 'RUNTIME', width: 70, align: 'center', editor: { type: 'float' }},"
			+ "{display: '抽油机故障时间', name: 'PUMP_ERROR_TIME', width: 70, align: 'center', editor: { type: 'float' }},"
			+ "{ display: '主管', columns:"
			+ "	["
			+ "		{display: '泵径 (mm)', name: 'PUMP_DIAMETER', align: 'center', width: 60,editor: { type: 'float' }," +
						"totalSummary:" +
			            "{" +
			            "	type:'sum,avg'" + 
			            "}" +
					"},"
			+ "		{display: '冲程 (m)', name: 'STROKE', align: 'center', width: 60,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '冲次 (次)', name: 'JIG_FREQUENCY', align: 'center', width: 60,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '油嘴(mm)', name: 'P_P_FLOW_NIPPLE', align: 'center', width: 60,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '油压(Mpa)', name: 'P_PRESSURE_PRESS', align: 'center', width: 60,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '温度(℃)', name: 'P_PRESSURE_TEMP', align: 'center', width: 60,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"}"
//			+ "		{display: '回压(Mpa)', name: 'ZGHY', width: 60, align: 'center'}"
			+ "	]},"
			+ "{ display: '副管', columns:"
			+ "	["
			+ "		{display: '油嘴(mm)', name: 'P_I_FLOW_NIPPLE', align: 'center', width: 60,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '油压(Mpa)', name: 'P_LOOPED_PRESS', align: 'center', width: 60,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '温度(℃)', name: 'P_LOOPED_TEMP', align: 'center', width: 60,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"}"
//			+ "		{display: '回压(Mpa)', name: 'FGHY', width: 60, align: 'center'}"
			+ "	]},"
			+ "{display: 'P井回压 (Mpa)', name: 'P_BACK_PRES' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '套压(Mpa)', name: 'P_DRIVEPIPE_PRESS' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '液量(t/d)', name: 'P_DAILY_OUTPUT' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '油量(t/d)', name: 'P_DAILY_OIL_OUTPUT' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '含水(%)', name: 'P_WATER_RATION' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{display: '瞬时流量(m3/h)', name: 'P_STEAM_FLOW', width: 70, align: 'center',editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{ display: '注汽量(t/d)', name: 'P_DAILY_CUMULATIVE_STEAM', align: 'center',minWidth: 70,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "{ display: '井下数据监控', columns:"
			+ "	["
			+ "		{display: '井温度1(℃)', name: 'P_TEMP1', align: 'center', width: 80,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度2(℃)', name: 'P_TEMP2', width: 80, align: 'center',editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度3(℃)', name: 'P_TEMP3', width: 80, align: 'center',editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度4(℃)', name: 'P_TEMP4', align: 'center', width: 80,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度5(℃)', name: 'P_TEMP5', width: 80, align: 'center',editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度6(℃)', name: 'P_TEMP6', align: 'center', width: 80,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度7(℃)', name: 'P_TEMP7', width: 80, align: 'center',editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度8(℃)', name: 'P_TEMP8', width: 80, align: 'center',editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度9(℃)', name: 'P_TEMP9', align: 'center', width: 80,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度10(℃)', name: 'P_TEMP10', width: 80, align: 'center',editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度11(℃)', name: 'P_TEMP11', align: 'center', width: 80,editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"},"
			+ "		{display: '井温度12(℃)', name: 'P_TEMP12', width: 80, align: 'center',editor: { type: 'float' }," +
			"totalSummary:" +
            "{" +
            "	type:'sum,avg'" + 
            "}" +
					"}"
//			+ "		{display: '井压力1(MPa)', name: 'P_PRESS1', align: 'center', width: 80,editor: { type: 'float' }," +
//			"totalSummary:" +
//            "{" +
//            "	type:'sum,avg'" + 
//            "}" +
//					"},"
//			+ "		{display: '井压力2(MPa)', name: 'P_PRESS2', width: 80, align: 'center',editor: { type: 'float' }," +
//			"totalSummary:" +
//            "{" +
//            "	type:'sum,avg'" + 
//            "}" +
//					"}"
			+ "	]},"
//			+ "{display: '关井原因', name: 'P_SHUTIN_CODE', align: 'center', align: 'center',width: 150, minWidth: 100,editor: { type: 'select', data: gjData, valueField: 'id', textField: 'text'}," +
//					"render: function (item)"+
//		            "{"+
//		            "    for (var i = 0; i < gjData.length; i++)"+
//		            "    {"+
//		            "        if (gjData[i]['id'] == item.P_SHUTIN_CODE)"+
//		            "            return gjData[i]['text']"+
//		            "    }"+
//		            "    return item.P_SHUTIN_CODE;"+
//		            "}"+
//					"},"
//            + "{display: '关井时间', name: 'P_SHUTIN_TIME',type: 'text', width: 130, editor: { type: 'text'}},"
//            + "{display: '开井时间', name: 'P_OPEN_TIME',type: 'text', width: 130, editor: { type: 'text'}},"
//            + "{display: '措施', name: 'P_STIMU_CODE', align: 'center', align: 'center',width: 150, minWidth: 100,editor: { type: 'select', data: csData, valueField: 'id', textField: 'text'}," +
//		            "render: function (item)"+
//		            "{"+
//		            "    for (var i = 0; i < csData.length; i++)"+
//		            "    {"+
//		            "        if (csData[i]['id'] == item.P_STIMU_CODE)"+
//		            "            return csData[i]['text']"+
//		            "    }"+
//		            "    return item.P_STIMU_CODE;"+
//		            "}"+
//            		"},"
			+ "{display: '备注', name: 'REMARK', align: 'center', align: 'center',width: 255, minWidth: 100,editor: { type: 'text' }}"
			+ "	]},"
			+" { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70}," 
			+ "{ display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
			+ "{ display: '审核人', name: 'VERIFIER', align: 'center',  minWidth: 90},"
			+ "{ display: '审核时间', name: 'VERIFY_TIME', align: 'center', minWidth: 150},"
			+ "{ display: 'SAGD日报ID', name: 'SAGDDID', align: 'center',  minWidth: 60 ,hide: true},"
			+ "{ display: 'SAGD井ID', name: 'SAGDID', align: 'center',  minWidth: 60 ,hide: true},"
			+ "{ display: 'SAGD井ORG_ID', name: 'ORG_ID', align: 'center',  minWidth: 60 ,hide: true},"
			+ "{display: '含水标识', name: 'CONTAINING_WATER_FLG', align: 'center', minWidth: 150 ,hide: true},"
			+ "{display: '报表顺序', name: 'BBSX', align: 'center', minWidth: 150 },"
			+ "{display: '班组划分', name: 'BZHF', align: 'center', minWidth: 150 }"
			+ "],"+
			"enabledEdit: true,"+
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sagddrpd_searchSagdRPD.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+	
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
//			"selectRowButtonOnly:true,"+
//			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
//			"frozen:true, "+
			"onBeforeEdit: f_onBeforeEdit, "+
			"onAfterEdit: f_onAfterEdit, "+
			"checkbox :true, "+
			"frozenRownumbers :true, "+
//			"fixedCellHeight :true, "+
			//"frozenCheckbox :false, "+
			"onBeforeCheckRow :f_onBeforeCheckRow";
//			"onDblClickRow: function(data, rowindex, rowobj) {" +
//			"$.ligerDialog.open({" +
//			"title: '显示SAGD日报详细信息'," +
//			"url:'sagdrpdview.jsp?sagdid='+data.SAGDID+'&sagddid='+data.SAGDDID+'&jhmc='+data.JHMC," +
//			"width: 850," +
//			"height: 500" +
//			"});" +
//			"}";
	
	//SAGD井日报生成数据-全部
	public static final String SAGDDRPDWHALL_PAGE_GRID1 = " columns: ["
			+ "{display: '井号名称', name: 'JHMC',align: 'center', minWidth: 80,frozen:true," +
					"totalSummary:" +
			        "{" +
			        "	type:'count'" + 
			        "}" +
			  "},"
			+ "{display: '日报日期', name: 'REPORT_DATE', minWidth: 90,align: 'center',frozen:true},"
			+ "{display: '井号名称', name: 'JHMC',align: 'center', minWidth: 80,editor: { type: 'select', data: wellData, valueField: 'text', textField: 'text'}},"
				
				//+ "{ display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
				+ "{display: '注汽井', columns:"
				+ "	["
				+ "{ display: '注汽井注汽时率', name: 'I_INJE_TIME_RATION', align: 'center', width: 60, minWidth: 60, editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
	            + "{ display: '注汽井(I井）生产时率', name: 'I_PROC_TIME_RATION', align: 'center', width: 75, minWidth: 75, editor: { type: 'float' }," +
					"totalSummary:" +
		            "{" +
		            "	type:'sum,avg'" + 
		            "}" +
				"},"
				+ "{ display: '主管', columns:"
				+ "	["
				+ "		{display: '油嘴(mm)', name: 'I_P_FLOW_NIPPLE', align: 'center', width: 60,editor: { type: 'float' }," +
							"totalSummary:" +
				            "{" +
				            "	type:'sum,avg'" + 
				            "}" +
						"},"
				+ "		{display: '油压(Mpa)', name: 'I_PRESSURE_PRESS', align: 'center', width: 60,editor: { type: 'float' }," +
							"totalSummary:" +
				            "{" +
				            "	type:'sum,avg'" + 
				            "}" +
						"},"
				+ "		{display: '温度(℃)', name: 'I_PRESSURE_TEMP', align: 'center', width: 60,editor: { type: 'float' }," +
							"totalSummary:" +
				            "{" +
				            "	type:'sum,avg'" + 
				            "}" +
						"}"
//				+ "		{display: '回压(Mpa)', name: 'ZGHY', width: 60, align: 'center'}"
				+ "	]},"
				+ "{ display: '副管', columns:"
				+ "	["
				+ "		{display: '油嘴(mm)', name: 'I_I_FLOW_NIPPLE', align: 'center', width: 60,editor: { type: 'float' }," +
							"totalSummary:" +
				            "{" +
				            "	type:'sum,avg'" + 
				            "}" +
						"},"
				+ "		{display: '油压(Mpa)', name: 'I_LOOPED_PRESS', align: 'center', width: 60,editor: { type: 'float' }," +
							"totalSummary:" +
				            "{" +
				            "	type:'sum,avg'" + 
				            "}" +
						"},"
				+ "		{display: '温度(℃)', name: 'I_LOOPED_TEMP', align: 'center', width: 60,editor: { type: 'float' }," +
							"totalSummary:" +
				            "{" +
				            "	type:'sum,avg'" + 
				            "}" +
						"}"
//				+ "		{display: '回压(Mpa)', name: 'FGHY', width: 60, align: 'center'}"
				+ "	]},"
				+ "{display: 'I井回压(Mpa)', name: 'I_BACK_PRES' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
							"totalSummary:" +
				            "{" +
				            "	type:'sum,avg'" + 
				            "}" +
						"},"
				+ "{display: '套压(Mpa)', name: 'I_DRIVEPIPE_PRESS' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
							"totalSummary:" +
				            "{" +
				            "	type:'sum,avg'" + 
				            "}" +
						"},"
				+ "{display: '液量(t/d)', name: 'I_DAILY_OUTPUT' ,align: 'center', minWidth: 60,editor: {type: 'float'}," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "{display: '油量(t/d)', name: 'I_DAILY_OIL_OUTPUT' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
							"totalSummary:" +
				            "{" +
				            "	type:'sum,avg'" + 
				            "}" +
						"},"
				+ "{display: '含水(%)', name: 'I_WATER_RATION' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
							"totalSummary:" +
				            "{" +
				            "	type:'sum,avg'" + 
				            "}" +
						"},"
				+ "{display: '瞬时流量(m3/h)', name: 'NO1INSTANT_FLOW', width: 70, align: 'center',editor: { type: 'float' }," +
							"totalSummary:" +
				            "{" +
				            "	type:'sum,avg'" + 
				            "}" +
						"},"
				+ "{ display: '注汽量(t/d)', name: 'I_DAILY_CUMULATIVE_STEAM', align: 'center',minWidth: 70,editor: { type: 'float' }," +
							"totalSummary:" +
				            "{" +
				            "	type:'sum,avg'" + 
				            "}" +
						"},"
//	            + "{display: '关井原因', name: 'I_SHUTIN_CODE', align: 'center', align: 'center',width: 150, minWidth: 100,editor: { type: 'select', data: gjData, valueField: 'id', textField: 'text'}," +
//	            		"render: function (item)"+
//	                    "{"+
//	                    "    for (var i = 0; i < gjData.length; i++)"+
//	                    "    {"+
//	                    "        if (gjData[i]['id'] == item.I_SHUTIN_CODE)"+
//	                    "            return gjData[i]['text']"+
//	                    "    }"+
//	                    "    return item.I_SHUTIN_CODE;"+
//	                    "}"+
//	            		"},"
//	                    // { display: '入职日期', name: 'IncomeDay', type: 'date', format: 'yyyy年MM月dd', width: 100, editor: { type: 'date'} },
//	            + "{display: '关井时间', name: 'I_SHUTIN_TIME',type: 'text', width: 130, editor: { type: 'text'}},"
//	            + "{display: '开井时间', name: 'I_OPEN_TIME', type: 'text', width: 130, editor: { type: 'text'}},"
//	            + "{display: '措施', name: 'I_STIMU_CODE', align: 'center', align: 'center',width: 150, minWidth: 100,editor: { type: 'select', data: csData, valueField: 'id', textField: 'text'}," +
//			            "render: function (item)"+
//			            "{"+
//			            "    for (var i = 0; i < csData.length; i++)"+
//			            "    {"+
//			            "        if (csData[i]['id'] == item.I_STIMU_CODE)"+
//			            "            return csData[i]['text']"+
//			            "    }"+
//			            "    return item.I_STIMU_CODE;"+
//			            "}"+
//	            		"},"
				+ "{display: '备注', name: 'I_REMARK', align: 'center', align: 'center',width: 255, minWidth: 100, editor: { type: 'text' }}"
				+ "	]},"
				+ "{display: '生产井', columns:"
				+ "	["
				+ "{display: '生产井注汽时率', name: 'P_INJE_TIME_RATION', width: 70, align: 'center', editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "{display: '生产井(P井）生产时率', name: 'P_PROC_TIME_RATION', align: 'center', width: 80,editor: { type: 'float' }," +
						"totalSummary:" +
			            "{" +
			            "	type:'sum,avg'" + 
			            "}" +
					"},"
				+ "{display: '抽油机运转时间', name: 'RUNTIME', width: 70, align: 'center', editor: { type: 'float' }},"
				+ "{display: '抽油机故障时间', name: 'PUMP_ERROR_TIME', width: 70, align: 'center', editor: { type: 'float' }},"
				+ "{ display: '主管', columns:"
				+ "	["
				+ "		{display: '泵径 (mm)', name: 'PUMP_DIAMETER', align: 'center', width: 60,editor: { type: 'float' }," +
							"totalSummary:" +
				            "{" +
				            "	type:'sum,avg'" + 
				            "}" +
						"},"
				+ "		{display: '冲程 (m)', name: 'STROKE', align: 'center', width: 60,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '冲次 (次)', name: 'JIG_FREQUENCY', align: 'center', width: 60,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '油嘴(mm)', name: 'P_P_FLOW_NIPPLE', align: 'center', width: 60,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '油压(Mpa)', name: 'P_PRESSURE_PRESS', align: 'center', width: 60,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '温度(℃)', name: 'P_PRESSURE_TEMP', align: 'center', width: 60,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"}"
//				+ "		{display: '回压(Mpa)', name: 'ZGHY', width: 60, align: 'center'}"
				+ "	]},"
				+ "{ display: '副管', columns:"
				+ "	["
				+ "		{display: '油嘴(mm)', name: 'P_I_FLOW_NIPPLE', align: 'center', width: 60,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '油压(Mpa)', name: 'P_LOOPED_PRESS', align: 'center', width: 60,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '温度(℃)', name: 'P_LOOPED_TEMP', align: 'center', width: 60,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"}"
//				+ "		{display: '回压(Mpa)', name: 'FGHY', width: 60, align: 'center'}"
				+ "	]},"
				+ "{display: 'P井回压 (Mpa)', name: 'P_BACK_PRES' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "{display: '套压(Mpa)', name: 'P_DRIVEPIPE_PRESS' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "{display: '液量(t/d)', name: 'P_DAILY_OUTPUT' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "{display: '油量(t/d)', name: 'P_DAILY_OIL_OUTPUT' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "{display: '含水(%)', name: 'P_WATER_RATION' ,align: 'center', minWidth: 60,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "{display: '瞬时流量(m3/h)', name: 'NO2INSTANT_FLOW', width: 70, align: 'center',editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "{ display: '注汽量(t/d)', name: 'P_DAILY_CUMULATIVE_STEAM', align: 'center',minWidth: 70,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "{ display: '井下数据监控', columns:"
				+ "	["
				+ "		{display: '井温度1(℃)', name: 'P_TEMP1', align: 'center', width: 80,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '井温度2(℃)', name: 'P_TEMP2', width: 80, align: 'center',editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '井温度3(℃)', name: 'P_TEMP3', width: 80, align: 'center',editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '井温度4(℃)', name: 'P_TEMP4', align: 'center', width: 80,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '井温度5(℃)', name: 'P_TEMP5', width: 80, align: 'center',editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '井温度6(℃)', name: 'P_TEMP6', align: 'center', width: 80,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '井温度7(℃)', name: 'P_TEMP7', width: 80, align: 'center',editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '井温度8(℃)', name: 'P_TEMP8', width: 80, align: 'center',editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '井温度9(℃)', name: 'P_TEMP9', align: 'center', width: 80,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '井温度10(℃)', name: 'P_TEMP10', width: 80, align: 'center',editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '井温度11(℃)', name: 'P_TEMP11', align: 'center', width: 80,editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"},"
				+ "		{display: '井温度12(℃)', name: 'P_TEMP12', width: 80, align: 'center',editor: { type: 'float' }," +
				"totalSummary:" +
	            "{" +
	            "	type:'sum,avg'" + 
	            "}" +
						"}"
//				+ "		{display: '井压力1(MPa)', name: 'P_PRESS1', align: 'center', width: 80,editor: { type: 'float' }," +
//				"totalSummary:" +
//	            "{" +
//	            "	type:'sum,avg'" + 
//	            "}" +
//						"},"
//				+ "		{display: '井压力2(MPa)', name: 'P_PRESS2', width: 80, align: 'center',editor: { type: 'float' }," +
//				"totalSummary:" +
//	            "{" +
//	            "	type:'sum,avg'" + 
//	            "}" +
//						"}"
				+ "	]},"
//				+ "{display: '关井原因', name: 'P_SHUTIN_CODE', align: 'center', align: 'center',width: 150, minWidth: 100,editor: { type: 'select', data: gjData, valueField: 'id', textField: 'text'}," +
//						"render: function (item)"+
//			            "{"+
//			            "    for (var i = 0; i < gjData.length; i++)"+
//			            "    {"+
//			            "        if (gjData[i]['id'] == item.P_SHUTIN_CODE)"+
//			            "            return gjData[i]['text']"+
//			            "    }"+
//			            "    return item.P_SHUTIN_CODE;"+
//			            "}"+
//						"},"
//	            + "{display: '关井时间', name: 'P_SHUTIN_TIME',type: 'text', width: 130, editor: { type: 'text'}},"
//	            + "{display: '开井时间', name: 'P_OPEN_TIME',type: 'text', width: 130, editor: { type: 'text'}},"
//	            + "{display: '措施', name: 'P_STIMU_CODE', align: 'center', align: 'center',width: 150, minWidth: 100,editor: { type: 'select', data: csData, valueField: 'id', textField: 'text'}," +
//			            "render: function (item)"+
//			            "{"+
//			            "    for (var i = 0; i < csData.length; i++)"+
//			            "    {"+
//			            "        if (csData[i]['id'] == item.P_STIMU_CODE)"+
//			            "            return csData[i]['text']"+
//			            "    }"+
//			            "    return item.P_STIMU_CODE;"+
//			            "}"+
//	            		"},"
				+ "{display: '备注', name: 'REMARK', align: 'center', align: 'center',width: 255, minWidth: 100,editor: { type: 'text' }}"
				+ "	]},"
				+" { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70}," 
				+ "{ display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"
				+ "{ display: '审核人', name: 'VERIFIER', align: 'center',  minWidth: 90},"
				+ "{ display: '审核时间', name: 'VERIFY_TIME', align: 'center', minWidth: 150},"
				+ "{ display: 'SAGD日报ID', name: 'SAGDDID', align: 'center',  minWidth: 60 ,hide: true},"
				+ "{ display: 'SAGD井ID', name: 'SAGDID', align: 'center',  minWidth: 60 ,hide: true},"
				+ "{ display: 'SAGD井ORG_ID', name: 'ORG_ID', align: 'center',  minWidth: 60 ,hide: true},"
				+ "{display: '含水标识', name: 'CONTAINING_WATER_FLG', align: 'center', minWidth: 150 ,hide: true},"
				+ "{display: '报表顺序', name: 'BBSX', align: 'center', minWidth: 150 },"
				+ "{display: '班组划分', name: 'BZHF', align: 'center', minWidth: 150 }"
				+ "],"+
				"enabledEdit: true,"+
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'sagddrpd_searchSagdRPD1.action',"+	
				"delayLoad :true, "+
				"pageParmName :'pageNo',"+	
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
//				"selectRowButtonOnly:true,"+
//				"sePaper:true,"+
				"pageSize:30 ,"+
				"rownumbers:true ,"+
//				"frozen:true, "+
				"onBeforeEdit: f_onBeforeEdit, "+
				"onAfterEdit: f_onAfterEdit, "+
				"checkbox :true, "+
				"frozenRownumbers :true, "+
//				"fixedCellHeight :true, "+
				//"frozenCheckbox :false, "+
				"onBeforeCheckRow :f_onBeforeCheckRow";
//				"onDblClickRow: function(data, rowindex, rowobj) {" +
//				"$.ligerDialog.open({" +
//				"title: '显示SAGD日报详细信息'," +
//				"url:'sagdrpdview.jsp?sagdid='+data.SAGDID+'&sagddid='+data.SAGDDID+'&jhmc='+data.JHMC," +
//				"width: 850," +
//				"height: 500" +
//				"});" +
//				"}";
	
	
	

	
	
	
	
	//SAGD井日报维护注气井报表
	public static final String SAGDDRPDZQJWH_PAGE_GRID = " columns: ["
			+ "  { display: '井号名称', name: 'JHMC',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '采集时间', name: 'ACQUISITION_TIME',align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '井号编码', name: 'JHMC_S',align: 'center', minWidth: 60},"
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 60},"
			+ "  { display: 'SAGD井ID', name: 'SAGDID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			
			+ "  { display: '巡检时间', name: 'XJTINME' ,align: 'center', minWidth: 150},"
			+ "  { display: '注汽小时(h)', name: 'ZQHOUR' ,align: 'center', minWidth: 70},"
		
			
			+ " { display: '主管', columns:"
			+ "	["
			+ "		{ display: '油压(Mpa)', name: 'ZGYY', align: 'center', width: 60},"
			+ "		{ display: '回压(Mpa)', name: 'ZGHY', width: 60, align: 'center'},"
			+ "		{ display: '温度(℃)', name: 'ZGWD', width: 60, align: 'center'}"
			+ "	]},"
			
			+ " { display: '副管', columns:"
			+ "	["
			+ "		{ display: '油咀(mm)', name: 'FGYZ', align: 'center', width: 60},"
			+ "		{ display: '油压(Mpa)', name: 'FGYY', align: 'center', width: 60},"
			+ "		{ display: '回压(Mpa)', name: 'FGHY', width: 60, align: 'center'},"
			+ "		{ display: '温度(℃)', name: 'FGWD', width: 60, align: 'center'}"
			+ "	]},"
			
			+ "  { display: '套压(Mpa)', name: 'TY' ,align: 'center', minWidth: 60},"
			+ "  { display: '液量(t)', name: 'YL' ,align: 'center', minWidth: 60},"
			
			+ " { display: '蒸汽计量', columns:"
			+ "	["
			+ "		{ display: '干度(%)', name: 'GD', align: 'center', width: 60},"
			+ "		{ display: '压力(MPa)', name: 'YL', align: 'center', width: 60},"
			+ "		{ display: '瞬时流量(t/h)', name: 'SSLL', width: 80, align: 'center'}"
			+ "	]},"
			
			+ " { display: '井下检测报表', columns:"
			+ "	["
			+ "		{ display: 'P井温度1(℃)', name: 'P_TEMP1', align: 'center', width: 100},"
			+ "		{ display: 'P井温度2(℃)', name: 'P_TEMP2', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度3(℃)', name: 'P_TEMP3', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度4(℃)', name: 'P_TEMP4', align: 'center', width: 100},"
			+ "		{ display: 'P井温度5(℃)', name: 'P_TEMP5', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度5(℃)', name: 'P_TEMP6', align: 'center', width: 100},"
			+ "		{ display: 'P井温度7(℃)', name: 'P_TEMP7', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度8(℃)', name: 'P_TEMP8', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度9(℃)', name: 'P_TEMP9', align: 'center', width: 100},"
			+ "		{ display: 'P井温度10(℃)', name: 'P_TEMP10', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度11(℃)', name: 'P_TEMP11', align: 'center', width: 100},"
			+ "		{ display: 'P井温度12(℃)', name: 'P_TEMP12', width: 100, align: 'center'}"
			+ "	]},"
			
			+ "  { display: '备注', name: 'REMARK', align: 'center', align: 'center',width: 255, minWidth: 100}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sagddrpd_searchSagddRPD.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
	
	//SAGD井日报维护生产井报表
	public static final String SAGDDRPDSCJWH_PAGE_GRID = " columns: ["
			+ "  { display: '井号名称', name: 'JHMC',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '采集时间', name: 'ACQUISITION_TIME',align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '井号编码', name: 'JHMC_S',align: 'center', minWidth: 60},"
			+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
			+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 60},"
			+ "  { display: 'SAGD井ID', name: 'SAGDID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			
			+ "  { display: '巡检时间', name: 'XJTINME' ,align: 'center', minWidth: 150},"
			+ "  { display: '生产小时(h)', name: 'SCHOUR' ,align: 'center', minWidth: 80},"
		
			
			+ " { display: '主管', columns:"
			+ "	["
			+ "		{ display: '油压(Mpa)', name: 'ZGYY', align: 'center', width: 60},"
			+ "		{ display: '回压(Mpa)', name: 'ZGHY', width: 60, align: 'center'},"
			+ "		{ display: '温度(℃)', name: 'ZGWD', width: 60, align: 'center'}"
			+ "	]},"
			
			+ " { display: '副管', columns:"
			+ "	["
			+ "		{ display: '油咀(mm)', name: 'FGYZ', align: 'center', width: 60},"
			+ "		{ display: '油压(Mpa)', name: 'FGYY', align: 'center', width: 60},"
			+ "		{ display: '回压(Mpa)', name: 'FGHY', width: 60, align: 'center'},"
			+ "		{ display: '温度(℃)', name: 'FGWD', width: 60, align: 'center'}"
			+ "	]},"
			
			+ "  { display: '套压(Mpa)', name: 'TY' ,align: 'center', minWidth: 60},"
			+ "  { display: '液量(t)', name: 'YL' ,align: 'center', minWidth: 60},"
			
			+ " { display: '蒸汽计量', columns:"
			+ "	["
			+ "		{ display: '干度(%)', name: 'GD', align: 'center', width: 60},"
			+ "		{ display: '压力(MPa)', name: 'YL', align: 'center', width: 60},"
			+ "		{ display: '瞬时流量(t/h)', name: 'SSLL', width: 80, align: 'center'}"
			+ "	]},"
			
			+ " { display: '井下检测报表', columns:"
			+ "	["
			+ "		{ display: 'P井温度1(℃)', name: 'P_TEMP1', align: 'center', width: 100},"
			+ "		{ display: 'P井温度2(℃)', name: 'P_TEMP2', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度3(℃)', name: 'P_TEMP3', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度4(℃)', name: 'P_TEMP4', align: 'center', width: 100},"
			+ "		{ display: 'P井温度5(℃)', name: 'P_TEMP5', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度5(℃)', name: 'P_TEMP6', align: 'center', width: 100},"
			+ "		{ display: 'P井温度7(℃)', name: 'P_TEMP7', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度8(℃)', name: 'P_TEMP8', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度9(℃)', name: 'P_TEMP9', align: 'center', width: 100},"
			+ "		{ display: 'P井温度10(℃)', name: 'P_TEMP10', width: 100, align: 'center'},"
			+ "		{ display: 'P井温度11(℃)', name: 'P_TEMP11', align: 'center', width: 100},"
			+ "		{ display: 'P井温度12(℃)', name: 'P_TEMP12', width: 100, align: 'center'}"
			+ "	]},"
			
			+ "  { display: '备注', name: 'REMARK', align: 'center', align: 'center',width: 255, minWidth: 100}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sagddrpd_searchSagddRPD.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
//湿蒸汽锅炉日报数据维护信息
	
	public static final String SRGLCOMMOND_RPD_PAGE_GRID = "columns:["

		+ "  { display: '区块', name: 'BLOCK_NAME' ,align: 'center', minWidth: 70,frozen:true},"
		//+ "  { display: '接转战名称 ', name: 'BLOCKSTATION_NAME' ,align: 'center', minWidth: 70,hide:true},"
		+ "  { display: '锅炉名称', name: 'BOILER_NAME' ,align: 'center', minWidth: 60,frozen:true},"
		+ "  { display: '采集时间', name: 'REPORT_DATE' ,align: 'center', minWidth: 150,frozen:true},"
		+ "  { display: '运行时间(h)', name: 'RUNTIME' ,align: 'center', minWidth: 70,frozen:true},"
		+ "  { display: '天然气', columns:"
		+ "	["
		+ "  { display: '天然气分离器压差(kPa)', name: 'GAS_INTO_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '天然气阀前压力(MPa)', name: 'GAS1_PRESS' ,align: 'center', minWidth: 130},"
		+ "	 { display: '火量(%)', name: 'FIRE_MEASURE' ,align: 'center', minWidth: 60},"
		+ "  { display: '天然气流量(m3/h)', name: 'GAS_FLOW' ,align: 'center', minWidth: 110}"
		+ "	]},"
		+ "  { display: '锅炉给水',columns: ["
		+ "  { display: '给水压力(MPa)', name: 'PUMPIN_PRESS' ,align: 'center', minWidth: 100},"
		+ "  { display: '给水温度(℃)', name: 'PUMPOUT_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '柱塞泵变频频率(Hz)', name: 'PUMPFC_FREQUENCY' ,align: 'center', minWidth: 130},"
		+ "  { display: '给水流量(t/h)', name: 'WATERSUPPLY_FLOW' ,align: 'center', minWidth: 90}"
		+"]},"  
		+ "{display:'蒸汽性质', columns:["
		+ "  { display: '蒸汽出口压力(MPa)', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '蒸汽出口温度(℃)', name: 'STEAMOUT_TEMP' ,align: 'center', minWidth: 110},"
		+ "  { display: '蒸汽出口干度(%)', name: 'STEAM_DRYNESS' ,align: 'center', minWidth: 110}"
		+"]},"
		+ "  { display: '柱塞泵出口压力(MPa)', name: 'PUMPOUT_PRESS' ,align: 'center', minWidth: 120},"
		+" {display:'对流段',columns:["
		+ "  { display: '对流段入口压力(MPa)', name: 'CSIN_PRESS' ,align: 'center', minWidth: 130},"
		+ "  { display: '对流段入口温度(℃)', name: 'CSIN_TEMP' ,align: 'center', minWidth: 130},"
		+ "  { display: '对流段出口压力(MPa)', name: 'CSOUT_PRESS' ,align: 'center', minWidth: 130},"
		+ "  { display: '对流段出口温度(℃)', name: 'CSOUT_TEMP' ,align: 'center', minWidth: 130},"
		+ "  { display: '对流段压降(MPa)', name: 'CONVECTIONR_PRESS_REDUCTION' ,align: 'center', minWidth: 130}"
		+"]},"
		+"{display :'辐射段', columns:["
		+ "  { display: '辐射段入口压力(MPa)', name: 'RSIN_PRESS' ,align: 'center', minWidth: 130},"
		+ "  { display: '辐射段入口温度(℃)', name: 'RSIN_TEMP' ,align: 'center', minWidth: 130},"
		+ "  { display: '辐射段压降(MPa)', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 120},"
		+ "  { display: '辐射段出口管温(℃)', name: 'RS_TEMP' ,align: 'center', minWidth: 120}"
		+"]},"
		+"{display:'天然气', columns:["
		+ "  { display: '天然气阀后压力(MPa)', name: 'GAS2_PRESS' ,align: 'center', minWidth: 140},"
		+ "  { display: '膨胀管压力(kPa)', name: 'GAS3_PRESS' ,align: 'center', minWidth: 120}"
		+"]},"
		+ "  { display: '润滑油压力(MPa)', name: 'LUBE_PRESS' ,align: 'center', minWidth: 110},"
		+ "  { display: '润滑油温度(℃)', name: 'LUBE_TEMP' ,align: 'center', minWidth: 110},"
		+ "  { display: '鼓风机进风温度(℃)', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 110},"
		+ "  { display: '燃烧器喉温(℃)', name: 'BURNER_TEMP' ,align: 'center', minWidth: 110},"
		+ "  { display: '炉膛压力(Pa)', name: 'HEARTH_PRESS' ,align: 'center', minWidth: 110},"
		+ "  { display: '排烟温度(℃)', name: 'EJECTSMOKE_TEMP' ,align: 'center', minWidth: 110},"
		+"{display:'电机参数',columns:["
		+ "  { display: '电网电压(V)', name: 'SYSTEM_VOLTAGE' ,align: 'center', minWidth: 110},"
		+ "  { display: '柱塞泵电流(A)', name: 'PUMP_MOTOR_CURRENT' ,align: 'center', minWidth: 110},"
		+ "  { display: '柱塞泵电机温度(℃)', name: 'PUMP_MOTOR_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '鼓风机电流(A)', name: 'FAN_MOTOR_CURRENT' ,align: 'center', minWidth: 110}"
		+"]},"
		+ "  { display: '锅炉房可燃气体浓度(%)', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 120},"
		+ "  { display: '锅炉房H2S气体浓度(ppm)', name: 'H2S_DENSITY' ,align: 'center', minWidth: 120},"
		+ "  { display: '污水缓冲罐液位（m）', name: 'SEWAGE_BUFFER_TANK' ,align: 'center', minWidth: 120},"
		+ "  { display: '天然气日累积量(m3)', name: 'DAILY_CUMULATIVE_GAS' ,align: 'center', minWidth: 90},"
		+ "  { display: '注汽日累积量(t)', name: 'DAILY_CUMULATIVE_STEAM' ,align: 'center', minWidth: 80},"
		+ "  { display: '供气单位', name: 'STEAM_WORK_GROUP' ,align: 'center', minWidth: 70},"
		+ "  { display: '受汽单位', name: 'OILDRILLING_STATION_NAME' ,align: 'center', minWidth: 70},"
		+ "  { display: '注汽单位', name: 'STEAM_INJE_UNIT' ,align: 'center', minWidth: 70},"
		+ "  { display: '湿蒸汽锅炉动态数据ID', name: 'RPD_BOILER_COMMON_ID',align: 'center', minWidth: 120,hide: true},"
		+ "  { display: '锅炉ID', name: 'BOILERID',align: 'center', minWidth: 120,hide: true},"
		+ "  { display: '备注', name: 'REMARK' ,align: 'center', minWidth: 120},"
		+ "  { display: '审核人', name: 'CHECK_OPER' ,align: 'center', minWidth: 100},"
		+ "  { display: '审核时间', name: 'CHECK_DATE' ,align: 'center', minWidth: 120},"
		+ "  { display: '上次操作者', name: 'RLAST_OPER' ,align: 'center', minWidth: 100},"
		+ "  { display: '上次操作日期', name: 'RLAST_ODATE' ,align: 'center', minWidth: 120}"
		
		
		
		+ " ],"+

	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'srglrb_searchSRGLRB.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false,"+
			" onDblClickRow: function(data, rowindex, rowobj) {" +
			"		$.ligerDialog.open({" +
			" 			title: '显示湿蒸汽锅炉日报详细信息'," +
			"  			url:'ptsrglrpdview.jsp?boilerid='+data.BOILERID+'&rpd_boiler_common_id='+data.RPD_BOILER_COMMON_ID+'&boiler_name='+data.BOILER_NAME," +
			"  			width: 1000," +
			"  			height: 500" +
			"  			});" +
			"  } ";
	
//湿蒸汽锅炉日报数据信息
	
	
	public static final String SRGLCOMMONDWH_RPD_PAGE_GRID = " columns: ["
		+ "  { display: '区块', name: 'BLOCK_NAME' ,align: 'center', minWidth: 70,frozen:true},"
		+ "  { display: '接转战名称 ', name: 'BLOCKSTATION_NAME' ,align: 'center', minWidth: 70,hide:true},"
		+ "  { display: '锅炉名称', name: 'BOILER_NAME' ,align: 'center', minWidth: 120,frozen:true},"
		+ "  { display: '采集时间', name: 'REPORT_DATE' ,align: 'center', minWidth: 150,frozen:true},"
		+ "  { display: '运行时间(h)', name: 'RUNTIME' ,align: 'center', minWidth: 70,frozen:true},"
		+ "  { display: '天然气', columns:"
		+ "	["
		+ "  { display: '天然气分离器(kPa)', name: 'GAS_INTO_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '天然气阀前压力(MPa)', name: 'GAS1_PRESS' ,align: 'center', minWidth: 130},"
		+ "	 { display: '火量(%)', name: 'FIRE_MEASURE' ,align: 'center', minWidth: 60},"
		+ "  { display: '天然气流量(m3/h)', name: 'GAS_FLOW' ,align: 'center', minWidth: 110}"
		+ "	]},"
		+ "  { display: '锅炉给水',columns: ["
		+ "  { display: '给水压力(MPa)', name: 'PUMPIN_PRESS' ,align: 'center', minWidth: 100},"
		+ "  { display: '给水温度(℃)', name: 'PUMPOUT_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '柱塞泵变频频率(Hz)', name: 'PUMPFC_FREQUENCY' ,align: 'center', minWidth: 130},"
		+ "  { display: '给水流量(t/h)', name: 'WATERSUPPLY_FLOW' ,align: 'center', minWidth: 90}"
		+"]},"  
		+ "{display:'蒸汽性质', columns:["
		+ "  { display: '蒸汽出口压力(MPa)', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '蒸汽出口温度(℃)', name: 'STEAMOUT_TEMP' ,align: 'center', minWidth: 110},"
		+ "  { display: '蒸汽出口干度(%)', name: 'STEAM_DRYNESS' ,align: 'center', minWidth: 110}"
		+"]},"
		+ "  { display: '柱塞泵出口压力(MPa)', name: 'PUMPOUT_PRESS' ,align: 'center', minWidth: 120},"
		+" {display:'对流段',columns:["
		+ "  { display: '对流段入口压力(MPa)', name: 'CSIN_PRESS' ,align: 'center', minWidth: 130},"
		+ "  { display: '对流段入口温度(℃)', name: 'CSIN_TEMP' ,align: 'center', minWidth: 130},"
		+ "  { display: '对流段出口压力(MPa)', name: 'CSOUT_PRESS' ,align: 'center', minWidth: 130},"
		+ "  { display: '对流段出口温度(℃)', name: 'CSOUT_TEMP' ,align: 'center', minWidth: 130},"
		+ "  { display: '对流段压降(MPa)', name: 'CONVECTIONR_PRESS_REDUCTION' ,align: 'center', minWidth: 130}"
		+"]},"
		+"{display :'辐射段', columns:["
		+ "  { display: '辐射段入口压力(MPa)', name: 'RSIN_PRESS' ,align: 'center', minWidth: 130},"
		+ "  { display: '辐射段入口温度(℃)', name: 'RSIN_TEMP' ,align: 'center', minWidth: 130},"
		+ "  { display: '辐射段压降(MPa)', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 120},"
		+ "  { display: '辐射段出口管温(℃)', name: 'RS_TEMP' ,align: 'center', minWidth: 120}"
		+"]},"
		+"{display:'天然气', columns:["
		+ "  { display: '天然气阀后压力(MPa)', name: 'GAS2_PRESS' ,align: 'center', minWidth: 140,editor:{type: 'float'}},"
		+ "  { display: '膨胀管压力(kPa)', name: 'GAS3_PRESS' ,align: 'center', minWidth: 120,editor:{type: 'float'}}"
		+"]},"
		+ "  { display: '润滑油压力(MPa)', name: 'LUBE_PRESS' ,align: 'center', minWidth: 110,editor:{type: 'float'}},"
		+ "  { display: '润滑油温度(℃)', name: 'LUBE_TEMP' ,align: 'center', minWidth: 110,editor:{type: 'float'}},"
		+ "  { display: '鼓风机进风温度(℃)', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 110,editor:{type: 'float'}},"
		+ "  { display: '燃烧器喉温(℃)', name: 'BURNER_TEMP' ,align: 'center', minWidth: 110,editor:{type: 'float'}},"
		+ "  { display: '炉膛压力(Pa)', name: 'HEARTH_PRESS' ,align: 'center', minWidth: 110,editor:{type: 'float'}},"
		+ "  { display: '排烟温度(℃)', name: 'EJECTSMOKE_TEMP' ,align: 'center', minWidth: 110},"
		+"{display:'电机参数',columns:["
		+ "  { display: '电网电压(V)', name: 'SYSTEM_VOLTAGE' ,align: 'center', minWidth: 110},"
		+ "  { display: '柱塞泵电流(A)', name: 'PUMP_MOTOR_CURRENT' ,align: 'center', minWidth: 110},"
		+ "  { display: '柱塞泵电机温度(℃)', name: 'PUMP_MOTOR_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '鼓风机电流(A)', name: 'FAN_MOTOR_CURRENT' ,align: 'center', minWidth: 110}"
		+"]},"
		+ "  { display: '锅炉房可燃气体浓度(%)', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 120},"
		+ "  { display: '锅炉房H2S气体浓度(ppm)', name: 'H2S_DENSITY' ,align: 'center', minWidth: 120},"
		+ "  { display: '污水缓冲罐液位（m）', name: 'SEWAGE_BUFFER_TANK' ,align: 'center', minWidth: 120},"
		+ "  { display: '天然气日累积量(m3)', name: 'DAILY_CUMULATIVE_GAS' ,align: 'center', minWidth: 90},"
		+ "  { display: '注汽日累积量(t)', name: 'DAILY_CUMULATIVE_STEAM' ,align: 'center', minWidth: 80},"
		+ "  { display: '供气单位', name: 'STEAM_WORK_GROUP' ,align: 'center', minWidth: 70},"
		+ "  { display: '受汽单位', name: 'OILDRILLING_STATION_NAME' ,align: 'center', minWidth: 70},"
		+ "  { display: '注汽单位', name: 'STEAM_INJE_UNIT' ,align: 'center', minWidth: 70},"
		+ "  { display: '湿蒸汽锅炉动态数据ID', name: 'RPD_BOILER_COMMON_ID',align: 'center', minWidth: 120,hide: true},"
		+ "  { display: '锅炉ID', name: 'BOILERID',align: 'center', minWidth: 120,hide: true},"
		+ "  { display: '备注', name: 'REMARK' ,align: 'center', minWidth: 200}"
		+ " ],"+

	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'srglrb_searchSRGLRB.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"enabledEdit:true,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";

	
	public static final String BOILER_CROSSDD_RPD_PAGE_GRID = " columns: ["
		+ "  { display: 'ID', name: 'RPD_BOILER_SUPERHEAT_ID' ,align: 'center', minWidth: 150,hide:true},"
		+ "  { display: '区块', name: 'BLOCK_NAME' ,align: 'center', minWidth: 90,frozen:true},"
		+ "  { display: '锅炉名称', name: 'BOILER_NAME' ,align: 'center', minWidth: 100,frozen:true},"
		+ "  { display: '采集时间', name: 'REPORT_DATE' ,align: 'center', minWidth: 150,frozen:true},"
		+ "  { display: '运行时间(h)', name: 'RUNTIME' ,align: 'center', minWidth: 70,frozen:true},"
		+ "  { display: '天然气', columns:"
		+ "	["
		+ "  { display: '天然气分离器/过滤器压差(kPa)', name: 'GAS_INTO_PRESS' ,align: 'center', minWidth: 160},"
		+ "	 { display: '阀前压力(MPa)', name: 'GAS1_PRESS' ,align: 'center', minWidth: 100},"
		+ "	 { display: '火量(%)', name: 'FIRE_MEASURE' ,align: 'center', minWidth: 100},"
		+ "  { display: '天然气流量(m3/h)', name: 'GAS_FLOW' ,align: 'center', minWidth: 100}"
		+ "	]},"
		+ "  { display: '锅炉给水',columns: ["
		+ "  { display: '给水压力(MPa)', name: 'PUMPIN_PRESS' ,align: 'center', minWidth: 100},"
		+ "  { display: '给水温度(℃)', name: 'PUMPOUT_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '柱塞泵变频频率(Hz)', name: 'PUMPFC_FREQUENCY' ,align: 'center', minWidth: 110},"
		+ "  { display: '给水流量(t/h)', name: 'WATERSUPPLY_FLOW' ,align: 'center', minWidth: 100}"
		+"	]},"  
		+ "{display:'蒸汽性质', columns:["
		+ "  { display: '蒸汽出口压力(MPa)', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 110},"
		+ "  { display: '蒸汽出口温度(℃)', name: 'STEAMOUT_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '过热度(℃)', name: 'SUPERHEAT_DEGREE' ,align: 'center', minWidth: 100}"
		+"]},"
		+" {display:'运行关键参数',columns:["
		+ "  { display: '过热段出口温度(℃)', name: 'SUPERHEATOUT_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '分离器液位(mm)', name: 'SL_LEVEL' ,align: 'center', minWidth: 100},"
		+ "  { display: '辐射段压降(MPa)', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 100},"
		+ "  { display: '过热段压降(MPa)', name: 'SUPERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 100},"
		+ "  { display: '掺混器压降(MPa)', name: 'MIXER_PRESS_REDUCTION' ,align: 'center', minWidth: 100}"
		+"]},"
		+ "  { display: '柱塞泵出口压力(MPa)', name: 'PUMPOUT_PRESS' ,align: 'center', minWidth: 0100},"
		+" {display:'对流段',columns:["
		+ "  { display: '对流段入口压力(MPa)', name: 'CSIN_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '对流段入口温度(℃)', name: 'CSIN_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '对流段出口压力(MPa)', name: 'CSOUT_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '对流段出口温度(℃)', name: 'CSOUT_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '对流段压降(MPa)', name: 'CONVECTIONR_PRESS_REDUCTION' ,align: 'center', minWidth: 100}"
		+"]},"
		+"{display :'辐射段', columns:["
		+ "  { display: '辐射段入口压力(MPa)', name: 'RSIN_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '辐射段入口温度(℃)', name: 'RSIN_TEMP' ,align: 'center', minWidth:120},"
		+ "  { display: '辐射段出口压力(MPa)', name: 'RS_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '辐射段出口温度(℃)', name: 'RSOUT_TEMP' ,align: 'center', minWidth:120},"
		//+ "  { display: '辐射段压降(MPa)', name: 'RS_PRESS_REDUCTION	' ,align: 'center', minWidth: 100},"
		+ "  { display: '辐射段出口管温(℃)', name: 'RS_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '辐射段出口干度(%)', name: 'RS_DRYNESS' ,align: 'center', minWidth: 120}"
		+"]},"
		+ "  { display: '分离器出口压力(MPa)', name: 'SEPARATOR_PRESS_EXPORT' ,align: 'center', minWidth: 120},"
		+"{display :'过热段', columns:["
		+ "  { display: '过热段入口压力(MPa)', name: 'SUPERHEATIN_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '过热段入口温度(℃)', name: 'SUPERHEATIN_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '过热段出口压力(MPa)', name: 'SUPERHEATOUT_PRESS' ,align: 'center', minWidth: 120},"
		//+ "  { display: '过热段出口温度(℃)', name: 'SUPERHEATOUT_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '过热段出口管温(℃)', name: 'SUPERHEAT_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '过热段蒸汽流量(t/h)', name: 'SUPERHEATIN_FLOW' ,align: 'center', minWidth: 120}"
		+"]},"
		+ "  { display: '掺混水压力(MPa)', name: 'MIXER_PRESS_WATER' ,align: 'center', minWidth: 120},"
		+"{display:'天然气', columns:["
		+ "  { display: '天然气阀后压力(MPa)', name: 'GAS2_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '膨胀管压力(kPa)', name: 'GAS3_PRESS' ,align: 'center', minWidth: 100}"
		+"]},"
		+ "  { display: '润滑油温度(℃)', name: 'LUBE_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '助燃风压力(MPa)', name: 'FAN_AIR_EXPORT_PRESS' ,align: 'center', minWidth: 100},"
		+ "  { display: '鼓风机进风温度(℃)', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '燃烧器喉温(℃)', name: 'BURNER_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '炉膛压力(MPa)', name: 'HEARTH_PRESS' ,align: 'center', minWidth: 60},"
		+ "  { display: '排烟温度(℃)', name: 'EJECTSMOKE_TEMP' ,align: 'center', minWidth: 100},"
		+"{display:'电机参数',columns:["
		+ "  { display: '电网电压(V)', name: 'SYSTEM_VOLTAGE' ,align: 'center', minWidth: 100},"
		+ "  { display: '柱塞泵电流(A)', name: 'PUMP_MOTOR_CURRENT' ,align: 'center', minWidth: 100},"
		+ "  { display: '柱塞泵电机温度(℃)', name: 'PUMP_MOTOR_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '鼓风机电流(A)', name: 'FAN_MOTOR_CURRENT' ,align: 'center', minWidth: 100}"
		+"]},"
		+ "  { display: '锅炉房可燃气体浓度(%)', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 110},"
		+ "  { display: '锅炉房H2S气体浓度(ppm)', name: 'H2S_DENSITY' ,align: 'center', minWidth: 110},"
		+ "  { display: '污水缓冲罐液位（m）', name: 'SEWAGE_BUFFER_TANK' ,align: 'center', minWidth: 120},"
		+ "  { display: '天然气日累积量(m3)', name: 'DAILY_CUMULATIVE_GAS' ,align: 'center', minWidth: 110},"
		+ "  { display: '注汽日累积量(t)', name: 'DAILY_CUMULATIVE_STEAM' ,align: 'center', minWidth: 110},"
		+ "  { display: '供气单位', name: 'STEAM_WORK_GROUP' ,align: 'center', minWidth: 100},"
		+ "  { display: '受汽单位', name: 'OILDRILLING_STATION_NAME' ,align: 'center', minWidth: 90},"
		+ "  { display: '注汽单位', name: 'STEAM_INJE_UNIT' ,align: 'center', minWidth: 90},"
		+ "  { display: '备注', name: 'REMARK' ,align: 'center', minWidth: 200}"
		+ " ],"+

	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'blilercdrpd_searchBoilerCrossRPD.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";

	
//过热锅炉维护数据信息
	public static final String BOILER_CROSSDDWH_RPD_PAGE_GRID = " columns: ["
		+ "  { display: 'ID', name: 'RPD_BOILER_SUPERHEAT_ID' ,align: 'center', minWidth: 150,hide:true},"
		+ "  { display: 'BOILERID', name: 'BOILERID' ,align: 'center', minWidth: 150,hide:true},"
		+ "  { display: '区块', name: 'BLOCK_NAME' ,align: 'center', minWidth: 90,frozen:true},"
		+ "  { display: '锅炉名称', name: 'BOILER_NAME' ,align: 'center', minWidth: 100,frozen:true},"
		+ "  { display: '采集时间', name: 'REPORT_DATE' ,align: 'center', minWidth: 150,frozen:true},"
		+ "  { display: '运行时间(h)', name: 'RUNTIME' ,align: 'center', minWidth: 70,frozen:true},"
		+ "  { display: '天然气', columns:"
		+ "	["
		+ "  { display: '天然气分离器/过滤器压差(kPa)', name: 'GAS_INTO_PRESS' ,align: 'center', minWidth: 110},"
		+ "	 { display: '阀前压力(MPa)', name: 'GAS1_PRESS' ,align: 'center', minWidth: 100},"
		+ "	 { display: '火量(%)', name: 'FIRE_MEASURE' ,align: 'center', minWidth: 100},"
		+ "  { display: '天然气流量(m3/h)', name: 'GAS_FLOW' ,align: 'center', minWidth: 100}"
		+ "	]},"
		+ "  { display: '锅炉给水',columns: ["
		+ "  { display: '给水压力(MPa)', name: 'PUMPIN_PRESS' ,align: 'center', minWidth: 100},"
		+ "  { display: '给水温度(℃)', name: 'PUMPOUT_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '柱塞泵变频频率(Hz)', name: 'PUMPFC_FREQUENCY' ,align: 'center', minWidth: 110},"
		+ "  { display: '给水流量(t/h)', name: 'WATERSUPPLY_FLOW' ,align: 'center', minWidth: 100}"
		+"	]},"  
		+ "{display:'蒸汽性质', columns:["
		+ "  { display: '蒸汽出口压力(MPa)', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 110},"
		+ "  { display: '蒸汽出口温度(℃)', name: 'STEAMOUT_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '过热度(℃)', name: 'SUPERHEAT_DEGREE' ,align: 'center', minWidth: 100}"
		+"]},"
		+" {display:'运行关键参数',columns:["
		+ "  { display: '过热段出口温度(℃)', name: 'SUPERHEATOUT_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '分离器液位(mm)', name: 'SL_LEVEL' ,align: 'center', minWidth: 100},"
		+ "  { display: '辐射段压降(MPa)', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 100},"
		+ "  { display: '过热段压降(MPa)', name: 'SUPERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 100},"
		+ "  { display: '掺混器压降(MPa)', name: 'MIXER_PRESS_REDUCTION' ,align: 'center', minWidth: 100}"
		+"]},"
		+ "  { display: '柱塞泵出口压力(MPa)', name: 'PUMPOUT_PRESS' ,align: 'center', minWidth: 100},"
		+" {display:'对流段',columns:["
		+ "  { display: '对流段入口压力(MPa)', name: 'CSIN_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '对流段入口温度(℃)', name: 'CSIN_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '对流段出口压力(MPa)', name: 'CSOUT_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '对流段出口温度(℃)', name: 'CSOUT_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '对流段压降(MPa)', name: 'CONVECTIONR_PRESS_REDUCTION' ,align: 'center', minWidth: 100}"
		+"]},"
		+"{display :'辐射段', columns:["
		+ "  { display: '辐射段入口压力(MPa)', name: 'RSIN_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '辐射段入口温度(℃)', name: 'RSIN_TEMP' ,align: 'center', minWidth:120},"
		+ "  { display: '辐射段出口压力(MPa)', name: 'RS_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '辐射段出口温度(℃)', name: 'RSOUT_TEMP' ,align: 'center', minWidth:120},"
		//+ "  { display: '辐射段压降(MPa)', name: 'RS_PRESS_REDUCTION	' ,align: 'center', minWidth: 100},"
		+ "  { display: '辐射段出口管温(℃)', name: 'RS_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '辐射段出口干度(%)', name: 'RS_DRYNESS' ,align: 'center', minWidth: 120}"
		+"]},"
		+ "  { display: '分离器出口压力(MPa)', name: 'SEPARATOR_PRESS_EXPORT' ,align: 'center', minWidth: 120},"
		+"{display :'过热段', columns:["
		+ "  { display: '过热段入口压力(MPa)', name: 'SUPERHEATIN_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '过热段入口温度(℃)', name: 'SUPERHEATIN_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '过热段出口压力(MPa)', name: 'SUPERHEATOUT_PRESS' ,align: 'center', minWidth: 120},"
		//+ "  { display: '过热段出口温度(℃)', name: 'SUPERHEATOUT_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '过热段出口管温(℃)', name: 'SUPERHEAT_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '过热段蒸汽流量(t/h)', name: 'SUPERHEATIN_FLOW' ,align: 'center', minWidth: 120}"
		+"]},"
		+ "  { display: '掺混水压力(MPa)', name: 'MIXER_PRESS_WATER' ,align: 'center', minWidth: 120},"
		+"{display:'天然气', columns:["
		+ "  { display: '天然气阀后压力(MPa)', name: 'GAS2_PRESS' ,align: 'center', minWidth: 120},"
		+ "  { display: '膨胀管压力(kPa)', name: 'GAS3_PRESS' ,align: 'center', minWidth: 100}"
		+"]},"
		+ "  { display: '润滑油温度(℃)', name: 'LUBE_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '助燃风压力(MPa)', name: 'FAN_AIR_EXPORT_PRESS' ,align: 'center', minWidth: 100},"
		+ "  { display: '鼓风机进风温度(℃)', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '燃烧器喉温(℃)', name: 'BURNER_TEMP' ,align: 'center', minWidth: 100},"
		+ "  { display: '炉膛压力(MPa)', name: 'HEARTH_PRESS' ,align: 'center', minWidth: 60},"
		+ "  { display: '排烟温度(℃)', name: 'EJECTSMOKE_TEMP' ,align: 'center', minWidth: 100},"
		+"{display:'电机参数',columns:["
		+ "  { display: '电网电压(V)', name: 'SYSTEM_VOLTAGE' ,align: 'center', minWidth: 100},"
		+ "  { display: '柱塞泵电流(A)', name: 'PUMP_MOTOR_CURRENT' ,align: 'center', minWidth: 100},"
		+ "  { display: '柱塞泵电机温度(℃)', name: 'PUMP_MOTOR_TEMP' ,align: 'center', minWidth: 120},"
		+ "  { display: '鼓风机电流(A)', name: 'FAN_MOTOR_CURRENT' ,align: 'center', minWidth: 100}"
		+"]},"
		+ "  { display: '锅炉房可燃气体浓度(%)', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 110},"
		+ "  { display: '锅炉房H2S气体浓度(ppm)', name: 'H2S_DENSITY' ,align: 'center', minWidth: 110},"
		+ "  { display: '污水缓冲罐液位(m)', name: 'SEWAGE_BUFFER_TANK' ,align: 'center', minWidth: 120},"
		+ "  { display: '天然气日累积量(m3)', name: 'DAILY_CUMULATIVE_GAS' ,align: 'center', minWidth: 110},"
		+ "  { display: '注汽日累积量(t)', name: 'DAILY_CUMULATIVE_STEAM' ,align: 'center', minWidth: 110},"
		+ "  { display: '供气单位', name: 'STEAM_WORK_GROUP' ,align: 'center', minWidth: 100},"
		+ "  { display: '受汽单位', name: 'OILDRILLING_STATION_NAME' ,align: 'center', minWidth: 90},"
		+ "  { display: '注汽单位', name: 'STEAM_INJE_UNIT' ,align: 'center', minWidth: 90},"
		+ "  { display: '备注', name: 'REMARK' ,align: 'center', minWidth: 200},"
		+ "  { display: '审核人', name: 'CHECK_OPER' ,align: 'center', minWidth: 100},"
		+ "  { display: '审核时间', name: 'CHECK_DATE' ,align: 'center', minWidth: 120},"
		+ "  { display: '上次操作者', name: 'RLAST_OPER' ,align: 'center', minWidth: 100},"
		+ "  { display: '上次操作日期', name: 'RLAST_ODATE' ,align: 'center', minWidth: 120}"
	
		
		+ " ],"+

	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'blilercdrpd_searchBoilerCrossRPD.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
	
	
	//炉线管理
	public static final String BOILERLINE_PAGE_GRID = " columns: [ "+
			"{ display: '炉线单元节点ID', name: 'CELL_ID', align: 'left', width: 200, minWidth: 60 ,hide: true}, "+
			//"{ display: '分产模式ID', name: 'SPLIT_MODEL'  ,align: 'center', minWidth: 120,hide: true},  "+
			"{ display: '炉线名称', name: 'CELL_NAME',align: 'left', minWidth: 120 ,editor: { type: 'text' }}, "+
			"{ display: '分产模式', name: 'SPLIT_MODEL'  ,align: 'center', minWidth: 120,editor: { type: 'select', data: modeData, valueField: 'text', textField: 'text' }},"+
			"{ display: '日期', name: 'VALID_DATE',align: 'center', minWidth: 120 }, "+
			"{ display: '所属单位ID', name: 'OWNER_ORG',align: 'left', minWidth: 120,editor: { type: 'select', data: ownerData, valueField: 'text', textField: 'text' }}, "+
			//"{ display: '所属单位', name: 'OWNER_NAME',align: 'left', minWidth: 120}, "+
			"  { display: '上次操作者', name: 'RLAST_OPER'  ,align: 'center', minWidth: 70},"+ 
			"  { display: '上次操作日期', name: 'RLAST_ODATE'  ,align: 'center', minWidth: 150},"+ 
			"{ display: '备注', name: 'REMARK',align: 'center',minWidth: 150,editor: { type: 'text' }} ],"+
			"enabledEdit: true,"+
			"height:'100%',dataAction: 'server'," +
			"url:'boilerline_seachblines.action',"+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			//"frozen:true, "+
			"checkbox :false,"+
			"onSelectRow: function (rowdata, rowindex) "+
			"{fromAu(rowdata);}";
	
	//高干度锅炉日报
		public static final String PC_RPD_BOILER_HIGH_DRY_T = " columns: ["
			+ "  { display: '受汽区块', name: 'BLOCK_NAME' ,align: 'center', minWidth: 100,frozen:true},"
			+ "  { display: '锅炉名称', name: 'BOILER_NAME' ,align: 'center', minWidth: 100,frozen:true},"
			+ "  { display: '采集时间', name: 'REPORT_DATE' ,align: 'center', minWidth: 130,frozen:true},"
			//+ "  { display: '运行状态', name: 'RUN_STATUS' ,align: 'center', minWidth: 80,frozen:true},"
			+ "  { display: '运行时间', name: 'RUNTIME' ,align: 'center', minWidth: 80},"
			//+ "  { display: '鼓风机进风温度', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 90},"
			//+ "  { display: '锅炉房可燃气体浓度', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 110},"
			//+ "  { display: 'H2s气体浓度', name: 'H2S_DENSITY' ,align: 'center', minWidth: 80},"
			//+ "  { display: '系统总压降', name: 'SYSTEM_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
			//+ "  { display: '减少掺混器压降', name: 'MIXER_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
			+ "  { display: '天然气', columns:"
			+ "	["
			+ "  { display: '天然气分离器压差(kPa)', name: 'GAS_INTO_PRESS' ,align: 'center', minWidth: 80},"
			+ "  { display: '阀前压力(MPa)', name: 'GAS1_PRESS' ,align: 'center', minWidth: 60},"
			+ "	 { display: '火量(%)', name: 'FIRE_MEASURE' ,align: 'center', minWidth: 40},"
			+ "  { display: '天然气流量(m3/h)', name: 'GAS_FLOW' ,align: 'center', minWidth: 80}"
			+ "	]},"
			+ "  { display: '锅炉给水',columns: ["
			+ "  { display: '给水压力(MPa)', name: 'PUMPIN_PRESS' ,align: 'center', minWidth: 80},"
			+ "  { display: '给水温度(℃)', name: 'PUMPOUT_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '柱塞泵变频频率(Hz)', name: 'PUMPFC_FREQUENCY' ,align: 'center', minWidth: 90},"
			+ "  { display: '给水流量(t/h)', name: 'WATERSUPPLY_FLOW' ,align: 'center', minWidth: 60}"
			+"]},"  
			+ "{display:'蒸汽性质', columns:["
			+ "  { display: '蒸汽出口压力(MPa)', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '蒸汽出口温度(℃)', name: 'STEAMOUT_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '蒸汽出口干度(%)', name: 'STEAM_DRYNESS' ,align: 'center', minWidth: 80}"
			+"]},"
			//+ "{display:'运行关键参数', columns:["
			//+ "  { display: '辐射段压降', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
			//+ "  { display: '过热段压降', name: 'SUPERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
			//+ "  { display: '再热段压降', name: 'RERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
			//+ "  { display: '掺混器压降', name: 'MIXER_PRESS_REDUCTION' ,align: 'center', minWidth: 80}"
			//+"]},"
			+ "  { display: '柱塞泵出口压力(MPa)', name: 'PUMPOUT_PRESS' ,align: 'center', minWidth: 90},"
			+" {display:'对流段',columns:["
			+ "  { display: '对流段入口压力(MPa)', name: 'CSIN_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段入口温度(℃)', name: 'CSIN_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段出口压力(MPa)', name: 'CSOUT_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段出口温度(℃)', name: 'CSOUT_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段压降(MPa)', name: 'CONVECTIONR_PRESS_REDUCTION' ,align: 'center', minWidth: 80}"
			+"]},"
			+"{display :'辐射段', columns:["
			+ "  { display: '辐射段入口压力(MPa)', name: 'RSIN_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段入口温度(℃)', name: 'RSIN_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段出口压力(MPa)', name: 'RS_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段出口温度(℃)', name: 'RSOUT_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段出口管温(℃)', name: 'RS_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段压降(Mpa)', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 90}"
			+"]},"
			+"{display :'再热段', columns:["
			+ "  { display: '再热段入口压力(MPa)', name: 'REHEAT_PRESS_ENTRANCE' ,align: 'center', minWidth: 90},"
			+ "  { display: '再热段入口温度(℃)', name: 'REHEAT_TEMP_ENTRANCE' ,align: 'center', minWidth: 90},"
			+ "  { display: '再热段出口管温(℃)', name: 'REHEAT_TEMP_EXPORT' ,align: 'center', minWidth: 90},"
			+ "  { display: '再热段压降(Mpa)', name: 'RERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 90}"
			+"]},"
			+"{display:'天然气', columns:["
			+ "  { display: '天然气阀后压力(MPa)', name: 'GAS2_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '膨胀管压力(KPa)', name: 'GAS3_PRESS' ,align: 'center', minWidth: 80}"
			+"]},"
			+ "  { display: '润滑油温度(℃)', name: 'LUBE_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '鼓风机进风温度', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '燃烧器喉温(℃)', name: 'BURNER_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '炉膛压力(Pa)', name: 'HEARTH_PRESS' ,align: 'center', minWidth: 60},"
			+ "  { display: '排烟温度(℃)', name: 'EJECTSMOKE_TEMP' ,align: 'center', minWidth: 60},"
			+"{display:'电机参数',columns:["
			+ "  { display: '电网电压(V)', name: 'SYSTEM_VOLTAGE' ,align: 'center', minWidth: 60},"
			+ "  { display: '柱塞泵电流(A)', name: 'PUMP_MOTOR_CURRENT' ,align: 'center', minWidth: 80},"
			+ "  { display: '柱塞泵电机温度(℃)', name: 'PUMP_MOTOR_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '鼓风机电流(A)', name: 'FAN_MOTOR_CURRENT' ,align: 'center', minWidth: 80}"
			+"]},"
			+ "  { display: '锅炉房可燃气体浓度(%)', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 110},"
			+ "  { display: 'H2s气体浓度(ppm)', name: 'H2S_DENSITY' ,align: 'center', minWidth: 80},"
			
			+ "  { display: '污水缓冲罐液位(m)', name: 'SEWAGE_BUFFER_TANK' ,align: 'center', minWidth: 110},"
			+ "  { display: '天然气日累积量', name: 'DAILY_CUMULATIVE_GAS' ,align: 'center', minWidth: 80},"
			+ "  { display: '注汽日累积量', name: 'DAILY_CUMULATIVE_STEAM' ,align: 'center', minWidth: 110},"
			+ "  { display: '供气单位', name: 'STEAM_WORK_GROUP' ,align: 'center', minWidth: 50},"
			+ "  { display: '采油站', name: 'OILDRILLING_STATION_NAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '注汽单位', name: 'STEAM_INJE_UNIT' ,align: 'center', minWidth: 80},"
			+ "  { display: '备注', name: 'REMARK' ,align: 'center', minWidth: 200}"
			+ " ],"+
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'ggdglrd_searchGGDRD.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
		//高干度锅炉日报维护
		public static final String PC_RPD_BOILER_HIGH_DRY_T_WH = " columns: ["
			+ "  { display: '高干度锅炉日报ID', name: 'RPD_BOILER_HIGH_DRY_ID' ,align: 'center', minWidth: 150,hide:true},"
			+ "  { display: '受汽区块', name: 'BLOCK_NAME' ,align: 'center', minWidth: 100,frozen:true},"
			+ "  { display: '接转战名称', name: 'BLOCKSTATION_NAME' ,align: 'center', minWidth: 100,hide:true},"
			+ "  { display: '锅炉名称', name: 'BOILER_NAME' ,align: 'center', minWidth: 100,frozen:true},"
			+ "  { display: '采集时间', name: 'REPORT_DATE' ,align: 'center', minWidth: 130,frozen:true},"
			//+ "  { display: '运行状态', name: 'RUN_STATUS' ,align: 'center', minWidth: 80,frozen:true},"
			+ "  { display: '运行时间', name: 'RUNTIME' ,align: 'center', minWidth: 80},"
			//+ "  { display: '鼓风机进风温度', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 90},"
			//+ "  { display: '锅炉房可燃气体浓度', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 110},"
			//+ "  { display: 'H2s气体浓度', name: 'H2S_DENSITY' ,align: 'center', minWidth: 80},"
			//+ "  { display: '系统总压降', name: 'SYSTEM_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
			//+ "  { display: '减少掺混器压降', name: 'MIXER_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
			+ "  { display: '天然气', columns:"
			+ "	["
			+ "  { display: '天然气分离器压差(kPa)', name: 'GAS_INTO_PRESS' ,align: 'center', minWidth: 80},"
			+ "  { display: '阀前压力(MPa)', name: 'GAS1_PRESS' ,align: 'center', minWidth: 60},"
			+ "	 { display: '火量(%)', name: 'FIRE_MEASURE' ,align: 'center', minWidth: 40},"
			+ "  { display: '天然气流量(m3/h)', name: 'GAS_FLOW' ,align: 'center', minWidth: 80}"
			+ "	]},"
			+ "  { display: '锅炉给水',columns: ["
			+ "  { display: '给水压力(MPa)', name: 'PUMPIN_PRESS' ,align: 'center', minWidth: 80},"
			+ "  { display: '给水温度(℃)', name: 'PUMPOUT_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '柱塞泵变频频率(Hz)', name: 'PUMPFC_FREQUENCY' ,align: 'center', minWidth: 90},"
			+ "  { display: '给水流量(t/h)', name: 'WATERSUPPLY_FLOW' ,align: 'center', minWidth: 60}"
			+"]},"  
			+ "{display:'蒸汽性质', columns:["
			+ "  { display: '蒸汽出口压力(MPa)', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '蒸汽出口温度(℃)', name: 'STEAMOUT_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '蒸汽出口干度(%)', name: 'STEAM_DRYNESS' ,align: 'center', minWidth: 80}"
			+"]},"
			//+ "{display:'运行关键参数', columns:["
			//+ "  { display: '辐射段压降', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
			//+ "  { display: '过热段压降', name: 'SUPERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
			//+ "  { display: '再热段压降', name: 'RERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
			//+ "  { display: '掺混器压降', name: 'MIXER_PRESS_REDUCTION' ,align: 'center', minWidth: 80}"
			//+"]},"
			+ "  { display: '柱塞泵出口压力(MPa)', name: 'PUMPOUT_PRESS' ,align: 'center', minWidth: 90},"
			+" {display:'对流段',columns:["
			+ "  { display: '对流段入口压力(MPa)', name: 'CSIN_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段入口温度(℃)', name: 'CSIN_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段出口压力(MPa)', name: 'CSOUT_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段出口温度(℃)', name: 'CSOUT_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段压降(MPa)', name: 'CONVECTIONR_PRESS_REDUCTION' ,align: 'center', minWidth: 80}"
			+"]},"
			+"{display :'辐射段', columns:["
			+ "  { display: '辐射段入口压力(MPa)', name: 'RSIN_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段入口温度(℃)', name: 'RSIN_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段出口压力(MPa)', name: 'RS_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段出口温度(℃)', name: 'RSOUT_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段出口管温(℃)', name: 'RS_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段压降(Mpa)', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 90}"
			+"]},"
			+"{display :'再热段', columns:["
			+ "  { display: '再热段入口压力(MPa)', name: 'REHEAT_PRESS_ENTRANCE' ,align: 'center', minWidth: 90},"
			+ "  { display: '再热段入口温度(℃)', name: 'REHEAT_TEMP_ENTRANCE' ,align: 'center', minWidth: 90},"
			+ "  { display: '再热段出口管温(℃)', name: 'REHEAT_TEMP_EXPORT' ,align: 'center', minWidth: 90},"
			+ "  { display: '再热段压降(Mpa)', name: 'RERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 90}"
			+"]},"
			+"{display:'天然气', columns:["
			+ "  { display: '天然气阀后压力(MPa)', name: 'GAS2_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '膨胀管压力(KPa)', name: 'GAS3_PRESS' ,align: 'center', minWidth: 80}"
			+"]},"
			+ "  { display: '润滑油温度(℃)', name: 'LUBE_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '鼓风机进风温度', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '燃烧器喉温(℃)', name: 'BURNER_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '炉膛压力(Pa)', name: 'HEARTH_PRESS' ,align: 'center', minWidth: 60},"
			+ "  { display: '排烟温度(℃)', name: 'EJECTSMOKE_TEMP' ,align: 'center', minWidth: 60},"
			+"{display:'电机参数',columns:["
			+ "  { display: '电网电压(V)', name: 'SYSTEM_VOLTAGE' ,align: 'center', minWidth: 60},"
			+ "  { display: '柱塞泵电流(A)', name: 'PUMP_MOTOR_CURRENT' ,align: 'center', minWidth: 80},"
			+ "  { display: '柱塞泵电机温度(℃)', name: 'PUMP_MOTOR_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '鼓风机电流(A)', name: 'FAN_MOTOR_CURRENT' ,align: 'center', minWidth: 80}"
			+"]},"
			+ "  { display: '锅炉房可燃气体浓度(%)', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 110},"
			+ "  { display: 'H2s气体浓度(ppm)', name: 'H2S_DENSITY' ,align: 'center', minWidth: 80},"
			
			+ "  { display: '污水缓冲罐液位(m)', name: 'SEWAGE_BUFFER_TANK' ,align: 'center', minWidth: 110},"
			+ "  { display: '天然气日累积量(m3)', name: 'DAILY_CUMULATIVE_GAS' ,align: 'center', minWidth: 80},"
			+ "  { display: '注汽日累积量(t)', name: 'DAILY_CUMULATIVE_STEAM' ,align: 'center', minWidth: 110},"
			+ "  { display: '供气单位', name: 'STEAM_WORK_GROUP' ,align: 'center', minWidth: 50},"
			+ "  { display: '采油站', name: 'OILDRILLING_STATION_NAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '注汽单位', name: 'STEAM_INJE_UNIT' ,align: 'center', minWidth: 80},"
			+ "  { display: '备注', name: 'REMARK' ,align: 'center', minWidth: 200},"
			+ "  { display: '审核人', name: 'CHECK_OPER' ,align: 'center', minWidth: 100},"
			+ "  { display: '审核时间', name: 'CHECK_DATE' ,align: 'center', minWidth: 120},"
			+ "  { display: '上次操作者', name: 'RLAST_OPER' ,align: 'center', minWidth: 100},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE' ,align: 'center', minWidth: 120}"
			
			+ " ],"+
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'ggdglrd_searchGGDRD.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			//"enabledEdit: true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :true";
		
		
		//二号联合站水质监测
		public static final String U2_WATER_QUALITY = " columns: ["
//				+ " { display: '时间', columns:"
//				+ "	["
				+ "		{ display: '所属日期', name: 'REPORT_DATE', align: 'center', width: 90,frozen:true},"
				+ "		{ display: '报表时间', name: 'REPORT_TIME', width: 80, align: 'center',frozen:true},"
//				+ "	]},"
				
				+ "  { display: '含油（mg/L)', columns:"
				+ "	["
				+ "		{ display: '沉降出', columns:"
				+ "		[{ display: '', name: 'HYCJC', align: 'center', width: 60}]},"
				+ "		{ display: '调进', columns:"
				+ "		[{ display: '≤3000', name: 'HYDJ', align: 'center', width: 60}]},"
				+ "		{ display: '1#-2#', columns:"
				+ "		[{ display: '≤300', name: 'HY1_2', align: 'center', width: 60}]},"
				+ "		{ display: '调出', columns:"
				+ "		[{ display: '≤200', name: 'HYDC', align: 'center', width: 60}]},"
				+ "		{ display: '反出', columns:"
				+ "		[{ display: '≤10', name: 'HYFC', align: 'center', width: 60}]},"
				+ "		{ display: '混凝出', columns:"
				+ "		[{ display: '≤7', name: 'HYHNC', align: 'center', width: 60}]},"
				+ "		{ display: '一级进', columns:"
				+ "		[{ display: '≤5', name: 'HYYJJ', align: 'center', width: 60}]},"
				+ "		{ display: '一级出', columns:"
				+ "		[{ display: '≤3', name: 'HYYJC', align: 'center', width: 60}]},"
				+ "		{ display: '二级出', columns:"
				+ "		[{ display: '≤2', name: 'HYEJC', align: 'center', width: 60}]},"
				+ "		{ display: '软化水', columns:"
				+ "		[{ display: '≤2', name: 'HYRHS', align: 'center', width: 60}]}"
				
				+ "	]},"
				
				+ "  { display: '悬浮（mg/L)', columns:"
				+ "	["
				+ "		{ display: '沉降出', columns:"
				+ "		[{ display: '', name: 'XFCJC', align: 'center', width: 60}]},"
				+ "		{ display: '调进', columns:"
				+ "		[{ display: '≤1000', name: 'XFDJ', align: 'center', width: 60}]},"
				+ "		{ display: '1#-2#', columns:"
				+ "		[{ display: '≤300', name: 'XF1_2', align: 'center', width: 60}]},"
				+ "		{ display: '调出', columns:"
				+ "		[{ display: '≤200', name: 'XFDC', align: 'center', width: 60}]},"
				+ "		{ display: '反出', columns:"
				+ "		[{ display: '≤10', name: 'XFFC', align: 'center', width: 60}]},"
				+ "		{ display: '混凝出', columns:"
				+ "		[{ display: '≤7', name: 'XFHNC', align: 'center', width: 60}]},"
				+ "		{ display: '一级进', columns:"
				+ "		[{ display: '≤5', name: 'XFYJJ', align: 'center', width: 60}]},"
				+ "		{ display: '一级出', columns:"
				+ "		[{ display: '≤3', name: 'XFYJC', align: 'center', width: 60}]},"
				+ "		{ display: '二级出', columns:"
				+ "		[{ display: '≤2', name: 'XFEJC', align: 'center', width: 60}]},"
				+ "		{ display: '软化水', columns:"
				+ "		[{ display: '≤2', name: 'XFRHS', align: 'center', width: 60}]}"
				
				+ "	]},"
				
				+ "  { display: '硬度（mg/L)', columns:"
				+ "	["
				+ "		{ display: '沉降出', columns:"
				+ "		[{ display: '', name: 'YDCJC', align: 'center', width: 60}]},"
				+ "		{ display: '调进', columns:"
				+ "		[{ display: '', name: 'YDDJ', align: 'center', width: 60}]},"
				+ "		{ display: '调出', columns:"
				+ "		[{ display: '', name: 'YDDC', align: 'center', width: 60}]},"
				+ "		{ display: '反出', columns:"
				+ "		[{ display: '', name: 'YDFC', align: 'center', width: 60}]},"
				+ "		{ display: '混凝出', columns:"
				+ "		[{ display: '', name: 'YDHNC', align: 'center', width: 60}]},"
				+ "		{ display: '一级进', columns:"
				+ "		[{ display: '', name: 'YDYJJ', align: 'center', width: 60}]},"
				+ "		{ display: '一级出', columns:"
				+ "		[{ display: '', name: 'HNYJC', align: 'center', width: 60}]},"
				+ "		{ display: '二级出', columns:"
				+ "		[{ display: '', name: 'HNEJC', align: 'center', width: 60}]}"
				
				+ "	]}," +
//				"{ display: '操作', name: 'OPERATION', align: 'center', width: 100 },"+
				"{ display: '水质监测数据ID', name: 'RPD_U2_WATER_QUALITY_ID', align: 'center', width: 60 ,hide:true}"+
				
				"]," +
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'u2szjc_searchU2SZJC.action',"+
				"delayLoad :true, "+
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"sePaper:false,"+
				"pageSize:1000,"+
				"rownumbers:true ,"+
				"frozen:true, "+
//				"enabledEdit: true," +
//				"clickToEdit:false,  "+
				
//				"onBeforeEdit: f_onBeforeEdit, "+
//				"onAfterEdit: f_onAfterEdit, "+
//				"frozenRownumbers :true, "+
				
				"checkbox :false, "+
				"onSelectRow: function (rowdata, rowindex) "+
				"{fromAu(rowdata);}";
//				"onSelectRow: function (rowdata, rowindex) "+
//			 "{" +
//			 "	$('#txtrowindex').val(rowindex);" +
//			 "} ";		
//		//二号联合站水质监测
//		public static final String U2_WATER_QUALITY = " columns: ["
////				+ " { display: '时间', columns:"
////				+ "	["
//				+ "		{ display: '所属日期', name: 'REPORT_DATE', align: 'center', width: 90,frozen:true},"
//				+ "		{ display: '报表时间', name: 'REPORT_TIME', width: 80, align: 'center',frozen:true},"
////				+ "	]},"
//				
//				+ "  { display: '含油（mg/L)', columns:"
//				+ "	["
//				+ "		{ display: '沉降出', columns:"
//				+ "		[{ display: '', name: 'HYCJC', align: 'center', width: 60,  editor:{ type: 'float' }}]},"
//				+ "		{ display: '调进', columns:"
//				+ "		[{ display: '≤3000', name: 'HYDJ', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '1#-2#', columns:"
//				+ "		[{ display: '≤300', name: 'HY1_2', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '调出', columns:"
//				+ "		[{ display: '≤200', name: 'HYDC', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '反出', columns:"
//				+ "		[{ display: '≤10', name: 'HYFC', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '混凝出', columns:"
//				+ "		[{ display: '≤7', name: 'HYHNC', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '一级进', columns:"
//				+ "		[{ display: '≤5', name: 'HYYJJ', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '一级出', columns:"
//				+ "		[{ display: '≤3', name: 'HYYJC', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '二级出', columns:"
//				+ "		[{ display: '≤2', name: 'HYEJC', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '软化水', columns:"
//				+ "		[{ display: '≤2', name: 'HYRHS', align: 'center', width: 60,editor:{ type: 'text' }}]}"
//				
//				+ "	]},"
//				
//				+ "  { display: '悬浮（mg/L)', columns:"
//				+ "	["
//				+ "		{ display: '沉降出', columns:"
//				+ "		[{ display: '', name: 'XFCJC', align: 'center', width: 60,editor:{ type: 'float' }}]},"
//				+ "		{ display: '调进', columns:"
//				+ "		[{ display: '≤1000', name: 'XFDJ', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '1#-2#', columns:"
//				+ "		[{ display: '≤300', name: 'XF1_2', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '调出', columns:"
//				+ "		[{ display: '≤200', name: 'XFDC', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '反出', columns:"
//				+ "		[{ display: '≤10', name: 'XFFC', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '混凝出', columns:"
//				+ "		[{ display: '≤7', name: 'XFHNC', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '一级进', columns:"
//				+ "		[{ display: '≤5', name: 'XFYJJ', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '一级出', columns:"
//				+ "		[{ display: '≤3', name: 'XFYJC', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '二级出', columns:"
//				+ "		[{ display: '≤2', name: 'XFEJC', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '软化水', columns:"
//				+ "		[{ display: '≤2', name: 'XFRHS', align: 'center', width: 60,editor:{ type: 'text' }}]}"
//				
//				+ "	]},"
//				
//				+ "  { display: '硬度（mg/L)', columns:"
//				+ "	["
//				+ "		{ display: '沉降出', columns:"
//				+ "		[{ display: '', name: 'YDCJC', align: 'center', width: 60,editor:{ type: 'float' }}]},"
//				+ "		{ display: '调进', columns:"
//				+ "		[{ display: '', name: 'YDDJ', align: 'center', width: 60,editor:{ type: 'float' }}]},"
//				+ "		{ display: '调出', columns:"
//				+ "		[{ display: '', name: 'YDDC', align: 'center', width: 60,editor:{ type: 'float' }}]},"
//				+ "		{ display: '反出', columns:"
//				+ "		[{ display: '', name: 'YDFC', align: 'center', width: 60,editor:{ type: 'float' }}]},"
//				+ "		{ display: '混凝出', columns:"
//				+ "		[{ display: '', name: 'YDHNC', align: 'center', width: 60,editor:{ type: 'float' }}]},"
//				+ "		{ display: '一级进', columns:"
//				+ "		[{ display: '', name: 'YDYJJ', align: 'center', width: 60,editor:{ type: 'float' }}]},"
//				+ "		{ display: '一级出', columns:"
//				+ "		[{ display: '', name: 'HNYJC', align: 'center', width: 60,editor:{ type: 'float' }}]},"
//				+ "		{ display: '二级出', columns:"
//				+ "		[{ display: '', name: 'HNEJC', align: 'center', width: 60,editor:{ type: 'float' }}]}"
//				
//				+ "	]}," +
////				"{ display: '操作', name: 'OPERATION', align: 'center', width: 100 },"+
//				"{ display: '水质监测数据ID', name: 'RPD_U2_WATER_QUALITY_ID', align: 'center', width: 60 ,hide:true}"+
//				
//				"]," +
//		
//				"height:'100%',"+
//				"dataAction: 'server',"+
//				"url:'u2szjc_searchU2SZJC.action',"+	
//				"pageParmName :'pageNo',"+
//				"sortnameParmName:'sort',"+
//				"sortorderParmName: 'order',  "+
//				"pagesizeParmName:'rowsPerpage', "+
//				"selectRowButtonOnly:true,"+
//				"sePaper:false,"+
//				"pageSize:1000 ,"+
//				"rownumbers:true ,"+
//				"frozen:true, "+
////				"onBeforeCheckRow :f_onBeforeCheckRow,"+
//				"enabledEdit: true," +
////				"clickToEdit:false,  "+
//				
//				"onBeforeEdit: f_onBeforeEdit, "+
//				"onAfterEdit: f_onAfterEdit, "+
////				"frozenRownumbers :true, "+
//				
//				"checkbox :false, "+
//				"onSelectRow: function (rowdata, rowindex) "+
//				"{fromAu(rowdata);}";
////				"onSelectRow: function (rowdata, rowindex) "+
////			 "{" +
////			 "	$('#txtrowindex').val(rowindex);" +
////			 "} ";
		//一号稠油联合处理站水一区水质监测记录报表
		public static final String U1_WATER_QUALITY1 = " columns: ["
//				+ " { display: '时间', columns:"
//				+ "	["
				+ "		{ display: '所属日期', name: 'BBRQ', align: 'center', width: 90,frozen:true},"
				+ "		{ display: '报表时间', name: 'BBSJ', width: 80, align: 'center',frozen:true},"
//				+ "	]},"
				
				+ "  { display: '污水含油（mg/L）', columns:"
				+ "	["
				+ "		{ display: '调储罐进口', columns:"
				+ "		[{ display: '≤5000', name: 'HYTCGJK', align: 'center',width: 70}]},"
//				+ "		{ display: '11#沉降罐高出水', columns:"
//				+ "		[{ display: '', name: 'HYCJG11GCS', align: 'center',width: 70}]},"
//				+ "		{ display: 'SAGD出水', columns:"
//				+ "		[{ display: '≤8000', name: 'HYSAGDCS', align: 'center', width: 70}]},"
//				+ "		{ display: '旋流进口', columns:"
//				+ "		[{ display: '', name: 'HYXLJK', align: 'center',width: 70}]},"
//				+ "		{ display: '旋流出口', columns:"
//				+ "		[{ display: '', name: 'HYXLCK', align: 'center',width: 70}]},"
				+ "		{ display: '2#到1#罐', columns:"
				+ "		[{ display: '≤300', name: 'HYG2_1', align: 'center', width: 70}]},"
				+ "		{ display: '调储罐出口', columns:"
				+ "		[{ display: '≤150', name: 'HYTCGCK', align: 'center', width: 70}]},"
				+ "		{ display: '反应罐出口', columns:"
				+ "		[{ display: '≤15', name: 'HYGYGCK', align: 'center', width: 70}]},"
				+ "		{ display: '斜板罐出口', columns:"
				+ "		[{ display: '≤10', name: 'HYXBGCK', align: 'center', width: 70}]},"
				+ "		{ display: '一级进', columns:"
				+ "		[{ display: '≤5', name: 'HYYJJK', align: 'center', width: 70}]},"
				+ "		{ display: '一级出', columns:"
				+ "		[{ display: '≤3', name: 'HYYKCK', align: 'center', width: 70}]},"
				+ "		{ display: '二级出', columns:"
				+ "		[{ display: '≤2', name: 'HYEJCK', align: 'center', width: 70}]}"
			
				
				+ "	]},"
				
				+ "  { display: '污水含悬浮物（mg/L）', columns:"
				+ "	["
				+ "		{ display: '调储罐进口', columns:"
				+ "		[{ display: '≤5000', name: 'XFWTCGJK', align: 'center', width: 70}]},"
//				+ "		{ display: '11#沉降罐高出水', columns:"
//				+ "		[{ display: '', name: 'XFWCJGGCS', align: 'center', width: 70}]},"
//				+ "		{ display: '旋流进口', columns:"
//				+ "		[{ display: '', name: 'XFWXLJK', align: 'center', width: 70}]},"
//				+ "		{ display: '旋流出口', columns:"
//				+ "		[{ display: '', name: 'XFWXLCK1J', align: 'center', width: 70}]},"
				+ "		{ display: '2#到1#罐', columns:"
				+ "		[{ display: '≤300', name: 'XFWG2_1', align: 'center', width: 70}]},"
				+ "		{ display: '调储罐出口', columns:"
				+ "		[{ display: '≤150', name: 'XFWTCGCK', align: 'center', width: 70}]},"
				+ "		{ display: '反应罐出口', columns:"
				+ "		[{ display: '≤15', name: 'XFWFYGCK', align: 'center', width: 70}]},"
				+ "		{ display: '斜板罐出口', columns:"
				+ "		[{ display: '≤10', name: 'XFWXBGCK', align: 'center', width: 70}]},"
				+ "		{ display: '一级进', columns:"
				+ "		[{ display: '≤5', name: 'XFWYJJK', align: 'center', width: 70}]},"
				+ "		{ display: '一级出', columns:"
				+ "		[{ display: '≤3', name: 'XFWYJCK', align: 'center', width: 70}]},"
				+ "		{ display: '二级出', columns:"
				+ "		[{ display: '≤2', name: 'XFWEJCK', align: 'center', width: 70}]}"
				
				+ "	]},"
				
				+ "  { display: '污水硬度（mg/L）', columns:"
				+ "	["
				+ "		{ display: '调储罐进口', columns:"
				+ "		[{ display: '≤120', name: 'YDTCGJK', align: 'center', width: 70}]},"
				+ "		{ display: '2#到1#罐', columns:"
				+ "		[{ display: '≤120', name: 'YD2_1', align: 'center', width: 70}]},"
				+ "		{ display: '调储罐出口', columns:"
				+ "		[{ display: '≤120', name: 'YDTCGCK', align: 'center', width: 70}]},"
				+ "		{ display: '反应罐出口', columns:"
				+ "		[{ display: '≤120', name: 'YDFYGCK', align: 'center', width: 70}]},"
				+ "		{ display: '斜板罐出口', columns:"
				+ "		[{ display: '≤120', name: 'YDXBGCK', align: 'center', width: 70}]},"
				+ "		{ display: '一级进口', columns:"
				+ "		[{ display: '≤120', name: 'YDYJJK', align: 'center', width: 70}]},"
				+ "		{ display: '一级出口', columns:"
				+ "		[{ display: '≤120', name: 'YDYJCK', align: 'center', width: 70}]},"
				+ "		{ display: '二级出口', columns:"
				+ "		[{ display: '≤120', name: 'YDEJCK', align: 'center', width: 70}]}"				
				+ "	]}," 
				+ "  { display: '二氧化硅（mg/L）', columns:"
				+ "	["
				+ "		{ display: '调储罐进口', columns:"
				+ "		[{ display: '', name: 'GTCGJK', align: 'center', width: 70}]},"
				+ "		{ display: '反应器出口', columns:"
				+ "		[{ display: '', name: 'GFYQCK', align: 'center', width: 70}]},"
				+ "		{ display: '斜板罐出口', columns:"
				+ "		[{ display: '≤130', name: 'GXBGCK', align: 'center', width: 70}]}"
				+ "	]}," +
				"{ display: '备注', name: 'BZ', align: 'center', width: 150 },"+
				"{ display: '一号稠油联合处理站水一区水质监测数据ID', name: 'RPD_U1_WATER_QUALITY1_ID', align: 'center', width: 60 ,hide:true}"+
				
				"]," +
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'u1q1szjc_searchU2SZJC.action',"+
				"delayLoad :true, "+
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"sePaper:false,"+
				"pageSize:1000 ,"+
				"rownumbers:true ,"+
				"frozen:true, "+
				"checkbox :false, "+
				"onSelectRow: function (rowdata, rowindex) "+
				"{fromAu(rowdata);}";

		
//		//一号稠油联合处理站水一区水质监测记录报表
//		public static final String U1_WATER_QUALITY1 = " columns: ["
////				+ " { display: '时间', columns:"
////				+ "	["
//				+ "		{ display: '所属日期', name: 'BBRQ', align: 'center', width: 90,frozen:true},"
//				+ "		{ display: '报表时间', name: 'BBSJ', width: 80, align: 'center',frozen:true},"
////				+ "	]},"
//				
//				+ "  { display: '污水含油（mg/L）', columns:"
//				+ "	["
//				+ "		{ display: '调储罐进口', columns:"
//				+ "		[{ display: '≤4000', name: 'HYTCGJK', align: 'center', width: 60,  editor:{ type: 'float' }}]},"
//				+ "		{ display: '11#沉降罐高出水', columns:"
//				+ "		[{ display: '', name: 'HYCJG11GCS', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: 'SAGD出水', columns:"
//				+ "		[{ display: '≤8000', name: 'HYSAGDCS', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '旋流进口', columns:"
//				+ "		[{ display: '', name: 'HYXLJK', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '旋流出口', columns:"
//				+ "		[{ display: '', name: 'HYXLCK', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '2#到1#罐', columns:"
//				+ "		[{ display: '≤400', name: 'HYG2_1', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '调储罐出口', columns:"
//				+ "		[{ display: '≤150', name: 'HYTCGCK', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '反应罐出口', columns:"
//				+ "		[{ display: '≤15', name: 'HYGYGCK', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '斜板罐出口', columns:"
//				+ "		[{ display: '≤8', name: 'HYXBGCK', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '一级进', columns:"
//				+ "		[{ display: '≤5', name: 'HYYJJK', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '一级出', columns:"
//				+ "		[{ display: '≤3', name: 'HYYKCK', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '二级出', columns:"
//				+ "		[{ display: '≤2', name: 'HYEJCK', align: 'center', width: 60,editor:{ type: 'text' }}]}"
//			
//				
//				+ "	]},"
//				
//				+ "  { display: '污水含悬浮物（mg/L）', columns:"
//				+ "	["
//				+ "		{ display: '调储罐进口', columns:"
//				+ "		[{ display: '≤4000', name: 'XFWTCGJK', align: 'center', width: 60,  editor:{ type: 'float' }}]},"
//				+ "		{ display: '11#沉降罐高出水', columns:"
//				+ "		[{ display: '', name: 'XFWCJGGCS', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '旋流进口', columns:"
//				+ "		[{ display: '', name: 'XFWXLJK', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '旋流出口', columns:"
//				+ "		[{ display: '', name: 'XFWXLCK1J', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '2#到1#罐', columns:"
//				+ "		[{ display: '≤400', name: 'XFWG2_1', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '调储罐出口', columns:"
//				+ "		[{ display: '≤150', name: 'XFWTCGCK', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '反应罐出口', columns:"
//				+ "		[{ display: '≤15', name: 'XFWFYGCK', align: 'center', width: 60,  editor:{ type: 'text' }}]},"
//				+ "		{ display: '斜板罐出口', columns:"
//				+ "		[{ display: '≤8', name: 'XFWXBGCK', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '一级进', columns:"
//				+ "		[{ display: '≤5', name: 'XFWYJJK', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '一级出', columns:"
//				+ "		[{ display: '≤3', name: 'XFWYJCK', align: 'center', width: 60,editor:{ type: 'text' }}]},"
//				+ "		{ display: '二级出', columns:"
//				+ "		[{ display: '≤2', name: 'XFWEJCK', align: 'center', width: 60,editor:{ type: 'text' }}]}"
//				
//				+ "	]},"
//				
//				+ "  { display: '污水硬度（mg/L）', columns:"
//				+ "	["
//				+ "		{ display: '反应罐', columns:"
//				+ "		[{ display: '', name: 'YDFYGCK', align: 'center', width: 60,editor:{ type: 'float' }}]},"
//				+ "		{ display: '二级出口', columns:"
//				+ "		[{ display: '', name: 'YDEJCK', align: 'center', width: 60,editor:{ type: 'float' }}]}"
//			
//				
//				+ "	]}," +
////				"{ display: '操作', name: 'OPERATION', align: 'center', width: 100 },"+
//				"{ display: '一号稠油联合处理站水一区水质监测数据ID', name: 'RPD_U1_WATER_QUALITY1_ID', align: 'center', width: 60 ,hide:true}"+
//				
//				"]," +
//		
//				"height:'100%',"+
//				"dataAction: 'server',"+
//				"url:'u1q1szjc_searchU2SZJC.action',"+	
//				"pageParmName :'pageNo',"+
//				"sortnameParmName:'sort',"+
//				"sortorderParmName: 'order',  "+
//				"pagesizeParmName:'rowsPerpage', "+
//				"selectRowButtonOnly:true,"+
//				"sePaper:false,"+
//				"pageSize:1000 ,"+
//				"rownumbers:true ,"+
//				"frozen:true, "+
////				"onBeforeCheckRow :f_onBeforeCheckRow,"+
//				"enabledEdit: true," +
////				"clickToEdit:false,  "+
//				
//				"onBeforeEdit: f_onBeforeEdit, "+
//				"onAfterEdit: f_onAfterEdit, "+
////				"frozenRownumbers :true, "+
//				
//				"checkbox :false, "+
//				"onSelectRow: function (rowdata, rowindex) "+
//				"{fromAu(rowdata);}";
////				"onSelectRow: function (rowdata, rowindex) "+
////			 "{" +
////			 "	$('#txtrowindex').val(rowindex);" +
////			 "} ";
//		
		//一号稠油联合处理站水二区水质监测记录报表
		public static final String U1_WATER_QUALITY2 = " columns: ["
//			+ " { display: '时间', columns:"
//			+ "	["
+ "		{ display: '所属日期', name: 'BBRQ', align: 'center', width: 90,frozen:true},"
+ "		{ display: '报表时间', name: 'BBSJ', width: 90, align: 'center',frozen:true},"
//+ "	]},"

+ "  { display: '污水含油', columns:"
+ "	["
+ "		{ display: '水2区调储罐进口', columns:"
+ "		[{ display: '≤5000', name: 'HYS2TCGJK', align: 'center', width: 100}]},"
+ "		{ display: '水2区调储罐出口', columns:"
+ "		[{ display: '≤150', name: 'HYS2TCGCK', align: 'center', width: 100}]},"
+ "		{ display: '4#-3#', columns:"
+ "		[{ display: '≤300', name: 'HYG4_3', align: 'center', width: 90}]},"
+ "		{ display: '水2区气浮出口', columns:"
+ "		[{ display: '≤15', name: 'HYS2QFCK', align: 'center', width: 90}]},"
+ "		{ display: '水2区缓冲池', columns:"
+ "		[{ display: '≤10', name: 'HYS2HCC', align: 'center', width: 90}]},"
+ "		{ display: '水2区一级出口', columns:"
+ "		[{ display: '≤5', name: 'HYS2YJCK', align: 'center', width: 90}]},"
+ "		{ display: '水2区二级出口', columns:"
+ "		[{ display: '≤2', name: 'HYS2EJCK', align: 'center', width: 90}]}"

+ "	]},"

+ "  { display: '污水悬浮', columns:"
+ "	["
+ "		{ display: '水2区调储罐进口', columns:"
+ "		[{ display: '≤500', name: 'XFS2TCGJK', align: 'center', width: 100}]},"
+ "		{ display: '水2区调储罐出口', columns:"
+ "		[{ display: '≤150', name: 'XFS2TCGCK', align: 'center', width: 100}]},"
+ "		{ display: '4#-3#', columns:"
+ "		[{ display: '≤300', name: 'XFG4_3', align: 'center', width: 90}]},"
+ "		{ display: '水2区气浮出口', columns:"
+ "		[{ display: '≤30', name: 'XFS2QFCK', align: 'center', width: 90}]},"
+ "		{ display: '水2区缓冲池', columns:"
+ "		[{ display: '≤10', name: 'XFS2HCC', align: 'center', width: 90}]},"
+ "		{ display: '水2区一级出口', columns:"
+ "		[{ display: '≤5', name: 'XFS2YJCK', align: 'center', width: 90}]},"
+ "		{ display: '水2区二级出口', columns:"
+ "		[{ display: '≤2', name: 'XFS2EJCK', align: 'center', width: 90}]}"

+ "	]},"

+ "  { display: '污水硬度', columns:"
+ "	["
+ "		{ display: '水2区调储罐进口', columns:"
+ "		[{ display: '≤120', name: 'YDS2TCGJK', align: 'center', width: 100}]},"
+ "		{ display: '水2区调储罐出口', columns:"
+ "		[{ display: '≤120', name: 'YDS2TCGCK', align: 'center', width: 100}]},"
+ "		{ display: '4#-3#', columns:"
+ "		[{ display: '≤120', name: 'YD4_3', align: 'center', width: 90}]},"
+ "		{ display: '水2区缓冲池', columns:"
+ "		[{ display: '≤120', name: 'YDS2HCC', align: 'center', width: 90}]},"
+ "		{ display: '水2区气浮', columns:"
+ "		[{ display: '≤120', name: 'YDS2QF', align: 'center', width: 90}]},"
+ "		{ display: '水2区一级出口', columns:"
+ "		[{ display: '≤120', name: 'YDS2YJCK', align: 'center', width: 90}]},"
+ "		{ display: '水2区二级出口', columns:"
+ "		[{ display: '≤120', name: 'YDS2EJCK', align: 'center', width: 90}]}"

+ "	]}," +
"{ display: '备注', name: 'BZ', align: 'center', width: 150 },"+
"{ display: '一号稠油联合处理站水二区水质监测数据ID', name: 'RPD_U1_WATER_QUALITY2_ID', align: 'center', width: 60 ,hide:true}"+

"]," +

"height:'100%',"+
"dataAction: 'server',"+
"url:'u1s2szjc_searchU2SZJC.action',"+
"delayLoad :true, "+
"pageParmName :'pageNo',"+
"sortnameParmName:'sort',"+
"sortorderParmName: 'order',  "+
"pagesizeParmName:'rowsPerpage', "+
//"selectRowButtonOnly:true,"+
"sePaper:false,"+
"pageSize:1000 ,"+
"rownumbers:true ,"+
"frozen:true, "+
//"onBeforeCheckRow :f_onBeforeCheckRow,"+
//"enabledEdit: true," +
//"clickToEdit:false,  "+

//"onBeforeEdit: f_onBeforeEdit, "+
//"onAfterEdit: f_onAfterEdit, "+
//"frozenRownumbers :true, "+

"checkbox :false, "+
"onSelectRow: function (rowdata, rowindex) "+
"{fromAu(rowdata);}";
//"onSelectRow: function (rowdata, rowindex) "+
//"{" +
//"	$('#txtrowindex').val(rowindex);" +
//"} ";
		
		
//稀油注输联合站（油区）
		
		public static final String XYYQDT_PAGE_GRID = " columns: ["
				+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 130,frozen:true},"	
				+ "  { display: '乌尔禾1流量(m3/h)', name: 'WEH_FT1' ,align: 'center', minWidth: 85},"
				+ "  { display: '乌尔禾2流量(m3/h)', name: 'WEH_FT2' ,align: 'center', minWidth: 85},"
				+ "  { display: '乌尔禾1含水(%)', name: 'WEH_HS1' ,align: 'center', minWidth: 85},"
				+ "  { display: '乌尔禾2含水(%)', name: 'WEH_HS2' ,align: 'center', minWidth: 85},"
				+ "  { display: '夏子街流量(m3/h)', name: 'XZJ_FT' ,align: 'center', minWidth: 85},"
				+ "  { display: '夏子街含水(%)', name: 'XZJ_HS' ,align: 'center', minWidth: 85},"
				+ "  { display: '去增压站湿气流量(m3/h)', name: 'FIT1101' ,align: 'center', minWidth: 85},"
				+ "  { display: '夏子街来油流量(m3/h)', name: 'FIT11101' ,align: 'center', minWidth: 85},"
				+ "  { display: '35万吨卸油台来油流量计(m3)', name: 'FIT11102' ,align: 'center', minWidth: 85},"
				+ "  { display: '提升泵来油流量计(m3)', name: 'FIT11103' ,align: 'center', minWidth: 85},"
				+ "  { display: '相变炉用干气流量(m3/h)', name: 'FIT1301' ,align: 'center', minWidth: 85},"
				+ "  { display: '出水管线流量(m3/h)', name: 'FIT1851' ,align: 'center', minWidth: 85},"
				+ "  { display: '夏子街来油含水(%)', name: 'HIT1001' ,align: 'center', minWidth: 85},"
				+ "  { display: '1#分离器液位(m)', name: 'LIT1101' ,align: 'center', minWidth: 85},"
				+ "  { display: '2#分离器液位(m)', name: 'LIT1102' ,align: 'center', minWidth: 85},"
				+ "  { display: '除油器液位(m)', name: 'LIT1103' ,align: 'center', minWidth: 85},"
				+ "  { display: '1#沉降罐油厚(m)', name: 'LIT1201' ,align: 'center', minWidth: 85},"
				+ "  { display: '2#沉降罐油厚(m)', name: 'LIT1202' ,align: 'center', minWidth: 85},"
				+ "  { display: '1#缓冲罐液位(m)', name: 'LIT1203' ,align: 'center', minWidth: 85},"
				+ "  { display: '2#缓冲罐液位(m)', name: 'LIT1204' ,align: 'center', minWidth: 85},"
				+ "  { display: '1#净化油罐液位(m)', name: 'LIT1205' ,align: 'center', minWidth: 85},"
				+ "  { display: '2#净化油罐液位(m)', name: 'LIT1206' ,align: 'center', minWidth: 85},"
				+ "  { display: '3#净化油罐液位(m)', name: 'LIT1207' ,align: 'center', minWidth: 85},"
				+ "  { display: '4#净化油罐液位(m)', name: 'LIT1208' ,align: 'center', minWidth: 85},"
				+ "  { display: '5#净化油罐液位(m)', name: 'LIT1209' ,align: 'center', minWidth: 85},"
				+ "  { display: '6#净化油罐液位(m)', name: 'LIT1210' ,align: 'center', minWidth: 85},"
				+ "  { display: '调节水罐液位(m)', name: 'LIT1851' ,align: 'center', minWidth: 85},"
				+ "  { display: '回掺泵含水(%)', name: 'MT1401' ,align: 'center', minWidth: 85},"
				+ "  { display: '提升泵含水(%)', name: 'MT1402' ,align: 'center', minWidth: 85},"
				+ "  { display: '1#输油泵进口压力连锁(Mpa)', name: 'PS101' ,align: 'center', minWidth: 95},"
				+ "  { display: '2#输油泵进口压力连锁	(Mpa)', name: 'PS102' ,align: 'center', minWidth: 95},"
				+ "  { display: '3#输油泵进口压力连锁	(Mpa)', name: 'PS103' ,align: 'center', minWidth: 95},"
				+ "  { display: '输油泵出口压力连锁(Mpa)', name: 'PS104' ,align: 'center', minWidth: 85},"
				+ "  { display: '输油泵进口管汇压力(Mpa)', name: 'PT101' ,align: 'center', minWidth: 85},"
				+ "  { display: '1#输油泵出口压力(Mpa)', name: 'PT105' ,align: 'center', minWidth: 85},"
				+ "  { display: '2#输油泵出口压力(Mpa)', name: 'PT106' ,align: 'center', minWidth: 85},"
				+ "  { display: '3#输油泵出口压力(Mpa)', name: 'PT107' ,align: 'center', minWidth: 85},"
				+ "  { display: '输油泵出口管汇压力(Mpa)', name: 'PT108' ,align: 'center', minWidth: 85},"
				+ "  { display: '外输管线出站压力(Mpa)', name: 'PT109' ,align: 'center', minWidth: 85},"
				+ "  { display: '来油汇管压力(Mpa)', name: 'PT1101' ,align: 'center', minWidth: 85},"
				+ "  { display: '除液器出口压力(Mpa)', name: 'PT1102' ,align: 'center', minWidth: 85},"
				+ "  { display: '相变炉进口原油压力(Mpa)', name: 'PT1301' ,align: 'center', minWidth: 85},"
				+ "  { display: '相变炉出口原油压力(Mpa)', name: 'PT1302' ,align: 'center', minWidth: 85},"
				+ "  { display: '相变炉燃气压力(Mpa)', name: 'PT1303' ,align: 'center', minWidth: 85},"
				+ "  { display: '供水压力(Mpa)', name: 'PT1501' ,align: 'center', minWidth: 85},"
				+ "  { display: '回水压力(Mpa)', name: 'PT1502' ,align: 'center', minWidth: 85},"
				+ "  { display: '变频器频率反馈(Hz)', name: 'SEI1201' ,align: 'center', minWidth: 85},"
				+ "  { display: '变频器控制输出(Hz)', name: 'SEO1201' ,align: 'center', minWidth: 85},"
				+ "  { display: '1#变频调节(Hz)', name: 'SZ101' ,align: 'center', minWidth: 85},"
				+ "  { display: '2#变频调节(Hz)', name: 'SZ102' ,align: 'center', minWidth: 85},"
				+ "  { display: '输油泵进口管汇温度(℃)', name: 'TE101' ,align: 'center', minWidth: 85},"
				+ "  { display: '外输管线出站温度(℃)', name: 'TE102' ,align: 'center', minWidth: 85},"
				+ "  { display: '空气温度(℃)', name: 'TE103' ,align: 'center', minWidth: 85},"
				+ "  { display: '沙土温度(℃)', name: 'TE104' ,align: 'center', minWidth: 85},"
				+ "  { display: '乌33、36来油温度(℃)', name: 'TE1101' ,align: 'center', minWidth: 85},"
				+ "  { display: '乌5来油温度(℃)', name: 'TE1102' ,align: 'center', minWidth: 85},"
				+ "  { display: '夏子街来油水管线温度(℃)', name: 'TE1103' ,align: 'center', minWidth: 85},"
				+ "  { display: '相变炉原油进口温度(℃)', name: 'TE1301' ,align: 'center', minWidth: 85},"
				+ "  { display: '相变炉原油出口温度(℃)', name: 'TE1302' ,align: 'center', minWidth: 85},"
				+ "  { display: '相变炉供水温度(℃)', name: 'TE1501' ,align: 'center', minWidth: 85},"
				+ "  { display: '回水温度(℃)', name: 'TE1502' ,align: 'center', minWidth: 85},"
				+ "  { display: '1#相变炉出油温度(℃)', name: 'TES1301' ,align: 'center', minWidth: 85},"
				+ "  { display: '1#相变炉炉水温度(℃)', name: 'TES1302' ,align: 'center', minWidth: 85},"
				+ "  { display: '1#相变炉出水温度(℃)', name: 'TES1303' ,align: 'center', minWidth: 85},"
				+ "  { display: '1#相变炉进油温度(℃)', name: 'TES1304' ,align: 'center', minWidth: 85},"
				+ "  { display: '2#相变炉出油温度(℃)', name: 'TES1401' ,align: 'center', minWidth: 85},"
				+ "  { display: '2#相变炉炉水温度(℃)', name: 'TES1402' ,align: 'center', minWidth: 85},"
				+ "  { display: '2#相变炉出水温度(℃)', name: 'TES1403' ,align: 'center', minWidth: 85},"
				+ "  { display: '2#相变炉进油温度(℃)', name: 'TES1404' ,align: 'center', minWidth: 85},"
				+ "  { display: '提升泵累积流量(m3)', name: 'FIT11103_Q' ,align: 'center', minWidth: 85},"
				+ "  { display: '湿气流量累积流量(m3)', name: 'FIT1101_Q' ,align: 'center', minWidth: 85},"
				+ "  { display: '干气流量累积流量(m3)', name: 'FIT1301_Q' ,align: 'center', minWidth: 85}"
//				+ "  { display: '稀油联合站油自动化动态数据ID', name: 'RD_U_THIN_OIL_AUTO_ID' ,align: 'center', minWidth: 60,hide:true}"
				+ " ],"+
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'xyyqdt_searchDatas.action',"+	
				"delayLoad :true, "+
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"selectRowButtonOnly:true,"+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"rownumbers:true ,"+
				"frozen:true, "+
				"headerRowHeight:50, "+
				"checkbox :false";
//稀油注输联合站（水区）
		
		public static final String XYSQDT_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 130,frozen:true},"	
			+ "  { display: '加药间1#反应提升泵频率(Hz)', name: 'F_1_FYTSB' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间1#过滤提升泵频率(Hz)', name: 'F_1_GLTSB' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间3#反应提升泵频率(Hz)', name: 'F_3_FYTSB' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间1#药剂流量(m3/h)', name: 'FI_1_FYJ' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间2#反应器进水流量(m3/h)', name: 'FI_2_FYQ' ,align: 'center', minWidth: 95},"	
			+ "  { display: '加药间2#药剂进1#反应器流量(m3/h)', name: 'FI_2_YJO1' ,align: 'center', minWidth: 105},"	
			+ "  { display: '加药间2#药剂进2#反应器流量(m3/h)', name: 'FI_2_YJO2' ,align: 'center', minWidth: 105},"	
			+ "  { display: '加药间2#药剂进3#反应器流量(m3/h)', name: 'FI_2_YJO3' ,align: 'center', minWidth: 105},"	
			+ "  { display: '加药间3#药剂进1#反应器流量(m3/h)', name: 'FI_3_FYJO1' ,align: 'center', minWidth: 105},"	
			+ "  { display: '加药间3#药剂进2#反应器流量(m3/h)', name: 'FI_3_FYJO2' ,align: 'center', minWidth: 105},"	
			+ "  { display: '加药间3#药剂进3#反应器流量(m3/h)', name: 'FI_3_FYJO3' ,align: 'center', minWidth: 105},"	
			+ "  { display: '加药间3#反应器进水流量(m3/h)', name: 'FI_3_FYQ' ,align: 'center', minWidth: 90},"	
			+ "  { display: '加药间净化水外输流量	(m3/h)', name: 'FI_JHSO1' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间油田注水流量(m3/h)', name: 'FI_YTZS' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间原油处理站来水流量	(m3/h)', name: 'FI_YYCLZLS' ,align: 'center', minWidth: 105},"	
			+ "  { display: '东线总出口流量(m3/h)', name: 'FT_DX' ,align: 'center', minWidth: 85},"	
			+ "  { display: '东线注水总出口流量累计(m3)', name: 'FT_DX_ADD' ,align: 'center', minWidth: 85},"	
			+ "  { display: '喂水泵出口流量(m3/h)', name: 'FT_WSB' ,align: 'center', minWidth: 85},"	
			+ "  { display: '西线总出口流量(m3/h)', name: 'FT_XX' ,align: 'center', minWidth: 85},"	
			+ "  { display: '西线注水总出口流量累计(m3)', name: 'FT_XX_ADD' ,align: 'center', minWidth: 85},"	
			+ "  { display: '注水撬流量1(m3/h)', name: 'FT_ZSQ1' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间1#调储罐液位(m)', name: 'L1_1_TCG' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间1#缓冲罐液位(m)', name: 'LI_1_HCG' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间1#净化罐液位(m)', name: 'LI_1_JHG' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间1#污水池液位(m)', name: 'LI_1_WSC' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间2#缓冲罐液位(m)', name: 'LI_2_HCG' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间2#净化罐液位(m)', name: 'LI_2_JHG' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间2#调储罐液位(m)', name: 'LI_2_TCG' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间2#污水池液位(m)', name: 'LI_2_WSC' ,align: 'center', minWidth: 85},"	
			+ "  { display: '加药间污油罐液位(m)', name: 'LI_WYG' ,align: 'center', minWidth: 85},"	
			+ "  { display: '消防罐液位(m)', name: 'LT_XFYW' ,align: 'center', minWidth: 85},"	
			+ "  { display: '进口压力监测1#泵(Mpa)', name: 'PT_101A' ,align: 'center', minWidth: 85},"	
			+ "  { display: '出口压力监测1#泵(Mpa)', name: 'PT_101B' ,align: 'center', minWidth: 85},"	
			+ "  { display: '进口压力监测2#泵(Mpa)', name: 'PT_102A' ,align: 'center', minWidth: 85},"	
			+ "  { display: '出口压力监测2#泵(Mpa)', name: 'PT_102B' ,align: 'center', minWidth: 85},"	
			+ "  { display: '进口压力监测3#泵(Mpa)', name: 'PT_103A' ,align: 'center', minWidth: 85},"	
			+ "  { display: '出口压力监测3#泵(Mpa)', name: 'PT_103B' ,align: 'center', minWidth: 85},"	
			+ "  { display: '进口压力监测4#泵(Mpa)', name: 'PT_104A' ,align: 'center', minWidth: 85},"	
			+ "  { display: '出口压力监测4#泵(Mpa)', name: 'PT_104B' ,align: 'center', minWidth: 85},"	
			+ "  { display: '东线注水泵出口压力(Mpa)', name: 'PT_DX_ZSB' ,align: 'center', minWidth: 85},"	
			+ "  { display: '西线注水泵出口压力(Mpa)', name: 'PT_XX_ZSB' ,align: 'center', minWidth: 85},"	
			+ "  { display: '注水泵总出口压力(Mpa)', name: 'PT_ZSB' ,align: 'center', minWidth: 85},"	
			+ "  { display: '注水撬压力(Mpa)', name: 'PT_ZSQ' ,align: 'center', minWidth: 85},"	
			+ "  { display: '油温监测1#泵(℃)', name: 'TT_101' ,align: 'center', minWidth: 85},"	
			+ "  { display: '油温监测2#泵(℃)', name: 'TT_102' ,align: 'center', minWidth: 85},"	
			+ "  { display: '油温监测3#泵(℃)', name: 'TT_103' ,align: 'center', minWidth: 85},"	
			+ "  { display: '油温监测4#泵(℃)', name: 'TT_104' ,align: 'center', minWidth: 85},"	
			+ "  { display: '东线注水泵出口温度检测(℃)', name: 'TT_DX_ZSB' ,align: 'center', minWidth: 85},"	
			+ "  { display: '西线注水泵出口温度检测(℃)', name: 'TT_XX_ZSB' ,align: 'center', minWidth: 85},"	
			+ "  { display: '注水泵油温高设定(℃)', name: 'TT_ZSBF_HSV' ,align: 'center', minWidth: 85},"	
			+ "  { display: '污油罐低液位设定(m)', name: 'WYG_LT_LSV' ,align: 'center', minWidth: 85},"	
			+ "  { display: '药剂1变频手动设定频率(Hz)', name: 'YJB1_SC_SV' ,align: 'center', minWidth: 85},"	
			+ "  { display: '药剂2变频1手动设定频率(Hz)', name: 'YJB2_SC1_SV' ,align: 'center', minWidth: 85},"	
			+ "  { display: '药剂2变频2手动设定频率(Hz)', name: 'YJB2_SC2_SV' ,align: 'center', minWidth: 85},"	
			+ "  { display: '药剂2变频3手动设定频率(Hz)', name: 'YJB2_SC3_SV' ,align: 'center', minWidth: 85},"	
			+ "  { display: '药剂3变频1手动设定频率(Hz)', name: 'YJB3_SC1_SV' ,align: 'center', minWidth: 85},"	
			+ "  { display: '药剂3变频2手动设定频率(Hz)', name: 'YJB3_SC2_SV' ,align: 'center', minWidth: 85},"	
			+ "  { display: '进水流量L/h1#反应器瞬时流量(m3/h)', name: 'FI_1_FYQ' ,align: 'center', minWidth: 105}"	
				+ " ],"+
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'xysqdt_searchDatas.action',"+
				"delayLoad :true, "+
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"selectRowButtonOnly:true,"+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"rownumbers:true ,"+
				"frozen:true, "+
				"headerRowHeight:50, "+
				"checkbox :false";
		
		
		//1号稠油联合处理站（油区动态数据)
		//	字段名  	字段类型		单位	注释
		public static final String PC_RD_U1_OIL_AUTO_T_PAGE_GRID = " columns: ["
			+ "		{ display: '采集时间', name: 'CJSJ', align: 'center' ,minWidth: 140,frozen:true},"	
			+ "		{ display: '罐2/1原油温度A℃', name: 'TR_220A	', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/1原油温度B℃', name: 'TR_220B', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/1原油温度C℃', name: 'TR_220C', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/1原油温度D℃', name: 'TR_220D', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/2原油温度A℃', name: 'TR_221A', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/2原油温度B℃', name: 'TR_221B', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/2原油温度C℃', name: 'TR_221C', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/2原油温度D℃', name: 'TR_221D', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/3原油温度A℃', name: 'TR_222A', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/3原油温度B℃', name: 'TR_222B', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/3原油温度C℃', name: 'TR_222C', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/3原油温度D℃', name: 'TR_222D', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/4原油温度A℃', name: 'TR_223A', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/4原油温度B℃', name: 'TR_223B', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/4原油温度C℃', name: 'TR_223C', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/4原油温度D℃', name: 'TR_223D', align: 'center', minWidth: 60},"
			+ "		{ display: '一段掺热进油温度℃', name: 'TE210A', align: 'center', minWidth: 60},"
			+ "		{ display: '3#缓冲罐温度1℃', name: 'TE202A', align: 'center', minWidth: 60},"
			+ "		{ display: '3#缓冲罐温度2℃', name: 'TE202B', align: 'center', minWidth: 60},"
			+ "		{ display: '3#缓冲罐温度3℃', name: 'TE202C', align: 'center', minWidth: 60},"
			+ "		{ display: '3#缓冲罐温度4℃', name: 'TE202D', align: 'center', minWidth: 60},"
			+ "		{ display: '4#缓冲罐温度1℃', name: 'TE203A', align: 'center', minWidth: 60},"
			+ "		{ display: '4#缓冲罐温度2℃', name: 'TE203B', align: 'center', minWidth: 60},"
			+ "		{ display: '4#缓冲罐温度3℃', name: 'TE203C', align: 'center', minWidth: 60},"
			+ "		{ display: '4#缓冲罐温度4℃', name: 'TE203D', align: 'center', minWidth: 60},"
			+ "		{ display: '5#净化油罐温度1℃', name: 'TE204A', align: 'center', minWidth: 60},"
			+ "		{ display: '5#净化油罐温度2℃', name: 'TE204B', align: 'center', minWidth: 60},"
			+ "		{ display: '5#净化油罐温度3℃', name: 'TE204C', align: 'center', minWidth: 60},"
			+ "		{ display: '5#净化油罐温度4℃', name: 'TE204D', align: 'center', minWidth: 60},"
			+ "		{ display: '6#净化油罐温度1℃', name: 'TE205A', align: 'center', minWidth: 60},"
			+ "		{ display: '6#净化油罐温度2℃', name: 'TE205B', align: 'center', minWidth: 60},"
			+ "		{ display: '6#净化油罐温度3℃', name: 'TE205C', align: 'center', minWidth: 60},"
			+ "		{ display: '6#净化油罐温度4℃', name: 'TE205D', align: 'center', minWidth: 60},"
			+ "		{ display: '7#净化油罐温度1℃', name: 'TE206A', align: 'center', minWidth: 60},"
			+ "		{ display: '7#净化油罐温度2℃', name: 'TE206B', align: 'center', minWidth: 60},"
			+ "		{ display: '7#净化油罐温度3℃', name: 'TE206C', align: 'center', minWidth: 60},"
			+ "		{ display: '7#净化油罐温度4℃', name: 'TE206D', align: 'center', minWidth: 60},"
			+ "		{ display: '8#净化油罐温度1℃', name: 'TE207A', align: 'center', minWidth: 60},"
			+ "		{ display: '8#净化油罐温度2℃', name: 'TE207B', align: 'center', minWidth: 60},"
			+ "		{ display: '8#净化油罐温度3℃', name: 'TE207C', align: 'center', minWidth: 60},"
			+ "		{ display: '8#净化油罐温度4℃', name: 'TE207D', align: 'center', minWidth: 60},"
			+ "		{ display: '9#净化油罐温度1℃', name: 'TE208A', align: 'center', minWidth: 60},"
			+ "		{ display: '9#净化油罐温度2℃', name: 'TE208B', align: 'center', minWidth: 60},"
			+ "		{ display: '9#净化油罐温度3℃', name: 'TE208C', align: 'center', minWidth: 60},"
			+ "		{ display: '9#净化油罐温度4℃', name: 'TE208D', align: 'center', minWidth: 60},"
			+ "		{ display: '10#净化油罐温度1℃', name: 'TE209A', align: 'center', minWidth: 60},"
			+ "		{ display: '10#净化油罐温度2℃', name: 'TE209B', align: 'center', minWidth: 60},"
			+ "		{ display: '10#净化油罐温度3℃', name: 'TE209C', align: 'center', minWidth: 60},"
			+ "		{ display: '10#净化油罐温度4℃', name: 'TE209D', align: 'center', minWidth: 60},"
			+ "		{ display: '1#出口油温℃', name: 'TT01_001', align: 'center', minWidth: 60},"
			+ "		{ display: '1#进口油温℃', name: 'TT01_002', align: 'center', minWidth: 60},"
			+ "		{ display: '1#烟气温度℃', name: 'TT01_003', align: 'center', minWidth: 60},"
			+ "		{ display: '1#出口油温℃', name: 'TT01_004', align: 'center', minWidth: 60},"
			+ "		{ display: '2#出口油温℃', name: 'TT02_001', align: 'center', minWidth: 60},"
			+ "		{ display: '2#进口油温℃', name: 'TT02_002', align: 'center', minWidth: 60},"
			+ "		{ display: '2#烟气温度℃', name: 'TT02_003', align: 'center', minWidth: 60},"
			+ "		{ display: '3#出口油温℃', name: 'TT03_001', align: 'center', minWidth: 60},"
			+ "		{ display: '3#进口油温℃', name: 'TT03_002', align: 'center', minWidth: 60},"
			+ "		{ display: '3#烟气温度℃', name: 'TT03_003', align: 'center', minWidth: 60},"
			+ "		{ display: '沉降罐出口原油温度℃', name: 'TR_226', align: 'center', minWidth: 60},"
			+ "		{ display: '掺热装置出口管道温度℃', name: 'TR_225', align: 'center', minWidth: 60},"
			+ "		{ display: '来油管汇温度℃', name: 'TE201', align: 'center', minWidth: 60},"
			+ "		{ display: '总出水温度℃', name: 'REG_014', align: 'center', minWidth: 60},"
			+ "		{ display: '总进水温度℃', name: 'REG_013', align: 'center', minWidth: 60},"
			+ "		{ display: '来油管汇压力Mpa', name: 'PT201', align: 'center', minWidth: 60},"
			+ "		{ display: '油二区掺蒸汽压力Mpa', name: 'PT0001', align: 'center', minWidth: 60},"
			+ "		{ display: '1#热媒泵出口压力Mpa', name: 'PT_001', align: 'center', minWidth: 60},"
			+ "		{ display: '2#热媒泵出口压力Mpa', name: 'PT_002', align: 'center', minWidth: 60},"
			+ "		{ display: '3#热媒泵出口压力Mpa', name: 'PT_003', align: 'center', minWidth: 60},"
			+ "		{ display: '回脱原油出油泵管含水%', name: 'MT202', align: 'center', minWidth: 60},"
			+ "		{ display: '原油进站计量管含水%', name: 'MT201', align: 'center', minWidth: 60},"
			+ "		{ display: '一段沉降罐原油含水%', name: 'MR_210', align: 'center', minWidth: 60},"
			+ "		{ display: '1#沉降罐油层厚度m', name: 'LIT408', align: 'center', minWidth: 60},"
			+ "		{ display: '2#沉降罐油层厚度m', name: 'LIT407', align: 'center', minWidth: 60},"
			+ "		{ display: '3#缓冲罐液位m', name: 'LT201', align: 'center', minWidth: 60},"
			+ "		{ display: '4#缓冲罐液位m', name: 'LT202', align: 'center', minWidth: 60},"
			+ "		{ display: '5#净化油罐液位m', name: 'LT203', align: 'center', minWidth: 60},"
			+ "		{ display: '6#净化油罐液位m', name: 'LT204', align: 'center', minWidth: 60},"
			+ "		{ display: '7#净化油罐液位m', name: 'LT205', align: 'center', minWidth: 60},"
			+ "		{ display: '8#净化油罐液位m', name: 'LT206', align: 'center', minWidth: 60},"
			+ "		{ display: '9#净化油罐液位m', name: 'LT207', align: 'center', minWidth: 60},"
			+ "		{ display: '10#净化油罐液位m', name: 'LT208', align: 'center', minWidth: 60},"
			+ "		{ display: '14#油罐液位m', name: 'LRR_217', align: 'center', minWidth: 60},"
			+ "		{ display: '13#油罐液位m', name: 'LRR_218', align: 'center', minWidth: 60},"
			+ "		{ display: '16#油罐液位m', name: 'LRR_219', align: 'center', minWidth: 60},"
			+ "		{ display: '15#油罐液位m', name: 'LRR_220', align: 'center', minWidth: 60},"
			+ "		{ display: '膨胀罐液位m', name: 'LIT_PZG', align: 'center', minWidth: 60},"
			+ "		{ display: '11#沉降罐油厚m', name: 'LIT_222', align: 'center', minWidth: 60},"
			+ "		{ display: '12#沉降罐油厚m', name: 'LIT_221', align: 'center', minWidth: 60},"
			+ "		{ display: '除砂间污油池液位02m', name: 'LIT_002', align: 'center', minWidth: 60},"
			+ "		{ display: '博达污水来液流量累计m3', name: 'FITQ001', align: 'center', minWidth: 60},"
			+ "		{ display: '热煤炉燃料气流量m3/h', name: 'FIT202', align: 'center', minWidth: 60},"
			+ "		{ display: '原油进站计量管线流量m3/h', name: 'FIT201', align: 'center', minWidth: 60},"
			+ "		{ display: '博达污水来液流量m3/h', name: 'FIT_001', align: 'center', minWidth: 60},"
			+ "		{ display: '热煤炉燃料气流量累计m3', name: 'FIQ202', align: 'center', minWidth: 60},"
			+ "		{ display: '原油进站流量累计m3', name: 'FIQ201', align: 'center', minWidth: 60},"
			+ "		{ display: '1号净化油罐含水%', name: 'AR_101', align: 'center', minWidth: 60},"
			+ "		{ display: '2号净化油罐含水%', name: 'AR_102', align: 'center', minWidth: 60},"
			+ "		{ display: '3号净化油罐含水%', name: 'AR_103', align: 'center', minWidth: 60},"
			+ "		{ display: '4号净化油罐含水%', name: 'AR_104', align: 'center', minWidth: 60},"
			+ "		{ display: '柴油来油管线压力Mpa', name: 'PT210SC', align: 'center', minWidth: 60},"
			+ "		{ display: '柴油来油管线温度℃', name: 'TE219RC', align: 'center', minWidth: 60},"
			+ "		{ display: '柴油来油管线流量m3/h', name: 'FIT210SC', align: 'center', minWidth: 60},"
			+ "		{ display: '1#管线含水Mpa', name: 'AIT001', align: 'center', minWidth: 60},"
			+ "		{ display: '2#管线含水Mpa', name: 'AIT002', align: 'center', minWidth: 60},"
			+ "		{ display: '3#管线含水Mpa', name: 'AIT003', align: 'center', minWidth: 60},"
			+ "		{ display: '4#管线含水Mpa', name: 'AIT004', align: 'center', minWidth: 60},"
			+ "		{ display: '5#管线含水Mpa', name: 'AIT005', align: 'center', minWidth: 60},"
			+ "		{ display: '6#管线含水Mpa', name: 'AIT006', align: 'center', minWidth: 60},"
			+ "		{ display: '7#管线含水Mpa', name: 'AIT007', align: 'center', minWidth: 60},"
			+ "		{ display: '8#管线含水Mpa', name: 'AIT008', align: 'center', minWidth: 60},"
			+ "		{ display: '1#管线原油流量m3/h', name: 'FT001', align: 'center', minWidth: 60},"
			+ "		{ display: '2#管线原油流量m3/h', name: 'FT002', align: 'center', minWidth: 60},"
			+ "		{ display: '3#管线原油流量m3/h', name: 'FT003', align: 'center', minWidth: 60},"
			+ "		{ display: '5#管线原油流量m3/h', name: 'FT005', align: 'center', minWidth: 60},"
			+ "		{ display: '6#管线原油流量m3/h', name: 'FT006', align: 'center', minWidth: 60},"
			+ "		{ display: '7#管线原油流量m3/h', name: 'FT007', align: 'center', minWidth: 60},"
			+ "		{ display: '8#管线原油流量m3/h', name: 'FT008', align: 'center', minWidth: 60},"
			+ "		{ display: '1#管线压力Mpa', name: 'PT001', align: 'center', minWidth: 60},"
			+ "		{ display: '2#管线压力Mpa', name: 'PT002', align: 'center', minWidth: 60},"
			+ "		{ display: '3#管线压力Mpa', name: 'PT003', align: 'center', minWidth: 60},"
			+ "		{ display: '4#管线压力Mpa', name: 'PT004', align: 'center', minWidth: 60},"
			+ "		{ display: '5#管线压力Mpa', name: 'PT005', align: 'center', minWidth: 60},"
			+ "		{ display: '6#管线压力Mpa', name: 'PT006', align: 'center', minWidth: 60},"
			+ "		{ display: '7#管线压力Mpa', name: 'PT007', align: 'center', minWidth: 60},"
			+ "		{ display: '8#管线压力Mpa', name: 'PT008', align: 'center', minWidth: 60},"
			+ "		{ display: '1#管线温度℃', name: 'TE001', align: 'center', minWidth: 60},"
			+ "		{ display: '2#管线温度℃', name: 'TE002', align: 'center', minWidth: 60},"
			+ "		{ display: '3#管线温度℃', name: 'TE003', align: 'center', minWidth: 60},"
			+ "		{ display: '4#管线温度℃', name: 'TE004', align: 'center', minWidth: 60},"
			+ "		{ display: '5#管线温度℃', name: 'TE005', align: 'center', minWidth: 60},"
			+ "		{ display: '6#管线温度℃', name: 'TE006', align: 'center', minWidth: 60},"
			+ "		{ display: '7#管线温度℃', name: 'TE007', align: 'center', minWidth: 60},"
			+ "		{ display: '8#管线温度℃', name: 'TE008', align: 'center', minWidth: 60},"
			+ "		{ display: '去2号站油管压力Mpa', name: 'PT2HZ', align: 'center', minWidth: 60},"
			+ "		{ display: '去2号联合站油管含水Mpa', name: 'mT2HZ', align: 'center', minWidth: 60},"
			+ "		{ display: '去2号联合站油管温度℃', name: 'TE2HZ', align: 'center', minWidth: 60},"
			+ "		{ display: '去2号联合站油管流量m3/h', name: 'FT2HZ', align: 'center', minWidth: 60},"
			+ "		{ display: '去2号联合站油管累计流量m3', name: 'FIQ2HZ', align: 'center', minWidth: 60},"
			+ "		{ display: '罐2/1原油温度A℃', name: 'TR_220A	', align: 'center', width: 100},"
			+ "		{ display: '罐2/1原油温度B℃', name: 'TR_220B', align: 'center', width: 100},"
			+ "		{ display: '罐2/1原油温度C℃', name: 'TR_220C', align: 'center', width: 100},"
			+ "		{ display: '罐2/1原油温度D℃', name: 'TR_220D', align: 'center', width: 100},"
			+ "		{ display: '罐2/2原油温度A℃', name: 'TR_221A', align: 'center', width: 100},"
			+ "		{ display: '罐2/2原油温度B℃', name: 'TR_221B', align: 'center', width: 100},"
			+ "		{ display: '罐2/2原油温度C℃', name: 'TR_221C', align: 'center', width: 100},"
			+ "		{ display: '罐2/2原油温度D℃', name: 'TR_221D', align: 'center', width: 100},"
			+ "		{ display: '罐2/3原油温度A℃', name: 'TR_222A', align: 'center', width: 100},"
			+ "		{ display: '罐2/3原油温度B℃', name: 'TR_222B', align: 'center', width: 100},"
			+ "		{ display: '罐2/3原油温度C℃', name: 'TR_222C', align: 'center', width: 100},"
			+ "		{ display: '罐2/3原油温度D℃', name: 'TR_222D', align: 'center', width: 100},"
			+ "		{ display: '罐2/4原油温度A℃', name: 'TR_223A', align: 'center', width: 100},"
			+ "		{ display: '罐2/4原油温度B℃', name: 'TR_223B', align: 'center', width: 100},"
			+ "		{ display: '罐2/4原油温度C℃', name: 'TR_223C', align: 'center', width: 100},"
			+ "		{ display: '罐2/4原油温度D℃', name: 'TR_223D', align: 'center', width: 100},"
			+ "		{ display: '一段掺热进油温度℃', name: 'TE210A', align: 'center', width: 100},"
			+ "		{ display: '3#缓冲罐温度1℃', name: 'TE202A', align: 'center', width: 100},"
			+ "		{ display: '3#缓冲罐温度2℃', name: 'TE202B', align: 'center', width: 100},"
			+ "		{ display: '3#缓冲罐温度3℃', name: 'TE202C', align: 'center', width: 100},"
			+ "		{ display: '3#缓冲罐温度4℃', name: 'TE202D', align: 'center', width: 100},"
			+ "		{ display: '4#缓冲罐温度1℃', name: 'TE203A', align: 'center', width: 100},"
			+ "		{ display: '4#缓冲罐温度2℃', name: 'TE203B', align: 'center', width: 100},"
			+ "		{ display: '4#缓冲罐温度3℃', name: 'TE203C', align: 'center', width: 100},"
			+ "		{ display: '4#缓冲罐温度4℃', name: 'TE203D', align: 'center', width: 100},"
			+ "		{ display: '5#净化油罐温度1℃', name: 'TE204A', align: 'center', width: 100},"
			+ "		{ display: '5#净化油罐温度2℃', name: 'TE204B', align: 'center', width: 100},"
			+ "		{ display: '5#净化油罐温度3℃', name: 'TE204C', align: 'center', width: 100},"
			+ "		{ display: '5#净化油罐温度4℃', name: 'TE204D', align: 'center', width: 100},"
			+ "		{ display: '6#净化油罐温度1℃', name: 'TE205A', align: 'center', width: 100},"
			+ "		{ display: '6#净化油罐温度2℃', name: 'TE205B', align: 'center', width: 100},"
			+ "		{ display: '6#净化油罐温度3℃', name: 'TE205C', align: 'center', width: 100},"
			+ "		{ display: '6#净化油罐温度4℃', name: 'TE205D', align: 'center', width: 100},"
			+ "		{ display: '7#净化油罐温度1℃', name: 'TE206A', align: 'center', width: 100},"
			+ "		{ display: '7#净化油罐温度2℃', name: 'TE206B', align: 'center', width: 100},"
			+ "		{ display: '7#净化油罐温度3℃', name: 'TE206C', align: 'center', width: 100},"
			+ "		{ display: '7#净化油罐温度4℃', name: 'TE206D', align: 'center', width: 100},"
			+ "		{ display: '8#净化油罐温度1℃', name: 'TE207A', align: 'center', width: 100},"
			+ "		{ display: '8#净化油罐温度2℃', name: 'TE207B', align: 'center', width: 100},"
			+ "		{ display: '8#净化油罐温度3℃', name: 'TE207C', align: 'center', width: 100},"
			+ "		{ display: '8#净化油罐温度4℃', name: 'TE207D', align: 'center', width: 100},"
			+ "		{ display: '9#净化油罐温度1℃', name: 'TE208A', align: 'center', width: 100},"
			+ "		{ display: '9#净化油罐温度2℃', name: 'TE208B', align: 'center', width: 100},"
			+ "		{ display: '9#净化油罐温度3℃', name: 'TE208C', align: 'center', width: 100},"
			+ "		{ display: '9#净化油罐温度4℃', name: 'TE208D', align: 'center', width: 100},"
			+ "		{ display: '10#净化油罐温度1℃', name: 'TE209A', align: 'center', width: 100},"
			+ "		{ display: '10#净化油罐温度2℃', name: 'TE209B', align: 'center', width: 100},"
			+ "		{ display: '10#净化油罐温度3℃', name: 'TE209C', align: 'center', width: 100},"
			+ "		{ display: '10#净化油罐温度4℃', name: 'TE209D', align: 'center', width: 100},"
			+ "		{ display: '1#出口油温℃', name: 'TT01_001', align: 'center', width: 100},"
			+ "		{ display: '1#进口油温℃', name: 'TT01_002', align: 'center', width: 100},"
			+ "		{ display: '1#烟气温度℃', name: 'TT01_003', align: 'center', width: 100},"
			+ "		{ display: '1#出口油温℃', name: 'TT01_004', align: 'center', width: 100},"
			+ "		{ display: '2#出口油温℃', name: 'TT02_001', align: 'center', width: 100},"
			+ "		{ display: '2#进口油温℃', name: 'TT02_002', align: 'center', width: 100},"
			+ "		{ display: '2#烟气温度℃', name: 'TT02_003', align: 'center', width: 100},"
			+ "		{ display: '3#出口油温℃', name: 'TT03_001', align: 'center', width: 100},"
			+ "		{ display: '3#进口油温℃', name: 'TT03_002', align: 'center', width: 100},"
			+ "		{ display: '3#烟气温度℃', name: 'TT03_003', align: 'center', width: 100},"
			+ "		{ display: '沉降罐出口原油温度℃', name: 'TR_226', align: 'center', width: 100},"
			+ "		{ display: '掺热装置出口管道温度℃', name: 'TR_225', align: 'center', width: 100},"
			+ "		{ display: '来油管汇温度℃', name: 'TE201', align: 'center', width: 100},"
			+ "		{ display: '总出水温度℃', name: 'REG_014', align: 'center', width: 100},"
			+ "		{ display: '总进水温度℃', name: 'REG_013', align: 'center', width: 100},"
			+ "		{ display: '来油管汇压力Mpa', name: 'PT201', align: 'center', width: 100},"
			+ "		{ display: '油二区掺蒸汽压力Mpa', name: 'PT0001', align: 'center', width: 100},"
			+ "		{ display: '1#热媒泵出口压力Mpa', name: 'PT_001', align: 'center', width: 100},"
			+ "		{ display: '2#热媒泵出口压力Mpa', name: 'PT_002', align: 'center', width: 100},"
			+ "		{ display: '3#热媒泵出口压力Mpa', name: 'PT_003', align: 'center', width: 100},"
			+ "		{ display: '回脱原油出油泵管含水%', name: 'MT202', align: 'center', width: 100},"
			+ "		{ display: '原油进站计量管含水%', name: 'MT201', align: 'center', width: 100},"
			+ "		{ display: '一段沉降罐原油含水%', name: 'MR_210', align: 'center', width: 100},"
			+ "		{ display: '1#沉降罐油层厚度m', name: 'LIT408', align: 'center', width: 100},"
			+ "		{ display: '2#沉降罐油层厚度m	', name: 'LIT407', align: 'center', width: 100},"
			+ "		{ display: '3#缓冲罐液位m', name: 'LT201', align: 'center', width: 100},"
			+ "		{ display: '4#缓冲罐液位m', name: 'LT202', align: 'center', width: 100},"
			+ "		{ display: '5#净化油罐液位m', name: 'LT203', align: 'center', width: 100},"
			+ "		{ display: '6#净化油罐液位m', name: 'LT204', align: 'center', width: 100},"
			+ "		{ display: '7#净化油罐液位m', name: 'LT205', align: 'center', width: 100},"
			+ "		{ display: '8#净化油罐液位m', name: 'LT206', align: 'center', width: 100},"
			+ "		{ display: '9#净化油罐液位m', name: 'LT207', align: 'center', width: 100},"
			+ "		{ display: '10#净化油罐液位m', name: 'LT208', align: 'center', width: 100},"
			+ "		{ display: '14#油罐液位m', name: 'LRR_217', align: 'center', width: 100},"
			+ "		{ display: '13#油罐液位m', name: 'LRR_218', align: 'center', width: 100},"
			+ "		{ display: '16#油罐液位m', name: 'LRR_219', align: 'center', width: 100},"
			+ "		{ display: '15#油罐液位m', name: 'LRR_220', align: 'center', width: 100},"
			+ "		{ display: '膨胀罐液位m', name: 'LIT_PZG', align: 'center', width: 100},"
			+ "		{ display: '11#沉降罐油厚m', name: 'LIT_222', align: 'center', width: 100},"
			+ "		{ display: '12#沉降罐油厚m', name: 'LIT_221', align: 'center', width: 100},"
			+ "		{ display: '除砂间污油池液位02m', name: 'LIT_002', align: 'center', width: 100},"
			+ "		{ display: '博达污水来液流量累计m3', name: 'FITQ001', align: 'center', width: 100},"
			+ "		{ display: '热煤炉燃料气流量m3/h', name: 'FIT202', align: 'center', width: 100},"
			+ "		{ display: '原油进站计量管线流量m3/h', name: 'FIT201', align: 'center', width: 100},"
			+ "		{ display: '博达污水来液流量m3/h', name: 'FIT_001', align: 'center', width: 100},"
			+ "		{ display: '热煤炉燃料气流量累计m3', name: 'FIQ202', align: 'center', width: 100},"
			+ "		{ display: '原油进站流量累计m3', name: 'FIQ201', align: 'center', width: 100},"
			+ "		{ display: '1号净化油罐含水%', name: 'AR_101', align: 'center', width: 100},"
			+ "		{ display: '2号净化油罐含水%', name: 'AR_102', align: 'center', width: 100},"
			+ "		{ display: '3号净化油罐含水%', name: 'AR_103', align: 'center', width: 100},"
			+ "		{ display: '4号净化油罐含水%', name: 'AR_104', align: 'center', width: 100},"
			+ "		{ display: '柴油来油管线压力Mpa', name: 'PT210SC', align: 'center', width: 100},"
			+ "		{ display: '柴油来油管线温度℃', name: 'TE219RC', align: 'center', width: 100},"
			+ "		{ display: '柴油来油管线流量m3/h', name: 'FIT210SC', align: 'center', width: 100},"
			+ "		{ display: '1#管线含水Mpa', name: 'AIT001', align: 'center', width: 100},"
			+ "		{ display: '2#管线含水Mpa', name: 'AIT002', align: 'center', width: 100},"
			+ "		{ display: '3#管线含水Mpa', name: 'AIT003', align: 'center', width: 100},"
			+ "		{ display: '4#管线含水Mpa', name: 'AIT004', align: 'center', width: 100},"
			+ "		{ display: '5#管线含水Mpa', name: 'AIT005', align: 'center', width: 100},"
			+ "		{ display: '6#管线含水Mpa', name: 'AIT006', align: 'center', width: 100},"
			+ "		{ display: '7#管线含水Mpa', name: 'AIT007', align: 'center', width: 100},"
			+ "		{ display: '8#管线含水Mpa', name: 'AIT008', align: 'center', width: 100},"
			+ "		{ display: '1#管线原油流量m3/h', name: 'FT001', align: 'center', width: 100},"
			+ "		{ display: '2#管线原油流量m3/h', name: 'FT002', align: 'center', width: 100},"
			+ "		{ display: '3#管线原油流量m3/h', name: 'FT003', align: 'center', width: 100},"
			+ "		{ display: '5#管线原油流量m3/h', name: 'FT005', align: 'center', width: 100},"
			+ "		{ display: '6#管线原油流量m3/h', name: 'FT006', align: 'center', width: 100},"
			+ "		{ display: '7#管线原油流量m3/h', name: 'FT007', align: 'center', width: 100},"
			+ "		{ display: '8#管线原油流量m3/h', name: 'FT008', align: 'center', width: 100},"
			+ "		{ display: '1#管线压力Mpa', name: 'PT001', align: 'center', width: 100},"
			+ "		{ display: '2#管线压力Mpa', name: 'PT002', align: 'center', width: 100},"
			+ "		{ display: '3#管线压力Mpa', name: 'PT003', align: 'center', width: 100},"
			+ "		{ display: '4#管线压力Mpa', name: 'PT004', align: 'center', width: 100},"
			+ "		{ display: '5#管线压力Mpa', name: 'PT005', align: 'center', width: 100},"
			+ "		{ display: '6#管线压力Mpa', name: 'PT006', align: 'center', width: 100},"
			+ "		{ display: '7#管线压力Mpa', name: 'PT007', align: 'center', width: 100},"
			+ "		{ display: '8#管线压力Mpa', name: 'PT008', align: 'center', width: 100},"
			+ "		{ display: '1#管线温度℃', name: 'TE001', align: 'center', width: 100},"
			+ "		{ display: '2#管线温度℃', name: 'TE002', align: 'center', width: 100},"
			+ "		{ display: '3#管线温度℃', name: 'TE003', align: 'center', width: 100},"
			+ "		{ display: '4#管线温度℃', name: 'TE004', align: 'center', width: 100},"
			+ "		{ display: '5#管线温度℃', name: 'TE005', align: 'center', width: 100},"
			+ "		{ display: '6#管线温度℃', name: 'TE006', align: 'center', width: 100},"
			+ "		{ display: '7#管线温度℃', name: 'TE007', align: 'center', width: 100},"
			+ "		{ display: '8#管线温度℃', name: 'TE008', align: 'center', width: 100},"
			+ "		{ display: '去2号站油管压力Mpa', name: 'PT2HZ', align: 'center', width: 100},"
			+ "		{ display: '去2号联合站油管含水Mpa', name: 'mT2HZ', align: 'center', width: 100},"
			+ "		{ display: '去2号联合站油管温度℃', name: 'TE2HZ', align: 'center', width: 100},"
			+ "		{ display: '去2号联合站油管流量m3/h', name: 'FT2HZ', align: 'center', width: 100},"
			+ "		{ display: '去2号联合站油管累计流量m3', name: 'FIQ2HZ', align: 'center', width: 100}"
			+ " ],"+
			

			"height:'100%',"+
			"dataAction: 'server',"+
			//"url:'xysqdt_searchDatas.action',"+	
			"url:'u1yqdt_serarchU1yqdt.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"headerRowHeight:50, "+
			"frozen:true, "+
			"checkbox :false";
		//1号稠油联合处理站（分线计量动态数据)
				//	字段名  	字段类型		单位	注释
				public static final String PC_RD_U1_OIL_AUTO_T_GRID = " columns: ["
					+ "		{ display: '采集时间', name: 'CJSJ', align: 'center' ,minWidth: 140,frozen:true},"	
					+ "		{ display: '1#管线原油流量(m3/h)', name: 'FT001	', align: 'center', minWidth: 60},"
					+ "		{ display: '1#管线压力（Mpa）', name: 'PT001', align: 'center', minWidth: 60},"
					+ "		{ display: '1#管线温度(℃)', name: 'TE001', align: 'center', minWidth: 60},"
					+ "		{ display: '1#管线含水(%)', name: 'AIT001', align: 'center', minWidth: 60},"
					+ "		{ display: '1#重18二线流量累计(m3)', name: 'FITQ101', align: 'center', minWidth: 60},"
					+ "		{ display: '2#管线原油流量(m3/h)', name: 'FT002', align: 'center', minWidth: 60},"
					+ "		{ display: '2#管线压力（Mpa）', name: 'PT002', align: 'center', minWidth: 60},"
					+ "		{ display: '2#管线温度(℃)', name: 'TE002', align: 'center', minWidth: 60},"
					+ "		{ display: '2#管线含水(%)', name: 'AIT002', align: 'center', minWidth: 60},"
					+ "		{ display: '2#重32北线流量累计(m3)', name: 'FITQ102', align: 'center', minWidth: 60},"
					+ "		{ display: '3#管线原油流量(m3/h)', name: 'FT003', align: 'center', minWidth: 60},"
					+ "		{ display: '3#管线压力（Mpa）', name: 'PT003', align: 'center', minWidth: 60},"
					+ "		{ display: '3#管线温度(℃)', name: 'TE003', align: 'center', minWidth: 60},"
					+ "		{ display: '3#管线含水(%)', name: 'AIT003', align: 'center', minWidth: 60},"
					+ "		{ display: '3#重18一线流量累计(m3)', name: 'FITQ103', align: 'center', minWidth: 60},"
					+ "		{ display: '4#管线原油流量(m3/h)', name: 'FT004', align: 'center', minWidth: 60},"
					+ "		{ display: '4#管线压力（Mpa）', name: 'PT004', align: 'center', minWidth: 60},"
					+ "		{ display: '4#管线温度(℃)', name: 'TE004', align: 'center', minWidth: 60},"
					+ "		{ display: '4#管线含水(%)', name: 'AIT004', align: 'center', minWidth: 60},"
					+ "		{ display: '4#掺柴油流量累计(m3)', name: 'FITQ104', align: 'center', minWidth: 60},"
					+ "		{ display: '5#管线原油流量(m3/h)', name: 'FT005', align: 'center', minWidth: 60},"
					+ "		{ display: '5#管线压力（Mpa）', name: 'PT005', align: 'center', minWidth: 60},"
					+ "		{ display: '5#管线温度(℃)', name: 'TE005', align: 'center', minWidth: 60},"
					+ "		{ display: '5#管线含水(%)', name: 'AIT005', align: 'center', minWidth: 60},"
					+ "		{ display: '5#13-11线流量累计(m3)', name: 'FITQ105', align: 'center', minWidth: 60},"
					+ "		{ display: '6#管线原油流量(m3/h)', name: 'FT006', align: 'center', minWidth: 60},"
					+ "		{ display: '6#管线压力（Mpa）', name: 'PT006', align: 'center', minWidth: 60},"
					+ "		{ display: '6#管线温度(℃)', name: 'TE006', align: 'center', minWidth: 60},"
					+ "		{ display: '6#管线含水(%)', name: 'AIT006', align: 'center', minWidth: 60},"
					+ "		{ display: '6#换热站线流量累计(m3)', name: 'FITQ106', align: 'center', minWidth: 60},"
					+ "		{ display: '7#管线原油流量(m3/h)', name: 'FT007', align: 'center', minWidth: 60},"
					+ "		{ display: '7#管线压力（Mpa）', name: 'PT007', align: 'center', minWidth: 60},"
					+ "		{ display: '7#管线温度(℃)', name: 'TE007', align: 'center', minWidth: 60},"
					+ "		{ display: '7#管线含水(%)', name: 'AIT007', align: 'center', minWidth: 60},"
					+ "		{ display: '7#重32南线流量累计(m3)', name: 'FITQ107', align: 'center', minWidth: 60},"
					+ "		{ display: '8#管线原油流量(m3/h)', name: 'FT008', align: 'center', minWidth: 60},"
					+ "		{ display: '8#管线压力（Mpa）', name: 'PT008', align: 'center', minWidth: 60},"
					+ "		{ display: '8#管线温度(℃)', name: 'TE008', align: 'center', minWidth: 60},"
					+ "		{ display: '8#管线含水(%)', name: 'AIT008', align: 'center', minWidth: 60},"
					+ "		{ display: '8#卸油台流量累计(m3)', name: 'FITQ108', align: 'center', minWidth: 60},"
					+ "		{ display: '去2号联合站油管流量(m3/h)', name: 'FT2HZ', align: 'center', minWidth: 60},"
					+ "		{ display: '去2号站油管压力（Mpa）', name: 'PT2HZ', align: 'center', minWidth: 60},"
					+ "		{ display: '去2号联合站油管温度(℃)', name: 'TE2HZ', align: 'center', minWidth: 60},"
					+ "		{ display: '去2号联合站油管含水(%)', name: 'MT2HZ', align: 'center', minWidth: 60},"
					+ "		{ display: '去2号联合站油管流量累计(m3)', name: 'FIQ2HZ', align: 'center', minWidth: 60}"
					+ " ],"+
					

					"height:'100%',"+
					"dataAction: 'server',"+
					//"url:'xysqdt_searchDatas.action',"+	
					"url:'u1fxdt_serarchU1fxdt.action',"+	
					"delayLoad :true, "+
					"pageParmName :'pageNo',"+
					"sortnameParmName:'sort',"+
					"sortorderParmName: 'order',  "+
					"pagesizeParmName:'rowsPerpage', "+
					"selectRowButtonOnly:true,"+
					"sePaper:true,"+
					"pageSize:30 ,"+
					"rownumbers:true ,"+
					"headerRowHeight:50, "+
					"frozen:true, "+
					"checkbox :false";
				//密闭转接站动态数据
				//	字段名  	字段类型		单位	注释
				public static final String PC_RD_BSTATION_TURNHOT_T_GRID = " columns: ["
					+ "		{ display: '站号', name: 'BLOCKSTATION', align: 'center' ,minWidth: 60},"	
					+ "		{ display: '采集时间', name: 'ACQUISITION_TIME', align: 'center', minWidth: 140,frozen:true},"
					+ "		{ display: '1#事故罐液位', name: 'SGG_YW1', align: 'center', minWidth: 60},"
					+ "		{ display: '2#事故罐液位', name: 'SGG_YW2', align: 'center', minWidth: 60},"
					+ "		{ display: '1#汽液分离器液位', name: 'STEAM_YW1', align: 'center', minWidth: 60},"
					+ "		{ display: '2#汽液分离器液位', name: 'STEAM_YW2', align: 'center', minWidth: 60},"
					+ "		{ display: '3#汽液分离器液位', name: 'STEAM_YW3', align: 'center', minWidth: 60},"
					+ "		{ display: '冷凝液分离器液位', name: 'HCFLQ_YW', align: 'center', minWidth: 60},"
					+ "		{ display: '气相出口压力', name: 'CLQQXCKYL', align: 'center', minWidth: 60},"
					+ "		{ display: '脱硫罐进口温度', name: 'TLWD', align: 'center', minWidth: 60},"
					+ "		{ display: '1#转油泵频率', name: 'OILPUMP_PL1', align: 'center', minWidth: 60},"
					+ "		{ display: '2#转油泵频率', name: 'OILPUMP_PL2', align: 'center', minWidth: 60},"
					+ "		{ display: '3#转油泵频率', name: 'OILPUMP_PL3', align: 'center', minWidth: 60},"
					+ "		{ display: '1#冷凝水提升泵状态', name: 'HCFLQ_PUMP_ZT1', align: 'center', minWidth: 60},"
					+ "		{ display: '2#冷凝水提升泵状态', name: 'HCFLQ_PUMP_ZT2', align: 'center', minWidth: 60},"
					+ "		{ display: '转油泵进口温度', name: 'PUMP_JYGW', align: 'center', minWidth: 60},"
					+ "		{ display: '泵房集油线进口压力', name: 'PUMP_JYGY', align: 'center', minWidth: 60},"
					+ "		{ display: '泵房集油线出口压力', name: 'PUMP_CYGY', align: 'center', minWidth: 60},"
					+ "		{ display: '液相电动阀状态', name: 'TANK200_KZT', align: 'center', minWidth: 60},"
					+ "		{ display: '气相电动阀状态', name: 'GAS_CKF_KZT', align: 'center', minWidth: 60},"
					+ "		{ display: '脱硫罐电动阀状态', name: 'TLG_WDF_KZT', align: 'center', minWidth: 60},"
					+ "		{ display: '1#空冷机出口温度', name: 'KLJ_CKGW1', align: 'center', minWidth: 60},"
					+ "		{ display: '2#空冷机出口温度', name: 'KLJ_CKGW2', align: 'center', minWidth: 60},"
					+ "		{ display: '3#空冷机出口温度', name: 'KLJ_CKGW3', align: 'center', minWidth: 60},"
					+ "		{ display: '1#空冷机频率', name: 'KLJ_PL1', align: 'center', minWidth: 60},"
					+ "		{ display: '2#空冷机频率', name: 'KLJ_PL2', align: 'center', minWidth: 60},"
					+ "		{ display: '3#空冷机频率', name: 'KLJ_PL3', align: 'center', minWidth: 60}"
					+ " ],"+
					

					"height:'100%',"+
					"dataAction: 'server',"+
					//"url:'xysqdt_searchDatas.action',"+	
					"url:'mbzjdt_serarchmbzjdt.action',"+	
					"delayLoad :true, "+
					"pageParmName :'pageNo',"+
					"sortnameParmName:'sort',"+
					"sortorderParmName: 'order',  "+
					"pagesizeParmName:'rowsPerpage', "+
					"selectRowButtonOnly:true,"+
					"sePaper:true,"+
					"pageSize:30 ,"+
					"rownumbers:true ,"+
					"headerRowHeight:50, "+
					"frozen:true, "+
					"checkbox :false";
//2号稠油联合处理站（水区）
		
		public static final String U2SQDT_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'ACQUISITION_TIME' ,align: 'center', minWidth: 130,frozen:true},"	
			
			+ "  { display: '2#加药浓度(PPM)', name: 'SV_20502A_YND' ,align: 'center', minWidth: 85},"
			+ "  { display: '污水回收泵P-601/A变频器频率(Hz)', name: 'SC_20601A' ,align: 'center', minWidth: 115},"
			+ "  { display: '污水回收泵P-601/B变频器频率(Hz)', name: 'SC_20601B' ,align: 'center', minWidth: 115},"
			+ "  { display: '污水回收泵P-601/C变频器频率(Hz)', name: 'SC_20601C' ,align: 'center', minWidth: 115},"
			+ "  { display: '加药泵P-502/A变频器频率(Hz)', name: 'SC_20502A' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-502/B变频器频率(Hz)', name: 'SC_20502B' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-503/A变频器频率(Hz)', name: 'SC_20503A' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-503/B变频器频率(Hz)', name: 'SC_20503B' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-503/C变频器频率(Hz)', name: 'SC_20503C' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-503/D变频器频率(Hz)', name: 'SC_20503D' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-503/E变频器频率(Hz)', name: 'SC_20503E' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-503/F变频器频率(Hz)', name: 'SC_20503F' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-504/A变频器频率(Hz)', name: 'SC_20504A' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-504/B变频器频率(Hz)', name: 'SC_20504B' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-504/C变频器频率(Hz)', name: 'SC_20504C' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-504/D变频器频率(Hz)', name: 'SC_20504D' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-504/E变频器频率(Hz)', name: 'SC_20504E' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-504/F变频器频率(Hz)', name: 'SC_20504F' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-505/A变频器频率(Hz)', name: 'SC_20505A' ,align: 'center', minWidth: 90},"
			+ "  { display: '加药泵P-505/B变频器频率(Hz)', name: 'SC_20505B' ,align: 'center', minWidth: 90},"
			+ "  { display: '反应提升泵变频器P-201/A频率(Hz)', name: 'SC_20201A' ,align: 'center', minWidth: 105},"
			+ "  { display: '反应提升泵变频器P-201/B频率(Hz)', name: 'SC_20201B' ,align: 'center', minWidth: 105},"
			+ "  { display: '反应提升泵变频器P-201/C频率(Hz)', name: 'SC_20201C' ,align: 'center', minWidth: 105},"
			+ "  { display: '反应提升泵变频器P-201/D频率(Hz)', name: 'SC_20201D' ,align: 'center', minWidth: 105},"
			+ "  { display: '反应提升泵变频器P-201/E频率(Hz)', name: 'SC_20201E' ,align: 'center', minWidth: 105},"
			+ "  { display: '过滤提升泵变频器P-401/A频率(Hz)', name: 'SC_20401A' ,align: 'center', minWidth: 105},"
			+ "  { display: '过滤提升泵变频器P-401/B频率(Hz)', name: 'SC_20401B' ,align: 'center', minWidth: 105},"
			+ "  { display: '过滤提升泵变频器P-401/C频率(Hz)', name: 'SC_20401C' ,align: 'center', minWidth: 105},"
			+ "  { display: '过滤提升泵变频器P-401/D频率(Hz)', name: 'SC_20401D' ,align: 'center', minWidth: 105},"
			+ "  { display: '生活污水池-01/A液位(m)', name: 'LIT_20701A' ,align: 'center', minWidth: 85},"
			+ "  { display: '生活污水池-01/B液位(m)', name: 'LIT_20701B' ,align: 'center', minWidth: 85},"
			+ "  { display: '生活污水池-02/A液位(m)', name: 'LIT_20702A' ,align: 'center', minWidth: 85},"
			+ "  { display: '生活污水池-02/B液位(m)', name: 'LIT_20702B' ,align: 'center', minWidth: 85},"
			+ "  { display: '混凝沉降罐TA-302/A液位(m)', name: 'LIT_20302A' ,align: 'center', minWidth: 95},"
			+ "  { display: '混凝沉降罐TA-302/B液位(m)', name: 'LIT_20302B' ,align: 'center', minWidth: 95},"
			+ "  { display: '过滤沉降罐TA-401/A液位(m)', name: 'LIT_20401A' ,align: 'center', minWidth: 95},"
			+ "  { display: '过滤沉降罐TA-401/B液位(m)', name: 'LIT_20401B' ,align: 'center', minWidth: 95},"
			+ "  { display: '污泥沉降池PD-601/A液位(m)', name: 'LIT_20601A' ,align: 'center', minWidth: 95},"
			+ "  { display: '污泥沉降池PD-601/B液位(m)', name: 'LIT_20601B' ,align: 'center', minWidth: 95},"
			+ "  { display: '污泥沉降池PD-601/C液位(m)', name: 'LIT_20601C' ,align: 'center', minWidth: 95},"
			+ "  { display: '污泥沉降池PD-601/D液位(m)', name: 'LIT_20601D' ,align: 'center', minWidth: 95},"
			+ "  { display: '回收水池PD-602/A液位(m)', name: 'LIT_20602A' ,align: 'center', minWidth: 85},"
			+ "  { display: '回收水池PD-602/B液位(m)', name: 'LIT_20602B' ,align: 'center', minWidth: 85},"
			+ "  { display: '除油罐TA-101/A液位(m)', name: 'LIT_20101A' ,align: 'center', minWidth: 85},"
			+ "  { display: '除油罐TA-101/B液位(m)', name: 'LIT_20101B' ,align: 'center', minWidth: 85},"
			+ "  { display: '调储罐TA-101/A液位(m)', name: 'LIT_20201A' ,align: 'center', minWidth: 85},"
			+ "  { display: '调储罐TA-101/B液位(m)', name: 'LIT_20201B' ,align: 'center', minWidth: 85},"
			+ "  { display: '反应罐TA-301/A液位(m)', name: 'LIT_20301A' ,align: 'center', minWidth: 85},"
			+ "  { display: '反应罐TA-301/B液位(m)', name: 'LIT_20301B' ,align: 'center', minWidth: 85},"
			+ "  { display: '反应罐TA-301/C液位(m)', name: 'LIT_20301C' ,align: 'center', minWidth: 85},"
			+ "  { display: '反应罐TA-301/D液位(m)', name: 'LIT_20301D' ,align: 'center', minWidth: 85},"
			+ "  { display: '反应罐TA-301/E液位(m)', name: 'LIT_20301E' ,align: 'center', minWidth: 85},"
			+ "  { display: '净化水罐液位(m)', name: 'JINGHAUGUAN' ,align: 'center', minWidth: 85},"
			+ "  { display: '1#罐流量(m3/h)', name: 'FLOW_1' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#罐流量(m3/h)', name: 'FLOW_2' ,align: 'center', minWidth: 85},"
			+ "  { display: '3#罐流量(m3/h)', name: 'FLOW_3' ,align: 'center', minWidth: 85},"
			+ "  { display: '4#罐流量(m3/h)', name: 'FLOW_4' ,align: 'center', minWidth: 85},"
			+ "  { display: '5#罐流量(m3/h)', name: 'FLOW_5' ,align: 'center', minWidth: 85},"
			+ "  { display: '6#罐流量(m3/h)', name: 'FLOW_6' ,align: 'center', minWidth: 85},"
			+ "  { display: '7#罐流量(m3/h)', name: 'FLOW_7' ,align: 'center', minWidth: 85},"
			+ "  { display: '8#罐流量(m3/h)', name: 'FLOW_8' ,align: 'center', minWidth: 85},"
			+ "  { display: '9#罐流量(m3/h)', name: 'FLOW_9' ,align: 'center', minWidth: 85},"
			+ "  { display: '10#罐流量(m3/h)', name: 'FLOW_10' ,align: 'center', minWidth: 85},"
			+ "  { display: '11#罐流量(m3/h)', name: 'FLOW_11' ,align: 'center', minWidth: 85},"
			+ "  { display: '12#罐流量(m3/h)', name: 'FLOW_12' ,align: 'center', minWidth: 85},"
			+ "  { display: '13#罐流量(m3/h)', name: 'FLOW_13' ,align: 'center', minWidth: 85},"
			+ "  { display: '14#罐流量(m3/h)', name: 'FLOW_14' ,align: 'center', minWidth: 85},"
			+ "  { display: '15#罐流量(m3/h)', name: 'FLOW_15' ,align: 'center', minWidth: 85},"
			+ "  { display: '16#罐流量(m3/h)', name: 'FLOW_16' ,align: 'center', minWidth: 85},"
			+ "  { display: '17#罐流量(m3/h)', name: 'FLOW_17' ,align: 'center', minWidth: 85},"
			+ "  { display: '18#罐流量(m3/h)', name: 'FLOW_18' ,align: 'center', minWidth: 85},"
			+ "  { display: '清水进站1#管线流量(m3/h)', name: 'FIT_20701' ,align: 'center', minWidth: 85},"
			+ "  { display: '清水进站2#管线流量(m3/h)', name: 'FIT_20702' ,align: 'center', minWidth: 85},"
			+ "  { display: '清水软化水进站3#管线流量(m3/h)', name: 'FIT_20703' ,align: 'center', minWidth: 95},"
			+ "  { display: '清水软化水进站4#管线流量(m3/h)', name: 'FIT_20704' ,align: 'center', minWidth: 95},"
			+ "  { display: '清水软化水进站5#管线流量(m3/h)', name: 'FIT_20705' ,align: 'center', minWidth: 95},"
			+ "  { display: '清水软化水进站6#管线流量(m3/h)', name: 'FIT_20706' ,align: 'center', minWidth: 95},"
			+ "  { display: '清水软化水进站7#管线流量(m3/h)', name: 'FIT_20707' ,align: 'center', minWidth: 95},"
			+ "  { display: '清水软化水进站8#管线流量(m3/h)', name: 'FIT_20708' ,align: 'center', minWidth: 95},"
			+ "  { display: '污泥沉降池进泥流量(m3/h)', name: 'FIT_20601' ,align: 'center', minWidth: 85},"
			+ "  { display: '除油剂流量(m3/h)', name: 'FIT_20505' ,align: 'center', minWidth: 85},"
			+ "  { display: '缓蚀剂流量(m3/h)', name: 'FIT_20504' ,align: 'center', minWidth: 85},"
			+ "  { display: '1#药剂流量A(m3/h)', name: 'FIT_20501A' ,align: 'center', minWidth: 85},"
			+ "  { display: '1#药剂流量B(m3/h)', name: 'FIT_20501B' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#药剂流量A(m3/h)', name: 'FIT_20502A' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#药剂流量B(m3/h)', name: 'FIT_20502B' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#药剂流量C(m3/h)', name: 'FIT_20502C' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#药剂流量D(m3/h)', name: 'FIT_20502D' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#药剂流量E(m3/h)', name: 'FIT_20502E' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#药剂流量F(m3/h)', name: 'FIT_20502F' ,align: 'center', minWidth: 85},"
			+ "  { display: '3#药剂流量A(m3/h)', name: 'FIT_20503A' ,align: 'center', minWidth: 85},"
			+ "  { display: '3#药剂流量B(m3/h)', name: 'FIT_20503B' ,align: 'center', minWidth: 85},"
			+ "  { display: '3#药剂流量C(m3/h)', name: 'FIT_20503C' ,align: 'center', minWidth: 85},"
			+ "  { display: '3#药剂流量D(m3/h)', name: 'FIT_20503D' ,align: 'center', minWidth: 85},"
			+ "  { display: '3#药剂流量E(m3/h)', name: 'FIT_20503E' ,align: 'center', minWidth: 85},"
			+ "  { display: '3#药剂流量F(m3/h)', name: 'FIT_20503F' ,align: 'center', minWidth: 85},"
			+ "  { display: '过滤器2#管线出水流量(m3/h)', name: 'FIT_20401B' ,align: 'center', minWidth: 85},"
			+ "  { display: '过滤出水流量和(m3)', name: 'FIT_20401' ,align: 'center', minWidth: 85},"
			+ "  { display: '过滤器1#管线出水流量(m3/h)', name: 'FIT_20401A' ,align: 'center', minWidth: 85},"
			+ "  { display: '反应罐TA-301B进水流量(m3/h)', name: 'FIT_20301B' ,align: 'center', minWidth: 85},"
			+ "  { display: '反应罐TA-301C进水流量(m3/h)', name: 'FIT_20301C' ,align: 'center', minWidth: 85},"
			+ "  { display: '反应罐TA-301D进水流量(m3/h)', name: 'FIT_20301D' ,align: 'center', minWidth: 85},"
			+ "  { display: '反应罐TA-301E进水流量(m3/h)', name: 'FIT_20301E' ,align: 'center', minWidth: 85},"
			+ "  { display: '反应罐进水流量记录(m3/h)', name: 'FIT_20301' ,align: 'center', minWidth: 85},"
			+ "  { display: 'T2:反应罐TA-301A进水流量(m3/h)', name: 'FIT_20301A' ,align: 'center', minWidth: 95},"
			+ "  { display: '污水回收管线流量(m3/h)', name: 'FIT_20202' ,align: 'center', minWidth: 85},"
			+ "  { display: '总来水2#管线流量(m3/h)', name: 'FIT_20201B' ,align: 'center', minWidth: 85},"
			+ "  { display: '总来水管线流量记录(m3/h)', name: 'FIT_2020X' ,align: 'center', minWidth: 85},"
			+ "  { display: '总来水1#管线流量(m3/h)', name: 'FIT_20201A' ,align: 'center', minWidth: 85},"
			+ "  { display: '反洗进气流量(m3/h)', name: 'FI1003' ,align: 'center', minWidth: 85},"
			+ "  { display: '反洗进水流量(m3/h)', name: 'FI1002' ,align: 'center', minWidth: 85},"
			+ "  { display: 'A过滤进水流量(m3/h)', name: 'FI1001_A' ,align: 'center', minWidth: 85},"
			+ "  { display: 'B过滤进水流量(m3/h)', name: 'FI1001_B' ,align: 'center', minWidth: 85},"
			+ "  { display: 'C过滤进水流量(m3/h)', name: 'FI1001_C' ,align: 'center', minWidth: 85},"
			+ "  { display: 'D过滤进水流量(m3/h)', name: 'FI1001_D' ,align: 'center', minWidth: 85},"
			+ "  { display: 'E过滤进水流量(m3/h)', name: 'FI1001_E' ,align: 'center', minWidth: 85},"
			+ "  { display: 'F过滤进水流量(m3/h)', name: 'FI1001_F' ,align: 'center', minWidth: 85},"
			+ "  { display: 'G过滤进水流量(m3/h)', name: 'FI1001_G' ,align: 'center', minWidth: 85},"
			+ "  { display: 'H过滤进水流量(m3/h)', name: 'FI1001_H' ,align: 'center', minWidth: 85},"
			+ "  { display: 'I过滤进水流量(m3/h)', name: 'FI1001_I' ,align: 'center', minWidth: 85},"
			+ "  { display: 'J过滤进水流量(m3/h)', name: 'FI1001_J' ,align: 'center', minWidth: 85},"
			+ "  { display: 'K过滤进水流量(m3/h)', name: 'FI1001_K' ,align: 'center', minWidth: 85},"
			+ "  { display: 'L过滤进水流量(m3/h)', name: 'FI1001_L' ,align: 'center', minWidth: 85},"
			+ "  { display: 'M过滤进水流量(m3/h)', name: 'FI1001_M' ,align: 'center', minWidth: 85},"
			+ "  { display: 'N过滤进水流量(m3/h)', name: 'FI1001_N' ,align: 'center', minWidth: 85},"
			+ "  { display: '0过滤进水流量(m3/h)', name: 'FI1001_O' ,align: 'center', minWidth: 85},"
			+ "  { display: '二级过滤出水流量和(m3/h)', name: 'FI1001' ,align: 'center', minWidth: 85},"
			+ "  { display: '二级过滤器出水水含油浓度(%)', name: 'AT_20401' ,align: 'center', minWidth: 95},"
			+ "  { display: '混凝沉降罐进水水含油浓度(%)', name: 'AT_20301' ,align: 'center', minWidth: 95},"
			+ "  { display: '清水软水罐液位(m)', name: 'LIT_10102B' ,align: 'center', minWidth: 85},"
			+ "  { display: '清水罐液位(m)', name: 'LIT_10102A' ,align: 'center', minWidth: 85},"
			+ "  { display: '净化软水罐液位(m)', name: 'LIT_10101B' ,align: 'center', minWidth: 85},"
			+ "  { display: '净化水罐液位(m)', name: 'LIT_10101A' ,align: 'center', minWidth: 85},"
			+ "  { display: '1#药剂流量累积（A、B之和）(m3)', name: 'FIT20501Z' ,align: 'center', minWidth: 105},"
			+ "  { display: '2#药剂流量A累积(m3)', name: 'FIT20502AZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#药剂流量B累积(m3)', name: 'FIT20502BZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#药剂流量C累积(m3)', name: 'FIT20502CZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#药剂流量D累积(m3)', name: 'FIT20502DZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#药剂流量E累积(m3)', name: 'FIT20502EZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#药剂流量F累积(m3)', name: 'FIT20502FZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '3#药剂流量A累积(m3)', name: 'FIT20503AZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '3#药剂流量B累积(m3)', name: 'FIT20503BZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '3#药剂流量C累积(m3)', name: 'FIT20503CZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '3#药剂流量D累积(m3)', name: 'FIT20503DZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '3#药剂流量E累积(m3)', name: 'FIT20503EZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '3#药剂流量F累积(m3)', name: 'FIT20503FZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '过滤器1#管线出水流量累积(m3)', name: 'FIT20401AZ' ,align: 'center', minWidth: 90},"
			+ "  { display: '污水回收管线流量累积(m3)', name: 'FIT20202Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '总来水1#管线流量累积(m3)', name: 'FIT20201AZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '反应罐TA-301E进水流量累积(m3)', name: 'FIT20301EZ' ,align: 'center', minWidth: 105},"
			+ "  { display: '反应罐TA-301D进水流量累积(m3)', name: 'FIT20301DZ' ,align: 'center', minWidth: 105},"
			+ "  { display: '反应罐TA-301C进水流量累积(m3)', name: 'FIT20301CZ' ,align: 'center', minWidth: 105},"
			+ "  { display: '反应罐TA-301B进水流量累积(m3)', name: 'FIT20301BZ' ,align: 'center', minWidth: 105},"
			+ "  { display: '反应罐TA-301A进水流量累积(m3)', name: 'FIT20301AZ' ,align: 'center', minWidth: 105},"
			+ "  { display: '反应罐TA-301F进水流量累积(m3)', name: 'FIT20301FZ' ,align: 'center', minWidth: 105},"
			+ "  { display: '反应罐TA-301F进水流量(m3)', name: 'FIT_20301F' ,align: 'center', minWidth: 90},"
			+ "  { display: '过滤器管线出水流量累积(m3)', name: 'FIT20401Z' ,align: 'center', minWidth: 90},"
			+ "  { display: '9#罐流量累积(m3)', name: 'FLOW_9Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '8#罐流量累积(m3)', name: 'FLOW_8Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '7#罐流量累积(m3)', name: 'FLOW_7Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '6#罐流量累积(m3)', name: 'FLOW_6Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '5#罐流量累积(m3)', name: 'FLOW_5Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '4#罐流量累积(m3)', name: 'FLOW_4Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '3#罐流量累积(m3)', name: 'FLOW_3Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#罐流量累积(m3)', name: 'FLOW_2Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '18#罐流量累积(m3)', name: 'FLOW_18Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '17#罐流量累积(m3)', name: 'FLOW_17Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '16#罐流量累积(m3)', name: 'FLOW_16Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '15#罐流量累积(m3)', name: 'FLOW_15Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '14#罐流量累积(m3)', name: 'FLOW_14Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '13#罐流量累积(m3)', name: 'FLOW_13Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '12#罐流量累积(m3)', name: 'FLOW_12Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '11#罐流量累积(m3)', name: 'FLOW_11Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '10#罐流量累积(m3)', name: 'FLOW_10Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '1#罐流量累积(m3)', name: 'FLOW_1Z' ,align: 'center', minWidth: 85}"

				+ " ],"+
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'u2sqdt_searchDatas.action',"+	
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"selectRowButtonOnly:true,"+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"rownumbers:true ,"+
				"frozen:true, "+
				"delayLoad :true, "+
				"headerRowHeight:60, "+
				"headerRowWidth:90, "+
				"checkbox :false";
		
//2号稠油联合处理站（分线计量）
		
		public static final String U2FXJLDT_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'ACQUISITION_TIME' ,align: 'center', minWidth: 130,frozen:true},"	
			+ "  { display: '管汇间流量1(m3/h)', name: 'FIT10101A' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量2(m3/h)', name: 'FIT10102A' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量3(m3/h)', name: 'FIT10103A' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量4(m3/h)', name: 'FIT10104A' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量5(m3/h)', name: 'FIT10105A' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量6(m3/h)', name: 'FIT10106A' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量7(m3/h)', name: 'FIT10107A' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量8(m3/h)', name: 'FIT10108A' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间柴油来油流量检测9(m3/h)', name: 'FIT_10109' ,align: 'center', minWidth: 105},"
			+ "  { display: '管汇间柴油来油流量检测10(m3/h)', name: 'FIT_10110' ,align: 'center', minWidth: 105},"
			+ "  { display: '特1至特2分线流量(m3/h)', name: 'FT2HZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量累积1(m3)', name: 'FIT10101B' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量累积2(m3)', name: 'FIT10102B' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量累积3(m3)', name: 'FIT10103B' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量累积4(m3)', name: 'FIT10104B' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量累积5(m3)', name: 'FIT10105B' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量累积6(m3)', name: 'FIT10106B' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量累积7(m3)', name: 'FIT10107B' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间流量累积8(m3)', name: 'FIT10108B' ,align: 'center', minWidth: 85},"
			+ "  { display: '特1至特2分线累计流量(m3)', name: 'FIQ2HZ' ,align: 'center', minWidth: 100},"
			+ "  { display: '原油进站管线1压力(Mpa)', name: 'PT_10101' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线2压力(Mpa)', name: 'PT_10102' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线3压力(Mpa)', name: 'PT_10103' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线4压力(Mpa)', name: 'PT_10104' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线5压力(Mpa)', name: 'PT_10105' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线6压力(Mpa)', name: 'PT_10106' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线7压力(Mpa)', name: 'PT_10107' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线8压力(Mpa)', name: 'PT_10108' ,align: 'center', minWidth: 85},"
			+ "  { display: '柴油管道压力检测9(Mpa)', name: 'PT_10109' ,align: 'center', minWidth: 85},"
			+ "  { display: '柴油管道压力检测10(Mpa)', name: 'PT_10110' ,align: 'center', minWidth: 90},"
			+ "  { display: '特1至特2分线压力(Mpa)', name: 'PT2HZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油管线1温度(℃)', name: 'TT_10101' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油管线2温度(℃)', name: 'TT_10102' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油管线3温度(℃)', name: 'TT_10103' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油管线4温度(℃)', name: 'TT_10104' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油管线5温度(℃)', name: 'TT_10105' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油管线6温度(℃)', name: 'TT_10106' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油管线7温度(℃)', name: 'TT_10107' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油管线8温度(℃)', name: 'TT_10108' ,align: 'center', minWidth: 85},"
			+ "  { display: '柴油管道温度检测9(℃)', name: 'TT_10109' ,align: 'center', minWidth: 85},"
			+ "  { display: '柴油管道温度检测10(℃)', name: 'TT_10110' ,align: 'center', minWidth: 90},"
			+ "  { display: '特1至特2分线温度(℃)', name: 'TE2HZ' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线1含水(%)', name: 'MR_10101' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线2含水(%)', name: 'MR_10102' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线3含水(%)', name: 'MR_10103' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线4含水(%)', name: 'MR_10104' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线5含水(%)', name: 'MR_10105' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线6含水(%)', name: 'MR_10106' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线7含水(%)', name: 'MR_10107' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油进站管线8含水(%)', name: 'MR_10108' ,align: 'center', minWidth: 85},"
			+ "  { display: '特1至特2分线含水(%)', name: 'MT2HZ' ,align: 'center', minWidth: 85}"
				+ " ],"+
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'u2fxjldt_searchDatas.action',"+	
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"selectRowButtonOnly:true,"+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"rownumbers:true ,"+
				"delayLoad :true, "+
				"frozen:true, "+
				"headerRowHeight:50, "+
				"checkbox :false";
		
		//各采油站日报
		
				public static final String FXJLRB_PAGE_GRID = " columns: ["
						+ "		{ display: '时间', name: 'REPORT_DATE', width: 80, align: 'center',frozen:true},"					
						+ "  { display: '风城一站', columns:"
						+ "	["
						+ "  { display: '2#重32北线', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_102' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_102O' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_102OQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_102R' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '5#13-11线', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_105' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_105O' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_105OQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_105R' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '7#重32南线', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_107' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_107O' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_107OQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_107R' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '总液量(m3)', name: 'FTQ_C1Y' ,align: 'center', minWidth: 85},"
						+ "  { display: '总油量(t)', name: 'FTQ_C1' ,align: 'center', minWidth: 85}"											
						+ "	]},"
						+ "  { display: '风城二站', columns:"
						+ "	["
						+ "  { display: '1线18-2（A）', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_10101A' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_10101AO' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_10101AOQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_10101AR' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '2线18-2（B）', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_10102A' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_10102AO' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_10102AOQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_10102AR' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '18-1线（特一联）', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_103' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_103O' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_103OQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_103R' ,align: 'center', minWidth: 85}"
						+ "	]},"
						
						+ "  { display: '18-1线(B)（特一联）', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_109' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_109O' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_109OQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_109R' ,align: 'center', minWidth: 85}"
						+ "	]},"
						
						+ "  { display: '总液量(m3)', name: 'FTQ_C2Y' ,align: 'center', minWidth: 85},"
						+ "  { display: '总油量(t)', name: 'FTQ_C2' ,align: 'center', minWidth: 85}"											
						+ "	]},"
						+ "  { display: '风城三站', columns:"
						+ "	["
						+ "  { display: '3线18-3（1B）', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_10103A' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_10103AO' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_10103AOQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_10103AR' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '4线18-4（1B）', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_10104A' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_10104AO' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_10104AOQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_10104AR' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '总液量(m3)', name: 'FTQ_C3Y' ,align: 'center', minWidth: 85},"
						+ "  { display: '总油量(t)', name: 'FTQ_C3' ,align: 'center', minWidth: 85}"											
						+ "	]},"
						+ "  { display: 'SAGD采油站', columns:"
						+ "	["
						+ "  { display: 'SAGD采油二站（特一联）', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_S01' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_S01O' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_S01OQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_S01R' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: 'SAGD采油二站（特二联）', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_10108A' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_10108AO' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_10108AOQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_10108AR' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '采出液密闭处理（30万处理）', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_S02' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_S02O' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_S02OQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_S02R' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '重1换热线(管汇间）', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_S03' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_S03O' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_S03OQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_S03R' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '总液量(m3)', name: 'FTQ_S1Y' ,align: 'center', minWidth: 85},"
						+ "  { display: '总油量(t)', name: 'FTQ_S1' ,align: 'center', minWidth: 85}"											
						+ "	]},"
						+ "  { display: '夏子街', columns:"
						+ "	["
						+ "  { display: '夏子街来液线', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_X01' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_X01O' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_X01OQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_X01R' ,align: 'center', minWidth: 85}"
						+ "	]}"											
						+ "	]},"
						+ "  { display: '乌尔禾', columns:"
						+ "	["
						+ "  { display: '乌尔禾来液线', columns:"
						+ "	["
						+ "  { display: '累计流量（m3)', name: 'FTQ_W01' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(m3)', name: 'FTQ_W01O' ,align: 'center', minWidth: 85},"
						+ "  { display: '油量(t)', name: 'FTQ_W01OQ' ,align: 'center', minWidth: 85},"
						+ "  { display: '含水(%)', name: 'FTQ_W01R' ,align: 'center', minWidth: 85}"
						+ "	]}"											
						+ "	]},"
						+ "  { display: '稠油', columns:"
						+ "	["
						+ "  { display: '总液量(m3)', name: 'FTQ_CYZY' ,align: 'center', minWidth: 85},"
						+ "  { display: '总油量(t)', name: 'FTQ_CYZO' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '稀油', columns:"
						+ "	["
						+ "  { display: '总液量(m3)', name: 'FTQ_XYZY' ,align: 'center', minWidth: 85},"
						+ "  { display: '总油量(t)', name: 'FTQ_XYZO' ,align: 'center', minWidth: 85}"
						+ "	]}"
						+"]," +
				
						"height:'85%',"+
						"width:'99%',"+
						"dataAction: 'server',"+
						"url:'fxjlrb_searchDatas.action',"+
						"delayLoad :true,"+
						"usePager:false,"+
						"rownumbers:true ,"+
						"frozen:true, "+
						"checkbox :false";


				//夏次高压注水报表
				public static final String XCGYRB_PAGE_GRID = " columns: ["
						+ "		{ display: '时间', name: 'rq', width: 80, align: 'center',frozen:true},"					
						
						+ "  { display: '1#注水泵', columns:"
						+ "	["
						+ "  { display: '进口压力MPa', name: 'PRSA_301' ,align: 'center', minWidth: 85},"
						+ "  { display: '出口压力MPa', name: 'PRSA_306' ,align: 'center', minWidth: 85},"
						+ "  { display: '油温℃', name: 'TRSA_301' ,align: 'center', minWidth: 85},"
						+ "  { display: '电压V', name: 'ZSB1DY' ,align: 'center', minWidth: 85},"
						+ "  { display: '电流A', name: 'ZSB1DL' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '2#注水泵', columns:"
						+ "	["
						+ "  { display: '进口压力MPa', name: 'PRSA_302' ,align: 'center', minWidth: 85},"
						+ "  { display: '出口压力MPa', name: 'PRSA_307' ,align: 'center', minWidth: 85},"
						+ "  { display: '油温℃', name: 'TRSA_302' ,align: 'center', minWidth: 85},"
						+ "  { display: '电压V', name: 'ZSB2DY' ,align: 'center', minWidth: 85},"
						+ "  { display: '电流A', name: 'ZSB2DL' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '1#2#变频反馈', columns:"
						+ "	["
						+ "  { display: 'Hz', name: 'SR_301' ,align: 'center', minWidth: 85}"
						+ "	]},"										
						+ "  { display: '3#注水泵', columns:"
						+ "	["
						+ "  { display: '进口压力MPa', name: 'PRSA_303' ,align: 'center', minWidth: 85},"
						+ "  { display: '出口压力MPa', name: 'PRSA_308' ,align: 'center', minWidth: 85},"
						+ "  { display: '油温℃', name: 'TRSA_303' ,align: 'center', minWidth: 85},"
						+ "  { display: '电压V', name: 'ZSB3DY' ,align: 'center', minWidth: 85},"
						+ "  { display: '电流A', name: 'ZSB3DL' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '4#注水泵', columns:"
						+ "	["
						+ "  { display: '进口压力MPa', name: 'PRSA_304' ,align: 'center', minWidth: 85},"
						+ "  { display: '出口压力MPa', name: 'PRSA_309' ,align: 'center', minWidth: 85},"
						+ "  { display: '油温℃', name: 'TRSA_304' ,align: 'center', minWidth: 85},"
						+ "  { display: '电压V', name: 'ZSB4DY' ,align: 'center', minWidth: 85},"
						+ "  { display: '电流A', name: 'ZSB4DL' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '3#4#变频反馈', columns:"
						+ "	["
						+ "  { display: 'Hz', name: 'BP34PL' ,align: 'center', minWidth: 85}"
						+ "	]},"											
						+ "  { display: '提升泵', columns:"
						+ "	["
						+ "  { display: '流量计读数', name: 'TSBLLLJ' ,align: 'center', minWidth: 85},"
						+ "  { display: '提升量m³/h', name: 'FRQ_501' ,align: 'center', minWidth: 85},"
						+ "  { display: '变频反馈Hz', name: 'SR_501' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '液位', columns:"
						+ "	["
						+ "  { display: '1#罐液位m', name: 'LRSA_501' ,align: 'center', minWidth: 85},"
						+ "  { display: '2#罐液位m', name: 'LRSA_502' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '分水器', columns:"
						+ "	["
						+ "  { display: '压变MPa', name: 'PRSA_305' ,align: 'center', minWidth: 85}"
						+ "	]},"
						+ "  { display: '注水流量计读数', name: 'FRQ_301' ,align: 'center', minWidth: 85},"
						+ "  { display: '回流流量计读数', name: 'FRQ_301' ,align: 'center', minWidth: 85},"
						+ "  { display: '班注水量（m³）', name: 'BZSL' ,align: 'center', minWidth: 85},"
						+ "  { display: '日注水量（m³）', name: 'RZSL' ,align: 'center', minWidth: 85}"
						+"]," +				
						"height:'95%',"+
						"width:'99%',"+
						"dataAction: 'server',"+
						"url:'xiarb_searchDatas.action',"+
						"delayLoad :true,"+
						"usePager:false,"+
						"rownumbers:true ,"+
						"frozen:true, "+
						"checkbox :false";
//2号稠油联合处理站（油区）
		
		public static final String U2YQDT_PAGE_GRID = " columns: ["
+ "  { display: '采集时间', name: 'ACQUISITION_TIME' ,align: 'center', minWidth: 130,frozen:true},"	
			
			+ "  { display: '调储罐TA-101/B液位(m)', name: 'LIT_20201B' ,align: 'center', minWidth: 85},"
			+ "  { display: '调储罐TA-101/A液位(m)', name: 'LIT_20201A' ,align: 'center', minWidth: 85},"
			+ "  { display: '罐1/1油水界面(m)', name: 'LIT_10301' ,align: 'center', minWidth: 85},"
			+ "  { display: '罐1/1液位(m)', name: 'LIT_10302' ,align: 'center', minWidth: 85},"
			+ "  { display: '罐1/2油水界面(m)', name: 'LIT_10303' ,align: 'center', minWidth: 85},"
			+ "  { display: '罐1/2液位(m)', name: 'LIT_10304' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/1液位(m)', name: 'LIT_10305' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/2液位(m)', name: 'LIT_10306' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/3液位(m)', name: 'LIT_10307' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/4液位(m)', name: 'LIT_10308' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/5液位(m)', name: 'LIT_10309' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/6液位(m)', name: 'LIT_10310' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/7液位(m)', name: 'LIT_10311' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/8液位(m)', name: 'LIT_10312' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/9液位(m)', name: 'LIT_10313' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/10液位(m)', name: 'LIT_10314' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/1温度1(℃)', name: 'TT10301A' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/1温度2(℃)', name: 'TT10301B' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/1温度3(℃)', name: 'TT10301C' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/1温度4(℃)', name: 'TT10301D' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/2温度1(℃)', name: 'TT10302A' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/2温度2(℃)', name: 'TT10302B' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/2温度3(℃)', name: 'TT10302C' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/2温度4(℃)', name: 'TT10302D' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/3温度1(℃)', name: 'TT10303A' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/3温度2(℃)', name: 'TT10303B' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/3温度3(℃)', name: 'TT10303C' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/3温度4(℃)', name: 'TT10303D' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/4温度1(℃)', name: 'TT10304A' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/4温度2(℃)', name: 'TT10304B' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/4温度3(℃)', name: 'TT10304C' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/4温度4(℃)', name: 'TT10304D' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/5温度1(℃)', name: 'TT10305A' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/5温度2(℃)', name: 'TT10305B' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/5温度3(℃)', name: 'TT10305C' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/5温度4(℃)', name: 'TT10305D' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/6温度1(℃)', name: 'TT10306A' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/6温度2(℃)', name: 'TT10306B' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/6温度3(℃)', name: 'TT10306C' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/6温度4(℃)', name: 'TT10306D' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/7温度1(℃)', name: 'TT10307A' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/7温度2(℃)', name: 'TT10307B' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/7温度3(℃)', name: 'TT10307C' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/7温度4(℃)', name: 'TT10307D' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/8温度1(℃)', name: 'TT10308A' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/8温度2(℃)', name: 'TT10308B' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/8温度3(℃)', name: 'TT10308C' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/8温度4(℃)', name: 'TT10308D' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/9温度1(℃)', name: 'TT10309A' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/9温度2(℃)', name: 'TT10309B' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/9温度3(℃)', name: 'TT10309C' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/9温度4(℃)', name: 'TT10309D' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/10温度1(℃)', name: 'TT10310A' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/10温度2(℃)', name: 'TT10310B' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/10温度3(℃)', name: 'TT10310C' ,align: 'center', minWidth: 65},"
			+ "  { display: '罐2/10温度4(℃)', name: 'TT10310D' ,align: 'center', minWidth: 65},"
			+ "  { display: '污油提升泵出油总管温度(℃)', name: 'TT_10406' ,align: 'center', minWidth: 85},"
			+ "  { display: '除油罐出油进泵总管温度(℃)', name: 'TT_10405' ,align: 'center', minWidth: 85},"
			+ "  { display: '净化油罐出水或回脱原油进转油泵总管温(℃)', name: 'TT_10404' ,align: 'center', minWidth: 125},"
			+ "  { display: '缓冲罐出毛油进转油泵总管温度(℃)', name: 'TT_10403' ,align: 'center', minWidth: 105},"
			+ "  { display: '净化油罐出水出转油泵去一段沉降脱水罐温度(℃)', name: 'TT_10402' ,align: 'center', minWidth: 150},"
			+ "  { display: '毛油或回脱原油出转油泵去掺热装置总管温度(℃)', name: 'TT_10401' ,align: 'center', minWidth: 150},"
			+ "  { display: '沉降罐出口原油温度(℃)', name: 'TT_10312' ,align: 'center', minWidth: 85},"
			+ "  { display: '导热油伴热蒸汽管道温度A(℃)', name: 'TT_10311A' ,align: 'center', minWidth: 95},"
			+ "  { display: '导热油伴热蒸汽管道温度B(℃)', name: 'TT_10311B' ,align: 'center', minWidth: 95},"
			+ "  { display: '管汇间出口原油总管温度11(℃)', name: 'TT_10111' ,align: 'center', minWidth: 95},"
			+ "  { display: '管汇间出口原油总管温度12(℃)', name: 'TT_10112' ,align: 'center', minWidth: 95},"
			+ "  { display: '加药泵频率A(Hz)', name: 'SC_10501A' ,align: 'center', minWidth: 85},"
			+ "  { display: '加药泵频率B(Hz)', name: 'SC_10501B' ,align: 'center', minWidth: 85},"
			+ "  { display: '加药泵频率C(Hz)', name: 'SC_10501C' ,align: 'center', minWidth: 85},"
			+ "  { display: '加药泵频率D(Hz)', name: 'SC_10501D' ,align: 'center', minWidth: 85},"
			+ "  { display: '加药泵频率E(Hz)', name: 'SC_10501E' ,align: 'center', minWidth: 85},"
			+ "  { display: '加药泵频率F(Hz)', name: 'SC_10501F' ,align: 'center', minWidth: 85},"
			+ "  { display: '热煤膨胀槽液位1(m)', name: 'RMLIT1001' ,align: 'center', minWidth: 85},"
			+ "  { display: '热煤膨胀槽液位2(m)', name: 'RMLIT2001' ,align: 'center', minWidth: 85},"
			+ "  { display: '热煤出油温度1(℃)', name: 'RMTT1002' ,align: 'center', minWidth: 85},"
			+ "  { display: '热煤出油温度2(℃)', name: 'RMTT2002' ,align: 'center', minWidth: 85},"
			+ "  { display: '热媒换热器单元蒸汽管道压力控制(Mpa)', name: 'PV_10801' ,align: 'center', minWidth: 115},"
			+ "  { display: '仪表风储罐出口管线压力(Mpa)', name: 'PT_11101' ,align: 'center', minWidth: 85},"
			+ "  { display: '热媒换热器单元蒸汽管道压力(Mpa)', name: 'PT_10801' ,align: 'center', minWidth: 105},"
			+ "  { display: '新建罐区热导油供油管道压力1(Mpa)', name: 'PT_10601' ,align: 'center', minWidth: 105},"
			+ "  { display: '新建罐区热导油供油管道压力2(Mpa)', name: 'PT_10602' ,align: 'center', minWidth: 105},"
			+ "  { display: '新建罐区热导油供油管道压力3(Mpa)', name: 'PT_10603' ,align: 'center', minWidth: 105},"
			+ "  { display: '污油提升泵出油总管压力(Mpa)', name: 'PT_10406' ,align: 'center', minWidth: 85},"
			+ "  { display: '除油罐出油进泵总管压力(Mpa)', name: 'PT_10405' ,align: 'center', minWidth: 85},"
			+ "  { display: '净化油罐出水或回脱原油进转油泵总管压力(Mpa)', name: 'PT_10404' ,align: 'center', minWidth: 150},"
			+ "  { display: '缓冲罐出毛油进转油泵总管压力(Mpa)', name: 'PT_10403' ,align: 'center', minWidth: 120},"
			+ "  { display: '净化油罐出水出转油泵去一段沉降脱水罐压力(Mpa)', name: 'PT_10402' ,align: 'center', minWidth: 150},"
			+ "  { display: '毛油或回脱原油出转油泵去掺热装置总管压力(Mpa)', name: 'PT_10401' ,align: 'center', minWidth: 150},"
			+ "  { display: '掺热装置蒸汽进口管道压力(Mpa)', name: 'PT_10302' ,align: 'center', minWidth: 95},"
			+ "  { display: '蒸汽管道压力(Mpa)', name: 'PT_10301' ,align: 'center', minWidth: 85},"
			+ "  { display: '管汇间出口原油总管压力11(Mpa)', name: 'PT_10111' ,align: 'center', minWidth: 105},"
			+ "  { display: '管汇间出口原油总管压力12(Mpa)', name: 'PT_10112' ,align: 'center', minWidth: 105},"
			+ "  { display: '一段沉降罐后原油含水率(%)', name: 'MT_10301' ,align: 'center', minWidth: 85},"
			+ "  { display: '降罐油厚(m)', name: 'LIT10303YH' ,align: 'center', minWidth: 85},"
			+ "  { display: '降罐油厚(m)', name: 'LIT10301YH' ,align: 'center', minWidth: 85},"
			+ "  { display: '1号空压机出口压力(Mpa)', name: 'KYZ_PT1001' ,align: 'center', minWidth: 85},"
			+ "  { display: '1号空压机出口温度(℃)', name: 'KYZ_TT1001' ,align: 'center', minWidth: 85},"
			+ "  { display: '2号空压机出口温度(℃)', name: 'KYZ_TT2001' ,align: 'center', minWidth: 85},"
			+ "  { display: '正向破乳剂1流量(℃)', name: 'FIT_10501' ,align: 'center', minWidth: 85},"
			+ "  { display: '正向破乳剂2流量(℃)', name: 'FIT_10502' ,align: 'center', minWidth: 85},"
			+ "  { display: '反向破乳剂1流量(℃)', name: 'FIT_10503' ,align: 'center', minWidth: 85},"
			+ "  { display: '反向破乳剂2流量(℃)', name: 'FIT_10504' ,align: 'center', minWidth: 85},"
			+ "  { display: '原油罐区柴油来油流量计量1(m3/h)', name: 'FIT_10301' ,align: 'center', minWidth: 105},"
			+ "  { display: '原油罐区柴油来油流量计量2(m3/h)', name: 'FIT_10302' ,align: 'center', minWidth: 105},"
			+ "  { display: '供水一次供水压力(Mpa)', name: 'RHS_PT30401' ,align: 'center', minWidth: 85},"
			+ "  { display: '供水一次回水压力(Mpa)', name: 'RHS_PT30402' ,align: 'center', minWidth: 85},"
			+ "  { display: '供水二次供水压力(Mpa)', name: 'RHS_PT30403' ,align: 'center', minWidth: 85},"
			+ "  { display: '供水二次回水压力(Mpa)', name: 'RHS_PT30404' ,align: 'center', minWidth: 85},"
			+ "  { display: '供水一次供水温度(℃)', name: 'RHS_TT30401' ,align: 'center', minWidth: 85},"
			+ "  { display: '供水一次回水温度(℃)', name: 'RHS_TT30402' ,align: 'center', minWidth: 85},"
			+ "  { display: '供水二次供水温度(℃)', name: 'RHS_TT30403' ,align: 'center', minWidth: 85},"
			+ "  { display: '供水二次回水温度(℃)', name: 'RHS_TT30404' ,align: 'center', minWidth: 85},"
			+ "  { display: '软化水箱液位(m)', name: 'RHS_LIT30401' ,align: 'center', minWidth: 85},"
			+ "  { display: '热媒进油温度1(℃)', name: 'RMTT1001' ,align: 'center', minWidth: 85},"
			+ "  { display: '热媒进油温度2(℃)', name: 'RMTT2001' ,align: 'center', minWidth: 85},"
			+ "  { display: '正向破乳剂1流量累积(m3)', name: 'FIT10501Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '正向破乳剂2流量累积(m3)', name: 'FIT10502Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '反向破乳剂1流量累积(m3)', name: 'FIT10503Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '反向破乳剂2流量累积(m3)', name: 'FIT10504Z' ,align: 'center', minWidth: 85},"
			+ "  { display: '1#正相药罐液位(m)', name: 'LIT_00001' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#正相药罐液位(m)', name: 'LIT_00002' ,align: 'center', minWidth: 85},"
			+ "  { display: '1#反相药罐液位(m)', name: 'LIT_00003' ,align: 'center', minWidth: 85},"
			+ "  { display: '2#反相药罐液位(m)', name: 'LIT_00004' ,align: 'center', minWidth: 85}"
				+ " ],"+
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'u2yqdt_searchDatas.action',"+	
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"selectRowButtonOnly:true,"+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"delayLoad :true, "+
				"rownumbers:true ,"+
				"frozen:true, "+
				"headerRowHeight:50, "+
				"checkbox :false";

		public static final String PC_RD_U1_WATER_AUTO_T_PAGE_GRID = " columns: ["
			//123注释	单位		字段名	字段类型
			+ "		{ display: '采集时间', name: 'CJSJ', align: 'center',minWidth: 140,frozen:true},"	
			+ "		{ display: '一区来水流量m3/h', name: 'FT1001', align: 'center', width: 100},"
			+ "		{ display: '预处理来水流量（水二区来水流量）m3/h		FIT_602', align: 'center', width: 100},"
			+ "		{ display: '二计算流量01 m3/h', name: 'SRJS_01', align: 'center', width: 100},"
			+ "		{ display: '二计算流量02 m3/h', name: 'SRJS_02', align: 'center', width: 100},"
			+ "		{ display: '二计算流量03 m3/h', name: 'SRJS_03', align: 'center', width: 100},"
			+ "		{ display: '一计算流量01 m3/h', name: 'SYJS_01', align: 'center', width: 100},"
			+ "		{ display: '一计算流量02 m3/h', name: 'SYJS_02', align: 'center', width: 100},"
			+ "		{ display: '一计算流量03 m3/h', name: 'SYJS_03', align: 'center', width: 100},"
			+ "		{ display: '#反应提升泵频率 Hz', name: 'ST1001', align: 'center', width: 100},"
			+ "		{ display: '2#反应提升泵频率 Hz', name: 'ST1002', align: 'center', width: 100},"
			+ "		{ display: '#过滤提升泵频率 Hz', name: 'ST1003', align: 'center', width: 100},"
			+ "		{ display: '过滤提升泵频率 Hz', name: 'ST1004', align: 'center', width: 100},"
			+ "		{ display: '气浮流量和 m3/h', name: 'QFH_01', align: 'center', width: 100},"
			+ "		{ display: '#加药撬计算流量 m3/h', name: 'Q1', align: 'center', width: 100},"
			+ "		{ display: '加药撬计算流量 m3/h', name: 'Q2', align: 'center', width: 100},"
			+ "		{ display: '加药撬计算流量 m3/h', name: 'Q3', align: 'center', width: 100},"
			+ "		{ display: '加药撬计算流量 m3/h', name: 'Q4', align: 'center', width: 100},"
			+ "		{ display: '加药撬计算流量 m3/h', name: 'Q5', align: 'center', width: 100},"
			+ "		{ display: '#反应器1#药剂浓度 PPM', name: 'PPM111', align: 'center', width: 100},"
			+ "		{ display: '#反应器2#药剂浓度 PPM', name: 'PPM1_2', align: 'center', width: 100},"
			+ "		{ display: '#反应器3#药剂浓度 PPM', name: 'PPM1_3', align: 'center', width: 100},"
			+ "		{ display: '反应器3#药剂浓度 PPM', name: 'PPM2_3', align: 'center', width: 100},"
			+ "		{ display: '反应器2#药剂浓度 PPM', name: 'PPM22_2', align: 'center', width: 100},"
			+ "		{ display: '反应器1#药剂浓度 PPM', name: 'PPM_34', align: 'center', width: 100},"
			+ "		{ display: '反应器2#药剂浓度 PPM', name: 'PPM3_2', align: 'center', width: 100},"
			+ "		{ display: '反应器3#药剂浓度 PPM', name: 'PPM3_3', align: 'center', width: 100},"
			+ "		{ display: '反应器2#药剂浓度 PPM', name: 'PPM4_2', align: 'center', width: 100},"
			+ "		{ display: '反应器3#药剂浓度 PPM', name: 'PPM4_3', align: 'center', width: 100},"
			+ "		{ display: '除油剂加药泵反馈 Hz', name: 'P603E_AI', align: 'center', width: 100},"
			+ "		{ display: '#混凝剂加药泵反馈 Hz', name: 'P603A_AI', align: 'center', width: 100},"
			+ "		{ display: '混凝剂加药泵反馈 Hz', name: 'P603B_AI', align: 'center', width: 100},"
			+ "		{ display: '#助凝剂加药泵反馈 Hz', name: 'P603C_AI', align: 'center', width: 100},"
			+ "		{ display: '助凝剂加药泵反馈 Hz', name: 'P603D_AI', align: 'center', width: 100},"
			+ "		{ display: '#过滤提升泵反馈 Hz', name: 'P602A_AI', align: 'center', width: 100},"
			+ "		{ display: '过滤提升泵反馈 Hz', name: 'P602B_AI', align: 'center', width: 100},"
			+ "		{ display: '过滤提升泵反馈 Hz', name: 'P602C_AI', align: 'center', width: 100},"
			+ "		{ display: '过滤提升泵反馈 Hz', name: 'P602D_AI', align: 'center', width: 100},"
			+ "		{ display: '#气浮提升泵反馈 Hz', name: 'P601A_AI', align: 'center', width: 100},"
			+ "		{ display: '气浮提升泵反馈 Hz', name: 'P601B_AI', align: 'center', width: 100},"
			+ "		{ display: '加药撬实际浓度 PPM', name: 'N55', align: 'center', width: 100},"
			+ "		{ display: '区1#净化水罐液位 m', name: 'LT603', align: 'center', width: 100},"
			+ "		{ display: '区2#净化水罐液位 m', name: 'LT604', align: 'center', width: 100},"
			+ "		{ display: '#消防水罐液位 m', name: 'LT404', align: 'center', width: 100},"
			+ "		{ display: '消防水罐液位 m', name: 'LT403', align: 'center', width: 100},"
			+ "		{ display: '软水罐液位 m', name: 'LT402', align: 'center', width: 100},"
			+ "		{ display: '生水罐液位 m', name: 'LT401', align: 'center', width: 100},"
			+ "		{ display: '缓冲罐液位 m', name: 'LT201', align: 'center', width: 100},"
			+ "		{ display: '污水池液位 m', name: 'LT1011', align: 'center', width: 100},"
			+ "		{ display: '#污水池液位 m', name: 'LT1012', align: 'center', width: 100},"
			+ "		{ display: '#污泥池液位 m', name: 'LT11013', align: 'center', width: 100},"
			+ "		{ display: '污泥池液位 m', name: 'LT11014', align: 'center', width: 100},"
			+ "		{ display: '#污水池液位 m', name: 'LT11015', align: 'center', width: 100},"
			+ "		{ display: '污水池液位 m', name: 'LT11016', align: 'center', width: 100},"
			+ "		{ display: '反冲洗罐液位 m', name: 'LT1010', align: 'center', width: 100},"
			+ "		{ display: '污油罐液位 m', name: 'LT1009', align: 'center', width: 100},"
			+ "		{ display: '#过滤缓冲罐液位 m', name: 'LT1007', align: 'center', width: 100},"
			+ "		{ display: '过滤缓冲罐液位 m', name: 'LT1008', align: 'center', width: 100},"
			+ "		{ display: '#调储鑵液位 m', name: 'LT1001', align: 'center', width: 100},"
			+ "		{ display: '调储鑵液位 m', name: 'LT1002', align: 'center', width: 100},"
			+ "		{ display: '#反应鑵液位 m', name: 'LT1003', align: 'center', width: 100},"
			+ "		{ display: '反应鑵液位 m', name: 'LT1004', align: 'center', width: 100},"
			+ "		{ display: '反应鑵液位 m', name: 'LT1005', align: 'center', width: 100},"
			+ "		{ display: '反应鑵液位 m', name: 'LT1006', align: 'center', width: 100},"
			+ "		{ display: '二过滤2#空压机压力	Mpa', name: 'LIT_606C', align: 'center', width: 100},"
			+ "		{ display: 'SAGD沉降池液位1 m', name: 'LIT_606A', align: 'center', width: 100},"
			+ "		{ display: 'SAGD沉降池液位2	m', name: 'LIT_606B', align: 'center', width: 100},"
			+ "		{ display: '生产污水池液位  m', name: 'LIT_605', align: 'center', width: 100},"
			+ "		{ display: '净化水罐液位B m', name: 'LIT_604B', align: 'center', width: 100},"
			+ "		{ display: '净化水罐液位A m', name: 'LIT_604A', align: 'center', width: 100},"
			+ "		{ display: '浮渣罐液位 m', name: 'LIT_603', align: 'center', width: 100},"
			+ "		{ display: '过滤缓冲水池液位B m', name: 'LIT_602B', align: 'center', width: 100},"
			+ "		{ display: '过滤缓冲水池液位A m', name: 'LIT_602A', align: 'center', width: 100},"
			+ "		{ display: '调储罐D01B液位  m', name: 'LIT_601B', align: 'center', width: 100},"
			+ "		{ display: '调储罐D01A液位  m', name: 'LIT_601A', align: 'center', width: 100},"
			+ "		{ display: '外输水流量	m3/h', name: 'FT1016', align: 'center', width: 100},"
			+ "		{ display: '一区1/2#反应罐1#药剂流量(m3/h)', name: 'FT1006', align: 'center', width: 100},"
			+ "		{ display: '一区4#反应罐2#药剂流量(m3/h)', name: 'FT1007', align: 'center', width: 100},"
			+ "		{ display: '一区4#反应罐3#药剂流量(m3/h	)', name: 'FT1008', align: 'center', width: 100},"
			+ "		{ display: '一区2#反应罐2#药剂流量(m3/h	)', name: 'FT1009', align: 'center', width: 100},"
			+ "		{ display: '一区2#反应罐3#药剂流量(m3/h	)', name: 'FT1010', align: 'center', width: 100},"
			+ "		{ display: '一区3/4#反应罐1#药剂流量  m3/h', name: 'FT1011', align: 'center', width: 100},"
			+ "		{ display: '一区2#反应罐2#药剂流量 m3/h', name: 'FT1012', align: 'center', width: 100},"
			+ "		{ display: '一区2#反应罐3#药剂流量 m3/h', name: 'FT1013', align: 'center', width: 100},"
			+ "		{ display: '一区4#反应罐2#药剂流量 m3/h', name: 'FT1014', align: 'center', width: 100},"
			+ "		{ display: '一区4#反应罐3#药剂流量 m3/h', name: 'FT1015', align: 'center', width: 100},"
			+ "		{ display: '1#反应罐流量 m3/h', name: 'FT1005', align: 'center', width: 100},"
			+ "		{ display: '2#反应罐流量 m3/h', name: 'FT1004', align: 'center', width: 100},"
			+ "		{ display: '3#反应罐流量 m3/h', name: 'FT1003', align: 'center', width: 100},"
			+ "		{ display: '4#反应罐流量 m3/h', name: 'FT1002', align: 'center', width: 100},"
			+ "		{ display: '向加药流量 m3/h', name: 'FT03', align: 'center', width: 100},"
			+ "		{ display: '向一段加药流量 m3/h', name: 'FT02', align: 'center', width: 100},"
			+ "		{ display: '向二段加药流量 m3/h', name: 'FT01', align: 'center', width: 100},"
			+ "		{ display: '向一段加药流量累计 m3', name: 'FITQ203', align: 'center', width: 100},"
			+ "		{ display: '向一段加药流量累计 m3', name: 'FITQ202', align: 'center', width: 100},"
			+ "		{ display: '向二段加药流量累计 m3', name: 'FITQ201', align: 'center', width: 100},"
			+ "		{ display: '区外输外排流量  m3/h', name: 'FIT801', align: 'center', width: 100},"
			+ "		{ display: '应罐总流量  m3/h', name: 'FIT_1234', align: 'center', width: 100},"
			+ "		{ display: '过滤器出水流量  m3/h', name: 'FIT_606', align: 'center', width: 100},"
			+ "		{ display: '气浮机E01B出水流量 m3/h', name: 'FIT_605', align: 'center', width: 100},"
			+ "		{ display: '气浮机E01A出水流量 m3/h', name: 'FIT_604', align: 'center', width: 100},"
			+ "		{ display: '回水流量 m3/h', name: 'FIT_603', align: 'center', width: 100},"
			+ "		{ display: '除油剂流量 m3/h', name: 'FIT_601E', align: 'center', width: 100},"
			+ "		{ display: '助凝剂流量 m3/h', name: 'FIT_601D', align: 'center', width: 100},"
			+ "		{ display: '#助凝剂流量 m3/h', name: 'FIT_601C', align: 'center', width: 100},"
			+ "		{ display: '二区2#混凝剂流量 m3/h', name: 'FIT_601B', align: 'center', width: 100},"
			+ "		{ display: '#混凝剂流量	m3/h', name: 'FIT_601A', align: 'center', width: 100},"
			+ "		{ display: '污泥回收流量	m3/h', name: 'FIT_317', align: 'center', width: 100},"
			+ "		{ display: '除油剂流量累计 m3', name: 'FIQ_601E', align: 'center', width: 100},"
			+ "		{ display: '#混凝剂流量累计 m3', name: 'FIQ_601A', align: 'center', width: 100},"
			+ "		{ display: '混凝剂流量累计  m3', name: 'FIQ_601B', align: 'center', width: 100},"
			+ "		{ display: '#助凝剂流量累计 m3', name: 'FIQ_601C', align: 'center', width: 100},"
			+ "		{ display: '助凝剂流量累计 m3', name: 'FIQ_601D', align: 'center', width: 100},"
			+ "		{ display: '污泥回收流量累计 m3', name: 'FIQ_317', align: 'center', width: 100},"
			+ "		{ display: '相1#泵变频 Hz', name: 'BP01_MV', align: 'center', width: 100},"
			+ "		{ display: '相2#泵变频 Hz', name: 'BP02_MV', align: 'center', width: 100},"
			+ "		{ display: '相3#泵变频 Hz', name: 'BP03_MV', align: 'center', width: 100},"
			+ "		{ display: '向1#泵变频 Hz', name: 'BP04_MV', align: 'center', width: 100},"
			+ "		{ display: '向2#泵变频 Hz', name: 'BP05_MV', align: 'center', width: 100},"
			+ "		{ display: '向3#泵变频 Hz', name: 'BP06_MV', align: 'center', width: 100},"
			+ "		{ display: '相1#变频器频率反馈	Hz', name: 'BP001PL', align: 'center', width: 100},"
			+ "		{ display: '相2#变频器频率反馈	Hz', name: 'BP002PL', align: 'center', width: 100},"
			+ "		{ display: '相3#变频器频率反馈	Hz', name: 'BP003PL', align: 'center', width: 100},"
			+ "		{ display: '相1#变频器频率反馈	Hz', name: 'BP004PL', align: 'center', width: 100},"
			+ "		{ display: '相2#变频器频率反馈	Hz', name: 'BP005PL', align: 'center', width: 100},"
			+ "		{ display: '相3#变频器频率反馈	Hz', name: 'BP006PL', align: 'center', width: 100}"
			+ " ],"+
			
			"height:'100%',"+
			"dataAction: 'server',"+
			//"url:'xysqdt_searchDatas.action',"+	
			"url:'u1sqdt_serarchU1sqdt.action',"+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"delayLoad :true, "+
			"rownumbers:true ,"+
			"headerRowHeight:50, "+
			"frozen:true, "+
			"checkbox :false";				
		
		//1号稠油联合处理站（密闭处理）
		public static final String U1MBDT_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 130,frozen:true},"	
			
			+ "  { display: '1号除氧器回流水量(m3/h)', name: 'CHUYANGA_FITBACK' ,align: 'center', minWidth: 90},"	
			+ "  { display: '1号除氧器A组出水量(m3/h)', name: 'CHUYANGA_FITOUTA' ,align: 'center', minWidth: 90},"	
			+ "  { display: '1号除氧器B组出水量(m3/h)', name: 'CHUYANGA_FITOUTB' ,align: 'center', minWidth: 90},"	
			+ "  { display: '1号除氧器出口压力(Mpa)', name: 'CHUYANGA_PTOUT' ,align: 'center', minWidth: 85},"	
			+ "  { display: '2号除氧器回流水量(m3/h)', name: 'CHUYANGB_FITBACK' ,align: 'center', minWidth: 90},"	
			+ "  { display: '2号除氧器A组出水量(m3/h)', name: 'CHUYANGB_FITOUTA' ,align: 'center', minWidth: 90},"	
			+ "  { display: '2号除氧器B组出水量(m3/h)', name: 'CHUYANGB_FITOUTB' ,align: 'center', minWidth: 90},"	
			+ "  { display: '2号除氧器出口压力(Mpa)', name: 'CHUYANGB_PTOUT' ,align: 'center', minWidth: 90},"	
			+ "  { display: '1#除砂器温度(℃)', name: 'CSJ_TCS_1' ,align: 'center', minWidth: 75},"	
			+ "  { display: '2#除砂器温度(℃)', name: 'CSJ_TCS_2' ,align: 'center', minWidth: 75},"	
			+ "  { display: '3#除砂器温度(℃)', name: 'CSJ_TCS_3' ,align: 'center', minWidth: 75},"	
			+ "  { display: '4#除砂器温度(℃)', name: 'CSJ_TCS_4' ,align: 'center', minWidth: 75},"	
			+ "  { display: '清水软化水进换热器管道累计流量（支路1）(m3)', name: 'FIQ_101' ,align: 'center', minWidth: 150},"	
			+ "  { display: '清水软化水进换热器管道累计流量（支路2）(m3)', name: 'FIQ_102' ,align: 'center', minWidth: 150},"	
			+ "  { display: '净水软化水进换热器管道累计流量（支路1）(m3)', name: 'FIQ_103' ,align: 'center', minWidth: 150},"	
			+ "  { display: '净水软化水进换热器管道累计流量（支路2）(m3)', name: 'FIQ_104' ,align: 'center', minWidth: 150},"	
			+ "  { display: '冷却水进冷却喷淋罐流量(m3/h)', name: 'FIQ_201' ,align: 'center', minWidth: 100},"	
			+ "  { display: '冷却水进冷却喷淋罐累计流量(m3)', name: 'FIQ_201_RS' ,align: 'center', minWidth: 120},"	
			+ "  { display: '混凝除油罐进液流量(m3/h)', name: 'FIQ_202' ,align: 'center', minWidth: 85},"	
			+ "  { display: '混凝除油罐进液累计流量(m3)', name: 'FIQ_202_RS' ,align: 'center', minWidth: 95},"	
			+ "  { display: '采出水去水处理流量(m3/h)', name: 'FIQ_203' ,align: 'center', minWidth: 85},"	
			+ "  { display: '采出水去水处理累计流量(m3)', name: 'FIQ_203_RS' ,align: 'center', minWidth: 95},"	
			+ "  { display: '清水软化水进换热器管道流量（支路1）(m3/h)', name: 'FIT_101' ,align: 'center', minWidth: 150},"	
			+ "  { display: '清水软化水进换热器管道流量（支路2）(m3/h)', name: 'FIT_102' ,align: 'center', minWidth: 150},"	
			+ "  { display: '净水软化水进换热器管道流量（支路1）(m3/h)', name: 'FIT_103' ,align: 'center', minWidth: 150},"	
			+ "  { display: '净水软化水进换热器管道流量（支路2）(m3/h)', name: 'FIT_104' ,align: 'center', minWidth: 150},"	
			+ "  { display: '二次换热出油流量(m3/h)', name: 'FIT_201' ,align: 'center', minWidth: 85},"	
			+ "  { display: '软化水进单座一次1#换热器流量(m3/h)', name: 'FIT_2101' ,align: 'center', minWidth: 120},"	
			+ "  { display: '软化水进单座一次2#换热器流量(m3/h)', name: 'FIT_2102' ,align: 'center', minWidth: 120},"	
			+ "  { display: '软化水进单座一次3#换热器流量(m3/h)', name: 'FIT_2103' ,align: 'center', minWidth: 120},"	
			+ "  { display: '软化水进单座一次4#换热器流量(m3/h)', name: 'FIT_2104' ,align: 'center', minWidth: 120},"	
			+ "  { display: '软化除盐水进单座二次1#换热器流量(m3/h)', name: 'FIT_2105' ,align: 'center', minWidth: 120},"	
			+ "  { display: '软化除盐水进单座二次2#换热器流量(m3/h)', name: 'FIT_2106' ,align: 'center', minWidth: 120},"	
			+ "  { display: '软化除盐水进单座二次3#换热器流量(m3/h)', name: 'FIT_2107' ,align: 'center', minWidth: 120},"	
			+ "  { display: '软化除盐水进单座二次4#换热器流量(m3/h)', name: 'FIT_2108' ,align: 'center', minWidth: 120},"	
			+ "  { display: '软化除盐水进单座二次5#换热器流量(m3/h)', name: 'FIT_2109' ,align: 'center', minWidth: 120},"	
			+ "  { display: '软化除盐水进单座二次6#换热器流量(m3/h)', name: 'FIT_2110' ,align: 'center', minWidth: 120},"	
			+ "  { display: '吞吐区软化水进单座1#水水换热器流量(m3/h)', name: 'FIT_2112' ,align: 'center', minWidth: 125},"	
			+ "  { display: '吞吐区软化水进单座2#水水换热器流量(m3/h)', name: 'FIT_2113' ,align: 'center', minWidth: 125},"	
			+ "  { display: '吞吐区软化水进单座3#水水换热器流量(m3/h)', name: 'FIT_2114' ,align: 'center', minWidth: 125},"	
			+ "  { display: '吞吐区软化水进单座4#水水换热器流量(m3/h)', name: 'FIT_2115' ,align: 'center', minWidth: 125},"	
			+ "  { display: '气液分离器汽总出口小流量(m3/h)', name: 'FIT_2116A' ,align: 'center', minWidth: 105},"	
			+ "  { display: '气液分离器汽总出口大流量(m3/h)', name: 'FIT_2116B' ,align: 'center', minWidth: 105},"	
			+ "  { display: '软化水来水流量(m3/h)', name: 'FIT2201' ,align: 'center', minWidth: 85},"	
			+ "  { display: '稠油联合站去水流量(m3/h)', name: 'FIT2202' ,align: 'center', minWidth: 95},"	
			+ "  { display: '回收水流量(m3/h)', name: 'FIT2203' ,align: 'center', minWidth: 85},"	
			+ "  { display: '二次换热出油流量累计(m3)', name: 'FITQ_201' ,align: 'center', minWidth: 85},"	
			+ "  { display: '软化水出二次换热去SAGD区流量(m3/h)', name: 'FRC_2111' ,align: 'center', minWidth: 115},"	
			+ "  { display: '采出液预处理装置出口管线流量(m3/h)', name: 'FRQ_3102' ,align: 'center', minWidth: 115},"	
			+ "  { display: '采出液预处理装置出口管线流量累积(m3)', name: 'FRQ_3102_RS' ,align: 'center', minWidth: 125},"	
			+ "  { display: '原油进装置区管线流量计量累积A(m3)', name: 'FRQ_3201A' ,align: 'center', minWidth: 115},"	
			+ "  { display: '原油进装置区管线流量累积A(m3)', name: 'FRQ_3201A_RS' ,align: 'center', minWidth: 115},"	
			+ "  { display: '原油进装置区管线流量累积B(m3)', name: 'FRQ_3201B' ,align: 'center', minWidth: 105},"	
			+ "  { display: '原油进装置区管线流量累积C(m3)', name: 'FRQ_3201C' ,align: 'center', minWidth: 105},"	
			+ "  { display: '原油进装置区管线流量累积D(m3)', name: 'FRQ_3201D' ,align: 'center', minWidth: 105},"	
			+ "  { display: '热电化学联合脱水装置出口管线流量(m3/h)', name: 'FRQ_3202' ,align: 'center', minWidth: 125},"	
			+ "  { display: '热电化学联合脱水装置出口管线流量累积(m3)', name: 'FRQ_3202_RS' ,align: 'center', minWidth: 150},"	
			+ "  { display: '掺柴油泵出口管线流量(m3/h)', name: 'FRQC_3401' ,align: 'center', minWidth: 115},"	
			+ "  { display: '欲脱水装置（R-1-1）油水界面(m)', name: 'LIT_3101A' ,align: 'center', minWidth: 115},"	
			+ "  { display: '欲脱水装置（R-1-2）油水界面(m)', name: 'LIT_3101B' ,align: 'center', minWidth: 115},"	
			+ "  { display: 'M欲脱水装置（R-1-3）油水界面(m)', name: 'LIT_3101C' ,align: 'center', minWidth: 115},"	
			+ "  { display: '蒸汽分离器液位(m)', name: 'LRC_101_1' ,align: 'center', minWidth: 85},"	
			+ "  { display: '气液分离器A液位(m)', name: 'LRC_2101A' ,align: 'center', minWidth: 85},"	
			+ "  { display: '气液分离器B液位(m)', name: 'LRC_2101B' ,align: 'center', minWidth: 85},"	
			+ "  { display: '1#300方缓冲水罐液位(m)', name: 'LRC_2202A' ,align: 'center', minWidth: 95},"	
			+ "  { display: '2#300方缓冲水罐液位(m)', name: 'LRC_2202B' ,align: 'center', minWidth: 95},"	
			+ "  { display: '预脱水装置（R-1-1）油水界面液位(m)', name: 'LRC_3102A' ,align: 'center', minWidth: 125},"	
			+ "  { display: '预脱水装置（R-1-2）油水界面液位(m)', name: 'LRC_3102B' ,align: 'center', minWidth: 125},"	
			+ "  { display: '预脱水装置（R-1-3）油水界面液位(m)', name: 'LRC_3102C' ,align: 'center', minWidth: 125},"	
			+ "  { display: '预脱水装置（R-1-1）油相出口液位(m)', name: 'LRC_3103A' ,align: 'center', minWidth: 125},"	
			+ "  { display: '预脱水装置（R-1-2）油相出口液位(m)', name: 'LRC_3103B' ,align: 'center', minWidth: 125},"	
			+ "  { display: '预脱水装置（R-1-3）油相出口液位(m)', name: 'LRC_3103C' ,align: 'center', minWidth: 125},"	
			+ "  { display: '热化学脱水装置（R-2-1）油水界面液位(m)', name: 'LRC_3201A' ,align: 'center', minWidth: 150},"	
			+ "  { display: '热化学脱水装置(R-2-2)油水界面液位(m)', name: 'LRC_3201B' ,align: 'center', minWidth: 135},"	
			+ "  { display: '热化学脱水装置(R-2-3)油水界面液位(m)', name: 'LRC_3201C' ,align: 'center', minWidth: 135},"	
			+ "  { display: '电脱装置油水界面液位(m)', name: 'LRC_3201D' ,align: 'center', minWidth: 95},"	
			+ "  { display: '热化学脱水装置（R-2-1）油相出口液位(m)', name: 'LRC_3202A' ,align: 'center', minWidth: 150},"	
			+ "  { display: '热化学脱水装置（R-2-2）油相出口液位(m)', name: 'LRC_3202B' ,align: 'center', minWidth: 150},"	
			+ "  { display: '热化学脱水装置（R-2-3）油相出口液位(m)', name: 'LRC_3202C' ,align: 'center', minWidth: 150},"	
			+ "  { display: '电脱装置油相出口液位(m)', name: 'LRC_3202D' ,align: 'center', minWidth: 105},"	
			+ "  { display: '热化学脱水装置(R-3-1)油水界面液位(m)', name: 'LRC_3203A' ,align: 'center', minWidth: 150},"	
			+ "  { display: '热化学脱水装置（R-3-2）油水界面液位(m)', name: 'LRC_3203B' ,align: 'center', minWidth: 150},"	
			+ "  { display: '热化学脱水装置（R-3-3）油水界面液位(m)', name: 'LRC_3203C' ,align: 'center', minWidth: 150},"	
			+ "  { display: '3#分离器液位(m)', name: 'LRC_3FLQ' ,align: 'center', minWidth: 85},"	
			+ "  { display: '采出液冷却喷淋罐液位(m)', name: 'LRCS_102' ,align: 'center', minWidth: 95},"	
			+ "  { display: '混凝除油罐液位(m)', name: 'LRCS_103' ,align: 'center', minWidth: 95},"	
			+ "  { display: '事故池液位(m)', name: 'LRSA_3601' ,align: 'center', minWidth: 85},"	
			+ "  { display: '生产污水池液位(m)', name: 'LRSA_3602' ,align: 'center', minWidth: 95},"	
			+ "  { display: '一号原油提升泵变频反馈(Hz)', name: 'LV_2101AS' ,align: 'center', minWidth: 115},"	
			+ "  { display: '二号原油提升泵变频反馈(Hz)', name: 'LV_2101BS' ,align: 'center', minWidth: 115},"	
			+ "  { display: '热电化学脱水装置出口管线含水率A(%)', name: 'MR_3201A' ,align: 'center', minWidth: 135},"	
			+ "  { display: '热电化学脱水装置出口管线含水率B(%)', name: 'MR_3201B' ,align: 'center', minWidth: 135},"	
			+ "  { display: '热电化学脱水装置出口管线含水率C(%)', name: 'MR_3201C' ,align: 'center', minWidth: 135},"	
			+ "  { display: '电脱装置含水(%)', name: 'MR_3201D' ,align: 'center', minWidth: 85},"	
			+ "  { display: '原油含水分析仪(%)', name: 'MT_201' ,align: 'center', minWidth: 85},"	
			+ "  { display: 'SAGD来油管道压力(Mpa)', name: 'PR_3101' ,align: 'center', minWidth: 100},"	
			+ "  { display: '高效仰角预脱水器压力A（1#）(Mpa)', name: 'PR_3101A' ,align: 'center', minWidth: 135},"	
			+ "  { display: '高效仰角预脱水器压力B（2#）(Mpa)', name: 'PR_3101B' ,align: 'center', minWidth: 135},"	
			+ "  { display: '高效仰角预脱水器压力C（3#）(Mpa)', name: 'PR_3101C' ,align: 'center', minWidth: 135},"	
			+ "  { display: '预脱水装置出水管道压力(Mpa)', name: 'PR_3103' ,align: 'center', minWidth: 115},"	
			+ "  { display: '预脱水装置出油管道压力(Mpa)', name: 'PR_3104' ,align: 'center', minWidth: 115},"	
			+ "  { display: '热化学脱水器压力A(Mpa)', name: 'PR_3201A' ,align: 'center', minWidth: 115},"	
			+ "  { display: '热化学脱水器压力B(Mpa)', name: 'PR_3201B' ,align: 'center', minWidth: 115},"	
			+ "  { display: '热化学脱水器压力C(Mpa)', name: 'PR_3201C' ,align: 'center', minWidth: 115},"	
			+ "  { display: '装置出水进换热区汇管压力(Mpa)', name: 'PR_3202' ,align: 'center', minWidth: 135},"	
			+ "  { display: '装置出油进换热区汇管压力(Mpa)', name: 'PR_3203' ,align: 'center', minWidth: 135},"	
			+ "  { display: '除砂间入口管道压力(Mpa)', name: 'PR_3501' ,align: 'center', minWidth: 115},"	
			+ "  { display: '除砂间出口管道压力(Mpa)', name: 'PR_3502' ,align: 'center', minWidth: 115},"	
			+ "  { display: '循环预热阶段来液管道压力(Mpa)', name: 'PR_3902' ,align: 'center', minWidth: 135},"	
			+ "  { display: '采出液电脱水装置压力(Mpa)', name: 'PRA_3201D' ,align: 'center', minWidth: 135},"	
			+ "  { display: '二次换热器油总出口压力(Mpa)', name: 'PRC_2105' ,align: 'center', minWidth: 135},"	
			+ "  { display: '气液分离器总汽出口压力(Mpa)', name: 'PRC_2113' ,align: 'center', minWidth: 135},"	
			+ "  { display: '增压水泵总出口压力(Mpa)', name: 'PRC_2201' ,align: 'center', minWidth: 115},"	
			+ "  { display: '预脱水装置蒸汽出口管道压力(Mpa)', name: 'PRC_3102' ,align: 'center', minWidth: 135},"	
			+ "  { display: '采出水换热后去特1联管道压力(Mpa)', name: 'PRC_3301' ,align: 'center', minWidth: 135},"	
			+ "  { display: '热电化学脱水装置补气压力(Mpa)', name: 'PRC_3702' ,align: 'center', minWidth: 135},"	
			+ "  { display: '预脱水装置补气压力(Mpa)', name: 'PRC_3703' ,align: 'center', minWidth: 115},"	
			+ "  { display: '预脱水装置泄气压力(Mpa)', name: 'PRC_3704' ,align: 'center', minWidth: 115},"	
			+ "  { display: '热电化学脱水装置泄气压力(Mpa)', name: 'PRC_3705' ,align: 'center', minWidth: 135},"	
			+ "  { display: '电脱水装置补气压力(Mpa)', name: 'PRC_3706' ,align: 'center', minWidth: 115},"	
			+ "  { display: '电脱装置泄气压力(Mpa)', name: 'PRC_3707' ,align: 'center', minWidth: 115},"	
			+ "  { display: '正常生产阶段蒸汽出口管道压力(Mpa)', name: 'PRC_3901' ,align: 'center', minWidth: 150},"	
			+ "  { display: '3#分离器来液压力(Mpa)', name: 'PRC_3FLQ' ,align: 'center', minWidth: 85},"	
			+ "  { display: 'SAGD循环预热液分出蒸汽进换热器管道压力（1）(Mpa)', name: 'PT_101' ,align: 'center', minWidth: 160},"	
			+ "  { display: 'SAGD循环预热液分出蒸汽进换热器管道压力（2）(Mpa)', name: 'PT_102' ,align: 'center', minWidth: 160},"	
			+ "  { display: '清水软化水进换热器管道压力(Mpa)', name: 'PT_103' ,align: 'center', minWidth: 150},"	
			+ "  { display: '换热后液出换热器管道压力（支路2）(Mpa)', name: 'PT_104' ,align: 'center', minWidth: 160},"	
			+ "  { display: '换热后液出换热器管道压力（支路1）(Mpa)', name: 'PT_105' ,align: 'center', minWidth: 160},"	
			+ "  { display: '清水软化水出换热器管道压力(Mpa)', name: 'PT_106' ,align: 'center', minWidth: 150},"	
			+ "  { display: '蒸汽分离器来液压力(Mpa)', name: 'PT_201' ,align: 'center', minWidth: 135},"	
			+ "  { display: '蒸汽分离器来液压力(Mpa)', name: 'PT_201_1' ,align: 'center', minWidth: 135},"	
			+ "  { display: '蒸汽分离器液相出口压力(Mpa)', name: 'PT_202' ,align: 'center', minWidth: 135},"	
			+ "  { display: '蒸汽分离器来液压力(Mpa)', name: 'PT_202_1' ,align: 'center', minWidth: 135},"	
			+ "  { display: '蒸汽分离器蒸汽出口（阀后）压力(Mpa)', name: 'PT_204_1' ,align: 'center', minWidth: 160},"	
			+ "  { display: '一次换热器油总进口压力(Mpa)', name: 'PT_2101' ,align: 'center', minWidth: 150},"	
			+ "  { display: '一次换热器油总口压力(Mpa)', name: 'PT_2102' ,align: 'center', minWidth: 135},"	
			+ "  { display: '吞吐区软化水一次换热器总进口压力(Mpa)', name: 'PT_2103' ,align: 'center', minWidth: 160},"	
			+ "  { display: '吞吐区软化水一次换热器总出口压力(Mpa)', name: 'PT_2104' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水二次换热器总进口压力(Mpa)', name: 'PT_2106' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水二次换热器总出口压力(Mpa)', name: 'PT_2107' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水水水换热器总进口压力(Mpa)', name: 'PT_2108' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水水水换热器总出口压力(Mpa)', name: 'PT_2109' ,align: 'center', minWidth: 160},"	
			+ "  { display: '吞吐区软化水水水换热器总进口压力(Mpa)', name: 'PT_2110' ,align: 'center', minWidth: 160},"	
			+ "  { display: '吞吐区软化水水水换热器总出口压力(Mpa)', name: 'PT_2111' ,align: 'center', minWidth: 160},"	
			+ "  { display: '气液分离器总进口压力(Mpa)', name: 'PT_2112' ,align: 'center', minWidth: 150},"	
			+ "  { display: '气液分离器总液出口压力(Mpa)', name: 'PT_2114' ,align: 'center', minWidth: 150},"	
			+ "  { display: '3#蒸汽分离器气出口压力(Mpa)', name: 'PT_GE_3FLQ' ,align: 'center', minWidth: 150},"	
			+ "  { display: '蒸汽分离器压力(Mpa)', name: 'PTC_203_1' ,align: 'center', minWidth: 135},"	
			+ "  { display: '一号增压水泵变频反馈(Hz)', name: 'PV_2201AS' ,align: 'center', minWidth: 150},"	
			+ "  { display: '二号增压水泵变频反馈(Hz)', name: 'PV_2201BS' ,align: 'center', minWidth: 150},"	
			+ "  { display: '掺柴油泵变频器频率反馈(Hz)', name: 'SR_3401' ,align: 'center', minWidth: 150},"	
			+ "  { display: '一次换热器油总进口温度(℃)', name: 'TE_2101' ,align: 'center', minWidth: 150},"	
			+ "  { display: '一次换热器单座1#油出口温度(℃)', name: 'TE_2102' ,align: 'center', minWidth: 150},"	
			+ "  { display: '一次换热器单座2#油出口温度(℃)', name: 'TE_2103' ,align: 'center', minWidth: 150},"	
			+ "  { display: '一次换热器单座3#油出口温度(℃)', name: 'TE_2104' ,align: 'center', minWidth: 150},"	
			+ "  { display: '吞吐区软化水一次换热器总进口温度(℃)', name: 'TE_2107' ,align: 'center', minWidth: 160},"	
			+ "  { display: '吞吐区软化水单座一次换热器1#出口温度(℃)', name: 'TE_2108' ,align: 'center', minWidth: 160},"	
			+ "  { display: '吞吐区软化水单座一次换热器2#出口温度(℃)', name: 'TE_2109' ,align: 'center', minWidth: 160},"	
			+ "  { display: '吞吐区软化水单座一次换热器3#出口温度(℃)', name: 'TE_2110' ,align: 'center', minWidth: 160},"	
			+ "  { display: '吞吐区软化水单座一次换热器4#出口温度(℃)', name: 'TE_2111' ,align: 'center', minWidth: 160},"	
			+ "  { display: '吞吐区软化水一次换热器总出口温度(℃)', name: 'TE_2112' ,align: 'center', minWidth: 160},"	
			+ "  { display: '单座二次换热器1#油出口温度(℃)', name: 'TE_2113' ,align: 'center', minWidth: 150},"	
			+ "  { display: '单座二次换热器2#油出口温度(℃)', name: 'TE_2114' ,align: 'center', minWidth: 150},"	
			+ "  { display: '单座二次换热器3#油出口温度(℃)', name: 'TE_2115' ,align: 'center', minWidth: 150},"	
			+ "  { display: '单座二次换热器4#油出口温度(℃)', name: 'TE_2116' ,align: 'center', minWidth: 150},"	
			+ "  { display: '单座二次换热器5#油出口温度(℃)', name: 'TE_2117' ,align: 'center', minWidth: 150},"	
			+ "  { display: '软化除盐水二次换热器总进口温度(℃)', name: 'TE_2120' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水单座二次换热器1#出口温度(℃)', name: 'TE_2121' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水单座二次换热器2#出口温度(℃)', name: 'TE_2122' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水单座二次换热器3#出口温度(℃)', name: 'TE_2123' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水单座二次换热器4#出口温度(℃)', name: 'TE_2124' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水单座二次换热器5#出口温度(℃)', name: 'TE_2125' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水单座二次换热器6#出口温度(℃)', name: 'TE_2126' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水二次换热器总出口温度(℃)', name: 'TE_2127' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水水水换热器总进口温度(℃)', name: 'TE_2128' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水单座水水换热器1#出口温度(℃)', name: 'TE_2129' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水单座水水换热器2#出口温度(℃)', name: 'TE_2130' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水单座水水换热器3#出口温度(℃)', name: 'TE_2131' ,align: 'center', minWidth: 160},"	
			+ "  { display: '软化除盐水单座水水换热器4#出口温度(℃)', name: 'TE_2132' ,align: 'center', minWidth: 160},"	
			+ "  { display: '吞吐区软化水水水换热器总进口温度(℃)', name: 'TE_2134' ,align: 'center', minWidth: 160},"	
			+ "  { display: '吞吐区软化水单座水水换热器1#出口温度(℃)', name: 'TE_2135' ,align: 'center', minWidth: 160},"	
			+ "  { display: '吞吐区软化水单座水水换热器2#出口温度(℃)', name: 'TE_2136' ,align: 'center', minWidth: 160},"	
			+ "  { display: '吞吐区软化水单座水水换热器3#出口温度(℃)', name: 'TE_2137' ,align: 'center', minWidth: 160},"	
			+ "  { display: '吞吐区软化水单座水水换热器4#出口温度(℃)', name: 'TE_2138' ,align: 'center', minWidth: 160},"	
			+ "  { display: '吞吐区软化水水水换热器总出口温度(℃)', name: 'TE_2139' ,align: 'center', minWidth: 160},"	
			+ "  { display: '气液分离器总进口温度(℃)', name: 'TE_2140' ,align: 'center', minWidth: 150},"	
			+ "  { display: '气液分离器总液出口温度(℃)', name: 'TE_2141' ,align: 'center', minWidth: 150},"	
			+ "  { display: 'SAGD来油管道温度(℃)', name: 'TR_3101' ,align: 'center', minWidth: 150},"	
			+ "  { display: '预脱水装置出油管道温度A(℃)', name: 'TR_3101A' ,align: 'center', minWidth: 150},"	
			+ "  { display: '预脱水装置出油管道温度B(℃)', name: 'TR_3101B' ,align: 'center', minWidth: 150},"	
			+ "  { display: '预脱水装置出油管道温度C(℃)', name: 'TR_3101C' ,align: 'center', minWidth: 150},"	
			+ "  { display: '预脱水装置总出水管道温度(℃)', name: 'TR_3103' ,align: 'center', minWidth: 150},"	
			+ "  { display: '预脱水装置蒸汽出口管道温度(℃)', name: 'TR_3104' ,align: 'center', minWidth: 150},"	
			+ "  { display: '热化学脱水装置出油管道温度A(℃)', name: 'TR_3201A' ,align: 'center', minWidth: 150},"	
			+ "  { display: '热化学脱水装置出油管道温度B(℃)', name: 'TR_3201B' ,align: 'center', minWidth: 150},"	
			+ "  { display: '热化学脱水装置出油管道温度C(℃)', name: 'TR_3201C' ,align: 'center', minWidth: 150},"	
			+ "  { display: '电脱装置出油管道温度(℃)', name: 'TR_3201D' ,align: 'center', minWidth: 150},"	
			+ "  { display: '装置总出水进换热区汇管温度(℃)', name: 'TR_3202' ,align: 'center', minWidth: 150},"	
			+ "  { display: '装置总出油进换热区汇管温度(℃)', name: 'TR_3203' ,align: 'center', minWidth: 150},"	
			+ "  { display: '除砂间入口管道温度(℃)', name: 'TR_3501' ,align: 'center', minWidth: 115},"	
			+ "  { display: '除砂间出口管道温度(℃)', name: 'TR_3502' ,align: 'center', minWidth: 115},"	
			+ "  { display: '循环预热阶段来液管道温度(℃)', name: 'TR_3901' ,align: 'center', minWidth: 150},"	
			+ "  { display: '一次换热器油总出口温度(℃)', name: 'TRC_2106' ,align: 'center', minWidth: 150},"	
			+ "  { display: '二次换热器油总出口温度(℃)', name: 'TRC_2119' ,align: 'center', minWidth: 150},"	
			+ "  { display: '软化除盐水水水换热器总出口温度(℃)', name: 'TRC_2133' ,align: 'center', minWidth: 150},"	
			+ "  { display: '软化清水进水水换热器区温度记录(℃)', name: 'TRC_3301' ,align: 'center', minWidth: 150},"	
			+ "  { display: 'SAGD循环预热液分出蒸汽进换热器管道温度1(℃)', name: 'TT_101' ,align: 'center', minWidth: 160},"	
			+ "  { display: 'SAGD循环预热液分出蒸汽进换热器管道温度2(℃)', name: 'TT_102' ,align: 'center', minWidth: 160},"	
			+ "  { display: 'SAGD循环预热液分出蒸汽进换热器管道温度3(℃)', name: 'TT_103' ,align: 'center', minWidth: 160},"	
			+ "  { display: 'SAGD循环预热液分出蒸汽进换热器管道温度4(℃)', name: 'TT_104' ,align: 'center', minWidth: 160},"	
			+ "  { display: '换热后液出换热器管道温度（支路1）(℃)', name: 'TT_105' ,align: 'center', minWidth: 150},"	
			+ "  { display: '换热后液出换热器管道温度（支路2）(℃)', name: 'TT_106' ,align: 'center', minWidth: 150},"	
			+ "  { display: '换热后液出换热器管道温度（支路3）(℃)', name: 'TT_107' ,align: 'center', minWidth: 150},"	
			+ "  { display: '换热后液出换热器管道温度（支路4）(℃)', name: 'TT_108' ,align: 'center', minWidth: 150},"	
			+ "  { display: '净水软化水进换热器管道温度（支路1）(℃)', name: 'TT_111' ,align: 'center', minWidth: 150},"	
			+ "  { display: '净水软化水进换热器管道温度（支路2）(℃)', name: 'TT_112' ,align: 'center', minWidth: 150},"	
			+ "  { display: '净水软化水出换热器管道温度（支路1）(℃)', name: 'TT_115' ,align: 'center', minWidth: 150},"	
			+ "  { display: '蒸汽分离器来液温度(℃)', name: 'TT_201' ,align: 'center', minWidth: 95},"	
			+ "  { display: '蒸汽分离器来液温度(℃)', name: 'TT_201_1' ,align: 'center', minWidth: 95},"	
			+ "  { display: '蒸汽分离器液相出口温度(℃)', name: 'TT_202' ,align: 'center', minWidth: 115},"	
			+ "  { display: '蒸汽分离器来液温度(℃)', name: 'TT_202_1' ,align: 'center', minWidth: 95},"	
			+ "  { display: '蒸汽分离器蒸汽出口温度(℃)', name: 'TT_203_1' ,align: 'center', minWidth: 115},"	
			+ "  { display: '1#空气压缩机压力(Mpa)', name: 'YSJ_P1' ,align: 'center', minWidth: 85},"	
			+ "  { display: '2#空气压缩机压力(Mpa)', name: 'YSJ_P2' ,align: 'center', minWidth: 85},"	
			+ "  { display: '1#空气压缩机温度(℃)', name: 'YSJ_T1' ,align: 'center', minWidth: 85},"	
			+ "  { display: '2#空气压缩机温度(℃)', name: 'YSJ_T2' ,align: 'center', minWidth: 85}"	
				+ " ],"+
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'u1mbdt_searchDatas.action',"+	
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"selectRowButtonOnly:true,"+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"delayLoad :true, "+
				"rownumbers:true ,"+
				"frozen:true, "+
				"headerRowHeight:50, "+
				"checkbox :false";
		
	// Ip规划大表
	public static final String IPGHDB_PAGE_GRID = " columns: ["
			+ "  { display: '分段ID', name: 'segment_id', align: 'center', width: 100, minWidth: 60 ,hide: true},"
			+ "  { display: '一分段代码', name: 'segment_code1', align: 'center', width: 100, minWidth: 60,frozen:true},"
			+ "  { display: '二分段代码', name: 'segment_code2', align: 'center', width: 100, minWidth: 60,frozen:true },"
			+ "  { display: '分段名称', name: 'segment_name',align: 'center', minWidth: 120  ,frozen:true},"
			+ "  { display: 'IP段', name: 'ip_segment',align: 'center', minWidth: 120},"
			+ "  { display: '起始IP', name: 'ip_start' ,align: 'center', minWidth: 120},"
			+ "  { display: '结束IP', name: 'ip_end',align: 'center', minWidth: 120},"
			+ "  { display: '使用状态', name: 'is_used'  ,align: 'center', minWidth: 120},"
			+ "  { display: '已使用地址数', name: 'used_count'  ,align: 'center', minWidth: 120},"
			+ "  { display: '说明', name: 'explanation'  ,align: 'center', minWidth: 150},"
			+ "  { display: '备注', name: 'remark',align: 'center',minWidth: 120},"
			+ "  { display: '上次操作者', name: 'rlast_oper'  ,align: 'center', minWidth: 150},"
			+ "  { display: '上次操作日期', name: 'rlast_odate',align: 'center',minWidth: 160}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'ipghdb_searchIpghdb.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
		
		
		//IP使用表
		public static final String IP_USED_T=" columns: ["
			+" { display :'IP', name: 'ip' ,align : 'center' ,minWidth :130,frozen : true},"
			+" { display :'所属对象类型', name: 'category' ,align : 'center' ,minWidth :80},"
			+" { display :'区块名称', name: 'area' ,align : 'center' ,minWidth :80},"
			+" { display :'所属对象', name: 'unit' ,align : 'center' ,minWidth :100},"
			+" { display: '产能设计年', name:'design_date',align: 'center', minWidth: 100},"
			+" { display :'设备大类名', name: 'instru_1tn' ,align : 'center' ,minWidth :100},"
			+" { display :'设备名称', name: 'instrumentation_name' ,align : 'center' ,minWidth :100},"
			+" { display :'使用网口序号', name: 'ip_no' ,align : 'center' ,minWidth :100},"
			+" { display :'驱动程序', name: 'device' ,align : 'center' ,minWidth :80},"
			+" { display :'二级接口', name: 'interface2th' ,align : 'center' ,minWidth :80},"
			+" { display :'所属二分段代码', name: 'segment_code2' ,align : 'center' ,minWidth :110},"
			+" { display :'使用状态', name: 'is_used' ,align : 'center' ,minWidth :80},"
			+" { display :'开始使用日期', name: 'start_date' ,align : 'center' ,minWidth :80},"
			+" { display :'停用日期', name: 'stop_date' ,align : 'center' ,minWidth :80},"
			+" { display :'备注', name: 'remark' ,align : 'center' ,minWidth :80},"
			+" { display :'VPN使用IP地址', name: 'vpn_ip' ,align : 'center' ,minWidth :80},"
			+" { display :'上次操作者', name: 'rlast_oper' ,align : 'center' ,minWidth :100},"
			+" { display :'上次操作日期', name: 'rlast_odate' ,align : 'center' ,minWidth :160},"
			
			+" { display :'对象ID', name: 'org_id' ,align : 'center' ,minWidth :160,hide :true},"
			+" { display :'所属对象类型代码', name: 'object_code' ,align : 'center' ,minWidth :160,hide:true},"
			+" { display :'设备名代码', name: 'instru_clc' ,align : 'center' ,minWidth :160,hide:true},"
			+" { display :'区块ID', name: 'qkid' ,align : 'center' ,minWidth :100,hide:true},"
			+" { display :'设备大类名ID', name: 'instru_1tc' ,align : 'center' ,minWidth :100,hide:true}"
			+ " ],"+
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'ipuse_searchDatas.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			//"headerRowHeight:50, "+
			"checkbox :false,"+
		"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
		
		//常规油井日报
		public static final String RULEWELL_PAGE_GRID = " columns: ["
			+ "  { display: '井号', name: 'JHMC',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '日期', name: 'REPORT_DATE',align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '单位', name: 'JHMC_S',align: 'center', minWidth: 60},"
			+ "  { display: '班组', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '管汇', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
			+ "  { display: '生产时间(t)', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 70},"
			+ "  { display: '冲程(m)', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
			+ "  { display: '冲次(/min)', name: 'BRANCHINGID' ,align: 'center', minWidth: 60},"
			+ "  { display: '油嘴(mm)', name: 'SAGDID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			+ "  { display: '油压(Mpa)', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
			+ "  { display: '套压(Mpa)', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			
			+ "  { display: '油温(℃)', name: 'XJTINME' ,align: 'center', minWidth: 150},"
			+ "  { display: '日产液量(t)', name: 'SCHOUR' ,align: 'center', minWidth: 60},"
		
			+ "  { display: '取样(油水)', name: 'XJTINME' ,align: 'center', minWidth: 150},"
			+ "  { display: '抽油机运转时间(t)', name: 'SCHOUR' ,align: 'center', minWidth: 60},"
			+ "  { display: '抽油机故障时间(t)', name: 'XJTINME' ,align: 'center', minWidth: 150},"
			+ "  { display: '抽油机故障描述', name: 'SCHOUR' ,align: 'center', minWidth: 60},"
			+ "  { display: '备注', name: 'XJTINME' ,align: 'center', minWidth: 150},"
			+ "  { display: '班组审核人', name: 'SCHOUR' ,align: 'center', minWidth: 60},"
			+ "  { display: '班组审核时间', name: 'XJTINME' ,align: 'center', minWidth: 150},"
			+ "  { display: '地质组审核人', name: 'SCHOUR' ,align: 'center', minWidth: 60},"
			+ "  { display: '地质组审核时间', name: 'XJTINME' ,align: 'center', minWidth: 150},"
			+ "  { display: '最后修改人', name: 'SCHOUR' ,align: 'center', minWidth: 60},"
			+ "  { display: '最后修改时间', name: 'XJTINME' ,align: 'center', minWidth: 150}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'ruleWellRPD_searchRuleRPD.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"delayLoad :true, "+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
		
		//常规油井日报维护  //,hide: true
		public static final String RULEWELL_WH_PAGE_GRID = " columns: ["
			+ "  { display: '井号', name: 'WELL_NAME',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '日期', name: 'REPORT_DATE',align: 'center', minWidth: 90,frozen:true},"
			+ "  { display: '单位', name: 'OILSTATIONNAME',align: 'center', minWidth: 90},"
			+ "  { display: '班组', name: 'TEAMNAME' ,align: 'center', minWidth: 90},"
			+ "  { display: '转油站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 90},"
			+ "  { display: '管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 90},"
			+ "  { display: '生产时间(h)', name: 'PROC_TIME_RATION' ,align: 'center', minWidth: 90},"
			+ "  { display: '冲程(m)', name: 'STROKE' ,align: 'center', minWidth: 90},"
			+ "  { display: '冲次(/min)', name: 'JIG_FREQUENCY' ,align: 'center', minWidth: 90},"
			+ "  { display: '油嘴(mm)', name: 'FLOW_NIPPLE', align: 'center', minWidth: 90},"
			+ "  { display: '油压(Mpa)', name: 'TUBING_PRES', align: 'center', minWidth: 90},"
			+ "  { display: '套压(Mpa)', name: 'CASING_PRES', align: 'center',  minWidth: 90},"
			+ "  { display: '回压(Mpa)', name: 'BACK_PRES', align: 'center',  minWidth: 90},"
			+ "  { display: '油温(℃)', name: 'OIL_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '日产液量(t)', name: 'LIQUID_OUTPUT' ,align: 'center', minWidth: 90},"
			+" { display: '产液量标志', name: 'LIQUID_FLAG' ,align: 'center', minWidth: 150,hide:true},"
			+ "  { display: '日产气量(m3)', name: 'GAS_OUTPUT' ,align: 'center', minWidth: 90},"
			+ "  { display: '取样(油水)', name: 'SAMPLING' ,align: 'center', minWidth: 90},"
			+ "  { display: '抽油机运转时间(t)', name: 'RUNTIME' ,align: 'center', minWidth: 90},"
			+ "  { display: '抽油机故障时间(t)', name: 'PUMP_ERROR_TIME' ,align: 'center', minWidth: 90},"
			+ "  { display: '抽油机故障描述', name: 'PUMP_ERROR_DESC' ,align: 'center', minWidth: 150},"
			+ "  { display: '备注', name: 'REMARK' ,align: 'center', minWidth: 150},"
			+ "  { display: '班组审核人', name: 'CLASS_CHECK_OPER' ,align: 'center', minWidth: 90},"
			+ "  { display: '班组审核时间', name: 'CLASS_CHECK_DATE' ,align: 'center', minWidth: 150},"
			+ "  { display: '地质组审核人', name: 'GEOLOGY_CHECK_OPER' ,align: 'center', minWidth: 90},"
			+ "  { display: '地质组审核时间', name: 'GEOLOGY_CHECK_DATE' ,align: 'center', minWidth: 150},"
			+ "  { display: '最后修改人', name: 'RLAST_OPER' ,align: 'center', minWidth: 90},"
			+ "  { display: '最后修改时间', name: 'RLAST_ODATE' ,align: 'center', minWidth: 150},"
			+ "  { display: '井号组织结构ID ', name: 'RULE_ORG_ID' ,align: 'center', minWidth: 60,hide: true},"
			+ "  { display: '班组ID', name: 'TEAM_ORG_ID' ,align: 'center', minWidth: 60,hide: true},"
			+ "  { display: '转油站ID', name: 'BLOCKSTATIONID' ,align: 'center', minWidth: 60,hide: true},"
			+ "  { display: '管汇ID', name: 'GH_ID', align: 'center',  minWidth: 60,hide: true},"
			+ "  { display: '稠油井日报ID', name: 'RPD_RULE_WELL_PROD_ID', align: 'center',  minWidth: 60,hide: true}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'ruleWellRPD_searchRuleRPD.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"delayLoad :true, "+
			"frozen:true, "+
			"checkbox :false, "+
			"onSelectRow: function (rowdata, rowindex) "+
			"{fromAu(rowdata);} ";
		
		//气井日报数据
		public static final String GAS_WELL_RPD_T=" columns: ["
			+" { display :'气井日报数据ID', name: 'GAS_WELLDID' ,align : 'center' ,minWidth :130,hide : true},"
			+" { display :'气井ID', name: 'GAS_WELLID' ,align : 'center' ,minWidth :80 , hide : true},"
			+" { display :'井名', name: 'well_name' ,align : 'center' ,minWidth :80 , frozen : true},"
			+" { display :'日期', name: 'REPORT_DATE' ,align : 'center' ,minWidth :80 , frozen : true},"
			+" { display :'采油站', name: 'oilname' ,align : 'center' ,minWidth :100 },"
			+" { display :'气站', name: 'qzname' ,align : 'center' ,minWidth :100 },"
			+" { display :'井区块', name: 'qkmc' ,align : 'center' ,minWidth :100 },"
			+" { display :'油压', name: 'TUBING_PRES' ,align : 'center' ,minWidth :80 },"
			+" { display :'井口温度', name: 'WELL_TEMP' ,align : 'center' ,minWidth :80 },"
			+" { display :'瞬时流量', name: 'INSTANTANEOUS_DELIVERY' ,align : 'center' ,minWidth :80 },"
			+" { display :'累积流量', name: 'CUMULATIVE_DISCHARGE' ,align : 'center' ,minWidth :80 },"
			+" { display :'日累积流量', name: 'DALIY_CUMULATIVE_FLOW' ,align : 'center' ,minWidth :80 }"
			+"],"+
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'gasRPD_searchGWRPD.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"delayLoad :true, "+
			"frozen:true, "+
			//"headerRowHeight:50, "+
			"checkbox :false,"+
		"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
		
		//注水井日报维护数据
		public static final String FloodingRpd_T=" columns: ["
			+" { display :'注水井日报数据ID', name: 'wellrpdid' ,align : 'center' ,minWidth :130,hide : true},"
			+" { display :'注水井ID', name: 'wforgID' ,align : 'center' ,minWidth :80 , hide : true},"
			+" { display :'单位', name: 'oilname' ,align : 'center' ,minWidth :100 },"
			+" { display :'班组', name: 'team' ,align : 'center' ,minWidth :100 },"
			
			+" { display :'注水撬', name: 'injectname' ,align : 'center' ,minWidth :100 },"
			+" { display :'井号', name: 'wellName' ,align : 'center' ,minWidth :100 , frozen : true},"
			+" { display :'日期', name: 'report_date' ,align : 'center' ,minWidth :80 , frozen : true},"
			+" { display :'注汽/注水小时h', name: 'zqzwater' ,align : 'center' ,minWidth :100 },"
			
			+ "{display: '注汽/注水压力Mpa', columns:["
			+" { display :'配汽间/泵压', name: 'pqjby' ,align : 'center' ,minWidth :100 },"
			+" { display :'井口/油压', name: 'jkyy' ,align : 'center' ,minWidth :100 },"
			+" { display :'套压', name: 'ty' ,align : 'center' ,minWidth :100 }"
			+"]},"
			+ "{display: '注汽温度℃', columns:["
			+" { display :'配汽间', name: 'pqj' ,align : 'center' ,minWidth :100 },"
			+" { display :'井口', name: 'jk' ,align : 'center' ,minWidth :100 }"
			+"]},"
			+ "{display: '注水量m3', columns:[" 
			+" { display :'配注量', name: 'pzl' ,align : 'center' ,minWidth :100 },"
			+" { display :'日注量', name: 'rzl' ,align : 'center' ,minWidth :100 }"
			+"]},"
			+" { display: '产液量标志', name: 'liquid_flag' ,align: 'center', minWidth: 150,hide:true},"
			+" { display :'备注', name: 'remark' ,align : 'center' ,minWidth :100 },"
			+ "  { display: '班组审核人', name: 'class_check_oper' ,align: 'center', minWidth: 90},"
			+ "  { display: '班组审核时间', name: 'class_check_date' ,align: 'center', minWidth: 150},"
			+ "  { display: '地质组审核人', name: 'geology_check_oper' ,align: 'center', minWidth: 90},"
			+ "  { display: '地质组审核时间', name: 'geology_check_date' ,align: 'center', minWidth: 150},"
			+ "  { display: '最后修改人', name: 'rlast_oper' ,align: 'center', minWidth: 90},"
			+ "  { display: '最后修改时间', name: 'rlast_odate' ,align: 'center', minWidth: 150}"
				+"],"+
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'floodingRPD_searchwaterFLRRD.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"delayLoad :true, "+
			//"headerRowHeight:50, "+
			"checkbox :false,"+
		"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
		
		
		//注汽井日报维护数据
		public static final String GASDAILYWH_T=" columns: ["
			+" { display :'注汽井日报数据ID', name: 'gaswellrpdid' ,align : 'center' ,minWidth :130,hide : true},"
			+" { display :'注水井ID', name: 'org_id' ,align : 'center' ,minWidth :80 , hide : true},"
			+" { display :'单位', name: 'oilname' ,align : 'center' ,minWidth :100 },"
			+" { display :'班组', name: 'team' ,align : 'center' ,minWidth :100 },"
			+" { display :'注转站', name: 'stationname' ,align : 'center' ,minWidth :100 },"
			+" { display :'管汇', name: 'maniname' ,align : 'center' ,minWidth :100 },"
			+" { display :'井号', name: 'well_name' ,align : 'center' ,minWidth :100 , frozen : true},"
			+" { display :'日期', name: 'report_date' ,align : 'center' ,minWidth :80 , frozen : true},"
			+" { display :'注汽/注水小时h', name: 'zqzwater' ,align : 'center' ,minWidth :100 },"
			+ "{display: '注汽/注水压力Mpa', columns:["
			+" { display :'配汽间/泵压', name: 'pqjby' ,align : 'center' ,minWidth :100 },"
			+" { display :'井口/油压', name: 'jkyy' ,align : 'center' ,minWidth :100 },"
			+" { display :'套压', name: 'ty' ,align : 'center' ,minWidth :100 }"
			+"]},"
			+ "{display: '注汽温度℃', columns:["
			+" { display :'配汽间', name: 'pqj' ,align : 'center' ,minWidth :100 },"
			+" { display :'井口', name: 'jk' ,align : 'center' ,minWidth :100 }"
			+"]},"
			+ "{display: '注水量m3', columns:[" 
			+" { display :'配注量', name: 'pzl' ,align : 'center' ,minWidth :100 },"
			+" { display :'日注量', name: 'rzl' ,align : 'center' ,minWidth :100 }"
			+"]},"
			+" { display: '产液量标志', name: 'liquid_flag' ,align: 'center', minWidth: 150,hide:true},"
			+" { display :'备注', name: 'remark' ,align : 'center' ,minWidth :100 },"
			+ "  { display: '班组审核人', name: 'class_check_oper' ,align: 'center', minWidth: 90},"
			+ "  { display: '班组审核时间', name: 'class_check_date' ,align: 'center', minWidth: 150},"
			+ "  { display: '地质组审核人', name: 'geology_check_oper' ,align: 'center', minWidth: 90},"
			+ "  { display: '地质组审核时间', name: 'geology_check_date' ,align: 'center', minWidth: 150},"
			+ "  { display: '最后修改人', name: 'rlast_oper' ,align: 'center', minWidth: 90},"
			+ "  { display: '最后修改时间', name: 'rlast_odate' ,align: 'center', minWidth: 150}"
				+"],"+
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'gasDailyWH_searchRRDData.action',"+	
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"delayLoad :true, "+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			//"headerRowHeight:50, "+
			"checkbox :false,"+
		"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
		
		//稀油油井日报维护 
		public static final String THINWELL_WH_PAGE_GRID = " columns: ["
			+ "  { display: '井号', name: 'well_name',align: 'center', minWidth: 60,frozen:true},"
			+ "  { display: '日期', name: 'report_date',align: 'center', minWidth: 90,frozen:true},"
			+ "  { display: '单位', name: 'oilname',align: 'center', minWidth: 90}," //采油站
			+ "  { display: '班组', name: 'team' ,align: 'center', minWidth: 70},"
			//+ "  { display: '转油站', name: 'stationName' ,align: 'center', minWidth: 60},"
			+ "  { display: '管汇', name: 'maniname' ,align: 'center', minWidth: 60},"
			+ "  { display: '生产时间(h)', name: 'prott' ,align: 'center', minWidth: 80},"
			+ "  { display: '冲程(m)', name: 'stroke' ,align: 'center', minWidth: 80},"
			+ "  { display: '冲次(/min)', name: 'at_times' ,align: 'center', minWidth: 80},"
			+ "  { display: '油嘴(mm)', name: 'nozzle', align: 'center',  minWidth: 80 },"
			+ "  { display: '油压(Mpa)', name: 'pressure', align: 'center', minWidth: 80},"
			+ "  { display: '套压(Mpa)', name: 'tcpr', align: 'center', minWidth: 80 },"
			+ "  { display: '回压(Mpa)', name: 'backpre', align: 'center', minWidth: 80 },"
			+ "  { display: '油温(℃)', name: 'tot' ,align: 'center', minWidth: 80},"
			+ "  { display: '日产液量(t)', name: 'nfv' ,align: 'center', minWidth: 80},"
			+ "  { display: '日产气量(m3)', name: 'gasp' ,align: 'center', minWidth: 80},"
			+ "  { display: '取样(油水)', name: 'sampli' ,align: 'center', minWidth: 80},"
			+ "  { display: '抽油机运转时间(h)', name: 'pumping_time' ,align: 'center', minWidth: 80},"
			+ "  { display: '抽油机故障时间(h)', name: 'pumping_machine' ,align: 'center', minWidth: 80},"
			+ "  { display: '抽油机故障描述', name: 'pumping_description' ,align: 'center', minWidth: 150},"
			+ "  { display: '产液量标志', name: 'liquid_flag' ,align: 'center', minWidth: 150,hide:true},"
			+ "  { display: '备注', name: 'remark' ,align: 'center', minWidth: 150},"
			+ "  { display: '稀油油井日报维护ID', name: 'thinwellrpd', align: 'center',  minWidth: 60 ,hide: true},"
			+ "  { display: '稀油井ID', name: 'wellid', align: 'center',  minWidth: 60 ,hide: true},"
			+ "  { display: '班组审核人', name: 'class_check_oper' ,align: 'center', minWidth: 90},"
			+ "  { display: '班组审核时间', name: 'class_check_date' ,align: 'center', minWidth: 150},"
			+ "  { display: '地质组审核人', name: 'geology_check_oper' ,align: 'center', minWidth: 90},"
			+ "  { display: '地质组审核时间', name: 'geology_check_date' ,align: 'center', minWidth: 150},"
			+ "  { display: '最后修改人', name: 'rlast_oper' ,align: 'center', minWidth: 90},"
			+ "  { display: '最后修改时间', name: 'rlast_odate' ,align: 'center', minWidth: 150}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'thinWellRPDWH_searchThinoRRD.action',"+	
//			"pageParmName :'pageNo',"+	
//			"sortnameParmName:'sort',"+
//			"sortorderParmName: 'order',  "+
//			"pagesizeParmName:'rowsPerpage', "+
//			"pageSize:30 ,"+
//			"selectRowButtonOnly:true,"+
//			"sePaper:true,"+
//			"rownumbers:true ,"+
//			"frozen:true, "+
//			"checkbox :false, "+
//			"onSelectRow: function (rowdata, rowindex) "+
//			"{fromAu(rowdata);} ";
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"delayLoad :true, "+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			//"headerRowHeight:50, "+
			"checkbox :false,"+
		"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
		
		//采出日报审核统计
		public static final String CCRBSHTJ_PAGE_GRID = " columns: ["
			+ "  { display: '单位', name: 'OILNAME',align: 'center', minWidth: 80}," //采油站
			+ "  { display: '班组', name: 'TEAM' ,align: 'center', minWidth: 90},"
			+ "  { display: '日期', name: 'REPORT_DATE',align: 'center', minWidth: 90},"
			+ "  { display: '总井数', name: 'WELLSUM' ,align: 'center', minWidth: 60},"
			+ "  { display: '对比', name: 'WELLSUM_COMPARE' ,align: 'center', minWidth: 60},"
			+ "  { display: '开井数', name: 'OPENWELL' ,align: 'center', minWidth: 60},"
			+ "  { display: '对比', name: 'OPENWELL_COMPARE' ,align: 'center', minWidth: 60},"
			+ "  { display: '产液量(t)', name: 'LIQUID', align: 'center',  minWidth: 60 },"
			+ "  { display: '对比', name: 'LIQUID_COMPARE', align: 'center', minWidth: 60},"
			+ "  { display: '班组审核状态', name: 'SH_STAUTS', align: 'center',  minWidth: 60},"
			+ "  { display: '地质组审核状态', name: 'DZ_STAUTS', align: 'center',  minWidth: 60}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'cczr_searchCCDatas.action',"+	
//			"pageParmName :'pageNo',"+
//			"sortnameParmName:'sort',"+
//			"sortorderParmName: 'order',  "+
//			"pagesizeParmName:'rowsPerpage', "+
//			"sePaper:true,"+
			"usePager : false,"+
			"pageSize:200 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"delayLoad :true, "+
			"checkbox :false";
		
		//注入日报审核统计
		public static final String ZRRBSHTJ_PAGE_GRID = " columns: ["
			+ "  { display: '单位', name: 'OILNAME',align: 'center', minWidth: 80}," //采油站
			+ "  { display: '班组', name: 'TEAM' ,align: 'center', minWidth: 90},"
			+ "  { display: '日期', name: 'REPORT_DATE',align: 'center', minWidth: 90},"
			+ "  { display: '总井数', name: 'WELLSUM' ,align: 'center', minWidth: 60},"
			+ "  { display: '对比', name: 'WELLSUM_COMPARE' ,align: 'center', minWidth: 60},"
			+ "  { display: '开井数', name: 'OPENWELL' ,align: 'center', minWidth: 60},"
			+ "  { display: '对比', name: 'OPENWELL_COMPARE' ,align: 'center', minWidth: 60},"
			+ "  { display: '注入/注汽量(t)', name: 'LIQUID', align: 'center',  minWidth: 80 },"
			+ "  { display: '对比', name: 'LIQUID_COMPARE', align: 'center', minWidth: 60},"
			+ "  { display: '班组审核状态', name: 'SH_STAUTS', align: 'center',  minWidth: 60},"
			+ "  { display: '地质组审核状态', name: 'DZ_STAUTS', align: 'center',  minWidth: 60}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'cczr_searchZRDatas.action',"+	
//			"pageParmName :'pageNo',"+
//			"sortnameParmName:'sort',"+
//			"sortorderParmName: 'order',  "+
//			"pagesizeParmName:'rowsPerpage', "+
//			"sePaper:true,"+
			"usePager : false,"+
			"pageSize:200 ,"+
			"rownumbers:true ,"+
			"delayLoad :true, "+
			"frozen:true, "+
			"checkbox :false";
		
		//关井维护
		public static final String GJWH_PAGE_GRID = " columns: ["
				+ "  { display: '井号', name: 'WELL_ID' ,align: 'center', minWidth: 100,frozen:true},"
				+ "  { display: '关井原因', name: 'DOWNTIME_NAME' ,align: 'center', minWidth: 200},"
				+ "  { display: '关井时间', name: 'START_DATETIME' ,align: 'center', minWidth: 150},"	
				+ "  { display: '开井时间', name: 'END_DATETIME' ,align: 'center', minWidth: 150},"
				+ "  { display: '关井重点标志', name: 'KEY_DOWN_TAG' ,align: 'center', minWidth: 60},"
				+ "  { display: '备注', name: 'REMARK' ,align: 'center', minWidth: 150},"
				+ "  { display: '上次操作者', name: 'RLAST_OPER' ,align: 'center', minWidth: 90},"
				+ "  { display: '上次操作日期', name: 'RLAST_ODATE' ,align: 'center', minWidth: 150},"
	
				+ "  { display: '井号组织结构id', name: 'ORG_ID' ,align: 'center', minWidth: 90,hide: true},"
				+ "  { display: '关井代码', name: 'DOWNTIME_TYPE' ,ALIGN: 'center', minWidth: 90,hide: true},"
				+ "  { display: '关井数据id', name: 'CLOSING_WELL_ID	' ,align: 'center', minWidth: 90,hide: true}"
				+ " ],"+
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'gjwh_searchDatas.action',"+	
				"delayLoad :true, "+
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"rownumbers:true ,"+
				"frozen:true, "+
				"checkbox :false, "+
				"onSelectRow: function (rowdata, rowindex) "+
			 "{fromAu(rowdata);} ";
//		//关井维护B
//		public static final String GJWHB_PAGE_GRID = " columns: ["
//				+ "  { display: '井号', name: 'WELLNAME' ,align: 'center', minWidth: 100,frozen:true},"
//				+ "  { display: '关井原因', name: 'DOWNTIME_NAME' ,align: 'center', minWidth: 200},"
//				+ "  { display: '关井时间', name: 'START_DATETIME' ,align: 'center', minWidth: 150},"	
//				+ "  { display: '开井时间', name: 'END_DATETIME' ,align: 'center', minWidth: 150},"
//				+ "  { display: '关井重点标志', name: 'KEY_DOWN_TAG' ,align: 'center', minWidth: 60},"
//				+ "  { display: '备注', name: 'REMARK' ,align: 'center', minWidth: 150},"
//				+ "  { display: '上次操作者', name: 'RLAST_OPER' ,align: 'center', minWidth: 90},"
//				+ "  { display: '上次操作日期', name: 'RLAST_ODATE' ,align: 'center', minWidth: 150},"
//				
//				+ "  { display: '审核人', name: 'CHECK_OPER' ,align: 'center', minWidth: 90},"
//				+ "  { display: '审核时间', name: 'CHECK_DATE' ,align: 'center', minWidth: 90},"
//				+ "  { display: '报表日期', name: 'REPORT_DATE' ,align: 'center', minWidth: 90},"
//				
//				+ "  { display: '井号组织结构id', name: 'ORG_ID' ,align: 'center', minWidth: 90,hide: true},"
//				+ "  { display: '关井代码', name: 'DOWNTIME_TYPE' ,align: 'center', minWidth: 90,hide: true},"
//				+ "  { display: '关井数据id', name: 'CLOSING_WELL_ID	' ,align: 'center', minWidth: 90,hide: true}"
//				+ " ],"+
//		
//				"height:'100%',"+
//				"dataAction: 'server',"+
//				"url:'gjwh_searchDatas.action?tableid=B',"+	
//				"delayLoad :true, "+
//				"pageParmName :'pageNo',"+
//				"sortnameParmName:'sort',"+
//				"sortorderParmName: 'order',  "+
//				"pagesizeParmName:'rowsPerpage', "+
//				"sePaper:true,"+
//				"pageSize:30 ,"+
//				"rownumbers:true ,"+
//				"frozen:true, "+
//				"checkbox :false, "+
//				"onSelectRow: function (rowdata, rowindex) "+
//			 "{fromAu(rowdata);} ";
		
		
		//措施维护
		public static final String CSWH_PAGE_GRID = " columns: ["
			+ "  { display: '井号', name: 'WELLNAME' ,align: 'center', minWidth: 140,frozen:true},"
			//+ "  { display: '采油站', name: 'oil_station_name' ,align: 'center', minWidth: 140,hide: true},"
			//+ "  { display: '班组', name: 'team_station_name' ,align: 'center', minWidth: 140,hide: true},"
			+ "  { display: '措施日期开始', name: 'START_DATETIME' ,align: 'center', minWidth: 140},"
			+ "  { display: '措施完工日期', name: 'END_DATETIME' ,align: 'center', minWidth: 140},"	
			+ "  { display: '措施代码(id)', name: 'PKCODE' ,align: 'center', minWidth: 140,hide: true},"
			+ "  { display: '措施代码', name: 'P_DESCRIPTION' ,align: 'center', minWidth: 140},"
			
			+ "  { display: '措施井重点标志', name: 'KEY_DOWN_TAG' ,align: 'center', minWidth: 140},"
			+ "  { display: '备注', name: 'REMARK' ,align: 'center', minWidth: 140},"
			+ "  { display: '上次操作者', name: 'RLAST_OPER' ,align: 'center', minWidth: 140},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE' ,align: 'center', minWidth: 140},"
			
			//+ "  { display: '采油站ID', name: 'oil_satation_id' ,align: 'center', minWidth: 140,hide: true},"
			//+ "  { display: '班组ID', name: 'team_satation_id' ,align: 'center', minWidth: 140,hide: true},"
			+ "  { display: '井号组织结构ID', name: 'ORG_ID' ,align: 'center', minWidth: 90,hide: true},"
			+ "  { display: '措施数据ID', name: 'MEASURE_ID' ,align: 'center', minWidth: 90,hide: true}"
			+ " ],"+
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'cswh_searchDatas.action',"+		
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"delayLoad :true, "+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			//"headerRowHeight:50, "+
			"checkbox :false,"+
		"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";

		//热洗清蜡
		public static final String RXGLWH_PAGE_GRID = " columns: ["
			+ "  { display: '井号', name: 'WELLNAME' ,align: 'center', minWidth: 140,frozen:true},"
			//+ "  { display: '采油站', name: 'oil_station_name' ,align: 'center', minWidth: 140,hide: true},"
			//+ "  { display: '班组', name: 'team_station_name' ,align: 'center', minWidth: 140,hide: true},"
			+ "  { display: '日期', name: 'EVENT_DATE' ,align: 'center', minWidth: 140},"
			+ "  { display: '维护工作类型(id)', name: 'PARAFFIN_STIM_TYPE' ,align: 'center', minWidth: 140,hide: true},"	
			+ "  { display: '维护工作类型', name: 'WHTYPE' ,align: 'center', minWidth: 140},"
			+ "  { display: '热洗方式(id)', name: 'HOT_WASH_TYPE' ,align: 'center', minWidth: 140,hide: true},"
			+ "  { display: '热洗方式', name: 'XRFSWH' ,align: 'center', minWidth: 140},"
			+ "  { display: '溶蜡液温度', name: 'PARAFFIN_MELT_TEMP' ,align: 'center', minWidth: 140},"
			
			+ "  { display: '化学刮蜡液量', name: 'PARAFFIN_MELT_VOL' ,align: 'center', minWidth: 140},"
			+ "  { display: '备注', name: 'REMARK' ,align: 'center', minWidth: 140},"
			+ "  { display: '上次操作者', name: 'RLAST_OPER' ,align: 'center', minWidth: 140},"
			+ "  { display: '上次操作日期', name: 'RLAST_ODATE' ,align: 'center', minWidth: 140},"
			
			//+ "  { display: '采油站ID', name: 'oil_satation_id' ,align: 'center', minWidth: 140,hide: true},"
			//+ "  { display: '班组ID', name: 'team_satation_id' ,align: 'center', minWidth: 140,hide: true},"
			+ "  { display: '井号组织结构ID', name: 'ORG_ID' ,align: 'center', minWidth: 90,hide: true},"
			+ "  { display: '自喷刮蜡ID', name: 'HOTWASH_PARAFFIN_ID' ,align: 'center', minWidth: 90,hide: true}"
			+ " ],"+
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'rxqlwh_searchDatas.action',"+		
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"delayLoad :true, "+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			//"headerRowHeight:50, "+
			"checkbox :false,"+
		"onSelectRow: function (rowdata, rowindex) "+
		 "{fromAu(rowdata);} ";
		
		
		
		//自喷刮蜡
		public static final String ZPGL_PAGE_GRID = " columns: ["
				+ "  { display: '井号', name: 'WELL_ID' ,align: 'center', minWidth: 100,frozen:true},"
				+ "  { display: '日期', name: 'EVENT_DATE' ,align: 'center', minWidth: 150},"	
				+ "  { display: '维护工作类型', name: 'PARAFFIN_STIM_NAME' ,align: 'center', minWidth: 120},"
				+ "  { display: '刮蜡片深度', name: 'WAX_CUT_DEPTH' ,align: 'center', minWidth: 60},"
				+ "  { display: '刮蜡片直径', name: 'PARAFFIN_CUTTER_DIAMETER' ,align: 'center', minWidth: 60},"
				+ "  { display: '刮蜡次数', name: 'PARAFFIN_CUTTING_TIMES' ,align: 'center', minWidth: 60},"
				+ "  { display: '化学刮蜡液量', name: 'CHEM_PARAFFIN_CUTTING_VOL' ,align: 'center', minWidth: 60},"
				+ "  { display: '备注', name: 'REMARK' ,align: 'center', minWidth: 150},"
				+ "  { display: '上次操作者', name: 'RLAST_OPER' ,align: 'center', minWidth: 90},"
				+ "  { display: '上次操作日期', name: 'RLAST_ODATE' ,align: 'center', minWidth: 150},"

				+ "  { display: '井号组织结构id', name: 'ORG_ID' ,align: 'center', minWidth: 90,hide: true},"
				+ "  { display: '维护工作类型code', name: 'PARAFFIN_STIM_TYPE' ,ALIGN: 'center', minWidth: 90,hide: true},"
				+ "  { display: '自喷刮蜡数据id', name: 'FLOWING_PARAFFIN_ID	' ,align: 'center', minWidth: 90,hide: true}"
				+ " ],"+
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'zpgl_searchDatas.action',"+	
				"delayLoad :true, "+
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"rownumbers:true ,"+
				"frozen:true, "+
				"checkbox :false, "+
				"onSelectRow: function (rowdata, rowindex) "+
			 "{fromAu(rowdata);} ";
		
		
		//除氧器动态数据
		public static final String CYQRD_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '水处理站', name: 'SCLZMC' ,align: 'center', minWidth: 150},"
			+ "  { display: '除氧器编号', name: 'CYQBH' ,align: 'center', minWidth: 50},"
			+ "  { display: '真空度(MPa)', name: 'ZKD', align: 'center', width: 200, minWidth: 90},"
			+ "  { display: '出口压力(MPa)', name: 'CYQ_CKYL' ,align: 'center', minWidth: 90},"
			+ "  { display: '出水流量1(m3/h)', name: 'CYQ_DCLL1' ,align: 'center', minWidth: 90},"
			+ "  { display: '出水流量2(m3/h)', name: 'CYQ_DCLL2', align: 'center',minWidth: 90}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'jzsclrd_searchCyqDatas.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
		
		//脱氧塔动态数据
		public static final String TYTRD_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '水处理站', name: 'SCLZMC' ,align: 'center', minWidth: 150},"
			+ "  { display: '脱氧塔', name: 'TYTBH' ,align: 'center', minWidth: 50},"
			+ "  { display: '除氧塔液位(m)', name: 'W1LT', align: 'center',  minWidth: 70},"
			+ "  { display: '供水泵频率(Hz)', name: 'W1YO' ,align: 'center', minWidth: 70},"
			+ "  { display: '出水流量(m³/h)', name: 'W1FT' ,align: 'center', minWidth: 70},"
			+ "  { display: '循环泵压力(MPa)', name: 'XH_PUMP_P', align: 'center',minWidth: 70},"
			
			+ "  { display: '供水泵压力(MPa)', name: 'GS_PUMP_P', align: 'center',  minWidth: 70},"
			+ "  { display: '增压泵压力(MPa)', name: 'ZY_PUMP_P' ,align: 'center', minWidth: 70},"
			+ "  { display: '增压泵频率(Hz)', name: 'ZY_PUMP_FREQ', align: 'center',minWidth: 70}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'jzsclrd_searchTytDatas.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
		//软化器动态数据
		public static final String RHQRD_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '水处理站', name: 'SCLZMC' ,align: 'center', minWidth: 150},"
			+ "  { display: '软化器编号', name: 'RHQBH' ,align: 'center', minWidth: 50},"
			+ "  { display: 'A组瞬时流量(m3/h)', name: 'SCL_ALL', align: 'center',  minWidth: 70},"
			+ "  { display: 'A组累计流量(m3/h)', name: 'SCL_ALLLJ' ,align: 'center', minWidth: 70},"
			+ "  { display: 'B组瞬时流量(m3/h)', name: 'SCL_BLL', align: 'center',  minWidth: 70},"
			+ "  { display: 'B组累计流量(m3/h)', name: 'SCL_BLLLJ' ,align: 'center', minWidth: 70},"
			+ "  { display: 'C组瞬时流量(m3/h)', name: 'SCL_CLL', align: 'center',  minWidth: 70},"
			+ "  { display: 'C组累计流量(m3/h)', name: 'SCL_CLLLJ' ,align: 'center', minWidth: 70},"
			+ "  { display: 'D组瞬时流量(m3/h)', name: 'SCL_DLL', align: 'center',  minWidth: 70},"
			+ "  { display: 'D组累计流量(m3/h)', name: 'SCL_DLLLJ' ,align: 'center', minWidth: 70}"
		
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'jzsclrd_searchRhqDatas.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
		
		//特二联水处理动态数据
		public static final String T2SCLRD_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '软化器总出水量', name: 'RHQZCSL' ,align: 'center', minWidth: 70},"
			+ "  { display: '除氧器总出水量', name: 'CYQZCSL' ,align: 'center', minWidth: 70},"
			+ "  { display: '增压泵总出水量', name: 'ZYBZCSL', align: 'center',  minWidth: 70},"
			+ "  { display: '净化水生水罐液位', name: 'LT_30101A' ,align: 'center', minWidth: 70},"
			+ "  { display: '净化水软水罐液位', name: 'LT_30101B', align: 'center',  minWidth: 70},"
			+ "  { display: '清水生水罐液位', name: 'LT_30102A' ,align: 'center', minWidth: 70},"
			+ "  { display: '清水软水罐液位', name: 'LT_30102B', align: 'center',  minWidth: 70},"
			+ "  { display: '高盐池液位', name: 'LT_30301' ,align: 'center', minWidth: 70},"
			+ "  { display: '低盐池液位', name: 'LT_30302', align: 'center',  minWidth: 70},"
			+ "  { display: '一期增压泵压力', name: 'ZYBYL1' ,align: 'center', minWidth: 70},"
			+ "  { display: '二期增压泵压力', name: 'ZYBYL2' ,align: 'center', minWidth: 70},"
			+ "  { display: '一期压缩空气压力', name: 'YSKQYL1' ,align: 'center', minWidth: 70},"
			+ "  { display: '二期压缩空气压力', name: 'YSKQYL2' ,align: 'center', minWidth: 70}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'jzsclrd_searchT2sclDatas.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
		
		//集中水处理动态数据
		public static final String JZSCLRD_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '软化器总出水量', name: 'RHQZCSL' ,align: 'center', minWidth: 70},"
			+ "  { display: '除氧塔总出水量', name: 'CYTZCSL' ,align: 'center', minWidth: 70},"
			+ "  { display: '1号水罐液位', name: 'LT401', align: 'center',  minWidth: 70},"
			+ "  { display: '2号水罐液位', name: 'LT402' ,align: 'center', minWidth: 70},"
			+ "  { display: '清水生水罐液位', name: 'LT102', align: 'center',  minWidth: 70},"
			+ "  { display: '清水软水罐液位', name: 'LT101' ,align: 'center', minWidth: 70},"
			+ "  { display: '外排池液位', name: 'LT403', align: 'center',  minWidth: 70},"
			+ "  { display: '一号增压泵压力', name: 'ZYBYL1' ,align: 'center', minWidth: 70},"
			+ "  { display: '二号增压泵压力', name: 'ZYBYL2', align: 'center',  minWidth: 70},"
			+ "  { display: '三号增压泵压力', name: 'ZYBYL3' ,align: 'center', minWidth: 70},"
			+ "  { display: '压缩空气压力', name: 'PI_201' ,align: 'center', minWidth: 70}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'jzsclrd_searchJzsclDatas.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
		
		//四合一水处理动态数据
		public static final String SHYSCLRD_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '软化器总出水量', name: 'RHQZCSL' ,align: 'center', minWidth: 70},"
			+ "  { display: '除氧器总出水量', name: 'CYQZCSL' ,align: 'center', minWidth: 70},"
			+ "  { display: '增压泵总出水量', name: 'ZYBZCSL', align: 'center',  minWidth: 70},"
			+ "  { display: '净化水生水罐液位', name: 'LT_30101A' ,align: 'center', minWidth: 70},"
			+ "  { display: '净化水软水罐液位', name: 'LT_30101B', align: 'center',  minWidth: 70},"
			+ "  { display: '清水生水罐液位', name: 'LT_30102A' ,align: 'center', minWidth: 70},"
			+ "  { display: '清水软水罐液位', name: 'LT_30102B', align: 'center',  minWidth: 70},"
			+ "  { display: '高盐池液位', name: 'LT_30301' ,align: 'center', minWidth: 70},"
			+ "  { display: '低盐池液位', name: 'LT_30302', align: 'center',  minWidth: 70},"
			+ "  { display: '一期增压泵压力', name: 'ZYBYL1' ,align: 'center', minWidth: 70},"
			+ "  { display: '二期增压泵压力', name: 'ZYBYL2' ,align: 'center', minWidth: 70},"
			+ "  { display: '三期增压泵压力', name: 'ZYBYL3' ,align: 'center', minWidth: 70},"
			+ "  { display: '一期压缩空气压力', name: 'YSKQYL1' ,align: 'center', minWidth: 70},"
			+ "  { display: '二期压缩空气压力', name: 'YSKQYL2' ,align: 'center', minWidth: 70},"
			+ "  { display: '三期压缩空气压力', name: 'YSKQYL3' ,align: 'center', minWidth: 70}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'jzsclrd_searchShysclDatas.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
		
		//管网管汇动态数据
		public static final String GWGHRD_PAGE_GRID = " columns: ["
			+ "  { display: '管汇名称', name: 'NETWORK_MD_NAME' ,align: 'center', minWidth: 120,frozen:true},"
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '接转站', name: 'STATION_NAME' ,align: 'center', minWidth: 120},"
			+ "  { display: '采油站', name: 'OIL_STATION_NAME' ,align: 'center', minWidth: 120},"
			+ "  { display: '压力', name: 'PRESS' ,align: 'center', minWidth: 120},"
			+ "  { display: '温度', name: 'TEMP' ,align: 'center', minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'jzsclrd_searchGwghDatas.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
		
		//阀池动态数据
		public static final String FCRD_PAGE_GRID = " columns: ["
			+ "  { display: '阀池名称', name: 'CLIQUE_POOL_NAME' ,align: 'center', minWidth: 120,frozen:true},"
			+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '接转站', name: 'STATION_NAME' ,align: 'center', minWidth: 120},"
			+ "  { display: '采油站', name: 'OIL_STATION_NAME', align: 'center',  minWidth: 120},"
			+ "  { display: '阀池类型', name: 'CLIQUE_TYPE' ,align: 'center', minWidth: 120},"
			+ "  { display: '阀池压力', name: 'PRESS' ,align: 'center', minWidth: 120}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'jzsclrd_searchFcrdDatas.action',"+	
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";
		
		
		//管网管汇基础信息
		public static final String GWGHJCXX_PAGE_GRID = " columns: ["
			+ "  { display: '管汇名称', name: 'PIPE_NETWORK' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '管汇编码', name: 'PIPE_CODE' ,align: 'center', minWidth: 70},"
			+ "  { display: '所属注转站', name: 'STATIONNAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '所属注转站编码', name: 'STATIONCODE', align: 'center',  minWidth: 100},"
			+ "  { display: '采油站名称', name: 'OILNAME' ,align: 'center', minWidth: 110},"
			+ "  { display: '区块名称', name: 'QKMC', align: 'center',  minWidth: 70},"
			//+ "  { display: '区块名称(注转站)', name: 'QKSTATION', align: 'center',  minWidth: 70},"
			+ "  { display: '区类名称', name: 'QKLB' ,align: 'center', minWidth: 70},"
			+ "  { display: 'RTU编号', name: 'RTU_CODE', align: 'center',  minWidth: 70},"
			+ "  { display: '接入标志ID', name: 'SWITCH_IN_FLAG' ,align: 'center', minWidth: 70,hide:true},"
			+ "  { display: '接入标志', name: 'JRBZ' ,align: 'center', minWidth: 70},"
			+ "  { display: '服务器逻辑节点名ID', name: 'SCADA_NODE', align: 'center',  minWidth: 120,hide:true},"
			+ "  { display: '服务器逻辑节点名', name: 'LJJD', align: 'center',  minWidth: 120},"
			+ "  { display: '安装日期', name: 'INSTALL_DATE' ,align: 'center', minWidth: 100},"
			//+ "  { display: 'IP', name: 'ZYBYL2' ,align: 'center', minWidth: 70},"
			//+ "  { display: '站号', name: 'ZYBYL3' ,align: 'center', minWidth: 70},"
			+ "  { display: '上次操作者', name: 'RLAST_OPER' ,align: 'center', minWidth: 100},"
			+ "  { display: '上次操作时间', name: 'RLAST_ODATE' ,align: 'center', minWidth: 160},"
			+ "  { display: '备注', name: 'REMARK' ,align: 'center', minWidth: 120},"
			+ "  { display: '管网管汇ID', name: 'NETWORK_MD_ID' ,align: 'center', minWidth: 70,hide :true},"
			+ "  { display: '组织结构ID', name: 'ORG_ID' ,align: 'center', minWidth: 70,hide :true},"
			+ "  { display: '所属注转站ID', name: 'STATIONID' ,align: 'center', minWidth: 70 ,hide :true}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'gwgh_searchAllData.action',"+	
			//"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false,"+
			"onSelectRow: function (rowdata, rowindex) "+
			"{fromAu(rowdata);} ";
		
		
		//站库系统信息管理
		
		public static final String ZKXTXX_PAGE_GRID = " columns: ["
				+ " { display: '对象类型', name: 'OBJECT_NAME',align: 'center', minWidth: 90  ,frozen:true},"
				+ " { display: '所属单位', name: 'COMUNIT',align: 'center', minWidth: 90},"
				+ " { display: '接入标识', name: 'ACCESS_SIGNS',align: 'center', minWidth: 90},"
				+ " { display: '区块名称', name: 'QKMC',align: 'center', minWidth: 90},"
				+ " { display: '单元名', name: 'UNIT_NAME',align: 'center', minWidth: 90},"
				+ " { display: '系统代码', name: 'SYSTEM_CODE',align: 'center', minWidth: 90},"
				+ " { display: '下位软件型号', name: 'DOWN_SOFTWARE_MODEL',align: 'center', minWidth: 100},"
				+ " { display: '下位软件厂家', name: 'DOWN_SOFTWARE_NAME',align: 'center', minWidth: 100},"
				+ " { display: '上位软件型号', name: 'UPPER_SOFTWARE_MODEL',align: 'center', minWidth: 100},"
				+ " { display: '上位软件厂家', name: 'UPPER_SOFTWARE_NAME',align: 'center', minWidth: 100},"
				+ " { display: '集成商厂家 ', name: 'INTEGRATOR_NAME' ,align: 'center', minWidth: 100},"
				+ "  { display: '服务器节点名', name: 'LJJD_S',align: 'center', minWidth: 70},"
				+ " { display: '描述标识', name: 'REMARK',align: 'center',minWidth: 120},"
				+ "  { display: '创建时间', name: 'CREATE_DATE',align: 'center', minWidth: 90},"
				+ "  { display: '上次操作者', name: 'RLAST_OPER',align: 'center', minWidth: 90},"
				+ "  { display: '上次操作日期', name: 'RLAST_ODATE',align: 'center', minWidth: 135},"

				+ " { display: '服务器ID', name: 'LJJD_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
				+ " { display: '下位软件厂家ID', name: 'DOWN_SOFTWARE_FACTURER', align: 'center', width: 200, minWidth: 60,hide: true},"
				+ " { display: '上位软件厂家ID', name: 'UPPER_SOFTWARE_FACTURER', align: 'center', width: 200, minWidth: 60,hide: true},"
				+ " { display: '集成商厂家ID', name: 'INTEGRATOR_CODE', align: 'center', width: 200, minWidth: 60,hide: true},"
				+ " { display: '对象类型ID', name: 'OBJECT_CODE', align: 'center', width: 200, minWidth: 60,hide: true},"
				+ " { display: '所属单位ID', name: 'COMBINATION_STATIONID', align: 'center', width: 200, minWidth: 60,hide: true},"
				+ " { display: '大型站库类型ID', name: 'LARGE_STATION_ID', align: 'center', width: 200, minWidth: 60 ,hide: true}"
				+ " ],"+
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'zkxtxx_searchDatas.action',"+
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"selectRowButtonOnly:true,"+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"rownumbers:true ,"+
				"frozen:true, "+
				"checkbox :false, "+
				"onSelectRow: function (rowdata, rowindex) "+
			 "{fromAu(rowdata);} ";
		
//井站采集点信息管理
		
		public static final String JZCJDXX_PAGE_GRID = " columns: ["
				+ " { display: '对象类型', name: 'OBJECT_TYPE_NAME',align: 'center', minWidth: 70  ,frozen:true},"
				+ " { display: '对象类型代码(ID)', name: 'OBJECT_CODE',align: 'center', minWidth: 90  ,hide:true},"
				
				+ " { display: '点描述', name: 'POINT_NAME', align: 'center',  minWidth: 120},"
				+ " { display: '界面统一描述', name: 'INTERFACE_REMARK', align: 'center',  minWidth: 100},"
				+ " { display: '是否接入', name: 'ACCESS_SIGNS',align: 'center',minWidth: 30},"
			//	+ " { display: '点类型', name: 'POINT_ORDER', align: 'center',  minWidth: 100},"
			//	+ " { display: '点类型(ID)', name: 'POINT_TYPE', align: 'center',  minWidth: 100,hide: true},"
				+ " { display: '点类型', name: 'POINT_TYPE', align: 'center',  minWidth: 30},"
				
				+ " { display: '量程上限', name: 'RANGES_UPPER', align: 'center',  minWidth: 50 },"
				+ " { display: '量程下限', name: 'RANGES_DOWN', align: 'center',  minWidth: 50 },"
				+ " { display: '单位', name: 'IFIX_UNIT', align: 'center',  minWidth: 40},"
				
				+ " { display: '是否控制', name: 'CONTROL_OR', align: 'center',  minWidth: 30},"
				//历史曲线启用否
				+ " { display: '是否曲线', name: 'HISTORY_CURVE', align: 'center',  minWidth: 30 },"
				//关系数据库存储
				+ " { display: '是否存储', name: 'ORACLE_SAVE', align: 'center',  minWidth: 30 },"
				+ " { display: '是否报警', name: 'ALARM_OR', align: 'center',  minWidth: 30},"
				
				//+ " { display: '报警级别代码(ID)', name: 'ALARM_CODE', align: 'center',  minWidth: 100,hide: true },"
				+ " { display: '报警级别代码', name: 'ALARM_VALUE', align: 'center',  minWidth: 40 },"
				
				+ " { display: '报警低低限', name: 'ALARM_LIMITLL',align: 'center',minWidth: 50},"
				+ " { display: '报警低限', name: 'ALARM_LIMITL',align: 'center', minWidth: 50},"
				+ " { display: '报警高限', name: 'ALARM_LIMITH',align: 'center', minWidth: 50},"
				+ " { display: '报警高高限', name: 'ALARM_LIMITHH',align: 'center', minWidth: 55},"
				+ " { display: '建点时间', name: 'CREATE_DATE', align: 'center',  minWidth: 80 },"
				+ " { display: '备注', name: 'REMARK', align: 'center',  minWidth: 100 },"
			
				
				+ " { display: '接入控制器(ID)', name: 'TORTU_CODE',align: 'center',minWidth: 120, hide: true},"
				+ " { display: '接入控制器', name: 'JRKZQ',align: 'center',minWidth: 100},"
				
				+ " { display: '1次关联设备(ID)', name: 'INSTRUMENT_CALLED',align: 'center', minWidth: 140 ,hide :true},"
				+ " { display: '1次关联设备', name: 'YCGLSB',align: 'center',minWidth: 100},"
				
				+ " { display: '点代码', name: 'POINT_CODE', align: 'center',  minWidth: 60},"
				+ " { display: '厂家数据类型', name: 'DATA_TYPE',align: 'center', minWidth: 50},"
				+ " { display: '厂家数据地址', name: 'PLC_ADDRESS',align: 'center', minWidth: 50},"
				+ " { display: '厂家点描述', name: 'POINT_DESC' ,align: 'center', minWidth: 100},"
				+ " { display: '厂家值描述', name: 'POINT_REMARK',align: 'center', minWidth: 90},"
				+ " { display: '值缩放比', name: 'VALUE_RATIO',align: 'center', minWidth: 50},"
				+ " { display: 'Modbus地址', name: 'MAILING_ADDRESS',align: 'center',minWidth: 60},"
				+ " { display: 'Modbus地址B', name: 'MAILING_ADDRESSB',align: 'center',minWidth: 60},"
			
				+ " { display: '厂2数据类型', name: 'DATA_TYPE2',align: 'center', minWidth: 50},"
				+ " { display: '厂2数据地址', name: 'PLC_ADDRESS2',align: 'center', minWidth: 60},"
				+ " { display: '厂2点描述', name: 'POINT_DESC2' ,align: 'center', minWidth: 100},"
				+ " { display: '厂2值描述', name: 'POINT_REMARK2',align: 'center', minWidth: 90},"
				+ " { display: '值缩放比2', name: 'VALUE_RATIO2',align: 'center', minWidth: 50},"
				+ " { display: 'Modbus地址2', name: 'MAILING_ADDRESS2',align: 'center',minWidth: 60},"
				+ " { display: 'Modbus地址B2', name: 'MAILING_ADDRESSB2',align: 'center',minWidth: 60},"
	
				+ " { display: '更新人', name: 'RLAST_OPER', align: 'center',  minWidth: 70 },"
				+ " { display: '更新时间', name: 'RLAST_ODATE', align: 'center',  minWidth: 130 },"
				+ " { display: '大型站库类型ID', name: 'SMALL_STATION_ID', align: 'center',  minWidth: 100,hide:true }"
				+ " ],"+
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'jzcjdxx_searchDatas.action',"+
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"selectRowButtonOnly:true,"+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"rownumbers:true ,"+
				"frozen:true, "+
				"checkbox :false, "+
				"headerRowHeight:50, "+
				"onSelectRow: function (rowdata, rowindex) "+
			 "{fromAu(rowdata);} ";
		
//站库采集点管理
		
		public static final String ZKCJD_PAGE_GRID = " columns: ["
				+ " { display: '系统代码', name: 'SYSTEM_CODE',align: 'center', minWidth: 90,frozen:true},"
				+ " { display: '系统名称', name: 'SYSTEM_NAME',align: 'center', minWidth: 200},"
				+ " { display: '标签名', name: 'TAG_NAME',align: 'center', minWidth: 90},"
				+ " { display: '描述', name: 'REMARK',align: 'center', minWidth: 110},"
				+ " { display: '是否接入', name: 'ACCESS_SIGNS',align: 'center', minWidth: 60},"
				+ " { display: '点类型', name: 'POINT_ORDER',align: 'center', minWidth: 100},"
				+ " { display: '点类型(ID)', name: 'POINT_TYPE', align: 'center',  minWidth: 100,hide: true},"
				+ " { display: '单位', name: 'UNIT',align: 'center', minWidth: 100},"
				+ " { display: '创建时间', name: 'CREATE_DATE',align: 'center', minWidth: 100},"
				+ " { display: '流程代码(id)', name: 'FLOW_CODE',align: 'center', minWidth: 100, hide:true},"
				+ " { display: '流程名称', name: 'FLOW_NAME',align: 'center', minWidth: 100},"
				+ " { display: '量程下限 ', name: 'RANGES_DOWN' ,align: 'center', minWidth: 100},"
				+ " { display: '量程上限', name: 'RANGES_UPPER',align: 'center', minWidth: 70},"
				+ " { display: '报警限值低低', name: 'ALARM_LIMITLL',align: 'center',minWidth: 120},"
				+ " { display: '报警限值低', name: 'ALARM_LIMITL',align: 'center', minWidth: 90},"
				+ " { display: '报警限值高', name: 'ALARM_LIMITH',align: 'center', minWidth: 90},"
				+ " { display: '报警限值高高', name: 'ALARM_LIMITHH',align: 'center', minWidth: 135},"
				+ " { display: '报警否', name: 'ALARM_OR', align: 'center',  minWidth: 60},"
				+ " { display: '报警级别代码(ID)', name: 'ALARM_CODE', align: 'center',  minWidth: 100,hide: true },"
				+ " { display: '报警级别代码', name: 'ALARM_VALUE', align: 'center',  minWidth: 100 },"
				+ " { display: '设备代码(ID)', name: 'DEVICE_CODE', align: 'center', width: 200, minWidth: 60,hide: true },"
				+ " { display: '设备名称', name: 'DEVICE_NAME', align: 'center',  minWidth: 60},"
				+ " { display: '是否控制', name: 'CONTROL_OR', align: 'center',  minWidth: 60},"
				+ " { display: '是否启用历史曲线', name: 'HISTORY_CURVE', align: 'center',  minWidth: 100},"
				+ " { display: 'ORACLE库是否存储', name: 'ORACLE_SAVE', align: 'center',  minWidth: 100},"
				+ " { display: '备注', name: 'BEIZHU', align: 'center', width: 200, minWidth: 60},"
				+ " { display: '上次操作者', name: 'RLAST_OPER', align: 'center', width: 200, minWidth: 60},"
				+ " { display: '上次操作日期', name: 'RLAST_ODATE', align: 'center', width: 200, minWidth: 60 },"
				+ " { display: '站库点ID', name: 'STATION_POINT_ID', align: 'center', width: 200, minWidth: 60,hide:true }"
				+ " ],"+
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'zkcjd_searchDatas.action',"+
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"selectRowButtonOnly:true,"+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"rownumbers:true ,"+
				"frozen:true, "+
				"checkbox :false, "+
				"onSelectRow: function (rowdata, rowindex) "+
			 "{fromAu(rowdata);} ";
	//	数据通断检查统计表
		public static final String SJTDJCTJB_PAGE_GRID = " columns: ["
			+ " { display: '检查时间', name: 'CHECK_DATE',align: 'center', minWidth: 90  ,frozen:true},"
			+ " { display: '对象类型代码', name: 'OBJECT_CODE',align: 'center', minWidth: 110  ,frozen:true},"
			+ " { display: '对象类型名', name: 'OBJECT_TYPE_NAME',align: 'center', minWidth: 90  ,frozen:true},"
			+ " { display: '对象总数', name: 'OTOTAL_NUM', align: 'center',  minWidth: 20},"
			+ " { display: '未接入数', name: 'NOACCESS_NUM', align: 'center',  minWidth: 20},"
			+ " { display: '不具备网络数', name: 'NONETO_NUM',align: 'center',minWidth: 20},"
			+ " { display: '已经接入数', name: 'HAACCESS_NUM', align: 'center',  minWidth: 20},"
			+ " { display: '接入占比', name: 'HAACCESS_RATIO', align: 'center',  minWidth: 50 },"
			+ " { display: '数据中断对象数', name: 'DATAAOBJ_NUM', align: 'center',  minWidth: 50 },"
			+ " { display: '当天无数据对象数', name: 'DANONEOBJ_NUM', align: 'center',  minWidth: 40},"
			+ " { display: '数据异常对象数', name: 'DABNOOBJ_NUM', align: 'center',  minWidth: 30},"
			+ " { display: '数据异常占比', name: 'DABNOOBJ_RATIO', align: 'center',  minWidth: 30 },"
			+ " { display: '当天数据中断现象描述', name: 'DATAAOBJ_DESC', align: 'center',  minWidth: 300 },"
			+ " { display: '当天无数据对象描述', name: 'DANONEOBJ_DESC', align: 'center',  minWidth: 200 },"
			+ " { display: '不具备网络对象描述', name: 'NONETO_DESC',align: 'center',minWidth: 50},"
			+ " { display: '服务器状态描述', name: 'SERVER_DES',align: 'center', minWidth: 50}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sjtdjctjb_searchDatas.action',"+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"headerRowHeight:50 ";
			//"onSelectRow: function (rowdata, rowindex) "+
		// "{fromAu(rowdata);} ";
		
	//数据通断检查动态表
		public static final String SJTDJCDTB_PAGE_GRID = " columns: ["
			+ " { display: '检查时间', name: 'CHECK_DATE',align: 'center', minWidth: 90  ,frozen:true},"
			+ " { display: '动态数据表名', name: 'RTABLE_NAME',align: 'center', minWidth: 110  ,frozen:true},"
			+ " { display: '对象类型编码', name: 'OBJECT_CODE',align: 'center', minWidth: 90  ,frozen:true},"
			+ " { display: '对象名称', name: 'STRUCTURENAME', align: 'center',  minWidth: 50},"
			+ " { display: '单位名称', name: 'DWMC', align: 'center',  minWidth: 50},"
			+ " { display: '区块名称', name: 'QKMC',align: 'center',minWidth: 50},"
			+ " { display: '站库名称', name: 'BLOCKSTATION_NAME', align: 'center',  minWidth: 20},"
			+ " { display: '生产状态', name: 'PROD_SNS', align: 'center',  minWidth: 50 },"
			+ " { display: '接入状态', name: 'DESCRIPTION', align: 'center',  minWidth: 50 },"
			+ " { display: '检查数据单元数', name: 'CHECK_UNITNUM', align: 'center',  minWidth: 40},"
			+ " { display: '检查数据单元1描述', name: 'CHECK_UNITDS1', align: 'center',  minWidth: 30},"
			+ " { display: '检查数据单元1选择列的名称', name: 'CHECK_SFIELDNA1', align: 'center',  minWidth: 30 },"
			+ " { display: '检查数据单元1选择列的描述', name: 'CHECK_SFIELDDS1', align: 'center',  minWidth: 200 },"
			+ " { display: '单元1记为1次中断的连续中断条数', name: 'CHECK_INTERCON1', align: 'center',  minWidth: 20 },"
			+ " { display: '数据单元1中断次数', name: 'DATA_INTERNUM1',align: 'center',minWidth: 50},"
			+ " { display: '数据单元1中断累计时长', name: 'DATA_INTERACT1',align: 'center', minWidth: 50},"
			
			+ " { display: '检查数据单元2描述', name: 'CHECK_UNITDS2',align: 'center', minWidth: 90  },"
			+ " { display: '检查数据单元2选择列的名称', name: 'CHECK_SFIELDNA2',align: 'center', minWidth: 110},"
			+ " { display: '检查数据单元2选择列的描述', name: 'CHECK_SFIELDDS2',align: 'center', minWidth: 90  },"
			+ " { display: '单元2记为1次中断的连续中断条数', name: 'CHECK_INTERCON2', align: 'center',  minWidth: 20},"
			+ " { display: '数据单元2中断次数', name: 'DATA_INTERNUM2', align: 'center',  minWidth: 20},"
			+ " { display: '数据单元2中断累计时长', name: 'DATA_INTERACT2',align: 'center',minWidth: 20},"
			+ " { display: '检查数据单元3描述', name: 'CHECK_UNITDS3', align: 'center',  minWidth: 20},"
			+ " { display: '检查数据单元3选择列的名称', name: 'CHECK_SFIELDNA3', align: 'center',  minWidth: 50 },"
			+ " { display: '检查数据单元3选择列的描述', name: 'CHECK_SFIELDDS3', align: 'center',  minWidth: 50 },"
			+ " { display: '单元3记为1次中断的连续中断条数', name: 'CHECK_INTERCON3', align: 'center',  minWidth: 40},"
			+ " { display: '数据单元3中断次数', name: 'DATA_INTERNUM3', align: 'center',  minWidth: 30},"
			+ " { display: '数据单元3中断累计时长', name: 'DATA_INTERACT3', align: 'center',  minWidth: 30 },"
			+ " { display: '动态表中数据存储时间间隔', name: 'ATIME_INTERVAL', align: 'center',  minWidth: 300 },"
			+ " { display: '有效记录条数', name: 'RECODE_VDNUM', align: 'center',  minWidth: 200 },"
			+ " { display: '组织对象ID（隐藏）', name: 'ORG_ID',align: 'center',minWidth: 50 ,hide :true}"
			+ " ],"+
	
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sjtdjctjb_searchDTDatas.action',"+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false, "+
			"headerRowHeight:50 ";
		
		public static final String SUBCOOL_ALARM_GRID = "columns: [ " +
			"{ display: 'CALID', name: 'CALID' ,align: 'center', minWidth: 40, hide: true}," +
			"{ display: 'SAGDID', name: 'SAGDID' ,align: 'center', minWidth: 40, hide: true}," +
			"{ display: 'ORG_ID', name: 'ORG_ID' ,align: 'center', minWidth: 40, hide: true}," +
			"{ display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth:60}," + 		
			"{ display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 80}," + 
			"{ display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 80}," + 
			"{ display: '井号名称', name: 'JHMC',align: 'center', minWidth: 80}," + 
			"{ display: '生产阶段', name: 'PRODSTAGES' ,align: 'center', minWidth: 60}," + 
			"{ display: '操作方法ID', name: 'calculate_methodsid' ,align: 'center', minWidth: 40, hide: true}," +
			"{ display: '操作压力方法', name: 'calculate_methods' ,align: 'center', minWidth: 180}," +
			"{ display: '计算频率', name: 'calculate_rate' ,align: 'center', minWidth: 40, hide: true}," +
			"{ display: '正常SubCool值(℃)', columns:" + 
			"	[" + 
			"		{ display: '最小', name: 'min_subcool' ,align: 'center', minWidth: 40}," + 
			"		{ display: '最大', name: 'max_subcool' ,align: 'center', minWidth: 40}" + 
			"	]}," + 			
			"{ display: '流量计方法', name: 'flow_methods' ,align: 'center', minWidth: 60}," + 
			"{ display: 'FLOWID', name: 'flowid' ,align: 'center', minWidth: 40, hide: true}" +
			"	]," + 
			"height: '96%'," + 
			"width: '99%'," + 
			"dataAction: 'server'," + 
			"url:'subcool_searchAlarmParam.action'," + 
			"pageParmName: 'pageNo'," + 
			"sortnameParmName: 'sort'," + 
			"sortorderParmName: 'order'," +  
			"pagesizeParmName: 'rowsPerpage'," + 
			"selectRowButtonOnly: true," + 
			//"sePaper: true," + 
			"pageSize: 30 ," + 
			"rownumbers: true," + 
			"frozen: true," + 
			"checkbox: false," +
			"onSelectRow: function (rowdata, rowindex) {" + 
			"	fromAu(rowdata);" + 
			"}";
		
		public static final String SUBCOOL_INFO_GRID = "columns: [ " +
			"{ display: 'CALID', name: 'CALID' ,align: 'center', minWidth: 40, hide: true}," +
			"{ display: 'SAGDID', name: 'SAGDID' ,align: 'center', minWidth: 40, hide: true}," +
			"{ display: 'ORG_ID', name: 'ORG_ID' ,align: 'center', minWidth: 40, hide: true}," +
			"{ display: '井号名称', name: 'JHMC',align: 'center', minWidth: 70, frozen:true}," + 
			"{ display: '采集时间', name: 'CALTIME' ,align: 'center', minWidth: 130, format:'yyyy-MM-dd hh:mm:ss', frozen:true}," +
			"{ display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth:80, frozen:true}," + 		
			"{ display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 100}," + 
			"{ display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 130}," + 
			"{ display: '生产阶段', name: 'PRODSTAGES' ,align: 'center', minWidth: 60, hide: true}," +
			"{ display: '操作方法ID', name: 'CALMETHODID' ,align: 'center', minWidth: 40, hide: true}," +
			"{ display: '压力取值方法', name: 'CALMETHOD' ,align: 'center', minWidth: 140, hide: true}," +
			"{ display: '操作压力', name: 'OPERPRESS' ,align: 'center', minWidth: 50}," +
			"{ display: 'SubCool', name: 'CURSUBCOOL' ,align: 'center', minWidth: 50}," +
			"{ display: '正常SubCool', columns:" + 
			"	[" + 
			"		{ display: '最小', name: 'MINSUBCOOL' ,align: 'center', minWidth: 30}," + 
			"		{ display: '最大', name: 'MAXSUBCOOL' ,align: 'center', minWidth: 30}" + 
			"	]}," + 			
			"{ display: '当前控制参数', columns:" + 
			"	[" + 
			"		{ display: '注汽量', name: 'CURFLOW' ,align: 'center', minWidth: 40}," + 
			"		{ display: '冲次', name: 'CURJIG' ,align: 'center', minWidth: 30}," + 
			"		{ display: '油嘴', name: 'CURNIP' ,align: 'center', minWidth: 30}" + 
			"	]}," + 
			"{ display: '建议控制参数', columns:" + 
			"	[" + 
			"		{ display: '注汽量', name: 'SUGFLOW' ,align: 'center', minWidth: 40}," + 
			"		{ display: '冲次', name: 'SUGJIG' ,align: 'center', minWidth: 30}," + 
			"		{ display: '油嘴', name: 'SUGNIP' ,align: 'center', minWidth: 30}" + 
			"	]}," + 
			"{ display: '人工调整参数', columns:" + 
			"	[" + 
			"		{ display: '注汽量', name: 'MODIFYFLOW' ,align: 'center', minWidth: 40}," + 
			"		{ display: '冲次', name: 'MODIFYJIG' ,align: 'center', minWidth: 30}," + 
			"		{ display: '油嘴', name: 'MODIFYNIP' ,align: 'center', minWidth: 30}" +
			"	]}," + 
			"{ display: '人工调整时间', name: 'MODIFYTIME' ,align: 'center', minWidth:130}," +
			"{ display: '操作人', name: 'MODIFYUSER' ,align: 'center', minWidth:100}" +
			"	]," + 
			"height: '96%'," + 
			"width: '99%'," + 
			"dataAction: 'server'," + 
			"url:'subcool_searchAlarmInfo.action'," + 
			"pageParmName: 'pageNo'," + 
			"sortnameParmName: 'sort'," + 
			"sortorderParmName: 'order'," +  
			"pagesizeParmName: 'rowsPerpage'," + 
			"selectRowButtonOnly: true," + 
			"sePaper: true," + 
			"pageSize: 30 ," + 
			"rownumbers: true," + 
			"frozen: true," + 
			"alternatingRow: false," +
			"checkbox: false," +
			"rowAttrRender: function (rowdata,rowid) {" +
			"	var subcool = parseInt(rowdata.CURSUBCOOL);" +
			"	if(subcool < parseInt(rowdata.MINSUBCOOL) || subcool > parseInt(rowdata.MAXSUBCOOL)) {" +
			"		return 'style=\"background:#F1D3F7;\"';" +
			"	}" +		
			"	return '';" +
			"}";
		
		public static final String SUBCOOL_NEW_GRID = "columns: [ " +
			"{ display: 'SAGDID', name: 'SAGDID' ,align: 'center', minWidth: 40, hide: true}," +
			"{ display: '井号名称', name: 'JHMC',align: 'center', minWidth: 70, frozen:true}," + 
			"{ display: '采集时间', name: 'CALTIME' ,align: 'center', minWidth: 130, format:'yyyy-MM-dd hh:mm:ss', frozen:true}," +
			"{ display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 120}," + 
			"{ display: '压力取值方法', name: 'CALMETHOD' ,align: 'center', minWidth: 80}," +
			"{ display: '操作压力', name: 'OPERPRESS' ,align: 'center', minWidth: 50}," +
			"{ display: 'SubCool', name: 'CURSUBCOOL' ,align: 'center', minWidth: 50}," +
			"{ display: '正常SubCool', columns:" + 
			"	[" + 
			"		{ display: '最小', name: 'MINSUBCOOL' ,align: 'center', minWidth: 30}," + 
			"		{ display: '最大', name: 'MAXSUBCOOL' ,align: 'center', minWidth: 30}" + 
			"	]}," + 			
			"{ display: '当前控制参数', columns:" + 
			"	[" + 
			"		{ display: '注汽量', name: 'CURFLOW' ,align: 'center', minWidth: 40}," + 
			"		{ display: '冲次', name: 'CURJIG' ,align: 'center', minWidth: 30}," + 
			"		{ display: '油嘴', name: 'CURNIP' ,align: 'center', minWidth: 30}" + 
			"	]}," + 
			"{ display: '建议控制参数', columns:" + 
			"	[" + 
			"		{ display: '注汽量', name: 'SUGFLOW' ,align: 'center', minWidth: 40}," + 
			"		{ display: '冲次', name: 'SUGJIG' ,align: 'center', minWidth: 30}," + 
			"		{ display: '油嘴', name: 'SUGNIP' ,align: 'center', minWidth: 30}" + 
			"	]}," + 
			"{ display: '人工调整参数', columns:" + 
			"	[" + 
			"		{ display: '注汽量', name: 'MODIFYFLOW' ,align: 'center', minWidth: 40}," + 
			"		{ display: '冲次', name: 'MODIFYJIG' ,align: 'center', minWidth: 30}," + 
			"		{ display: '油嘴', name: 'MODIFYNIP' ,align: 'center', minWidth: 30}" + 
			"	]}," +
			"{ display: '人工调整时间', name: 'MODIFYTIME' ,align: 'center', minWidth:130}," +
			"{ display: '操作人', name: 'MODIFYUSER' ,align: 'center', minWidth:100}," +
			"{ display: '上次取值时间', name: 'LASTTIME' ,align: 'center', minWidth:130}," +
			"{ display: '上次调整取值方式', name: 'MODIFYMODE' ,align: 'center', minWidth:80}," +
			"{ display: '生产阶段', name: 'PRODSTAGES' ,align: 'center', minWidth: 60, hide: true}," +
			"{ display: '操作方法ID', name: 'CALMETHODID' ,align: 'center', minWidth: 40, hide: true}," +
			"{ display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 100}," + 
			"{ display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth:80}," +
			"{ display: 'CALID', name: 'CALID' ,align: 'center', minWidth: 40, hide: true}," +
			"{ display: 'ORG_ID', name: 'ORG_ID' ,align: 'center', minWidth: 40, hide: true}" +
			"	]," + 
			"height: '96%'," + 
			"width: '99%'," + 
			"dataAction: 'server'," + 
			"url:'subcool_searchNewInfo.action'," +
			"usePager: false," + 
			"selectRowButtonOnly: false," + 
			"rownumbers: true," + 
			"frozen: true," + 
			"alternatingRow: false," +
			"checkbox: true," +
			"rowAttrRender: function (rowdata,rowid) {" +
			"	var subcool = parseInt(rowdata.CURSUBCOOL);" +
			"	if(subcool < parseInt(rowdata.MINSUBCOOL) || subcool > parseInt(rowdata.MAXSUBCOOL)) {" +
			"		return 'style=\"background:#F1D3F7;\"';" +
			"	}" +		
			"	return '';" +
			"}";

		
		//SAGD管汇动态数据-全部
		public static final String SAGDRDALL_PAGE_GH = " columns: ["				
				+ "  { display: '采集时间', name: 'CJSJ' ,align: 'center', minWidth: 150,frozen:true},"
				+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
	/*			+ "  { display: '站号id', name: 'GRZHID' ,align: 'center', minWidth: 120,hide: true},"
				+ "  { display: '管汇ID', name: 'MANIFOLDID' ,align: 'center', minWidth: 120,hide: true},"
				+ "  { display: 'MANIFOLDRID', name: 'MANIFOLDRID' ,align: 'center', minWidth: 120,hide: true},"*/
				+ "  { display: '管汇名称', name: 'PIPE_SINK' ,align: 'center', minWidth: 70},"

				+ "  { display: '主线压力（MPa）', name: 'pressure_press' ,align: 'center', minWidth: 100},"
				+ "  { display: '主线温度（℃）', name: 'pressure_temp' ,align: 'center', minWidth: 100},"
				+ "  { display: '副线压力（MPa）', name: 'looped_press' ,align: 'center', minWidth: 100},"
				+ "  { display: '副线温度（℃）', name: 'looped_temp', align: 'center', minWidth: 100},"
				+ "  { display: '计量线压力（MPa）', name: 'measure_press', align: 'center', minWidth: 100},"
				+ "  { display: '计量线温度（℃）', name: 'measure_temp', align: 'center', minWidth: 100}"
				+ " ],"+
			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sagdgh_searchSagdgh.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false,"+
			"onSelectRow: function () "+
			"{searchSagdnow()} ";
		
		
		
		

		//SAGD18-3#动态数据-全部
		public static final String SAGDZYZ_PAGE_GRID = " columns: ["
				+ "  { display: '采集时间', name: 'acquisition_time',align: 'center', minWidth: 150,frozen:true},"
				
				+ " { display: '1#分离器', columns:"
				+ "	["
				+ "		{ display: '汽相出口压力（MPa）', name: 'flq_qckyl1', align: 'center', width: 140},"
				+ "		{ display: '电液位高度（mm）', name: 'flq_ywgd1', width: 140, align: 'center'},"
				+ "		{ display: '汽相电控阀开度（%）', name: 'flq_qdkfkd1', width: 140, align: 'center'},"
				+ "		{ display: '液相电控阀开度（%）', name: 'flq_ydkfkd1', width: 140, align: 'center'}"			
				+ "	]},"
				
				+ " { display: '2#分离器', columns:"
				+ "	["
				+ "		{ display: '汽相出口压力（MPa）', name: 'flq_qckyl2', align: 'center', width: 140},"
				+ "		{ display: '电液位高度（mm）', name: 'flq_ywgd2', width: 140, align: 'center'},"
				+ "		{ display: '汽相电控阀开度（%）', name: 'flq_qdkfkd2', width: 140, align: 'center'},"
				+ "		{ display: '液相电控阀开度（%）', name: 'flq_ydkfkd2', width: 140, align: 'center'}"			
				+ "	]},"
				
				+ " { display: '3#分离器', columns:"
				+ "	["
				+ "		{ display: '汽相出口压力（MPa）', name: 'flq_qckyl3', align: 'center', width: 140},"
				+ "		{ display: '电液位高度（mm）', name: 'flq_ywgd3', width: 140, align: 'center'},"
				+ "		{ display: '汽相电控阀开度（%）', name: 'flq_qdkfkd3', width: 140, align: 'center'},"
				+ "		{ display: '液相电控阀开度（%）', name: 'flq_ydkfkd3', width: 140, align: 'center'}"			
				+ "	]},"				
			
				+ " { display: '泵进口', columns:"
				+ "	["
				+ "		{ display: '主线压力（MPa）', name: 'pump_in_zx_press', width: 100, align: 'center'},"
				+ "		{ display: '主线温度（℃）', name: 'pump_in_zx_tem', width: 100, align: 'center'},"
				+ "		{ display: '副线压力（MPa）', name: 'pump_in_fx_press', width: 100, align: 'center'},"
				+ "		{ display: '副线温度（℃）', name: 'pump_in_fx_tem', width: 100, align: 'center'}"
				+ "	]},"				
				
				+ " { display: '泵出口', columns:"
				+ "	["
				+ "		{ display: '主线压力（MPa）', name: 'pump_out_zx_press', width: 100, align: 'center'},"
				+ "		{ display: '主线温度（℃）', name: 'pump_out_zx_tem', width: 100, align: 'center'},"
				+ "		{ display: '副线压力（MPa）', name: 'pump_out_fx_press', width: 100, align: 'center'},"
				+ "		{ display: '副线温度（℃）', name: 'pump_out_fx_tem', width: 100, align: 'center'}"
				+ "	]},"				
				
				+ " { display: '1#泵', columns:"
				+ "	["
				+ "		{ display: '泵启停状态（启/停）', name: 'pump_zt1', align: 'center', width: 80},"
				+ "		{ display: '泵频', name: 'pump_pl1', width: 80, align: 'center'},"
				+ "		{ display: '手动/自动', name: 'pump_sd1', align: 'center', width: 80}"
				+ "	]},"								
				
				+ " { display: '2#泵', columns:"
				+ "	["
				+ "		{ display: '泵启停状态（启/停）', name: 'pump_zt2', align: 'center', width: 80},"
				+ "		{ display: '泵频', name: 'pump_pl2', width: 80, align: 'center'},"
				+ "		{ display: '手动/自动', name: 'pump_sd2', align: 'center', width: 80}"
				+ "	]},"								
				
				+ " { display: '3#泵', columns:"
				+ "	["
				+ "		{ display: '泵启停状态（启/停）', name: 'pump_zt3', align: 'center', width: 80},"
				+ "		{ display: '泵频', name: 'pump_pl3', width: 80, align: 'center'},"
				+ "		{ display: '手动/自动', name: 'pump_sd3', align: 'center', width: 80}"
				+ "	]}"				
				
				+ " ],"+

			"height:'100%',"+
			"dataAction: 'server',"+
			"url:'sagd183dt_searchSagd183.action',"+
			"delayLoad :true, "+
			"pageParmName :'pageNo',"+
			"sortnameParmName:'sort',"+
			"sortorderParmName: 'order',  "+
			"pagesizeParmName:'rowsPerpage', "+
			"selectRowButtonOnly:true,"+
			"sePaper:true,"+
			"delayLoad :true, "+
			"pageSize:30 ,"+
			"rownumbers:true ,"+
			"frozen:true, "+
			"checkbox :false";

		
		
		//SAGD井实时数据-全部
		public static final String SAGDNOW_PAGE_GRID = " columns: ["
				+ "  { display: '井号名称', name: 'JHMC',align: 'center', minWidth: 60,frozen:true},"
				+ "  { display: '采集时间', name: 'ACQUISITION_TIME',align: 'center', minWidth: 150,frozen:true},"
				+ "  { display: '井号编码', name: 'JHMC_S',align: 'center', minWidth: 60},"
				+ "  { display: '所属采油站', name: 'OILSTATIONNAME' ,align: 'center', minWidth: 70},"
				+ "  { display: '所属区块', name: 'AREABLOCK' ,align: 'center', minWidth: 60},"
				+ "  { display: '所属注转站', name: 'BLOCKSTATIONNAME' ,align: 'center', minWidth: 70},"
				+ "  { display: '所属管汇', name: 'MANIFOLD' ,align: 'center', minWidth: 60},"
				+ "  { display: '所属分线', name: 'BRANCHINGID' ,align: 'center', minWidth: 60},"
				+ "  { display: 'SAGD井ID', name: 'SAGDID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
				+ "  { display: '组织结构ID', name: 'ORG_ID', align: 'center', width: 200, minWidth: 60,hide: true},"
				+ "  { display: 'A2ID', name: 'A2ID', align: 'center', width: 200, minWidth: 60 ,hide: true},"
			
				
				+ " { display: '抽油机参数', columns:"
				+ "	["
				+ "		{ display: '电机状态', name: 'MOTORSTATUS', align: 'center', width: 60},"
				+ "		{ display: '电机A相电压(V)', name: 'MOTOR_PRESS_A', width: 100, align: 'center'},"
				+ "		{ display: '电机B相电压(V)', name: 'MOTOR_PRESS_B', width: 100, align: 'center'},"
				+ "		{ display: '电机C相电压(V)', name: 'MOTOR_PRESS_C', width: 100, align: 'center'},"
				+ "		{ display: '电机A相电流(A)', name: 'MOTOR_TEMP_A', width: 100, align: 'center'},"
				+ "		{ display: '电机B相电流(A)', name: 'MOTOR_TEMP_B', width: 100, align: 'center'},"
				+ "		{ display: '电机C相电流(A)', name: 'MOTOR_TEMP_C', width: 100, align: 'center'},"
				+ "		{ display: '有功电量(kWh)', name: 'ACTIVE_ENERGY', width: 90, align: 'center'},"
				+ "		{ display: '无功电量(kVar)', name: 'IDLE_ENERGY', width: 90, align: 'center'},"
				+ "		{ display: '功率因素(%)', name: 'POWER_FACTOR', width: 90, align: 'center'},"
				+ "		{ display: '变频电压(V)', name: 'CONVERSION_PRESS', width: 90, align: 'center'},"
				+ "		{ display: '变频电流(A)', name: 'CONVERSION_ELECTRICITY', width: 90, align: 'center'},"
				+ "		{ display: '当前频率(HZ)', name: 'NOW_FREQUENCY', width: 90, align: 'center'},"
				+ "		{ display: '冲程(m)', name: 'STROKE', width: 60, align: 'center'},"
				+ "		{ display: '冲次(次/分)', name: 'JIG_FREQUENCY', width: 80, align: 'center'},"
				//+ "		{ display: '脱抽饱和方式', name: 'BHFS', width: 100, align: 'center'}," //数据字典中没有该字段
				+ "		{ display: '报警状态', name: 'ALMSTATE', width: 60, align: 'center'},"
				//+ "		{ display: '最新频率(HZ)', name: 'ZXPL', width: 100, align: 'center'},"
				//+ "		{ display: '正常频率(HZ)', name: 'ZCPL', width: 100, align: 'center'},"
				
				+ "		{ display: '当前载荷', name: 'NOW_LOAD', width: 60, align: 'center'},"
				+ "		{ display: '电机运行模式', name: 'MOTOR_MODE', width: 100, align: 'center'},"
				+ "		{ display: '抽油机运行状态', name: 'PUMPING_RUNNING_STATE', width: 100, align: 'center'}"
				
				+ "	]},"
				
				
				+ " { display: '井口数据', columns:"
				+ "	["
				+ "		{ display: 'P井主管压力(MPa)', name: 'P_PRESSURE_PRESS', width: 100, align: 'center'},"
				+ "		{ display: 'P井主管温度(℃)', name: 'P_PRESSURE_TEMP', width: 100, align: 'center'},"
				+ "		{ display: 'P井副管压力(MPa)', name: 'P_LOOPED_PRESS', width: 100, align: 'center'},"
				+ "		{ display: 'P井副管温度(℃)', name: 'P_LOOPED_TEMP', width: 100, align: 'center'},"
				+ "		{ display: 'P井套管压力(MPa)', name: 'P_DRIVEPIPE_PRESS', width: 100, align: 'center'},"
				+ "		{ display: 'I井主管压力(MPa)', name: 'I_PRESSURE_PRESS', width: 100, align: 'center'},"
				+ "		{ display: 'I井主管温度(℃)', name: 'I_PRESSURE_TEMP', width: 100, align: 'center'},"
				+ "		{ display: 'I井副管压力(MPa)', name: 'I_LOOPED_PRESS', width: 100, align: 'center'},"
				+ "		{ display: 'I井副管温度(℃)', name: 'I_LOOPED_TEMP', width: 100, align: 'center'},"
				+ "		{ display: 'I井套管压力(MPa)', name: 'I_DRIVEPIPE_PRESS', width: 100, align: 'center'}"
				+ "	]},"
				
				+ " { display: '井下数据', columns:"
				+ "	["
				+ "		{ display: 'P井压力1(MPa)', name: 'P_PRESS1', align: 'center', width: 80},"
				+ "		{ display: 'P井压力2(MPa)', name: 'P_PRESS2', width: 80, align: 'center'},"
				+ "		{ display: 'P井温度1(℃)', name: 'P_TEMP1', align: 'center', width: 80},"
				+ "		{ display: 'P井温度2(℃)', name: 'P_TEMP2', width: 80, align: 'center'},"
				+ "		{ display: 'P井温度3(℃)', name: 'P_TEMP3', width: 80, align: 'center'},"
				+ "		{ display: 'P井温度4(℃)', name: 'P_TEMP4', align: 'center', width: 80},"
				+ "		{ display: 'P井温度5(℃)', name: 'P_TEMP5', width: 80, align: 'center'},"
				+ "		{ display: 'P井温度6(℃)', name: 'P_TEMP6', align: 'center', width: 80},"
				+ "		{ display: 'P井温度7(℃)', name: 'P_TEMP7', width: 80, align: 'center'},"
				+ "		{ display: 'P井温度8(℃)', name: 'P_TEMP8', width: 80, align: 'center'},"
				+ "		{ display: 'P井温度9(℃)', name: 'P_TEMP9', align: 'center', width: 80},"
				+ "		{ display: 'P井温度10(℃)', name: 'P_TEMP10', width: 80, align: 'center'},"
				+ "		{ display: 'P井温度11(℃)', name: 'P_TEMP11', align: 'center', width: 80},"
				+ "		{ display: 'P井温度12(℃)', name: 'P_TEMP12', width: 80, align: 'center'}"
				+ "	]},"
				
				+ " { display: '蒸汽计量数据', columns:"
				+ "	["
				+ "		{ display: '蒸汽压力1#(mPa)', name: 'NO1STEAM_PRESS', align: 'center', width: 100},"
				+ "		{ display: '蒸汽压力2#(mPa)', name: 'NO2STEAM_PRESS', width: 100, align: 'center'},"
				+ "		{ display: '蒸汽温度1#(℃)', name: 'NO1STEAM_TEMP', align: 'center', width: 100},"
				+ "		{ display: '蒸汽温度2#(℃)', name: 'NO2STEAM_TEMP', width: 100, align: 'center'},"
				+ "		{ display: '蒸汽差压1#(mPa)', name: 'NO1STEAM_DIFP', width: 100, align: 'center'},"
				+ "		{ display: '蒸汽差压2#(mPa)', name: 'NO2STEAM_DIFP', align: 'center', width: 100},"
				+ "		{ display: '瞬时干度1# (%)', name: 'NO1INSTANT_DRYNESS', width: 100, align: 'center'},"
				+ "		{ display: '瞬时干度2# (%)', name: 'NO2INSTANT_DRYNESS', align: 'center', width: 100},"
				+ "		{ display: '瞬时流量1#(T/H)', name: 'NO1INSTANT_FLOW', width: 100, align: 'center'},"
				+ "		{ display: '瞬时流量2#(T/H)', name: 'NO2INSTANT_FLOW', width: 100, align: 'center'},"
				+ "		{ display: '调节阀开度1#(%)', name: 'NO1CONTROL_APERTURE', align: 'center', width: 100},"
				+ "		{ display: '调节阀开度2#(%)', name: 'NO2CONTROL_APERTURE', width: 100, align: 'center'},"
				+ "		{ display: '阀控制状态1#', name: 'NO1CONTROL_STATUS', align: 'center', width: 100},"//阀控制状态1#
				+ "		{ display: '阀控制状态2#', name: 'NO2CONTROL_STATUS', width: 100, align: 'center'},"
				+ "		{ display: '阀控制开度1#(%)', name: 'CLIQUE_CONTROL_APERTURE1', width: 100, align: 'center'},"
				+ "		{ display: '阀控制开度2#(%)', name: 'CLIQUE_CONTROL_APERTURE2', align: 'center', width: 100},"
				+ "		{ display: '流量设定1#(T/H)', name: 'NO1FLOW_SET', width: 100, align: 'center'},"
				+ "		{ display: '流量设定2#(T/H)', name: 'NO2FLOW_SET', align: 'center', width: 100},"
//				+ "		{ display: '比例系数P 1#', name: 'BLXS1', width: 100, align: 'center'},"
//				+ "		{ display: '比例系数P 2#', name: 'BLXS2', width: 100, align: 'center'},"
//				+ "		{ display: '积分系数T 2#', name: 'JFXS1', width: 100, align: 'center'},"
//				+ "		{ display: '积分系数T 2#', name: 'JFXS2', align: 'center', width: 100},"
				+ "		{ display: '昨日累积流量1# (T)', name: 'YCUMULATIVE_FLOW1', width: 100, align: 'center'},"
				+ "		{ display: '昨日累积流量2# (T)', name: 'YCUMULATIVE_FLOW2', width: 100, align: 'center'},"
				+ "		{ display: '今日累积流量1# (T)', name: 'TCUMULATIVE_FLOW1', width: 100, align: 'center'},"
				+ "		{ display: '今日累积流量2# (T)', name: 'TCUMULATIVE_FLOW2', align: 'center', width: 100}"
				//+ "		{ display: '总累积流量1# (T)', name: 'SUM_FLOW1', width: 100, align: 'center'},"
				//+ "		{ display: '总累积流量2# (T)', name: 'SUM_FLOW2', align: 'center', width: 100}"
				+ "	]},"
				+ "  { display: '备注', name: 'REMARK', align: 'center', align: 'center',width: 255,minWidth: 120}"
				+ " ],"+
		
	"height:'100%',"+
	"dataAction: 'server',"+
	"url:'sagdsssj_searchSagdss.action',"+
	"delayLoad :true, "+
	"pageParmName :'pageNo',"+
	"sortnameParmName:'sort',"+
	"sortorderParmName: 'order',  "+
	"pagesizeParmName:'rowsPerpage', "+
	"selectRowButtonOnly:true,"+
	"sePaper:true,"+
	"delayLoad :true, "+
	"pageSize:30 ,"+
	"rownumbers:true ,"+
	"frozen:true, "+
	"checkbox :false";
		

		
		//报警限值执行查询
		public static final String BJXZCX_PAGE_GRID = " columns: ["
				+ "  { display: '申请日期', name: 'acquisition_time' ,align: 'center', minWidth: 150,frozen:true},"
				+ "  { display: '申请人', name: 'sqr' ,align: 'center', minWidth: 70},"
				+ "  { display: '审批人', name: 'spr' ,align: 'center', minWidth: 70},"
				+ "  { display: '执行人', name: 'zxr' ,align: 'center', minWidth: 100},"
				+ "  { display: '标签名称', name: 'bqmc' ,align: 'center', minWidth: 100},"
				+ "  { display: '执行情况', name: 'zxqk' ,align: 'center', minWidth: 100}"
				+ " ],"+

					"height:'100%',"+
					"dataAction: 'server',"+
					"url:'Bjxzzxcx_searchBjcx.action',"+
					"delayLoad :true, "+
					"pageParmName :'pageNo',"+
					"sortnameParmName:'sort',"+
					"sortorderParmName: 'order',  "+
					"pagesizeParmName:'rowsPerpage', "+
					"selectRowButtonOnly:true,"+
					"sePaper:true,"+
					"delayLoad :true, "+
					"pageSize:30 ,"+
					"rownumbers:true ,"+
					"frozen:true, "+
					"checkbox :false";
		
		
		//短信推送信息查询
				public static final String DXTSCX_PAGE_GRID = " columns: ["
						+ "  { display: '推送时间', name: 'acquisition_time' ,align: 'center', width: 150,frozen:true},"
						+ "  { display: '接受部门', name: 'jsbm' ,align: 'center', width: 100},"
						+ "  { display: '接收人', name: 'jsr' ,align: 'center', width: 100},"
						+ "  { display: '短信详细信息', name: 'szdts' ,align: 'center', width: 480},"
						+ "  { display: '推送部门', name: 'tsbm' ,align: 'center', width: 100},"
						+ "  { display: '推送人', name: 'tsr' ,align: 'center', width: 100}"
						+ " ],"+
							"height:'100%',"+
							"dataAction: 'server',"+
							"url:'dxtscx_searchDxts.action',"+
							"delayLoad :true, "+
							"pageParmName :'pageNo',"+
							"sortnameParmName:'sort',"+
							"sortorderParmName: 'order',  "+
							"pagesizeParmName:'rowsPerpage', "+
							"selectRowButtonOnly:true,"+
							"sePaper:true,"+
							"delayLoad :true, "+
							"pageSize:30 ,"+
							"rownumbers:true ,"+
							"frozen:true, "+
							"checkbox :false";
		
		
				//报警知识库管理
				public static final String BJZSK_PAGE_GRID = " columns: ["
								+ "  { display: '知识库ID', name: 'zsk_id', align: 'center', width: 200, minWidth: 60 ,hide: true},"
								+ "  { display: '描述', name: 'miaoshu',align: 'center', minWidth: 120,frozen:true},"
								+ "  { display: '产生原因', name: 'CSYY',align: 'center', minWidth: 120},"
								+ "  { display: '处理方式', name: 'CLFS',align: 'center', minWidth: 120}"
								+ " ],"+						
								"height:'100%',"+
								"dataAction: 'server',"+
								"url:'bjfzzsk_searchBjzsk.action',"+	
								"pageParmName :'pageNo',"+
								"sortnameParmName:'sort',"+
								"sortorderParmName: 'order',  "+
								"pagesizeParmName:'rowsPerpage', "+
								"selectRowButtonOnly:true,"+
								"sePaper:true,"+
								"pageSize:30 ,"+
								"rownumbers:true ,"+
								"frozen:true, "+
								"checkbox :false, "+
								//"toolbar:true,"+
								"onSelectRow: function (rowdata, rowindex) "+
								"{fromAu(rowdata);} ";
		
				public static final String BJXZSP_PAGE_GRID = " columns: ["
						+ "  { display: '申请日期', name: 'acquisition_time', align: 'center', width: 200, minWidth: 60},"
						+ "  { display: '审批表名称', name: 'spbmc',align: 'center', minWidth: 120},"
						+ "  { display: '申请人员', name: 'sqry',align: 'center', minWidth: 120},"
						+ "  { display: '执行状态', name: 'zxzt',align: 'center', minWidth: 120}"
						+ " ],"+						
						"height:'100%',"+
						"dataAction: 'server',"+
						"url:'bjxzsp_searchBjxzsp.action',"+	
						"pageParmName :'pageNo',"+
						"sortnameParmName:'sort',"+
						"sortorderParmName: 'order',  "+
						"pagesizeParmName:'rowsPerpage', "+
						"selectRowButtonOnly:true,"+
						"sePaper:true,"+
						"pageSize:30 ,"+
						"rownumbers:true ,"+
						"frozen:true, "+
						"checkbox :false, "+
						//"toolbar:true,"+
						"onSelectRow: function (rowdata, rowindex) "+
						"{fromAu(rowdata);} ";
				
				

		//过热锅炉周报
		public static final String GRGLZB_PAGE_GRID = " columns: ["
				
				+ "  { display: '日期', name: 'acquisition_time' ,align: 'center', width: 100,frozen:true},"
				+ "  { display: '供汽单位', name: 'gqdw' ,align: 'center', width: 50},"

				+ "  { display: '总台数(台)', name: 'glzts' ,align: 'center', width: 70},"

				+ "  { display: '运行数(台)', name: 'glyxts' ,align: 'center', width: 70},"
				+ "  { display: '合格数(台)', name: 'hgglts' ,align: 'center', width: 70},"
				+ "  { display: '不合格数(台)', name: 'bhgglts' ,align: 'center', width: 70},"
				+ "  { display: '合格率(%)', name: 'hgl', align: 'center', width: 70},"
				+ "  { display: '不合格锅炉号和时长', name: 'bhggllh', align: 'left', width: 1300}"

				+ " ],"+
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'grglzb1_searchGrglzb1.action',"+
				"delayLoad :true, "+
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"selectRowButtonOnly:true,"+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"rownumbers:true ,"+
				"frozen:true, "+
				"checkbox :false";
		
		
		//软化器再生一览表
		public static final String RHQZS_PAGE_GRID = " columns: ["
				+ "  { display: '日期', name: 'rq' ,align: 'center', width: 90,frozen:true,"+
					"totalSummary:" +
					"{" +
					"	type:'count'" + 
					"}" +
				"},"
				+ "  { display: '水处理站', name: 'zh' ,align: 'center', width: 100},"
				+ "  { display: '软化器编号', name: 'rhqbh' ,align: 'center', width: 70},"
				+ "  { display: '再生次数', name: 'ZSCS' ,align: 'center', width: 70,"+
					"totalSummary:" +
	            	"{" +
	            	"	type:'sum,avg'" + 
	            	"}" +
				"},"
				+ "  { display: '再生过程描述A', name: 'A' ,align: 'left', width: 520},"	
				+ "  { display: '再生过程描述B', name: 'B' ,align: 'left', width: 520},"	
				+ "  { display: '再生过程描述C', name: 'C' ,align: 'left', width: 180}"	
				+ " ],"+
					"height:'100%',"+
					"dataAction: 'server',"+
					"url:'rhqzs1_searchRhqzs.action',"+	
					"delayLoad :true, "+
					"pageParmName :'pageNo',"+
					"sortnameParmName:'sort',"+
					"sortorderParmName: 'order',  "+
					"pagesizeParmName:'rowsPerpage', "+
					"selectRowButtonOnly:true,"+
					"sePaper:true,"+
					"pageSize:30 ,"+
					"rownumbers:true ,"+
					"frozen:true, "+
					"checkbox :false, "+
					" onDblClickRow: function(data, rowindex, rowobj) {" +
					"		$.ligerDialog.open({" +
					" 			title: '显示SAGD日报详细信息'," +
					"  			url:'sagdrpdview.jsp?sagdid='+data.SAGDID+'&sagddid='+data.SAGDDID+'&jhmc='+data.JHMC," +
					"  			width: 1000," +
					"  			height: 500" +
					"  			});" +
					"  } ";
					


		
		
		//报警用户管理
		public static final String DXYHGL_PAGE_GRID = " columns: ["
				+ "  { display: 'id', name: 'alarmuserid' ,align: 'center', width: 100,hide: true},"
				+ "  { display: '推送人', name: 'username' ,align: 'center', width: 100},"
				+ "  { display: '推送号码', name: 'phone', align: 'center', width: 120},"
				+ "  { display: '所属部门', name: 'dept',align: 'center', width: 120},"
				+ "  { display: '报警关键字', name: 'alarm_key',align: 'center', width: 400},"
				+ "  { display: '短信发出中心', name: 'dep_manag',align: 'center', width: 120},"
				+ "  { display: '数据报警单位', name: 'data_unit',align: 'center', width: 80},"
				+ "  { display: '备注', name: 'remark'  ,align: 'center', width: 120}"
				+ " ],"+
		
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'dxyhgl_searchDatas.action',"+	
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"selectRowButtonOnly:true,"+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"rownumbers:true ,"+
				"frozen:true, "+
				"checkbox :false, "+
				"onSelectRow: function (rowdata, rowindex) "+
				"{fromAu(rowdata);} ";
		
		
		
		
		
		//湿蒸汽锅炉分时数据信息	
		public static final String SZGLFS_RPD_PAGE_GRID = " columns: ["
				+ "  { display: '区块', name: 'BLOCK_NAME' ,align: 'center', minWidth: 70,frozen:true},"
				+ "  { display: '接转战名称 ', name: 'BLOCKSTATION_NAME' ,align: 'center', minWidth: 70,hide:true},"
				+ "  { display: '锅炉名称', name: 'BOILER_NAME' ,align: 'center', minWidth: 60,frozen:true},"
				+ "  { display: '采集时间', name: 'REPORT_DATE' ,align: 'center', minWidth: 150,frozen:true},"
				+ "  { display: '运行状态', name: 'RUN_STATUS' ,align: 'center', minWidth: 70,frozen:true},"
				+ "  { display: '天然气', columns:"
				+ "	["
				+ "  { display: '天然气分离器压差(KPa)', name: 'GAS_INTO_PRESS' ,align: 'center', minWidth: 130},"
				+ "  { display: '天然气阀前压力(MPa)', name: 'GAS1_PRESS' ,align: 'center', minWidth: 130},"
				+ "	 { display: '火量(%)', name: 'FIRE_MEASURE' ,align: 'center', minWidth: 60},"
				+ "  { display: '天然气流量(m3/h)', name: 'GAS_FLOW' ,align: 'center', minWidth: 110}"
				+ "	]},"
				+ "  { display: '锅炉给水',columns: ["
				+ "  { display: '给水压力(MPa)', name: 'PUMPIN_PRESS' ,align: 'center', minWidth: 100},"
				+ "  { display: '给水温度(℃)', name: 'PUMPOUT_TEMP' ,align: 'center', minWidth: 120},"
				+ "  { display: '柱塞泵变频频率(Hz)', name: 'PUMPFC_FREQUENCY' ,align: 'center', minWidth: 130},"
				+ "  { display: '给水流量(t/h)', name: 'WATERSUPPLY_FLOW' ,align: 'center', minWidth: 90}"
				+"]},"  
				+ "{display:'蒸汽性质', columns:["
				+ "  { display: '蒸汽出口压力(MPa)', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 120},"
				+ "  { display: '蒸汽出口温度(℃)', name: 'STEAMOUT_TEMP' ,align: 'center', minWidth: 110},"
				+ "  { display: '蒸汽出口干度(%)', name: 'STEAM_DRYNESS' ,align: 'center', minWidth: 110}"
				+"]},"
				+ "  { display: '柱塞泵出口压力(MPa)', name: 'PUMPOUT_PRESS' ,align: 'center', minWidth: 120},"
				+" {display:'对流段',columns:["
				+ "  { display: '对流段入口压力(MPa)', name: 'CSIN_PRESS' ,align: 'center', minWidth: 130},"
				+ "  { display: '对流段入口温度(℃)', name: 'CSIN_TEMP' ,align: 'center', minWidth: 130},"
				+ "  { display: '对流段出口压力(MPa)', name: 'CSOUT_PRESS' ,align: 'center', minWidth: 130},"
				+ "  { display: '对流段出口温度(℃)', name: 'CSOUT_TEMP' ,align: 'center', minWidth: 130},"
				+ "  { display: '对流段压降(MPa)', name: 'CONVECTIONR_PRESS_REDUCTION' ,align: 'center', minWidth: 130}"
				+"]},"
				+"{display :'辐射段', columns:["
				+ "  { display: '辐射段入口压力(MPa)', name: 'RSIN_PRESS' ,align: 'center', minWidth: 130},"
				+ "  { display: '辐射段入口温度(℃)', name: 'RSIN_TEMP' ,align: 'center', minWidth: 130},"
				+ "  { display: '辐射段压降(MPa)', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 120},"
				+ "  { display: '辐射段出口管温(℃)', name: 'RS_TEMP' ,align: 'center', minWidth: 120}"
				+"]},"
				+"{display:'天然气', columns:["
				+ "  { display: '天然气阀后压力(MPa)', name: 'GAS2_PRESS' ,align: 'center', minWidth: 140},"
				+ "  { display: '膨胀管压力(MPa)', name: 'GAS3_PRESS' ,align: 'center', minWidth: 120}"
				+"]},"
				+ "  { display: '润滑油压力(MPa)', name: 'LUBE_PRESS' ,align: 'center', minWidth: 110},"
				+ "  { display: '润滑油温度(℃)', name: 'LUBE_TEMP' ,align: 'center', minWidth: 110},"
				+ "  { display: '鼓风机进风温度(℃)', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 120},"
				+ "  { display: '燃烧器喉温(℃)', name: 'BURNER_TEMP' ,align: 'center', minWidth: 110},"
				+ "  { display: '炉膛压力(Pa)', name: 'HEARTH_PRESS' ,align: 'center', minWidth: 110},"
				+ "  { display: '排烟温度(℃)', name: 'EJECTSMOKE_TEMP' ,align: 'center', minWidth: 110},"
				+"{display:'电机参数',columns:["
				+ "  { display: '电网电压(V)', name: 'SYSTEM_VOLTAGE' ,align: 'center', minWidth: 110},"
				+ "  { display: '柱塞泵电流(A)', name: 'PUMP_MOTOR_CURRENT' ,align: 'center', minWidth: 110},"
				+ "  { display: '柱塞泵电机温度(℃)', name: 'PUMP_MOTOR_TEMP' ,align: 'center', minWidth: 120},"
				+ "  { display: '鼓风机电流(A)', name: 'FAN_MOTOR_CURRENT' ,align: 'center', minWidth: 110}"
				+"]},"
				+ "  { display: '锅炉房可燃气体浓度(%)', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 120},"
				+ "  { display: '锅炉房H2S气体浓度(ppm)', name: 'H2S_DENSITY' ,align: 'center', minWidth: 120},"
				+ "  { display: '污水缓冲罐液位（m）', name: 'SEWAGE_BUFFER_TANK' ,align: 'center', minWidth: 120},"
				+ "  { display: '供气单位', name: 'STEAM_WORK_GROUP' ,align: 'center', minWidth: 70},"
				//+ "  { display: '采油站', name: 'OILDRILLING_STATION_NAME' ,align: 'center', minWidth: 70},"
				+ "  { display: '受汽单位', name: 'OILDRILLING_STATION_NAME' ,align: 'center', minWidth: 70},"
				+ "  { display: '注汽单位', name: 'STEAM_INJE_UNIT' ,align: 'center', minWidth: 70}"
				//+ "  { display: '湿蒸汽锅炉动态数据ID', name: 'RD_BOILER_COMMON_ID',align: 'center', minWidth: 120,hide: true},"
				//+ "  { display: '锅炉ID', name: 'BOILERID',align: 'center', minWidth: 120,hide: true}"
				
				/*+ "  { display: '接转站名称', name: 'BLOCKSTATION_NAME' ,align: 'center', minWidth: 70},"
				+ "  { display: '累积水量低四位', name: 'WATERSUPPLY_TOTAL_L' ,align: 'center', minWidth: 90},"
				+ "  { display: '累积水量高五位', name: 'WATERSUPPLY_TOTAL_H' ,align: 'center', minWidth: 90},"
				+ "  { display: '润滑油压力', name: 'LUBE_PRESS' ,align: 'center', minWidth: 70},"
				+ "  { display: '暖气压力', name: 'CENTRAL_HEAT_PRESS' ,align: 'center', minWidth: 60},"
				+ "  { display: '辐射段出口管温', name: 'RS_TEMP' ,align: 'center', minWidth: 70},"
				+ "  { display: '热风温度', name: 'HOTBLAST_TEMP' ,align: 'center', minWidth: 70},"
				+ "  { display: '泵进口温度', name: 'PUMPIN_TEMP' ,align: 'center', minWidth: 70},"
				+ "  { display: '对流段入口温度', name: '	CSIN_TEMP' ,align: 'center', minWidth: 70},"
				+ "  { display: '蒸汽出口压力', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 70},"
				
				+ "  { display: '膨胀管压力', name: 'GAS3_PRESS' ,align: 'center', minWidth: 70},"
				+ "  { display: '检漏压力', name: 'LEAK_HUNT_PRESS' ,align: 'center', minWidth: 70},"
				+ "  { display: '天然气累积量', name: 'GAS_TOTAL' ,align: 'center', minWidth: 70},"
				+ "  { display: '硫化氢浓度', name: 'H2S_DENSITY' ,align: 'center', minWidth: 70},"
				+ "  { display: '可燃气体浓度', name: 'FUEL GAS_DENSITY' ,align: 'center', minWidth: 70},"
				+ "  { display: '蒸汽出口干度', name: 'STEAM_DRYNESS' ,align: 'center', minWidth: 70},"
				+ "  { display: '辐射段压降', name: 'RS_PRESS_REDUCTION	' ,align: 'center', minWidth: 70},"*/
				
				+ " ],"+
				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'szglfs_searchSRGLFS.action',"+
				"delayLoad :true, "+
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"selectRowButtonOnly:true,"+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"enabledEdit:true,"+
				"rownumbers:true ,"+
				"frozen:true, "+
				"checkbox :false";
		
		

	//高干度锅炉分时数据
	public static final String PC_RPD_BOILER_HIGH_HOURS_T = " columns: ["
			+ "  { display: '受汽区块', name: 'BLOCK_NAME' ,align: 'center', minWidth: 100,frozen:true},"
			+ "  { display: '锅炉名称', name: 'BOILER_NAME' ,align: 'center', minWidth: 100,frozen:true},"
			+ "  { display: '采集时间', name: 'REPORT_DATE' ,align: 'center', minWidth: 130,frozen:true},"
			+ "  { display: '运行状态', name: 'RUN_STATUS' ,align: 'center', minWidth: 80,frozen:true},"
			//+ "  { display: '运行时间', name: 'RUNTIME' ,align: 'center', minWidth: 80},"
			//+ "  { display: '减少掺混器压降', name: 'MIXER_PRESS_REDUCTION' ,align: 'center', minWidth: 90},"
			+ "  { display: '天然气', columns:"
			+ "	["
			+ "  { display: '天然气分离器压差(kPa)', name: 'GAS_INTO_PRESS' ,align: 'center', minWidth: 80},"
			+ "  { display: '阀前压力(MPa)', name: 'GAS1_PRESS' ,align: 'center', minWidth: 60},"
			+ "	 { display: '火量(%)', name: 'FIRE_MEASURE' ,align: 'center', minWidth: 40},"
			+ "  { display: '天然气流量(m3/h)', name: 'GAS_FLOW' ,align: 'center', minWidth: 80}"
			+ "	]},"
			+ "  { display: '锅炉给水',columns: ["
			+ "  { display: '给水压力(MPa)', name: 'PUMPIN_PRESS' ,align: 'center', minWidth: 80},"
			+ "  { display: '给水温度(℃)', name: 'PUMPOUT_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '柱塞泵变频频率(Hz)', name: 'PUMPFC_FREQUENCY' ,align: 'center', minWidth: 90},"
			+ "  { display: '给水流量(t/h)', name: 'WATERSUPPLY_FLOW' ,align: 'center', minWidth: 60}"
			+"]},"  
			+ "{display:'蒸汽性质', columns:["
			+ "  { display: '蒸汽出口压力(MPa)', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '蒸汽出口温度(℃)', name: 'STEAMOUT_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '蒸汽出口干度(%)', name: 'STEAM_DRYNESS' ,align: 'center', minWidth: 80}"
			+"]},"
//			+ "{display:'运行关键参数', columns:["
//			+ "  { display: '辐射段压降(MPa)', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
//			+ "  { display: '过热段压降(MPa)', name: 'SUPERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
//			+ "  { display: '再热段压降(MPa)', name: 'RERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
//			+ "  { display: '掺混器压降(MPa)', name: 'MIXER_PRESS_REDUCTION' ,align: 'center', minWidth: 80}"
//			+"]},"
			+ "  { display: '柱塞泵出口压力(MPa)', name: 'PUMPOUT_PRESS' ,align: 'center', minWidth: 90},"
			+" {display:'对流段',columns:["
			+ "  { display: '对流段入口压力(MPa)', name: 'CSIN_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段入口温度(℃)', name: 'CSIN_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段出口压力(MPa)', name: 'CSOUT_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段出口温度(℃)', name: 'CSOUT_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '对流段压降(MPa)', name: 'CONVECTIONR_PRESS_REDUCTION' ,align: 'center', minWidth: 80}"
			+"]},"
			+"{display :'辐射段', columns:["
			+ "  { display: '辐射段入口压力(MPa)', name: 'RSIN_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段入口温度(℃)', name: 'RSIN_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段出口压力(MPa)', name: 'RS_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段出口温度(℃)', name: 'RSOUT_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段压降(Mpa)', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 90},"
			+ "  { display: '辐射段出口管温(℃)', name: 'RS_TEMP' ,align: 'center', minWidth: 90}"
			+"]},"
			+"{display :'再热段', columns:["
			+ "  { display: '再热段入口压力(MPa)', name: 'REHEAT_PRESS_ENTRANCE' ,align: 'center', minWidth: 90},"
			+ "  { display: '再热段入口温度(℃)', name: 'REHEAT_TEMP_ENTRANCE' ,align: 'center', minWidth: 90},"
			+ "  { display: '再热段出口管温(℃)', name: 'REHEAT_TEMP_EXPORT' ,align: 'center', minWidth: 90},"
			+ "  { display: '再热段压降(℃)', name: 'RERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 90}"
			+"]},"
			+"{display:'天然气', columns:["
			+ "  { display: '天然气阀后压力(MPa)', name: 'GAS2_PRESS' ,align: 'center', minWidth: 90},"
			+ "  { display: '膨胀管压力(MPa)', name: 'GAS3_PRESS' ,align: 'center', minWidth: 80}"
			+"]},"
			+ "  { display: '润滑油温度(℃)', name: 'LUBE_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '鼓风机进风温度', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '燃烧器喉温(℃)', name: 'BURNER_TEMP' ,align: 'center', minWidth: 80},"
			+ "  { display: '炉膛压力(Pa)', name: 'HEARTH_PRESS' ,align: 'center', minWidth: 60},"
			+ "  { display: '排烟温度(℃)', name: 'EJECTSMOKE_TEMP' ,align: 'center', minWidth: 60},"
			+"{display:'电机参数',columns:["
			+ "  { display: '电网电压(V)', name: 'SYSTEM_VOLTAGE' ,align: 'center', minWidth: 60},"
			+ "  { display: '柱塞泵电流(A)', name: 'PUMP_MOTOR_CURRENT' ,align: 'center', minWidth: 80},"
			+ "  { display: '柱塞泵电机温度(℃)', name: 'PUMP_MOTOR_TEMP' ,align: 'center', minWidth: 90},"
			+ "  { display: '鼓风机电流(A)', name: 'FAN_MOTOR_CURRENT' ,align: 'center', minWidth: 80}"
			+"]},"
			+ "  { display: '锅炉房可燃气体浓度(%)', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 110},"
			+ "  { display: 'H2s气体浓度(ppm)', name: 'H2S_DENSITY' ,align: 'center', minWidth: 80},"
			+ "  { display: '污水缓冲罐液位（m）', name: 'SEWAGE_BUFFER_TANK' ,align: 'center', minWidth: 120},"
			//+ "  { display: '鼓风机进风温度', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 90},"
			//+ "  { display: '锅炉房可燃气体浓度', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 110},"
			//+ "  { display: 'H2s气体浓度', name: 'H2S_DENSITY' ,align: 'center', minWidth: 80},"
			//+ "  { display: '系统总压降', name: 'SYSTEM_PRESS_REDUCTION' ,align: 'center', minWidth: 80},"
			+ "  { display: '供气单位', name: 'STEAM_WORK_GROUP' ,align: 'center', minWidth: 50},"
			+ "  { display: '采油站', name: 'OILDRILLING_STATION_NAME' ,align: 'center', minWidth: 80},"
			+ "  { display: '注汽单位', name: 'STEAM_INJE_UNIT' ,align: 'center', minWidth: 80}"
			+ " ],"+
		"height:'100%',"+
		"dataAction: 'server',"+
		"url:'ggdglfs_searchGGDRD.action',"+	
		"delayLoad :true, "+
		"pageParmName :'pageNo',"+
		"sortnameParmName:'sort',"+
		"sortorderParmName: 'order',  "+
		"pagesizeParmName:'rowsPerpage', "+
		"selectRowButtonOnly:true,"+
		"sePaper:true,"+
		//"enabledEdit: true,"+
		"pageSize:30 ,"+
		"rownumbers:true ,"+
		"frozen:true, "+
		"checkbox :true";
	
	//过热锅炉分时数据
	public static final String PC_RPD_BOILER_SUP_HOURS_T = " columns: ["
			+ "  { display: '区块', name: 'BLOCK_NAME' ,align: 'center', minWidth: 90,frozen:true},"
			+ "  { display: '锅炉名称', name: 'BOILER_NAME' ,align: 'center', minWidth: 100,frozen:true},"
			+ "  { display: '报表时间', name: 'REPORT_DATE' ,align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '运行状态', name: 'RUN_STATUS' ,align: 'center', minWidth: 70,frozen:true},"
			+ "  { display: '天然气', columns:"
			+ "	["
			+ "  { display: '站区来气压力(MPa)', name: 'GAS_INTO_PRESS' ,align: 'center', minWidth: 110},"
			+ "	 { display: '阀前压力(MPa)', name: 'GAS1_PRESS' ,align: 'center', minWidth: 100},"
			+ "	 { display: '火量(%)', name: 'FIRE_MEASURE' ,align: 'center', minWidth: 100},"
			+ "  { display: '天然气流量(m3/h)', name: 'GAS_FLOW' ,align: 'center', minWidth: 100}"
			+ "	]},"
			+ "  { display: '锅炉给水',columns: ["
			+ "  { display: '给水压力(MPa)', name: 'PUMPIN_PRESS' ,align: 'center', minWidth: 100},"
			+ "  { display: '给水温度(℃)', name: 'PUMPOUT_TEMP' ,align: 'center', minWidth: 100},"
			+ "  { display: '柱塞泵变频频率(Hz)', name: 'PUMPFC_FREQUENCY' ,align: 'center', minWidth: 110},"
			+ "  { display: '给水流量(t/h)', name: 'WATERSUPPLY_FLOW' ,align: 'center', minWidth: 100}"
			+"	]},"  
			+ "{display:'蒸汽性质', columns:["
			+ "  { display: '蒸汽出口压力(MPa)', name: 'STEAMIN_TEMP' ,align: 'center', minWidth: 110},"
			+ "  { display: '蒸汽出口温度(℃)', name: 'STEAMOUT_TEMP' ,align: 'center', minWidth: 100},"
			+ "  { display: '过热度(℃)', name: 'SUPERHEAT_DEGREE' ,align: 'center', minWidth: 100}"
			+"]},"
			+" {display:'运行关键参数',columns:["
			+ "  { display: '过热段出口温度(℃)', name: 'SUPERHEATOUT_TEMP' ,align: 'center', minWidth: 120},"
			+ "  { display: '分离器液位(mm)', name: 'SL_LEVEL' ,align: 'center', minWidth: 100},"
			+ "  { display: '辐射段压降(MPa)', name: 'RS_PRESS_REDUCTION' ,align: 'center', minWidth: 100},"
			+ "  { display: '过热段压降(MPa)', name: 'SUPERHEAT_PRESS_REDUCTION' ,align: 'center', minWidth: 100},"
			+ "  { display: '掺混器压降(MPa)', name: 'MIXER_PRESS_REDUCTION' ,align: 'center', minWidth: 100}"
			+"]},"
			+ "  { display: '柱塞泵出口压力(MPa)', name: 'PUMPOUT_PRESS' ,align: 'center', minWidth: 0100},"
			+" {display:'对流段',columns:["
			+ "  { display: '对流段入口压力(MPa)', name: 'CSIN_PRESS' ,align: 'center', minWidth: 120},"
			+ "  { display: '对流段入口温度(℃)', name: 'CSIN_TEMP' ,align: 'center', minWidth: 120},"
			+ "  { display: '对流段出口压力(MPa)', name: 'CSOUT_PRESS' ,align: 'center', minWidth: 120},"
			+ "  { display: '对流段出口温度(℃)', name: 'CSOUT_TEMP' ,align: 'center', minWidth: 120},"
			+ "  { display: '对流段压降(MPa)', name: 'CONVECTIONR_PRESS_REDUCTION' ,align: 'center', minWidth: 100}"
			+"]},"
			+"{display :'辐射段', columns:["
			+ "  { display: '辐射段入口压力(MPa)', name: 'RSIN_PRESS' ,align: 'center', minWidth: 120},"
			+ "  { display: '辐射段入口温度(℃)', name: 'RSIN_TEMP' ,align: 'center', minWidth:120},"
			+ "  { display: '辐射段出口压力(MPa)', name: 'RS_PRESS' ,align: 'center', minWidth: 120},"
			+ "  { display: '辐射段出口温度(℃)', name: 'RSOUT_TEMP' ,align: 'center', minWidth:120},"
			//+ "  { display: '辐射段压降(MPa)', name: 'RS_PRESS_REDUCTION	' ,align: 'center', minWidth: 100},"
			+ "  { display: '辐射段出口管温(℃)', name: 'RS_TEMP' ,align: 'center', minWidth: 120},"
			+ "  { display: '辐射段出口干度(%)', name: 'RS_DRYNESS' ,align: 'center', minWidth: 120}"
			+"]},"
			+ "  { display: '分离器出口压力(MPa)', name: 'SEPARATOR_PRESS_EXPORT' ,align: 'center', minWidth: 120},"
			+"{display :'过热段', columns:["
			+ "  { display: '过热段入口压力(MPa)', name: 'SUPERHEATIN_PRESS' ,align: 'center', minWidth: 120},"
			+ "  { display: '过热段入口温度(℃)', name: 'SUPERHEATIN_TEMP' ,align: 'center', minWidth: 120},"
			+ "  { display: '过热段出口压力(MPa)', name: 'SUPERHEATOUT_PRESS' ,align: 'center', minWidth: 120},"
			//+ "  { display: '过热段出口温度(℃)', name: 'SUPERHEATOUT_TEMP' ,align: 'center', minWidth: 100},"
			+ "  { display: '过热段出口管温(℃)', name: 'SUPERHEAT_TEMP' ,align: 'center', minWidth: 120},"
			+ "  { display: '过热段蒸汽流量(t/h)', name: 'SUPERHEATIN_FLOW' ,align: 'center', minWidth: 120}"
			+"]},"
			+ "  { display: '掺混水压力(MPa)', name: 'MIXER_PRESS_WATER' ,align: 'center', minWidth: 120},"
			+"{display:'天然气', columns:["
			+ "  { display: '天然气阀后压力(MPa)', name: 'GAS2_PRESS' ,align: 'center', minWidth: 120},"
			+ "  { display: '膨胀管压力(MPa)', name: 'GAS3_PRESS' ,align: 'center', minWidth: 100}"
			+"]},"
			+ "  { display: '润滑油温度(℃)', name: 'LUBE_TEMP' ,align: 'center', minWidth: 100},"
			+ "  { display: '助燃风压力(MPa)', name: 'FAN_AIR_EXPORT_PRESS' ,align: 'center', minWidth: 100},"
			+ "  { display: '鼓风机进风温度(℃)', name: 'FAN_AIR_INTAKE_TEMP' ,align: 'center', minWidth: 120},"
			+ "  { display: '燃烧器喉温(℃)', name: 'BURNER_TEMP' ,align: 'center', minWidth: 100},"
			+ "  { display: '炉膛压力(MPa)', name: 'HEARTH_PRESS' ,align: 'center', minWidth: 60},"
			+ "  { display: '排烟温度(℃)', name: 'EJECTSMOKE_TEMP' ,align: 'center', minWidth: 100},"
			+"{display:'电机参数',columns:["
			+ "  { display: '电网电压(V)', name: 'SYSTEM_VOLTAGE' ,align: 'center', minWidth: 100},"
			+ "  { display: '柱塞泵电流(A)', name: 'PUMP_MOTOR_CURRENT' ,align: 'center', minWidth: 100},"
			+ "  { display: '柱塞泵电机温度(℃)', name: 'PUMP_MOTOR_TEMP' ,align: 'center', minWidth: 120},"
			+ "  { display: '鼓风机电流(A)', name: 'FAN_MOTOR_CURRENT' ,align: 'center', minWidth: 100}"
			+"]},"
			+ "  { display: '锅炉房可燃气体浓度(%)', name: 'FUEL_GAS_DENSITY' ,align: 'center', minWidth: 110},"
			+ "  { display: '锅炉房H2S气体浓度(ppm)', name: 'H2S_DENSITY' ,align: 'center', minWidth: 110},"
			+ "  { display: '污水缓冲罐液位（m）', name: 'SEWAGE_BUFFER_TANK' ,align: 'center', minWidth: 120},"
			+ "  { display: '供气单位', name: 'STEAM_WORK_GROUP' ,align: 'center', minWidth: 100},"
			+ "  { display: '受汽单位', name: 'OILDRILLING_STATION_NAME' ,align: 'center', minWidth: 90},"
			+ "  { display: '注汽单位', name: 'STEAM_INJE_UNIT' ,align: 'center', minWidth: 90}"
			
			+ " ],"+

				"height:'100%',"+
				"dataAction: 'server',"+
				"url:'grglfs_searchGrglRPD.action',"+
				"delayLoad :true, "+
				"pageParmName :'pageNo',"+
				"sortnameParmName:'sort',"+
				"sortorderParmName: 'order',  "+
				"pagesizeParmName:'rowsPerpage', "+
				"selectRowButtonOnly:true,"+
				"sePaper:true,"+
				"pageSize:30 ,"+
				"rownumbers:true ,"+
				"frozen:true, "+
				"checkbox :false";
	
	
	//蒸汽配气撬动态
	public static final String ZQPQQ_PAGE_GRID = " columns: ["
			+ "  { display: '配汽撬名称', name: 'steam_name',align: 'center', minWidth: 100,frozen:true},"
			+ "  { display: '采集时间', name: 'acquisition_time',align: 'center', minWidth: 150,frozen:true},"
			
			+ " { display: '1线', columns:"
			+ "	["
			+ "		{ display: '压力（MPa）', name: 'pt_101', align: 'center', width: 80},"
			+ "		{ display: '温度（℃）', name: 'tt_101', width: 80, align: 'center'},"
			+ "		{ display: '瞬时流量（m^3/h）', name: 'fit_101', width: 80, align: 'center'},"
			+ "		{ display: '累积流量（m^3）', name: 'fiq_101', width: 80, align: 'center'}"			
			+ "	]},"
			
			+ " { display: '2线', columns:"
			+ "	["
			+ "		{ display: '压力（MPa）', name: 'pt_102', align: 'center', width: 80},"
			+ "		{ display: '温度（℃）', name: 'tt_102', width: 80, align: 'center'},"
			+ "		{ display: '瞬时流量（m^3/h）', name: 'fit_102', width: 80, align: 'center'},"
			+ "		{ display: '累积流量（m^3）', name: 'fiq_102', width: 80, align: 'center'}"				
			+ "	]},"
			
			+ " { display: '3线', columns:"
			+ "	["
			+ "		{ display: '压力（MPa）', name: 'pt_103', align: 'center', width: 80},"
			+ "		{ display: '温度（℃）', name: 'tt_103', width: 80, align: 'center'},"
			+ "		{ display: '瞬时流量（m^3/h）', name: 'fit_103', width: 80, align: 'center'},"
			+ "		{ display: '累积流量（m^3）', name: 'fiq_103', width: 80, align: 'center'}"				
			+ "	]},"				
		
			+ " { display: '4线', columns:"
			+ "	["
			+ "		{ display: '压力（MPa）', name: 'pt_104', align: 'center', width: 80},"
			+ "		{ display: '温度（℃）', name: 'tt_104', width: 80, align: 'center'},"
			+ "		{ display: '瞬时流量（m^3/h）', name: 'fit_104', width: 80, align: 'center'},"
			+ "		{ display: '累积流量（m^3）', name: 'fiq_104', width: 80, align: 'center'}"		
			+ "	]},"				
			
			+ " { display: '5线', columns:"
			+ "	["
			+ "		{ display: '压力（MPa）', name: 'pt_105', align: 'center', width: 80},"
			+ "		{ display: '温度（℃）', name: 'tt_105', width: 80, align: 'center'},"
			+ "		{ display: '瞬时流量（m^3/h）', name: 'fit_105', width: 80, align: 'center'},"
			+ "		{ display: '累积流量（m^3）', name: 'fiq_105', width: 80, align: 'center'}"					
			+ "	]}"				
			
			+ " ],"+

		"height:'100%',"+
		"dataAction: 'server',"+
		"url:'zqpqq_searchZqpqqdt.action',"+
		"delayLoad :true, "+
		"pageParmName :'pageNo',"+
		"sortnameParmName:'sort',"+
		"sortorderParmName: 'order',  "+
		"pagesizeParmName:'rowsPerpage', "+
		"selectRowButtonOnly:true,"+
		"sePaper:true,"+
		"pageSize:30 ,"+
		"rownumbers:true ,"+
		"frozen:true, "+
		"checkbox :false";
	
	
	
	//
	public static final String FXJL2CY_PAGE_GRID = " columns: ["
			+ "  { display: '时间', name: 'report_date',align: 'center', minWidth: 150,frozen:true},"
			
			+ " { display: '18-2A线', columns:"
			+ "	["
			+ "		{ display: '累计液量（m3）', name: 'FIT_HH_10101AV', width: 80, align: 'center'},"
			+ "		{ display: '对比液量（m3）', name: 'DB10101', width: 80, align: 'center'}"			
			+ "	]},"
			
			+ " { display: '18-2B线', columns:"
			+ "	["
			+ "		{ display: '累计液量（m3）', name: 'FIT_HH_10102AV', width: 80, align: 'center'},"
			+ "		{ display: '对比液量（m3）', name: 'DB10102', width: 80, align: 'center'}"			
			+ "	]},"
			
			+ " { display: '18-3（1B）线', columns:"
			+ "	["
			+ "		{ display: '累计液量（m3）', name: 'FIT_HH_10103AV', width: 80, align: 'center'},"
			+ "		{ display: '对比液量（m3）', name: 'DB10103', width: 80, align: 'center'}"				
			+ "	]},"				
		
			+ " { display: '18-4（1B）线', columns:"
			+ "	["
			+ "		{ display: '累计液量（m3）', name: 'FIT_HH_10104AV', width: 80, align: 'center'},"
			+ "		{ display: '对比液量（m3）', name: 'DB10104', width: 80, align: 'center'}"		
			+ "	]},"				
			
			+ " { display: '18-1（B）线', columns:"
			+ "	["
			+ "		{ display: '累计液量（m3）', name: 'FIT_HH_109', width: 80, align: 'center'},"
			+ "		{ display: '对比液量（m3）', name: 'DB109', width: 80, align: 'center'}"				
			+ "	]},"				
			+ " { display: '6000线', columns:"
			+ "	["
			+ "		{ display: '累计液量（m3）', name: 'FIT_HH_10108AV', width: 80, align: 'center'},"
			+ "		{ display: '对比液量（m3）', name: 'DB10108', width: 80, align: 'center'}"				
			+ "	]},"	
			+ "  { display: '采油二站液量', name: 'FC2YE' ,align: 'center', minWidth: 100},"
			+ "  { display: '对比昨日液量', name: 'DB_FC2YE' ,align: 'center', minWidth: 100},"
			+ "  { display: '采油三站液量', name: 'FC3YE' ,align: 'center', minWidth: 100},"
			+ "  { display: '对比昨日液量', name: 'DB_FC3YE' ,align: 'center', minWidth: 100}"
			+ " ],"+

		"height:'100%',"+
		"dataAction: 'server',"+
		"url:'fxjl2cy_searchDatas.action',"+
		"delayLoad :true, "+
		"pageParmName :'pageNo',"+
		"sortnameParmName:'sort',"+
		"sortorderParmName: 'order',  "+
		"pagesizeParmName:'rowsPerpage', "+
		"selectRowButtonOnly:true,"+
		"sePaper:true,"+
		"pageSize:30 ,"+
		"rownumbers:true ,"+
		"frozen:true, "+
		"checkbox :false";
	
	
	
	//宇澄汽线温压动态数据
	public static final String YCGX_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'acquisition_time',align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '管线名称', name: 'steam_name',align: 'center', minWidth: 150,frozen:true},"
			+ " { display: '1#测量点', columns:"
			+ "	["
			+ "		{ display: '压力（MPa）', name: 'pt_101', width: 80, align: 'center'},"
			+ "		{ display: '温度（℃）', name: 'tt_104', width: 80, align: 'center'}"			
			+ "	]},"
			
			+ " { display: '2#测量点', columns:"
			+ "	["
			+ "		{ display: '压力（MPa）', name: 'pt_102', width: 80, align: 'center'},"
			+ "		{ display: '温度（℃）', name: 'tt_103', width: 80, align: 'center'}"			
			+ "	]},"
			
			+ " { display: '3#测量点', columns:"
			+ "	["
			+ "		{ display: '压力（MPa）', name: 'pt_103', width: 80, align: 'center'},"
			+ "		{ display: '温度（℃）', name: 'tt_102', width: 80, align: 'center'}"				
			+ "	]},"				
		
			+ " { display: '4#测量点', columns:"
			+ "	["
			+ "		{ display: '压力（MPa）', name: 'pt_104', width: 80, align: 'center'},"
			+ "		{ display: '温度（℃）', name: 'tt_101', width: 80, align: 'center'}"		
			+ "	]}"				
			
			+ " ],"+

		"height:'100%',"+
		"dataAction: 'server',"+
		"url:'ycgx_searchYcgxdt.action',"+
		"delayLoad :true, "+
		"pageParmName :'pageNo',"+
		"sortnameParmName:'sort',"+
		"sortorderParmName: 'order',  "+
		"pagesizeParmName:'rowsPerpage', "+
		"selectRowButtonOnly:true,"+
		"sePaper:true,"+
		"pageSize:30 ,"+
		"rownumbers:true ,"+
		"frozen:true, "+
		"checkbox :false";
	
	
	
	
	//SAGD18-3#动态数据-全部
	public static final String SAGD1HRDT_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'acquisition_time',align: 'center', minWidth: 150,frozen:true},"
			+ "  { display: '主集油线温度（℃）', name: 'ZJYX_TEM',align: 'center', minWidth: 100},"
			+ "  { display: '循环集油线温度（℃）', name: 'XHJY_TEM',align: 'center', minWidth: 100},"
			+ " { display: '1#分离器', columns:"
			+ "	["
			+ "		{ display: '汽相出口压力（MPa）', name: 'FLQ_QCKYL1', align: 'center', width: 140},"
			+ "		{ display: '液相出口压力（MPa）', name: 'FLQ_YCKYL1', align: 'center', width: 140},"
			+ "		{ display: '液位高度（mm）', name: 'FLQ_YWGD1', width: 140, align: 'center'},"
			+ "		{ display: '汽相电控阀开度（%）', name: 'FLQ_QDKFKD1', width: 140, align: 'center'},"
			+ "		{ display: '液相电控阀开度（%）', name: 'FLQ_YDKFKD1', width: 140, align: 'center'}"			
			+ "	]},"
			
			+ " { display: '2#分离器', columns:"
			+ "	["
			+ "		{ display: '汽相出口压力（MPa）', name: 'FLQ_QCKYL2', align: 'center', width: 140},"
			+ "		{ display: '液相出口压力（MPa）', name: 'FLQ_YCKYL2', align: 'center', width: 140},"
			+ "		{ display: '液位高度（mm）', name: 'FLQ_YWGD2', width: 140, align: 'center'},"
			+ "		{ display: '汽相电控阀开度（%）', name: 'FLQ_QDKFKD2', width: 140, align: 'center'},"
			+ "		{ display: '液相电控阀开度（%）', name: 'FLQ_YDKFKD2', width: 140, align: 'center'}"			
			+ "	]},"
			
			+ " { display: '3#分离器', columns:"
			+ "	["
			+ "		{ display: '汽相出口压力（MPa）', name: 'FLQ_QCKYL3', align: 'center', width: 140},"
			+ "		{ display: '液相出口压力（MPa）', name: 'FLQ_YCKYL3', align: 'center', width: 140},"
			+ "		{ display: '液位高度（mm）', name: 'FLQ_YWGD3', width: 140, align: 'center'},"
			+ "		{ display: '汽相电控阀开度（%）', name: 'FLQ_QDKFKD3', width: 140, align: 'center'},"
			+ "		{ display: '液相电控阀开度（%）', name: 'FLQ_YDKFKD3', width: 140, align: 'center'}"		
			+ "	]},"				
		
			+ " { display: '1#泵', columns:"
			+ "	["
			+ "		{ display: '进口压力（MPa）', name: 'PUMP_IN_PRESS1', width: 100, align: 'center'},"
			+ "		{ display: '进口温度（℃）', name: 'PUMP_IN_TEM1', width: 100, align: 'center'},"
			+ "		{ display: '出口压力（MPa）', name: 'PUMP_OUT_PRESS1', width: 100, align: 'center'},"
			+ "		{ display: '出口温度（℃）', name: 'PUMP_OUT_TEM1', width: 100, align: 'center'},"
			+ "		{ display: '泵启停状态（启0/停1）', name: 'PUMP_ZT1', width: 100, align: 'center'},"
			+ "		{ display: '泵频 （Hz）', name: 'PUMP_PL1', width: 100, align: 'center'}"
			+ "	]},"				
			
			+ " { display: '2#泵', columns:"
			+ "	["
			+ "		{ display: '进口压力（MPa）', name: 'PUMP_IN_PRESS2', width: 100, align: 'center'},"
			+ "		{ display: '进口温度（℃）', name: 'PUMP_IN_TEM2', width: 100, align: 'center'},"
			+ "		{ display: '出口压力（MPa）', name: 'PUMP_OUT_PRESS2', width: 100, align: 'center'},"
			+ "		{ display: '出口温度（℃）', name: 'PUMP_OUT_TEM2', width: 100, align: 'center'},"
			+ "		{ display: '泵启停状态（启0/停1）', name: 'PUMP_ZT2', width: 100, align: 'center'},"
			+ "		{ display: '泵频 （Hz）', name: 'PUMP_PL2', width: 100, align: 'center'}"
			+ "	]},"				
			
			+ " { display: '3#泵', columns:"
			+ "	["
			+ "		{ display: '进口压力（MPa）', name: 'PUMP_IN_PRESS3', width: 100, align: 'center'},"
			+ "		{ display: '进口温度（℃）', name: 'PUMP_IN_TEM3', width: 100, align: 'center'},"
			+ "		{ display: '出口压力（MPa）', name: 'PUMP_OUT_PRESS3', width: 100, align: 'center'},"
			+ "		{ display: '出口温度（℃）', name: 'PUMP_OUT_TEM3', width: 100, align: 'center'},"
			+ "		{ display: '泵启停状态（启0/停1）', name: 'PUMP_ZT3', width: 100, align: 'center'},"
			+ "		{ display: '泵频 （Hz）', name: 'PUMP_PL3', width: 100, align: 'center'}"
			+ "	]},"								
			
			+ " { display: '4#泵', columns:"
			+ "	["
			+ "		{ display: '进口压力（MPa）', name: 'PUMP_IN_PRESS4', width: 100, align: 'center'},"
			+ "		{ display: '进口温度（℃）', name: 'PUMP_IN_TEM4', width: 100, align: 'center'},"
			+ "		{ display: '出口压力（MPa）', name: 'PUMP_OUT_PRESS4', width: 100, align: 'center'},"
			+ "		{ display: '出口温度（℃）', name: 'PUMP_OUT_TEM4', width: 100, align: 'center'},"
			+ "		{ display: '泵启停状态（启0/停1）', name: 'PUMP_ZT4', width: 100, align: 'center'},"
			+ "		{ display: '泵频 （Hz）', name: 'PUMP_PL4', width: 100, align: 'center'}"
			+ "	]},"								
			
			+ " { display: '5#泵', columns:"
			+ "	["    
			+ "		{ display: '进口压力（MPa）', name: 'PUMP_IN_PRESS5', width: 100, align: 'center'},"
			+ "		{ display: '进口温度（℃）', name: 'PUMP_IN_TEM5', width: 100, align: 'center'},"
			+ "		{ display: '出口压力（MPa）', name: 'PUMP_OUT_PRESS5', width: 100, align: 'center'},"
			+ "		{ display: '出口温度（℃）', name: 'PUMP_OUT_TEM5', width: 100, align: 'center'},"
			+ "		{ display: '泵启停状态（启0/停1）', name: 'PUMP_ZT5', width: 100, align: 'center'},"
			+ "		{ display: '泵频 （Hz）', name: 'PUMP_PL5', width: 100, align: 'center'}"
			+ "	]}"				
			
			+ " ],"+

		"height:'100%',"+
		"dataAction: 'server',"+
		"url:'sagdhrdt_searchDatas.action',"+
		"delayLoad :true, "+
		"pageParmName :'pageNo',"+
		"sortnameParmName:'sort',"+
		"sortorderParmName: 'order',  "+
		"pagesizeParmName:'rowsPerpage', "+
		"selectRowButtonOnly:true,"+
		"sePaper:true,"+
		"delayLoad :true, "+
		"pageSize:30 ,"+
		"rownumbers:true ,"+
		"frozen:true, "+
		"checkbox :false";

	
	
	
	public static final String FC160WF_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'acquisition_time',align: 'center', width: 120,frozen:true},"
			+ "  { display: '液位', name: 'level1',align: 'center', width: 120,frozen:true},"
			+ " { display: '1#泵', columns:"
			+ "	["
			+ "		{ display: '状态（停0/启1）', name: 'pump_zt1', width: 130, align: 'center'},"
			+ "		{ display: '频率', name: 'pump_pl1', width: 130, align: 'center'}"			
			+ "	]},"
			
			+ " { display: '2#泵', columns:"
			+ "	["
			+ "		{ display: '状态（停0/启1）', name: 'pump_zt2', width: 130, align: 'center'},"
			+ "		{ display: '频率', name: 'pump_pl2', width: 130, align: 'center'}"			
			+ "	]},"
			
			+ " { display: '3#泵', columns:"
			+ "	["
			+ "		{ display: '状态（停0/启1）', name: 'pump_zt3', width: 130, align: 'center'},"
			+ "		{ display: '频率', name: 'pump_pl3', width: 130, align: 'center'}"				
			+ "	]}"						
			
			+ " ],"+

		"height:'100%',"+
		"dataAction: 'server',"+
		"url:'fc160wf_searchDatas.action',"+
		"delayLoad :true, "+
		"pageParmName :'pageNo',"+
		"sortnameParmName:'sort',"+
		"sortorderParmName: 'order',  "+
		"pagesizeParmName:'rowsPerpage', "+
		"selectRowButtonOnly:true,"+
		"sePaper:true,"+
		"pageSize:30 ,"+
		"rownumbers:true ,"+
		"frozen:true, "+
		"checkbox :false";
	
	
	
	public static final String SAGD1LNG_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'acquisition_time',align: 'center', width: 120,frozen:true},"
			+ "	 { display: '1#罐液位', name: 'LIT_1', width: 100, align: 'center'},"
			+ "	 { display: '2#罐液位', name: 'LIT_2', width: 100, align: 'center'},"
			+ "	 { display: '3#罐液位', name: 'LIT_3', width: 100, align: 'center'},"
			+ "	 { display: '4#罐液位', name: 'LIT_4', width: 100, align: 'center'},"
			+ "	 { display: '5#罐液位', name: 'LIT_5', width: 100, align: 'center'},"
			+ "	 { display: '6#罐液位', name: 'LIT_6', width: 100, align: 'center'},"
			+ "	 { display: '7#罐液位', name: 'LIT_7', width: 100, align: 'center'},"
			+ "	 { display: '8#罐液位', name: 'LIT_8', width: 100, align: 'center'},"
			+ "	 { display: '9#罐液位', name: 'LIT_9', width: 100, align: 'center'},"
			+ "	 { display: '10#罐液位', name: 'LIT_10', width: 100, align: 'center'},"
			+ "	 { display: '11#罐液位', name: 'LIT_11', width: 100, align: 'center'},"
			+ "	 { display: '12#罐液位', name: 'LIT_12', width: 100, align: 'center'},"
			+ "	 { display: '13#罐液位', name: 'LIT_13', width: 100, align: 'center'},"
			+ "	 { display: '14#罐液位', name: 'LIT_14', width: 100, align: 'center'}"
			
			+ " ],"+

		"height:'100%',"+
		"dataAction: 'server',"+
		"url:'sagdhrdt_searchDataslng.action',"+
		"delayLoad :true, "+
		"pageParmName :'pageNo',"+
		"sortnameParmName:'sort',"+
		"sortorderParmName: 'order',  "+
		"pagesizeParmName:'rowsPerpage', "+
		"selectRowButtonOnly:true,"+
		"sePaper:true,"+
		"pageSize:30 ,"+
		"rownumbers:true ,"+
		"frozen:true, "+
		"checkbox :false";
	
	public static final String QTJC_PAGE_GRID = " columns: ["
			+ "  { display: '采集时间', name: 'acquisition_time',align: 'center', width: 120,frozen:true},"
			+ "	 { display: '可燃气体浓度1', name: 'KRQT_ND1', width: 100, align: 'center'},"
			+ "	 { display: '可燃气体浓度2', name: 'KRQT_ND2', width: 100, align: 'center'},"
			+ "	 { display: '有毒气体浓度1', name: 'YDQT_ND1', width: 100, align: 'center'},"
			+ "	 { display: '有毒气体浓度2', name: 'YDQT_ND2', width: 100, align: 'center'},"
			+ "	 { display: '有毒气体浓度3', name: 'YDQT_ND3', width: 100, align: 'center'},"
			+ "	 { display: '有毒气体浓度4', name: 'YDQT_ND4', width: 100, align: 'center'},"
			+ "	 { display: '有毒气体浓度5', name: 'YDQT_ND5', width: 100, align: 'center'},"
			+ "	 { display: '有毒气体浓度6', name: 'YDQT_ND6', width: 100, align: 'center'}"			
			+ " ],"+

		"height:'100%',"+
		"dataAction: 'server',"+
		"url:'sagdhrdt_searchDatasqtjc.action',"+
		"delayLoad :true, "+
		"pageParmName :'pageNo',"+
		"sortnameParmName:'sort',"+
		"sortorderParmName: 'order',  "+
		"pagesizeParmName:'rowsPerpage', "+
		"selectRowButtonOnly:true,"+
		"sePaper:true,"+
		"pageSize:30 ,"+
		"rownumbers:true ,"+
		"frozen:true, "+
		"checkbox :false";
	
	
	
}
