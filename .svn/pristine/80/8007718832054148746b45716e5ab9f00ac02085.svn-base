package com.echo.util;

import java.util.ArrayList;
import java.util.List;

public  class test{
	
	public static void main(String[] args) {
		//12、11、17、18
		
		String a = "2014-06-07";
		System.out.println(DateBean.getsqlDateTime(a));
		System.out.println(a.substring(5, 10));
		System.out.println(a.substring(11, 16));
		int args1 = 17;
			
//		List<DFutureValue3D> lists =  DFutureValue3(args1);
//		if(lists != null && lists.size()>0){
//			for(DFutureValue3D dv3d :lists){
//				
//				System.out.println(dv3d.getFristValue()+","+dv3d.getSecondValue()+","+dv3d.getThirdValue()+"");
//			}
//		}
		
		System.out.println("-------------------------------------------------------------------------------------");
		List<Integer> hundred = new ArrayList<Integer>();
		hundred.add(1);
		hundred.add(2);
		hundred.add(6);
		
		List<Integer> ten = new ArrayList<Integer>();
		ten.add(9);
		ten.add(2);
		ten.add(3);
		
		List<Integer> Entries = new ArrayList<Integer>();
		Entries.add(2);
		Entries.add(4);
		Entries.add(9);
		
//		int beforevalue = 14;
		
//		List<DFutureValue3D> list2 = DFutureValue3(hundred, ten, Entries,beforevalue);
//			for(DFutureValue3D dv3d :list2){
//				
//				System.out.println(dv3d.getFristValue()+","+dv3d.getSecondValue()+","+dv3d.getThirdValue()+"");
//			}
		int hopevalue = 9;
			System.out.println("******************************************************************************");
		List<DFutureValue3D> list3 = DFutureValue(hundred, ten, Entries,hopevalue);
		for(DFutureValue3D dv3d :list3){
			
			System.out.println(dv3d.getFristValue()+","+dv3d.getSecondValue()+","+dv3d.getThirdValue()+"");
		}
		
		int hezhi = 14;
		System.out.println("****************************kuaidu**************************************************");
		List<Integer> Tujians = new ArrayList<Integer>();
		Tujians.add(0);
		Tujians.add(1);
		
		Tujians.add(2);
		Tujians.add(3);
		Tujians.add(4);
		Tujians.add(8);
		Tujians.add(9);
		List<DFutureValue3D> list4 = get3DVLUEA(Tujians);
		for(DFutureValue3D dv3d :list4){
				//System.out.println("----------和值:"+hezhi+"----------");
			if(dv3d.getFristValue()+dv3d.getSecondValue()+dv3d.getThirdValue() == hezhi){
				System.out.println(dv3d.getFristValue()+","+dv3d.getSecondValue()+","+dv3d.getThirdValue());
			}
//			System.out.println(dv3d.getFristValue()+","+dv3d.getSecondValue()+","+dv3d.getThirdValue()+"----和值"+dv3d.getValues());
		}
		
//		
//		List<DFutureValue3D> list4 = DFutureValueKd(9);
//		for(DFutureValue3D dv3d :list4){
//			
//			System.out.println(dv3d.getFristValue()+","+dv3d.getSecondValue()+","+dv3d.getThirdValue()+"");
//		}
		
	}
	
