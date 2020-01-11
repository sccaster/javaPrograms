package edu.waketech.csc251.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import edu.waketech.csc251.collection.DataSetGeneric;
import edu.waketech.csc251.hr.mgmt.Executive;
import edu.waketech.csc251.hr.mgmt.ExecutiveScreen;
import edu.waketech.csc251.hr.mgmt.Manager;
import edu.waketech.csc251.hr.mgmt.ManagerOnlyScreen;
import edu.waketech.csc251.hr.person.Employee;
import edu.waketech.csc251.hr.person.EmployeeOnlyScreen;
import edu.waketech.csc251.tools.Screener;

public class dataSetGenericTest {





	Employee emp = new Employee("Matthew", 20000);
	Manager man = new Manager("Mark", 30000, "Sales");
	Executive ex = new Executive("Luke", 50000, .2, "Engineering");
	DataSetGeneric<Employee> dataStore = new DataSetGeneric<>();


	Screener<Employee> empScreen = new EmployeeOnlyScreen();
	Screener<Employee> manScreen = new ManagerOnlyScreen();
	Screener<Employee> exScreen = new ExecutiveScreen();




	@Test
	public void testIfOnlyEmp() {
		dataStore.add(emp);
		dataStore.add(man);
		dataStore.add(ex);
		assertEquals("[0] Employee [name=" + emp.getName() + ", salary=" + emp.getSalary() + "]\n", dataStore.getList(dataStore, empScreen));
	}
	
	@Test
	public void testIfOnlyMan() {
		dataStore.add(emp);
		dataStore.add(man);
		dataStore.add(ex);
		assertEquals("[0] Manager [name=" + man.getName() + ", salary=" + man.getSalary() + ", department=" + man.getDepartment() + "]\n", dataStore.getList(dataStore, manScreen));
	}
	
	@Test
	public void testIfOnlyEx() {
		dataStore.add(emp);
		dataStore.add(man);
		dataStore.add(ex);
		assertEquals("[0] Executive [name=" + ex.getName() + ", salary=" + ex.getSalary() + ", bonus rate=" + ex.getBonusRate() + ", department= " + ex.getDepartment() + "]\n", dataStore.getList(dataStore, exScreen));
	}
}
