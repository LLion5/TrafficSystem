package cn.zjgsu.lightsever.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.zjgsu.lightsever.util.DataBase;
import cn.zjgsu.lightsever.util.StreamSort;

public class Window extends JFrame implements ActionListener{

	JTextField pathText = new JTextField("�����복��ID��",20);
	JTextField stationText = new JTextField("�������վID��",20);
	
	public Window() {
		getContentPane().setForeground(Color.MAGENTA);
		JFrame frame = new JFrame("���̵ƹ���ϵͳ");
		
		getContentPane().setLayout(new BorderLayout());
		
		JPanel cp = new JPanel();
		
		JButton path = new JButton("·����ѯ");
		path.addActionListener(this);
		
		JButton stream = new JButton("������ѯ");
		stream.addActionListener(this);
		
		cp.add(pathText);
		cp.add(path);
		cp.add(stationText);
		cp.add(stream);
		getContentPane().add("South",cp);
		
		
	}
	
	public static void main(String[] args) {
		Window frame = new Window();
		frame.setBounds(600,450,700,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton temp=(JButton)e.getSource();
		String str = temp.getText();
		if(str.equals("·����ѯ")) {
			int car_id = Integer.parseInt(pathText.getText());
			System.out.println(car_id);
			this.pathWindow(car_id);
			
		}else if(str.equals("������ѯ")) {
			int station_id = Integer.parseInt(stationText.getText());
			System.out.println(station_id);
			this.streamWindow(station_id);
		}
	}
	
	public void pathWindow(int car_id) {
        JFrame frame = new JFrame("·��ͼ");
		
		DataBase temp = new DataBase();
		List<Map.Entry<Long,Integer>> list = temp.search(Integer.toString(car_id));
		
		ImageIcon img = new ImageIcon("res\\path.jpg");//���Ǳ���ͼƬ   
		JLabel imgLabel = new JLabel(img);//������ͼ���ڱ�ǩ�      
		frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����    
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//���ñ�����ǩ��λ��   
		Container cp=frame.getContentPane();   
		cp.setLayout(new BorderLayout());    
		((JPanel)cp).setOpaque(false); //ע����������������Ϊ͸��������LayeredPane����еı���������ʾ������  
		
		DrawPath  path = new DrawPath(list);
		frame.getContentPane().add(path);
		
		frame.setBounds(400,200,img.getIconWidth(), img.getIconHeight());   
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public void streamWindow(int station_id) {

		int[] number = new StreamSort(station_id).StreamDeal();
		
		for(int entry : number)
	    	 System.out.println(entry); 
		
		DrawStream drawstream = new DrawStream();
		drawstream.draw(number);
	}
}