	static void pong(){
		System.out.println("pong");
	};
	/**
	 * 根据推荐数据获取预期和值推荐数
	 * @param hundred 百位
	 * @param ten 十位
	 * @param Entries 个位
	 * @param args 预期和值
	 * @return
	 */
	public static List<DFutureValue3D> DFutureValue(List<Integer> hundred, List<Integer> ten,List<Integer> Entries,int args){
		List<DFutureValue3D> lists = new ArrayList<DFutureValue3D>();
		DFutureValue3D dv3d = null;
		for(int i = 0; i<hundred.size(); i++){
			for(int j = i+1; j<ten.size(); j++){
				for(int k = j+1; k<Entries.size(); k++){
					if(hundred.get(i) + ten.get(j)+Entries.get(k) == args){
						dv3d = new DFutureValue3D();
						dv3d.setFristValue(hundred.get(i));
						dv3d.setSecondValue(ten.get(j));
						dv3d.setThirdValue(Entries.get(k));
						lists.add(dv3d);
					}
					
				}
			}
		}
		
		return lists;
	}
	/**
	 * 根据推荐三组数据计算和值与前一天不相等推荐值
	 * @param hundred 百位
	 * @param ten 十位
	 * @param Entries 个位
	 * @param args 前一天和值
	 * @return
	 */
	public static List<DFutureValue3D> DFutureValue3(List<Integer> hundred, List<Integer> ten,List<Integer> Entries,int args){
		List<DFutureValue3D> lists = new ArrayList<DFutureValue3D>();
		DFutureValue3D dv3d = null;
		for(int i = 0; i<hundred.size(); i++){
			for(int j = 0; j<ten.size(); j++){
				for(int k = 0; k<Entries.size(); k++){
					if(hundred.get(i) + ten.get(j)+Entries.get(k) != args){
						dv3d = new DFutureValue3D();
						dv3d.setFristValue(hundred.get(i));
						dv3d.setSecondValue(ten.get(j));
						dv3d.setThirdValue(Entries.get(k));
						lists.add(dv3d);
					}
					
				}
			}
		}
		
		return lists;
	}
	/**
	 * 根据和值计算
	 * @param args  和值
	 * @return
	 */
	public static List<DFutureValue3D> DFutureValue3(int args){
		int value = 0;
		List<DFutureValue3D> lists = new ArrayList<DFutureValue3D>();
		DFutureValue3D dv3d = null;
		for(int i = 0; i <= 9; i++){
			for(int j=0; j <= 9; j++){
				for(int k =0; k <= 9; k++){
					value = i+j+k;
					if(value == args){
						dv3d = new DFutureValue3D();
						dv3d.setFristValue(i);
						dv3d.setSecondValue(j);
						dv3d.setThirdValue(k);
						lists.add(dv3d);
						//break;
					}
				}
			}
		}
		
		return lists;
	}
	
	public static List<DFutureValue3D> get3DVLUEA(List<Integer> args){
		int value = 0;
		List<DFutureValue3D> lists = new ArrayList<DFutureValue3D>();
		DFutureValue3D dv3d = null;
		for(int i = 0; i < args.size(); i++){
			for(int j=0; j < args.size(); j++){
				for(int k =0; k < args.size(); k++){
					value = args.get(i)+args.get(j)+args.get(k);
						dv3d = new DFutureValue3D();
						dv3d.setFristValue(args.get(i));
						dv3d.setSecondValue(args.get(j));
						dv3d.setThirdValue(args.get(k));
						dv3d.setValues(value);
						lists.add(dv3d);
						//break;
				}
			}
		}
		
		return lists;
	}
	/**
	 * 求跨度
	 * @param args
	 * @return
	 */
	public static List<DFutureValue3D> DFutureValueKd(int args){
		int value = 0;
		List<DFutureValue3D> lists = new ArrayList<DFutureValue3D>();
		List<DFutureValue3D> newlist = new ArrayList<DFutureValue3D>();
		DFutureValue3D dv3d = null;
		for(int i = 0; i <= 9; i++){
			for(int j=0; j <= 9; j++){
				for(int k =0; k <= 9; k++){
//					value = i+j+k;
//					if(value == args){
						dv3d = new DFutureValue3D();
						dv3d.setFristValue(i);
						dv3d.setSecondValue(j);
						dv3d.setThirdValue(k);
						lists.add(dv3d);
						//break;
//					}
				}
			}
		}
		int sum12 =0; 
//		int sum13 =0; 
//		int sum23 =0; 
		int max =0;
		int min =0;
		if(lists != null && lists.size()>0){
			
			for(DFutureValue3D d3 :lists){
				sum12 = d3.getFristValue()-d3.getSecondValue();
//				sum13 = d3.getFristValue() - d3.getThirdValue();
//				sum23 = d3.getSecondValue() -d3.getThirdValue();
//				
				
				
				if(sum12 >= 0){
					max = d3.getFristValue();
					min = d3.getSecondValue();
				}else{
					max = d3.getSecondValue();
					min = d3.getFristValue();
				}
				
				if(max - d3.getThirdValue() < 0){
					max =d3.getThirdValue();
				}
				
				if(min - d3.getThirdValue() > 0){
					
					min =d3.getThirdValue();
				}
				if(max - min == args){
					newlist.add(d3);
				}
				
				
			}
		}
		
		return newlist;
	}
	
	
	
	
}