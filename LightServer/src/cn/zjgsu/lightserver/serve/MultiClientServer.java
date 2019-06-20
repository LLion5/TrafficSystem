package cn.zjgsu.lightserver.serve;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiClientServer implements Runnable {

	static int SerialNum = 0;
	Socket socket;
	
	
	public MultiClientServer(Socket ss) {
		socket = ss;
	}
	
	public static void main(String[] args) {
		int MaxClientNum = 5;
		try {
			ServerSocket server = new ServerSocket(8888);  //服务器端口号，需要更改
			for(int i = 0; i < MaxClientNum; i++) {
				Socket socket = server.accept();
				Thread t = new Thread(new MultiClientServer(socket));
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	@Override
	public void run() {
		int myNum = ++SerialNum;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			String s;
			
			while(!(s= in.readLine()).equals("bye")) {
				System.out.println("#Reiceived from Client No. " + myNum + ":" +s);
				out.println(sin.readLine());
				out.flush();
			}
			
			System.out.println("The connecttion to Clicent No"  + myNum + "is closing ....");
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
