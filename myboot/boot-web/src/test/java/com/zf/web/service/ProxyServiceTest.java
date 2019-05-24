package com.zf.web.service;

import com.alibaba.fastjson.JSON;
import com.zf.web.BaseTest;
import com.zf.web.model.hasoffer.ProxyRatio;
import com.zf.web.model.vo.ProxyQuery;
import com.zf.web.service.hasoffer.ProxyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * @Author zhaofeng
 * @Date2019/5/23 09:48
 * @Version V1.0
 **/
public class ProxyServiceTest extends BaseTest {

    @Autowired
    ProxyService proxyService;

    @Test
    public void testPage() {
        int page = 1;
        int size = 2;
        ProxyQuery query = new ProxyQuery();
        query.setProxy("a");
        for (int i = 1; i < 10; i++) {
            Page<ProxyRatio> list =
                    proxyService.getProxyratioByPageAndQuery(i, size, new Sort(Sort.Direction.DESC, "id"), query);

            System.out.println(JSON.toJSONString(list.getContent()));
        }
    }
}
