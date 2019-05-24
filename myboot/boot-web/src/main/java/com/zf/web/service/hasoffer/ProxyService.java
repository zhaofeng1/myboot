package com.zf.web.service.hasoffer;

import com.zf.web.model.hasoffer.ProxyRatio;
import com.zf.web.model.vo.ProxyQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProxyService {

    public ProxyRatio getProxyRatio(int id);

    public List<ProxyRatio> getProxyratioByPage(int page, int size, Sort sort);

    public Page<ProxyRatio> getProxyratioByPageAndQuery(int page, int size, Sort sort, final ProxyQuery proxyQuery);
}
