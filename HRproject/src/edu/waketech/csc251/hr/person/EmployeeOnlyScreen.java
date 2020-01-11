package edu.waketech.csc251.hr.person;


import edu.waketech.csc251.tools.Screener;

public class EmployeeOnlyScreen implements Screener<Employee> {

	@Override
	public Boolean test(Employee t) {
		return t.getClass().getSimpleName().equals("Employee");
			
		
	}

	
}
