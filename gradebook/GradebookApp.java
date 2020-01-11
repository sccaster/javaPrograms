package src.edu.waketech;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import src.edu.waketech.academic.Assignment;
import src.edu.waketech.academic.Course;
import src.edu.waketech.common.Student;
import src.edu.waketech.common.StudentBody;
import src.edu.waketech.test.StudentGenerator;
import src.edu.waketech.utils.Utils;

/**
 * A text-based Gradebook application.
 * 
 * @author parks
 *
 */
public class GradebookApp {

	/**
	 * Top level menu
	 */
	public static final String[] MENU = { 
			"Create Course", 
			"Populate a Course", 
			"Add an Assignment",
			"Display all Labs for a Student in all courses", 
			"Display all Students for a Course",
			"Display the average for a Student in a Course", 
			"Display the average for an Assignment",
			"Display a student's average in all courses",
			"Display all students that are in all courses", 
			"Exit", // must be last
																										// // last
	};

	public static void main(String[] args) {

		// TO-DONE
		StudentBody allStudents = StudentBody.getInstance();
		Scanner kybd = new Scanner(System.in);

		// TO-DONE
		List<Course> courses = new ArrayList<>();

		String userChoice = "";

		// main loop for using interaction
		while (!MENU[MENU.length - 1].equals(userChoice)) {
			userChoice = Utils.userChoose(kybd, MENU);
			
			// "Create Course",
			if (MENU[0].equals(userChoice)) {
				System.out.print("What's the department? ");
				String dept = kybd.next();
				System.out.print("What's the course number? ");
				int courseNum = kybd.nextInt();
				// TO-DONE
				Course course = new Course(courseNum, dept);
				courses.add(course);
			}
			
			// "Populate a Course",
			else if (MENU[1].equals(userChoice)) {
				Course courseToHandle = whichCourse(kybd, courses);
				if (courseToHandle != null) {
					System.out.print("How many students? ");
					int seats = kybd.nextInt();
					// TO-DONE use populateCourse to place students into 
					// the specified course.
					// Note that because populateCourse may
					// generate duplicate students, and duplicate students
					// are ignored by the course, the resulting number
					// of students in the course may be less than the
					// requested number
					populateCourse(courseToHandle, seats);
				}
			}
			
			// "Add an Assignment",
			else if (MENU[2].equals(userChoice)) {
				Course courseToHandle = whichCourse(kybd, courses);
				if (courseToHandle != null) {
					System.out.print("What's the assignment name? ");
					String assignmentName = kybd.next();
					System.out.print("How many points?");
					int possiblePoints = kybd.nextInt();
					// TO-DONE use createAssignment to create Assignment objects with
					// the given name and possible points
					// and a random score for each student in the
					// course
					createAssignment(courseToHandle, assignmentName, possiblePoints);
				}
			}
				
			// Display all Labs for a Student in all courses 
			else if (MENU[3].equals(userChoice)) {
				System.out.print("What's the student's id? \n");
				// Print the ordered list of all students:
				ArrayList<String> makeList = new ArrayList<String>();
				Set<Integer> students = allStudents.keySet();
				StudentBody sb = StudentBody.getInstance();
				for (Integer s: students) {
					Student a = (sb.get(s));
					makeList.add(a.getLastName() + ", " + a.getFirstName() + " studentid: " + s);
				}
				Collections.sort(makeList);
				for (int t = 0; t< makeList.size(); t++)
					System.out.println(makeList.get(t));
				
				// Get the student id sought:
				int studentId = kybd.nextInt();
				System.out.println(oneStudentResults(studentId, courses));
			}
			
			// "Display all Students for a Course",
			else if (MENU[4].equals(userChoice)) {
				Course courseToHandle = whichCourse(kybd, courses);
				if (courseToHandle != null) {
					List<String> roster = courseRoster(courseToHandle);
					for (String s: roster) {
						System.out.println(s);
					}
				}
			}	
			
			// "Display the average for a Student in a Course",
			else if (MENU[5].equals(userChoice)) {
				Course courseToHandle = whichCourse(kybd, courses);
				if (courseToHandle != null) {
					System.out.println("What's the student id? ");
					for (String s: courseRoster(courseToHandle)) {
						System.out.println(s);
					}
					int studentToAverage = kybd.nextInt();
					// TO-DONE use calculateStudentAverageInOneCourse,
					// then print the following:
					// student last name, student first name, student id, course department, course number, student average
					StudentBody sb = StudentBody.getInstance();
					Student b = sb.get(studentToAverage);
					double avgInOneCourse = calculateStudentAverageInOneCourse(courseToHandle, studentToAverage);
					String dispLine = ("For " + b.getLastName() + ", " + b.getFirstName() + " with id " + b.getId()  + "\n");
					dispLine += ("in class " + courseToHandle.getDepartment() + " " + courseToHandle.getCourseNumber() + " has average " + avgInOneCourse);
					System.out.println(dispLine);
				}

			}
			
			// 	"Display the average for an Assignment",
			else if (MENU[6].equals(userChoice)) {
				Course courseToHandle = whichCourse(kybd, courses);
				if (courseToHandle != null) {
					System.out.println("Which assignment? ");
					List<String> retVal = courseToHandle.getAllAssignmentNames();
					for (int i = 0; i < retVal.size(); i++) {
						System.out.println("[" + i + "] " + retVal.get(i));
					}
					int num = kybd.nextInt();
					String assignment = retVal.get(num);
					// TO-DONE retrieve all assignment names for the course,
					// ask the user which assignment to process,
					// use courseAverageForAssignment to calculate the average,
					// then display it
					System.out.println("The course average for that lab is ");
					System.out.println(courseAverageForAssignment(courseToHandle, assignment) + "%\n");
				}
			}
			
			// 	"Display a student's average in all courses",
			else if (MENU[7].equals(userChoice)) {
				System.out.println("What's the student's id? ");
				Set<Integer> students = allStudents.keySet();
				ArrayList<String> makeList = new ArrayList<String>();
				StudentBody sb = StudentBody.getInstance();
				for (Integer s: students) {
					Student a = (sb.get(s));
					makeList.add(a.getLastName() + ", " + a.getFirstName() + " studentid: " + s);
				}
				Collections.sort(makeList);
				for (int t = 0; t< makeList.size(); t++)
					System.out.println(makeList.get(t));
				int studentId = kybd.nextInt();
				// TO-DONE Look up and display student last name, first name, id,
				// for each course the student is in, use calculateStudentAverageInOneCourse
				// to calculate the student's average.
				// Display the course department and number, with the student's average
				
				Student s = sb.get(studentId);
				System.out.println(s.getLastName() + " " + s.getFirstName() + " with id " + s.getId() + "\n");
				for (Course c: courses) {
					if (c.getRoster().containsKey(s.getId())) {
						System.out.println("in " + c.getDepartment() + "-" + c.getCourseNumber() + " has an average of ");
						System.out.println(calculateStudentAverageInOneCourse(c, s.getId()) + "%\n");
					}
				}

			}
			
			// 	"Display all students that are in all courses", 
			else if (MENU[8].equals(userChoice)) {
				// TO-DONE use getStudentsTakingEverything to find all students taking every course.
				// print the first name, last name and id of each student.
				StudentBody sb = StudentBody.getInstance();
				List<Integer> studentsTakingEverything = new ArrayList<>();
				List<String> orderedStudents = new ArrayList<String>();
				Student s = null;
				studentsTakingEverything = getStudentsTakingEverything(courses);
				for (int i = 0; i < studentsTakingEverything.size(); i++) {
					s = sb.get(studentsTakingEverything.get(i));
					orderedStudents.add(s.getLastName() + ", " + s.getFirstName() + " studentid=" + s.getId());
				Collections.sort(orderedStudents);
				}
				System.out.println("Students in all courses: \n");
				for (int t = 0; t<orderedStudents.size(); t++) {
					System.out.println(orderedStudents.get(t));
				}
			}
		}
	}
	
