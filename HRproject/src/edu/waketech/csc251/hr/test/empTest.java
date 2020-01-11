package edu.waketech.csc251.hr.test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import edu.waketech.csc251.hr.mgmt.Executive;
import edu.waketech.csc251.hr.mgmt.ExecutiveScreen;
import edu.waketech.csc251.hr.mgmt.Manager;
import edu.waketech.csc251.hr.mgmt.ManagerOnlyScreen;
import edu.waketech.csc251.hr.person.Employee;
import edu.waketech.csc251.hr.person.EmployeeOnlyScreen;
import edu.waketech.csc251.tools.Screener;

public class empTest {
	
	Employee emp = new Employee("Matthew", 20000);
	Manager man = new Manager("Mark", 30000, "Sales");
	Executive ex = new Executive("Luke", 50000, .2, "Engineering");
	
	Screener<Employee> empScreen = new EmployeeOnlyScreen();
	Screener<Employee> manScreen = new ManagerOnlyScreen();
	Screener<Employee> exScreen = new ExecutiveScreen();
	
	
	// First, test that each is only an Employee:
	@Test
	public void testIfOnlyEmp1() {
		assertEquals(true, empScreen.test(emp));
	}
	
	@Test
	public void testIfOnlyEmp2() {
		assertEquals(false, empScreen.test(man));
	}
	
	@Test
	public void testIfOnlyEmp3() {
		assertEquals(false, empScreen.test(ex));
	}
	
	
	// Second, test that each is only a Manager:
	
	@Test
	public void testIfOnlyMan1() {
		assertEquals(false, manScreen.test(emp));
	}
	
	@Test
	public void testIfOnlyMan2() {
		assertEquals(true, manScreen.test(man));
	}
	
	@Test
	public void testIfOnlyMan3() {
		assertEquals(false, manScreen.test(ex));
	}
	
	
	// Third, test that each is only an Executive:
	
	@Test
	public void testIfOnlyEx1() {
		assertEquals(false, exScreen.test(emp));
	}
	
	@Test
	public void testIfOnlyEx2() {
		assertEquals(false, exScreen.test(man));
	}
	
	@Test
	public void testIfOnlyEx3() {
		assertEquals(true, exScreen.test(ex));
	}

}
