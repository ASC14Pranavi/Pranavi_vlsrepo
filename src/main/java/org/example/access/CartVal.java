package org.example.access;
import org.example.model.Cart;
import org.example.model.Course;

public class CartVal {
    private Cart cart = new Cart();

    public void addCourseToCart(Course course) {
        cart.addCourse(course);
    }

    public void removeCourseFromCart(int courseId) {
        cart.removeCourse(courseId);
    }

    public Cart getCart() {
        return cart;
    }
}
