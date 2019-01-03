/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising.Interface;

import io.github.minhaz1217.onlineadvising.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.util.List;

/**
 *
 * @author Minhaz
 */

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {



    List<Course> findAllByOrderByCodeAsc();


    Course findCourseByName(String name);
    Course findCourseByCode(String code);
    Course findCourseById(String id);
    List<Course> findCoursesByCodeOrCodeOrCode(String code, String s2, String s3);
    List<Course> findCoursesByCodeOrCode(String code, String s2);
}