	// TO-DONE
	/**
	 * Determine which students are taking <em>all</em> of the Course list.
	 * 
	 * @param courses to be examined for common students
	 * 
	 * @return ids for students in every course in courses
	 */
	public static List<Integer> getStudentsTakingEverything(List<Course> courses) {
		List<Integer> retList = new ArrayList<>();
		List<Integer> studentIds = courses.get(0).getAllStudentIds();
		int numClassesTaken = 0;
		for (int i = 0; i < studentIds.size(); i++) {
			numClassesTaken = 0;
			for (int t = 0; t < courses.size(); t++) {
				if (courses.get(t).getAllStudentIds().contains(studentIds.get(i))) {
					numClassesTaken += 1;
				}
			}
			if (courses.size() == numClassesTaken) {
				retList.add(studentIds.get(i));
			}
		}
		
		return retList;
	}
	
	// TO-DONE
	/**
	 * Average the Assignments of a given name in one course (for all students)
	 * 
	 * @param courseToHandle the course whose assignments are to be averaged
	 * 
	 * @param labName the name of the Assignment 
	 * 
	 * @return the average (in percent) of all labName assignments
	 */
	public static double courseAverageForAssignment(Course courseToHandle, String labName) {
		List<Integer> studentIds = courseToHandle.getAllStudentIds();
		ArrayList<List<Assignment>> rv = new ArrayList<List<Assignment>>();
		double avgGrade = 0;
		int divideBy = 0;
		double finalGrade = 0;
		// This gets a list containing lists of the assignment so that the information can be drawn out in the next for loops
		for (int i = 0; i < studentIds.size(); i++) {
			rv.add(courseToHandle.getAssignment(studentIds.get(i), labName));
		}
		// These for loops draw out the total scores of the assignments so that the final average can be retrieved
		for (int t = 0; t < rv.size(); t++) {
			for (int o = 0; o < rv.get(t).size(); o++) {
				avgGrade += rv.get(t).get(o).getScorePercent();
				divideBy += 1;
			}
		}
		finalGrade = avgGrade / divideBy;
		return finalGrade;
	}

