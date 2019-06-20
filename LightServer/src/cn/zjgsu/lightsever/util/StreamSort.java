package cn.zjgsu.lightsever.util;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StreamSort {
	
	private int station_id;
	//24个小时的车流量
	private int[] number = new int[24]; 

	public StreamSort(int station_id) {
		this.station_id = station_id;
	}
	
	//获取车流量数组
	public int[] StreamDeal() {
		DataBase temp = new DataBase();
		Map<Date, Integer> map = temp.searchOfStation(Integer.toString(this.station_id));
		
		Set<Map.Entry<Date,Integer>> mapEntries = map.entrySet();
		List<Map.Entry<Date,Integer>> alist = new LinkedList<Map.Entry<Date,Integer>>(mapEntries); 
		List<Date> list = new LinkedList<Date>();
		
		//整理得到一条条数据
		for(Map.Entry<Date,Integer> entry : alist)
	    	 list.add(entry.getKey());
		
		//根据指定比较器产生的顺序对指定列表进行排序
		Collections.sort(list);
		this.sort(list);
		return number;
	}
	
	//按小时统计数量
	public void sort(List<Date> list) {
		int num;
		for(Date temp:list) {
			//这个数据所在的时间（小时）
			num = Integer.parseInt(temp.toString().substring(temp.toString().indexOf(':')-2, temp.toString().indexOf(':')));
			switch(num) {
			   case 0:number[0]++;break;
			   case 1:number[1]++;break;
			   case 2:number[2]++;break;
			   case 3:number[3]++;break;
			   case 4:number[4]++;break;
			   case 5:number[5]++;break;
			   case 6:number[6]++;break;
			   case 7:number[7]++;break;
			   case 8:number[8]++;break;
			   case 9:number[9]++;break;
			   case 10:number[10]++;break;
			   case 11:number[11]++;break;
			   case 12:number[12]++;break;
			   case 13:number[13]++;break;
			   case 14:number[14]++;break;
			   case 15:number[15]++;break;
			   case 16:number[16]++;break;
			   case 17:number[17]++;break;
			   case 18:number[18]++;break;
			   case 19:number[19]++;break;
			   case 20:number[20]++;break;
			   case 21:number[21]++;break;
			   case 22:number[22]++;break;
			   case 23:number[23]++;break;
			   default:break;
			}
		}
			
	}
	
}
