package model.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionManager {
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@dblab.dongduk.ac.kr:1521/orclpdb";
    private static final String DB_USERNAME = "dbp230105";
    private static final String DB_PASSWORD = "120841";
    private static DataSource ds = null;
    
    public ConnectionManager() {
    	try {
			// DataSource ���� �� ����
			BasicDataSource bds = new BasicDataSource();
	        bds.setDriverClassName(DB_DRIVER);
	        bds.setUrl(DB_URL);
	        bds.setUsername(DB_USERNAME);
	        bds.setPassword(DB_PASSWORD);     
			ds = bds;
			
			// ����: WAS�� DataSource�� �̿��� ���: 
			// 		Context init = new InitialContext();
			// 		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDS");
		} catch (Exception ex) {
			ex.printStackTrace();
		}    	   
    }

    public Connection getConnection() {
    	Connection conn = null;
    	try {
    		System.out.print("conn 얻기");
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
    }
    
    public void close() {
		BasicDataSource bds = (BasicDataSource) ds;
		try {
			bds.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// ���� Ȱ��ȭ ������ Connection �� ������ ��Ȱ��ȭ ������ Connection ���� ���
	public void printDataSourceStats() {
		try {
			BasicDataSource bds = (BasicDataSource) ds;
			System.out.println("NumActive: " + bds.getNumActive());
			System.out.println("NumIdle: " + bds.getNumIdle());
		} catch (Exception ex) {
			ex.printStackTrace();
		}   
	}
}
