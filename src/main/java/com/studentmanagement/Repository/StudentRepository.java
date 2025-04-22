package com.studentmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.studentmanagement.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	boolean existsByEmail(String email);

    @Query("select name from Student where name = :name")
    List<Student> findByName(@Param("name") String name);

    List<Student> findByAgeGreaterThan(int age);

    List<Student> findByNameStartingWith(String prefix);

    List<Student> findByNameContainingIgnoreCase(String keyword);

    List<Student> findAllByOrderByNameAsc();
    
    // Custom query methods
    List<Student> findByNameStartingWithIgnoreCase(String prefix);
    
    List<Student> findByNameIgnoreCase(String name);
}
