package cn.zjgsu.lightsever.UI;

import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWComplexity;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

import plotdemo.Class1;

public class DrawStream {

	public DrawStream() {
	}
	
	public void draw(int[] num) {
		  MWNumericArray x = null;   //���xֵ������
	      MWNumericArray y = null;    //���yֵ������
	      Class1 thePlot = null;    //plotter���ʵ��
	      int n = num.length;                //��ͼ����
	 
	      try
	      {
	        //����x��y��ֵ
	         int[] dims = {1, n};
	         x = MWNumericArray.newInstance(dims, 
	            MWClassID.DOUBLE, MWComplexity.REAL);
	         y = MWNumericArray.newInstance(dims, 
	            MWClassID.DOUBLE, MWComplexity.REAL);
	 
	         //����  y = x^2
	         for (int i = 1; i <= n; i++)
	         {
	            x.set(i, i);
	            y.set(i, num[i-1]);
	         }
	 
	         //��ʼ��plotter�Ķ���
	         thePlot = new Class1();
	 
	         //��ͼ
	         thePlot.drawplot(x, y);
	         thePlot.waitForFigures();
	      }
	 
	      catch (Exception e)
	      {
	         System.out.println("Exception: " + e.toString());
	      }
	 
	      finally
	      {
	         //�ͷű�����Դ
	         MWArray.disposeArray(x);
	         MWArray.disposeArray(y);
	         if (thePlot != null)
	            thePlot.dispose();
	      }
	   }
	}
	

