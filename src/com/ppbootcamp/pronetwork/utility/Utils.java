package com.ppbootcamp.pronetwork.utility;

import java.security.MessageDigest;
import java.util.ArrayList;

import com.ppbootcamp.pronetwork.entity.EducationBean;
import com.ppbootcamp.pronetwork.entity.EmploymentBean;

public class Utils {

	public static ArrayList<EmploymentBean> createEmploymentBean(String employmentDetails) {
		ArrayList<EmploymentBean> employmentBeanList = new ArrayList<EmploymentBean>();
		EmploymentBean employmentBean = new EmploymentBean();
		String[] employmentDetailsList = employmentDetails.split(",",0);
		String empdetails[];
		
		for (String empl: employmentDetailsList) {  
			    employmentBean = new EmploymentBean();
			    empdetails = empl.split(":", 0);
			    employmentBean.setCompany(empdetails[0]);
			    employmentBean.setSyear(empdetails[1]);
			    employmentBean.setEyear(empdetails[2]);
			    employmentBeanList.add(employmentBean);
	    }
		return employmentBeanList;
	}
	
	public static ArrayList<EducationBean> createEducationBean(String educationDetails) {
		ArrayList<EducationBean> educationBeanList = new ArrayList<EducationBean>();
		EducationBean educationBean = new EducationBean();
		
		String[] educationDetailsList = educationDetails.split(",",0);
		String edudetails[];
		
		for (String empl: educationDetailsList) {  
			educationBean = new EducationBean();
			    edudetails = empl.split(":", 0);
			    educationBean.setInstitution(edudetails[0]);
			    educationBean.setDegree(edudetails[1]);
			    educationBean.setYear(Integer.parseInt(edudetails[2]));
			    educationBeanList.add(educationBean);
	    }
		return educationBeanList;
	}
	
	public static String encryptPassword(String password) throws Exception {
		StringBuffer hexString = new StringBuffer();
		try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        md.update(password.getBytes());

	        byte byteData[] = md.digest();

/*	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }

	        System.out.println("Hex format : " + sb.toString());*/

	        //convert the byte to hex format method 2
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	    	}
		}
		catch(Exception ex) {
			throw ex;
		}
		return hexString.toString();
	}
	
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
*/
}
