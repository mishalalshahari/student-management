package com.studentmanagement.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.DTO.StudentDTO;
import com.studentmanagement.Entity.Student;
import com.studentmanagement.Service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO dto) {
        StudentDTO saved = studentService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO dto) {
        return ResponseEntity.ok(studentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists")
    public boolean existsByEmail(@RequestParam String email) {
        return studentService.existsByEmail(email);
    }

    @GetMapping("/count")
    public long count() {
        return studentService.countStudents();
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<StudentDTO>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(studentService.findByName(name));
    }

    @GetMapping("/above-age")
    public ResponseEntity<List<StudentDTO>> getAboveAge(@RequestParam int age) {
        return ResponseEntity.ok(studentService.findByAgeGreaterThan(age));
    }

    @GetMapping("/starts-with")
    public ResponseEntity<List<StudentDTO>> startsWith(@RequestParam String prefix) {
        return ResponseEntity.ok(studentService.findByNameStartingWith(prefix));
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDTO>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(studentService.searchByNameKeyword(keyword));
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<StudentDTO>> getSortedByName() {
        return ResponseEntity.ok(studentService.findAllSortedByNameAsc());
    }
}

