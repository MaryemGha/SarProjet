package com.example.RestProject.Models;

import jakarta.persistence.*;
   @Entity
public class Task {


        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private int id ;

        @Column
        private String description;

        @Column
        private int empId;

           public int getId() {
                   return id;
           }

           public void setId(int id) {
                   this.id = id;
           }

           public String getDescription() {
                   return description;
           }

           public void setDescription(String description) {
                   this.description = description;
           }

           public int getEmpId() {
                   return empId;
           }

           public void setEmpId(int empId) {
                   this.empId = empId;
           }
   }
