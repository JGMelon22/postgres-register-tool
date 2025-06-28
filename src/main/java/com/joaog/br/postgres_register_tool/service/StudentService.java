package com.joaog.br.postgres_register_tool.service;

import com.joaog.br.postgres_register_tool.dto.StudentRequest;
import com.joaog.br.postgres_register_tool.dto.StudentResponse;
import com.joaog.br.postgres_register_tool.mapper.StudentMapper;
import com.joaog.br.postgres_register_tool.model.Student;
import com.joaog.br.postgres_register_tool.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public ArrayList<StudentResponse> getAllStudents() {
        Iterable<Student> students = studentRepository.findAll();
        return studentMapper.toResponse(students);
    }

    public Optional<StudentResponse> getStudentById(int id) {
        return studentRepository.findById(id)
                .map(studentMapper::toResponse);
    }

    public StudentResponse saveProduct(StudentRequest studentRequest) {
        Student student = studentMapper.toEntity(studentRequest);
        Student savedStudent = studentRepository.save(student);

        return studentMapper.toResponse(savedStudent);
    }

    public Optional<Student> updateStudent(int id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Student with id %d not found", id)));

        studentMapper.applyUpdate(studentRequest, student);
        studentRepository.save(student);

        return Optional.of(student);
    }

    public void deleteStudent(int id) {
        if (!studentRepository.existsById(id))
            throw new RuntimeException(String.format("Student with id %d not found", id));

        studentRepository.deleteById(id);
    }

}
