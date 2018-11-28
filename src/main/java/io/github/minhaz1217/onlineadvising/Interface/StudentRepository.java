/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising.Interface;

import io.github.minhaz1217.onlineadvising.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Minhaz
 */
@Repository
public interface StudentRepository extends MongoRepository<Student, String>{
    //Student getStudentByStudent_id(String Student_id);
    Student getStudentByEmail(String studentId);

    @Query(value = "{'student_id':?0}")
    Student findStudentByStudentCode(String code);

    List<Student> findAllByOrderByEmailAsc();
}
