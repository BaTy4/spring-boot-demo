package com.example.demo.Controller;

import com.example.demo.Models.Course;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.ResourceNotFoundException;
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
                                   @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        return "dashboard"; // Must match template name
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
    public String notifications() {
        return "dashboard";
    }

    @GetMapping("/dashboard/s_vacancies")
    public String vacancies() {
        return "dashboard";
    }

    @GetMapping("/dashboard/s_messages")
    public String messages() {
        return "dashboard";
    }

    @GetMapping("/dashboard/resume")
    public String resume() {
        return "dashboard";
    }
}
