package src.edu.waketech.academic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The representation of a Course at Wake Tech.
 * 
 * <p>
 * The general concept of a course is that it has a roster of students. For each
 * of these students, there is a list of Assignmemts. This list of Assignments
 * may have multiple Assignments with identical names, as a student may attempt
 * an Assignment multiple times.
 * </p>
 * <p>
 * In addition to a roster of students with their assignments, a Course has a
 * String department name (such as "CSC" or "DBA") and an int course number
 * (such as 151 or 120).
 * </p>
 * <p>
 * The course roster is a Map&lt;Integer, List&lt;Assignment&gt;&gt;, where the
 * key is the student's id number.
 * </p>
 * 
 * @author parks
 *
 */
public class Course {

	private String department;
	private int courseNumber;

	// Map of <studentId, List of assignments>
	private Map<Integer, List<Assignment>> rosterWithAssignments;

	/**
	 * Create a Course with a given courseNumber and department
	 * 
	 * Note that a course number must be a positive number, and the department name
	 * must be non-null. Otherwise an IllegalArgumentException is thrown.
	 * 
	 * @param courseNumber
	 *            the course number, such as 151 or 251
	 * @param department
	 *            the department offering the course, such as CSC or DBA
	 */
	public Course(int courseNumber, String department) {
		if (courseNumber <= 0) {
			throw new IllegalArgumentException("course number must be greater than zero");
		}
		if (department == null) {
			throw new IllegalArgumentException("department must be non-null");
		}
		this.courseNumber = courseNumber;
		this.department = department.toUpperCase();
		rosterWithAssignments = new HashMap<>();
	}

	/**
	 * See Course(courseNumber, department). Only the parameters have been reversed
	 * 
	 * @param department
	 *            the department offering the course, such as CSC or DBA
	 * @param courseNumber
	 *            the course number, such as 151 or 251
	 */
	public Course(String department, int courseNumber) {
		this(courseNumber, department);
	}

	/**
	 * Add a student to this course roster. Students are identified in courses only
	 * by id.
	 * 
	 * @param studentId
	 *            to be added to this course
	 * 
	 * @return true if the roster of students was changed as part of this method
	 *         call, false otherwise.
	 */
	// TO-DONE
	public boolean addStudent(int studentId) {
		ArrayList<Assignment> empty = new ArrayList<Assignment>();
		if (rosterWithAssignments.containsKey(studentId)) {
			return false;
		} else {
			rosterWithAssignments.put(studentId, empty);
			return true;
		}
	}

	/**
	 * Add a given assignment for a given student to this course
	 * 
	 * Note that the assignment must be non-null. Otherwise an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param studentId
	 *            identifier of the student related to this assignment
	 * @param assignment
	 *            containing the grade for this student
	 */
	// TO-DONE
	public void addAssignment(int studentId, Assignment assignment) {
		List<Assignment> retList = rosterWithAssignments.get(studentId);
		if (retList == null) {
			retList = new ArrayList<Assignment>();
			retList.add(assignment);
		} else {
			retList.add(assignment);
		}
		rosterWithAssignments.put(studentId, retList);
	}


	/**
	 * Scan this Course for all assignment related to a given Student
	 * 
	 * @param studentId
	 *            identifier of the student whose assignments are requested
	 * 
	 * @return all the assignments related to the given student, or an empty list.
	 */
	public List<Assignment> getAssignmentsForStudent(int studentId) {
		List<Assignment> retList = rosterWithAssignments.get(studentId);
		if (retList == null)
			retList = new ArrayList<>();
		return retList;
	}

