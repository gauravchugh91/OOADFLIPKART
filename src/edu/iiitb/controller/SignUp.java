package edu.iiitb.controller;

import java.util.Map;





import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;
import edu.iiitb.model.UserWho;

public class SignUp extends ActionSupport implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String assign_password;
	private String reTypePass;
	private SessionMap<String, Object> sessionMap;
	private int isLoggedIn;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAssign_password() {
		return assign_password;
	}
	public void setAssign_password(String assign_password) {
		this.assign_password = assign_password;
	}
	public String getReTypePass() {
		return reTypePass;
	}
	public void setReTypePass(String reTypePass) {
		this.reTypePass = reTypePass;
	}
	public int getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(int isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	
	
	
	public String signUp() 
	{
		setIsLoggedIn(0);
		
	if(getEmail()!=null && getAssign_password()!=null && getReTypePass()!=null)
	  {
		
		if(( getAssign_password() != getReTypePass() )
		&&( LoginAction.isEmailNotValid(getEmail(),getAssign_password())!=null ))
	    	{
			System.out.println("1 "+LoginAction.isEmailNotValid(getEmail(),getAssign_password())+ " "+ " "+getAssign_password()+" "+getReTypePass());
			 return "error";  
		    }
				
			
		else if(LoginAction.isEmailNotValid(getEmail(), getAssign_password())==null)
			{
			System.out.println("1 over here");
	          	UserWho newEntry = DB.addNewAccount(getEmail(),getAssign_password());
	          	System.out.println("addNewAccount executed! Value: "+ newEntry.getEmail());
	          	if(newEntry!=null)
	          	  {
	          		System.out.println("refer here for whoIsLogin:"+newEntry.getEmail());
			        
			        	System.out.println(newEntry.getIsAdmin() + newEntry.getUserID());
						//1. error is from the following line!
	        		//	 sessionMap.put("login","true");
				
	        			 sessionMap.put("email",newEntry.getEmail());
				
	        			 sessionMap.put("userID",newEntry.getUserID());
			
	        			 setIsLoggedIn(1);
	        			 System.out.println("2");
	        			 sessionMap.put("isLoggedin", getIsLoggedIn());
	        			 return "customer";
				  }
			}
		System.out.println("3");
		return "error";
	  }
	System.out.println("4");
	
	return "error";

    }
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		sessionMap = (SessionMap) arg0;
		
	}


}	
	