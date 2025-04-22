package com.studentmanagement.Mapper;

import org.mapstruct.Mapper;

import com.studentmanagement.DTO.StudentDTO;
import com.studentmanagement.Entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	
    StudentDTO toDTO(Student student);
    
    Student toEntity(StudentDTO dto);
}
