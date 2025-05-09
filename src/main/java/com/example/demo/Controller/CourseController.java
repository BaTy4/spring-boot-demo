package com.example.demo.Controller;

import com.example.demo.Models.Course;
import com.example.demo.Repository.CourseRepository;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class CourseController{

    @Autowired
    private CourseRepository courseRepository;
    private final String uploadDir = "uploads/";

    @GetMapping("/dashboard/courses/new")
    public String showCreateForm(Model model,
                                 HttpServletRequest request){
        String requestURI = request.getRequestURI();
        model.addAttribute("course", new Course());
        model.addAttribute("languages", List.of("Python", "Java", "C++", "JavaScript", "C#", "Golang", "HTML", "CSS"));
        model.addAttribute("requestURI", requestURI);
        return "create_course";
    }

    @PostMapping(value = "/dashboard/courses", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createCourse(@ModelAttribute Course course,
                               @RequestParam("file")MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            course.setFilename(file.getOriginalFilename());
            course.setFileData(file.getBytes());
        }

        courseRepository.save(course);
        return "redirect:/dashboard/courses";
    }

    @GetMapping("/dashboard/courses")
    public String getAllCourses(Model model,
                                HttpServletRequest request){
        String requestURI = request.getRequestURI();
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("requestURI", requestURI);
        return "courses_list";
    }


    @GetMapping("/dashboard/courses/{id}")
    public String getCourse(@PathVariable Long id, Model model){
        Optional<Course> optionalCourse = courseRepository.findById(id);

        if(optionalCourse.isPresent()){
            model.addAttribute("course", optionalCourse.get());
            return "course_details";
        }
        return "redirect:/dashboard/courses";
    }


    @GetMapping("/dashboard/courses/{id}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + id));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + course.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(course.getFileData());
    }
}