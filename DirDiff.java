package uplan;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map; 

public class DirDiff {
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
    public static void main(String[] args) {
        String dir1 = "H:\\U plan\\Backup";
        String dir2 = "I:\\Backup";        
        Map<String, String> map = getDirMD5(new File(dir1), true);
        Map<String, String> map2 = getDirMD5(new File(dir2), true);        
        Iterator<String> it = map.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            String key2 = key.replace(dir1, dir2);
            String value = map.get(key);
            String value2 = map2.remove(key2);
            if(value2 == null){
                System.out.println(key + " -> " + value + " || 文件不存在" );
            }else if (!value.equals(value2)){
                System.out.println(key + " -> " + value + " || " + value2 );
            }
        }       
        it = map2.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            System.out.println("文件不存在 || " + key + " -> " + map2.get(key));
        }
    }
 
}

