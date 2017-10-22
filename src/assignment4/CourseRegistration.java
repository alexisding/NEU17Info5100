package assignment4;

import java.util.ArrayList;

/**
 * Created by alexis on 10/4/17.
 */

// Question 4
class CourseRegistration { // score 2

	public static void main(String[] args) {

		Student s1 = new Student("Chloe", "001");
		Student s2 = new Student("Tracy", "002");
		Student s3 = new Student("Lillian", "003");
		Student s4 = new Student("Juliana", "004");
		Student s5 = new Student("Alexis", "005");
		Student s6 = new Student("Esther", "006");
		Student s7 = new Student("Anya", "007");
		Student s8 = new Student("Elisabeth", "008");
		Student s9 = new Student("Nicole", "009");
		Student s10 = new Student("Evelyn", "010");
		Student s11 = new Student("Franco", "011");

		Course java = new Course("Java");

		java.registerStudent(s1);
		java.registerStudent(s2);
		java.registerStudent(s3);
		java.registerStudent(s4);
		java.registerStudent(s5);
		java.registerStudent(s6);
		java.registerStudent(s7);
		java.registerStudent(s8);
		java.registerStudent(s9);
		java.registerStudent(s10);


		System.out.println("There are " + java.getNumberOfStudent() + " students in " + java.getTitle() + " course.");

		Student[] students = java.getStudents();
		for (Student student: students) {
			System.out.println(student.getName() + "'s id is " + student.getId());
		}

		java.registerStudent(s11);
	}
}

class Student {
	private String name;
	private String id;

	public Student(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

class Course {

	public static final int MAX = 10;
	private String title;
	private int numberOfStudent;
	private Student[] students;

	public Course(String title) {
		this.title = title;
		students = new Student[MAX];
	}

	public Student[] getStudents() {
		return students;
	}

	public boolean isFull(int numberOfStudent) {
		return numberOfStudent == MAX;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumberOfStudent() {
		return numberOfStudent;
	}

	public void setNumberOfStudent(int numberOfStudent) {
		this.numberOfStudent = numberOfStudent;
	}

	public void registerStudent(Student student) {

		if (isFull(numberOfStudent)) {
			System.out.println("Sorry! The class is full!");
			return;
		}

		for (int i = 0; i < MAX; i++) {
			if (students[i] == null) {
				students[i] = student;
				numberOfStudent++;
				return;
			}
		}
	}
}

