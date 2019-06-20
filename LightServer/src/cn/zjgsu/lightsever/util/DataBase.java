package cn.zjgsu.lightsever.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataBase {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String URL = "jdbc:mysql://localhost:3306/lightdata";
    static final String USER = "root";
    static final String PASS = "123456";
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs;
    
	public DataBase() {
		this.init();
	}
	
	public void init() {
		try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(URL,USER,PASS);
        
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
           
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
	}

	public List search(String car_id) {
		   try{
	            String sql;
	            sql = "select date,station_id from car where car_id= " + car_id + ";";  //数据库mysql语句需更改
	            ResultSet rs = stmt.executeQuery(sql);
	            
	            SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
	            Date temp;
	            Map<Long, Integer> map = new HashMap<Long, Integer>();
	            
	            while(rs.next()){
	                String date = rs.getString("date");
	                temp = sdf.parse(date);
	                int station_id = rs.getInt("station_id");
	                
	                System.out.print("	date: " + temp.getTime());
	                System.out.print("	station_id: " + station_id);
	                System.out.print("\n");
	                
	                map.put(temp.getTime(), station_id);
	                
	            }
	            List list = this.sortMapByKeys(map);
	            rs.close();
	            return list;
	        }catch(SQLException se){
	            se.printStackTrace();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		   return null;
	}
	
	public Map searchOfStation(String station_id) {
		   try{
	            String sql;
	            sql = "select date,car_id from car where station_id= " + station_id + ";";  //数据库mysql语句需更改
	            rs = stmt.executeQuery(sql);
	            
	            SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
	            Date temp;
	            
	            Map<Date, Integer> map = new HashMap<Date, Integer>();
	            int car_id;
	            
	            rs.next();
	            String date = rs.getString("date");
                temp = sdf.parse(date);
                car_id = rs.getInt("car_id");
                System.out.print("	date: " + temp.getTime()); 
                System.out.println("    car_id:" + car_id);
                map.put(temp, car_id);
	            while(rs.next()){
	                date = rs.getString("date");
	                temp = sdf.parse(date);
	                if(car_id == rs.getInt("car_id")) {
	                	continue;
	                }
	                car_id = rs.getInt("car_id");
	                System.out.print("	date: " + temp.getTime()); 
	                System.out.println("    car_id:" + car_id);
	                map.put(temp, car_id);
	            }
	            
	            this.close();
	            return map;
	        }catch(SQLException se){
	            se.printStackTrace();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		   return null;
	}

	public void insert(String date,String car_id,String station_id) {
		try{
            String sql;
            sql = "INSERT INTO car" + "(date, car_id, station_id)" + 
            		"VALUES" + 
            		"('" + date + "'," + car_id + "," + station_id + ");";  //数据库mysql语句需更改
            stmt.executeUpdate(sql);
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
	}

	public void close() {
		 try{
			 rs.close();
             if(stmt!=null) stmt.close();
         }catch(SQLException se2){
         }// 什么都不做
         try{
             if(conn!=null) conn.close();
         }catch(SQLException se){
             se.printStackTrace();
         }
	}

	private static List sortMapByKeys(Map<Long, Integer> aMap){
		 Set<Map.Entry<Long,Integer>> mapEntries = aMap.entrySet();
	     System.out.println("Values and Keys before sorting "); 
	     for(Map.Entry<Long,Integer> entry : mapEntries)
	    	 System.out.println(entry.getKey() + " - "+ entry.getValue()); 
	     
	     List<Map.Entry<Long,Integer>> aList = new LinkedList<Map.Entry<Long,Integer>>(mapEntries); 
	     // sorting the List 
	     Collections.sort(aList, new Comparator<Map.Entry<Long,Integer>>(){
	    	 @Override 
	         public int compare(Map.Entry<Long, Integer> ele1, Map.Entry<Long, Integer> ele2){
	    		 return ele1.getKey().compareTo(ele2.getKey()); 
	        }
	     }); 
	     
	     System.out.println("Values and Keys after sorting "); 
	    
	     for(Map.Entry<Long,Integer> entry: aList){
	    	System.out.println(entry.getKey() + "	" + entry.getValue());
	     } 
	      
	     for (int i=0; i<aList.size()-1;) {  
	    		 if(aList.get(i).getValue().equals(aList.get(i+1).getValue())) {
	 	    		aList.remove(i);
	    		 }else {
	    			 i++;
	    		 }
	    	 
	     }  
	     
	     System.out.println("Values and Keys after delete "); 
		    
	     for(Map.Entry<Long,Integer> entry: aList){
	    	System.out.println(entry.getKey() + "	" + entry.getValue());
	     } 
	     
	     return aList;
	} 

}
