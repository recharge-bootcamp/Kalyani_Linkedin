package com.ppbootcamp.pronetwork.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ppbootcamp.pronetwork.dbconnectivity.ProNetworkDataSource;
import com.ppbootcamp.pronetwork.entity.EducationBean;
import com.ppbootcamp.pronetwork.entity.EmploymentBean;
import com.ppbootcamp.pronetwork.entity.LoginBean;
import com.ppbootcamp.pronetwork.entity.PersonBean;
import com.ppbootcamp.pronetwork.utility.Utils;

public class ExistingPersonDataAccess {
	
	public int validate(LoginBean loginBean) throws SQLException,Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int id = 0;
		
		try {
			//String password = Utils.encryptPassword(loginBean.getPassword());
			
			conn = ProNetworkDataSource.getPNDataSource().getConnection();
			pstmt = conn.prepareStatement("select id from person where mailid=? and password=?");
			pstmt.setString(1, loginBean.getMailid());
			pstmt.setString(2, loginBean.getPassword());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				id = rset.getInt(1);
			}
		}
		catch (Exception ex) {
			throw ex;
		}
		finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			}
			catch (SQLException ex) {
				System.out.println("Exception while closing resources in validateandRetrieve");
			}
		}
		return id;		
	}

	public PersonBean retrievePersonData(int id) throws SQLException,Exception {
		PersonBean personBean = new PersonBean();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		EmploymentBean employmentBean;
		EducationBean educationBean;
		ArrayList<EmploymentBean> employmentBeanList;
		ArrayList<EducationBean> educationtBeanList;
		
		try {
			conn = ProNetworkDataSource.getPNDataSource().getConnection();
			pstmt = conn.prepareStatement("select id,mailid,name,skills from person where id=?");
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
	             personBean.setId(rset.getInt("id"));
	             personBean.setName(rset.getString("name"));
	             personBean.setMailid(rset.getString("mailid"));
	             personBean.setSkills(rset.getString("skills"));	             
			}
			
			pstmt = conn.prepareStatement("select * from employment where id=?");
			pstmt.setInt(1, id);

			rset = pstmt.executeQuery();
			employmentBeanList = new ArrayList<EmploymentBean>();
			if(rset.next()) {	
				do {
					employmentBean = new EmploymentBean();
					employmentBean.setCompany(rset.getString("company"));
					employmentBean.setSyear(rset.getString("start_year"));
					employmentBean.setEyear(rset.getString("end_year"));
					employmentBeanList.add(employmentBean);
				} while(rset.next());
			}
			personBean.setEmploymentBean(employmentBeanList);
			
			pstmt = conn.prepareStatement("select * from education where id=?");
			pstmt.setInt(1, id);

			rset = pstmt.executeQuery();
			educationtBeanList = new ArrayList<EducationBean>();
			if(rset.next()) {				
				do {
					educationBean = new EducationBean();
					educationBean.setDegree(rset.getString("degree_level"));
					educationBean.setInstitution(rset.getString("institution"));
					educationBean.setYear(rset.getInt("year"));
					educationtBeanList.add(educationBean);
				} while(rset.next());
				personBean.setEducationBean(educationtBeanList);
			}
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			 throw ex;
		}
		finally {
				try {
					rset.close();
					pstmt.close();
					conn.close();
				}
				catch (SQLException ex) {
					
				}
		}
			return personBean;
	}
}
