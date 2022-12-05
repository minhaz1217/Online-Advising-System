# Online Course advising system

This was my first project built using Java Spring Boot and mongodb. I built it for a course at university. I was new in both spring and in mongodb. The main purpose of this project was to use different types of validation and learn about spring and mongodb and try to make it look presentable. 

### A course advising system that lets the students select courses and sections for their next semester with time conflict detection and maintaining prerequisites.

### Live URL - [https://online-advising-system.minhazul.com/](https://online-advising-system.minhazul.com/)

Demo 1: The landing page

![Landing Page](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc1.png)


Demo 2: The dashboard of student, notice the already done advising

![Dashboard](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc2.png)

Demo 3: Student Dashboard, previously completed courses

![Dashboard with completed courses](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc3.png)

Demo 4: Entry to the advising, Students have to insert their id, currently the format is "year-semester-department-roll"

![Advising](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc4.png)

Demo 5: The courses that the student is able to take, it is generated from their already completed course list

![Selecting a section](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc5.png)

Demo 6: There is a time conflict in their selected section, both CSE350 and CSE360 conflicts in Sunday 8:30

![Time Conflict](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc6.png)

Demo 7: Time conflict between lab and a course

![Time Conflict 2](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc7.png)

Demo 8: After the advising the student's dashboard

![After the advising](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc8.png)

Demo 9: The full student list, table, notice each row can be deleted or editted

![Student list](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc9.png)

Demo 10: Sample of editing a student's information. All the fields are filled with the current data.

![Edit a student's information](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc10.png)

Demo 11: Course Description table, It maintains the full name of the course, the department which offers it and whether it has lab or not, and most importantly if the course has a prerequisite.

![Course list](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc11.png)

Demo 12: Sample of editing a course.

![Editing a course](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc12.png)

Demo 13: Adding a course, notice the add prerequisite.

![Adding a course](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc13.png)

Demo 14: All the courses offered in that semester with their section, time room number and current number of seats available.

![Sections offered](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc15.png)

Demo 15: Sample of editing a course description.

![Edit Course Description](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc16.png)

Demo 16: Adding a course description

![Adding a course description](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc17.png)

Demo 17: Sample of the 404 page

![404 page](https://raw.githubusercontent.com/minhaz1217/Online-Advising-System/master/images/sc18.png)

# Steps to build and run the project

### At first change the mongodb connection string in the file
`nano src/main/resources/application.properties`

## Without Docker

### Run these command to get the jar file
`mvn compile package`

### Use this to run the project.
`java -jar target/online-advising-1.0.0.jar`

## With Docker

### Build the image with
`docker build -t i_online_advising_system .`

### Run the docker container
`docker run -d --name online-advising-system --network minhazul-net -p 8080:8080 i_online_advising_system`


## Use this to seed the database for the project to work correctly
`curl http://localhost:8080/resetall/MINHAZUL_HAYAT_KHAN_EWU`