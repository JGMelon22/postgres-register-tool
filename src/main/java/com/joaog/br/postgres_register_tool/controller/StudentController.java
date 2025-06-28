package com.joaog.br.postgres_register_tool.controller;

import com.joaog.br.postgres_register_tool.dto.StudentRequest;
import com.joaog.br.postgres_register_tool.dto.StudentResponse;
import com.joaog.br.postgres_register_tool.model.Student;
import com.joaog.br.postgres_register_tool.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<StudentResponse>> getAllStudents() {
        ArrayList<StudentResponse> students = studentService.getAllStudents();
        return students.isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable int id) {
        Optional<StudentResponse> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudentResponse createStudent(@RequestBody @Valid StudentRequest studentRequest) {
        return studentService.saveProduct(studentRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable(value = "id") int id,
                                                         @RequestBody @Valid StudentRequest studentRequest) {
        Optional<StudentResponse> updatedStudent = studentService.updateStudent(id, studentRequest);

        return updatedStudent.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable(value = "id") int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