	// TO-DONE
	/**
	 * Calculate a given student's grade in one course
	 * 
	 * @param courseToHandle the student's course
	 * 
	 * @param studentToAverage the id of the student whose score is to be calculated
	 * 
	 * @return the student's average for all Assignments, in percent
	 */
	public static double calculateStudentAverageInOneCourse(Course courseToHandle, int studentToAverage) {
		List<Assignment> retList = courseToHandle.getAssignmentsForStudent(studentToAverage);
		double avgGrade = 0;
		for (int i = 0; i < retList.size(); i++) {
			avgGrade += retList.get(i).getScorePercent();
		}
		double finalGrade = avgGrade / retList.size();
		return finalGrade;
	}
	
	// TO-DONE
	/**
	 * For each student associated with a set of studentIds,
	 * fetch and
	 * sort the students, and assemble
	 * their toString results.  
	 * Students are sorted first by last name, then first name and finally studentId
	 * <br>
	 * Hint:  use a 
	 * <em>
	 * Comparator
	 * </em> to specify the sort order
	 * 
	 * @param studentKeys list of student ids to return
	 * 
	 * @return list of Student toString values for each studentId in sorted order
	 */
	public static List<String> roster(Set<Integer> studentKeys) {
		StudentBody sb = StudentBody.getInstance();
		List<String> retVal = new ArrayList<>();
		studentKeys.stream()
		.forEach(e -> {
			retVal.add(sb.get(e).toString());
		});
		Collections.sort(retVal);
		return retVal;
	}

	/**
	 * List the students in a given Course sorted by last name, first name, studentId
	 * 
	 * @param courseToHandle the Course whose roster we will return
	 * 
	 * @return sorted list of students, sorted by lastname, then firstname, and finally studentid 
	 * (one entry per student)
	 */
	public static List<String> courseRoster(Course courseToHandle) {
		ArrayList<String> makeList = new ArrayList<String>();
		Set<Integer> students = courseToHandle.getRoster().keySet();
		StudentBody sb = StudentBody.getInstance();
		for (Integer s: students) {
			Student a = (sb.get(s));
			makeList.add(a.getLastName() + ", " + a.getFirstName() + " studentid: " + s);
		}
		Collections.sort(makeList);
		return makeList;
	}

