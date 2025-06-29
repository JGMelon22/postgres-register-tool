package com.joaog.br.postgres_register_tool.controller;

import com.joaog.br.postgres_register_tool.dto.StudentRequest;
import com.joaog.br.postgres_register_tool.dto.StudentResponse;
import com.joaog.br.postgres_register_tool.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<StudentResponse>>> getAllStudents() {
        return studentService.getAllStudents()
                .thenApply(students -> students.isEmpty()
                        ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                        : ResponseEntity.status(HttpStatus.OK).body(students));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<StudentResponse>> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id)
                .thenApply(student -> student != null
                        ? ResponseEntity.status(HttpStatus.OK).body(student)
                        : ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public CompletableFuture<StudentResponse> createStudent(@RequestBody @Valid StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<StudentResponse>> updateStudent(@PathVariable(value = "id") int id,
                                                                            @RequestBody @Valid StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest)
                .thenApply(updatedStudent -> updatedStudent != null
                        ? ResponseEntity.ok(updatedStudent)
                        : ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable(value = "id") int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
