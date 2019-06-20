package serialPort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

/**锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
 */
public class SerialTool {

    private static SerialTool serialTool = null;
    public static StringBuilder Id = new StringBuilder();

    static {
        //锟斤拷锟斤拷锟斤拷锟斤拷ClassLoader锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷SerialTool锟斤拷锟斤拷
        if (serialTool == null) {
            serialTool = new SerialTool();
        }
    }

    //锟斤拷锟斤拷锟斤拷SerialTool锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷SerialTool锟斤拷锟斤拷
    private SerialTool() {} 
    /**
     * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷SerialTool锟斤拷锟斤拷
     * @return serialTool
     */
    public static SerialTool getSerialTool() {

        if (serialTool == null) {
            serialTool = new SerialTool();
        }
        return serialTool;
    }
    /**
     * OK锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
     * @return 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
     */
    public static final List<String> findPort() {

        //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
        @SuppressWarnings("unchecked")
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers(); 
        List<String> portNameList = new ArrayList<>();
        //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷List锟斤拷锟斤拷锟斤拷锟斤拷List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
        }
        return portNameList;
    }
    /**
     * OK锟斤拷锟斤拷锟斤拷锟斤拷
     * @param portName 锟斤拷锟斤拷锟斤拷锟斤拷
     * @param baudrate 锟斤拷锟斤拷锟斤拷
     * @return 锟斤拷锟斤拷锟斤拷锟斤拷
     * @throws UnsupportedCommOperationException
     * @throws PortInUseException
     * @throws NoSuchPortException
     */
    public static final SerialPort openPort(String portName, int baudrate) throws UnsupportedCommOperationException, PortInUseException, NoSuchPortException {

        //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷timeout锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
        CommPort commPort = portIdentifier.open(portName, 2000);
        //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
        if (commPort instanceof SerialPort) {
            SerialPort serialPort = (SerialPort) commPort;
            //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷,锟斤拷锟斤拷锟斤拷,锟斤拷锟斤拷锟斤拷,锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
            serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1 , SerialPort.PARITY_SPACE );                              
            return serialPort;
        }
        return null;
    }
    /**
     * OK锟斤拷锟斤拷锟斤拷锟斤拷
     * @param serialport 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
     */
    public static void closePort(SerialPort serialPort) {

        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }
    /**
     * OK锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
     * @param serialPort 锟斤拷锟斤拷锟斤拷锟斤拷
     * @param order 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
     * @throws IOException 
     */
    public static void sendToPort(SerialPort serialPort, byte[] order) throws IOException  {

        OutputStream out = null;
        out = serialPort.getOutputStream();
        out.write(order);
        out.flush();
        out.close();
    }
    
    public static String read(SerialPort serialPort) throws IOException{
    	InputStream in = null;
    	byte[] temp = new byte[4];
    	in = serialPort.getInputStream();  
    	if(in.available() >= 4) {
    		in.read(temp);
    		String Buff = new String(temp).trim();
        	return Buff;
    	}
    	return "";
    	
}

    public static String ReadCar(SerialPort serialPort) throws IOException{
    	InputStream in = null;
    	byte[] temp = new byte[20];
    	in = serialPort.getInputStream(); 
    	//System.out.println("准备读取数据");
        if(in.available()>=6) {
        	in.read(temp);
        	String str = new String(temp);
        	SerialTool.Id.append(str);
        	if(SerialTool.Id.toString().contains(";")&&SerialTool.Id.length()>=4) {
        		
        		return SerialTool.Id.toString().substring(SerialTool.Id.toString().indexOf(";")-2,SerialTool.Id.toString().indexOf(";")+2);
        		//return str.substring(str.indexOf(";")-2, str.indexOf(";")+1);
        	}
        }
    	return "";
    	
}
    
    public static String printHexString( byte[] b) {    
  	  StringBuilder str = new StringBuilder();
  	  int a;
      	   for (int i = 0; i < b.length; i++) {
      		   if(b[i]<0) {
      			   a = b[i] + 256;
      		   }
      		   else {
      			   a = b[i];
      		   }
      	     String hex = Integer.toHexString(b[i] & 0xFF);    
      	     if (hex.length() == 1) {    
      	       hex = '0' + hex;    
      	     }    
      	     str.append(hex);
      	     //System.out.print(hex.toUpperCase() );    
      	   }    
      	  return str.toString();
     } 
    
    public static byte[] readFromPort(SerialPort serialPort) throws IOException {

        InputStream in = null;
        byte[] bytes = null;
        try {
            in = serialPort.getInputStream();
            int bufflenth = in.available();
            while (bufflenth != 0) {                             
                bytes = new byte[bufflenth]; 
                in.read(bytes);
                bufflenth = in.available();
            } 
        } catch (IOException e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
                in = null;
            }
        }
        return bytes;
    }
  
    public static void addListener(SerialPort port, SerialPortEventListener listener) throws TooManyListenersException {

        port.addEventListener(listener);
        port.notifyOnDataAvailable(true);
        port.notifyOnBreakInterrupt(true);
    }
}