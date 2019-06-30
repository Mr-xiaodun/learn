package uplan;
import java.io.File;

public class newfolder {
	  
	public static void main(String[] args){	
		File file=new File("H:\\U plan");
		File comFile=new File("H:\\U plan\\Backup");//如果默认路径下没有Backup文件夹，则创建路径下文件夹，并以Backup命名
		File oldFile=new File("H:\\U plan\\Old Backup");
		File usbFile=new File("I:\\Backup");
		if(!file.exists()) {
			file.mkdir();		
		}else if(!comFile.exists() | !usbFile.exists() | !oldFile.exists()) {			
		comFile.mkdir();
		usbFile.mkdir();
		oldFile.mkdir();
		}
	}
	
}
