package org.example.model;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Course> courses;

    public Cart() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(int courseId) {
        this.courses.removeIf(course -> course.getCourseId() == courseId);
    }

    public List<Course> getCourses() {
        return courses;
    }

}
