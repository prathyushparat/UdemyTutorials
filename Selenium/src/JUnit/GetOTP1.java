package JUnit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;



public class GetOTP1 {
	
	private final String USER_AGENT="Mozilla/5.0";
	@Test
	public void GetResponse() throws ClientProtocolException, IOException
	{
		String otp="";
		StringBuffer result=new StringBuffer();
		HttpClient client=new DefaultHttpClient();
		String url="https://wallet.pc.enstage-sas.com/sampleMerchant/getOtp.jsp?accessData=9986939965&programId=6019&eventId=5";
		HttpGet request=new HttpGet(url);
		request.addHeader("User-Agent",USER_AGENT);
		
		HttpResponse response=client.execute(request);
		int responseCode=response.getStatusLine().getStatusCode();
		//System.out.println("Response Code: " + responseCode);
		try{
		if(responseCode==200)
			
		{
			//System.out.println("Get Response is Successfull");
			//BufferedReader reader=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			//String line="";
			otp=result.toString();
			System.out.println(otp);
			//while((line=reader.readLine())!=null)
			{
				//result.append(line);
				System.out.println(result.toString().split("\n")[0]);
			}
		}
		return;
		
	}
		catch(Exception ex)
	{
		result.append("Get Response Failed");
		return;
	}

}
	
}