	/**
	 * Find the particular Assignments for a particular Student. It is possible that
	 * a given Assignment has multiple attempts, and each of them will be returned.
	 * 
	 * 
	 * @param studentId
	 *            identifier of the Student
	 * 
	 * @param assignmentName
	 *            the name of the Assignment
	 * 
	 * @return the assignments with the given name and student
	 */
	// TO-DONE
	public List<Assignment> getAssignment(int studentId, String assignmentName) {
		ArrayList<Assignment> listOfAssignments = new ArrayList<>();
		if (rosterWithAssignments.containsKey(studentId)) {
			List<Assignment> retList = rosterWithAssignments.get(studentId);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					if (retList.get(i).isNamed(assignmentName)) {
						listOfAssignments.add(retList.get(i));
					}
				}
			}
		}
		return listOfAssignments;
	}

	/**
	 * Given an Assignment, list the Student attempts. See getAssignment for how a
	 * given assigment may have multiple attempts
	 * 
	 * @param assignmentName
	 *            the name of the assignment whose attempts are to be returned
	 * 
	 * 
	 * @return the students attempting the given Assignment, with their resulting
	 *         results
	 */
	// TO-DONE
	public Map<Integer, List<Assignment>> getStudentsForAssignment(String assignmentName) {
		Map<Integer, List<Assignment>> rv = new HashMap<Integer, List<Assignment>>();
		Set<Integer> IDset = rosterWithAssignments.keySet();
		IDset.stream()
			 .forEach(e -> {
				 rv.put(e, (this.getAssignment(e, assignmentName)));
				 });
		return rv;
	}

	/**
	 * An unordered list of student ids in this course
	 * 
	 * @return an unordered list of student ids in this course
	 */
	public List<Integer> getAllStudentIds() {
		return new ArrayList<Integer>(rosterWithAssignments.keySet());
	}

	/**
	 * Return, in alphabetical order, all assignment names known to this course.
	 * <br>
	 * Hint: Use a data structure that will automatically sort and remove duplicate
	 * entries
	 * 
	 * @return sorted list of all assignment names known to this course
	 */
	// TO-DONE
	public List<String> getAllAssignmentNames() {
		ArrayList<String> allAssignmentNames = new ArrayList<String>();
		Set<List<Assignment>> stuff = rosterWithAssignments.values().stream()
				.collect(Collectors.toSet());
		for (List<Assignment> w : stuff) {
			for (Assignment t : w) {
				allAssignmentNames.add(t.getName());
			}
		}
		List<String> finalAssignmentNames = allAssignmentNames.stream()
						.distinct()
						.collect(Collectors.toList());
		return finalAssignmentNames;
	}

	/**
	 * Filter the roster based on a student filter AND an assignment filter. 
	 * <br>
	 * Note that this method is optional. If you choose not to provide it, simply
	 * return 
	 * <em> 
	 * new HashMap&lt;Integer, List&lt;Assignment&gt;&gt;(); 
	 * </em>
	 * 
	 * @param studentFilter
	 *            identifies the Students to be considered for this filter
	 * 
	 * @param assignmentFilter
	 *            identifies the Assignment to be considered for this filter
	 * 
	 * @return the Students with the Assignments that pass both the Student and
	 *         Assignment filters.
	 */
	// TODO optional method - worth 10 bonus points
	public Map<Integer, List<Assignment>> filterRoster(Predicate<Integer> studentFilter,
			Predicate<Assignment> assignmentFilter) {
		Map<Integer, List<Assignment>> retVals = new HashMap<>();
		ArrayList<Integer> nums = new ArrayList<Integer>();
		ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
		ArrayList<Assignment> newList = new ArrayList<Assignment>();
		rosterWithAssignments.keySet().stream()
									.filter(e -> e.equals(studentFilter))
									.forEach(e -> {
										nums.add(e);
									});
		rosterWithAssignments.values().stream()
									.filter(e -> e.equals(assignmentFilter))
									.forEach(e -> {
										for (int i = 0; i < e.size(); i++) {
											assignmentList.add(e.get(i));
										}
									});
		newList.add(assignmentList.get(0));
		nums.stream()
			.forEach(e -> {
				retVals.put(e, newList);
			});
									

		return retVals;
	}

	/**
	 * Simple getter for department
	 * 
	 * @return this course's department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Setter for department. 
	 * <br>
	 * A null parameter value will cause an IllegalArgumentException to be thrown
	 * 
	 * @param department
	 *            new department for this course
	 */
	public void setDepartment(String department) {
		if (department == null) {
			throw new IllegalArgumentException("department must be non-null");
		}
		this.department = department;
	}

	/**
	 * Getter for this course number
	 * 
	 * @return this course's number
	 */
	public int getCourseNumber() {
		return courseNumber;
	}

	/**
	 * Setter for this course number. 
	 * <br>
	 * Note that course number must be positive. A non-positive parameter value will
	 * cause an IllegalArgumentException to be thrown
	 * 
	 * @param courseNumber
	 *            new course number
	 */
	public void setCourseNumber(int courseNumber) {
		if (courseNumber <= 0) {
			throw new IllegalArgumentException("course number must be greater than zero");
		}
		this.courseNumber = courseNumber;
	}

	/**
	 * Getter for this course's roster of students with each student's assignment
	 * scores
	 * 
	 * @return A Map keyed by student id. For each student, the list of assignments
	 *         with their scores
	 */
	public Map<Integer, List<Assignment>> getRoster() {
		return rosterWithAssignments;
	}

	// haven't covered hashCode
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 * 
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + courseNumber; result = prime * result +
	 * ((department == null) ? 0 : department.hashCode()); return result; }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseNumber != other.courseNumber)
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [department=" + department + ", courseNumber=" + courseNumber + ", roster="
				+ rosterWithAssignments + "]";
	}
}
