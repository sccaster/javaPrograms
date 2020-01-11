package edu.waketech.csc251.hr.mgmt;

import edu.waketech.csc251.hr.person.Employee;
import edu.waketech.csc251.tools.Screener;

public class ManagerOnlyScreen implements Screener<Employee> {

	@Override
	public Boolean test(Employee t) {
		return t.getClass().getSimpleName().equals("Manager");
	}

}
