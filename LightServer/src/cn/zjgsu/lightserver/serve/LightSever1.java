package cn.zjgsu.lightserver.serve;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import cn.zjgsu.lightsever.util.DataBase;

public class LightSever1 {

	public LightSever1() {
	}
	
	 public void start(){            
		 try {
				ServerSocket server = new ServerSocket(8888);    //�������˵Ķ˿ںţ���Ҫ����
				Socket socket = server.accept();
	
				BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream());
				BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
				
				String s;
				while(!(s = in.readLine()).equals("bye")) {   
					this.store(s);
					//System.out.println("#Received form Client: " + s);
					//out.println(sin.readLine());
					out.println("Thanks for your data.");
					out.flush();
				}
				
				System.out.println("The connection is closing...");
				in.close();
				out.close();
				socket.close();
				server.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
	 
	public void store(String s) {//���ܵ���s�ĸ�ʽΪ��"yyyy.MM.dd G 'at' HH:mm:ss z:34:877"  ʱ��;����ID;��վID
		//System.out.println("#Received form Client: " + s);
		int car_id,station_id;
		String[] array = s.split(";");
		if(array.length==3) {
			
			DataBase data = new DataBase();
			data.insert(array[0],array[1],array[2]);
			data.close();
			
			//System.out.println("ʱ��:" + array[0] + " ����ID:" + array[1] + " ��վID:" + array[2]);
		}else {
			System.out.println("The format of the data from the client is wrong!!!");  //�˴�������Ҫ����
		}
	}
	
}
