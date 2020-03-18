package com.qa.test;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.BASE.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.data.Cost;
import java.util.List;
//import 'ObjectMapper'(com.fasterxml.jackson.databind)

public class PosiAPItest extends TestBase{
	
	TestBase testbase;
	String serviceurl;
	String apiUrl;
	String url;
	RestClient restclient;
	String industry;
	String name;
	String desc;
	int duration;
    String level;	
	int value;
	
	CloseableHttpResponse closeableHttpResponse;
	@BeforeMethod
	public void setUp() throws Exception, Exception
	{
		 testbase = new TestBase();
		 		 serviceurl = prop.getProperty("URL");
		 apiUrl = prop.getProperty("serviceURL");
				 url = serviceurl+apiUrl;
		 System.out.println(url);	
		 industry =prop.getProperty("industry");
		 name =prop.getProperty("name");
		 desc =prop.getProperty("desc");
		 level =prop.getProperty("level");
		// System.out.println("level from config file"+level);
		duration = Integer.parseInt(prop.getProperty("duration"));
		 //System.out.println("duartion from config file"+duration);
		 value = Integer.parseInt(prop.getProperty("value"));
			 
	}
	
	@Test
	public void postAPITest() throws Exception
	{
		
		restclient = new RestClient();
		 
		 HashMap<String,String>headerMap =new  HashMap<String,String>();
		 headerMap.put("Content-Type", "application/json");
		//headerMap.put("Accept", "application/vnd.aurlis.reach+json");
		 headerMap.put("Aurlis-Authorisation", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI1ZTY2Nzc2ZDVmN2Y1OTExOWViNjU5ZGEiLCJsaW5rZWRJZCI6IjVlNTAwMTQzNWY3ZjU5MjU0MGMyMzNkNCIsInVzZXJOYW1lIjoiUmVhY2ggR21haWwiLCJ1c2VyRW1haWwiOiJ5b3VyZWFjaDAxQGdtYWlsLmNvbSIsImFwcElkIjozLCJjb3VudHJ5IjoiR0JSIiwiZXhwIjoxNTg0NTQ0MDMxfQ.EjeO0y8u8CljuXd2C7KcDg9-3KgO5spQNpDddtc1Np8");		 
		 
		 //jackson api -convert java to json (marshling)
		ObjectMapper mapper = new ObjectMapper();
		Cost c= new Cost(level,value);
		//List<Object> val = c.addtoList("Level:5e4d98bb5f7f592554d174fc", "Value:1234");
		List<Object> bal= new ArrayList<Object>();
		
		bal.add(c);
		
		

		//System.out.println(li.isEmpty());
		
		Users u = new Users(industry,name,desc,duration,bal);//expected user object
		
		
				
		//java object to json conversion
		
	mapper.writeValue(new File("C://Users//ELCOT//Desktop//Automation//org.com.rest//src//test//java//com//qa//data//user.json"), u);
	
	//java Object to json in String
	String userstring = mapper.writeValueAsString(u);
	System.out.println(userstring);
	closeableHttpResponse =restclient.post(url, userstring, headerMap);
	
	//1.status code
	int statuscode=closeableHttpResponse.getStatusLine().getStatusCode();
	Assert.assertEquals(statuscode, testbase.RESPONSE_STATUS_CODE_201);
	
	//2.jsonString
	String responseString =EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
	JSONObject responsejson = new JSONObject(responseString);
	System.out.println("Response JSON from API ---"+responsejson);
	
	//json to java object
	
	Users userobj =mapper.readValue(responseString, Users.class);//actual user object
	System.out.println(userobj);
	Assert.assertTrue(u.getName().equals(userobj.getName()));
	Assert.assertTrue(u.getDesc().equals(userobj.getDesc()));
	
	Assert.assertTrue(u.getIndustry().equals(userobj.getIndustry()));
	Assert.assertTrue(u.getCost().equals(userobj.getCost()));
	System.out.println(userobj.getTime());
	System.out.println(userobj.getMesg());
	
	
	}
	
	

}
