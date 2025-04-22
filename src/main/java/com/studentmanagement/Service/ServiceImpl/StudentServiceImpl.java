package com.studentmanagement.Service.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

    private StudentRepository studentRepo;
    private ModelMapper modelMapper;

    @Override
    public StudentDTO save(StudentDTO dto) {
        Student student = modelMapper.map(dto, Student.class);
        Student saved = studentRepo.save(student);
        return modelMapper.map(saved, StudentDTO.class);
    }

    @Override
    public StudentDTO getById(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepo.findAll().stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO update(Long id, StudentDTO dto) {
        Student existing = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());

        Student updated = studentRepo.save(existing);
        return modelMapper.map(updated, StudentDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!studentRepo.existsById(id)) {
            throw new StudentNotFoundException("Cannot delete, student not found with id: " + id);
        }
        studentRepo.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return studentRepo.existsByEmail(email);
    }

    @Override
    public long countStudents() {
        return studentRepo.count();
    }

    @Override
    public List<StudentDTO> findByName(String name) {
        return studentRepo.findByNameIgnoreCase(name).stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findByAgeGreaterThan(int age) {
        return studentRepo.findByAgeGreaterThan(age).stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findByNameStartingWith(String prefix) {
        return studentRepo.findByNameStartingWithIgnoreCase(prefix).stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> searchByNameKeyword(String keyword) {
        return studentRepo.findByNameContainingIgnoreCase(keyword).stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findAllSortedByNameAsc() {
        return studentRepo.findAllByOrderByNameAsc().stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }
}