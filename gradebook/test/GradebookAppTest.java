package src.edu.waketech.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

import src.edu.waketech.academic.Assignment;
import src.edu.waketech.academic.Course;
import src.edu.waketech.common.Student;
import src.edu.waketech.common.StudentBody;
import src.edu.waketech.GradebookApp;

class GradebookAppTest {


	@Test
	void testCreateAssignment() {
		Course course = new Course("CSC", 251);
		Student s1 = new Student("last1", "first1", 2);
		Student s2 = new Student("last2", "first2", 3);
		StudentBody sb = StudentBody.getInstance();
		sb.add(s1);
		sb.add(s2);
		
		course.addStudent(s1.getId());
		course.addStudent(s2.getId());
		
		GradebookApp.createAssignment(course, "lab1", 50);
		List<Assignment> labList = course.getAssignment(s1.getId(), "lab1");
		Assignment foundLab = labList.get(0);
		assertEquals("lab1", foundLab.getName());
		assertEquals(50, foundLab.getPossiblePoints());
		assertTrue(foundLab.getScore() >= 35);
	}
	
	@Test
	void testCreateAssignment1() {
		Course course = new Course("CSC", 251);
		Student s1 = new Student("last1", "first1", 2);
		Student s2 = new Student("last2", "first2", 3);
		StudentBody sb = StudentBody.getInstance();
		sb.add(s1);
		sb.add(s2);
		
		course.addStudent(s1.getId());
		course.addStudent(s2.getId());
		
		GradebookApp.createAssignment(course, "lab1", 50);
		List<Assignment> labList = course.getAssignment(s2.getId(), "lab1");
		Assignment foundLab = labList.get(0);
		assertEquals("lab1", foundLab.getName());
		assertEquals(50, foundLab.getPossiblePoints());
		assertTrue(foundLab.getScore() >= 35);
	}


}