	/**
	 * Construct a String describing a given student's labs for all courses.  
	 * The format of the returned String should be similar to the following example:
	 * <br>
	 * <em>
	 * for Sophie Princess
	 * <br>
	 * in course CSC251
	 * <br>
	 * lab00 40 out of 50 for 80%
	 * <br>
	 * lab01 81 out of 90 for 90%
	 * <br>
	 * in course CSC258
	 * <br>
	 * labSayHello 100 of 100 for 100%
	 * <br>
	 * </em>
	 * etc.
	 * 
	 * @param studentId whose name, id number, courses and labs in each course is to be returned
	 * 
	 * @param courses list to search for the given studentId
	 * 
	 * @return String formatted as above
	 */
	// TO-DONE
	public static String oneStudentResults(int studentId, List<Course> courses) {
		StudentBody sb = StudentBody.getInstance();
		String rv = "for " + sb.get(studentId).getFirstName() + " " + sb.get(studentId).getLastName() + "\n";
		List<Assignment> retList = null;
		for (int i =0; i < courses.size(); i++) {
			 retList = (courses.get(i).getAssignmentsForStudent(studentId));
			 rv += ("in course " + courses.get(i).getDepartment() + courses.get(i).getCourseNumber() + "\n");
			 for (int t = 0; t < retList.size(); t++) {
				 Assignment assign = retList.get(t);
				 rv += (assign.getName() + " " + assign.getScore() + " out of " + assign.getPossiblePoints() + " for " + assign.getScorePercent() + "%\n");
			 }
		}
		return rv;
	}

	/**
	 * Create a new Assignment for each student in the given Course.  
	 * <br>
	 * The application 
	 * <em>
	 * "should"
	 * </em> ask the user 
	 * <br>
	 * "give me the grade for Sophie, 
	 * <br>
	 * now give me the grade for Sally, 
	 * <br>
	 * now give me the grade for Jack...."  
	 * <p>
	 * That's really too much
	 * pain and agony for our purposes, so we'll just assign a random grade for each student
	 * as we create the Assignment objects.
	 * The assigned grades will be 70% or higher
	 * for each person.  
	 * </p>
	 * <p>
	 * Note that since assignments are
	 * identified by name, multiple attempts at an assignment (i.e., two assignments with the
	 * same name) are allowed.
	 * </p>
	 * @param courseToHandle - the Assignments will be added to this Course
	 * 
	 * @param assignmentName - name for the new Assignment objects
	 * 
	 * @param possiblePoints - maximum number of points for these Assignment objects
	 */
	public static void createAssignment(Course courseToHandle, String assignmentName, int possiblePoints) {
		Random rand = new Random();
		Map<Integer, List<Assignment>> roster = courseToHandle.getRoster();
		int scoreMin = (int) (possiblePoints * .7);
		int scoreRange = possiblePoints - scoreMin;
		for (int studentId: roster.keySet()) {
			int studentScore = rand.nextInt(scoreRange) + scoreMin;
			Assignment lab = new Assignment(assignmentName, possiblePoints, studentScore);
			courseToHandle.addAssignment(studentId, lab);
		}
		
	}

	/** 
	 * Present the user with a list of the current courses and ask which he or she 
	 * wishes to work on next.
	 * 
	 * @param input I/O reader to determine user choice
	 * 
	 * @param courses the courses the user may select
	 * 
	 * @return the user-selected course
	 */
	public static Course whichCourse(Scanner input, List<Course> courses) {
		System.out.print("Which Course? ");
		String courseSelection = Utils.userChoose(input, courses);
		// System.out.println(courseSelection);
		if (!"".equals(courseSelection)) {
			String[] divided = courseSelection.split("=|,");
			/*
			 * for (String s: divided) { System.out.println(s); }
			 */
			String courseDept = divided[1];
			String courseNumber = divided[3];

			// System.out.println("debug: courseDept: " + courseDept);
			// System.out.println("debug: courseNumber: " + courseNumber);

			for (Course c : courses) {
				if (c.getDepartment().equals(courseDept) && c.getCourseNumber() == Integer.parseInt(courseNumber)) {
					return c;
				}
			}
		}
		System.out.println("Invalid course chosen, using [0]: " + courses.get(0).getDepartment() + "-"
				+ courses.get(0).getCourseNumber());
		return courses.get(0);
	}

	/**
	 * Add up to the given number of students to a given course.
	 * <br>
	 * Note that since our Student generator can generate duplicate students,
	 * and since a Course will gracefully ignore adding duplicate students,
	 * less than the specified students may wind up being added to the course.
	 * 
	 * @param c - Course for the new Students
	 * 
	 * @param seats - The number of students to be randomly generated and added
	 * to the course.  See above why the course might actually wind up with
	 * fewer Students than this parameter specifies.
	 */
	public static void populateCourse(Course c, int seats) {
		for (int i = 0; i < seats; i++) {
			Student student = StudentGenerator.genStudent();
			StudentBody sb = StudentBody.getInstance();
			sb.add(student);
			c.addStudent(student.getId());
		}

	}

}
