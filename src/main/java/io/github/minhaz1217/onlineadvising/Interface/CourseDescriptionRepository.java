package io.github.minhaz1217.onlineadvising.Interface;

import io.github.minhaz1217.onlineadvising.models.CourseDescription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDescriptionRepository extends MongoRepository<CourseDescription, String> {
    public CourseDescription findCourseDescriptionByName(String name);
    public CourseDescription findCourseDescriptionByCodeAndSec(String code);
}
