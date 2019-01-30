package memory;

import java.util.ArrayList;

public class JavaHeapTest {
	 public static void main(String[] args)
	    {
	        ArrayList list=new ArrayList();
	        while(true)
	        {
	        	System.out.println("add ");
	            list.add(new JavaHeapTest());
	        }
	    }
}
