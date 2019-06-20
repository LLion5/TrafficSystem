package cn.zjgsu.lightsever.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("192.168.0.37",8888);  //��������ַ���˿���Ҫ����
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			
			String s;
			int i=10;
			do {
				//s��ʽ��yyyy.MM.dd G 'at' HH:mm:ss z:34:877
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");//Сд��mm��ʾ���Ƿ���
				Date temp = new Date();
				String str = sdf.format(temp);
				
				s = str + ";" + 21 + ";" + 3;
				out.println(s);
				out.flush();
				
				System.out.println("@ Server response: " + in.readLine());
			}while(i--!=0);
			
			System.out.println("The connection is closing.... ");
			
			out.println("bye");
			out.flush();
			
			out.close();
			in.close();
			socket.close();
			
		} catch (Exception e) {
			System.out.println("The server is missing or " + e.toString() 
			        + "\n and you need to remmember the last data may missing..");
		}
		
	}
}
