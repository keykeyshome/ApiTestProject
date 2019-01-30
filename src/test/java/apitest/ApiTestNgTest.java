package apitest;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.crab2died.ExcelUtils;

import api.TestCase;
import http.HttpClientException;
import http.HttpUtils;
import utils.CheckPointUtils;
import utils.MapUtils;
import utils.PatternUtils;
import utils.SaveParamsUtils;

public class ApiTestNgTest {
	
	    @Test(dataProvider = "exceldata", threadPoolSize = 5)
	    public void TestCase(TestCase testCase) throws HttpClientException {
	    	System.out.println("thread----"+  Thread.currentThread().getName());
	    	PatternUtils.matcher(testCase);
			System.out.println(testCase);
			String reString = null;
			if ("get".equalsIgnoreCase(testCase.getType())) {
				reString = HttpUtils.doGet(testCase.getUrl(), MapUtils.covertStringToMp(testCase.getHeader()));
			} else if ("post".equalsIgnoreCase(testCase.getType())) {
				reString = HttpUtils.doPost(testCase.getUrl(),MapUtils.covertStringToMp(testCase.getParams(), "&"),MapUtils.covertStringToMp(testCase.getHeader()));
			}else if ("postjson".equalsIgnoreCase(testCase.getType())) {
				reString = HttpUtils.doPostJson(testCase.getUrl(), testCase.getParams(), MapUtils.covertStringToMp(testCase.getHeader()));
			}
			boolean check = CheckPointUtils.checkbyJsonPath(reString, testCase.getCheck());

			if (check) {
				SaveParamsUtils.saveMapbyJsonPath(reString, testCase.getCorrelation());
			}
			Assert.assertTrue(check);
	    }
	
	
	    @DataProvider(name = "exceldata")
	    public Iterator<Object[]> parameterIntTestProvider() {
	    	List<Object[]> dataProvider = new ArrayList<Object[]>();
	    	String path = System.getProperty("user.dir") + File.separator + "data" + File.separator;
	    	try {
				List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path + "apitest.xlsx", TestCase.class);
				for (TestCase testCase : list) {
					if(testCase.isRun()) {
						dataProvider.add(new Object[] { testCase});
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return dataProvider.iterator();
	    }

}
