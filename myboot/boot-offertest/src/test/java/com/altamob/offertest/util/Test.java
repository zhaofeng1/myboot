package com.altamob.offertest.util;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Test {

	public static void main(String[] args) {
		String body = "";

		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			//采用绕过验证的方式处理https请求  
			SSLContext sslcontext = createIgnoreVerifySSL();
			//设置协议http和https对应的处理socket链接工厂的对象  
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", new SSLConnectionSocketFactory(sslcontext)).build();
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			HttpClients.custom().setConnectionManager(connManager);

			//创建自定义的httpclient对象  
			client = HttpClients.custom().setConnectionManager(connManager).build();
			//CloseableHttpClient client = HttpClients.createDefault();

			//创建post方式请求对象  
			HttpPost httpPost = new HttpPost("https://api.offertest.net/offertest");

			//指定报文头Content-type、User-Agent
			//			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
			//			httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJWU3BsYWIxX1JoYTVHSk1jSkR3SmxnIiwiaWF0IjoxNTMyOTIwODI1LCJqdGkiOiJmTVhDRUIxLVYyVHdXV2cwa3lKb0ZBIn0.e7py7mrqR30yU9eXbpCKFT-a2O9y1TlxDrSGGM39jYU");

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("userid", "VSplab1_Rha5GJMcJDwJlg"));
			params.add(new BasicNameValuePair("country", "ID"));
			params.add(new BasicNameValuePair("url", "http://ad.click.kaffnet.com/v1/tracking?type=01&p1=30053&p2=6657314&p3=10850&p9=6657314&p12=174215926&p5=1662684189370000_1769833153870389&p7=4e15fe03b27719d5&p33=7d30f0d6-07d1-469b-a8f9-cfcbd390d9ce"));
			params.add(new BasicNameValuePair("platform", "android"));

			String content = "{\n\"userid\":\"VSplab1_Rha5GJMcJDwJlg\",\n\"country\":\"ID\",\n\"url\":\"http://ad.click.kaffnet.com/v1/tracking?type=01&p1=30053&p2=6657314&p3=10850&p9=6657314&p12=174215926&p5=1662684189370000_1769833153870389&p7=4e15fe03b27719d5&p33=7d30f0d6-07d1-469b-a8f9-cfcbd390d9ce\",\n\"platform\": \"android\" \n}";
			httpPost.setEntity(new StringEntity(content, "UTF-8"));
			//执行请求操作，并拿到结果（同步阻塞）  
			response = client.execute(httpPost);

			//获取结果实体  
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				//按指定编码转换结果实体为String类型  
				body = EntityUtils.toString(entity, "UTF-8");
			}

			EntityUtils.consume(entity);
			//释放链接  
			response.close();
			System.out.println("body:" + body);
		} catch (Exception e) {
			System.out.println(e);
		} finally {

			HttpClientUtils.closeQuietly(client);
			HttpClientUtils.closeQuietly(response);
		}
	}

	/** 
	* 绕过验证 
	*   
	* @return 
	* @throws NoSuchAlgorithmException  
	* @throws KeyManagementException  
	*/
	public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("SSLv3");

		// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法  
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		sc.init(null, new TrustManager[] { trustManager }, null);
		return sc;
	}
}
