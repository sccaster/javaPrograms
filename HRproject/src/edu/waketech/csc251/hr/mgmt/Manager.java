package edu.waketech.csc251.hr.mgmt;

import edu.waketech.csc251.hr.person.Employee;

public class Manager extends Employee {
	private String department;
	public Manager() {
	}
	
	public Manager(String name, double salary, String department) {
		setName(name);
		setSalary(salary);
		this.department = department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getDepartment() {
		return department;
	}
	
	@Override
	public String toString() {
		return "Manager [name=" + getName() + ", salary=" + getSalary() + ", department=" + department + "]";
	}

}
