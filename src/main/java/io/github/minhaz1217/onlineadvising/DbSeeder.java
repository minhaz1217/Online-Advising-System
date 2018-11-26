/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising;
import io.github.minhaz1217.onlineadvising.Interface.CourseRepository;
import io.github.minhaz1217.onlineadvising.Interface.StudentRepository;
import io.github.minhaz1217.onlineadvising.Interface.UserRepository;
import io.github.minhaz1217.onlineadvising.models.Course;
import java.util.ArrayList;
import java.util.Arrays;

import io.github.minhaz1217.onlineadvising.models.Student;
import io.github.minhaz1217.onlineadvising.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
/**
 *
 * @author Minhaz
 */


@Component
public class DbSeeder implements CommandLineRunner {
    
    protected CourseRepository courseRepository;
    private UserRepository userRepository;
    private StudentRepository studentRepository;
    public DbSeeder(CourseRepository courseRepository,
                    UserRepository userRepository,
                    StudentRepository studentRepository){
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }
    
    
    @Override
    public void run(String... args) throws Exception {

        // clearing all the tables
        this.courseRepository.deleteAll();
        this.userRepository.deleteAll();
        this.studentRepository.deleteAll();
        System.out.println("DB Details: " + " Users: "+ userRepository.count() + " Courses: "+ courseRepository.count() + " Students: " + studentRepository.count());


        //adding some user to use
        User minhaz = new User("minhaz2", "minhaz2", "ROLE_ADMIN", "ROLE_USER");
        User admin = new User("admin", "admin", "ROLE_ADMIN", "ROLE_USER");
        this.userRepository.save(minhaz);
        this.userRepository.save(admin);



        // adding all the courses
        this.courseRepository.save( new Course("Basic English","ENG101","Department of English",0,new ArrayList<String>()));
        this.courseRepository.save( new Course("Differential And Integral Calculus","MAT101","Mathematical and Physical Sciences",0,new ArrayList<String>()));
        this.courseRepository.save( new Course("Structured Programming","CSE105","Department of Computer Seience and Engineering",1,new ArrayList<String>()));
        this.courseRepository.save( new Course("Composition and Communication Skills","ENG102","Department of English",0,new ArrayList<String>(Arrays.asList("ENG101"))));
        this.courseRepository.save( new Course("Differential Equations and Special Functions","MAT102","Mathematical and Physical Sciences",0,new ArrayList<String>(Arrays.asList("MAT101"))));
        this.courseRepository.save( new Course("Object Oriented Programming","CSE107","Department of Computer Seience and Engineering",1,new ArrayList<String>(Arrays.asList("CSE105"))));
        this.courseRepository.save( new Course("Engineering Physics-I (Introductory Classical Physics)","PHY109","Mathematical and Physical Sciences",1,new ArrayList<String>(Arrays.asList("MAT102"))));
        this.courseRepository.save( new Course("Coordinate Geometry and Vector Analysis","MAT104","Mathematical and Physical Sciences",0,new ArrayList<String>()));
        this.courseRepository.save( new Course("Engineering Chemistry","CHE109","Department of Pharmacy",1,new ArrayList<String>()));
        this.courseRepository.save( new Course("Electrical Circuits","CSE109","Department of Computer Seience and Engineering",1,new ArrayList<String>()));
        this.courseRepository.save( new Course("Bangladesh Studies","GEN201","Department of Sociology",0,new ArrayList<String>()));
        this.courseRepository.save( new Course("Statics and Probability","STA102","Department of Applied Statistics",0,new ArrayList<String>()));
        this.courseRepository.save( new Course("Discrete Mathematics","CSE205","Department of Computer Seience and Engineering",0,new ArrayList<String>(Arrays.asList("CSE107"))));
        this.courseRepository.save( new Course("Linear Algebra and Complex Veriable","MAT205","Mathematical and Physical Sciences",0,new ArrayList<String>(Arrays.asList("MAT102"))));
        this.courseRepository.save( new Course("Data Structures","CSE207","Department of Computer Seience and Engineering",1,new ArrayList<String>(Arrays.asList("CSE205"))));
        this.courseRepository.save( new Course("Numerical Methods","CSE225","Department of Computer Seience and Engineering",1,new ArrayList<String>(Arrays.asList("CSE105","MAT102"))));
        this.courseRepository.save( new Course("Engineering Physics-II (Introductory Quantum Physics)","PHY209","Mathematical and Physical Sciences",0,new ArrayList<String>(Arrays.asList("MAT205"))));
        this.courseRepository.save( new Course("Algorithms","CSE245","Department of Computer Seience and Engineering",1,new ArrayList<String>(Arrays.asList("CSE207"))));
        this.courseRepository.save( new Course("Signals and Systems","CSE248","Department of Computer Seience and Engineering",0,new ArrayList<String>(Arrays.asList("CSE109","MAT205"))));
        this.courseRepository.save( new Course("Electronic Circuits","CSE251","Department of Computer Seience and Engineering",1,new ArrayList<String>(Arrays.asList("CSE109"))));
        this.courseRepository.save( new Course("Database Systems","CSE301","Department of Computer Seience and Engineering",1,new ArrayList<String>(Arrays.asList("CSE205"))));
        this.courseRepository.save( new Course("Operating Systems","CSE325","Department of Computer Seience and Engineering",1,new ArrayList<String>()));
        this.courseRepository.save( new Course("Digital Logic Design","CSE345","Department of Computer Seience and Engineering",1,new ArrayList<String>(Arrays.asList("CSE205","CSE251"))));
        this.courseRepository.save( new Course("Data Communications","CSE350","Department of Computer Seience and Engineering",0,new ArrayList<String>(Arrays.asList("CSE251","CSE248"))));
        this.courseRepository.save( new Course("Computer Architecture","CSE360","Department of Computer Seience and Engineering",0,new ArrayList<String>(Arrays.asList("CSE325","CSE345"))));
        this.courseRepository.save( new Course("Artificial Intelligence","CSE365","Department of Computer Seience and Engineering",1,new ArrayList<String>(Arrays.asList("CSE245"))));
        this.courseRepository.save( new Course("Compiler Design","CSE375","Department of Computer Seience and Engineering",0,new ArrayList<String>()));
        this.courseRepository.save( new Course("Computer Networks","CSE405","Department of Computer Seience and Engineering",1,new ArrayList<String>(Arrays.asList("CSE245","CSE350"))));
        this.courseRepository.save( new Course("Social and Professional Issues in Computing","CSE498","Department of Computer Seience and Engineering",0,new ArrayList<String>()));
        this.courseRepository.save( new Course("Software Engineering and Information System Design","CSE411","Department of Computer Seience and Engineering",1,new ArrayList<String>(Arrays.asList("CSE301"))));
        this.courseRepository.save( new Course("Microprocessors and Microcontrollers","CSE442","Department of Computer Seience and Engineering",1,new ArrayList<String>(Arrays.asList("CSE360"))));




        //adding some demo students
        this.studentRepository.save(new Student( "Alex", "Jone", "minhaz1217@gmail.com", "2016-1-60-100", "ENG101", "MAT101", "CSE105", "ENG102", "MAT102", "CSE107", "PHY109", "MAT104", "CHE109", "CSE109", "GEN201", "STA102", "CSE205", "MAT205", "CSE207", "CSE225", "PHY209", "CSE245", "CSE248", "CSE251", "CSE301", "CSE325", "CSE345" ));
        this.studentRepository.save(new Student( "Mikasa", "Es Sukasa", "minhaz1217@gmail.com", "2016-1-60-101", "ENG101", "MAT101", "CSE105", "ENG102", "MAT102", "CSE107", "PHY109", "MAT104", "CHE109", "CSE109" ));
        this.studentRepository.save(new Student( "Hakua", "Matata", "minhaz1217@gmail.com", "2016-1-60-102", "" ));

        //showing some messages to verify that everything went ok
        System.out.println("DB Details: " + " Users: "+ userRepository.count() + " Courses: "+ courseRepository.count() + " Students: " + studentRepository.count());
        System.out.println(courseRepository.count());
        
        System.out.println("DB LOADED SUCCESSFULLY");
    }
    
}
