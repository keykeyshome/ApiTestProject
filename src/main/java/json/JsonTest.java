package json;

import com.jayway.jsonpath.JsonPath;

import http.HttpClientException;
import http.HttpUtils;

public class JsonTest {
	
	public static void main(String[] args) {
		try {
			String reString = HttpUtils.doGet("http://localhost:8090/getJson");
			Object object = JsonPath.read(reString, "$.store.bicycle.color");
			System.out.println(object);
			//System.out.println(reString);
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
	}

}
