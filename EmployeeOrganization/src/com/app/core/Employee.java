package com.app.core;

import java.text.SimpleDateFormat;
import java.util.Date;

//Employee organization project having validation rules with centralized error handling

//emp id,  first name, last name, email, join date, salary
public class Employee {

	private int empId;
	private String firstName;
	private String lastName;
	private String email;
	private String deptId;
	private Date joinDate;
	private double salary;
	
	//add SDF for parsing(String--> Date) n format(Date-->String)
	public static SimpleDateFormat sdf; //default value null
	//static init : for initing sdf: day/mon/yr -- pattern
	
	static {   //called exactly once when employee class is getting loaded
		//static used for single copy shared with multiple instances
		sdf=new SimpleDateFormat("dd/MM/yyyy");
		
	}
	//all args constr: to init complete state of emp
	public Employee(int empId, String firstName, String lastName, String email, String deptId, Date joinDate, double salary) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.deptId=deptId;
		this.joinDate = joinDate;
		this.salary = salary;
	}
	
	//add overloaded constr to init empid
	public Employee(int empId) {
		super();
		this.empId = empId;
	}
	
	//if + joinDate is used instead of formatting it will call Date class toString method and will print all the details from 
	//from tostring method so we have used format
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", deptId=" + deptId +", joinDate=" + sdf.format(joinDate) + ", salary=" + salary + "]";
	}
	
	

	//override equals for replacing ref equality by contents equality(PK: unique ID --emp id)
	@Override
	public boolean equals(Object o)
	{
		System.out.println("in emp equals");
		if(o instanceof Employee)
			return this.empId == ((Employee)o).empId;
		return false;
	}
}
