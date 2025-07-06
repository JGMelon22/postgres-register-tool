package com.joaog.br.postgres_register_tool.mapper;

import com.joaog.br.postgres_register_tool.dto.StudentRequest;
import com.joaog.br.postgres_register_tool.dto.StudentResponse;
import com.joaog.br.postgres_register_tool.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    Student toEntity(StudentRequest student);

    StudentResponse toResponse(Student student);

    ArrayList<StudentResponse> toResponse(Iterable<Student> students);

    @Mapping(target = "id", ignore = true)
    void applyUpdate(StudentRequest studentRequest, @MappingTarget Student student);
}
