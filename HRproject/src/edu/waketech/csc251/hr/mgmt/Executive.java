package edu.waketech.csc251.hr.mgmt;

public class Executive extends Manager {
	private double bonusRate;

	public Executive() {
	}

	public Executive(String name, double salary, double bonusRate, String department) {
		setName(name);
		this.bonusRate = bonusRate;
		setDepartment(department);
		setSalary(getTotalSalary(salary, bonusRate));
	}

	public double getTotalSalary(double salary, double bonusRate) {
		return (salary * (1 + bonusRate));
	}
	
	public double getBonusRate() {
		return this.bonusRate;
	}

	@Override
	public String toString() {
		return "Executive [name=" + getName() + ", salary=" + getSalary() + ", bonus rate=" + bonusRate + ", department= " + getDepartment() + "]";
	}
	
	@Override
	public int getMeasure() {
		return (int) (this.getSalary() + (this.getSalary() * this.bonusRate));
	}

}
