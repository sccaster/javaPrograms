package edu.waketech.csc251.hr;

import java.util.Scanner;

import edu.waketech.csc251.collection.DataSetGeneric;
import edu.waketech.csc251.hr.mgmt.Executive;
import edu.waketech.csc251.hr.mgmt.ExecutiveScreen;
import edu.waketech.csc251.hr.mgmt.Manager;
import edu.waketech.csc251.hr.mgmt.ManagerOnlyScreen;
import edu.waketech.csc251.hr.person.*;
import edu.waketech.csc251.tools.Screener;
import edu.waketech.csc251.tools.Utils;

public class Payroll {

   // Possible User Actions.
   public static final String MENU_EXIT = "Exit";
   public static final String MENU_ADD_EMPLOYEE = "Add Employee";
   public static final String MENU_ADD_MANAGER = "Add Manager";
   public static final String MENU_ADD_EXECUTIVE = "Add Executive";
   public static final String MENU_LIST_ALL = "List All";
   public static final String MENU_LIST_EMPLOYEE = "List (Regular) Employees";
   public static final String MENU_LIST_MANAGER = "List Managers";
   public static final String MENU_LIST_EXECUTIVE = "List Executives";
   public static final String MENU_SHOW_HIGHEST_SALARY_PERSON = "Show Highest Salary Person";
   public static final String MENU_GENERATE_PAYROLL = "Generate Payroll";

   // Menu for user actions
   public static final String[] MAIN_MENU = { MENU_EXIT, MENU_ADD_EMPLOYEE, MENU_ADD_MANAGER, MENU_ADD_EXECUTIVE,
         MENU_LIST_ALL, MENU_LIST_EMPLOYEE, MENU_LIST_MANAGER, MENU_LIST_EXECUTIVE, MENU_SHOW_HIGHEST_SALARY_PERSON,
         MENU_GENERATE_PAYROLL, };

   public static void main(String[] args) {
      Scanner kybd = new Scanner(System.in);
      DataSetGeneric<Employee> hrdb = new DataSetGeneric<>();


      String userChoice = Utils.userChoose(kybd, MAIN_MENU);
      while (!MENU_EXIT.equals(userChoice)) {
         if (MENU_ADD_EMPLOYEE.equals(userChoice)) {
            addEmployee(kybd, hrdb);
         } else if (MENU_ADD_MANAGER.equals(userChoice)) {
            addManager(kybd, hrdb);
         } else if (MENU_ADD_EXECUTIVE.equals(userChoice)) {
            addExecutive(kybd, hrdb);
         } else if (MENU_LIST_ALL.equals(userChoice)) {
            displayEverybody(kybd, hrdb);
         } else if (MENU_LIST_EMPLOYEE.equals(userChoice)) {
            displayOnlyEmployees(kybd, hrdb);
         } else if (MENU_LIST_MANAGER.equals(userChoice)) {
            displayOnlyManagers(kybd, hrdb);
         } else if (MENU_LIST_EXECUTIVE.equals(userChoice)) {
            displayOnlyExecutives(kybd, hrdb);
         } else if (MENU_SHOW_HIGHEST_SALARY_PERSON.equals(userChoice)) {
            displayHighestSalary(kybd, hrdb);
         } else if (MENU_GENERATE_PAYROLL.equals(userChoice)) {
            generatePayroll(kybd, hrdb);
         }

         userChoice = Utils.userChoose(kybd, MAIN_MENU);
      }
      System.out.println("Bye");
   }

   /**
    * Add a regular employee to the data store
    * 
    * @param input
    *           incoming data stream
    * @param dataStore
    *           will hold the new employee
    */
   public static void addEmployee(Scanner input, DataSetGeneric<Employee> dataStore) {
	   System.out.println("Name: ");
	   String name = input.next();
	   System.out.println("Enter Salary: ");
	   int sal = input.nextInt();
	   Employee employee = new Employee(name, sal);
	   dataStore.add(employee);
   }

