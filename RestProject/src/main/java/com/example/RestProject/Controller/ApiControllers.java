package com.example.RestProject.Controller;

import com.example.RestProject.Models.Employer;
import com.example.RestProject.Repo.EmployerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
public class ApiControllers {
    @Autowired
    private EmployerRepo employerRepo ;
    @GetMapping(value = "/")
    public String home() {
    return "home" ;

    }

    @GetMapping(value="/employers")
    public List<Employer> getUsers() {


        return employerRepo.findAll() ;
    }


    @GetMapping(value="/employers/{id}")
    public Object getUsersId(@PathVariable Long id) {

        try {
            Employer empl = employerRepo.findById(id).get();
            return empl;
        } catch (Exception e) {
            return "not found " ;
        }

    }
    @PostMapping(value="/save")
    public String saveEmployer(@RequestBody Employer emp) {
       employerRepo.save(emp);
       return "saved" ;
    }

    @PutMapping(value = "/update/{id}")
    public String updateEmployer(@PathVariable Long id, @RequestBody Employer emp) {

        try {
            Employer updatedEmp = employerRepo.findById(id).get();
            updatedEmp.setName(emp.getName());
            updatedEmp.setLastName(emp.getLastName());
            updatedEmp.setAccountNum(emp.getAccountNum());
            updatedEmp.setGrade(emp.getGrade());
            updatedEmp.setAddress(emp.getAddress());
            employerRepo.save(updatedEmp);
            return "update complete";
        }


        catch (Exception e) {
            return "not found " ;
        }



    }

    @DeleteMapping(value="/delete/{id}")
    public String deleteEmployer(@PathVariable long id)
    {
        try {
        Employer deletedEmp = employerRepo.findById(id).get();

          employerRepo.delete(deletedEmp);
          return "deleted user with id:"+id ;

      }
      catch (Exception e){
      return "no user found with id:"+id ; }


    }



}
