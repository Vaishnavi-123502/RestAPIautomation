package com.qa.client;



import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

//without headers
public class RestClient {

	public CloseableHttpResponse get(String url) throws ClientProtocolException, Exception
	{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpGet httpget=new HttpGet(url);
		CloseableHttpResponse closeablehttpresponse=httpclient.execute(httpget);
		return closeablehttpresponse;
		
	} 
	
//with headers
	
	public CloseableHttpResponse get(String url,HashMap<String,String>headermap) throws ClientProtocolException, Exception
	{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpGet httpget=new HttpGet(url);
		for(Map.Entry<String, String>entry:headermap.entrySet())
		{
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeablehttpresponse=httpclient.execute(httpget);
		return closeablehttpresponse;
		
	} 
}
