package com.ppbootcamp.pronetwork.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ppbootcamp.pronetwork.dataaccess.ConnectionsDataAccess;
import com.ppbootcamp.pronetwork.dataaccess.ExistingPersonDataAccess;
import com.ppbootcamp.pronetwork.dataaccess.NewPersonDataAccess;
import com.ppbootcamp.pronetwork.entity.ConnectionBean;
import com.ppbootcamp.pronetwork.entity.LoginBean;
import com.ppbootcamp.pronetwork.entity.PersonBean;
import com.ppbootcamp.pronetwork.utility.Utils;

public class ControllerServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session;
		
		String action = request.getParameter("action_details");
		
		if(action.equalsIgnoreCase("loginauth")) {
			try {
				String mailid=request.getParameter("mailid");
				String password=request.getParameter("password");
				
				LoginBean loginBean=new LoginBean();
				
				loginBean.setMailid(mailid);
				loginBean.setPassword(Utils.encryptPassword(password));
				
				ExistingPersonDataAccess existingPersonDetails = new ExistingPersonDataAccess();
				int id = existingPersonDetails.validate(loginBean);
		
				if(id!=0) {
					//request.setAttribute("bean",personBean);
					request.setAttribute("id", id);
					
					session=request.getSession();  
			        session.setAttribute("id",id);  
			        session.setAttribute("mailid", mailid);
					
					RequestDispatcher rd=request.getRequestDispatcher("Main.jsp");
					rd.forward(request, response);
				}
				else {
					RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");
					rd.forward(request, response);
				}
			}
			catch (Exception ex) {
				displayErrorOnBrowser(ex,out);
			}
			
		}
		
		if(action.equalsIgnoreCase("newuseradd")) {
			try  {
				
				String name = request.getParameter("name");
				String mailid = request.getParameter("mailid");
				String password = request.getParameter("password");
				
				String educationDetails = request.getParameter("educationdetails");
								
				String year = request.getParameter("year");
				String institution = request.getParameter("institution");
				String degree = request.getParameter("degree");
				
				String addDetails = null;
				
				if(year!=null && institution!=null && degree!=null) {
					if(year.length()>0 && institution.length()>0 && degree.length()>0)
					addDetails = String.join(":",institution,degree,year);
					if(educationDetails!=null&&educationDetails.length()>0) {
						educationDetails = String.join(",", educationDetails,addDetails);
					}
					else {
						educationDetails = addDetails;
					}
				}
				
				String employmentDetails = request.getParameter("employmentdetails");
				
				String company = request.getParameter("company");
				String syear = request.getParameter("syear");
				String eyear = request.getParameter("eyear");
				if(company!=null&&eyear!=null&&syear!=null)
				{
					if(company.length()>0&&eyear.length()>0&&syear.length()>0){
						addDetails = String.join(":", company,syear,eyear);
					}
					if(employmentDetails!=null && employmentDetails.length()>0) {
						employmentDetails = String.join(",", employmentDetails, addDetails);
					}
					else {
						employmentDetails = addDetails;
					}
				}
				
				String skills = request.getParameter("skills");
				
				if (name != null && mailid != null)  {
					PersonBean personBean = new PersonBean();
					personBean.setName(name);
					personBean.setMailid(mailid);
					personBean.setPassword(Utils.encryptPassword(password));
					personBean.setSkills(skills);
					
					if(employmentDetails!=null && employmentDetails.length()!=0) {
						personBean.setEmploymentBean(Utils.createEmploymentBean(employmentDetails));
					}
					
					if(educationDetails!=null&&educationDetails.length()!=0) {
						personBean.setEducationBean(Utils.createEducationBean(educationDetails));
					}
  				    NewPersonDataAccess newPersonDAO = new NewPersonDataAccess();
					newPersonDAO.insertPersonData(personBean);
				}
				else {
					
				}
			}
			catch (Exception ex) {
				displayErrorOnBrowser(ex,out);
			}
			
		}
		if(action.equalsIgnoreCase("personInfo")) {
			try {
				int id=Integer.parseInt(request.getParameter("id"));
		
				ExistingPersonDataAccess existingPersonDetails = new ExistingPersonDataAccess();
				PersonBean personBean = existingPersonDetails.retrievePersonData(id);
		
				if(personBean!=null) {
					request.setAttribute("bean", personBean);
					RequestDispatcher rd=request.getRequestDispatcher("PersonInfo.jsp");
					rd.forward(request, response);
				}
				else {
					RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");
					rd.forward(request, response);
				}
			}
			catch (Exception ex) {
				displayErrorOnBrowser(ex,out);
			}
			
		}
		
		if(action.equalsIgnoreCase("existing_conn")) {

			try	{
				int id = Integer.parseInt(request.getParameter("id"));
				ConnectionsDataAccess connDataDetails = new ConnectionsDataAccess();
				ArrayList<ConnectionBean> connBeanList = connDataDetails.getExisting(id);
				request.setAttribute("connBeanList", connBeanList);
				//response.sendRedirect(response.encodeURL("test.jsp", target="myFrame"));
				RequestDispatcher rd=request.getRequestDispatcher("ExistingConnections.jsp");
				rd.forward(request, response);
			}
			catch (Exception ex) {
				displayErrorOnBrowser(ex,out);
			}
			
		}
		
		if(action.equalsIgnoreCase("recommend_conn")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ConnectionsDataAccess connDataDetails = new ConnectionsDataAccess();
			try {
				ArrayList<ConnectionBean> connBeanList = connDataDetails.getRecommendations(id);
				request.setAttribute("connBeanList", connBeanList);
				RequestDispatcher rd=request.getRequestDispatcher("RecommendConnections.jsp");
				rd.forward(request, response);
			}
			catch (Exception ex) {
				displayErrorOnBrowser(ex,out);
			}
			
		}
		
		if(action.equalsIgnoreCase("invitation_conn")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ConnectionsDataAccess connDataDetails = new ConnectionsDataAccess();
			try {
				ArrayList<ConnectionBean> connBeanList = connDataDetails.getInvitations(id);
				request.setAttribute("connBeanList", connBeanList);
				RequestDispatcher rd=request.getRequestDispatcher("Invitations.jsp");
				rd.forward(request, response);
				
			}
			catch (Exception ex) {
				displayErrorOnBrowser(ex,out);
			}
			
		}
		
		if(action.equalsIgnoreCase("sendinvitations")) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				String[] connList = request.getParameterValues("connlist");
				ConnectionsDataAccess connDataDetails = new ConnectionsDataAccess();
				connDataDetails.sendInvitations(id, connList);
			}
			catch (Exception ex) {
				displayErrorOnBrowser(ex,out);
			}
			
		}
		if(action.equalsIgnoreCase("updateinvitations")) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				int size = Integer.parseInt(request.getParameter("size"));
				ArrayList<Integer> accept = new ArrayList<Integer>();
				ArrayList<Integer> decline = new ArrayList<Integer>();
				String propName="";
				String chkpropName="";
				String selected = "";
				
				for(int i=1;i<=size;i++) {
					propName="connid";
					chkpropName="conninv";
					propName = propName.concat(Integer.toString(i));
					chkpropName = chkpropName.concat(Integer.toString(i));
					selected = request.getParameter(chkpropName);
					if(selected!=null) {
						if(selected.length()>0) {
							if(selected.equals("Y"))
								accept.add(Integer.parseInt(request.getParameter(propName)));
							if(selected.equals("N"))
								decline.add(Integer.parseInt(request.getParameter(propName)));
						}
					}
				}
				ConnectionsDataAccess connDataDetails = new ConnectionsDataAccess();
				connDataDetails.updateInvitations(id, accept, decline);
			}
			catch (Exception ex) {
				displayErrorOnBrowser(ex,out);
			}
			
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	public void displayErrorOnBrowser(Throwable t,PrintWriter out) {
		out.println("<br/><br/>An error occurred while processing your request.Please try again:</br>");
		out.println("<br/><br/>Stack Trace:</br>");
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		String stackTrace = sw.toString();
		out.println(stackTrace.replace(System.getProperty("line.separator"), "<br/>\n"));
	}
}
	

