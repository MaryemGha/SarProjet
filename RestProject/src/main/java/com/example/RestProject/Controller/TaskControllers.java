package com.example.RestProject.Controller;

import com.example.RestProject.Models.Employer;
import com.example.RestProject.Models.Task;
import com.example.RestProject.Repo.EmployerRepo;
import com.example.RestProject.Repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskControllers {
    @Autowired
    private TaskRepo taskRepo ;
    private EmployerRepo empRepo ;
    @GetMapping(value = "/task")
    public String getPage() {
        return "home" ;

    }
    @GetMapping(value="/getTask")
    public List<Task> getTasks() {
        return taskRepo.findAll() ;
    }

    @GetMapping(value="/getTask/{id}")
    public Object getTaskId(@PathVariable Long id) {

        try {
            Task task = taskRepo.findById(id).get();
            return task;
        } catch (Exception e) {
            return "not found " ;
        }

    }

    @PostMapping(value="/saveTask")
    public String saveTask(@RequestBody Task task) {
       Long empId=  (long)(task.getEmpId()) ;
       if (empRepo.findById(empId).orElse(null) != null) {
           taskRepo.save(task);
           return "saved";
       }
       else {
           return ("there is no employee with this id "+empId) ;
       }
    }

    @PutMapping(value = "/updateTask/{id}")
    public String updateTask(@PathVariable Long id, @RequestBody Task task) {
        try {


            Task updatedTask = taskRepo.findById(id).get();
            updatedTask.setDescription(task.getDescription());
            updatedTask.setEmpId(task.getEmpId());
            taskRepo.save(updatedTask);
            return "update complete";
        }

        catch (Exception e) {
            return "task not found" ;
        }


    }

    @DeleteMapping(value="/deleteTask/{id}")
    public String deleteEmployer(@PathVariable long id)
    {    try {Task deletedTask = taskRepo.findById(id).get();

            taskRepo.delete(deletedTask);
            return "deleted task with id:"+id ;

        }
        catch (Exception e){
            return "no Task found with id:"+id ; }


    }



}