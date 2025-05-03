package com.example.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Course name is required")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @ElementCollection
    @CollectionTable(name = "course_materials", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "material_url")
    private Set<String> materials = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    private Set<AppUser> enrolledUsers = new HashSet<>();

    public Course() {}

    public Course(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Set<String> getMaterials() { return materials; }
    public void setMaterials(Set<String> materials) { this.materials = materials; }
    public Set<AppUser> getEnrolledUsers() { return enrolledUsers; }
    public void setEnrolledUsers(Set<AppUser> enrolledUsers) { this.enrolledUsers = enrolledUsers; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}