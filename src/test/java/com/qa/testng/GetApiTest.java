package com.qa.testng;

import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.http.client.methods.CloseableHttpResponse;
import com.qa.base.Testbase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends Testbase {
	
	Testbase testbase;
	String serviceurl;
	String url;
	String apiurl;
	CloseableHttpResponse closeablehttpresponse;
	
	@BeforeMethod
	public void setup() throws Exception
	{
		testbase=new Testbase();
		 serviceurl=prop.getProperty("URL");
		apiurl=prop.getProperty("serviceurl");
		
		
		 url=serviceurl+apiurl;
		 
	
	
	}
		
		@Test(priority=1)
		public void getAPITestwithoutheader() throws ClientProtocolException, Exception
		{
			RestClient restclient=new RestClient();
			closeablehttpresponse=restclient.get(url);
			int statuscode=closeablehttpresponse.getStatusLine().getStatusCode();
			System.out.println("Status code---->"+statuscode);
			
			Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200);
			String responsestring=EntityUtils.toString(closeablehttpresponse.getEntity(),"UTF-8");
			
			JSONObject responsejson=new JSONObject(responsestring);
			System.out.println("Response json----->"+responsejson);
			
			String Perpagevalue=TestUtil.getValueByJPath(responsejson, "/per_page");
			System.out.println(Perpagevalue);
			Assert.assertEquals(Integer.parseInt(Perpagevalue), 6);
			
			String totalvalue=TestUtil.getValueByJPath(responsejson, "/total");
			System.out.println(totalvalue);
			Assert.assertEquals(Integer.parseInt(totalvalue), 12);

			String lastname=TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
			System.out.println(lastname);
			//Assert.assertEquals(Integer.parseInt(totalvalue), 12);
			
			
			Header[]  headersarray=closeablehttpresponse.getAllHeaders();
			HashMap <String,String> hashmap=new HashMap <String,String>();
			
			for(Header header:headersarray)
			{
				hashmap.put(header.getName(), header.getValue());
			}
			
			System.out.println("HeadersArray---->"+hashmap);
		}
		
		
@Test(priority=2)		
public void getAPITestwithheader() throws ClientProtocolException, Exception
{
	RestClient restclient=new RestClient();
	HashMap<String,String>headermap=new HashMap<String,String>();
	headermap.put("Content-type", "application/json");
	closeablehttpresponse=restclient.get(url);
	int statuscode=closeablehttpresponse.getStatusLine().getStatusCode();
	System.out.println("Status code---->"+statuscode);
	
	Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200);
	String responsestring=EntityUtils.toString(closeablehttpresponse.getEntity(),"UTF-8");
	
	JSONObject responsejson=new JSONObject(responsestring);
	System.out.println("Response json----->"+responsejson);
	
	String Perpagevalue=TestUtil.getValueByJPath(responsejson, "/per_page");
	System.out.println(Perpagevalue);
	Assert.assertEquals(Integer.parseInt(Perpagevalue), 6);
	
	String totalvalue=TestUtil.getValueByJPath(responsejson, "/total");
	System.out.println(totalvalue);
	Assert.assertEquals(Integer.parseInt(totalvalue), 12);

	String lastname=TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
	System.out.println(lastname);
	//Assert.assertEquals(Integer.parseInt(totalvalue), 12);
	
	
	Header[]  headersarray=closeablehttpresponse.getAllHeaders();
	HashMap <String,String> hashmap=new HashMap <String,String>();
	
	for(Header header:headersarray)
	{
		hashmap.put(header.getName(), header.getValue());
	}
	
	System.out.println("HeadersArray---->"+hashmap);
}

}




