package com.echo.util;
import java.security.MessageDigest;
/**
 * 系统通用方法类
 * @author yanlong
 *
 */
public class SystemUtil {
	/**
	 * 用户密码MD5加密方法 32位
	 * @param str 32位
	 * @return
	 */
	public static String md5(String inStr) {
		  MessageDigest md5 = null;
		  try {
		   md5 = MessageDigest.getInstance("MD5");
		  } catch (Exception e) {
		   System.out.println(e.toString());
		   e.printStackTrace();
		   return "";
		  }
		  char[] charArray = inStr.toCharArray();
		  byte[] byteArray = new byte[charArray.length];


		  for (int i = 0; i < charArray.length; i++)
		   byteArray[i] = (byte) charArray[i];


		  byte[] md5Bytes = md5.digest(byteArray);


		  StringBuffer hexValue = new StringBuffer();


		  for (int i = 0; i < md5Bytes.length; i++) {
		   int val = ((int) md5Bytes[i]) & 0xff;
		   if (val < 16)
		    hexValue.append("0");
		   hexValue.append(Integer.toHexString(val));
		  }


		  return hexValue.toString();
		 }
	
		 // 可逆的加密算法
		 public static String KL(String inStr) {
		  // String s = new String(inStr);
		  char[] a = inStr.toCharArray();
		  for (int i = 0; i < a.length; i++) {
		   a[i] = (char) (a[i] ^ 't');
		  }
		  String s = new String(a);
		  return s;
		 }
	
	
		 // 加密后解密
		 public static String JM(String inStr) {
		  char[] a = inStr.toCharArray();
		  for (int i = 0; i < a.length; i++) {
		   a[i] = (char) (a[i] ^ 't');
		  }
		  String k = new String(a);
		  return k;
		 }
		 
		 //获取18位随机数：主键id 5位英文字母+13位时间戳
		 
		 public static String getKeyID(){
			 StringBuffer sb = new StringBuffer();
			 for (int i=0;i<5;i++){
				   char c='a';
				   c =(char)(c+(int)(Math.random()*26));
				   sb.append(c);
				  
				  }
			 return sb+String.valueOf(System.currentTimeMillis());
			 
		 }
		 
		// 测试主函数
//			public static void main(String args[]) {
//				String s = new String("echo");
//				  System.out.println("原始：" + s);
//				  System.out.println("MD5后：" + md5(s));
//				  System.out.println("MD5后再加密：" + KL(md5(s)));
//				  System.out.println("解密为MD5后的：" + JM(KL(md5(s))));
//				  System.out.println("加密的：" + KL(s));
//				  System.out.println("解密的：" + JM(KL(s)));
//			}

}
