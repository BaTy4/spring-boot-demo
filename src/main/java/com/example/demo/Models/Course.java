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
    @NotBlank(message = "Заголовок обязателен лол")
    private String name;

    @Column(name = "language")
    @NotBlank(message = "Выбери язык программирования")
    private String language;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "filename")
    private String filename;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] fileData;

    @ElementCollection
    @CollectionTable(name = "course_materials", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "material_url")
    private Set<String> materials = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    private Set<AppUser> enrolledUsers = new HashSet<>();

    public Course() {}

    public Course(String name, String description, String filename, String language, byte[] fileData) {
        this.name = name;
        this.description = description;
        this.filename = filename;
        this.language = language;
        this.fileData = fileData;
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

    public String getFilename() { return filename; }
    public void setFilename (String filename) { this.filename = filename; }

    public String getLanguage(){return this.language;}
    public void setLanguage(String language){this.language = language;}

    public byte[] getFileData(){return this.fileData;}
    public void setFileData(byte[] fileData){this.fileData = fileData;}
}