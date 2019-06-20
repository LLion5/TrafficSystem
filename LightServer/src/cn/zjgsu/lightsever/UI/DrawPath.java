package cn.zjgsu.lightsever.UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

public class DrawPath extends JPanel {
	
	private int station_a_x = 0;
	private int station_a_y = 0;
	private int station_b_x = 0;
	private int station_b_y = 0;
	
	private static final int [][] STATION_LOCATION = {
			{150,430},{150,130},{830,130}
	};
	
	
	private List<Map.Entry<Long,Integer>> list;
	
	public DrawPath(List<Map.Entry<Long,Integer>> list) {
		super();
		this.list = list;
		this.setOpaque(false);    //内容面板重叠问题，设置为透明
	}
	
	public void setLocation(int station_one,int staion_two) {
		this.station_a_x = STATION_LOCATION[station_one-1][0];
		this.station_a_y =  STATION_LOCATION[station_one-1][1];
		this.station_b_x = STATION_LOCATION[staion_two-1][0];
		this.station_b_y = STATION_LOCATION[staion_two-1][1];
	}

	@Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;  //g是Graphics对象
        g2.setStroke(new BasicStroke(3.0f));
       
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(3.0f));
        
        for (int i=0; i<list.size()-1;i++) {  
   		 int station_1 = list.get(i).getValue() ,station_2 = list.get(i+1).getValue() ; 
   		   this.setLocation(station_1, station_2);
   		   g2.drawLine(this.station_a_x, this.station_a_y, this.station_b_x, this.station_b_y);
	    }
        
       /* this.setLocation(1,2);
        g2.drawLine(this.station_a_x, this.station_a_y, this.station_b_x, this.station_b_y);
        this.setLocation(2,3);
        g2.drawLine(this.station_a_x, this.station_a_y, this.station_b_x, this.station_b_y);*/
        
        
    }
	
}