package retry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.apache.poi.ss.formula.functions.Count;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

import api.TestCase;

public class OverrideIReTry implements IRetryAnalyzer {
   // public static Logger logger=Logger.getLogger(OverrideIReTry.class);
    //public int initReTryNum=1;
    public int maxReTryNum=3;
    
    static Map<String,Integer> countmp = new HashMap<String,Integer>();
    @Override
    public boolean retry(ITestResult iTestResult) {
    	Object testcase = iTestResult.getParameters()[0];
    	int initReTryNum = getTestCaseErrorCount(testcase.toString());
    	System.out.println(testcase + " ---- " + initReTryNum);
        if(initReTryNum<=maxReTryNum){
            String message="方法<"+iTestResult.getName()+">执行失败，重试第"+initReTryNum+"次";
            System.out.println(message);
            Reporter.setCurrentTestResult(iTestResult);
            Reporter.log(message);
            //initReTryNum++;
            return true;
        }
        return false;
    }
    
    private int getTestCaseErrorCount(String key) {
    	if(countmp.containsKey(key)) {
    		int count = countmp.get(key);
    		count++;
    		countmp.put(key, count);
    	}else {
    		countmp.put(key, 1);
    	}
    	return countmp.get(key);
    }
}