   /**
    * Add an executive to the data store
    * 
    * @param input
    *           incoming data stream
    * @param dataStore
    *           will hold the new executive
    */
   public static void addExecutive(Scanner input, DataSetGeneric<Employee> dataStore) {
	   System.out.println("Name: ");
	   String name = input.next();
	   System.out.println("Enter Salary: ");
	   int sal = input.nextInt();
	   System.out.println("Enter Bonus Rate (in decimal): ");
	   double bonus = input.nextDouble();
	   System.out.println("Enter Department Managed: ");
	   String dpt = input.next();
	   Executive executive = new Executive(name, sal, bonus, dpt);
	   dataStore.add(executive);
   }

   /**
    * Add a manager to the data store
    * 
    * @param input
    *           incoming data stream
    * @param dataStore
    *           will hold the new manager
    */
   public static void addManager(Scanner input, DataSetGeneric<Employee> dataStore) {
	   System.out.println("Name: ");
	   String name = input.next();
	   System.out.println("Enter Salary: ");
	   int sal = input.nextInt();
	   System.out.println("Enter Department Managed: ");
	   String dpt = input.next();
	   Manager manager = new Manager(name, sal, dpt);
	   dataStore.add(manager);
   }

   /**
    * Display everybody in the data store
    * 
    * @param kybd
    *           incoming data stream so the user can verify that the data has been
    *           seen
    * @param dataStore
    *           containing the employees to be displayed
    */
   public static void displayEverybody(Scanner kybd, DataSetGeneric<Employee> dataStore) {
	   System.out.println(dataStore.getList(dataStore));
	   System.out.println("Press any key and enter to Continue: ");
	   kybd.next();
   }

   /**
    * Display the person with the highest salary
    * 
    * @param kybd
    *           incoming data stream so the user can verify that the data has been
    *           seen
    * @param dataStore
    *           containing the employees to be displayed
    */
   public static void displayHighestSalary(Scanner kybd, DataSetGeneric<Employee> dataStore) {
	   System.out.println(dataStore.getMax(dataStore));
	   System.out.println("Press any key and enter to continue: ");
	   kybd.next();
   }

   /**
    * Display only the regular employees
    * @param kybd
    *           incoming data stream so the user can verify that the data has been
    *           seen
    * @param dataStore
    *           containing the employees to be displayed
    */
   public static void displayOnlyEmployees(Scanner kybd, DataSetGeneric<Employee> dataStore) {
	   Screener<Employee> employeeScreen = new EmployeeOnlyScreen();
	   System.out.println(dataStore.getList(dataStore, employeeScreen));
	   System.out.println("Press any key and enter to continue: ");
	   kybd.next();
	   
   }

   /**
    * Display only the executives
    * @param kybd
    *           incoming data stream so the user can verify that the data has been
    *           seen
    * @param dataStore
    *           containing the employees to be displayed
    */
   public static void displayOnlyExecutives(Scanner kybd, DataSetGeneric<Employee> dataStore) {
	   Screener<Employee> executiveScreen = new ExecutiveScreen();
	   System.out.println(dataStore.getList(dataStore, executiveScreen));
	   System.out.println("Press any key and enter to continue: ");
	   kybd.next();
   }

   /**
    * Display on the managers
    * @param kybd
    *           incoming data stream so the user can verify that the data has been
    *           seen
    * @param dataStore
    *           containing the employees to be displayed
    */
   public static void displayOnlyManagers(Scanner kybd, DataSetGeneric<Employee> dataStore) {
	   Screener<Employee> managerScreen = new ManagerOnlyScreen();
	   System.out.println(dataStore.getList(dataStore, managerScreen));
	   System.out.println("Press any key and enter to continue: ");
	   kybd.next();
   }

   /**
    * Generate the payroll
    * @param kybd
    *           incoming data stream so the user can verify that the data has been
    *           seen
    * @param dataStore
    *           containing the employees 
    */
   public static void generatePayroll(Scanner kybd, DataSetGeneric<Employee> dataStore) {
	   dataStore
	   	.stream()
	   	.forEach(e -> System.out.println("\n[" + dataStore.indexOf(e) + "] Pay " + e.getName() + " $" + e.getSalary()));
	   System.out.println("Press any key and enter to continue: ");
	   kybd.next();
   }
}
