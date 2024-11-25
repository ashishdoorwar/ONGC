package com.ongc.liferay.passion.connection;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * @author Ranjeet
 * 
 *<p>
 * <CODE>DatasourceConnection</CODE> class provides a connection to the external database 
 * 
 */
public class DatasourceConnection {

	
private static final Log LOGGER = LogFactoryUtil.getLog(DatasourceConnection.class.getName());


//Working Connection using JNDI	
public static Connection getConnection(){
Thread thread = Thread.currentThread();
Connection connection = null;
//Get the thread's class loader. You'll reinstate it after using
//the data source you look up using JNDI
ClassLoader origLoader = thread.getContextClassLoader();

//get the shielded class loader
ClassLoader shieldedClassLoader = PortalClassLoaderUtil.getClassLoader();

//get the webapp class loader from it
ClassLoader webappClassLoader = shieldedClassLoader.getClass().getClassLoader();

//Set webapp class loader on the thread

thread.setContextClassLoader(webappClassLoader);
try {
// Look up the data source and connect to it

InitialContext ctx = new InitialContext();
DataSource datasource = (DataSource) ctx.lookup("java:comp/env/jdbc/postgresDataSource");

connection = datasource.getConnection();

// Execute SQL statements here ...

} catch (NamingException ne) {
ne.printStackTrace();
} catch (SQLException sqle) {
sqle.printStackTrace();
} finally {
// Switch back to the original context class loader
thread.setContextClassLoader(origLoader);
}
return connection;

}


public static void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet set) {
	try {
		set.close();
	} catch (Exception e2) { }
	try {
		pstmt.close();	
	} catch (Exception e2) {}
	try {
		conn.close();
	} catch (Exception e2) {}
}

public static void closeConnection(Connection conn, Statement pstmt, ResultSet set) {
	try {
		set.close();
	} catch (Exception e2) { }
	try {
		pstmt.close();	
	} catch (Exception e2) {}
	try {
		conn.close();
	} catch (Exception e2) {}
}


public static void closeConnection(PreparedStatement pstmt, Connection conn) {
	try {
		pstmt.close();	
	} catch (Exception e2) {}
	try {
		conn.close();
	} catch (Exception e2) {}

}


public static void closeConnection(Statement stmt, ResultSet rs) {
	try {
		stmt.close();
	} catch (Exception e2) { }
	try {
		rs.close();	
	} catch (Exception e2) {}	
}

/*
	private static String userName="postgres";
//	private static String password="O1g2r@1234";
	private static String password="postgres";
//	private static String jdbcUrl="jdbc:postgresql://10.205.42.153:5432/reports1";
	private static String jdbcUrl="jdbc:postgresql://localhost:5432/ongc";
	private static String driverClass="org.postgresql.Driver";
 
	static{
		try{
		 Class.forName(driverClass);
		}catch(Exception e) {
			
		}
	}
		public static Connection getConnection(){
			Connection con =null;
			try{
			con=		DriverManager.getConnection(jdbcUrl,userName,password);		
			}catch(Exception ex){
				LOGGER.info(ex);
				ex.printStackTrace();
			}
		return con;
		}
		*/
		
//	public static void main(String[] args) {
//		getConnection();
//	}
}
