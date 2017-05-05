package com.ppbootcamp.pronetwork.dataaccess;

import java.sql.*;
import java.util.ArrayList;

import com.ppbootcamp.pronetwork.dbconnectivity.ProNetworkDataSource;
import com.ppbootcamp.pronetwork.entity.EmploymentBean;
import com.ppbootcamp.pronetwork.entity.EducationBean;
import com.ppbootcamp.pronetwork.entity.PersonBean;

public class NewPersonDataAccess {

	public void insertPersonData( PersonBean personBean )throws SQLException, Exception {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement preStatement = null;
		ResultSet rset = null;
		int id;
		try {
			conn = ProNetworkDataSource.getPNDataSource().getConnection();
			stmt = conn.createStatement();
			rset = stmt.executeQuery("select max(id) from person");
			if(rset.next()) {
				id = rset.getInt(1);
				id = id+1;
			}
			else {
				id = 1;
			}
			
			preStatement = conn.prepareStatement("insert into person(id,mailid,name,password,skills) values(?,?,?,?,?)");
			preStatement.setInt(1, id);
			preStatement.setString(2, personBean.getMailid());
			preStatement.setString(3, personBean.getName());
			preStatement.setString(4,personBean.getPassword());
			preStatement.setString(5,personBean.getSkills());
			preStatement.executeQuery();
			
			ArrayList<EmploymentBean> employmentBeanList = personBean.getEmploymentBean();
			
			if(employmentBeanList!=null && employmentBeanList.size()>0) {
				for (EmploymentBean employmentBean: employmentBeanList) {
					preStatement=conn.prepareStatement("insert into EMPLOYMENT(ID, START_YEAR, END_YEAR, COMPANY) values(?,?,?,?)");
					preStatement.setInt(1, id);
					preStatement.setString(2, employmentBean.getSyear());
					preStatement.setString(3, employmentBean.getEyear());
					preStatement.setString(4,employmentBean.getCompany());
					preStatement.executeQuery();					
				}
			}
			
			ArrayList<EducationBean> educationBeanList = personBean.getEducationBean();
			
			if(educationBeanList!=null && educationBeanList.size()>0) {
				for (EducationBean educationBean: educationBeanList) {
			
			preStatement=conn.prepareStatement("insert into EDUCATION(ID, YEAR, DEGREE_LEVEL, INSTITUTION) values(?,?,?,?)");
			preStatement.setInt(1, id);
			preStatement.setInt(2, educationBean.getYear());
			preStatement.setString(3, educationBean.getDegree());
			preStatement.setString(4,educationBean.getInstitution());
			preStatement.executeQuery();
				}
			}
			conn.commit();
		}
		catch (SQLException ex) {
			conn.rollback();
			throw ex;
		}
		catch (Exception ex) {
			 throw ex;
		}
		finally	{
			try {
				rset.close();
				stmt.close();
				preStatement.close();
				conn.close();
			}
			catch (SQLException ex) {
				System.out.println("Exception while closing resources in insertPersonData");
			}		
		}
	}

}
