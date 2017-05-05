package com.ppbootcamp.pronetwork.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ppbootcamp.pronetwork.dbconnectivity.ProNetworkDataSource;
import com.ppbootcamp.pronetwork.entity.ConnectionBean;

public class ConnectionsDataAccess {
	
	public ArrayList<ConnectionBean> getRecommendations(int id) throws SQLException,Exception {
		ArrayList<ConnectionBean> connectionBeanList;
		ConnectionBean connectionBean;
		Connection conn = null;
		ResultSet rset = null;
		PreparedStatement preStatement=null;
		connectionBeanList = new ArrayList<ConnectionBean>();
		try {
			conn = ProNetworkDataSource.getPNDataSource().getConnection();
			
			preStatement=conn.prepareStatement("select id, name,mailid from person where id in (select s.id from employment s JOIN employment t on s.company = t.company where t.id = ? and s.id!=t.id UNION select s.id from education s JOIN education t on s.institution = t.institution where t.id = ? and s.id!=t.id)");
			preStatement.setInt(1, id);
			preStatement.setInt(2, id);
			rset = preStatement.executeQuery();
			
			//connectionBeanList = new ArrayList<ConnectionBean>();
			while(rset.next()) {
				connectionBean = new ConnectionBean();
				connectionBean.setId(rset.getInt(1));
				connectionBean.setName(rset.getString("name"));
				connectionBean.setMailid(rset.getString("mailid"));
				connectionBeanList.add(connectionBean);
			}
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			 throw ex;
		}
		finally	{
			try {
				rset.close();
				preStatement.close();
				conn.close();
			}
			catch (SQLException ex) {
				System.out.println("Exception while closing resources in getRecommendations");
			}
			
		}
		return connectionBeanList;
	}
	
	public ArrayList<ConnectionBean> getExisting(int id) throws SQLException,Exception {
		ArrayList<ConnectionBean> connectionBeanList;
		ConnectionBean connectionBean;
		Connection conn = null;
		ResultSet rset = null;
		PreparedStatement preStatement = null;
		connectionBeanList = new ArrayList<ConnectionBean>();
		try {
			conn = ProNetworkDataSource.getPNDataSource().getConnection();
			
			preStatement = conn.prepareStatement("select id,name,mailid from person where id in (select connid from conn_existing where id = ?)");
			preStatement.setInt(1, id);
			rset = preStatement.executeQuery();
			
			//connectionBeanList = new ArrayList<ConnectionBean>();
			while(rset.next()) {
				connectionBean = new ConnectionBean();
				connectionBean.setId(rset.getInt(1));
				connectionBean.setName(rset.getString("name"));
				connectionBean.setMailid(rset.getString("mailid"));
				connectionBeanList.add(connectionBean);
			}
				
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			 throw ex;
		}
		finally	{
			try {
				rset.close();
				preStatement.close();
				conn.close();
			}
			catch (SQLException ex) {
				System.out.println("Exception while closing resources in getExisting");
			}			
		}
		return connectionBeanList;
	}
	
	public ArrayList<ConnectionBean> getInvitations(int id) throws SQLException,Exception {
		ArrayList<ConnectionBean> connectionBeanList;
		ConnectionBean connectionBean;
		Connection conn = null;
		ResultSet rset = null;
		PreparedStatement preStatement = null;
		connectionBeanList = new ArrayList<ConnectionBean>();
		try {
			conn = ProNetworkDataSource.getPNDataSource().getConnection();
			
			preStatement=conn.prepareStatement("select id,name,mailid from person where id in (select connid from conn_invitations where id = ?)");
			preStatement.setInt(1, id);
			rset = preStatement.executeQuery();
			
			//connectionBeanList = new ArrayList<ConnectionBean>();
			while(rset.next()) {
				connectionBean = new ConnectionBean();
				connectionBean.setId(rset.getInt(1));
				connectionBean.setName(rset.getString("name"));
				connectionBean.setMailid(rset.getString("mailid"));
				connectionBeanList.add(connectionBean);
			}
				
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			 throw ex;
		}
		finally	{
			try {
				rset.close();
				preStatement.close();
				conn.close();
			}
			catch (SQLException ex) {
				System.out.println("Exception while closing resources in getInvitations");
			}			
		}
		return connectionBeanList;
	}
	
	public void sendInvitations(int id,String[] connList) throws SQLException,Exception {
		Connection conn = null;
		ResultSet rset = null;
		PreparedStatement preStatement = null;
		try {
			conn = ProNetworkDataSource.getPNDataSource().getConnection();
			for (String s : connList) {
				preStatement=conn.prepareStatement("INSERT INTO CONN_INVITATIONS(ID, CONNID) VALUES (?,?)");
				preStatement.setInt(1, Integer.parseInt(s));
				preStatement.setInt(2, id);
				rset = preStatement.executeQuery();
			}
			conn.commit();
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			 throw ex;
		}
		finally	{
			try {
				rset.close();
				preStatement.close();
				conn.close();
			}
			catch (SQLException ex) {
				System.out.println("Exception while closing resources in getInvitations");
			}			
		}
	}
	
	
	
	public void updateInvitations(int id, ArrayList<Integer> accept,ArrayList<Integer> decline) throws SQLException,Exception {
		Connection conn = null;
		ResultSet rset = null;
		PreparedStatement preStatement = null;
		try {
			conn = ProNetworkDataSource.getPNDataSource().getConnection();
			for (int connID : accept) {
				preStatement=conn.prepareStatement("INSERT INTO CONN_EXISTING(ID, CONNID) VALUES (?,?)");
				preStatement.setInt(1, connID);
				preStatement.setInt(2, id);
				rset = preStatement.executeQuery();
				
				preStatement=conn.prepareStatement("DELETE FROM CONN_INVITATIONS WHERE ID=? and CONNID=?");
				preStatement.setInt(1, id);
				preStatement.setInt(2, connID);
				rset = preStatement.executeQuery();
			}
			for (int connID : decline) {
				preStatement=conn.prepareStatement("DELETE FROM CONN_INVITATIONS WHERE ID=? and CONNID=?");
				preStatement.setInt(1, id);
				preStatement.setInt(2, connID);
				rset = preStatement.executeQuery();
			}
			conn.commit();
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			 throw ex;
		}
		finally	{
			try {
				rset.close();
				preStatement.close();
				conn.close();
			}
			catch (SQLException ex) {
				System.out.println("Exception while closing resources in getInvitations");
			}			
		}
	}
	
	/*public static void main(String args[]) {
		ConnectionsDataAccess connDAO = new ConnectionsDataAccess();
		try {
		ArrayList<ConnectionBean> connectionBeanList = connDAO.getExisting(9);
		if(connectionBeanList.size()>0) {
			for (ConnectionBean connBean: connectionBeanList ) {
				System.out.println("details...id:"+connBean.getId()+"name..."+connBean.getName()+"mailid.."+connBean.getMailid());
			}
		}
		}
		catch(Exception ex) {
			
		}
	}*/
}

