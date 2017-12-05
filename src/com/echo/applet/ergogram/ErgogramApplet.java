package com.echo.applet.ergogram;

import com.echo.applet.ChartApplet;
import com.echo.util.StringUtils;

public class ErgogramApplet extends ChartApplet {
	
	public void init()
	{
		super.init();
		createChart();
		//setPara();
		main();
		panel.setChart(chart);
		//setVisible(true);
		//repaint();
		
	}
	
	/**
	 * 创建chart
	 */
	protected void createChart()
	{
		Ergogram chart = new Ergogram();
		this.chart = chart;
		
	}

	private void setPara()
	{
		//初始化图形格式数据
		ErgogramForm form = ((Ergogram)chart).form;
		form.objectField = getParameter("objectField");
		form.timeField = getParameter("timeField");
		form.title = getParameter("title");
		form.setTitleColor(getParameter("titleColor"));
		form.setTitleFont(getParameter("titleFont"),getParameter("titleFontSize"));
		form.setChartClosed(getParameter("chartClosed"));
		form.setChartLineColors(getParameter("chartLinecColorStr"));
		form.chartLineWidth=StringUtils.parseInt(getParameter("chartLineWidth"), 1);
		form.setBgColor(getParameter("bgColor"));
		form.setAxesLineColor(getParameter("axeslineColor"));
		form.axesLineWidth = StringUtils.parseInt(getParameter("axesLineWidth"), 1);
		form.setGridLineColor(getParameter("gridLineColor"));
		form.gridLineWidth = StringUtils.parseInt(getParameter("gridLineWidth"), 0);
		form.setAideTextColor(getParameter("aideTextFontColor"));
		form.setAideTextFont(getParameter("aideTextFont"), getParameter("aideTextFontSize"));
		form.setAxesFontColor(getParameter("axesFontColor"));
		form.setAxesFont(getParameter("axesFont"), getParameter("axesFontSize"));
		form.fixChartWHScale=StringUtils.parseFloat(getParameter("fixChartWHScale"), 0.625f);
		form.minChartWidth = StringUtils.parseFloat(getParameter("minChartWidth"), 100f);
		form.minChartHeight= StringUtils.parseFloat(getParameter("minChartHeight"), 62.5f);
		form.axesXUnit = getParameter("axesXunit");
		form.axesXTagNum = StringUtils.parseInt(getParameter("axesXtagNum"),6);
		form.setAxesXGridLine(getParameter("axesXshowGrid"));
		form.axesYUnit = getParameter("axesYunit");
		form.axesYTagNum = StringUtils.parseInt(getParameter("axesYtagNum"), 6);
		form.setAxesYGridLine(getParameter("axesYshowGrid"));
		form.axesYBlankScale = StringUtils.parseInt(getParameter("axesYblankScale"), 10);
		form.axesYMaxLimit = StringUtils.parseFloat(getParameter("axesYmaxLimit"), Float.MAX_VALUE);
		form.axesYMinLimit = StringUtils.parseFloat(getParameter("axesYminLimit"),Float.MIN_VALUE);
		form.setMarkingPointNos(getParameter("markingPointFields"));
		//辅助标志点参数
		/*strArr1 = StringUtils.split(getParameter("markingPointNoFields"),",");
		if(strArr1!=null)
		{
			for(int i = 0;i<strArr1.length;i++)
			{
				form.markingPointNoFields.add(strArr1[i]);
			}
		}*/
		
		String[] strArr1 = StringUtils.split(getParameter("axesYreferenceLines"),";");
		String[] strArr2;
		if (strArr1!=null)
		{
			for (int i = 0; i < strArr1.length; i++)
			{
				strArr2 = StringUtils.split(strArr1[i], ",");
				if(strArr2!=null && strArr2.length >=4)
				{
					form.addYRefLine(strArr2[0], strArr2[1], strArr2[2], strArr2[3]);
				}
			}
		}
		
		//辅助文本信息
		strArr1 = StringUtils.split(getParameter("aideTexts"), ";");
		if (strArr1!=null)
		{
			for (int i = 0; i < strArr1.length; i++)
			{
				strArr2 = StringUtils.split(strArr1[i], ",");
				if(strArr2!=null && strArr2.length >=3)
				{
					form.addAideText(strArr2[0], strArr2[1], strArr2[2]);
				}
			}
		}
		
		//初始化图形数据
		createData();
	}
	public  void main()
	{
		//Ergogram chart = new Ergogram();
		//this.chart = chart;
		//初始化图形格式数据
		ErgogramForm form = ((Ergogram)chart).form;
		form.objectField = "p01";
		form.timeField = "p02";
		form.title = "地面功图";
		form.setTitleColor("#000000");
		form.setTitleFont("黑体","20");
		form.setChartClosed("true");
		form.setChartLineColors("#0");
		form.chartLineWidth=1;
		form.setBgColor("#FFFFFF");
		form.setAxesLineColor("#000000");
		form.axesLineWidth = 1;
		form.setGridLineColor("#000000");
		form.gridLineWidth = 0;
		form.setAideTextColor("#000000");
		form.setAideTextFont("宋体", "12");
		form.setAxesFontColor("#000000");
		form.setAxesFont("宋体", "12");
		form.fixChartWHScale= 0.625f;
		form.minChartWidth =  100f;
		form.minChartHeight=  62.5f;
		form.axesXUnit = "m";
		form.axesXTagNum = 6;
		form.setAxesXGridLine("true");
		form.axesYUnit = "KN";
		form.axesYTagNum = 6;
		form.setAxesYGridLine("true");
		form.axesYBlankScale = 15;
		form.axesYMaxLimit = 150;
		form.axesYMinLimit = 0;
		form.setMarkingPointNos("P34,P35,P36,P37");
		String[] strArr1 = StringUtils.split("",";");
		String[] strArr2;
		if (strArr1!=null)
		{
			for (int i = 0; i < strArr1.length; i++)
			{
				strArr2 = StringUtils.split(strArr1[i], ",");
				if(strArr2!=null && strArr2.length >=4)
				{
					form.addYRefLine(strArr2[0], strArr2[1], strArr2[2], strArr2[3]);
				}
			}
		}
		
		strArr1 = StringUtils.split("冲程,P03,m;冲次,P04,次/分钟;--,--,--;最小负荷,P06,KN;最大负荷,P05,KN", ";");
		if (strArr1!=null)
		{
			for (int i = 0; i < strArr1.length; i++)
			{
				strArr2 = StringUtils.split(strArr1[i], ",");
				if(strArr2!=null && strArr2.length >=3)
				{
					form.addAideText(strArr2[0], strArr2[1], strArr2[2]);
				}
			}
		}
		
		//初始化图形数据
		ErgogramData ergData ;
		strArr1 = StringUtils.split("风21-17,1/25/2008 10:0:0,000046410000479800204970004051580060536201005707014059580190620902406444029066950370707104407338051073060590718106707040079069460880704009707165107072911170732213207197143071031540707116507118181072441930733820507322217072592290719724507150258071812700721228207244294072753100724432207103334069933450694635606946371070083820704039207024402069614110689942306867432068994400707144807416457075264640758947007306475070084800671148606648490067584930678949506820497068674990685249906820499067584990671149706507495062724920614648906005485059114800594247405974468059894620597445505911445058014370581742905832420058804110591139805942388059273780595836705958356059893410603633006052318060363060600529405942278059422660594225405927241058482290564421305001201045621890421717703857161034021500313613903151128032611170349610403637094036060850352807603386067033710560344904903559042036370350362202903606022035430170354301303590009036530060379400304092001042490000439000004468,011497750094994600750131006503280055053700450767003510310025132800051638000519220015213700552257012522860215226003152223040522110495223305752275066523090765231908852306100522891135229012452317135523601465239715852407171523891845236019852342210523472225237023452389246523792595233127352260287521953005216031252160323521773335218434452161355521193665209037552115382522133875236539152518395526084015258941052462420522694295207943751951441519154435195544252029442520874425209544352045445519454475181544851674449515334495140444751295445512174415117543551162429511644215116341451145406511113995107539051055381510643705109935851144346511833345120332251206310512022985120228551210272512202585122324551210232511772195112020851027197508731885063218250287179498531774937817548940171486171634845315248440139485241264862911548693105486940984864909148603084485930754862806748688059487370534874904848725045486930424869103848745033488600274901402249179018493350154947901349621,;P34,11;P35,58;P36,96;P37,118;p38,21.91;p39,-3.1;P06,5;P07,2.26;P15,31.35;P16,76.03", "|");
		if (strArr1!=null)
		{
			for (int i = 0; i < strArr1.length; i++)
			{
//				ergData = DetailErgogramData.create(strArr1[i]);
				ergData = ErgogramData.create(strArr1[i]);
				if (ergData!=null)
				{
					((Ergogram)chart).addSeries(ergData);
				}
			}
		}
		
	}
	
	protected int createData()
	{
		int result=0;
		//初始化图形数据
		String[] strArr1;
		ErgogramData ergData ;
		strArr1 = StringUtils.split(getParameter("datas"), "|");
		if (strArr1!=null)
		{
			for (int i = 0; i < strArr1.length; i++)
			{
				ergData = ErgogramData.create(strArr1[i]);
				if (ergData!=null)
				{
					result=((Ergogram)chart).addSeries(ergData);
				}
			}
		}
		return result;
		
	}
	
}
