//package edu.iiitb.controller;
//
//import javax.servlet.http.Cookie;
//
//import org.apache.struts2.ServletActionContext;
//
//import edu.iiitb.model.Person;
//
//public class addcookies {
//
//	Person person;
//	String name;
//	int age;
//	String gender;
//	String country;
//
//	public Person getPerson() {
//		return person;
//	}
//
//	public void setPerson(Person person) {
//		this.person = person;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	public String getGender() {
//		return gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//	public String getCountry() {
//		return country;
//	}
//	
//	public void setCountry(String country) {
//		this.country = country;
//	}
//
//	public String execute() throws Exception {
//		Cookie ck = null;
//		Person person= new Person();
//		person.setAge(age);
//		person.setCountry(country);
//		person.setGender(gender);
//		person.setName(name);
//		ck= new Cookie("mycook", name);
//		System.out.println(name);
//		ServletActionContext.getResponse().addCookie(ck);
//		return "success";
//	}
//}
