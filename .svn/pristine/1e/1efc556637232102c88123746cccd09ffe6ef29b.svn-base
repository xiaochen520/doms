//package com.echo.util;
//
//import com.jacob.activeX.ActiveXComponent;  
//import com.jacob.com.*;  
//  
//public class OfficeToXML {  
//  
//    private final static OfficeToXML oOfficeToXML = new OfficeToXML();  
//  
//    public static OfficeToXML getInstance() {  
//        return oOfficeToXML;  
//    }  
//  
//    public OfficeToXML() {  
//    }  
//  
//    
//  
//    public boolean ExceltoHtml(String s, String s1) {  
//         ComThread.InitSTA();  
//         ActiveXComponent activexcomponent = new  
//         ActiveXComponent("Excel.Application");  
//         String s2 = s;  
//         String s3 = s1;  
//         boolean flag = false;  
//         try  
//         {  
//         activexcomponent.setProperty("Visible", new Variant(false));  
//         Dispatch dispatch =  
//         activexcomponent.getProperty("Workbooks").toDispatch();  
//         Dispatch dispatch1 = Dispatch.invoke(dispatch, "Open", 1, new  
//         Object[] {  
//         s2, new Variant(false), new Variant(true)  
//         }, new int[1]).toDispatch();  
//         Dispatch.call(dispatch1, "SaveAs", s3, new Variant(44));  
//         Variant variant = new Variant(false);  
//         Dispatch.call(dispatch1, "Close", variant);  
//         flag = true;  
//         }  
//         catch(Exception exception)  
//         {  
//         System.out.println("|||" + exception.toString());  
//         }  
//         finally  
//         {  
//         activexcomponent.invoke("Quit", new Variant[0]);  
//         ComThread.Release();  
//         ComThread.quitMainSTA();  
//         }  
//         return flag;  
//    }  
//  
//    public static void main(String args[]) {  
//        OfficeToXML otx = OfficeToXML.getInstance();  
//
//        boolean flag3 = otx.ExceltoHtml("D:/workspaces/echoms/dmsweb/WebRoot/exceltemplet/combination/二号稠油联合处理站日生产简报-新（综合日报）.xls", "D:/workspaces/echoms/dmsweb/WebRoot/page/combination/二号稠油联合处理站日生产简报-新（综合日报）.htm");  
//        if(flag3){  
//            System.out.println("EXCEL文件转换成HTML成功！");  
//        }else{  
//            System.out.println("EXCEL文件转换成HTML失败！");  
//        }  
//    }  
//}  
