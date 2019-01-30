package api;

import com.github.crab2died.annotation.ExcelField;


public class TestCase {
	
	@ExcelField(title = "类型")
	private String type;
	
	@ExcelField(title = "地址")
	private String url;
	
	@ExcelField(title = "参数")
	private String params;
	
	@ExcelField(title = "头部")
	private String header;
	
	@ExcelField(title = "检查点")
	private String check;
	
	@ExcelField(title = "关联")
	private String correlation;
	
	@ExcelField(title = "开启",order=1,readConverter = IsRunCovert.class, writeConverter = BooleanWriteConvert.class)
	private boolean run;
	
	@ExcelField(title = "测试结果")
	private String result;
	

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getCorrelation() {
		return correlation;
	}

	public void setCorrelation(String correlation) {
		this.correlation = correlation;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "TestCase [type=" + type + ", url=" + url + ", params=" + params + ", header=" + header + ", result="
				+ result + ", check=" + check + ", correlation=" + correlation + "]";
	}
}
