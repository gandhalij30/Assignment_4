package in.co.vwits.sms.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.vwits.model.exception.StudentNotFoundException;
import in.co.vwits.sms.dao.StudentDao;
import in.co.vwits.sms.model.Student;
import in.co.vwits.sms.service.StudentService;

@Service
@Transactional // this annotation instruct spring frameworks 
public class StudentServiceImpl implements StudentService {
	
	// using reference of interface to communicate with other layer of application is knows as coding to interface
	// coding  to interface is best practices  as it give loosely coupling among the layer
	@Autowired // reflection api in internal
	private StudentDao dao;
	
	// single line comment

	/*
	 * Multiple line comment
	 */

	/**
	 * DOCUMENTATION Comment find all the student details who score more than given
	 * percentage
	 * 
	 * @parameter percentage
	 * @return java.util.List<Student>
	 */
	@Override
	public List<Student> findAllStudentsScoredMoreThan(double percentage) {
		return dao.findAll().stream()
				.filter(student -> student.getPercentage() > percentage)
				.collect(Collectors.toList());
	}

	@Override
	public List<Student> findAllStudentsScoredLessThan(double percentage, int attempts) {
		return dao.findAll().stream().filter(student -> student.getNumberOfAttempts() < attempts)
				.filter(student -> student.getPercentage() < percentage)
				.collect(Collectors.toList());
	}

	@Override
	public long findTotalCountofStudentsStartsWith(String s) {
		return dao.findAll().stream()
				.filter(student -> student.getName().startsWith(s))
				.count();
	}

	@Override
	public List<Student> findAllStudentsStartsWith(String s) {
		return dao.findAll().stream()
				.filter(student -> student.getName().startsWith(s)).collect(Collectors.toList()); // toList is after jdk 16																													
	}

	@Override
	public List<Student> findAllStudentSortedByPercentage() {
		return dao.findAll().stream().sorted().collect(Collectors.toList()); // collector for before jdk 16
	}

	@Override
	public List<String> findStudentNameWhoScoredMoreThanGivenPercentage(double percentage) {
		return dao.findAll().stream().filter(student -> student.getPercentage() > percentage)
//				.map(student -> student.getName())
				.map(Student::getName).collect(Collectors.toList());
	}

	@Override
	public List<Student> findAllStudentsLearningSpecificSubject(String subject) {
		return dao.findAll().stream()
				.filter(student -> student.getSubjectsLearning().stream().anyMatch(sub -> sub.equals(subject)))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Student> findAllStudentsBornBeforeSpecificDate(LocalDate SpecificDate){
		return dao.findAll().stream()
				.filter(student -> student.getDateOfBirth().isBefore(SpecificDate))
				.collect(Collectors.toList());
	}
	
	@Override
	public long findStudentsCountBornAfterSpecificDate(LocalDate SpecificDate){
		return dao.findAll().stream()
				.filter(student -> student.getDateOfBirth().isAfter(SpecificDate))
				.count();
	}

	@Override
	public List<Student> findAllStudentsBornBetweenSpecificDates(LocalDate startDate, LocalDate endDate){
		return dao.findAll().stream()
				.filter(student -> student.getDateOfBirth().isBefore(endDate))
				.filter(student -> student.getDateOfBirth().isAfter(startDate))
				.collect(Collectors.toList());
	}
	
	@Override
	public Map<Boolean, List<Student>> partioningStudentsBasedOnMarks(double mark){
		return dao.findAll().stream()
				.collect(Collectors.partitioningBy(s -> s.getPercentage() > mark));
	}
	
	@Override
	public long findUniqueSubjects() {
		return dao.findAll().stream()
				.flatMap(s->s.getSubjectsLearning().stream())
				.distinct()
				.count();
	}
	
	// Find All
	@Override
	public List<Student> findAll() {
		return dao.findAll();
	}

	// Create
	@Override
	public void save(Student s) {
		dao.save(s);
	}

	// Find
	@Override
	public Optional<Student> findByRollNo(int rollno) throws StudentNotFoundException {
		return dao.findByRollNo(rollno);
	}

	// delete
	@Override
	public void deleteStudent(int rollno) {
		dao.deleteStudent(rollno);
	}


	@Override
	public void updateMarkByRollNo(int rollno, double updateMark) {
		dao.updateMark(rollno, updateMark);
	}

	@Override
	public void updateByRollNo(Student ss) {
		// TODO Auto-generated method stub
		dao.updateByRollno(ss);
	}

	@Override
	public List<Student> findAllWithSubjects() {
		// TODO Auto-generated method stub
		return dao.findAllWithSubjects();
	}

	@Override
	public Optional<Student> findOneWithSubjectsLearning(int rollno) {
		// TODO Auto-generated method stub
		return dao.findOneWithSubjectsLearning(rollno);
	}
	

}
