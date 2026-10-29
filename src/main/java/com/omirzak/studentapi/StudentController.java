package com.omirzak.studentapi;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of("app", "Student API", "author", "Erbarak Omirzak", "studentId", "37403");
    }

    @GetMapping("/api/students")
    public List<Student> all() {
        return repository.findAll();
    }

    @GetMapping("/api/students/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return repository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> notFound(id));
    }

    @PostMapping("/api/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        student.setId(null);
        return repository.save(student);
    }

    @PutMapping("/api/students/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Student data) {
        return repository.findById(id).<ResponseEntity<?>>map(existing -> {
            existing.setName(data.getName());
            existing.setStudentCode(data.getStudentCode());
            existing.setEmail(data.getEmail());
            return ResponseEntity.ok(repository.save(existing));
        }).orElseGet(() -> notFound(id));
    }

    @DeleteMapping("/api/students/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return notFound(id);
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> notFound(Long id) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Student with id " + id + " not found"));
    }
}
