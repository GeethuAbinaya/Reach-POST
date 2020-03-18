package com.qa.test;

import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.BASE.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	
	TestBase testbase;
	String serviceurl;
	String apiUrl;
	String url;
	RestClient restclient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setUp() throws Exception, Exception
	{
		testbase = new TestBase();
		 serviceurl = prop.getProperty("URL");
		 apiUrl = prop.getProperty("serviceURL");
		 url = serviceurl+apiUrl;
		}
	
	@Test
	public void getAPITest() throws Exception, Exception
	{
		 restclient = new RestClient();
		 
		 HashMap<String,String>headerMap =new  HashMap<String,String>();
		 headerMap.put("Content-Type", "application/json");
		 
		 
		 closeableHttpResponse=restclient.get(url,headerMap);
		
		
		//hit the GET URL
		
				//1.to get status code
				int statuscode =closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status Code --->"+statuscode);
				
				Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200,"status code is not 200");
				
				
				String responseString =EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
				//UTF 8 is charcter set standard
				
				//2.to convert string into JSON
				JSONObject responsejson = new JSONObject(responseString);
				System.out.println("Response JSON from API ---"+responsejson);
				
				
				//single value assertion
				String perPageValue =TestUtil.getValueByJPath(responsejson,"/per_page");
				System.out.println("Value of per page ---->"+perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 6);
				
				
				//array assertion for json
				String lastname =TestUtil.getValueByJPath(responsejson,"/data[0]/last_name");
				String id =TestUtil.getValueByJPath(responsejson,"/data[0]/id");
				String avatar=TestUtil.getValueByJPath(responsejson,"/data[0]/avatar");
				String firstname=TestUtil.getValueByJPath(responsejson,"/data[0]/first_name");
				
				System.out.println(lastname);
				System.out.println(id);
				System.out.println(avatar);

				System.out.println(firstname);
				//3.to get header details from API
				Header[]headerArray= closeableHttpResponse.getAllHeaders();
				HashMap<String,String> allheaders = new HashMap<String,String>();
				
				for(Header header :headerArray)
				{
					allheaders.put(header.getName(), header.getValue());
				}
				
				System.out.println("Headers Array ---->"+allheaders);
		
	}
	
	
	

}
