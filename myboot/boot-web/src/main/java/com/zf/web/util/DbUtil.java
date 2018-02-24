package com.zf.web.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

@Service
public class DbUtil {
	
	//	public static final String DB_HASOFFER="HASOFFER";
	//	public static final String DB_REPORT = "REPORT";

	//	@Autowired
	//	@Qualifier("db1")
	//	private DataSource dataSource1;
	//
	//	@Autowired
	//	@Qualifier("db2")
	//	private DataSource dataSource2;

	/**
	 * query
	 *
	 * @param dataSource
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public static List<Map<String, Object>> query(DataSource dataSource, String sql, Object[] params) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					preparedStatement.setObject(i + 1, params[i]);
				}
			}
			resultSet = preparedStatement.executeQuery();

			List<Map<String, Object>> ret = resultSetToList(resultSet);
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			close(connection);
		}
		return null;
	}
	
	//	public Connection getConnection(String dbType) {
	//		try {
	//			if (DB_HASOFFER.equals(dbType)) {
	//				return dataSource1.getConnection();
	//			} else if (DB_REPORT.equals(dbType)) {
	//				return dataSource2.getConnection();
	//			}
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		return null;
	//	}

	/**
	 * @Method: close
	 * @Description:关闭数据库连接(注意，并不是真的关闭，而是把连接还给数据库连接池)
	 * 
	 *
	 */
	public static void close(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static List<Map<String, Object>> resultSetToList(ResultSet resultSet) throws SQLException {
		if (resultSet == null) {
			return new ArrayList<Map<String, Object>>();
		}

		ResultSetMetaData resultSetMetaData = resultSet.getMetaData(); // 得到结果集(rs)的结构信息，比如字段数、字段名等
		int columnCount = resultSetMetaData.getColumnCount(); // 返回此 ResultSet 对象中的列数

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		while (resultSet.next()) {
			Map<String, Object> rowData = new HashMap<String, Object>(columnCount);
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
			}
			list.add(rowData);
		}
		return list;
	}


}
