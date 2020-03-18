package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//1.GET Method
	public CloseableHttpResponse get(String url) throws Exception, IOException
	{
		
		CloseableHttpClient httpClient =HttpClients.createDefault();
		HttpGet httpget =new HttpGet(url);//http get Request
		CloseableHttpResponse closeableHttpResponse =httpClient.execute(httpget);
		return closeableHttpResponse;
		
		/*//hit the GET URL
		
		//1.to get status code
		int statuscode =closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code --->"+statuscode);
		
		
		String responseString =EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		//UTF 8 is charcter set standard
		
		//2.to convert string into JSON
		JSONObject responsejson = new JSONObject(responseString);
		System.out.println("Response JSON from API ---"+responsejson);
		
		
		//3.to get header details from API
		Header[]headerArray= closeableHttpResponse.getAllHeaders();
		HashMap<String,String> allheaders = new HashMap<String,String>();
		for(Header header :headerArray)
		{
			allheaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("Headers Array ---->"+allheaders);*/
		
	}
	//2.GET Method with headers
		public CloseableHttpResponse get(String url,HashMap<String,String> headermap) throws Exception, IOException
		{
			
			CloseableHttpClient httpClient =HttpClients.createDefault();
			HttpGet httpget =new HttpGet(url);//http get Request
			
			for(Map.Entry<String, String>entry:headermap.entrySet())
			{
				httpget.addHeader(entry.getKey(),entry.getValue());
			}
			CloseableHttpResponse closeableHttpResponse =httpClient.execute(httpget);
			return closeableHttpResponse;
		}
		
		//POST Method
		public CloseableHttpResponse post(String url,String entityString,HashMap<String,String>headerMap) throws Exception, IOException
		{
			CloseableHttpClient httpClient =HttpClients.createDefault();
	HttpPost httppost=new HttpPost(url);
	httppost.setEntity(new StringEntity(entityString));
	for(Map.Entry<String, String>entry:headerMap.entrySet())
	{
		httppost.addHeader(entry.getKey(),entry.getValue());
	}
	
	CloseableHttpResponse closeableHttpResponse =httpClient.execute(httppost);
	
	return closeableHttpResponse;
	
	
		}
		
			
	
	

}
