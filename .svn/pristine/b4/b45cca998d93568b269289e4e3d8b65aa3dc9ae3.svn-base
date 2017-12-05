package com.echo.beans;
/**
 * 
 * @ClassName: PageControl 
 * @Description: 此类中方法的功能只要是根据传递进来的当前页和总记录数，可以计算出当前页、上一页、下一页、总页数、
 * 	当前页的起始记录数，当前页的结束记录数和总的记录数,在方法里面也指定了每页显示的行数 
 * @author yanloong
 * @date 2013-7-8 下午04:36:44
 */
public class PageControl {

	private int curpage;	//当前页
	private int prepage;	//上一页
	private int nextpage;	//下一页
	private int totalpage;	//总页数
	private int start;		//当前页起始记录
	private int end;		//当前页结束记录
	private int totalnum;	//总记录数
	private int int_num ;//每页显示行数
	
	private int temp_curpage;
	private int temp_totalpage;
	private int temp_start;
	private int temp_end;
	private int temp_totalnum;
	

	public void init(int curpage,int totalnum)
	{
//		总记录数
		temp_totalnum = totalnum;
		
//		总页数
	//	temp_totalpage = (int)Math.ceil((double)totalnum/(double)int_num);//总记录数/每页显示行数
		 
				temp_totalpage = totalnum / int_num+ 1; 
			


		
//		当前页
		if (curpage > 1)
		{
			if (curpage > temp_totalpage)
			{
				temp_curpage = temp_totalpage;
			}
			else
			{
				temp_curpage = curpage;
			}
		}
		else
		{
			temp_curpage = 1;
		}
		
//		当页起始记录
		temp_start = (temp_curpage-1) * int_num + 1;
		
//		当页结束记录
		temp_end = temp_curpage * int_num;
		if (temp_end > temp_totalnum)
		{
			temp_end = temp_totalnum;
		}
		
		setCurpage();
		setPrepage();
		setNextpage();
		setTotalpage();
		setStart();
		setEnd();
		setTotalnum();
	}
	
	public void setCurpage()
	{
		this.curpage = temp_curpage;
	}
	public int getCurpage()
	{
		return this.curpage;
	}
	
	public void setPrepage()
	{
		if (temp_curpage - 1 < 1)
		{
			this.prepage = 1;
		}
		else
		{
			this.prepage = temp_curpage - 1;
		}		
	}
	public int getPrepage()
	{
		return this.prepage;
	}
	
	public void setNextpage()
	{
		if (temp_curpage + 1 > temp_totalpage)
		{
			this.nextpage = temp_totalpage;
		}
		else
		{
			this.nextpage = temp_curpage + 1;
		}
	}
	public int getNextpage()
	{
		return this.nextpage;
	}
	
	public void setTotalpage()
	{
		this.totalpage = temp_totalpage;
	}
	public int getTotalpage()
	{
		return this.totalpage;
	}
	
	public void setStart()
	{
		this.start = temp_start;
	}
	public int getStart()
	{
		return this.start;
	}
	
	public void setEnd()
	{
		this.end = temp_end;
	}
	public int getEnd()
	{
		return this.end;
	}
	
	public void setTotalnum()
	{
		this.totalnum = temp_totalnum;
	}
	public int getTotalnum()
	{
		return this.totalnum;
	}
	
	/**
	 * @return 返回 int_num。
	 */
	public int getInt_num() 
	{
		return int_num;
	}
	/**
	 * @param int_num 要设置的 int_num。
	 */
	public void setInt_num(int int_num) 
	{
		this.int_num = int_num;
	}

}
