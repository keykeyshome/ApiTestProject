package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.googlecode.aviator.AviatorEvaluator;
import com.jayway.jsonpath.JsonPath;

public class JsonPathTest {

	public static void main(String[] args) {
		//String json = readtxt();
	String json;
	try {
		json = FileUtils.readFileToString(new File("D:\\test.json"), "utf-8");
		List<String> authors = JsonPath.read(json, "$.store.book[*].author");
//		for (String str : authors) {
//			System.out.println(str);
//		}
		
		//读取数据
		
//		 String reg="$.store.bicycle.color=='red'";
//		 //$.store.book.length()
//		 
//		Object object = JsonPath.read(json, "$.store.bicycle.color");
//		System.out.println(object);
//		//String reg="$.store.book.length()>1"; ->"data>1"
//		
//	       Map<String, Object> env = new HashMap<String, Object>();
//	        env.put("data", object);
//	       //执行比较
//	        Boolean result2 = (Boolean) AviatorEvaluator.execute("data=='red'", env);
//	        System.out.println(result2);
	        
	       System.out.println(CheckPointUtils.checkbyJsonPath(json, "$.store.bicycle.color=red"));
	       System.out.println(CheckPointUtils.checkbyJsonPath(json, "$.store.book.length()>1"));
	} catch (IOException e) {
		e.printStackTrace();
	}
		
	}
	
	private static String readtxt() {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader("D:\\test.json");
            BufferedReader bfd = new BufferedReader(fr);
            String s = "";
            while((s=bfd.readLine())!=null) {
                sb.append(s);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

}
