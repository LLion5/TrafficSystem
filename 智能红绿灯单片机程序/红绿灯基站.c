/*主机烧录程序***
**主机（基站）在模式0（主机模式）下接收从机（小车）的ID，整合自身ID后，转换为模式1（从机发送模式），将数据发送给PC***/

#include <reg52.h>
#define uchar unsigned char
#define uint unsigned int

sbit M0 = P2^1;
sbit M1 = P2^2;  //*M0M1=00:模式0，主机模式，监听信道数据
				/*10:模式1，从机发送，数据发送到主机
				**01：模式2，从机接收，等待主机数据
				**11：模式3，休眠模式，接受参数配置	  */
sbit AUX = P1^7; //连接判断位，等于0没有连接，不能发送数据
sbit d1 = P0^0;  //低电平
sbit d2 = P0^1;
sbit d3 = P0^2;

int i;

char carNum[5] ="12345";  //接收小车ID（2位ID，前面3位为命令格式）
char ID = '1';    //基站ID
char S[4] = "1234";   //整合的数据，carNum+“；”+ID


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

void sCat()   //拼接函数，将小车ID正确截取出来并整合
{		
	int i;	  
	for(i=3;i<5;i++){
		S[i-3] =  carNum[i];
	}
	S[2] = ';';
	S[3]=ID;
}

void Sent(void)  //发送整合的数据
{
	int i;
	RI = 1;
	for (i=0;i<4;i++)
	{
		SBUF = S[i];
		while (TI == 0);//等待发送结束
		TI = 0;
		delay(5);
	}
}

void Read(void) //接收小车ID（后2位ID，前面3位为命令格式
{
	uchar i;
	TI = 1;
	for (i=0;i<5;i++)
	{
		while (RI == 0);//发送和接受同步进行，发送结束后，等待接受结束
		RI = 0;
		carNum[i] = SBUF;//接受端*/
	}
}

void main ()
{	
	for(i =0;i<2;i++){
	M0 = 0;
	M1 = 0;	//主机模式，用于接收数据
	d1 = 0; //低电平口
	d2 = 0;
	d3 = 0;	
	delay(200);
	inital ();
	Read();  //接收小车ID
    sCat();  //整合数据

	M0 = 1;	 //转换成从机模式，让数据发送到PC
	delay(1000);	//延时，让基站有时间与pc连接
	if(AUX == 1){
	  	Sent ();//发送数据
		delay(5);
	}
    }
	while(1){
			M1 = 1;
 			delay(6000);
	}
}