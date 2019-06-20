/*从机（小车）烧录程序***
**从机（车辆）负责把自己的节点ID号发送给主机（基站）***/
/**注意：电压不够或者不稳定会导致透传模块数据缺失**/
#include <reg52.h>
#define uchar unsigned char
#define uint unsigned int

sbit AUX = P1^7;//连接判断位，等于0没有连接，不能发送数据
sbit M0 = P2^3;
sbit M1 = P2^4;//*M0M1=00:模式0，主机模式，监听信道数据
				/*10:模式1，从机发送，数据发送到主机
				**01：模式2，从机接收，等待主机数据
				**11：模式3，休眠模式，接受参数配置	  */

uchar nodeNum[] = "35"; //小车节点号


void delay (uint time)   //毫秒延时
{
	uint i;
	uint j;
	for (i=0; i<time; i++)
		for (j=0; j<110; j++);
}

void inital (void)
{
	SM0 = 0;
	SM1 = 1;//工作方式1
	//SMOD = 0;//波特率不加倍(默认)
	REN = 1;//允许接受
	TMOD = 0X20;//T1工作于方式2：自动重装初值
	TH1 = 0XFD;
	TL1 = 0XFD;	//此初值波特率为：9600bps
	TR1 = 1;	//定时器是用来作为波特率的发生器
	EA = 1;
	ES = 1;	
}


void Send (void)   //数据发送
{
	uchar i;
	for (i=0;i<2;i++)   //需要多发一个
	{
		SBUF = nodeNum[i];
		while (TI == 0);//等待发送结束
		TI = 0;
	}
}

void main ()
{
	uchar i;

	M0 = 1;
	M1 = 0; //从机发送模式
	inital ();
	while (1)
	{
		if(AUX==1){
			Send ();//发送数据
		}
		delay (10);
	}
}
