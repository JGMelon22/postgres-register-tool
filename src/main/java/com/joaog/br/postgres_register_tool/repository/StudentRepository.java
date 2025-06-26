package com.joaog.br.postgres_register_tool.repository;

import com.joaog.br.postgres_register_tool.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
}
