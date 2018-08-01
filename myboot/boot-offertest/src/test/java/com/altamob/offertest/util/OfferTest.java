package com.altamob.offertest.util;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class OfferTest {

	@Test
	public void test() throws ClientProtocolException, IOException {
		String requesturl = "https://api.offertest.net/offertest";
		
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userid", "VSplab1_Rha5GJMcJDwJlg");
		dataMap.put("country", "ID");
		//		dataMap.put("url", "http://ad.click.kaffnet.com/v1/tracking?type=01&p1=30053&p2=6657314&p3=10850&p9=6657314&p12=174215926&p5=1662684189370000_1769833153870389&p7=4e15fe03b27719d5&p33=7d30f0d6-07d1-469b-a8f9-cfcbd390d9ce");
		dataMap.put(
				"url",
				"http://ad.click.kaffnet.com/v1/tracking?type=01&p1=30742&p12=174215926&p4=7161057866071683891111533024519966&p5=1662684189370000_1769833153870389&p7=4e15fe03b27719d5&p24=com.altamob.android.sdkdemo.fan21&p33=7d30f0d6-07d1-469b-a8f9-cfcbd390d9ce&p37=0&p48=0&p64=1&p65=5.0.0.5.4230&p66=1.0");
		dataMap.put("platform", "android");
		System.out.println(JSON.toJSONString(dataMap));
		//		String responseStr = HttpUtil.requestPostHttps(requesturl, JSON.toJSONString(dataMap));
		
		//		if (StringUtils.isNotBlank(responseStr)) {
		//			System.out.println(responseStr);
		//		}
	}


	@Test
	public void test1() throws IOException {
		//		OkHttpClient client = new OkHttpClient();
		OkHttpClient build = new OkHttpClient().newBuilder().sslSocketFactory(createSSLSocketFactory()).hostnameVerifier(new TrustAllHostnameVerifier()).build();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody
				.create(mediaType,
						"{\n\"userid\":\"VSplab1_Rha5GJMcJDwJlg\",\n\"country\":\"ID\",\n\"url\":\"http://ad.click.kaffnet.com/v1/tracking?type=01&p1=30053&p2=6657314&p3=10850&p9=6657314&p12=174215926&p5=1662684189370000_1769833153870389&p7=4e15fe03b27719d5&p33=7d30f0d6-07d1-469b-a8f9-cfcbd390d9ce\",\n\"platform\": \"android\" \n}");

		Request request = new Request.Builder().url("https://api.offertest.net/offertest").post(body).addHeader("Content-Type", "application/json")
				.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJWU3BsYWIxX1JoYTVHSk1jSkR3SmxnIiwiaWF0IjoxNTMyOTIwODI1LCJqdGkiOiJmTVhDRUIxLVYyVHdXV2cwa3lKb0ZBIn0.e7py7mrqR30yU9eXbpCKFT-a2O9y1TlxDrSGGM39jYU").addHeader("Cache-Control", "no-cache")
				.addHeader("Postman-Token", "77009f36-46e6-4a90-aac9-84ad2780c279").build();

		Response response = build.newCall(request).execute();
		String responseStr = response.body().string();
		System.out.println(responseStr);
	}

	private static class TrustAllHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	private static SSLSocketFactory createSSLSocketFactory() {
		SSLSocketFactory ssfFactory = null;

		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[] { new TrustAllCerts() }, new SecureRandom());

			ssfFactory = sc.getSocketFactory();
		} catch (Exception e) {
		}

		return ssfFactory;
	}

	private static class TrustAllCerts implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}
	}
}
