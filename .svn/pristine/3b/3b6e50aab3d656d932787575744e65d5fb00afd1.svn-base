<%@ page language="java" pageEncoding="UTF-8"%>
<script language="JavaScript">
   	function Enter2Tab() 
	{ 
		var e = document.activeElement; 
		if(e.tagName == "INPUT" && ( e.type == "text" || e.type == "password" || e.type == "checkbox" || e.type == "radio" ) || e.tagName == "SELECT") 
		{ 
			if(window.event.keyCode == 13) 
			{ 
			    window.event.keyCode = 9; 
			} 
		} 
	}
	function doSynchronization(change)
	{
		var fields = document.all.tags("input");
		var re =  new RegExp("^([0-9]+)$");
		if(change.value.search(re) == -1)
		{
			alert("请输入正整数，谢谢合作!");
			if(change.name=="pageNoWin")
			{
				change.value=change.form.pageNo.value;
			}else{
				change.value=change.form.pageSize.value;
			}
			
			return false;
		}
		if(parseInt(change.value)>parseInt(change.form.pageCount.value))
		{
			change.value=change.form.pageCount.value
		}
		for(var i=0;i< fields.length;i++)
		{
			if(fields[i].name.indexOf("page")==-1) continue;
			if(change.name.indexOf(fields[i].name)!=-1)
			{
				fields[i].value=change.value;
			}
		}
	}
	function doNextPage(srcObj)
	{
	    var pageNo = srcObj.form.pageNo.value;
	    if(pageNo - 0 < (srcObj.form.pageCount.value - 0))
	    {
	    	srcObj.form.pageNo.value++;
			srcObj.form.submit();
		}else{
			alert("当前已是最后一页！");
		}
	
		return false;
	}

	function doPrePage(srcObj)
	{
	    var pageNo = srcObj.form.pageNo.value;
	    if(pageNo - 0 > 1)
	    {
	    	srcObj.form.pageNo.value --;
			srcObj.form.submit();
		}else{
			alert("当前已是第一页！");
		}
		return false;
	}
	function doFirstPage(srcObj)
	
	{
	   	if(parseInt(srcObj.form.pageNo.value)-0 == 1)
	   	{
	   		alert("当前已是第一页！");
	   		return false;
	   	}
	   	srcObj.form.pageNo.value = 1;
		srcObj.form.submit();
	}
	function doLastPage(srcObj)
	{
	   	if(srcObj.form.pageNo.value-0 == srcObj.form.pageCount.value-0)
	   	{
	   		alert("当前已是最后一页！");
	   		return false;
	   	}
	   	srcObj.form.pageNo.value = srcObj.form.pageCount.value;
		srcObj.form.submit();
		return false;
	}
	function doPageSize(srcObj,size)
	{
		srcObj.form.pageSize.value = size;
		srcObj.form.submit();
	}
</script>

<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0 style="font-family: '宋体'; font-size: 9pt;"> 
	<TBODY>
		<TR>
			<TD vAlign=center align=right>
				页次
				<B ><script language="JavaScript">document.write(document.all.pageNo.value);</script>
				</B>/
				<script language="JavaScript">document.write(document.all.pageCount.value);</script>
				共
				<B ><script language="JavaScript">document.write(document.all.totalRows.value);</script>
				</B>条记录 分页：
			<td width="62" valign="middle" align="center">
				<input type="image" src="img/first.gif"
					style="CURSOR: hand" onclick="return doFirstPage(this)">
			</td>
			<td width="62" valign="middle" align="center">
				<input type="image" src="img/back.gif"
					style="CURSOR: hand" onclick="return doPrePage(this)">
			</td>
			<td width="62" valign="middle" align="center">
				<input type="image" src="img/next.gif"
					style="CURSOR: hand" onclick="return doNextPage(this)">
			</td>
			<td width="62" valign="middle" align="center">
				<input type="image" src="img/no_next.gif"
					style="CURSOR: hand" onclick="return doLastPage(this)">
			</td>
			<script language="JavaScript">document.write("<td width='58' valign='middle' align='right'>跳转到</td><td width='32' valign='middle' align='center'><input type='text' style='font-size: 9pt;' name='pageNoWin' size='1' value='"+document.all.pageNo.value+"' onChange='doSynchronization(this)' onkeydown='Enter2Tab()' maxlength='5'></td>");</script>
			<TD width=20>
				页
			</TD>
			<TD width=40>
				<input type="image" name="submit" class="button" src="img/btn_go.gif">
			</TD>
		
		</TR>
	</TBODY>
</TABLE>