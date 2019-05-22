package com.zf.web.service.hasoffer.imp;

import com.zf.web.dao.hasoffer.ProxyRepository;
import com.zf.web.model.hasoffer.ProxyRatio;
import com.zf.web.service.hasoffer.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhaofeng
 * @Date2019/5/22 16:48
 * @Version V1.0
 **/
@Service
public class ProxyServiceImp implements ProxyService {

    @Autowired
    ProxyRepository proxyRepository;

    @Override
    public ProxyRatio getProxyRatio(int id) {
        return proxyRepository.findById(id);
    }
}
