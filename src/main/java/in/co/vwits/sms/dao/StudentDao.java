package in.co.vwits.sms.dao;

import java.util.List;
import java.util.Optional;

import in.co.vwits.sms.model.Student;

public interface StudentDao {

	public int save(Student s);
	public void deleteStudent(int rollno);
	public Optional<Student> findByRollNo(int rollno);
	public List<Student> findAll();
	public void updateMark(int rollno, double mark);
	void updateByRollno(Student s);
	public List<Student> findAllWithSubjects();
	public Optional<Student> findOneWithSubjectsLearning(int rollno);
}
