package com.altamob.offertest.tracking;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.altamob.offertest.model.vo.ReqData;
import com.altamob.offertest.util.FieldMatchURLParam;
import com.altamob.offertest.util.GaidUtil;
import com.altamob.offertest.util.HttpUtil;

/**
 * 根据offerid生成点击连接，然后根据连接和地域请求offertest 获取结果
 * @author zhaofeng
 *
 */
public class OfferTracking {

	private static Logger logger = Logger.getLogger(OfferTracking.class);

	public static ConcurrentLinkedQueue<String> offerGeoQueue = new ConcurrentLinkedQueue<>();

	public static Map<String, ReqData> asyncResultMap = new HashMap<>();

	/**
	 * 
	 * @param offerid
	 * @return
	 */
	public static String getClickByOferid(String offerid, String platform, String country) {
		Map<FieldMatchURLParam, String> paramMap = new HashMap<>();
		paramMap.put(FieldMatchURLParam.placementID, "1662684189370000_1769833153870389");
		paramMap.put(FieldMatchURLParam.transactionID, getTransactionId());
		//		paramMap.put(FieldMatchURLParam.country, code);
		//		paramMap.put(FieldMatchURLParam.language, language);
		paramMap.put(FieldMatchURLParam.cid, GaidUtil.getAid());
		//		paramMap.put(FieldMatchURLParam.appid, ad.getAppid());

        paramMap.put(FieldMatchURLParam.customPkg, "com.altamob.android.sdkdemo.fan21");
		//      paramMap.put(FieldMatchURLParam.controllerName, request.getControllerName());
		if ("ios".equalsIgnoreCase(platform)) {
			paramMap.put(FieldMatchURLParam.idfa, "7d30f0d6-07d1-469b-a8f9-cfcbd390d9ce");
		} else {
			paramMap.put(FieldMatchURLParam.adid, GaidUtil.getGaid());
		}

		//		paramMap.put(FieldMatchURLParam.wifiOnly, null == ad.getWifi_only() ? "-1" : ad.getWifi_only().toString());
		//		paramMap.put(FieldMatchURLParam.modifyDevice, null == ad.getModify_device() ? "" : ad.getModify_device().toString());
		//		paramMap.put(FieldMatchURLParam.allowS3, null == ad.getAllow_s3() ? "" : ad.getAllow_s3().toString());
		paramMap.put(FieldMatchURLParam.adType, "0");
		//		paramMap.put(FieldMatchURLParam.openType, null == ad.getOpen_type() ? "" : ad.getOpen_type().toString());
		//		paramMap.put(FieldMatchURLParam.useMarketCover, null == ad.getUse_market_cover() ? "" : ad.getUse_market_cover().toString());
		//		paramMap.put(FieldMatchURLParam.flowIntercept, null == ad.getFlow_intercept() ? "" : ad.getFlow_intercept().toString());
		paramMap.put(FieldMatchURLParam.predictCtr, "0");
		//		paramMap.put(FieldMatchURLParam.groupId, null == ad.getGroupId() ? "" : ad.getGroupId());
		paramMap.put(FieldMatchURLParam.flowFlag, "1");
		paramMap.put(FieldMatchURLParam.apkVersion, "1.0");
		paramMap.put(FieldMatchURLParam.sdkVersion, "5.0.0.5.4230");

		URIBuilder builder = new URIBuilder();
		builder.addParameter(FieldMatchURLParam.type.getCode(), "01");
		builder.addParameter(FieldMatchURLParam.subsiteID.getCode(), "30742");
		//		builder.addParameter(FieldMatchURLParam.campaignID.getCode(), ad.getSourceId());
		//		builder.addParameter(FieldMatchURLParam.sourceID.getCode(), ad.getSource());
		//		builder.addParameter(FieldMatchURLParam.collectionID.getCode(), ad.getSourceId());
		builder.addParameter(FieldMatchURLParam.id.getCode(), offerid);


		FieldMatchURLParam[] addedGeneralParamsKeys = new FieldMatchURLParam[] {
				FieldMatchURLParam.transactionID, // 生成

				FieldMatchURLParam.placementID, // pid
				FieldMatchURLParam.country, // country
				FieldMatchURLParam.cid, // ad.payout
				FieldMatchURLParam.offerRankingInCollection, // ?
				FieldMatchURLParam.language, // 语言
				FieldMatchURLParam.creative_id, // 资源图片编号
				FieldMatchURLParam.applift_LoopOfferID, // ad.app.xxx
				FieldMatchURLParam.appid, // app.id
				FieldMatchURLParam.customPkg, // 包名
				FieldMatchURLParam.adid, //
				FieldMatchURLParam.wifiOnly,
				FieldMatchURLParam.modifyDevice, // ios
				FieldMatchURLParam.allowS3,
				FieldMatchURLParam.adType, //
				FieldMatchURLParam.openType, FieldMatchURLParam.useMarketCover, FieldMatchURLParam.flowIntercept, FieldMatchURLParam.planId, FieldMatchURLParam.planContentId, FieldMatchURLParam.predictCtr, FieldMatchURLParam.predictResponseId, FieldMatchURLParam.groupId,
				FieldMatchURLParam.isShortParam, FieldMatchURLParam.priceDeduct, FieldMatchURLParam.onlyRedictSendOffer, FieldMatchURLParam.idfa, FieldMatchURLParam.flowFlag, FieldMatchURLParam.sdkVersion, FieldMatchURLParam.apkVersion };

		for (FieldMatchURLParam paramKey : addedGeneralParamsKeys) {
			String value = paramMap.get(paramKey);
			if (null != value && !"".equals(value))
				builder.addParameter(paramKey.getCode(), value);
		}

		return builder.toString();
	}

