/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising;
import io.github.minhaz1217.onlineadvising.Interface.CourseRepository;
import io.github.minhaz1217.onlineadvising.Interface.UserRepository;
import io.github.minhaz1217.onlineadvising.models.Course;
import java.util.ArrayList;
import java.util.Arrays;

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
    
    public DbSeeder(CourseRepository courseRepository,
                    UserRepository userRepository){
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }
    
    
    @Override
    public void run(String... args) throws Exception {

        User minhaz = new User("minhaz2", "minhaz2", "ROLE_ADMIN", "ROLE_USER");
        User admin = new User("admin", "admin", "ROLE_ADMIN", "ROLE_USER");
        this.courseRepository.deleteAll();
        this.userRepository.deleteAll();
        this.userRepository.save(minhaz);
        this.userRepository.save(admin);

        ArrayList<String>hi = new ArrayList<>();
        hi.add("CSE248");
        Course cse411 = new Course(
            "Software Development",
            "CSE411",
            "Computer Science And Enginnering",
            1,
                new ArrayList<String>(
                Arrays.asList("Req_1",
                        "Req_2",
                        "Req_3"))
            );
        Course cse350 = new Course(
            "Data Communication",
            "CSE350",
            "Computer Science And Enginnering",
            0,
            hi
        );
        Course cse360 = new Course(
            "Computer Architecture",
            "CSE360",
            "Computer Science And Enginnering",
            0,
            new ArrayList<String>()
        );
        
        
        Course cse442 = new Course(
            "Micro Controller",
            "CSE442",
            "Computer Science And Enginnering",
            1, 
            new ArrayList<String>()
        );
        System.out.println(courseRepository.count());
        
        //List<Course> courses = Arrays.asList(cse411, cse360,cse350);
        
        this.courseRepository.save(cse411);
        this.courseRepository.save(cse350);
        this.courseRepository.save(cse360);
        this.courseRepository.save(cse442);
        this.courseRepository.save(new Course(
                "Operating System",
                "CSE325",
                "Computer Science And Enginnering",
                1,
                new ArrayList<String>(
                        Arrays.asList("OS_REQ_1",
                                "OS_REQ_2"
                        ))
        ));
         System.out.println(courseRepository.count());
        
        System.out.println("DB LOADED SUCCESSFULLY");
    }
    
}
