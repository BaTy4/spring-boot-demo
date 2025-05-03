package com.example.demo.Controller;

import com.example.demo.Models.Course;
import com.example.demo.Repository.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course());
        return "create-course.html";
    }

    @PostMapping
    public String createCourse(@ModelAttribute Course course,
                               @RequestParam(required = false) String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            course.setImageUrl(imageUrl);
        }
        courseRepository.save(course);
        return "redirect:/courses";
    }

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "course-dashboard";
    }
}