	public static String getTransactionId() {
		String ip = "127.0.0.1";
		String flag = "";
		Long data = java.nio.ByteBuffer.wrap(UUID.randomUUID().toString().getBytes()).asLongBuffer().get();
		while (data < 0) {
			data = java.nio.ByteBuffer.wrap(UUID.randomUUID().toString().getBytes()).asLongBuffer().get();
		}

		String[] strs = ip.split("\\.");
		if (strs.length > 0) {
			flag = data + strs[strs.length - 1];
		}
		return flag + "" + Thread.currentThread().getId() + "" + System.currentTimeMillis();
	}

	public static String getResultFromOfferTest(String country, String platform, String clickurl) {
		String requesturl = "https://api.offertest.net/offertest";
		String responseStr = "";

		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userid", "VSplab1_Rha5GJMcJDwJlg");
		dataMap.put("country", country);
		dataMap.put("url", clickurl);
		dataMap.put("platform", platform);
		//		System.out.println(JSON.toJSONString(dataMap));

		try {
			responseStr = HttpUtil.requestPostHttps(requesturl, JSON.toJSONString(dataMap));
		} catch (Exception e) {
			logger.error(e);
		}

		return responseStr;
	}

	/**
	 * 异步请求
	 * @param country
	 * @param platform
	 * @param clickurl
	 * @return
	 */
	public static String getResultFromOfferTestAsync(String country, String platform, String clickurl) {
		String requesturl = "https://api.offertest.net/offertest?async=true";
		String responseStr = "";

		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userid", "VSplab1_Rha5GJMcJDwJlg");
		dataMap.put("country", country);
		dataMap.put("url", clickurl);
		dataMap.put("platform", platform);
		dataMap.put("callback", "http://sg-ad.altamob.com/offertest/admin/callback");
		//		System.out.println(JSON.toJSONString(dataMap));

		try {
			responseStr = HttpUtil.requestPostHttps(requesturl, JSON.toJSONString(dataMap));
		} catch (Exception e) {
			logger.error(e);
		}

		return responseStr;
	}

	public static String getLog(JSONObject json, String offerid, String country, String clickurl, String source) {
		String status = "error";//最后一跳为gp或market 
		int jumpCount = 0;//如果最后一跳是gp或market 减1
		String targetUrl = null;//最后一跳连接
		Map<String, String> eachMap = new HashMap<String, String>();//记录每一跳连接：跳转状态。

		String id = json.getString("id");
		boolean bundleIdMatch = json.getBooleanValue("bundleIdMatch");//true 为gp 或itues
		int nRedir = json.getIntValue("nRedir");//总跳转次数
		JSONArray urls = json.getJSONArray("urls");
		if (bundleIdMatch) {
			status = "success";
			jumpCount = nRedir - 1;
		} else {
			jumpCount = nRedir;
		}
		if (urls != null && urls.size() > 0) {
			JSONObject temObj = null;
			int count = 1;
			for (Object obj : urls) {
				temObj = (JSONObject) obj;
				String url = temObj.getString("url");
				String code = temObj.getString("code");

				if (count == urls.size()) {
					targetUrl = url;
					if (!bundleIdMatch) {
						eachMap.put(url, "statusCode:" + code + ",jumpCouter:" + count);
					}
				} else {
					eachMap.put(url, "statusCode:" + code + ",jumpCouter:" + count);
				}
				count++;
			}
		}

		StringBuffer sb = new StringBuffer();
		sb.append(offerid).append("\t").append(country).append("\t").append(clickurl).append("\t").append(jumpCount).append("\t").append(status).append("\t").append(targetUrl).append("\t").append(JSON.toJSONString(eachMap)).append("\t").append(id)
				.append("\t").append(source).append("\t").append(System.currentTimeMillis());
		return sb.toString();
	}

