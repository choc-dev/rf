package kr.choc.rf.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseManager {

	Context initCtx = null;
	Context envCtx = null;
	DataSource ds = null;
	
	private static DatabaseManager dm = null;

	public static DatabaseManager getInstatnce() {
		if(dm == null) {
			dm = new DatabaseManager();
		}
		
		return dm;
	}
	
	public DatabaseManager() {
		init();
	}
	
	private void init() {
		try {
			initCtx = new InitialContext();
			envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/rf");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int selectOne() {
		Connection conn = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select 1");
			if(rs.next()) {
				result = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
