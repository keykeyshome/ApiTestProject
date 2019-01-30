package apitest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailException;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.collections.Lists;
import org.testng.xml.XmlSuite;

import email.EmailUtils;
import utils.DateUtils;
import utils.ZipUtil;

public class EmailReport implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
                               String outputDirectory) {
       System.out.println("  generateReport ---");
       String path =System.getProperty("user.dir")+File.separator+"report-output";
		System.out.println(path);
		String zip_path = path+File.separator+"result_"+DateUtils.getCurrentTime()+".zip";
		System.out.println("tozip --"+ zip_path);
		ZipUtil.zip(path, zip_path);
		try {
			EmailUtils.sendEmailsWithAttachments("测试邮件", "查收附件", zip_path);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		File dir = new File(path);
		System.out.println("dir--- "+ dir.getName());
		try {
			FileUtils.cleanDirectory(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    
}

