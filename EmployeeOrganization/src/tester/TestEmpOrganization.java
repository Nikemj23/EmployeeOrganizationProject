package tester;

import static com.app.core.Employee.sdf;
//can directly access validateEmail method of ValidationRules class
import static utils.ValidationRules.*;//to access all static members from this class

import java.util.Scanner;

import com.app.core.Employee;

import custom_exceptions.EmpHandlingException;

public class TestEmpOrganization {  //validation rules with centralized error handling

	public static void main(String[] args) {
		// scanner --try -- with -resources -- since scanner is autoClosable
		try (Scanner sc = new Scanner(System.in)) {
			// array
			System.out.println("Enter max no of emps to hire");
			Employee[] emps = new Employee[sc.nextInt()];// array object -- all arrray slots initialized to nulls
			// exit, counter
			boolean exit = false;
			int counter = 0;

			// while--options 1.Hire Emp 2. Display all 3. Exit
			while (!exit) {
				System.out.println("Options 1.Hire Emp 2. Display 3.Exit");
				System.out.println("Choose");
				try { // if this inner try catch removed in case of invalid inputs code will abort

					switch (sc.nextInt()) {
					case 1:
						if (counter < emps.length) {
							System.out.println(
									"Enter emp details-- empId, firstName, lastName, email, deptId, joinDate, salary");
							Employee e = validateAllInputs(sc.nextInt(), emps, sc.next(), sc.next(), sc.next(),
									sc.next(), sc.next(), sc.nextDouble());
//							Employee e = new Employee(validateEmpId(sc.nextInt(),emps), validateName(sc.next(), "First"),
//									validateName(sc.next(), "last"), validateEmail(sc.next()), validateDept(sc.next()),
//									parseValidateJoinDate(sc.next()), sc.nextDouble());
							// ==>valid inputs
							emps[counter++] = e;
							System.out.println("emp hired!!!!");

						} else // throw custom exception-- recruitment over
							throw new EmpHandlingException("Recruitment over!!!!");

						break;
					case 2:
						System.out.println("Emp details");
						for (Employee e : emps)
							if (e != null)
								System.out.println(e);
						break;
					case 3:

						break;

					}// switch over
				} // inner try over
				catch (Exception e) {
					System.out.println("Error" + e);
				}
				// clear off all pending inputs from scanner buffer till next line
				sc.nextLine();// if not given will cause infinite recursion
			} // end of while

		} // try-with-resources over:sc.close()
	} // main over

}// cls over
