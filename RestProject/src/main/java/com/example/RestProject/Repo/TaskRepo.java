package com.example.RestProject.Repo;
import com.example.RestProject.Models.Employer;
import com.example.RestProject.Models.Task;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task,Long> {
}
