package com.studentmanagement.Service;

import java.util.List;

import com.studentmanagement.DTO.StudentDTO;

public interface StudentService {

	StudentDTO save(StudentDTO dto);
	StudentDTO getById(Long id);
	List<StudentDTO> findAll();
	StudentDTO update(Long id, StudentDTO dto);
	void delete(Long id);
	boolean existsByEmail(String email);
	long countStudents();
	List<StudentDTO> findByName(String name);
	List<StudentDTO> findByAgeGreaterThan(int age);
	List<StudentDTO> findByNameStartingWith(String prefix);
	List<StudentDTO> searchByNameKeyword(String keyword);
	List<StudentDTO> findAllSortedByNameAsc();
}