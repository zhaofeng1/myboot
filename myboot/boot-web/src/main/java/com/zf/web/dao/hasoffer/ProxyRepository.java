package com.zf.web.dao.hasoffer;

import com.zf.web.model.hasoffer.ProxyRatio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author zhaofeng
 * @Date2019/5/22 11:09
 * @Version V1.0
 **/
@Repository
public interface ProxyRepository extends JpaRepository<ProxyRatio, Integer> {

    ProxyRatio findById(int id);
}
