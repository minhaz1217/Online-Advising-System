/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising;
import io.github.minhaz1217.onlineadvising.Interface.*;
import io.github.minhaz1217.onlineadvising.models.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
/**
 *
 * @author Minhaz
 */


@Component
public class DbSeeder implements CommandLineRunner {
    
    protected static CourseRepository courseRepository;
    private static UserRepository userRepository;
    private static StudentRepository studentRepository;
    private static CourseDescriptionRepository courseDescriptionRepository;
    private static RoleRepository roleRepository;
    public DbSeeder(CourseRepository courseRepository,
                    UserRepository userRepository,
                    StudentRepository studentRepository,
                    CourseDescriptionRepository courseDescriptionRepository,
                    RoleRepository roleRepository){
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.courseDescriptionRepository = courseDescriptionRepository;
        this.roleRepository = roleRepository;
    }

    public static String deleteAll(){
        courseRepository.deleteAll();
        userRepository.deleteAll();
        studentRepository.deleteAll();
        courseDescriptionRepository.deleteAll();
        roleRepository.deleteAll();
        System.out.println("DB Details: " + " Users: "+ userRepository.count() + " Courses: "+ courseRepository.count() + " Course Description: " + courseDescriptionRepository.count() + " Students: " + studentRepository.count());
        return ("Details: " + " Users: "+ userRepository.count() + " Courses: "+ courseRepository.count() + " Course Description: " + courseDescriptionRepository.count() + " Students: " + studentRepository.count());

    }
    public static String loadAll(){
        // clearing all the tables
        System.out.println("DB Details: " + " Users: "+ userRepository.count() + " Courses: "+ courseRepository.count() + " Course Description: " + courseDescriptionRepository.count() + " Students: " + studentRepository.count());


        //adding some user to use
        Role userRole = roleRepository.findRoleByRole("USER");
        Role adminRole = roleRepository.findRoleByRole("ADMIN");

        User user = new User("user", "user", new HashSet<>(Arrays.asList( userRole )) );

        User admin = new User("admin", "admin", new HashSet<>(Arrays.asList(adminRole)));
        //User minhaz1 = new User("minhaz123123", "minhaz123123", new HashSet<>(Arrays.asList( adminRole )));
        userRepository.save(admin);
        //userRepository.save(minhaz1);
        userRepository.save(user);


        // adding all the courses
        courseRepository.save( new Course("Basic English","ENG101","Department of English","0",new ArrayList<String>()));
        courseRepository.save( new Course("Differential And Integral Calculus","MAT101","Mathematical and Physical Sciences","0",new ArrayList<String>()));
        courseRepository.save( new Course("Structured Programming","CSE105","Department of Computer Seience and Engineering","1",new ArrayList<String>()));
        courseRepository.save( new Course("Composition and Communication Skills","ENG102","Department of English","0",new ArrayList<String>(Arrays.asList("ENG101"))));
        courseRepository.save( new Course("Differential Equations and Special Functions","MAT102","Mathematical and Physical Sciences","0",new ArrayList<String>(Arrays.asList("MAT101"))));
        courseRepository.save( new Course("Object Oriented Programming","CSE107","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE105"))));
        courseRepository.save( new Course("Engineering Physics-I (Introductory Classical Physics)","PHY109","Mathematical and Physical Sciences","1",new ArrayList<String>(Arrays.asList("MAT102"))));
        courseRepository.save( new Course("Coordinate Geometry and Vector Analysis","MAT104","Mathematical and Physical Sciences","0",new ArrayList<String>()));
        courseRepository.save( new Course("Engineering Chemistry","CHE109","Department of Pharmacy","1",new ArrayList<String>()));
        courseRepository.save( new Course("Electrical Circuits","CSE109","Department of Computer Seience and Engineering","1",new ArrayList<String>()));
        courseRepository.save( new Course("Bangladesh Studies","GEN201","Department of Sociology","0",new ArrayList<String>()));
        courseRepository.save( new Course("Statics and Probability","STA102","Department of Applied Statistics","0",new ArrayList<String>()));
        courseRepository.save( new Course("Discrete Mathematics","CSE205","Department of Computer Seience and Engineering","0",new ArrayList<String>(Arrays.asList("CSE107"))));
        courseRepository.save( new Course("Linear Algebra and Complex Veriable","MAT205","Mathematical and Physical Sciences","0",new ArrayList<String>(Arrays.asList("MAT102"))));
        courseRepository.save( new Course("Data Structures","CSE207","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE205"))));
        courseRepository.save( new Course("Numerical Methods","CSE225","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE105","MAT102"))));
        courseRepository.save( new Course("Engineering Physics-II (Introductory Quantum Physics)","PHY209","Mathematical and Physical Sciences","0",new ArrayList<String>(Arrays.asList("MAT205"))));
        courseRepository.save( new Course("Algorithms","CSE245","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE207"))));
        courseRepository.save( new Course("Signals and Systems","CSE248","Department of Computer Seience and Engineering","0",new ArrayList<String>(Arrays.asList("CSE109","MAT205"))));
        courseRepository.save( new Course("Electronic Circuits","CSE251","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE109"))));
        courseRepository.save( new Course("Database Systems","CSE301","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE205"))));
        courseRepository.save( new Course("Operating Systems","CSE325","Department of Computer Seience and Engineering","1",new ArrayList<String>()));
        courseRepository.save( new Course("Digital Logic Design","CSE345","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE205","CSE251"))));
        courseRepository.save( new Course("Data Communications","CSE350","Department of Computer Seience and Engineering","0",new ArrayList<String>(Arrays.asList("CSE251","CSE248"))));
        courseRepository.save( new Course("Computer Architecture","CSE360","Department of Computer Seience and Engineering","0",new ArrayList<String>(Arrays.asList("CSE325","CSE345"))));
        courseRepository.save( new Course("Artificial Intelligence","CSE365","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE245"))));
        courseRepository.save( new Course("Compiler Design","CSE375","Department of Computer Seience and Engineering","0",new ArrayList<String>()));
        courseRepository.save( new Course("Computer Networks","CSE405","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE245","CSE350"))));
        courseRepository.save( new Course("Social and Professional Issues in Computing","CSE498","Department of Computer Seience and Engineering","0",new ArrayList<String>()));
        courseRepository.save( new Course("Software Engineering and Information System Design","CSE411","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE301"))));
        courseRepository.save( new Course("Microprocessors and Microcontrollers","CSE442","Department of Computer Seience and Engineering","1",new ArrayList<String>(Arrays.asList("CSE360"))));

        //adding some course description
        courseDescriptionRepository.save(new CourseDescription( "CSE350","01","08:30 - 10:00","ST","AB2-405","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE350","02","11:50 - 01:20","T","226","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE350","02","11:50 - 01:20","R","225","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE350","03","11:50 - 01:20","MW","AB2-205","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE360","01","08:30 - 10:00","SR","112","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE360","02","10:10 - 11:40","SR","112","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE360","03","08:30 - 10:00","MW","435","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE365","01","10:10 - 11:40","ST","AB1-601","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE365","02","01:30 - 03:00","T","436","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE365","02","01:30 - 03:00","S","AB2-503","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE365","03","10:10 - 11:40","MW","109","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE375","01","11:50 - 01:20","ST","115","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE375","02","03:10 - 04:40","SR","Reading Course","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE375","03","01:30 - 03:00","TR","109","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE498","01","08:30 - 10:00","MW","221","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE498","02","03:10 - 04:40","SR","337","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE411","01","10:10 - 11:40","T","AB2-304","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE411","01","10:10 - 11:40","R","AB2-304","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE411","02","10:10 - 11:40","MW","AB1-301","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE411","03","01:30 - 03:00","TR","AB2-205","40"));
        courseDescriptionRepository.save(new CourseDescription( "GEN201","01","01:30 - 03:00","MW","219","40"));
        courseDescriptionRepository.save(new CourseDescription( "GEN201","02","01:30 - 03:00","SR","550 (Lecture Gallery)","40"));
        courseDescriptionRepository.save(new CourseDescription( "STA102","01","01:30 - 03:00","MW","AB1-302","40"));
        courseDescriptionRepository.save(new CourseDescription( "STA102","02","03:10 - 04:40","TR","AB1-302","40"));
        courseDescriptionRepository.save(new CourseDescription( "STA102","03","01:30 - 03:00","ST","AB1-402","40"));
        courseDescriptionRepository.save(new CourseDescription( "STA102","04","11:50 - 01:20","MW","AB1-402","40"));
        courseDescriptionRepository.save(new CourseDescription( "STA102","05","11:50 - 01:20","MW","AB1-302","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE205","01","10:10 - 11:40","ST","532","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE205","02","01:30 - 03:00","ST","532","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE205","03","10:10 - 11:40","MW","532","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE205","04","01:30 - 03:00","MW","532","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT205","01","01:30 - 03:00","ST","437","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT205","02","11:50 - 01:20","MW","437","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT205","03","10:10 - 11:40","ST","AB2-503","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT205","04","08:30 - 10:00","MW","436","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT205","05","08:30 - 10:00","ST","AB1-502","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT205","06","01:30 - 03:00","MW","AB1-502","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE225","01","10:10 - 11:40","MW","212","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE225","02","11:50 - 01:20","MW","212","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE225","03","10:10 - 11:40","T","AB2-405","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE225","03","10:10 - 11:40","R","AB2-404","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE225","04","01:30 - 03:00","T","212","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE225","04","01:30 - 03:00","R","107","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE225","05","08:30 - 10:00","MW","AB1-301","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE251","01","08:30 - 10:00","TR","AB1-301","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE251","02","01:30 - 03:00","TR","AB1-501","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE251","03","01:30 - 03:00","MW","TBA","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE325","01","08:30 - 10:00","MW","108","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE325","02","11:50 - 01:20","T","AB2-203","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE325","02","11:50 - 01:20","R","114","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE325","03","08:30 - 10:00","SR","AB1-701","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE325","04","03:10 - 04:40","MW","AB1-501","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","01","08:30 - 10:00","MW","223","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","10","01:30 - 03:00","MW","AB2-503","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","11","10:10 - 11:40","T","336","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","11","10:10 - 11:40","R","219","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","12","01:30 - 03:00","S","AB1-602","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","12","01:30 - 03:00","T","115","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","13","08:30 - 10:00","MW","115","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","14","03:10 - 04:40","ST","225","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","15","01:30 - 03:00","MW","AB1-602","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","16","08:30 - 10:00","ST","113","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","17","10:10 - 11:40","S","AB1-602","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","17","10:10 - 11:40","R","532","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","18","03:10 - 04:40","MW","227","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","19","10:10 - 11:40","S","AB2-203","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","19","10:10 - 11:40","R","AB1-401","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","02","08:30 - 10:00","ST","241","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","20","11:50 - 01:20","R","336","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","20","11:50 - 01:20","T","AB2-204","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","21","03:10 - 04:40","S","241","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","21","03:10 - 04:40","R","225","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","22","03:10 - 04:40","MW","AB2-204","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","23","08:30 - 10:00","MW","AB1-201","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","03","03:10 - 04:40","MW","221","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","04","08:30 - 10:00","MW","111","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","05","11:50 - 01:20","MW","AB2-303","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","06","11:50 - 01:20","MW","222","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","07","08:30 - 10:00","R","223","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","07","08:30 - 10:00","T","226","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","08","08:30 - 10:00","TR","AB2-404","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","09","01:30 - 03:00","S","223","40"));
        courseDescriptionRepository.save(new CourseDescription( "ENG101","09","01:30 - 03:00","T","221","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT101","01","01:30 - 03:00","MW","AB1-202","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT101","10","11:50 - 01:20","MW","AB1-502","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT101","12","11:50 - 01:20","S","TBA","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT101","13","08:30 - 10:00","S","TBA","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT101","02","11:50 - 01:20","ST","AB1-302","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT101","03","01:30 - 03:00","TR","AB1-302","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT101","04","11:50 - 01:20","ST","AB1-602","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT101","05","03:10 - 04:40","SR","AB1-602","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT101","06","11:50 - 01:20","MW","AB1-301","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT101","07","11:50 - 01:20","TR","AB1-402","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT101","08","11:50 - 01:20","S","AB1-402","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT101","08","11:50 - 01:20","R","AB1-302","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT101","09","10:10 - 11:40","ST","AB1-502","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE105","01","08:30 - 10:00","ST","110","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE105","02","11:50 - 01:20","MW","108","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE105","03","11:50 - 01:20","ST","110","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE105","04","08:30 - 10:00","MW","630 (Software Engineering Lab)","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT104","01","11:50 - 01:20","MW","358","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT104","10","11:50 - 01:20","SR","AB2-204","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT104","12","03:10 - 04:40","T","TBA","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT104","02","01:30 - 03:00","SR","359","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT104","03","11:50 - 01:20","ST","433","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT104","04","01:30 - 03:00","T","432","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT104","04","01:30 - 03:00","R","219","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT104","05","01:30 - 03:00","R","437","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT104","05","01:30 - 03:00","S","AB1-302","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT104","06","11:50 - 01:20","MW","AB1-501","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT104","07","11:50 - 01:20","ST","531","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT104","08","03:10 - 04:40","MW","532","40"));
        courseDescriptionRepository.save(new CourseDescription( "MAT104","09","01:30 - 03:00","TR","AB2-404","40"));
        courseDescriptionRepository.save(new CourseDescription( "CHE109","01","08:30 - 10:00","SR","109","40"));
        courseDescriptionRepository.save(new CourseDescription( "CHE109","02","11:50 - 01:20","TR","AB1-301","40"));
        courseDescriptionRepository.save(new CourseDescription( "CHE109","03","03:10 - 04:40","MW","107","40"));
        courseDescriptionRepository.save(new CourseDescription( "CHE109","04","08:30 - 10:00","T","102","40"));
        courseDescriptionRepository.save(new CourseDescription( "CHE109","04","08:30 - 10:00","R","108","40"));
        courseDescriptionRepository.save(new CourseDescription( "CHE109","05","10:10 - 11:40","TR","102","40"));
        courseDescriptionRepository.save(new CourseDescription( "CHE109","06","11:50 - 01:20","SR","113","40"));
        courseDescriptionRepository.save(new CourseDescription( "CHE109","07","10:10 - 11:40","SR","212","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE109","01","08:30 - 10:00","ST","221","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE109","02","01:30 - 03:00","MW","AB1-201","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE109","03","11:50 - 01:20","MW","AB2-302","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE109","04","11:50 - 01:20","S","AB2-203","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE109","04","11:50 - 01:20","R","AB1-401","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE109","05","10:10 - 11:40","MW","358","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE109","07","03:10 - 04:40","SR","AB2-203","40"));
        //adding the labs
        courseDescriptionRepository.save(new CourseDescription( "CSE365LAB","01","10:10 - 12:10","R","638","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE365LAB","02","01:30 - 03:30","R","638","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE365LAB","03","01:30 - 03:30","M","638","40"));

        courseDescriptionRepository.save(new CourseDescription( "CSE411LAB","01","10:10 - 12:10","S","638","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE411LAB","02","08:00 - 10:00","T","638","40"));
        courseDescriptionRepository.save(new CourseDescription( "CSE411LAB","03","04:50 - 06:50","T","638","40"));





        //adding some demo students
        studentRepository.save(new Student( "Jeremy", "G. Ingram", "JeremyGIngram@rhyta.com", "2016-1-60-100",new ArrayList<CourseExtended>( Arrays.asList(
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
                new CourseExtended("CSE345" )
        )
        )));
        studentRepository.save(new Student( "Erin","D. Gaither", "ErinDGaither@teleworm.us", "2016-1-60-101",
                new ArrayList<CourseExtended>( Arrays.asList(
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
                ))));
        studentRepository.save(new Student( "Minhazul", "Hayat Khan", "minhaz1217@gmail.com", "2016-1-60-102",
                new ArrayList<CourseExtended>( Arrays.asList(
                        new CourseExtended("")
                ))));
        studentRepository.save(new Student( "John", "A. Davis", "JohnADavis@armyspy.com ", "2016-1-60-103",
                new ArrayList<CourseExtended>( Arrays.asList(
                        new CourseExtended( "MAT101","2"),
                        new CourseExtended( "ENG101","2"),
                        new CourseExtended( "CSE411","1")
                ))));


        System.out.println("DB Details: " + " Users: "+ userRepository.count() + " Courses: "+ courseRepository.count() + " Course Description: " + courseDescriptionRepository.count() + " Students: " + studentRepository.count());
        return ("Details: " + " Users: "+ userRepository.count() + " Courses: "+ courseRepository.count() + " Course Description: " + courseDescriptionRepository.count() + " Students: " + studentRepository.count());

    }
    
    @Override
    public void run(String... args) throws Exception {

        deleteAll();

        Role userRole = roleRepository.findRoleByRole("USER");
        if(userRole == null){
            Role newRole = new Role();
            newRole.setRole("USER");
            roleRepository.save( newRole );
        }
        Role adminRole = roleRepository.findRoleByRole("ADMIN");
        if(adminRole == null){
            Role newRole = new Role();
            newRole.setRole("ADMIN");
            roleRepository.save( newRole );
        }

        //this.courseDescriptionRepository.save(new CourseDescription( "CSE405","1","08:30 - 10:00","MW", "112","40"));
        loadAll();


        //showing some messages to verify that everything went ok
        System.out.println("DB Details: " + " Users: "+ userRepository.count() + " Courses: "+ courseRepository.count() + " Course Description: " + courseDescriptionRepository.count() + " Students: " + studentRepository.count());
        //System.out.println(courseRepository.count());
        
        System.out.println("DB LOADED SUCCESSFULLY");
    }
    
}
