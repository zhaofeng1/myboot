package com.zf.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zf.web.util.DbUtil;

@Service
public class DaoManager {

	@Autowired
	private DataSource defaultDb;

	public void test() {
		String sql = "select * from t_user where 1=1 ";
		//		Object[] params = new Object[] { nowDay, hour };
		Object[] params = null;
		try {
			List<Map<String, Object>> result = DbUtil.query(defaultDb, sql, params);
			System.out.println(JSON.toJSONString(result));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
