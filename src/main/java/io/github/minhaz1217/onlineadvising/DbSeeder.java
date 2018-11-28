/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising;
import io.github.minhaz1217.onlineadvising.Interface.CourseDescriptionRepository;
import io.github.minhaz1217.onlineadvising.Interface.CourseRepository;
import io.github.minhaz1217.onlineadvising.Interface.StudentRepository;
import io.github.minhaz1217.onlineadvising.Interface.UserRepository;
import io.github.minhaz1217.onlineadvising.models.*;

import java.util.ArrayList;
import java.util.Arrays;

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
    private CourseDescriptionRepository courseDescriptionRepository;
    public DbSeeder(CourseRepository courseRepository,
                    UserRepository userRepository,
                    StudentRepository studentRepository,
                    CourseDescriptionRepository courseDescriptionRepository){
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.courseDescriptionRepository = courseDescriptionRepository;
    }
    
    
    @Override
    public void run(String... args) throws Exception {

        // clearing all the tables
        this.courseRepository.deleteAll();
        this.userRepository.deleteAll();
        this.studentRepository.deleteAll();
        this.courseDescriptionRepository.deleteAll();
        System.out.println("DB Details: " + " Users: "+ userRepository.count() + " Courses: "+ courseRepository.count() + " Course Description: " + courseDescriptionRepository.count() + " Students: " + studentRepository.count());


        //adding some user to use
        User minhaz = new User("minhaz2", "minhaz2", "ROLE_ADMIN", "ROLE_USER");
        User admin = new User("admin", "admin", "ROLE_ADMIN", "ROLE_USER");
        this.userRepository.save(minhaz);
        this.userRepository.save(admin);



        // adding all the courses
        this.courseRepository.save( new Course("Basic English","ENG101","Department of English","0",new ArrayList<String>()));
        this.courseRepository.save( new Course("Differential And Integral Calculus","MAT101","Mathematical and Physical Sciences","0",new ArrayList<String>()));
        this.courseRepository.save( new Course("Structured Programming","CSE105","Department of Computer Seience and Engineering","1",new ArrayList<String>()));
        this.courseRepository.save( new Course("Composition and Communication Skills","ENG102","Department of English","0",new ArrayList<String>(Arrays.asList("ENG101"))));
        this.courseRepository.save( new Course("Differential Equations and Special Functions","MAT102","Mathematical and Physical Sciences","0",new ArrayList<String>(Arrays.asList("MAT101"))));
        this.courseRepository.save( new Course("Object Oriented Programming","CSE107","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE105"))));
        this.courseRepository.save( new Course("Engineering Physics-I (Introductory Classical Physics)","PHY109","Mathematical and Physical Sciences","1",new ArrayList<String>(Arrays.asList("MAT102"))));
        this.courseRepository.save( new Course("Coordinate Geometry and Vector Analysis","MAT104","Mathematical and Physical Sciences","0",new ArrayList<String>()));
        this.courseRepository.save( new Course("Engineering Chemistry","CHE109","Department of Pharmacy","1",new ArrayList<String>()));
        this.courseRepository.save( new Course("Electrical Circuits","CSE109","Department of Computer Seience and Engineering","1",new ArrayList<String>()));
        this.courseRepository.save( new Course("Bangladesh Studies","GEN201","Department of Sociology","0",new ArrayList<String>()));
        this.courseRepository.save( new Course("Statics and Probability","STA102","Department of Applied Statistics","0",new ArrayList<String>()));
        this.courseRepository.save( new Course("Discrete Mathematics","CSE205","Department of Computer Seience and Engineering","0",new ArrayList<String>(Arrays.asList("CSE107"))));
        this.courseRepository.save( new Course("Linear Algebra and Complex Veriable","MAT205","Mathematical and Physical Sciences","0",new ArrayList<String>(Arrays.asList("MAT102"))));
        this.courseRepository.save( new Course("Data Structures","CSE207","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE205"))));
        this.courseRepository.save( new Course("Numerical Methods","CSE225","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE105","MAT102"))));
        this.courseRepository.save( new Course("Engineering Physics-II (Introductory Quantum Physics)","PHY209","Mathematical and Physical Sciences","0",new ArrayList<String>(Arrays.asList("MAT205"))));
        this.courseRepository.save( new Course("Algorithms","CSE245","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE207"))));
        this.courseRepository.save( new Course("Signals and Systems","CSE248","Department of Computer Seience and Engineering","0",new ArrayList<String>(Arrays.asList("CSE109","MAT205"))));
        this.courseRepository.save( new Course("Electronic Circuits","CSE251","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE109"))));
        this.courseRepository.save( new Course("Database Systems","CSE301","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE205"))));
        this.courseRepository.save( new Course("Operating Systems","CSE325","Department of Computer Seience and Engineering","1",new ArrayList<String>()));
        this.courseRepository.save( new Course("Digital Logic Design","CSE345","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE205","CSE251"))));
        this.courseRepository.save( new Course("Data Communications","CSE350","Department of Computer Seience and Engineering","0",new ArrayList<String>(Arrays.asList("CSE251","CSE248"))));
        this.courseRepository.save( new Course("Computer Architecture","CSE360","Department of Computer Seience and Engineering","0",new ArrayList<String>(Arrays.asList("CSE325","CSE345"))));
        this.courseRepository.save( new Course("Artificial Intelligence","CSE365","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE245"))));
        this.courseRepository.save( new Course("Compiler Design","CSE375","Department of Computer Seience and Engineering","0",new ArrayList<String>()));
        this.courseRepository.save( new Course("Computer Networks","CSE405","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE245","CSE350"))));
        this.courseRepository.save( new Course("Social and Professional Issues in Computing","CSE498","Department of Computer Seience and Engineering","0",new ArrayList<String>()));
        this.courseRepository.save( new Course("Software Engineering and Information System Design","CSE411","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE301"))));
        this.courseRepository.save( new Course("Microprocessors and Microcontrollers","CSE442","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE360"))));

        //adding some course description
        this.courseDescriptionRepository.save(new CourseDescription( "CSE350","1","08:30 - 10:00","ST","AB2-405","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE350","2","11:50 - 01:20","T","226","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE350","2","11:50 - 01:20","R","225","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE350","3","11:50 - 01:20","MW","AB2-205","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE360","1","08:30 - 10:00","SR","112","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE360","2","10:10 - 11:40","SR","112","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE360","3","08:30 - 10:00","MW","435","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE365","1","10:10 - 11:40","ST","AB1-601","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE365","2","01:30 - 03:00","T","436","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE365","2","01:30 - 03:00","S","AB2-503","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE365","3","10:10 - 11:40","MW","109","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE375","1","11:50 - 01:20","ST","115","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE375","2","03:10 - 04:40","SR","Reading Course","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE375","3","01:30 - 03:00","TR","109","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE498","1","08:30 - 10:00","MW","221","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE498","2","03:10 - 04:40","SR","337","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE411","1","10:10 - 11:40","T","AB2-304","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE411","1","10:10 - 11:40","R","AB2-304","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE411","2","10:10 - 11:40","MW","AB1-301","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE411","3","01:30 - 03:00","TR","AB2-205","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "GEN201","1","01:30 - 03:00","MW","219","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "GEN201","2","01:30 - 03:00","SR","550 (Lecture Gallery)","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "STA102","1","01:30 - 03:00","MW","AB1-302","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "STA102","2","03:10 - 04:40","TR","AB1-302","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "STA102","3","01:30 - 03:00","ST","AB1-402","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "STA102","4","11:50 - 01:20","MW","AB1-402","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "STA102","5","11:50 - 01:20","MW","AB1-302","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE205","1","10:10 - 11:40","ST","532","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE205","2","01:30 - 03:00","ST","532","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE205","3","10:10 - 11:40","MW","532","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE205","4","01:30 - 03:00","MW","532","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT205","1","01:30 - 03:00","ST","437","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT205","2","11:50 - 01:20","MW","437","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT205","3","10:10 - 11:40","ST","AB2-503","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT205","4","08:30 - 10:00","MW","436","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT205","5","08:30 - 10:00","ST","AB1-502","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT205","6","01:30 - 03:00","MW","AB1-502","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE225","1","10:10 - 11:40","MW","212","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE225","2","11:50 - 01:20","MW","212","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE225","3","10:10 - 11:40","T","AB2-405","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE225","3","10:10 - 11:40","R","AB2-404","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE225","4","01:30 - 03:00","T","212","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE225","4","01:30 - 03:00","R","107","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE225","5","08:30 - 10:00","MW","AB1-301","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE251","1","08:30 - 10:00","TR","AB1-301","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE251","2","01:30 - 03:00","TR","AB1-501","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE251","3","01:30 - 03:00","MW","TBA","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE325","1","08:30 - 10:00","MW","108","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE325","2","11:50 - 01:20","T","AB2-203","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE325","2","11:50 - 01:20","R","114","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE325","3","08:30 - 10:00","SR","AB1-701","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE325","4","03:10 - 04:40","MW","AB1-501","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","1","08:30 - 10:00","MW","223","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","10","01:30 - 03:00","MW","AB2-503","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","11","10:10 - 11:40","T","336","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","11","10:10 - 11:40","R","219","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","12","01:30 - 03:00","S","AB1-602","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","12","01:30 - 03:00","T","115","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","13","08:30 - 10:00","MW","115","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","14","03:10 - 04:40","ST","225","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","15","01:30 - 03:00","MW","AB1-602","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","16","08:30 - 10:00","ST","113","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","17","10:10 - 11:40","S","AB1-602","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","17","10:10 - 11:40","R","532","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","18","03:10 - 04:40","MW","227","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","19","10:10 - 11:40","S","AB2-203","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","19","10:10 - 11:40","R","AB1-401","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","2","08:30 - 10:00","ST","241","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","20","11:50 - 01:20","R","336","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","20","11:50 - 01:20","T","AB2-204","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","21","03:10 - 04:40","S","241","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","21","03:10 - 04:40","R","225","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","22","03:10 - 04:40","MW","AB2-204","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","23","08:30 - 10:00","MW","AB1-201","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","3","03:10 - 04:40","MW","221","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","4","08:30 - 10:00","MW","111","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","5","11:50 - 01:20","MW","AB2-303","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","6","11:50 - 01:20","MW","222","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","7","08:30 - 10:00","R","223","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","7","08:30 - 10:00","T","226","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","8","08:30 - 10:00","TR","AB2-404","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","9","01:30 - 03:00","S","223","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "ENG101","9","01:30 - 03:00","T","221","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT101","1","01:30 - 03:00","MW","AB1-202","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT101","10","11:50 - 01:20","MW","AB1-502","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT101","12","11:50 - 01:20","S","TBA","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT101","13","08:30 - 10:00","S","TBA","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT101","2","11:50 - 01:20","ST","AB1-302","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT101","3","01:30 - 03:00","TR","AB1-302","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT101","4","11:50 - 01:20","ST","AB1-602","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT101","5","03:10 - 04:40","SR","AB1-602","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT101","6","11:50 - 01:20","MW","AB1-301","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT101","7","11:50 - 01:20","TR","AB1-402","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT101","8","11:50 - 01:20","S","AB1-402","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT101","8","11:50 - 01:20","R","AB1-302","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT101","9","10:10 - 11:40","ST","AB1-502","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE105","1","08:30 - 10:00","ST","110","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE105","2","11:50 - 01:20","MW","108","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE105","3","11:50 - 01:20","ST","110","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE105","4","08:30 - 10:00","MW","630 (Software Engineering Lab)","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT104","1","11:50 - 01:20","MW","358","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT104","10","11:50 - 01:20","SR","AB2-204","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT104","12","03:10 - 04:40","T","TBA","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT104","2","01:30 - 03:00","SR","359","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT104","3","11:50 - 01:20","ST","433","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT104","4","01:30 - 03:00","T","432","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT104","4","01:30 - 03:00","R","219","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT104","5","01:30 - 03:00","R","437","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT104","5","01:30 - 03:00","S","AB1-302","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT104","6","11:50 - 01:20","MW","AB1-501","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT104","7","11:50 - 01:20","ST","531","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT104","8","03:10 - 04:40","MW","532","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "MAT104","9","01:30 - 03:00","TR","AB2-404","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CHE109","1","08:30 - 10:00","SR","109","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CHE109","2","11:50 - 01:20","TR","AB1-301","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CHE109","3","03:10 - 04:40","MW","107","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CHE109","4","08:30 - 10:00","T","102","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CHE109","4","08:30 - 10:00","R","108","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CHE109","5","10:10 - 11:40","TR","102","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CHE109","6","11:50 - 01:20","SR","113","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CHE109","7","10:10 - 11:40","SR","212","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE109","1","08:30 - 10:00","ST","221","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE109","2","01:30 - 03:00","MW","AB1-201","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE109","3","11:50 - 01:20","MW","AB2-302","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE109","4","11:50 - 01:20","S","AB2-203","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE109","4","11:50 - 01:20","R","AB1-401","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE109","5","10:10 - 11:40","MW","358","40"));
        this.courseDescriptionRepository.save(new CourseDescription( "CSE109","7","03:10 - 04:40","SR","AB2-203","40"));




        //adding some demo students
        this.studentRepository.save(new Student( "Alex", "Jone", "minhaz1217@gmail.com", "2016-1-60-100", 
                new CourseExtended("ENG101"), 
                new CourseExtended("MAT101"), 
                new CourseExtended("CSE105"), 
                new CourseExtended("ENG102"), 
                new CourseExtended("MAT102"), 
                new CourseExtended("CSE107"), 
                new CourseExtended("PHY109"), 
                new CourseExtended("MAT104"), 
                new CourseExtended("CHE109"), 
                new CourseExtended("CSE109"), 
                new CourseExtended("GEN201"), 
                new CourseExtended("STA102"), 
                new CourseExtended("CSE205"), 
                new CourseExtended("MAT205"), 
                new CourseExtended("CSE207"), 
                new CourseExtended("CSE225"), 
                new CourseExtended("PHY209"), 
                new CourseExtended("CSE245"), 
                new CourseExtended("CSE248"), 
                new CourseExtended("CSE251"), 
                new CourseExtended("CSE301"), 
                new CourseExtended("CSE325"), 
                new CourseExtended("CSE345" )));
        this.studentRepository.save(new Student( "Mikasa","Es Sukasa", "minhaz1217@gmail.com", "2016-1-60-101", 
                new CourseExtended("ENG101"), 
                new CourseExtended("MAT101"), 
                new CourseExtended("CSE105"), 
                new CourseExtended("ENG102"), 
                new CourseExtended("MAT102"), 
                new CourseExtended("CSE107"), 
                new CourseExtended("PHY109"), 
                new CourseExtended("MAT104"), 
                new CourseExtended("CHE109"), 
                new CourseExtended("CSE109" )
        ));
        this.studentRepository.save(new Student( "Hakua", "Matata", "minhaz1217@gmail.com", "2016-1-60-102", new CourseExtended("")));
        this.studentRepository.save(new Student( "Minhaz", "Khan", "minhaz1217@gmail.com", "2016-1-60-103",
                new CourseExtended( "MAT101", new CourseDescription( "MAT101","2","11:50 - 01:20","ST","AB1-302","40")),

                new CourseExtended(new CourseDescription( "ENG101","2","08:30 - 10:00","ST","241","40")),
                new CourseExtended(  new CourseDescription( "CSE411","1","10:10 - 11:40","T","AB2-304","40"))
        ));




        //this.courseDescriptionRepository.save(new CourseDescription( "CSE405","1","08:30 - 10:00","MW", "112","40"));



        //showing some messages to verify that everything went ok
        System.out.println("DB Details: " + " Users: "+ userRepository.count() + " Courses: "+ courseRepository.count() + " Course Description: " + courseDescriptionRepository.count() + " Students: " + studentRepository.count());
        System.out.println(courseRepository.count());
        
        System.out.println("DB LOADED SUCCESSFULLY");
    }
    
}
