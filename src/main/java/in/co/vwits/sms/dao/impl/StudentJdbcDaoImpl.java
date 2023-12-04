package in.co.vwits.sms.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import in.co.vwits.sms.dao.StudentDao;
import in.co.vwits.sms.model.Student;

public class StudentJdbcDaoImpl implements StudentDao{

	// Create
	public int save(Student s) {
		try	(
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db","root","root");
				PreparedStatement pstmt = con.prepareStatement("INSERT INTO tbl_student VALUES(?,?,?,?,?) "); // 4 ? map to columns in the table we want to insert
				)
		{
			// Before actually firing the QUERY you must set the values for all ?
			pstmt.setInt(1, s.getRollno());
			pstmt.setString(2, s.getName());
			pstmt.setDouble(3, s.getPercentage());
			pstmt.setInt(4, s.getNumberOfAttempts());
			pstmt.setDate(5, Date.valueOf(s.getDateOfBirth()));

			int noOfRowUpdated = pstmt.executeUpdate(); // firing QUERY
			return noOfRowUpdated;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// delete
	public void deleteStudent(int rollno) {

		try	(
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db","root","root");
				PreparedStatement pstmt = con.prepareStatement("DELETE FROM tbl_student WHERE rollno=? "); // ? map to columns in the table we want to insert
				)
		{

			// Before actually firing the QUERY you must set the values for all ?
			pstmt.setInt(1, rollno);

			int noOfRowUpdated = pstmt.executeUpdate(); // firing QUERY

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Find
	public Optional<Student> findByRollNo(int rollno) {

		try	(
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db","root","root");
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM tbl_student WHERE rollno=? "); // ? map to columns in the table we want to insert
				)
		{

			// Before actually firing the QUERY you must set the values for all ?
			pstmt.setInt(1, rollno);


			ResultSet rs = pstmt.executeQuery(); // firing QUERY.... // result set query on specific condition
			Student foundStudent = new Student();
			if(rs.next()) { // next() method returns
				foundStudent.setRollno(rs.getInt(1));
				foundStudent.setName(rs.getString(2));
				foundStudent.setPercentage(rs.getDouble(3));
				foundStudent.setNumberOfAttempts(rs.getInt(4));
			}
			return Optional.of(foundStudent);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	// Find All
	public List<Student> findAll() {
		List<Student> students = new ArrayList<>();
		try	(
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db","root","root");
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM tbl_student"); // ? map to columns in the table we want to insert
			)
		{

			ResultSet rs = pstmt.executeQuery(); // firing QUERY.... // result set query on specific condition
			
			while(rs.next()) { // next() method returns
				Student foundStudent = new Student();
				foundStudent.setRollno(rs.getInt(1));
				foundStudent.setName(rs.getString(2));
				foundStudent.setPercentage(rs.getDouble(3));
				foundStudent.setNumberOfAttempts(rs.getInt(4));
				students.add(foundStudent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}
	
	//update
	public void updateMark(int rollno, double updateMark) {
		try	(
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db","root","root");
				PreparedStatement pstmt = con.prepareStatement("UPDATE tbl_student SET percentage=? WHERE rollno=? "); // ? map to columns in the table we want to insert
			)
		{
			
			// Before actually firing the QUERY you must set the values for all ?
			pstmt.setDouble(1, updateMark); // 1st ? is for attempts
			pstmt.setInt(2, rollno); // 2nd ? is for rollno
			
			
			int noOfRowUpdated = pstmt.executeUpdate(); // firing QUERY
			System.out.println("No of Records Updated are "+ noOfRowUpdated);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
