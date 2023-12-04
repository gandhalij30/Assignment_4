package in.co.vwits.sms.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import in.co.vwits.sms.dao.StudentDao;
import in.co.vwits.sms.model.Student;

//To help our application to(Read, Insert, Update, Delete) talk to underline data store(in memory/databases).
public class StudentDaoImpl implements StudentDao {

	private List<Student> students;

	public StudentDaoImpl() {
		students = new ArrayList<Student>();
		Student s1 = new Student();
		s1.setRollno(1);
		s1.setName("Amit");
		s1.setPercentage(81);
		s1.setNumberOfAttempts(5);
		List<String> subjects1 = new ArrayList<>();
		subjects1.add("Python");
		subjects1.add("Java");
		s1.setSubjectsLearning(subjects1);
		s1.setDateOfBirth(LocalDate.of(2010, 10, 2));

		Student s2 = new Student();
		s2.setRollno(2);
		s2.setName("Sumit");
		s2.setPercentage(75);
		s2.setNumberOfAttempts(2);
		List<String> subjects2 = new ArrayList<>();
		subjects2.add("Spring");
		subjects2.add("Machine Learning");
		s2.setSubjectsLearning(subjects2);
		s2.setDateOfBirth(LocalDate.of(2011, 10, 2));

		Student s3 = new Student();
		s3.setRollno(3);
		s3.setName("Ragul");
		s3.setPercentage(85);
		s3.setNumberOfAttempts(3);
		s3.setSubjectsLearning(subjects1);
		s3.setDateOfBirth(LocalDate.of(2013, 10, 2));

		students.add(s1);
		students.add(s2);
		students.add(s3);
	}

	// Read All
	public List<Student> findAll() {
		return students;
	}

	// Create
	public int save(Student s) {
		students.add(s);
		return 1;
	}

	// Find
	public Optional<Student> findByRollNo(int rollno) {
		for (Student s : students) {
			if (s.getRollno() == rollno) {
				return Optional.of(s);
			}
		}
		return Optional.empty();
	}

	// delete
	public void deleteStudent(int rollno) {
		Iterator<Student> i = students.iterator();
		while (i.hasNext()) {
			Student s = i.next();
			if (s.getRollno() == rollno) {
				i.remove();
			}
		}
	}

	public void updateByRollNo(int rollno, double updateMark) {
		for (Student s : students) {
			if (s.getRollno() == rollno) {
				s.setPercentage(updateMark);
			}
		}
	}

	@Override
	public void updateMark(int rollno, double mark) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateByRollno(Student s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Student> findAllWithSubjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Student> findOneWithSubjectsLearning(int rollno) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}
