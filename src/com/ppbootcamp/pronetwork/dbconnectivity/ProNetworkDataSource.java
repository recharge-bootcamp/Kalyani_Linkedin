package com.ppbootcamp.pronetwork.dbconnectivity;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

public class ProNetworkDataSource {
	private static BasicDataSource ds ;
	
	public static BasicDataSource getPNDataSource() {
		Properties props = new Properties();
		FileInputStream fis = null;
		if(ds == null) {
			ds = new BasicDataSource();
			
			try {
				/*InitialContext jndiEnc = new InitialContext();
				jndiEnc = new InitialContext();
				ds = (BasicDataSource) jndiEnc.lookup("java:comp/env/jdbc/xe_bootcamp");*/
				
				ds.setDriverClassName("oracle.jdbc.OracleDriver");
				ds.setUrl("jdbc:oracle:thin:@localhost:1521/xe");
				ds.setUsername("bootcamp");
				ds.setPassword("Paypal@321");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return ds;
	}
	

}
