/*�ӻ���С������¼����***
**�ӻ���������������Լ��Ľڵ�ID�ŷ��͸���������վ��***/
/**ע�⣺��ѹ�������߲��ȶ��ᵼ��͸��ģ������ȱʧ**/
#include <reg52.h>
#define uchar unsigned char
#define uint unsigned int

sbit AUX = P1^7;//�����ж�λ������0û�����ӣ����ܷ�������
sbit M0 = P2^3;
sbit M1 = P2^4;//*M0M1=00:ģʽ0������ģʽ�������ŵ�����
				/*10:ģʽ1���ӻ����ͣ����ݷ��͵�����
				**01��ģʽ2���ӻ����գ��ȴ���������
				**11��ģʽ3������ģʽ�����ܲ�������	  */

uchar nodeNum[] = "35"; //С���ڵ��


void delay (uint time)   //������ʱ
{
	uint i;
	uint j;
	for (i=0; i<time; i++)
		for (j=0; j<110; j++);
}

void inital (void)
{
	SM0 = 0;
	SM1 = 1;//������ʽ1
	//SMOD = 0;//�����ʲ��ӱ�(Ĭ��)
	REN = 1;//�������
	TMOD = 0X20;//T1�����ڷ�ʽ2���Զ���װ��ֵ
	TH1 = 0XFD;
	TL1 = 0XFD;	//�˳�ֵ������Ϊ��9600bps
	TR1 = 1;	//��ʱ����������Ϊ�����ʵķ�����
	EA = 1;
	ES = 1;	
}


void Send (void)   //���ݷ���
{
	uchar i;
	for (i=0;i<2;i++)   //��Ҫ�෢һ��
	{
		SBUF = nodeNum[i];
		while (TI == 0);//�ȴ����ͽ���
		TI = 0;
	}
}

void main ()
{
	uchar i;

	M0 = 1;
	M1 = 0; //�ӻ�����ģʽ
	inital ();
	while (1)
	{
		if(AUX==1){
			Send ();//��������
		}
		delay (10);
	}
}
