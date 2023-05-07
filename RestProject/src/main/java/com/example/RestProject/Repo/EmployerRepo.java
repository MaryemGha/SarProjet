package com.example.RestProject.Repo;
import com.example.RestProject.Models.Employer;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepo extends JpaRepository<Employer,Long> {
}
