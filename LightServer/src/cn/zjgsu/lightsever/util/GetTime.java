package cn.zjgsu.lightsever.util;

import java.util.Date;
import cn.zjgsu.lightsever.util.StreamSort;

public class GetTime {
	private int GreTime = 20;//红灯时间
	private int relTime;//当前时间
	private float k;//斜率
	private int t;//时间变量
	private int b;//截距
	private int y;//车流量
	//24个小时的车流量
	private int[] number = new int[24]; 
	private StreamSort s = new StreamSort(2);
	//作为不同街道允许最大流量的修正量
	private float v;
	private int p = 200;//车道最大通行
	
	//求当前时间-车流量表达式
	public void Expression() {
		//找到当前时间，获取车流量，求k，b
		Date date = new Date();
		relTime = Integer.parseInt(date.toString().substring(date.toString().indexOf(':')-2, date.toString().indexOf(':')));
		number = s.StreamDeal();
		k = (number[relTime] - number[relTime-1])/60;
		b = (int) (number[relTime] - 60*k*relTime);
	}
	
	//获取时间
	public int getT() {
		Expression();
		//分级判断，如果前后车辆相差超过20，采用模糊控制
		int car = number[relTime] - number[relTime-1];
		int lcar = Math.abs(car);
		if(Math.abs(car) > 20) {
			if(lcar < 30)
				GreTime +=5;
			else if(lcar <40)
				GreTime +=10;
			else if(lcar <50)
				GreTime +=15;
			else if(car >-50)
				GreTime -=15;
			else if(car >-40)
				GreTime -=10;
			else if(car >-30)
				GreTime -=10;
		}
		//前后变化不大，可以用斜率预测下一时刻的车流量
		else {
			int temp = 20;
			//下一时刻车流量
			y = (int) (k *(relTime+1)*60 + b);
			if(y < 10) {
				temp = 10;
			}else if(y <20) {
				temp = 15;
			}else if(y <30) {
				temp = 20;
			}else if(y <40) {
				temp = 25;
			}
			//流量系数
			int Y = y/p;
			GreTime = (1 + Y*Y)*temp;
			
		}
		return GreTime;
	}
}
