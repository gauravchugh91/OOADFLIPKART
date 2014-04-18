package edu.iiitb.controller;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;

public class ChangePassword {

	private String password;
	private String new_password;
	private String retype_new;
	int status;

	public String getPassword() {
		return password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public String getRetype_new() {
		return retype_new;
	}

	public void setRetype_new(String retype_new) {
		this.retype_new = retype_new;
	}

	public String navigate() {
		setStatus(2);
		return "success";
	}
	public String update() {
		Map<String, Object> sessionMap = ActionContext.getContext()
				.getSession();

		setStatus(0);

		if (sessionMap.containsKey("userID")) {
			if (getPassword() != null && getNew_password().equals(getRetype_new())
					&& getNew_password() != null) {

				String check = DB.correctPassword(getPassword(),
						Integer.parseInt(sessionMap.get("userID").toString()));
                System.out.println("validation:"+check);
				if (check.equals("correct")) {

					String query = "update usercredentials set password = '"
							+ getNew_password()
							+ "' where userid="
							+ Integer.parseInt(sessionMap.get("userID")
									.toString());
					setStatus(DB.updateAccount(query));
					return "success";
					
				} 
			}

		}
		return "error";
	}

}
