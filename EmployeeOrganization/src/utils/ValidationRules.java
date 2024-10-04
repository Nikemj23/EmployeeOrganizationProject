package utils;

import java.text.ParseException;
import java.util.Date;

import com.app.core.Employee;

import custom_exceptions.EmpHandlingException;

import static com.app.core.Employee.sdf;

public class ValidationRules {
	public static final int MIN_LENGTH;
	public static final int MAX_LENGTH;
	public static Date thresholdDate;
	static { // for initializing
		MIN_LENGTH = 4;
		MAX_LENGTH = 15;
		try {
			thresholdDate = sdf.parse("1/4/2021");
		} catch (ParseException e) {
			System.out.println("error in static init block " + e);
		}
	}

	// add public static method for validating all inputs
	public static Employee validateAllInputs(int empId, Employee[] empData, String firstName, String lastName,
			String email, String deptId, String joinDate, double salary) throws EmpHandlingException, ParseException {
		validateEmpId(empId, empData);
		validateName(firstName, "first");
		validateName(lastName, "last");
		validateEmail(email);
		validateDept(deptId);
		Date date = parseValidateJoinDate(joinDate);
		// => all i/ps are valid --encapsulate all these details in emp class instance
		// ,ret its ref to the caller
		return new Employee(empId, firstName, lastName, email, deptId, date, salary);
	}

	// add public static method to validate email (invoked by tester)
	// rule: must contain :"@" --contains, must end with .com --endsWith
	// return validated email, in case of no validation errors
	// otherwise throw custom exec
	public static String validateEmail(String email) throws EmpHandlingException {
		if (email.contains("@") && email.endsWith(".com"))
			return email;
		throw new EmpHandlingException("Invalid Email!!!!");// unhandled checked exception(compiler forces handling of
															// the checked exception)
		// either use try catch block or delegate to the caller
	}

	// add static method to validate first/last name: in case of success return
	// first name, otherwise throw custom Exception
	// with proper message
	public static String validateName(String name, String mesg) throws EmpHandlingException {
		if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH)
			throw new EmpHandlingException("Invalid " + mesg + " Name: Must be within range 4-15");
		// =>valid name
		return name;
	}

	public static String validateDept(String dept) throws EmpHandlingException {
		switch (dept) {
		case "Rnd":
		case "Finance":
		case "HR":
		case "Marketing":
			return dept;

		default:// =>invalid department
			throw new EmpHandlingException("Invalid Dept!!!");

		}
	}

	// add a static method for parsing n validation of join date
	public static Date parseValidateJoinDate(String joinDate) throws ParseException, EmpHandlingException {
		// parsing (string --> Date)
		Date d1 = sdf.parse(joinDate);
		// => parsing is succesful-->continue to validation
		if (d1.after(thresholdDate))
			return d1;// parsed n validated date
		// =>validation failure
		throw new EmpHandlingException("invalid join date");
	}

	// add a static method to check for duplicate employee id
	// in case of dup id: throw custom exec, o.w return return validated(non dup)
	// emp id to the caller
	public static int validateEmpId(int empId, Employee[] empData) throws EmpHandlingException// empId=sc.nextInt(),
																								// empData=emps
	{
		// empData : {e1(10),e2(1),e3(100),e4(30),e5(50), null}
		Employee newEmp = new Employee(empId);// empId 100
		// will call overloaded id based constructor
		// since equals method take argument of object type so in order to compare two
		// employees

		// how are u going to check for duplicates?: call Employee's equals
		for (Employee e : empData)
			if (e != null)
				if (e.equals(newEmp))
					throw new EmpHandlingException("Dual Emp ID!!!! ");
		// ==> no dup id
		return empId;
	}
}
