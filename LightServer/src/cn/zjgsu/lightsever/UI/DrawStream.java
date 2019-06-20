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
		  MWNumericArray x = null;   //存放x值的数组
	      MWNumericArray y = null;    //存放y值的数组
	      Class1 thePlot = null;    //plotter类的实例
	      int n = num.length;                //作图点数
	 
	      try
	      {
	        //分配x、y的值
	         int[] dims = {1, n};
	         x = MWNumericArray.newInstance(dims, 
	            MWClassID.DOUBLE, MWComplexity.REAL);
	         y = MWNumericArray.newInstance(dims, 
	            MWClassID.DOUBLE, MWComplexity.REAL);
	 
	         //定义  y = x^2
	         for (int i = 1; i <= n; i++)
	         {
	            x.set(i, i);
	            y.set(i, num[i-1]);
	         }
	 
	         //初始化plotter的对象
	         thePlot = new Class1();
	 
	         //作图
	         thePlot.drawplot(x, y);
	         thePlot.waitForFigures();
	      }
	 
	      catch (Exception e)
	      {
	         System.out.println("Exception: " + e.toString());
	      }
	 
	      finally
	      {
	         //释放本地资源
	         MWArray.disposeArray(x);
	         MWArray.disposeArray(y);
	         if (thePlot != null)
	            thePlot.dispose();
	      }
	   }
	}
	

