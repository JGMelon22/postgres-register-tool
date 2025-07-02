package com.joaog.br.postgres_register_tool.service;

import com.joaog.br.postgres_register_tool.dto.StudentRequest;
import com.joaog.br.postgres_register_tool.dto.StudentResponse;
import com.joaog.br.postgres_register_tool.exception.StudentNotFoundException;
import com.joaog.br.postgres_register_tool.mapper.StudentMapper;
import com.joaog.br.postgres_register_tool.model.Student;
import com.joaog.br.postgres_register_tool.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        Iterable<Student> students = studentRepository.findAll();
        return studentMapper.toResponse(students);
    }

    @Transactional(readOnly = true)
    public StudentResponse getStudentById(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        return studentMapper.toResponse(student);
    }

    @Transactional
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        Student student = studentMapper.toEntity(studentRequest);
        Student savedStudent = studentRepository.save(student);

        return studentMapper.toResponse(savedStudent);
    }

    @Transactional
    public StudentResponse updateStudent(int id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        studentMapper.applyUpdate(studentRequest, student);

        Student savedStudent = studentRepository.save(student);

        return studentMapper.toResponse(savedStudent);
    }

    @Transactional
    public void deleteStudent(int id) {
        if (!studentRepository.existsById(id))
            throw new StudentNotFoundException(id);

        studentRepository.deleteById(id);
    }

}
