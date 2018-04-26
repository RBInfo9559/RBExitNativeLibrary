package rb.exit.myexitviewlibrary;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class exit_RestClient
{
	byte[] data = null;
    private ArrayList<NameValuePair> params;
    private ArrayList<NameValuePair> headers;
    private final int GET = 0,POST = 1,PUT = 2;
    private String url;
    
    public final static int TIMEOUT_CONNECTION = 20 * 60 * 1000; // Min
	public final static int TIMEOUT_SOCKET = 20 * 60 * 1000; // Min
 
    private int responseCode;
    private String message;
 
    private String response;
 
    public String getResponse() 
    {
        return response;
    }
 
    public String getErrorMessage() 
    {
        return message;
    }
 
    public int getResponseCode() 
    {
        return responseCode;
    }
 
    public exit_RestClient(String url)
    {
        this.url = url;
        params = new ArrayList<NameValuePair>();
        headers = new ArrayList<NameValuePair>();
    }
 
    public void addParam(String name, String value)
    {
        params.add(new BasicNameValuePair(name, value));
    }
 
    public void addHeader(String name, String value)
    {
        headers.add(new BasicNameValuePair(name, value));
    }
 
    public String execute(int method) throws Exception
    {
        switch(method) 
        {
            case GET:
            {
                //add parameters
                String combinedParams = "";
                if(!params.isEmpty())
                {
                    combinedParams += "?";
                    for(NameValuePair p : params)
                    {
                        String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(),"UTF-8");
                        if(combinedParams.length() > 1)
                        {
                            combinedParams  +=  "&" + paramString;
                        }
                        else
                        {
                            combinedParams += paramString;
                        }
                    }
                }
 
                HttpGet request = new HttpGet(url + combinedParams);
                
                //add headers
                for(NameValuePair h : headers)
                {
                	//request.addHeader(GloabalHelper.header_key, GloabalHelper.header_value);
                    request.addHeader(h.getName(), h.getValue());
                }
                
                return executeRequest(request, url);
            }
         
            case POST:
            {
                HttpPost request = new HttpPost(url);
                
                //add headers
                for(NameValuePair h : headers)
                {
                	//request.addHeader(GloabalHelper.header_key, GloabalHelper.header_value);
                    request.addHeader(h.getName(), h.getValue());
                }
 
                if(!params.isEmpty())
                {
                    request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                }
                return executeRequest(request, url);
            }
            
            case PUT:
            {
                HttpPost request = new HttpPost(url);
                
                //add headers
                for(NameValuePair h : headers)
                {
                	//request.addHeader(GloabalHelper.header_key, GloabalHelper.header_value);
                    request.addHeader(h.getName(), h.getValue());
                }
 
                if(!params.isEmpty())
                {
                    request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                }
                return executeRequest(request, url);
            }
        }
		return null;
    }
    
    @SuppressWarnings("finally")
	private String executeRequest(HttpUriRequest request, String url) throws IOException
	{
    	HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT_CONNECTION);
		HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT_SOCKET);
		
		HttpClient client = getHttpClient(httpParameters);
        HttpResponse httpResponse;
        try 
        {
            httpResponse = client.execute(request);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();

            HttpEntity entity = httpResponse.getEntity();

            if (entity != null)
            {
                InputStream instream = entity.getContent();
                response = convertStreamToString(instream);
                instream.close();
            }
        }
        catch (UnknownHostException e) 
        {
            e.printStackTrace();
            response = "Connection timeout.Please try after sometime.";
            throw e;
        }
        catch (SocketTimeoutException e) 
        {
            e.printStackTrace();
            response = "Connection timeout.Please try after sometime.";
            throw e;
        }
        catch (ConnectTimeoutException e)
        {
            e.printStackTrace();
            response = "Connection timeout.Please try after sometime.";
            throw e;
        }
        catch (ClientProtocolException e)
        {
            response = "Connection timeout.Please try after sometime.";
            e.printStackTrace();
            throw e;
        }
        catch (IOException e)
        {
            response = "Connection timeout.Please try after sometime.";
            e.printStackTrace();
            throw e;
        } 
        finally 
        {
            client.getConnectionManager().shutdown();
            return response;
        }
    }
 
    private static String convertStreamToString(InputStream is) throws IOException 
    {
 
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
 
        String line = null;
        try 
        {
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
        } 
        finally 
        {
            try 
            {
                is.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    
    public static HttpClient getHttpClient(HttpParams httpParameters)
	 {   
		 try 
		 {   
	/*		 KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());   
			 trustStore.load(null, null);   
			 HttpProtocolParams.setVersion(httpParameters, HttpVersion.HTTP_1_1);   
			 HttpProtocolParams.setContentCharset(httpParameters, HTTP.UTF_8);   
			 SchemeRegistry registry = new SchemeRegistry();   
			 registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));   
			 ClientConnectionManager ccm = new ThreadSafeClientConnManager(httpParameters, registry);   
	*/		 return new DefaultHttpClient(httpParameters);
		 } 
		 catch (Exception e) 
		 {   
			 return new DefaultHttpClient(httpParameters);
		 }   
	 }   
}