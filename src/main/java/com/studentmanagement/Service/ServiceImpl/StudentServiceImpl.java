package com.studentmanagement.Service.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.CustomException.StudentNotFoundException;
import com.studentmanagement.DTO.StudentDTO;
import com.studentmanagement.Entity.Student;
import com.studentmanagement.Repository.StudentRepository;
import com.studentmanagement.Service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    
    private ModelMapper modelMapper;
    
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;  // This will be injected by Spring
    }

    @Override
    public StudentDTO save(StudentDTO dto) {
        Student student = modelMapper.map(dto, Student.class);
        Student saved = studentRepository.save(student);
        return modelMapper.map(saved, StudentDTO.class);
    }

    @Override
    public StudentDTO getById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO update(Long id, StudentDTO dto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());

        Student updated = studentRepository.save(existing);
        return modelMapper.map(updated, StudentDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Cannot delete, student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    public long countStudents() {
        return studentRepository.count();
    }

    @Override
    public List<StudentDTO> findByName(String name) {
        return studentRepository.findByNameIgnoreCase(name).stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findByAgeGreaterThan(int age) {
        return studentRepository.findByAgeGreaterThan(age).stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findByNameStartingWith(String prefix) {
        return studentRepository.findByNameStartingWithIgnoreCase(prefix).stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> searchByNameKeyword(String keyword) {
        return studentRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findAllSortedByNameAsc() {
        return studentRepository.findAllByOrderByNameAsc().stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }
}