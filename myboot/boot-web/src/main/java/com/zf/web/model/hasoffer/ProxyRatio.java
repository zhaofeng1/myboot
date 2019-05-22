package com.zf.web.model.hasoffer;

import javax.persistence.*;

/**
 * @Author zhaofeng
 * @Date2019/5/22 10:10
 * @Version V1.0
 **/
@Table(name = "proxy_ratio")
@Entity
public class ProxyRatio {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = true)
    private String proxy;

    @Column(nullable = true)
    private int ratio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }
}
