package com.example.demo.Controller;

import com.example.demo.Models.Course;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StudentController {

    @GetMapping("/dashboard")
    public String studentDashboard(Model model,
                                   HttpServletRequest request,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        String requestURI = request.getRequestURI();
        System.out.println("Current Request URI: " + requestURI);  // Debugging output
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("requestURI", requestURI);
        return "dashboard";
    }


    private final CourseRepository courseRepository;

    public StudentController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/dashboard/{courseName}")
    public String courseDashboard(@PathVariable String courseName,
                                  Model model,
                                  @AuthenticationPrincipal UserDetails userDetails) {
        Course course = courseRepository.findByName(courseName)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("course", course);
        return "course-dashboard";
    }




    @GetMapping("/dashboard/s_notifications")
    public String notifications(Model model,
                                @AuthenticationPrincipal UserDetails userDetails,
                                HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        model.addAttribute("requestURI", requestURI);
        model.addAttribute("username", userDetails.getUsername());
        System.out.println(requestURI);
        return "notifications";
    }


//    @GetMapping("/dashboard/s_courses")
//    public String vacancies(Model model,
//                            @AuthenticationPrincipal UserDetails userDetails,
//                            HttpServletRequest request) {
//        String requestURI = request.getRequestURI();
//        model.addAttribute("requestURI", requestURI);
//        model.addAttribute("username", userDetails.getUsername());
//        return "create-course";
//    }


    @GetMapping("/dashboard/resume")
    public String resume() {
        return "dashboard";
    }
}