	/**
	 * 增加了发送时间和回传时间，测试使用。
	 * @param json
	 * @param offerid
	 * @param country
	 * @param clickurl
	 * @param source
	 * @return
	 */
	public static String getLogTemp(JSONObject json, String offerid, String country, String clickurl, String source, String start, String end) {
		String status = "error";//最后一跳为gp或market 
		int jumpCount = 0;//如果最后一跳是gp或market 减1
		String targetUrl = null;//最后一跳连接
		Map<String, String> eachMap = new HashMap<String, String>();//记录每一跳连接：跳转状态。

		String id = json.getString("id");
		boolean bundleIdMatch = json.getBooleanValue("bundleIdMatch");//true 为gp 或itues
		int nRedir = json.getIntValue("nRedir");//总跳转次数
		JSONArray urls = json.getJSONArray("urls");
		if (bundleIdMatch) {
			status = "success";
			jumpCount = nRedir - 1;
		} else {
			jumpCount = nRedir;
		}
		if (urls != null && urls.size() > 0) {
			JSONObject temObj = null;
			int count = 1;
			for (Object obj : urls) {
				temObj = (JSONObject) obj;
				String url = temObj.getString("url");
				String code = temObj.getString("code");

				if (count == urls.size()) {
					targetUrl = url;
					if (!bundleIdMatch) {
						eachMap.put(url, "statusCode:" + code + ",jumpCouter:" + count);
					}
				} else {
					eachMap.put(url, "statusCode:" + code + ",jumpCouter:" + count);
				}
				count++;
			}
		}

		StringBuffer sb = new StringBuffer();
		sb.append(offerid).append("\t").append(country).append("\t").append(clickurl).append("\t").append(jumpCount).append("\t").append(status).append("\t").append(targetUrl).append("\t").append(JSON.toJSONString(eachMap)).append("\t").append(id).append("\t").append(source)
				.append("\t").append(System.currentTimeMillis()).append("\t").append(start).append("\t").append(end);
		return sb.toString();
	}

	public static void startTracking(String path, int num) {
		String baseUrl = "http://ad.click.kaffnet.com/v1/tracking";
		String offerid = "174215926";
		String platform = "android";
		String country = "ID";
		String source = "10";

		//		String path = "E:/logs/offertest/offergeo.txt";

		try {
			List<String> list = FileUtils.readLines(new File(path));
			for (String s : list) {
				offerGeoQueue.add(s);
			}
			for (int i = 0; i < num; i++) {
				Thread t = new Thread(new TrackingThread());
				t.start();
			}

		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void startTrackingFromDb(List<Object[]> list, int num) {
		try {
			for (Object[] obj : list) {
				offerGeoQueue.add(obj[0] + "\t" + obj[1]);
			}
			for (int i = 0; i < num; i++) {
				Thread t = new Thread(new TrackingAsyncThread());
				t.start();
			}

		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static ReqData getReqFromAsyncResultMap(String key) {
		return asyncResultMap.get(key);
	}

	public static void main(String[] args) {
		String baseUrl = "http://ad.click.kaffnet.com/v1/tracking";
		String offerid = "174215926";
		String platform = "android";
		String country = "ID";
		String source = "10";
		
		String path = "E:/logs/offertest/offergeo.txt";

		try {
			List<String> list = FileUtils.readLines(new File(path));
			for (String s : list) {
				offerGeoQueue.add(s);
				//				String[] sArray = s.split("\t");
				//				offerid = sArray[0];
				//				country = sArray[1];
				//
				//				//根据offerid 生成连接
				//				String clickurl = getClickByOferid(offerid, platform, country);
				//				//		System.out.println(baseUrl + clickurl);
				//
				//				if (StringUtils.isNotBlank(clickurl)) {
				//					String realClickUrl = baseUrl + clickurl;
				//					String responseStr = getResultFromOfferTest(country, platform, realClickUrl);
				//					if (StringUtils.isNotBlank(responseStr)) {
				//						JSONObject json = JSON.parseObject(responseStr);
				//						String logTxt = getLog(json, offerid, country, realClickUrl, source);
				//						System.out.println(json.toJSONString());
				//						System.out.println(logTxt);
				//						OfferTrackingLog.logStr(logTxt);
				//					}
				//				}
			}
			for (int i = 0; i < 1; i++) {
				Thread t = new Thread(new TrackingThread());
				t.start();
			}
			//			Thread.sleep(1 * 24 * 60 * 60 * 1000);

		} catch (Exception e) {
			logger.error(e);
		}
	}

}
