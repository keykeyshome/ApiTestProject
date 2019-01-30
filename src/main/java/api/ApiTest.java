package api;

import java.io.File;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.crab2died.ExcelUtils;

import email.EmailUtils;
import http.HttpUtils;
import utils.CheckPointUtils;
import utils.DateUtils;
import utils.MapUtils;
import utils.PatternUtils;
import utils.SaveParamsUtils;

public class ApiTest {

	public static void main(String[] args) {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				testCase();
			}
		}, 0, 60, TimeUnit.SECONDS);
	}
	
	public static void testCase() {
		String path = System.getProperty("user.dir") + File.separator + "data" + File.separator;
        System.out.println(" path "+path);
		try {
			//处理测试过程
			List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path + "ownerapitest.xlsx", TestCase.class);
			for (TestCase testCase : list) {
				if (testCase.isRun()) {
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
						testCase.setResult("测试通过");
					}else {
						testCase.setResult("测试失败");
					}
					System.out.println(reString);
					System.out.println("check--" + check);
				}else {
					testCase.setResult("测试关闭");
				}
			}
			
			//写结果
			String path_result = System.getProperty("user.dir") + File.separator + "data" + File.separator+"result_"+DateUtils.getCurrentTime()+".xlsx";
			ExcelUtils.getInstance().exportObjects2Excel(list, TestCase.class, path_result);
			
			//发送邮件
			//EmailUtils.sendEmailsWithAttachments("测试结果", "请查收");
//			EmailUtils.sendEmailsWithAttachments("测试结果", "请查收", path_result);
			//EmailUtils.sendEmailsWithAttachments("测试结果", "请查收", new String[] {path_result,path_result});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
