package uplan;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class getTime {
	
	public static void main(String[] args) {
		
		File f1 = new File("H:\\U plan\\Backup\\新建文本文档.txt");
		
		Calendar cal=Calendar.getInstance();
		String timechange="";
	    //获取文件时间
	    long time1 = f1.lastModified();  
	    
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    //转换文件最后修改时间的格式
	    cal.setTimeInMillis(time1);     
	    timechange = formatter.format(cal.getTime());
	    System.out.println(f1+timechange);  
	    //输出：修改时间[2]    2009-08-17 10:32:38
	    
	    File f2=new File("I:\\Backup\\U计划.txt");
	    long time2 = f2.lastModified();
	    cal.setTimeInMillis(time2);	    
	    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    //转换文件最后修改时间的格式
	    cal.setTimeInMillis(time2);     
	    String timechange1 = formatter2.format(cal.getTime());
	    System.out.println(f2+timechange1);  
	    if(f1.lastModified()<f2.lastModified()) {
	    	System.out.println(f1+"  更早于  "+f2);
	    }else {
	    	System.out.println(f2+"  更早于  "+f1);
	    }
	
	}

}
