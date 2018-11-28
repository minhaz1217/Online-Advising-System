package io.github.minhaz1217.onlineadvising.Interface;

import io.github.minhaz1217.onlineadvising.models.Course;
import io.github.minhaz1217.onlineadvising.models.CourseDescription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDescriptionRepository extends MongoRepository<CourseDescription, String> {
    //public CourseDescription findCourseDescriptionByName(String name);
    public List<CourseDescription> findCourseDescriptionsByCode(String code);
    public CourseDescription findCourseDescriptionById(String id);

}
