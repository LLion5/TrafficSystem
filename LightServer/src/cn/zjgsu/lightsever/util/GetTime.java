package cn.zjgsu.lightsever.util;

import java.util.Date;
import cn.zjgsu.lightsever.util.StreamSort;

public class GetTime {
	private int GreTime = 20;//���ʱ��
	private int relTime;//��ǰʱ��
	private float k;//б��
	private int t;//ʱ�����
	private int b;//�ؾ�
	private int y;//������
	//24��Сʱ�ĳ�����
	private int[] number = new int[24]; 
	private StreamSort s = new StreamSort(2);
	//��Ϊ��ͬ�ֵ��������������������
	private float v;
	private int p = 200;//�������ͨ��
	
	//��ǰʱ��-���������ʽ
	public void Expression() {
		//�ҵ���ǰʱ�䣬��ȡ����������k��b
		Date date = new Date();
		relTime = Integer.parseInt(date.toString().substring(date.toString().indexOf(':')-2, date.toString().indexOf(':')));
		number = s.StreamDeal();
		k = (number[relTime] - number[relTime-1])/60;
		b = (int) (number[relTime] - 60*k*relTime);
	}
	
	//��ȡʱ��
	public int getT() {
		Expression();
		//�ּ��жϣ����ǰ��������20������ģ������
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
		//ǰ��仯���󣬿�����б��Ԥ����һʱ�̵ĳ�����
		else {
			int temp = 20;
			//��һʱ�̳�����
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
			//����ϵ��
			int Y = y/p;
			GreTime = (1 + Y*Y)*temp;
			
		}
		return GreTime;
	}
}
