package org.example.access;
import org.example.model.Course;
import org.example.JdbcConn.Dbconn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class courseVal {
    private Dbconn dbConn = new Dbconn();

    // Retrieve all courses
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Course";
        try (Connection conn = dbConn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("author_name"),
                        rs.getInt("duration_hours"),
                        rs.getBoolean("availability")
                );
                courses.add(course);
            }
        }
        return courses;
    }

    // Add a new course
    public void addCourse(Course course) throws SQLException {
        String query = "INSERT INTO Course (course_name, author_name, duration_hours, availability) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getAuthorName());
            stmt.setInt(3, course.getDurationHours());
            stmt.setBoolean(4, course.isAvailability());
            stmt.executeUpdate();
        }
    }

    // Search for courses by name or author
    public List<Course> searchCourses(String searchTerm) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Course WHERE course_name LIKE ? OR author_name LIKE ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("author_name"),
                        rs.getInt("duration_hours"),
                        rs.getBoolean("availability")
                );
                courses.add(course);
            }
        }
        return courses;
    }

    // Retrieve a course by its ID
    public Course getCourseById(int courseId) throws SQLException {
        String query = "SELECT * FROM Course WHERE course_id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("author_name"),
                        rs.getInt("duration_hours"),
                        rs.getBoolean("availability")
                );
            }
        }
        return null;
    }
}
