package uplan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class All {
	
	// 本地备份主目录
	static String dir1 = "H\\U plan";
	// 备份文件夹  
	static String dir2 = "H:\\U plan\\Backup";  
	// USB备份文件夹 
	static String dir3 = "I:\\Backup";  	
	// 旧备份文件夹
	static String dir4 = "H:\\U plan\\Old Backup";
	
	/**
	 * 获取单个文件的MD5值！
	 *
	 * @param file
	 * @return
	 */
	public static String getFileMD5(File file) {
	    if (!file.isFile()) {
	        return null;
	    }
	    MessageDigest digest = null;
	    FileInputStream in = null;
	    byte buffer[] = new byte[1024];
	    int len;
	    try {
	        digest = MessageDigest.getInstance("MD5");
	        in = new FileInputStream(file);
	        while ((len = in.read(buffer, 0, 1024)) != -1) {
	            digest.update(buffer, 0, len);
	        }
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    BigInteger bigInt = new BigInteger(1, digest.digest());
	    return bigInt.toString(16);
	}
	/**
	 * 获取文件夹中文件的MD5值
	 *
	 * @param file
	 * @param listChild
	 * ;true递归子目录中的文件
	 * @return
	 */
	public static Map<String, String> getDirMD5(File file, boolean listChild) {
	    if (!file.isDirectory()) {
	        return null;
	    }
	    Map<String, String> map = new HashMap<String, String>();
	   String md5;
	    File files[] = file.listFiles();
	    for (int i = 0; i < files.length; i++) {
	        File f = files[i];
	        if (f.isDirectory() && listChild) {
	            map.putAll(getDirMD5(f, listChild));
	        } else {
	            md5 = getFileMD5(f);
	            if (md5 != null) {
	                map.put(f.getPath(), md5);
	            }
	        }
	    }
	    return map;
	}
	
	/**
	 * 查找并新建文件夹！
	 * @author 小遁
	 * @param file
	 * @return
	 *
	 */
	public static void main(String[] args) {	
		
		File file=new File(dir1);
		File comFile=new File(dir2);
		File oldFile=new File(dir3);
		File usbFile=new File(dir4);
		
		if(!file.exists()) {
			file.mkdir();		
		}else if(!comFile.exists() | !usbFile.exists() | !oldFile.exists()) {	
			
		comFile.mkdir();
		usbFile.mkdir();
		oldFile.mkdir();
		}      
		    Map<String, String> map = getDirMD5(new File(dir2), true);
		    Map<String, String> map2 = getDirMD5(new File(dir3), true);        
		    Map<String, String> map3 = getDirMD5(new File(dir4), true);        
		    Iterator<String> it = map.keySet().iterator();
		    			    		    
		    while(it.hasNext()){
		        String key = it.next();
		        String key2 = key.replace(dir2, dir3);
		        String key3 = key.replace(dir2, dir4);
		        String value = map.get(key);
		        String value2 = map2.remove(key2);
		        String value3 = map3.remove(key3);
		        		        
		        File f1 = new File(key);
		        File f2 = new File(key2);
		        File f3 = new File(key3);
				
				Calendar cal=Calendar.getInstance();

			    long time1 = f1.lastModified();  
			    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			    //转换文件最后修改时间的格式
			    cal.setTimeInMillis(time1); 
			    String timechange="";
			    timechange = formatter.format(cal.getTime());
			    //System.out.println(f1+timechange);  
			    //输出：修改时间[2]    			    			    			       			         
			    long time2 = f2.lastModified();
			    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			    //转换文件最后修改时间的格式
			    cal.setTimeInMillis(time2);
			    String timechange2 = formatter2.format(cal.getTime());
			    //System.out.println(f2+timechange2);  
			    long time3 = f3.lastModified();
			    SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			    //转换文件最后修改时间的格式
			    cal.setTimeInMillis(time3);
			    //获取文件时间
			    String timechange3 = formatter3.format(cal.getTime());
			    //System.out.println(f3+timechange3);  
			    
		        if(value2 == null && value3 == null){
		        	
		        	try {
		        		FileInputStream fis = new FileInputStream(f1);
		        		FileOutputStream fos =new FileOutputStream(f2);
		        		byte[] b = new byte[1024*1024];
		        		int len = -1;
		        		while((len=fis.read(b))!=-1){
		        		fos.write(b, 0, len);
		        		fos.flush();
		        		}
		        		fos.close();
		        		fis.close();
		        		} catch (IOException e) {
		        		e.printStackTrace();
		        		}
		        				        	
		            System.out.println(key + " -备份> " + key2 );
		            
		        }else if (value2 == null && value.equals(value3)){

		        	try {
		        		FileInputStream fis = new FileInputStream(f1);
		        		FileOutputStream fos =new FileOutputStream(f2);
		        		byte[] b = new byte[1024*1024];
		        		int len = -1;
		        		while((len=fis.read(b))!=-1){
		        		fos.write(b, 0, len);
		        		fos.flush();
		        		}
		        		fos.close();
		        		fis.close();
		        		} catch (IOException e) {
		        		e.printStackTrace();
		        		}		        	
		        	System.out.println(key + " -备份> " + key2 );
		        }else if (value2 == null && !value.equals(value3)){

		        	try {
		        		FileInputStream fis = new FileInputStream(f1);
		        		FileOutputStream fos =new FileOutputStream(f2);
		        		byte[] b = new byte[1024*1024];
		        		int len = -1;
		        		while((len=fis.read(b))!=-1){
		        		fos.write(b, 0, len);
		        		fos.flush();
		        		}
		        		fos.close();
		        		fis.close();
		        		} catch (IOException e) {
		        		e.printStackTrace();
		        		}	
		        	
		        }else if (value.equals(value2) && value3 == null) {
		        	System.out.println("不操作");
		        }else if (!value.equals(value2) && value3 == null) {
		        	if(f1.lastModified()<f2.lastModified()) {
				    	System.out.println(f1+"  更早于  "+f2);
				    	
				    	try {
				    		FileInputStream fis = new FileInputStream(f2);
				    		FileOutputStream fos =new FileOutputStream(f1);
				    		byte[] b = new byte[1024*1024];
				    		int len = -1;
				    		while((len=fis.read(b))!=-1){
				    			fos.write(b, 0, len);
				    			fos.flush();
				    		}
				    		fos.close();
				    		fis.close();
				    	} catch (IOException e) {
				    		e.printStackTrace();
				    	}	
				    	System.out.println(key2 + " - 备份 >" + key);
				    }else {
				    	System.out.println(f2+"  更早于  "+f1);
				    	try {
				    		FileInputStream fis = new FileInputStream(f2);
				    		FileOutputStream fos =new FileOutputStream(f1);
				    		byte[] b = new byte[1024*1024];
				    		int len = -1;
				    		while((len=fis.read(b))!=-1){
				    			fos.write(b, 0, len);
				    			fos.flush();
				    		}
				    		fos.close();
				    		fis.close();
				    	} catch (IOException e) {
				    		e.printStackTrace();
				    	}	
				    	System.out.println(key + " - 备份 >" + key2);
				    }		 
		        }else if (value.equals(value2) && value.equals(value3)) {
		        	System.out.println(key + "相同");
		        }else if (!value.equals(value2) && !value.equals(value3)){
		        	if(f1.lastModified()<f2.lastModified()) {
				    	System.out.println(f1+"  更早于  "+f2);
				    	
				    	try {
				    		FileInputStream fis = new FileInputStream(f2);
				    		FileOutputStream fos =new FileOutputStream(f1);
				    		byte[] b = new byte[1024*1024];
				    		int len = -1;
				    		while((len=fis.read(b))!=-1){
				    			fos.write(b, 0, len);
				    			fos.flush();
				    		}
				    		fos.close();
				    		fis.close();
				    	} catch (IOException e) {
				    		e.printStackTrace();
				    	}	
				    	System.out.println(key2 + " - 备份 >" + key);
				    }else {
				    	System.out.println(f2+"  更早于  "+f1);
				    	try {
				    		FileInputStream fis = new FileInputStream(f2);
				    		FileOutputStream fos =new FileOutputStream(f1);
				    		byte[] b = new byte[1024*1024];
				    		int len = -1;
				    		while((len=fis.read(b))!=-1){
				    			fos.write(b, 0, len);
				    			fos.flush();
				    		}
				    		fos.close();
				    		fis.close();
				    	} catch (IOException e) {
				    		e.printStackTrace();
				    	}	
				    	System.out.println(key + " - 备份 >" + key2);
		        }
		    }
		}
		    it = map2.keySet().iterator();
		    while(it.hasNext()){
		        String key = it.next();
		        String key2 = key.replace(dir3, dir2);

		        File f1 = new File(key);
		        File f2 = new File(key2);
		        
		        try {
					FileInputStream fis = new FileInputStream(f1);
					FileOutputStream fos =new FileOutputStream(f2);
		    		byte[] b = new byte[1024*1024];
		    		int len = -1;
		    		while((len=fis.read(b))!=-1){
		    			fos.write(b, 0, len);
		    			fos.flush();
		    		}
		    		fos.close();
		    		fis.close();
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}	
		    	System.out.println(key + " - 备份 >" + key2);
		    }
		    it = map3.keySet().iterator();
		    while(it.hasNext()){
		    	String key = it.next();		        
		    	System.out.println("文件不存在 || " + key + " -> " + map3.get(key));
		    }
		}
		
	
}




 
