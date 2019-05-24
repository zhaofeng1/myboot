package com.zf.web.service.hasoffer.imp;

import com.zf.web.dao.hasoffer.ProxyPageRepository;
import com.zf.web.dao.hasoffer.ProxyRepository;
import com.zf.web.model.hasoffer.ProxyRatio;
import com.zf.web.model.vo.ProxyQuery;
import com.zf.web.service.hasoffer.ProxyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhaofeng
 * @Date2019/5/22 16:48
 * @Version V1.0
 **/
@Service
public class ProxyServiceImp implements ProxyService {

    @Autowired
    ProxyRepository proxyRepository;
    @Autowired
    ProxyPageRepository proxyPageRepository;

    @Override
    public ProxyRatio getProxyRatio(int id) {
        return proxyRepository.findById(id);
    }

    @Override
    public List<ProxyRatio> getProxyratioByPage(int page, int size, Sort sort) {
        PageRequest pageRequest = buildPageRequest(page, size, sort);
        proxyPageRepository.findAll(pageRequest);
        return null;
    }

    @Override
    public Page<ProxyRatio> getProxyratioByPageAndQuery(int page, int size, Sort sort, final ProxyQuery proxyQuery) {

        PageRequest pageRequest = buildPageRequest(page, size, sort);
        return proxyPageRepository.findAll(new Specification<ProxyRatio>() {
            @Override
            public Predicate toPredicate(Root<ProxyRatio> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();

                if (StringUtils.isNotBlank(proxyQuery.getProxy())) {
                    list.add(criteriaBuilder.like(root.get("proxy").as(String.class), proxyQuery.getProxy() + "%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageRequest);
    }

    public Page<ProxyRatio> getProxyratioByPageAndQuery1(int page, int size, Sort sort, final ProxyQuery proxyQuery) {

        PageRequest pageRequest = buildPageRequest(page, size, sort);
        new Thread(() -> {
            System.out.println("Lambda可读性强一些");
        }).start();
        return proxyPageRepository.findAll((Root<ProxyRatio> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<Predicate>();

            if (StringUtils.isNotBlank(proxyQuery.getProxy())) {
                list.add(criteriaBuilder.like(root.get("proxy").as(String.class), proxyQuery.getProxy() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, pageRequest);
    }

    public PageRequest buildPageRequest(int page, int size, Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}
