package com.altamob.offertest.util;

import java.util.concurrent.ConcurrentHashMap;


/**
 * 字段匹配params参数的枚举类
 *
 * 请注意约定 新增加的 枚举 code以 p 开头的按照顺序依次增加， 不要出现p7 在 p6 前面的情况
 */
public enum FieldMatchURLParam implements IEnumCode {

	type("type"), subsiteID("p1"), campaignID("p2"), sourceID("p3"), transactionID("p4"), placementID("p5"), country("p6"), cid("p7"), bid("p8"), collectionID("p9"), offerRankingInCollection("p10"), language("p11"), id("p12"), version("p13"), creative_id("p14"), applift_LoopOfferID(
			"lid"), appid("p15"), isBakPage("p16"), isRplaceOffer("p17"), primitive("p18"), replaceCampaignId("p19"), replaceSourceId("p20"), replaceId("p21"), replaceType("p22"), replaceAppLiftLoopId("p23"), customPkg("p24"), applovinClkUrl("p25"), controllerName("p26"), floatBid(
			"p27"), preLoad("p28"), clickTime("p29"), subType("p30"), finalUrl("p31"), aid("p32"), adid("p33"), wifiOnly("p34"), modifyDevice("p35"), allowS3("p36"), adType("p37"), openType("p38"), useMarketCover("p39"), flowIntercept("p40"), planId("p41"), planContentId("p42"), s2sId(
			"p43"), s2sOfferId("p44"), holdUpS2sPostback("p45"), cvrPayout("p46"), bidBackUp("p47"), predictCtr("p48"), subsiteTransactionID("p49"), isIncent("p50"), itemIncentType("p51"), ecpmVer("p52"), groupId("p53"), predictResponseId("p54"), clickUUID("p56"), subsiteName(
			"p57"), isShortParam("p58"), conv_ip("p60"), priceDeduct("p61"), onlyRedictSendOffer("p62"), clickDeduct("p63"), flowFlag("p64"), sdkVersion("p65"), apkVersion("p66"),subChannelId("p67"),ctitTime("p68"),clickBid("p69"),floatRatio("p70"), params("p"), idfa("p100"), platform("p101");

	private String code;

	private FieldMatchURLParam() {
	}

	private FieldMatchURLParam(String code) {
		this.code = code;
	}

	private static ConcurrentHashMap<String, FieldMatchURLParam> map = new ConcurrentHashMap<>();
	private static ConcurrentHashMap<String, FieldMatchURLParam> nameMap = new ConcurrentHashMap<>();

	static {
		FieldMatchURLParam[] values = FieldMatchURLParam.values();
		for (FieldMatchURLParam param : values) {
			map.put(param.getCode(), param);
			nameMap.put(param.name(), param);

		}
	}

	public static FieldMatchURLParam getEnum(String code) {
		return map.get(code);
	}

	public static FieldMatchURLParam getFromName(String name) {
		return nameMap.get(name);
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public boolean equals(String code) {
		if (this.name().equals(code)) {
			return true;
		}
		return false;
	}

	public static ConcurrentHashMap<String, FieldMatchURLParam> getNameMap() {
		return nameMap;
	}

	public static ConcurrentHashMap<String, FieldMatchURLParam> getMap() {
		return map;
	}
}
