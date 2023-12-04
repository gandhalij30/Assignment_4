import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import in.co.vwits.model.exception.StudentNotFoundException;
import in.co.vwits.sms.ApplicationConfiguration;
import in.co.vwits.sms.model.Student;
import in.co.vwits.sms.service.StudentService;


public class TestStudent {

	public static void main(String[] args) {

		ApplicationContext container;
		container = new AnnotationConfigApplicationContext(ApplicationConfiguration.class); // this line instenciate spring container
		EntityManagerFactory factory = container.getBean(EntityManagerFactory.class);
		System.out.println(factory);
		
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);

			//StudentService service = new StudentServiceImpl();
			StudentService service = container.getBean(StudentService.class);
			
			int option = 1;

			do {
				System.out.println("Welcome to Student Management..........");
				System.out.println("1. Find All Records ");
				System.out.println("2. Insert Records ");
				System.out.println("3. Find student by Roll No. ");
				System.out.println("4. Delete student by Roll No. ");
				System.out.println("5. Update Mark. ");
				System.out.println("6. Find All who scored more than Specific percentage. ");
				System.out.println("7. Find All who scored less than Specific percentage and less than Attemps. ");
				System.out.println("8. Find All Name Starts With Specific Character. ");
				System.out.println("9. Print the Student in Decensending order. ");
				System.out.println("10. Student Name list more than 90%. ");
				System.out.println("11. Print All Student in Decensending order. ");
				System.out.println("12. Print All Students Born  before specificdate. ");
				System.out.println("13. Students Count Born after specificdate. ");
				System.out.println("14. Print All Students Between Two dates. ");
				System.out.println("15. Partion Students Base on Marks. ");
				System.out.println("16. Count Unique Subjects. ");
				System.out.println("17. Find All with Subjects.");
				System.out.println("18. Find one with Subjects.");
				System.out.println("-1. To Exit");
				System.out.println("Enter your choice:");

				option = sc.nextInt();

				switch (option) {
				case 1:
					// show all students
					List<Student> students = service.findAll();
					for(Student s: students) {
						System.out.println(s.getRollno()+" "+s.getName()+" ");
					}
					System.out.println("After");
					//System.out.println(students);
					break;

				case 2:
					//
					Student s = new Student();
					System.out.println("Enter students Deatails");
					// Accept value from user
					//System.out.print("Roll No: ");
					//s.setRollno(sc.nextInt());
					System.out.print("Name: ");
					s.setName(sc.next());
					System.out.print("Percentage: ");
					s.setPercentage(sc.nextDouble());
					System.out.print("Attempts :");
					s.setNumberOfAttempts(sc.nextInt());
					System.out.print("DOB(dd-MM-YYYY) : ");
					LocalDate dob = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
					s.setDateOfBirth(dob);
					System.out.print("No of Sub :");
					int n = sc.nextInt();
					List<String> sub = new ArrayList<String>();
					for(int i=1; i <= n; i++) {
						System.out.print("Enter Subject"+i+": ");
						sub.add(sc.next());
					}
					s.setSubjectsLearning(sub);
					service.save(s);
					System.out.println("Student saved Successfully");
					break;

				case 3:
					System.out.println("Find the student record");
					System.out.println("Enter the rollno: ");
					Optional<Student> foundstudent = service.findByRollNo(sc.nextInt());
					System.out.println("Found student: " + foundstudent.get());
					break;

				case 4:
					System.out.println("Delete the student record");
					System.out.println("Enter the rollno: ");
					try {
						int rollno = sc.nextInt();
						service.deleteStudent(rollno);
					}
					catch(InputMismatchException e){
						System.out.println("Rollno must be an  Integer value");
						sc.nextLine(); // This statement is used to discard the input.
					}
					break;

				case 5:
					System.out.println("Update the record ");
					System.out.println("Enter the Id:  ");
					int rollno = sc.nextInt();
					Student ss = Optional.ofNullable(service.findByRollNo(rollno).get()).get();
					System.out.println("Enter the Mark:  ");
					double updateMark = sc.nextDouble();
					ss.setPercentage(updateMark);
					service.updateByRollNo(ss);
					System.out.println("Update successfully ");
					break;

				case 6:
					System.out.println("Enter Percentage :");
					System.out.println(service.findAllStudentsScoredMoreThan(sc.nextDouble()));
					break;

				case 7:
					System.out.println("Enter Percentage :");
					System.out.println("Max Attempts :");
					double percentage = sc.nextDouble();
					int attempts = sc.nextInt();
					System.out.println(service.findAllStudentsScoredLessThan(percentage, attempts));
					break;

				case 8:
					System.out.println("Enter Statring Letters :");
					System.out.println(service.findAllStudentsStartsWith(sc.next()));
					break;

				case 9:
					System.out.println(service.findAllStudentSortedByPercentage());
					break;

				case 10:
					System.out.println("Student Name list more than 90:");
					System.out.println(service.findStudentNameWhoScoredMoreThanGivenPercentage(90));
					break;

				case 11:
					System.out.println("Student Learning Specific subject");
					System.out.println(service.findAllStudentsLearningSpecificSubject("Java"));
					break;

				case 12:
					System.out.println("Enter Date(dd-MM-yyyy):");
					LocalDate specificDate = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
					List<Student> studentList = service.findAllStudentsBornBeforeSpecificDate(specificDate);
					System.out.println(studentList);
					break;

				case 13:
					System.out.println("Enter Date(dd-MM-yyyy):");
					LocalDate specificDate1 = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
					long count = service.findStudentsCountBornAfterSpecificDate(specificDate1);
					System.out.println(count);
					break;

				case 14:
					System.out.println("Enter Starting Date(dd-MM-yyyy):");
					String date1 = sc.next();
					System.out.println("Enter Endting Date(dd-MM-yyyy):");
					String date2 = sc.next();
					LocalDate startDate = LocalDate.parse(date1, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
					LocalDate endDate = LocalDate.parse(date2, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
					List<Student> studentList1 = service.findAllStudentsBornBetweenSpecificDates(startDate, endDate);
					System.out.println(studentList1);
					break;

				case 15:
					System.out.println("Enter the mark:");
					Map<Boolean, List<Student>> partioningStudentsBasedOnMarks = service.partioningStudentsBasedOnMarks(sc.nextDouble());
					System.out.println(partioningStudentsBasedOnMarks);
					break;
					
				case 16:
					System.out.print("Total Unique subjects: ");
					System.out.println(service.findUniqueSubjects());
					break;
					
				case 17:
					System.out.println("Find all with Subjects:");
					System.out.println(service.findAllWithSubjects());
					break;
					
				case 18:
					System.out.println("Find the student record");
					System.out.println("Enter the rollno: ");
					Optional<Student> stud = service.findOneWithSubjectsLearning(sc.nextInt());
					System.out.println("Found student: " + stud.get());
					break;

				case -1:
					System.out.println("Thankyou for visiting :)");
					break;

				default:
					System.out.println("Enter The Valid option :(");
					break;	
				}
			} while (option != -1);

		} catch (StudentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Student Not Found");
		}
		finally {
			sc.close();
		}

	}

}
