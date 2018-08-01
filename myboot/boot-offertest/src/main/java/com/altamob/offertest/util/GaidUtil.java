package com.altamob.offertest.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

public class GaidUtil {
	private static Logger logger = Logger.getLogger(GaidUtil.class);
	private static List<String> aidList = new ArrayList<String>();;
	private static List<String> gaidList = new ArrayList<String>();;

	static {
		try {
			reload(GaidUtil.class.getClassLoader().getResourceAsStream("gaid.properties"));
		} catch (Exception e) {
			logger.error("load gaid.properties/idfa.properties is error", e);
		}
	}
	
	public static String getAid() {
		Random r = new Random();
		return aidList.get(r.nextInt(aidList.size() - 1));
	}

	public static String getGaid() {
		Random r = new Random();
		return gaidList.get(r.nextInt(gaidList.size() - 1));
	}

	public static void reload(InputStream inputStream) {
		InputStreamReader read = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt;
		try {
			while ((lineTxt = bufferedReader.readLine()) != null) {
				String[] context = lineTxt.split(" ");
				aidList.add(context[0]);
				gaidList.add(context[1]);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			IoUtils.safeClose(bufferedReader);
			IoUtils.safeClose(read);
			IoUtils.safeClose(inputStream);
		}
	}

	public static void main(String[] args) {
		System.out.println(GaidUtil.getAid());
	}
}
